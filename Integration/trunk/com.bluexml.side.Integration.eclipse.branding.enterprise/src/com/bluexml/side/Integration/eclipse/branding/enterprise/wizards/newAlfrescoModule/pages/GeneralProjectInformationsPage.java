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
package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newAlfrescoModule.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.util.alfresco.tools.ToolingUtils;
import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPage;

public class GeneralProjectInformationsPage extends AbstractFieldsPage {
	public final static String ID = "page1";

	public String getArtifactId() {
		return getFieldValueString(Fields.artifactId.toString());
	}

	public String getGroupId() {
		return getFieldValueString(Fields.groupId.toString());
	}

	public String getArtifactName() {
		return getFieldValueString(Fields.artifactName.toString());
	}

	public String getArtifactVersion() {
		return getFieldValueString(Fields.artifactVersion.toString());
	}

	public String getModuleType() {
		return getFieldValueString(Fields.archetypeId.toString());
	}

	public GeneralProjectInformationsPage() {
		super(ID);
		this.setTitle("Create Alfresco extension project");
		this.setDescription("Enter Maven project attributes");
	}

	public void createFieldsControls(Composite composite) {

		Map<String, Object> allowedValues = new HashMap<String, Object>();
		List<IConfigurationElement> allToolingModuleExtensions = ToolingUtils.getAllToolingModuleExtensions();

		for (IConfigurationElement iConfigurationElement : allToolingModuleExtensions) {
			String label = iConfigurationElement.getAttribute("label");
			allowedValues.put(label, label);
		}

		createComboControl(composite, "archetypeId", Fields.archetypeId.toString(), allowedValues);

		createTextFieldControl(composite, "groupId", Fields.groupId.toString());

		createTextFieldControl(composite, "artifactId", Fields.artifactId.toString());

		createTextFieldControl(composite, "artifactName", Fields.artifactName.toString());

		createTextFieldControl(composite, "artifactVersion", Fields.artifactVersion.toString());

	}

	public enum Fields {
		artifactId, groupId, artifactName, artifactVersion, archetypeId;
	}
}
