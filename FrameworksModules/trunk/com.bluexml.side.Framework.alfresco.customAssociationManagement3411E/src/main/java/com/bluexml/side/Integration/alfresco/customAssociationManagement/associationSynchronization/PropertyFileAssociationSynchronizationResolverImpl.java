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
package com.bluexml.side.Integration.alfresco.customAssociationManagement.associationSynchronization;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.framework.alfresco.commons.configurations.PolicyPropertiesConfiguration;

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
