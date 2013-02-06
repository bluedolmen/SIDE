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
