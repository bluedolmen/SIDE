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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ui.action.GeneratePopUp;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ApplicationModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator;
import com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass.InitializerWizard;
import com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass.InitializerWizardDialog;

public class InitializeModelsStartWizard implements IObjectActionDelegate {

	private IStructuredSelection _selection;
	private Shell currentShell;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		currentShell = targetPart.getSite().getShell();

	}

	public void run(IAction action) {
		IFile classModel = (IFile) _selection.getFirstElement();

		InitializerWizard wiz = new InitializerWizard(classModel);
		WizardDialog wizDial = new InitializerWizardDialog(currentShell, wiz);
		wizDial.open();

		InitializerRegister ini = wiz.getIni();
		boolean side_gene = wiz.isSide_gene();
		if (side_gene) {
			try {
				System.out.println("InitializerWizard.performFinish() Generation"); //$NON-NLS-1$
				final GeneratePopUp generationPopUp;
				Shell activeShell = Display.getDefault().getActiveShell();

				ModelCreator applicationModelInitializer = ini.getInitializers(ApplicationModelInitializer.class).get(InitializerRegister.DEFAULT_INITIALIZER_KEY);
				IFile file = applicationModelInitializer.getNewModelIFile();
				Application newRootObject = (Application) applicationModelInitializer.getNewRootObject();
				generationPopUp = new GeneratePopUp(activeShell, file, newRootObject, newRootObject.getConfiguration(applicationModelInitializer.getModelName()));

				generationPopUp.launch();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}
}
