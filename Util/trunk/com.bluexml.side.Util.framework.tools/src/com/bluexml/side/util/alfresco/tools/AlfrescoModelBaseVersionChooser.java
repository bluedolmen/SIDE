package com.bluexml.side.util.alfresco.tools;

import java.util.ArrayList;
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
		Map<String, String> libs = new TreeMap<String, String>();
		
		List<IConfigurationElement> configurationElements = ToolingUtils.getAllToolingModelLibraryExtensions();

		for (IConfigurationElement iConfigurationElement : configurationElements) {
			String label = iConfigurationElement.getAttribute("label");
			libs.put(label, label);
		}
		
		libs.put("", "");
		
		ArrayList<String> allowedValues = new ArrayList<String>();
		allowedValues.addAll(libs.values());

		createComboControl(composite, "model library", fieldId, allowedValues);
	}
}
