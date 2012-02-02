package com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.clazz.edit.ui.Messages;
import com.bluexml.side.util.alfresco.tools.ToolingUtils;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPage;

public class InitializerPageWelcome extends AbstractFieldsPage {

	public final static String ID = "page1"; //$NON-NLS-1$

	public InitializerPageWelcome() {
		super(ID);
		this.setDescription(Messages.InitializerPageWelcome_1);
	}

	public void createFieldsControls(Composite composite) {

		createResourceControl(composite, Messages.InitializerPageWelcome_2, Fields.alfresco_home.toString(), RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY);

		Map<String, Object> allowedValues = new HashMap<String, Object>();
		List<IConfigurationElement> allToolingModelLibraryExtensions = ToolingUtils.getAllToolingModelLibraryExtensions();

		for (IConfigurationElement alfV : allToolingModelLibraryExtensions) {
			String attribute = alfV.getAttribute("label");
			allowedValues.put(attribute, attribute);
		}
		createComboControl(composite, Messages.InitializerPageWelcome_3, Fields.alfresco_version.toString(), allowedValues);

		createBooleanFieldControl(composite, Messages.InitializerPageWelcome_4, Fields.generate.toString(), false);
	}

	public enum Fields {
		alfresco_home, alfresco_version, generate
	}

}
