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
package com.bluexml.side.workflow.generator.alfresco34d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator;

public class WorkflowGeneratorCommunity extends WorkflowGenerator {

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.workflow.generator.alfresco.WorkflowGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();
		map.put("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_jpdl.mt", "/com.bluexml.side.Workflow.generator.alfresco34d/com/bluexml/side/workflow/generator/alfresco34d/templates/alfrescoGenerator_jpdl.mt");
		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

}
