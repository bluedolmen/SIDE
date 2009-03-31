package com.bluexml.side.form;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.utils.ClassInitialization;
import com.bluexml.side.form.utils.FieldTransformation;
import com.bluexml.side.form.utils.FormDiagramUtils;
import com.bluexml.side.form.utils.InternalModification;

public class ExpandModelChoice extends Action implements
ISelectionChangedListener {

	protected EObject selectedObject;
	private EditingDomain domain;
	private Clazz c;
	ModelChoiceField mcf;
	
	public ExpandModelChoice(Clazz p_c, ModelChoiceField p_mcf) {
		super();
		c = p_c;
		mcf = p_mcf;
	}

	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			setEnabled(updateSelection((IStructuredSelection) event
					.getSelection()));
		} else {
			setEnabled(false);
		}
	}

	public boolean updateSelection(IStructuredSelection selection) {
		selectedObject = null;
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof ModelChoiceField && !(object instanceof Reference)) {
				selectedObject = (EObject) object;
			} else {
				return false;
			}
		}

		return selectedObject != null;
	}
	
	@Override
	public void run() {
		super.run();
		doAction();
	}
	
	@SuppressWarnings("unchecked")
	private void doAction() {
		if (c != null && mcf != null) {
			InternalModification.dontMoveToDisabled();
			Reference ref = formFactory.eINSTANCE.createReference();
			FieldTransformation.transform(mcf, ref);
			
			Form form = createFormForRealClass(c,mcf);
			FormClass formClass = (FormClass) form.getRoot();
			ref.getTarget().add(formClass);
			
			// If association class
			Form formAssoClass = null;
			FormClass formClassAssoClass = null;
			if (mcf.getAssociation_class() != null) {
				formAssoClass = createFormForRealClass(mcf.getAssociation_class(),mcf);
				formClassAssoClass = (FormClass) formAssoClass.getRoot();
				ref.getAssociation_formClass().add(formClassAssoClass);
			}
			
			// Add the Form	
			CompoundCommand cc = new CompoundCommand();
			Command addFormCmd = AddCommand.create(domain, FormDiagramUtils.getParentFormCollection(mcf), formPackage.eINSTANCE.getFormCollection_Forms(), form);
			cc.append(addFormCmd);
			
			// We seek virtualized fields of this mcf to change link to the reference
			List<VirtualField> listVf = FormDiagramUtils.getVirtualizedFields(mcf);
			for (VirtualField vf : listVf) {
				cc.append(SetCommand.create(domain, vf, formPackage.eINSTANCE.getVirtualField_Link(), ref));
			}
			
			// Commands :
			// Delete Model Choice Field
			Command delCmd = RemoveCommand.create(domain, (Object)mcf);
			cc.append(delCmd);
			
			// Add the form class if needed :
			if (formAssoClass != null) {
				Command addFormCmdAssoClass = AddCommand.create(domain, FormDiagramUtils.getParentFormCollection(mcf), formPackage.eINSTANCE.getFormCollection_Forms(), formAssoClass);
				cc.append(addFormCmdAssoClass);
				cc.append(ClassInitialization.initializeClass(formClassAssoClass, domain));
			}
			// Add the reference
			Command addRefCmd = AddCommand.create(domain, mcf.eContainer(), formPackage.eINSTANCE.getFormGroup_Children(), ref, ((FormGroup)mcf.eContainer()).getChildren().lastIndexOf(mcf));
			cc.append(addRefCmd);

			cc.append(ClassInitialization.initializeClass(formClass, domain));
			domain.getCommandStack().execute(cc);
			InternalModification.moveToDisabled();
		}
		
	}
	
	protected Form createFormForRealClass(Clazz p_class, ModelChoiceField p_mcf) {
		Form form =  formFactory.eINSTANCE.createForm();
		FormClass formClass = formFactory.eINSTANCE.createFormClass();
		form.setRoot(formClass);
		
		formClass.setReal_class(p_class);
		Random random = new Random();
		int pick = random.nextInt(Integer.MAX_VALUE);
		
		formClass.setId(p_class.getName() + "_" + pick);
		
		if (p_class.getTitle() != null && p_class.getTitle().length() > 0) {
			formClass.setLabel(p_class.getTitle());
			form.setName(p_class.getTitle() + " ref from " + ((FormGroup)p_mcf.eContainer()).getLabel() + " (" + p_mcf.getLabel() + ")");
		} else {
			formClass.setLabel(p_class.getName());
			form.setName(p_class.getName() + " ref from " + ((FormGroup)p_mcf.eContainer()).getLabel() + " (" + p_mcf.getLabel() + ")");
		};
		return form;
	}
	
	
	
	@Override
	public String getText() {
		String label = ((c.getTitle() != null && c.getTitle().length() > 0) ? c.getTitle() : c.getName());
		if (c.isIsAbstract()) {
			label += " (Abstract Type)";
		}
		return label;
	}
	
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
