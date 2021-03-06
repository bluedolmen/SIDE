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
package com.bluexml.clazz.generator.alfresco34d.ext.sidelabs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AlfrescoGenerator extends com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator {

	public static final String templateBase34d = com.bluexml.side.clazz.generator.alfresco34d.Activator.templateBase34d;

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
		//		templates.add(templateBase34d + "alfrescoshare/custom-share-config.xml.mt"); //$NON-NLS-1$

		// i18n messages

		templates.add(templateBase34d + "alfrescoshare/doclist/documentlist.get_de.properties.mt"); //$NON-NLS-1$
		templates.add(templateBase34d + "alfrescoshare/doclist/documentlist.get_es.properties.mt"); //$NON-NLS-1$
		templates.add(templateBase34d + "alfrescoshare/doclist/documentlist.get_fr.properties.mt"); //$NON-NLS-1$
		templates.add(templateBase34d + "alfrescoshare/doclist/documentlist.get_it.properties.mt"); //$NON-NLS-1$

		templates.add(templateBase34d + "alfrescoshare/changetype/change-type.get_de.properties.mt");
		templates.add(templateBase34d + "alfrescoshare/changetype/change-type.get_es.properties.mt");
		templates.add(templateBase34d + "alfrescoshare/changetype/change-type.get_fr.properties.mt");
		templates.add(templateBase34d + "alfrescoshare/changetype/change-type.get_it.properties.mt");

		templates.add(templateBase34d + "alfrescoshare/messages/slingshot-context.mt");
		templates.add(templateBase34d + "alfrescoshare/messages/slingshot.properties.mt");

		return templates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();
		// 3.4.d specific
		map.put(templateBase + "Model/alfrescoGenerator_model.mt", templateBase34d + "model/alfrescoGenerator_model.mt");

		map.put(templateBase + "alfrescoshare/uploadForm/flash-upload.get.html.ftl.mt", templateBase34d + "alfrescoshare/upload/flash-upload.get.html.ftl.mt");

		// i18n messages
		map.put(templateBase + "alfrescoshare/defaultdocListView/documentlist.get.properties.mt", templateBase34d + "alfrescoshare/doclist/documentlist.get.properties.mt"); //$NON-NLS-1$

		// remove default config since form/portal generator do the job
		map.put(templateBase + "alfrescoshare/DefaultEditForms/web-framework-config-defaults.mt", null); //$NON-NLS-1$

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

}
