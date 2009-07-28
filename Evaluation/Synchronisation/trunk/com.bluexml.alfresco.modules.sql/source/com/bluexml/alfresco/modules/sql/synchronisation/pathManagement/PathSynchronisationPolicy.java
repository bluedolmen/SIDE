package com.bluexml.alfresco.modules.sql.synchronisation.pathManagement;

import java.util.List;

import org.alfresco.repo.node.NodeServicePolicies.OnCreateNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnMoveNodePolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement.SchemaCreation;

public class PathSynchronisationPolicy implements 
	OnCreateNodePolicy,
	OnMoveNodePolicy {

	private Logger logger = Logger.getLogger(getClass());

	private Behaviour onCreateNode;
	private Behaviour onMoveNode;

	public void init() {

		logger.debug("[init] Initializing path synchronisation");

		if (schemaCreation.isReady()) {
	
			// Create behaviours
			this.onCreateNode = new JavaBehaviour(this, "onCreateNode", NotificationFrequency.TRANSACTION_COMMIT);
			this.onMoveNode = new JavaBehaviour(this, "onMoveNode", NotificationFrequency.TRANSACTION_COMMIT);
	
			// Bind behaviours to node policies
			policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), this,this.onCreateNode);
			policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onMoveNode"), this,this.onMoveNode);
		} else {
			logger.warn("Path synchronisation was deactivated since schema is marked as not ready");
		}

	}
		

	
	public void onCreateNode(ChildAssociationRef childAssocRef) {
		NodeRef childNodeRef = childAssocRef.getChildRef(); 
		//logger.debug("Create node, path = " + path);
				
		pathService.updatePath(childNodeRef);
	}

	public void onMoveNode(ChildAssociationRef oldChildAssocRef,ChildAssociationRef newChildAssocRef) {
		NodeRef childNodeRef = newChildAssocRef.getChildRef(); 
		//logger.debug("Move node, path = " + path);
	
		pathService.updatePath(childNodeRef);

		List<NodeRef> descendantsNodeRef = searchService.selectNodes(childNodeRef, "*//.", null, namespaceService, false);
		for (NodeRef nodeRef : descendantsNodeRef) {
			logger.debug("Descendants: " + nodeRef);		
			pathService.updatePath(nodeRef);			
		}
	}

	/*
	 * Spring IoC/DI material
	 */
	private PolicyComponent policyComponent;
	private SearchService searchService;
	private NamespaceService namespaceService;
	private SchemaCreation schemaCreation;
	private PathService pathService;

	
	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}
	
	public void setSearchService (SearchService searchService_) {
		searchService = searchService_;
	}

	public void setNamespaceService (NamespaceService namespaceService_) {
		namespaceService = namespaceService_;
	}
		
	public void setSchemaCreation(SchemaCreation schemaCreation_) {
		schemaCreation = schemaCreation_;
	}
	
	public void setPathService(PathService pathService_) {
		pathService = pathService_;
	}
}
