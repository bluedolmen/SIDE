package com.bluexml.side.Integration.alfresco.customAssociationManagement.cascadeDelete;

import java.util.ArrayList;
import java.util.List;

import org.alfresco.repo.node.NodeServicePolicies.BeforeDeleteNodePolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.model.ContentModel;

/*
 * A node is cascade deleted if it belongs to the workspace spacestore and if the namespace of its type starts with bluexml content prefix
 */

public class CascadeDeletePolicy implements 
	BeforeDeleteNodePolicy
{
	// Behaviours
	private Behaviour beforeDeleteNode;

	private Logger logger = Logger.getLogger(getClass());

	public void init() {

		logger.debug("[init] Initializing cascade delete");

		// Create behaviours
		this.beforeDeleteNode = new JavaBehaviour(this, "beforeDeleteNode", NotificationFrequency.FIRST_EVENT); 

		// Bind behaviours to node policies
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "beforeDeleteNode"), this,this.beforeDeleteNode);
	}

	
	public void beforeDeleteNode(NodeRef nodeRef) {
		if (
				StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.equals(nodeRef.getStoreRef()) && 
				nodeService.getType(nodeRef).getNamespaceURI().startsWith(ContentModel.BXCONTENT_NAMESPACE_PREFIX) 
		) {
			List<ChildAssociationRef> bxChildren = getBxChildren(nodeRef);
			for (ChildAssociationRef child : bxChildren) {
				logger.debug("Cascading delete to node \"" + child.getChildRef().getId() + "\" regarding the association type \"" + child.getTypeQName() + "\"");
				NodeRef childNodeRef = child.getChildRef();
				if (nodeService.exists(childNodeRef) ) {
					nodeService.deleteNode(childNodeRef);
				} else {
					logger.error("The child node with id = \"" + childNodeRef.getId() + "\" does not exist");
				}
			}
		}		
	}

	private List<ChildAssociationRef> getBxChildren(NodeRef nodeRef) {
		//nodeService.getChildAssocs(nodeRef, new RegexQNamePattern(ContentModel.BXCONTENT_NAMESPACE_PREFIX + ".+",".+"), RegexQNamePattern.MATCH_ALL);
		List<ChildAssociationRef> children = nodeService.getChildAssocs(nodeRef);
		List<ChildAssociationRef> bxChildren = new ArrayList<ChildAssociationRef>();
		for (ChildAssociationRef child : children) {
			if (child.getTypeQName().getNamespaceURI().startsWith(ContentModel.BXCONTENT_NAMESPACE_PREFIX)) {
				bxChildren.add(child);
			}
		}	
		
		return bxChildren;
	}
	
	/*
	 * Helper methods
	 */
	
	/*
	 * Spring IoC/DI material
	 */
	private PolicyComponent policyComponent;
	private NodeService nodeService;
	
	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}



}
