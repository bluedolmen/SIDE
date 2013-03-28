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
package com.bluexml.side.clazz.alfresco.reverse.action.wizard.pages;

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
