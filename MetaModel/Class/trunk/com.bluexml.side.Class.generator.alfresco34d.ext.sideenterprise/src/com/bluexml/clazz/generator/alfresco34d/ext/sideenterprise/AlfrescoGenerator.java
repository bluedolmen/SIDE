package com.bluexml.clazz.generator.alfresco34d.ext.sideenterprise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class AlfrescoGenerator extends com.bluexml.side.clazz.generator.alfresco.extension.sideenterprise.AlfrescoGenerator {

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
		// let form processor build default alfresco forms
		templates.remove("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/DefaultEditForms/web-framework-config-defaults.mt"); //$NON-NLS-1$

		// define configuration for advancedSearch
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/custom-share-config.xml.mt"); //$NON-NLS-1$

		// i18n messages

		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_de.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_es.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_fr.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_it.properties.mt"); //$NON-NLS-1$

		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_de.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_es.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_fr.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_it.properties.mt");

		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/messages/slingshot-context.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/messages/slingshot.properties.mt");

		return templates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();
		// 3.4.d specific
		map.put("/com.bluexml.side.Class.generator.alfresco/templates/Model/alfrescoGenerator_model.mt", "/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/model/alfrescoGenerator_model.mt");

		map.put("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/uploadForm/flash-upload.get.html.ftl.mt", "/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/upload/flash-upload.get.html.ftl.mt");

		// i18n messages
		map.put("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/defaultdocListView/documentlist.get.properties.mt", "/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get.properties.mt"); //$NON-NLS-1$
		
		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

}
