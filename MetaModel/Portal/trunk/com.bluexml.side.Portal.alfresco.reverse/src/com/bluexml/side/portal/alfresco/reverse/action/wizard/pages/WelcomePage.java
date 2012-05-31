package com.bluexml.side.portal.alfresco.reverse.action.wizard.pages;

import java.io.File;

import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.util.alfresco.tools.AlfrescoModelBaseVersionChooser;
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

	public void createFieldsControls(Composite composite) {
		File file = null;
		if (alfrescoXML != null) {
			file = new File(alfrescoXML.getRawLocationURI());
			String absolutePath = file.getAbsolutePath();
			values.put(Fields.root.toString(), absolutePath);
		}
		createAlfrescoLibComboBox(composite, Fields.library.toString());

		
		
		createResourceControl(composite, "WEB-INF/classes/alfresco", Fields.root.toString(), RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY);
		createResourceControl(composite, "output folder", Fields.output.toString(), RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY);
	}

	public enum Fields {
		library, root, output;
	}
}
