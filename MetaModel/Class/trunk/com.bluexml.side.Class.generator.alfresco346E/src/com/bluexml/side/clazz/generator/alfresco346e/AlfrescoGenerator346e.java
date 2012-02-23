package com.bluexml.side.clazz.generator.alfresco346e;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.clazz.generator.alfresco34d.ext.sideenterprise.AlfrescoGenerator;
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class AlfrescoGenerator346e extends AlfrescoGenerator {
	public final static String OPTION_ALFRESCO_PROPERTIES_SYNCHRONISATION = "option.properties.synchro";
	final static String i18nFileExt = ".properties.mt";
	final static String templateBase346e = "/com.bluexml.side.Class.generator.alfresco346E/com/bluexml/side/clazz/generator/alfresco346e/templates";

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
		templates.add(templateBase346e + "/alfrescoshare/doclist/documentlist.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + "/alfrescoshare/doclist/documentlist.get_ja" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + "/alfrescoshare/changetype/change-type.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + "/alfrescoshare/changetype/change-type.get_ja" + i18nFileExt); //$NON-NLS-1$

		templates.add(templateBase346e + "/Model/single-child-associations.properties.mt");
		templates.add(templateBase346e + "/Model/properties_synchronization.mt");

		return templates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		//Template /com.bluexml.side.Class.generator.alfresco346E/com/bluexml/side/clazz/generator/alfresco346e/template/alfrescoshare/doclist/documentlist.get_de.properties.mt does not exist
		String string = "/alfrescoshare/doclist/documentlist.get_de" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$

		String string2 = "/alfrescoshare/doclist/documentlist.get_es" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string2, templateBase346e + string2); //$NON-NLS-1$
		String string3 = "/alfrescoshare/doclist/documentlist.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string3, templateBase346e + string3); //$NON-NLS-1$
		String string4 = "/alfrescoshare/doclist/documentlist.get_it" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string4, templateBase346e + string4); //$NON-NLS-1$
		String string5 = "/alfrescoshare/doclist/documentlist.get" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string5, templateBase346e + string5); //$NON-NLS-1$

		String string8 = "/alfrescoshare/changetype/change-type.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string8, templateBase346e + string8); //$NON-NLS-1$

		String string9 = "/alfrescoshare/changetype/change-type.get" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string9, templateBase346e + string9); //$NON-NLS-1$

		String string10 = "/Model/association_synchronization.mt";
		map.put(ClassAlfrescoGenerator.templateBase + string10, templateBase346e + string10); //$NON-NLS-1$

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

	@Override
	public boolean check() {
		return SecurityHelper.check(getComponentKey(), SidePreferences.getKey());
	}

}
