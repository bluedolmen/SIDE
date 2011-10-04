package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import com.bluexml.side.Integration.eclipse.branding.enterprise.Activator;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.NewSIDEProjectCreator.CreateOptions;
import com.bluexml.side.util.libs.eclipse.RunnableWithState;
import com.bluexml.side.util.libs.eclipse.WorkspaceModifyOperation;

public class Wizard extends org.eclipse.jface.wizard.Wizard implements INewWizard {


	// The workbench
	protected IWorkbench currentWorkbench;
	protected WizardNewProjectCreationPage mainPage;
	protected WizardModelPage optionsPage;

	public Wizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setHelpAvailable(false); // TODO have help
		setWindowTitle(Activator.Messages.getString("Wizard.3")); //$NON-NLS-1$
		currentWorkbench = workbench;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		URI location = null;
		if (!mainPage.useDefaults()) {
			URI locationURI = mainPage.getLocationURI();
			location = locationURI;
		}


		final IProject newProject = mainPage.getProjectHandle();
		CreateOptions createOptions = optionsPage.toOptions();
		final NewSIDEProjectCreator sideProjectCreator = new NewSIDEProjectCreator(newProject, createOptions, location);

		// create the new project operation
		WorkspaceModifyOperation op = sideProjectCreator.createWorkspaceModifyOperation();
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return true;
		} catch (InvocationTargetException e) {
			// ie.- one of the steps resulted in a core exception
			Throwable t = e.getTargetException();
			if (t instanceof CoreException) {
				if (((CoreException) t).getStatus().getCode() == IResourceStatus.CASE_VARIANT_EXISTS) {
					MessageDialog.openError(getShell(), Activator.Messages.getString("Wizard.11"), //$NON-NLS-1$
							NLS.bind(Activator.Messages.getString("Wizard.12"), //$NON-NLS-1$
									newProject.getName()));
				} else {
					ErrorDialog.openError(getShell(), Activator.Messages.getString("Wizard.13"), //$NON-NLS-1$
							null, ((CoreException) t).getStatus());
				}
			} else {
				// CoreExceptions are handled above, but unexpected runtime
				// exceptions and errors may still occur.
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, t.toString(), t));
				MessageDialog.openError(getShell(), Activator.Messages.getString("Wizard.14"), NLS //$NON-NLS-1$
						.bind(Activator.Messages.getString("Wizard.15"), t.getMessage())); //$NON-NLS-1$
			}
			return false;
		}

		while (!op.getState().equals(RunnableWithState.STATE.finished)) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				optionsPage.setErrorMessage(e.getLocalizedMessage());
				return false;
			}
		}
		return true;
	}

	@Override
	public void addPages() {
		super.addPages();
		// Main Page :
		mainPage = new WizardNewProjectCreationPage(Activator.Messages.getString("Wizard.4")); //$NON-NLS-1$
		mainPage.setImageDescriptor(Activator.getImageDescriptor("$nl$/icons/createTopcasedProjectWizard.gif")); //$NON-NLS-1$
		mainPage.setTitle(Activator.Messages.getString("Wizard.6")); //$NON-NLS-1$
		mainPage.setDescription(Activator.Messages.getString("Wizard.7")); //$NON-NLS-1$
		addPage(mainPage);

		// Page on model choice and SIDE options
		optionsPage = new WizardModelPage();
		optionsPage.setTitle(Activator.Messages.getString("Wizard.9")); //$NON-NLS-1$
		optionsPage.setDescription(Activator.Messages.getString("Wizard.10")); //$NON-NLS-1$
		addPage(optionsPage);
	}

}
