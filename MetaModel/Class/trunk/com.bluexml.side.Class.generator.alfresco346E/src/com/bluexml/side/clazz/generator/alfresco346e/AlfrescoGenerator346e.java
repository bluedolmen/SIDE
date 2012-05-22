package com.bluexml.side.clazz.generator.alfresco346e;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.clazz.generator.alfresco34d.ext.sideenterprise.AlfrescoGenerator;
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class AlfrescoGenerator346e extends AlfrescoGenerator {
	public final static String OPTION_ALFRESCO_PROPERTIES_SYNCHRONISATION = "option.properties.synchro";
	protected final static String i18nFileExt = ".properties.mt";
	protected final static String templateBase346e = "/com.bluexml.side.Class.generator.alfresco346E/com/bluexml/side/clazz/generator/alfresco346e/templates";
	protected final static String doclist = "/alfrescoshare/doclist";

	@Override
	public String getComponentKey() {
		return "CODE_GED_G_C_ALFRESCO_346E";
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator#
	 * getMainTemplates()
	 */
	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();
		///com.bluexml.side.Class.generator.alfresco346E/com/bluexml/side/clazz/generator/alfresco346e/template/alfrescoshare/changetype/change-type.get_it.properties.mt does not exist
		// new i18n messages files
		templates.add(templateBase346e + doclist + "/documentlist.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + doclist + "/documentlist.get_ja" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + "/alfrescoshare/changetype/change-type.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + "/alfrescoshare/changetype/change-type.get_ja" + i18nFileExt); //$NON-NLS-1$

		templates.add(templateBase346e + doclist + "/repo-documentlist.get_de" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + doclist + "/repo-documentlist.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + doclist + "/repo-documentlist.get_es" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + doclist + "/repo-documentlist.get_fr" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + doclist + "/repo-documentlist.get_it" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + doclist + "/repo-documentlist.get_ja" + i18nFileExt); //$NON-NLS-1$

		templates.add(templateBase346e + "/Model/single-child-associations.properties.mt");
		templates.add(templateBase346e + "/Model/properties_synchronization.mt");

		return templates;
	}

	protected List<String> getShareExtensionTemplates() {
		/*
		 * remove from base generator generation of upload components, now
		 * provided by modules and setup by portal generator
		 */
		List<String> result = new ArrayList<String>();
		// generator for alfresco Share web application

		result.add(templateBase + "/alfrescoshare/defaultdocListView/documentlist.get.properties.mt"); //$NON-NLS-1$

		return result;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		String string = doclist + "/documentlist.get_de" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_es" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_it" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$

		string = "/alfrescoshare/changetype/change-type.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$

		string = "/alfrescoshare/changetype/change-type.get" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$

		string = "/Model/association_synchronization.mt";
		map.put(ClassAlfrescoGenerator.templateBase + string, templateBase346e + string); //$NON-NLS-1$

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

	@Override
	public boolean check() {
		return SecurityHelper.check(getComponentKey(), SidePreferences.getKey());
	}

}
