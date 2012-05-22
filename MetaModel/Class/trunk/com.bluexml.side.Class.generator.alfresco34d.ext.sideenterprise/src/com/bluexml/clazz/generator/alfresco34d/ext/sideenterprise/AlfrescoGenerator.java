package com.bluexml.clazz.generator.alfresco34d.ext.sideenterprise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class AlfrescoGenerator extends com.bluexml.side.clazz.generator.alfresco.extension.sideenterprise.AlfrescoGenerator {
	public static final String templateBase34d = com.bluexml.side.clazz.generator.alfresco34d.Activator.templateBase34d;

	@Override
	public String getComponentKey() {
		return "CODE_GED_G_C_ALFRESCO_34D";
	}

	@Override
	public boolean check() {
		return SecurityHelper.check(getComponentKey(), SidePreferences.getKey());
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator#
	 * getMainTemplates()
	 */
	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();

		// define configuration for advancedSearch
		// replaced by portal generation page.advsearch.search.form page.documentlibrary.subtypes.form
		//		templates.add(templateBase34d + "/alfrescoshare/custom-share-config.xml.mt"); //$NON-NLS-1$

		// i18n messages

		templates.add(templateBase34d + "/alfrescoshare/doclist/documentlist.get_de.properties.mt"); //$NON-NLS-1$
		templates.add(templateBase34d + "/alfrescoshare/doclist/documentlist.get_es.properties.mt"); //$NON-NLS-1$
		templates.add(templateBase34d + "/alfrescoshare/doclist/documentlist.get_fr.properties.mt"); //$NON-NLS-1$
		templates.add(templateBase34d + "/alfrescoshare/doclist/documentlist.get_it.properties.mt"); //$NON-NLS-1$

		templates.add(templateBase34d + "/alfrescoshare/changetype/change-type.get_de.properties.mt");
		templates.add(templateBase34d + "/alfrescoshare/changetype/change-type.get_es.properties.mt");
		templates.add(templateBase34d + "/alfrescoshare/changetype/change-type.get_fr.properties.mt");
		templates.add(templateBase34d + "/alfrescoshare/changetype/change-type.get_it.properties.mt");

		templates.add(templateBase34d + "/alfrescoshare/messages/slingshot-context.mt");
		templates.add(templateBase34d + "/alfrescoshare/messages/slingshot.properties.mt");

		return templates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();
		// 3.4.d specific
		map.put(templateBase + "/Model/alfrescoGenerator_model.mt", templateBase34d + "/model/alfrescoGenerator_model.mt");

		map.put(templateBase + "alfrescoshare/uploadForm/flash-upload.get.html.ftl.mt", templateBase34d + "/alfrescoshare/upload/flash-upload.get.html.ftl.mt");

		// i18n messages
		map.put(templateBase + "/alfrescoshare/defaultdocListView/documentlist.get.properties.mt", templateBase34d + "/alfrescoshare/doclist/documentlist.get.properties.mt"); //$NON-NLS-1$

		// remove default config since form/portal generator do the job
		map.put(templateBase + "/alfrescoshare/DefaultEditForms/web-framework-config-defaults.mt", null); //$NON-NLS-1$

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

}
