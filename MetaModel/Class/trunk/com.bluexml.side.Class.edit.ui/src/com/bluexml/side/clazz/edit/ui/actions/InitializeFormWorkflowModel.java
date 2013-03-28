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
package com.bluexml.side.clazz.edit.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;

public class InitializeFormWorkflowModel implements IObjectActionDelegate {

	private ISelection _selection;

	/**
	 * Constructor for Action1.
	 */
	public InitializeFormWorkflowModel() {
		super();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IStructuredSelection ss = (IStructuredSelection) _selection;
		IFile workflowModel = (IFile) ss.getFirstElement();
		Job j = new Job(workflowModel);
		j.schedule();
	}

	class Job extends AbstractIFileJob {
		public Job(IFile iFile) {
			super("Initilize Form Workflow model", iFile);
		}

		@Override
		public void execute() throws Exception {
			InitializerRegister reg = InitializerRegister.getFormWorkFlowInitializerRegister(iFile, ModelInitializer.ASK_USER.ASK);
			reg.initialize();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		_selection = selection;
	}

	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		//Nothing
	}

}
