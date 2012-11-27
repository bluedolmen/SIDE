package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import com.bluexml.side.Integration.eclipse.branding.enterprise.actions.ModelMigrationHelper;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration.pages.GeneralProjectMigration;
import com.bluexml.side.util.alfresco.tools.ToolingUtils;
import com.bluexml.side.util.libs.IFileHelper;

public class ModelMigrationWizard extends Wizard implements IWorkbenchWizard {

	List<IProject> projects = null;

	public ModelMigrationWizard(List<IProject> projects) {
		this.projects = projects;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		List list = selection.toList();
		for (Object object : list) {
			System.out.println("ModelMigrationWizard.init() :" + object);
		}

	}

	@Override
	public boolean performFinish() {
		System.out.println("ModelMigrationWizard.performFinish()");
		GeneralProjectMigration page = (GeneralProjectMigration) getContainer().getCurrentPage();
		String libraryId = page.getFieldValueString(GeneralProjectMigration.Fields.library.toString());
		// import library if project do not exists in workspace
		try {
			IProject target = ToolingUtils.importLibrary(libraryId);

			// execute models conversion

			for (IProject source : this.projects) {
				ModelMigrationHelper.updateProject(source, target, true);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return false;
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
