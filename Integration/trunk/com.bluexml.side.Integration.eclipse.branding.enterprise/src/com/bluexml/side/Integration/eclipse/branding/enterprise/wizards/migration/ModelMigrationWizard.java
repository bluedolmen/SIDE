package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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

public class ModelMigrationWizard extends Wizard implements IWorkbenchWizard {

	List<IProject> projects = null;

	public ModelMigrationWizard(List<IProject> projects) {
		this.projects = projects;
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
			this.getContainer().run(true, false, runnable);
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
		List<IProject> project2Update = this.projects;
		// copy projects before as requested
		Boolean fieldValueBoolean = page.getFieldValueBoolean(GeneralProjectMigration.Fields.copybefore.toString());
		if (fieldValueBoolean) {
			project2Update = new ArrayList<IProject>();
			String newNameParam = page.getFieldValueString(GeneralProjectMigration.Fields.newName.toString());

			if (StringUtils.trimToNull(newNameParam) == null) {
				newNameParam = GeneralProjectMigration.DEFAULT_VALUE_NEWNAME;
			}
			for (IProject source : this.projects) {
				IProjectDescription description = source.getDescription();
				String name = description.getName();
				String newName = newNameParam + name;
				description.setName(newName);
				source.copy(description, true, monitor2);
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
				project2Update.add(project);

			}
		}

		// execute models conversion
		for (IProject source : project2Update) {
			ModelMigrationHelper.updateProject(source, target, false, monitor2);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		addPage(new GeneralProjectMigration());
	}

}
