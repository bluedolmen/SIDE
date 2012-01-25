package com.bluexml.side.util.framework.tools;

import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.util.alfresco.tools.AlfrescoModelBaseVersionChooser;

public class ImportPage extends AlfrescoModelBaseVersionChooser {

	protected ImportPage() {
		super("importBuildInLibrary");
		this.setTitle("SIDE Library Importation");
		this.setDescription("Select SIDE Library to import");
	}

	@Override
	public void createFieldsControls(Composite composite) {
		createAlfrescoLibComboBox(composite, Fields.library.toString());
	}

	public enum Fields {
		library;
	}
}
