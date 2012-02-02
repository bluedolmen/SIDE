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
