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
package com.bluexml.side.view.generator.facetmap.services;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.service.alfresco.CommonServices;

public class FacetMapService {

	public static String getCMISPropertyId(Attribute att) throws Exception {
		String alfresco_id = CommonServices.getPrefixedQName(att);
		String propertyDefinitionId = alfresco_id;
		if (alfresco_id.equals("cm:name")) {
			propertyDefinitionId = "cmis:name";
		} else if (alfresco_id.equals("cm:modified")) {
			propertyDefinitionId = "cmis:lastModificationDate";
		} else if (alfresco_id.equals("cm:created")) {
			propertyDefinitionId = "cmis:creationDate";
		} else if (alfresco_id.equals("cm:modifier")) {
			propertyDefinitionId = "cmis:lastModifiedBy";
		} else if (alfresco_id.equals("cm:contentType")) {
			propertyDefinitionId = "cmis:objectTypeId";
		} else if (alfresco_id.equals("cm:creator")) {
			propertyDefinitionId = "cmis:createdBy";
		}

		return propertyDefinitionId;
	}
}
