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
package com.bluexml.side.application.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.util.libs.ui.UIUtils;

public class CreateGenerationConfiguration implements IObjectActionDelegate {

	protected static List<String> inUse = new ArrayList<String>();

	protected ISelection selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.getFirstElement() instanceof IFile) {
				final IFile rwm_model = (IFile) iss.getFirstElement();
				// We open a dialog only if no dialog already open on it
				if (!inUse(rwm_model)) {
					Shell shell = new Shell();

					ApplicationDialog dialog = new ApplicationDialog(shell, rwm_model);
					addFileUnUse(rwm_model);
					dialog.open();
					removeFileUnUse(rwm_model);
				}
			} else {
				UIUtils.showError(Activator.Messages.getString("Erreur.Title.1"), Activator.Messages.getString("Erreur.Msg.1")); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

	protected boolean inUse(IFile model) {
		return inUse.contains(model.getLocation().toOSString());
	}

	protected void addFileUnUse(IFile model) {
		inUse.add(model.getLocation().toOSString());
	}

	protected void removeFileUnUse(IFile model) {
		inUse.remove(model.getLocation().toOSString());
	}

	public void selectionChanged(IAction action, ISelection _selection) {
		selection = _selection;
	}

}
