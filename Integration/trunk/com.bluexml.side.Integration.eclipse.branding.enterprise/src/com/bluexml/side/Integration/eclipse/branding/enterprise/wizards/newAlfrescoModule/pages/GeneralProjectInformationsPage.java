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
