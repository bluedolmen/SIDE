package com.bluexml.side.application.ui.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class CreateGenerationConfiguration implements IObjectActionDelegate {

	private ISelection selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.getFirstElement() instanceof IFile) {
				IFile rwm_model = (IFile) iss.getFirstElement();

				Shell shell = new Shell();
				ApplicationDialog dialog = new ApplicationDialog(shell, rwm_model);
				dialog.setBlockOnOpen(false);
				if (dialog.open() == Window.OK) {

				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection _selection) {
		selection = _selection;
	}

}
