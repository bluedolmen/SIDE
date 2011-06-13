package com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass.pages;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPage;

public class InitializerPageWelcome extends AbstractFieldsPage {

	public final static String ID = "page1";

	public InitializerPageWelcome() {
		super(ID);
		this.setDescription("This will take your Data Model and initialize other side Model including Application model");
	}

	@Override
	protected void createFieldsControls(Composite composite) {
		//		createRessourceControl(composite, label, id, type);
		createRessourceControl(composite, "Alfresco Home", Fields.alfresco_home.toString(), RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY);

		ArrayList<String> allowedValues = new ArrayList<String>();
		for (AlfrescoVersions alfV : AlfrescoVersions.values()) {
			allowedValues.add(alfV.toString());
		}
		createArchetypeTypeControl(composite, "Alfresco Version", Fields.alfresco_version.toString(), allowedValues);

		createBooleanFieldControl(composite, "Launch Generation ?", Fields.generate.toString(), false);
	}

	public enum Fields {
		alfresco_home, alfresco_version, generate
	}

	public enum AlfrescoVersions {
		COMMUNITY_32R2("Alfresco 3.2r2 Community"), COMMUNITY_34D("Alfresco 3.4.d Community");
		String label;

		private AlfrescoVersions(String label) {
			this.label = label;
		}

		public String toString() {
			return label;
		}
	}
}
