package com.bluexml.side.Integration.eclipse.branding.enterprise.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
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
import com.bluexml.side.integration.eclipse.builder.nature.SIDENature;
import com.bluexml.side.integration.eclipse.builder.nature.SIDENatureWithBuilder;

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
			if (project != null && isSIDEProject(project)) {
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

	public boolean isSIDEProject(IProject project) {
		return hasNature(project, SIDENature.NATURE_ID) || hasNature(project, SIDENatureWithBuilder.NATURE_ID);
	}

	private boolean hasNature(IProject project, String nature) {
		boolean found = false;
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();

			for (int i = 0; i < natures.length; ++i) {
				if (nature.equals(natures[i]))
					found = true;
			}

		} catch (CoreException e) {
		}
		return found;
	}
}
