package com.bluexml.side.view.edit.ui.actions;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.edit.ui.utils.InitView;
import com.bluexml.side.view.edit.ui.utils.InternalModification;

public class InitializeView extends Action implements ISelectionChangedListener {

	protected AbstractView selectedObject;
	private EditingDomain domain;

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
			if (object instanceof AbstractView) {
				selectedObject = (AbstractView) object;
			} else {
				return false;
			}
		}
		return selectedObject != null;
	}

	@Override
	public void run() {
		super.run();
		doAction((AbstractView) selectedObject);
	}

	private void doAction(AbstractView av) {
		InternalModification.dontMoveToDisabled();
		try {
			CompoundCommand cmd = new CompoundCommand();
			if (av instanceof ComposedView) {
				ComposedView cv = (ComposedView) av;
				for (AbstractView v : cv.getInnerView()) {
					if (v instanceof AbstractViewOf) {
						Command cmd_sub = InitView.init((AbstractViewOf) v, domain);
						if (cmd_sub != null) {
							cmd.append(cmd_sub);
						}
					}
				}
			} else if (av instanceof AbstractViewOf) {
				Command cmd_sub = InitView.init((AbstractViewOf) av, domain);
				if (cmd_sub != null) {
					cmd.append(cmd_sub);
				}
			}
			if (!cmd.isEmpty()) {
//				if (!cmd.canExecute()) {
//					System.out.println("Command NOT EXECUTABLE");
//				}
				domain.getCommandStack().execute(cmd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			EcorePlugin.INSTANCE.log("Init failed : " + e.getMessage());
			e.printStackTrace();
			InternalModification.moveToDisabled();
		}
		InternalModification.moveToDisabled();
	}

	@Override
	public String getText() {
		return "Initiliaze";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}

}
