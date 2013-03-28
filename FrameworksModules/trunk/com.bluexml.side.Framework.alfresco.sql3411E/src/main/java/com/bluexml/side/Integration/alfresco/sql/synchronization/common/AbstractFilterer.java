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
package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.QName;

/*
 * The default behavior of a filterer is to accept:
 * - Nodes which are in the workspace spaces store and which names is acceptable
 * - Associations which name is acceptable and which association ends (nodes) are
 * 	acceptable
 * - Association, type or property qnames which are acceptable wrt the generic
 * 	acceptQName method
 */
public abstract class AbstractFilterer implements Filterer {

	public boolean accept(NodeRef nodeRef) {
		if (!nodeService.exists(nodeRef)) return false;
		return StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.equals(nodeRef.getStoreRef()) && acceptQName(nodeService.getType(nodeRef));
	}

	public boolean accept(AssociationRef associationRef) {
		return (
				acceptQName(associationRef.getTypeQName()) &&
				accept(associationRef.getSourceRef()) &&
				accept(associationRef.getTargetRef())
				);
	}

	public boolean accept(ChildAssociationRef childAssociationRef) {
		return (
				acceptQName(childAssociationRef.getTypeQName()) &&
				accept(childAssociationRef.getParentRef()) &&
				accept(childAssociationRef.getChildRef())
				);
	}
	
	public boolean acceptAssociationQName(QName qname) {
		return acceptQName(qname);
	}

	public boolean acceptTypeQName(QName qname) {
		return acceptQName(qname);
	}
	
	public boolean acceptPropertyQName(String className, QName propertyQname) {
		return acceptPropertyQName(propertyQname);
	}
	
	public boolean acceptPropertyQName(QName qname) {
		return acceptQName(qname);
	}
	
	public boolean acceptModelQName(QName qname) {
		return acceptQName(qname);
	}
	
	
	private String externalTypesMapping;
	private HashMap<QName, ArrayList<QName>> externalTypesMappingArray;
	public void setExternalTypesMapping(String externalTypesMapping_) {
		this.externalTypesMapping = externalTypesMapping_;
	}

	public HashMap<QName, ArrayList<QName>> getExternalTypesMappingArray() {
		return externalTypesMappingArray;
	}

	//
	// IoC/DI Spring
	//

	// Dependencies
	protected NodeService nodeService;

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}


}
