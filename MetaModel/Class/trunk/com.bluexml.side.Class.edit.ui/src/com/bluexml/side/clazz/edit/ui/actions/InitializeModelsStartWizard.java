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

				ModelCreator applicationModelInitializer = ini.getInitializers(ApplicationModelInitializer.class).get(""); //$NON-NLS-1$
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
