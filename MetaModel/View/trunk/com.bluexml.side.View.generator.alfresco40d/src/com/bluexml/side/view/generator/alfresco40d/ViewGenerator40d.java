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
package com.bluexml.side.view.generator.alfresco40d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.view.generator.alfresco34d.ViewGenerator;

public class ViewGenerator40d extends ViewGenerator {
	public final static String TEMPLATE_HOME_40d = "/com.bluexml.side.View.generator.alfresco40d/com/bluexml/side/view/generator/alfresco40d/templates";

	@Override
	protected List<String> getMainTemplates() {
		// TODO Auto-generated method stub
		List<String> mainTemplates = super.getMainTemplates();
		mainTemplates.add(TEMPLATE_HOME_40d + "/doclib2/share-doclib2.mt");

		return mainTemplates;
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		map.put(TEMPLATE_HOME_34d + "/webscripts/alfrescoGenerator_template_js_get.mt", TEMPLATE_HOME_40d + "/webscripts/alfrescoGenerator_template_js_get.mt");

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}
}
