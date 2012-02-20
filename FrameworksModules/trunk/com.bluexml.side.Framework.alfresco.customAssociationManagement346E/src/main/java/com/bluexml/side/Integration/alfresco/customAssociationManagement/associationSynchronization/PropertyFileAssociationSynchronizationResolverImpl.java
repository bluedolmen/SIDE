package com.bluexml.side.Integration.alfresco.customAssociationManagement.associationSynchronization;

import org.alfresco.service.namespace.QName;

import com.bluexml.side.framework.alfresco.commons.configurations.PropertiesConfiguration;

public class PropertyFileAssociationSynchronizationResolverImpl extends PropertiesConfiguration implements AssociationSynchronizationResolver {

	public QName resolve(QName associationQName) {
		if (dictionary.containsKey(associationQName.getLocalName())) {
			return QName.createQName(associationQName.getNamespaceURI(), dictionary.get(associationQName.getLocalName()));
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
