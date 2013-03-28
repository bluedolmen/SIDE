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
package com.bluexml.side.util.alfresco.tools;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPage;

public abstract class AlfrescoModelBaseVersionChooser extends AbstractFieldsPage {

	protected AlfrescoModelBaseVersionChooser(String pageName) {
		super(pageName);
	}

	protected void createAlfrescoLibComboBox(Composite composite,String fieldId) {
		Map<String, Object> allowedValues = new TreeMap<String, Object>();
		
		List<IConfigurationElement> configurationElements = ToolingUtils.getAllToolingModelLibraryExtensions();

		for (IConfigurationElement iConfigurationElement : configurationElements) {
			String label = iConfigurationElement.getAttribute("label");
			String techVersion = iConfigurationElement.getAttribute("id");
			allowedValues.put(label, techVersion);
		}
		
		allowedValues.put("", "");
		
		
		

		createComboControl(composite, "model library", fieldId, allowedValues);
	}
	
	public class Lib {
		String techVersion="";
		String label="";
	}
}
