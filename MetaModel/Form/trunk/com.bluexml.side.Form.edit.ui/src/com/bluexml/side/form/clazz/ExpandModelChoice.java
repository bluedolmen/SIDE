package com.bluexml.side.form.clazz;

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
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.clazz.utils.ClassInitialization;
import com.bluexml.side.form.common.utils.FieldTransformation;
import com.bluexml.side.form.common.utils.FormDiagramUtils;
import com.bluexml.side.form.common.utils.InternalModification;

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

	private void doAction() {
		if (c != null && mcf != null) {
			InternalModification.dontMoveToDisabled();
			Reference ref = FormFactory.eINSTANCE.createReference();
			FieldTransformation.transform(mcf, ref);

			FormContainer form = createFormForRealClass(c,mcf);
			FormClass formClass = (FormClass) form;
			ref.getTarget().add(formClass);

			// Add the Form
			CompoundCommand cc = new CompoundCommand();
			Command addFormCmd = AddCommand.create(domain, FormDiagramUtils.getParentFormCollection(mcf), FormPackage.eINSTANCE.getFormCollection_Forms(), form);
			cc.append(addFormCmd);

			// We seek virtualized fields of this mcf to change link to the reference
			List<VirtualField> listVf = FormDiagramUtils.getVirtualizedFields(mcf);
			for (VirtualField vf : listVf) {
				cc.append(SetCommand.create(domain, vf, FormPackage.eINSTANCE.getVirtualField_Link(), ref));
			}

			// Commands :
			// Delete Model Choice Field
			Command delCmd = RemoveCommand.create(domain, (Object)mcf);
			cc.append(delCmd);

			// Add the reference
			Command addRefCmd = AddCommand.create(domain, mcf.eContainer(), FormPackage.eINSTANCE.getFormGroup_Children(), ref, ((FormGroup)mcf.eContainer()).getChildren().lastIndexOf(mcf));
			cc.append(addRefCmd);

			cc.append(ClassInitialization.initializeClass(formClass, domain));
			domain.getCommandStack().execute(cc);
			InternalModification.moveToDisabled();
		}

	}

	protected FormContainer createFormForRealClass(Clazz p_class, ModelChoiceField p_mcf) {
		//FormContainer form =  FormFactory.eINSTANCE.createFormContainer();
		FormClass formClass = FormFactory.eINSTANCE.createFormClass();
		//form.setRoot(formClass);

		formClass.setReal_class(p_class);
		Random random = new Random();
		int pick = random.nextInt(Integer.MAX_VALUE);

		formClass.setId(p_class.getName() + "_" + pick);

		if (p_class.getTitle() != null && p_class.getTitle().length() > 0) {
			formClass.setLabel(p_class.getTitle());
			//form.setName(p_class.getTitle() + " ref from " + ((FormGroup)p_mcf.eContainer()).getLabel() + " (" + p_mcf.getLabel() + ")");
		} else {
			formClass.setLabel(p_class.getName());
			//form.setName(p_class.getName() + " ref from " + ((FormGroup)p_mcf.eContainer()).getLabel() + " (" + p_mcf.getLabel() + ")");
		};
		return formClass;
	}



	@Override
	public String getText() {
		String label = ((c.getTitle() != null && c.getTitle().length() > 0) ? c.getTitle() : c.getName());
		if (c.isAbstract()) {
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
