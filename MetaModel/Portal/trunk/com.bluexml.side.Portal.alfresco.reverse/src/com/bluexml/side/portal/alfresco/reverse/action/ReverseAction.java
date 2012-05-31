package com.bluexml.side.portal.alfresco.reverse.action;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.portal.alfresco.reverse.action.wizard.ReverseFromAlfrescoWizard;

public class ReverseAction implements IObjectActionDelegate {

	private IStructuredSelection _selection;
	private Shell currentShell;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		currentShell = targetPart.getSite().getShell();

	}

	public void run(IAction action) {
		IFolder alfrescoXML = (IFolder) _selection.getFirstElement();

		ReverseFromAlfrescoWizard wizard = new ReverseFromAlfrescoWizard(alfrescoXML);
		WizardDialog wd = new WizardDialog(currentShell, wizard);

		wd.open();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;

	}

}
