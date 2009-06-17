package com.bluexml.side.side.view.edit.ui.actions;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;


import com.bluexml.side.side.view.edit.ui.utils.FieldTransformation;
import com.bluexml.side.side.view.edit.ui.utils.InternalModification;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldContainer;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;




public class TransformField  extends Action implements
ISelectionChangedListener {

	protected Field selectedObject;
	private EditingDomain domain;
	protected String transFormTo;
	
	public TransformField(String p_className, Field f) {
		super();
		transFormTo = p_className;
		selectedObject = f;
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
			if (object instanceof Field) {
				selectedObject = (Field) object;
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
		InternalModification.dontMoveToDisabled();
		try {
			InternalModification.dontMoveToDisabled();
			Method m = ViewFactory.eINSTANCE.getClass().getDeclaredMethod("create" + transFormTo, (Class[])null);
			Field f = (Field) m.invoke(ViewFactory.eINSTANCE, (Object[])null);
			FieldTransformation.transform(selectedObject,f);
			
			Command addMcfCmd = AddCommand.create(domain, selectedObject.eContainer(), ViewPackage.eINSTANCE.getFieldContainer_Children(), f,((FieldContainer)selectedObject.eContainer()).getChildren().lastIndexOf(selectedObject));
			Command delCmd = RemoveCommand.create(domain, (Object)selectedObject);
			CompoundCommand cc = new CompoundCommand();
			cc.append(addMcfCmd);
			cc.append(delCmd);
			domain.getCommandStack().execute(cc);
			InternalModification.moveToDisabled();
		} catch (Exception e) {
			e.printStackTrace();
			EcorePlugin.INSTANCE.log("Transformation failed : " + e.getMessage());
			e.printStackTrace();
			InternalModification.moveToDisabled(); 
		}
		InternalModification.moveToDisabled(); 
	}

	@Override
	public String getText() {
		return transFormTo;
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}

}
