package com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass.pages;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.clazz.edit.ui.Messages;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPage;

public class InitializerPageWelcome extends AbstractFieldsPage {

	public final static String ID = "page1"; //$NON-NLS-1$

	public InitializerPageWelcome() {
		super(ID);
		this.setDescription(Messages.InitializerPageWelcome_1);
	}

	public void createFieldsControls(Composite composite) {
		//		createRessourceControl(composite, label, id, type);
		createResourceControl(composite, Messages.InitializerPageWelcome_2, Fields.alfresco_home.toString(), RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY);

		ArrayList<String> allowedValues = new ArrayList<String>();
		for (AlfrescoVersions alfV : AlfrescoVersions.values()) {
			allowedValues.add(alfV.toString());
		}
		createComboControl(composite, Messages.InitializerPageWelcome_3, Fields.alfresco_version.toString(), allowedValues);

		createBooleanFieldControl(composite, Messages.InitializerPageWelcome_4, Fields.generate.toString(), false);
	}

	public enum Fields {
		alfresco_home, alfresco_version, generate
	}

	public enum AlfrescoVersions {
		COMMUNITY_32R2(Messages.InitializerPageWelcome_5), COMMUNITY_34D(Messages.InitializerPageWelcome_6);
		String label;

		private AlfrescoVersions(String label) {
			this.label = label;
		}

		public String toString() {
			return label;
		}
	}
}
