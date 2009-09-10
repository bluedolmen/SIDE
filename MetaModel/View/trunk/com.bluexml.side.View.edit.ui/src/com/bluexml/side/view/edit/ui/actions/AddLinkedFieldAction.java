package com.bluexml.side.view.edit.ui.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;
import com.bluexml.side.view.edit.ui.utils.InternalModification;
import com.bluexml.side.view.edit.ui.utils.model.ClassUtils;

public class AddLinkedFieldAction  extends Action implements
ISelectionChangedListener {
	protected EObject toAdd;
	protected AbstractView parent;
	private EditingDomain domain;
	protected List<Association> path;
	
	public AddLinkedFieldAction(EObject a, List<Association> path, AbstractView parent) {
		super();
		toAdd = a;
		this.path = path;
		this.parent = parent;
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
		
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof Attribute) {
				toAdd = (Attribute) object;
			} else {
				return false;
			}
		}

		return toAdd != null;
	}
	
	@Override
	public void run() {
		super.run();
		doAction();
	}
	
	private void doAction() {
		try {
			if (toAdd instanceof Attribute) {
				FieldElement child = null;
				Field f = ClassUtils.getFieldForAttribute((Attribute)toAdd);
				f.getPath().addAll(path);
				
				if (parent instanceof DataTable) {
					Col col = ViewFactory.eINSTANCE.createCol();
					col.getChildren().add(f);
					col.setName(f.getName());
					child = col;
				} else {
					child = f;
				}
				Command cmd = AddCommand.create(domain, parent, ViewPackage.eINSTANCE.getFieldContainer_Children(), child);
				domain.getCommandStack().execute(cmd);
			} else {
				EcorePlugin.INSTANCE.log("Error : Add Linked Field launch on non attribute item.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			EcorePlugin.INSTANCE.log("Get Linked Field : " + e.getMessage());
			e.printStackTrace();
			InternalModification.moveToDisabled(); 
		}
	}

	@Override
	public String getText() {
		String text = "";
		if (toAdd != null && toAdd instanceof Association) {
			Association asso = (Association) toAdd;
			text = asso.getTitle();
			if (text == null || text.length() == 0) {
				text = asso.getName();
			}
		} else if (toAdd != null && toAdd instanceof Attribute) {
			Attribute att = (Attribute) toAdd;
			text = att.getTitle();
			if (text == null || text.length() == 0) {
				text = att.getName();
			}
		}
		return text;
	}
	
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
