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
