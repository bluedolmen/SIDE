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
package com.bluexml.side.clazz.generator.alfresco40d.ext.labs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.clazz.generator.alfresco34d.ext.sidelabs.AlfrescoGenerator;
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;

public class AlfClassGenerator40d extends AlfrescoGenerator {
	protected final static String templateBase40d = com.bluexml.side.clazz.generator.alfresco40d.Activator.templateBase40d;
	
	final static String i18nFileExt = ".properties.mt";
	
	final static String doclist = "/alfrescoshare/doclist";

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
		templates.add(templateBase40d + doclist + "/documentlist.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase40d + doclist + "/documentlist.get_ja" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase40d + doclist + "/documentlist.get_nl" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase40d + "/alfrescoshare/changetype/change-type.get_en" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase40d + "/alfrescoshare/changetype/change-type.get_ja" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase40d + "/alfrescoshare/changetype/change-type.get_nl" + i18nFileExt); //$NON-NLS-1$

		templates.add(templateBase40d + "/Model/single-child-associations.properties.mt");
		templates.add(templateBase40d + "/Model/properties_synchronization.mt");
		
		templates.add(templateBase40d + doclist + "portal-evaluators-context.mt"); //$NON-NLS-1$

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
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_es" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_it" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase40d + string); //$NON-NLS-1$

		string = "/alfrescoshare/changetype/change-type.get_fr" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase40d + string); //$NON-NLS-1$

		string = "/alfrescoshare/changetype/change-type.get" + i18nFileExt;
		map.put(AlfrescoGenerator.templateBase34d + string, templateBase40d + string); //$NON-NLS-1$

		string = "/Model/association_synchronization.mt";
		map.put(ClassAlfrescoGenerator.templateBase + string, templateBase40d + string); //$NON-NLS-1$

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}
}
