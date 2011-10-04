package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

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
	
	public boolean acceptPropertyQName(QName qname) {
		return acceptQName(qname);
	}
	
	public boolean acceptModelQName(QName qname) {
		return acceptQName(qname);
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
