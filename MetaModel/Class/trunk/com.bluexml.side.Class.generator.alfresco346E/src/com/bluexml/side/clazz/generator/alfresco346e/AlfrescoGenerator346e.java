/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.clazz.generator.alfresco346e;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.clazz.generator.alfresco34d.ext.sideenterprise.AlfrescoGenerator;
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;

public class AlfrescoGenerator346e extends AlfrescoGenerator {
	public final static String OPTION_ALFRESCO_PROPERTIES_SYNCHRONISATION = "option.properties.synchro";
	protected final static String i18nFileExt = ".properties.mt";
	protected final static String templateBase346e = "/com.bluexml.side.Class.generator.alfresco346E/com/bluexml/side/clazz/generator/alfresco346e/templates/";
	protected final static String doclist = "alfrescoshare/doclist/";

	protected final static String search = "alfrescoshare/search/";

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
		// new i18n messages files
		templates.add(templateBase346e + doclist + "documentlist.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + doclist + "documentlist.get_ja" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + "alfrescoshare/changetype/change-type.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + "alfrescoshare/changetype/change-type.get_ja" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + "alfrescoshare/changetype/change-type.get" + i18nFileExt); //$NON-NLS-1$

		templates.add(templateBase346e + doclist + "repo-documentlist.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + doclist + "repo-documentlist.get_ja" + i18nFileExt); //$NON-NLS-1$

		templates.add(templateBase346e + "/Model/single-child-associations.properties.mt");
		templates.add(templateBase346e + "/Model/properties_synchronization.mt");

		templates.add(templateBase346e + search + "search.get_de" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + search + "search.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + search + "search.get_es" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + search + "search.get_fr" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + search + "search.get_it" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase346e + search + "search.get_ja" + i18nFileExt); //$NON-NLS-1$

		return templates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		// remove from shareExtensionTemplates
		map.put(templateBase + "alfrescoshare/uploadForm/flash-upload-js-get-patch.mt", null); //$NON-NLS-1$
		map.put(templateBase + "alfrescoshare/uploadForm/html-upload-js-get-patch.mt", null); //$NON-NLS-1$
		map.put(templateBase34d + "alfrescoshare/upload/flash-upload.get.html.ftl.mt", null); //$NON-NLS-1$

		// replace

		String string = doclist + "documentlist.get_de" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "documentlist.get_es" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "documentlist.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "documentlist.get_it" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "documentlist.get" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$

		string = "alfrescoshare/changetype/change-type.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		
		string = "Model/association_synchronization.mt";
		map.put(ClassAlfrescoGenerator.templateBase + string, templateBase346e + string); //$NON-NLS-1$
		
		string = doclist + "repo-documentlist.get_de" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "repo-documentlist.get_es" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "repo-documentlist.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$
		string = doclist + "repo-documentlist.get_it" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase346e + string); //$NON-NLS-1$

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

	@Override
	public boolean check() {
		return true;
	}

}
