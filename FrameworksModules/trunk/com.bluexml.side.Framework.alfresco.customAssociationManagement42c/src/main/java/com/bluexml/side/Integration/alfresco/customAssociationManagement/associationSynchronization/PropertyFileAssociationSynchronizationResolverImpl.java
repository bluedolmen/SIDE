package com.bluexml.side.Integration.alfresco.customAssociationManagement.associationSynchronization;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.framework.alfresco.commons.configurations.PolicyPropertiesConfiguration;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public class PropertyFileAssociationSynchronizationResolverImpl extends PolicyPropertiesConfiguration implements AssociationSynchronizationResolver {

	ServiceRegistry serviceRegistry;

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public QName resolve(QName associationQName) {
		String prefixString = associationQName.toPrefixString(this.serviceRegistry.getNamespaceService());
		if (dictionary.containsKey(prefixString)) {
			return QName.createQName(dictionary.get(prefixString), this.serviceRegistry.getNamespaceService());
		} else {
			return null;
		}
	}

	/*
	 * This implements relies on the fact that if a key can be found in the
	 * dictionary, then it is a both navigable association
	 * @see com.bluexml.side.Integration.alfresco.associationSynchronisation.
	 * AssociationSynchronisationResolver
	 * #isBothNavigable(org.alfresco.service.namespace.QName)
	 */
	public boolean isBothNavigable(QName associationQName) {
		return dictionary.containsKey(associationQName.getLocalName());
	}

}
