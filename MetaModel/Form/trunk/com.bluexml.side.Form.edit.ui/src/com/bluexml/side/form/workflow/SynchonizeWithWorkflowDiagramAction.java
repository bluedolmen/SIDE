/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.form.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.workflow.utils.SynchronizeWorkflowFormWithClass;

public class SynchonizeWithWorkflowDiagramAction extends Action implements ISelectionChangedListener {
	protected EObject selectedObject;
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
			if (object instanceof FormCollection) {
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
		doAction((FormCollection) selectedObject);
	}

	private void doAction(FormCollection fc) {
		if (fc instanceof WorkflowFormCollection) {
			SynchronizeWorkflowFormWithClass sw = new SynchronizeWorkflowFormWithClass(domain);
			sw.synchronize(fc);
			CompoundCommand c = sw.getCc();
			List<Command> invalideCommands = getInvalideCommands(c);
			System.out.println("INVALIDE command :" + invalideCommands.size());

			//			Command c = WorkflowSynchronizationUtils.synchronizeProcess((WorkflowFormCollection) fc, domain);
			domain.getCommandStack().execute(c);
		}
	}

	public static List<Command> getInvalideCommands(CompoundCommand cc) {
		List<Command> commandList = new ArrayList<Command>();
		if (cc.getCommandList().size() == 0) {
			commandList.add(cc);
		}
		for (Command command : cc.getCommandList()) {
			if (command instanceof CompoundCommand) {
				commandList.addAll(getInvalideCommands((CompoundCommand) command));
			} else {
				if (command.canExecute()) {
					System.out.println("command can execute :" + command);
				} else {
					System.out.println("command invalide ! :" + command);
					commandList.add(command);
				}
			}
		}
		return commandList;
	}

	@Override
	public String getText() {
		return "Synchronize with Workflow Diagram";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
