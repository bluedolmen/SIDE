package com.bluexml.side.form.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.common.utils.FieldTransformation;
import com.bluexml.side.form.common.utils.InternalModification;

public class TransformFieldAction extends Action implements
ISelectionChangedListener {

	protected Field selectedObject;
	private EditingDomain domain;
	protected String transFormTo;
	
	public TransformFieldAction(String p_className, Field f) {
		super();
		transFormTo = p_className;
		selectedObject = f;
	}
	
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			setEnabled(updateSelection((IStructuredSelection) event.getSelection()));
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
		doAction((Field) selectedObject);
	}
	
	@SuppressWarnings("unchecked")
	private void doAction(Field field) {
		// Transform field in a selected one
		try {
			InternalModification.dontMoveToDisabled();
			Method m = FormFactory.eINSTANCE.getClass().getDeclaredMethod("create" + transFormTo, (Class[])null);
			Field f = (Field) m.invoke(FormFactory.eINSTANCE, (Object[])null);
			FieldTransformation.transform(field,f);
			
			Command addMcfCmd = AddCommand.create(domain, field.eContainer(), FormPackage.eINSTANCE.getFormGroup_Children(), f,((FormGroup)field.eContainer()).getChildren().lastIndexOf(field));
			Command delCmd = RemoveCommand.create(domain, (Object)field);
			CompoundCommand cc = new CompoundCommand();
			cc.append(addMcfCmd);
			cc.append(delCmd);
			domain.getCommandStack().execute(cc);
			InternalModification.moveToDisabled();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String getText() {
		return "to " + transFormTo;
	}
	
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
	
	
}
