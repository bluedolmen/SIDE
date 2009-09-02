package com.bluexml.side.form.clazz;

import java.util.Iterator;
import java.util.List;

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

import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.common.utils.FieldTransformation;
import com.bluexml.side.form.common.utils.FormDiagramUtils;
import com.bluexml.side.form.common.utils.InternalModification;
import com.bluexml.side.util.libs.ui.UIUtils;

public class CollapseReferenceAction  extends Action implements
ISelectionChangedListener {

	protected EObject selectedObject;
	private EditingDomain domain;

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
			if (object instanceof Reference) {
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
		doAction((Reference) selectedObject);
	}

	@SuppressWarnings("unchecked")
	private void doAction(Reference ref) {
		InternalModification.dontMoveToDisabled();
		ModelChoiceField mcf = FormFactory.eINSTANCE.createModelChoiceField();
		FieldTransformation.transform(ref,mcf);

		CompoundCommand cc = new CompoundCommand();
		Command delCmd = RemoveCommand.create(domain, (Object)ref);
		Command addMcfCmd = AddCommand.create(domain, ref.eContainer(), FormPackage.eINSTANCE.getFormGroup_Children(), mcf, ((FormGroup)ref.eContainer()).getChildren().lastIndexOf(ref));

		// We seek virtualized fields of this ref to change link to the mcf
		List<VirtualField> listVf = FormDiagramUtils.getVirtualizedFields(ref);
		for (VirtualField vf : listVf) {
			cc.append(SetCommand.create(domain, vf, FormPackage.eINSTANCE.getVirtualField_Link(), mcf));
		}

		boolean doWork = UIUtils.showConfirmation("Delete pointed Form Class", "Do your want to delete pointed Form Class?");

		if (doWork) {
			for(FormContainer fc : ref.getTarget()) {
				if (fc instanceof FormClass) {
					Command delFcCmd = RemoveCommand.create(domain, fc);
					cc.append(delFcCmd);
				}
			}
		}

		cc.append(delCmd);
		cc.append(addMcfCmd);
		domain.getCommandStack().execute(cc);
		InternalModification.moveToDisabled();
	}

	@Override
	public String getText() {
		return "Collapse to Model Choice Field";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
