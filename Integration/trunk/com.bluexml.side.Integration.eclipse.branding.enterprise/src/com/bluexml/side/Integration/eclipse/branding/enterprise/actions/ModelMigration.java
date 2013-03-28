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
package com.bluexml.side.Integration.eclipse.branding.enterprise.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;

import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration.ModelMigrationDialog;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration.ModelMigrationWizard;
import com.bluexml.side.integration.eclipse.builder.actions.ActionOnSideProject;


public class ModelMigration extends ActionOnSideProject {

	@Override
	public void run(IProject project) {
		
		Wizard w = new ModelMigrationWizard(project);
		WizardDialog wd = new ModelMigrationDialog(currentShell, w);
		
		wd.open();
	}

	

}
