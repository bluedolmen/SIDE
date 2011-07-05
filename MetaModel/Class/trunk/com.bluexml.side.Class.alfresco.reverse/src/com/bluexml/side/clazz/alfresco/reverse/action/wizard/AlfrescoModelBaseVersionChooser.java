package com.bluexml.side.clazz.alfresco.reverse.action.wizard;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.clazz.alfresco.reverse.library.ModelLibrary;
import com.bluexml.side.clazz.alfresco.reverse.library.ModelLibrary.Libraries;
import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPage;

public abstract class AlfrescoModelBaseVersionChooser extends AbstractFieldsPage {

	protected AlfrescoModelBaseVersionChooser(String pageName) {
		super(pageName);
	}

	protected void createAlfrescoLibComboBox(Composite composite,String fieldId) {
		Map<String, String> libs = new TreeMap<String, String>();
		Libraries[] values2 = ModelLibrary.Libraries.values();
		for (Libraries libraries : values2) {
			String l1 = libraries.toString();
			libs.put(l1, l1);			
		}
		libs.put("", "");
		
		ArrayList<String> allowedValues = new ArrayList<String>();
		allowedValues.addAll(libs.values());

		createComboControl(composite, "model library", fieldId, allowedValues);
	}
}
