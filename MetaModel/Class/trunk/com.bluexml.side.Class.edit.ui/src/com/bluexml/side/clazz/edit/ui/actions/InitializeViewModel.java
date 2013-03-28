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

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator.ASK_USER;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ViewModelInitializer;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class InitializeViewModel implements IObjectActionDelegate {

	private IStructuredSelection _selection;

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IFile classModel = (IFile) _selection.getFirstElement();
		Job j = new Job(classModel);
		j.schedule();
	}

	class Job extends AbstractIFileJob {
		public Job(IFile iFile) {
			super("Initialize View Model", iFile);
		}

		@Override
		public void execute() throws Exception {
			ClassPackage cp = openModel(iFile);
			InitializerRegister initilizerList = InitializerRegister.getDefaultInitializerRegister(iFile, cp, ASK_USER.ASK);
			for (ModelCreator initializer : initilizerList.getInitializers(ViewModelInitializer.class).values()) {
				initializer.initialize();
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		//Nothing
	}

	private static ClassPackage openModel(IFile classModel) throws IOException {
		EList<?> l = EResourceUtils.openModel(classModel);
		return (ClassPackage) l.get(0);
	}
}
