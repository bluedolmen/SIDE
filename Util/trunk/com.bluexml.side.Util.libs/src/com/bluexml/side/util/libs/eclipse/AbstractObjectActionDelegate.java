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
package com.bluexml.side.util.libs.eclipse;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;

import com.bluexml.side.util.libs.ecore.EResourceUtils;

public abstract class AbstractObjectActionDelegate implements IObjectActionDelegate {

	protected IStructuredSelection _selection;

	public void run(IAction action) {
		try {

			WorkspaceJob job = getJob();
			job.schedule();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	protected abstract WorkspaceJob getJob();

	protected static EObject openModel(IFile classModel) throws IOException {
		EList<?> l = EResourceUtils.openModel(classModel);
		return (EObject) l.get(0);
	}

	public IFile getFirstSelection() {
		return (IFile) _selection.getFirstElement();
	}

}
