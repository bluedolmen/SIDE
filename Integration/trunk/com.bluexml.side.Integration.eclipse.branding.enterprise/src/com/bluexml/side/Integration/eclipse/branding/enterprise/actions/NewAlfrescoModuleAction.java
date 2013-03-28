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
package com.bluexml.side.Integration.eclipse.branding.enterprise.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newAlfrescoModule.NewModuleDialog;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newAlfrescoModule.NewModuleWizard;
import com.bluexml.side.integration.eclipse.builder.SIDEBuilderUtil;


public class NewAlfrescoModuleAction implements IObjectActionDelegate {

	private ISelection selection;
	private Shell currentShell;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		currentShell = targetPart.getSite().getShell();

	}

	public void run(IAction action) {
		System.out.println("action start " + selection);
		if (selection instanceof IStructuredSelection) {
			IProject project = null;
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();

				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
				}
			}
			if (project != null && SIDEBuilderUtil.isSIDEProject(project)) {
				System.out.println("selection resolved as Project :" + project);

				System.out.println("open Wizard");

				try {
					Wizard w = new NewModuleWizard(project);
					WizardDialog wd = new NewModuleDialog(currentShell, w);
					wd.open();
				} catch (Throwable e1) {
					e1.printStackTrace();
					MessageDialog.openError(currentShell.getShell(), "Error", e1.getMessage());
				}
				
			}
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	
}
