package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration.pages;

import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.util.alfresco.tools.AlfrescoModelBaseVersionChooser;

public class GeneralProjectMigration extends AlfrescoModelBaseVersionChooser {

	public GeneralProjectMigration() {
		super("importBuildInLibrary");
		this.setTitle("SIDE Library Importation");
		this.setDescription("Select SIDE Library to import");
	}

	public void createFieldsControls(Composite composite) {
		createAlfrescoLibComboBox(composite, Fields.library.toString());
	}

	public enum Fields {
		library;
	}
}
