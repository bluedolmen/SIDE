package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import com.bluexml.side.Integration.eclipse.branding.enterprise.Activator;
import com.bluexml.side.Integration.eclipse.branding.enterprise.actions.ModelMigrationHelper;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration.pages.GeneralProjectMigration;
import com.bluexml.side.util.alfresco.tools.ToolingUtils;
import com.bluexml.side.util.libs.IFileHelper;

public class ModelMigrationWizard extends Wizard implements IWorkbenchWizard {

	IProject project = null;

	public ModelMigrationWizard(IProject project) {
		this.project = project;
		setNeedsProgressMonitor(true);

	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

	@Override
	public boolean performFinish() {
		System.out.println("ModelMigrationWizard.performFinish()");
		final GeneralProjectMigration page = (GeneralProjectMigration) getContainer().getCurrentPage();
		final String libraryId = page.getFieldValueString(GeneralProjectMigration.Fields.library.toString());
		// import library if project do not exists in workspace

		IRunnableWithProgress runnable = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				try {
					execute(page, libraryId, monitor);
				} catch (Exception e) {

					throw new InvocationTargetException(e);
				}
			}
		};

		try {
			this.getContainer().run(true, true, runnable);
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, cause.toString(), cause));
			MessageDialog.openError(getShell(), "Error", NLS.bind("Internal error {0}", cause.getMessage())); //$NON-NLS-1$
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	protected void execute(GeneralProjectMigration page, String libraryId, IProgressMonitor monitor2) throws Exception {

		IProject target = ToolingUtils.importLibrary(libraryId);
		IProject project2Update = this.project;
		// copy projects before as requested
		Boolean projectCopy = page.getFieldValueBoolean(GeneralProjectMigration.Fields.copybefore.toString());
		if (projectCopy) {
			project2Update = null;
			String newNameParam = page.getFieldValueString(GeneralProjectMigration.Fields.newName.toString());

			if (StringUtils.trimToNull(newNameParam) == null) {
				newNameParam = GeneralProjectMigration.DEFAULT_VALUE_NEWNAME;
			}

			IProjectDescription description = project.getDescription();
			String name = description.getName();
			String newName = newNameParam + name;
			description.setName(newName);
			description.setLocationURI(null);
			System.out.println("ModelMigrationWizard.execute() rename project " + project.getName() + " -> " + newName);
			project.copy(description, true, monitor2);
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
			project2Update = project;

		}

		// execute models conversion

		ModelMigrationHelper.updateProject(project2Update, target, libraryId, projectCopy, monitor2);
		monitor2.subTask("Refresh project");
		IFileHelper.refreshFolder(project2Update);
		monitor2.done();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		addPage(new GeneralProjectMigration(project.getName()));
	}

}
