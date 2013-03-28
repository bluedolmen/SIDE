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
package com.bluexml.side.framework.alfresco.webscriptExtension;

import java.util.List;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.QName;

public class DictionaryScriptExtension extends BaseScopableProcessorExtension {

	private ServiceRegistry serviceRegistry;

	public boolean isMultivalued(String prop_type) {
		PropertyDefinition property = serviceRegistry.getDictionaryService().getProperty(QName.createQName(prop_type, serviceRegistry.getNamespaceService()));
		return property.isMultiValued();
	}

	public boolean haveListConstaint(String prop_type) {
		PropertyDefinition property = serviceRegistry.getDictionaryService().getProperty(QName.createQName(prop_type, serviceRegistry.getNamespaceService()));
		List<ConstraintDefinition> constraints = property.getConstraints();
		for (ConstraintDefinition constraintDefinition : constraints) {
			String type = constraintDefinition.getConstraint().getType();
			if (type != null && type.equals("LIST")) {
				return true;
			}
		}
		return false;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
}
