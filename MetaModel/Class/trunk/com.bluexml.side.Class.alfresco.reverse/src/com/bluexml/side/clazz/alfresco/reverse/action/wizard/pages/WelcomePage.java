package com.bluexml.side.clazz.alfresco.reverse.action.wizard.pages;

import java.io.File;

import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.clazz.alfresco.reverse.action.wizard.AlfrescoModelBaseVersionChooser;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;

public class WelcomePage extends AlfrescoModelBaseVersionChooser {
	IFolder alfrescoXML;
	public static final String ID = "welcome";

	public WelcomePage(IFolder alfrescoXML) {
		super(ID);
		this.alfrescoXML = alfrescoXML;
		this.setDescription("description");
		this.setTitle("title");

	}

	@Override
	public void createFieldsControls(Composite composite) {
		File file = null;
		if (alfrescoXML != null) {
			file = new File(alfrescoXML.getRawLocationURI());
			String absolutePath = file.getAbsolutePath();
			values.put(Fields.output.toString(), absolutePath);
		}
		createAlfrescoLibComboBox(composite, Fields.library.toString());

		createResourcesControl(composite, "custom models", Fields.root.toString(), RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY, alfrescoXML, "xml");
		createResourceControl(composite, "output folder", Fields.output.toString(), RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY);
	}

	public enum Fields {
		library, root, output;
	}
}
