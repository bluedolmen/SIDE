package com.bluexml.side.Integration.eclipse.branding.enterprise.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;

import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration.ModelMigrationDialog;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration.ModelMigrationWizard;
import com.bluexml.side.integration.eclipse.builder.actions.ActionOnSideProject;


public class ModelMigration extends ActionOnSideProject {

	@Override
	public void run(List<IProject> projects) {
		
		Wizard w = new ModelMigrationWizard(projects);
		WizardDialog wd = new ModelMigrationDialog(currentShell, w);
		
		wd.open();
	}

	

}
