package com.bluexml.side.Integration.alfresco.sql.synchronization;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.repo.node.NodeServicePolicies.BeforeDeleteNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateChildAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteChildAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnUpdatePropertiesPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.NodeFilterer;
import com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeService;
import com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement.SchemaCreation;

public class SQLSynchronizationPolicy implements 
	OnCreateNodePolicy, 
	OnUpdatePropertiesPolicy, 
	OnCreateAssociationPolicy, 
	OnDeleteAssociationPolicy, 
	OnCreateChildAssociationPolicy, 
	OnDeleteChildAssociationPolicy, 
	BeforeDeleteNodePolicy {

	// Behaviours
	private Behaviour onCreateNode;
	private Behaviour onUpdateProperties;
	private Behaviour onCreateAssociation;
	private Behaviour onCreateChildAssociation;
	private Behaviour onDeleteChildAssociation;
	private Behaviour beforeDeleteNode;
	private Behaviour onDeleteAssociation;

	private Logger logger = Logger.getLogger(getClass());



//	private static ThreadLocal<TransactionListenerForAssociationClasses> listener = new ThreadLocal<TransactionListenerForAssociationClasses>();
//	private TransactionListener listener;

	public void init() {

		logger.debug("[init] Initializing relational synchronization");

		if (schemaCreation.isReady()) {
			// Create behaviours
			this.onCreateNode = new JavaBehaviour(this, "onCreateNode", NotificationFrequency.TRANSACTION_COMMIT);
			this.onUpdateProperties = new JavaBehaviour(this, "onUpdateProperties", NotificationFrequency.TRANSACTION_COMMIT);
			this.onCreateAssociation = new JavaBehaviour(this, "onCreateAssociation", NotificationFrequency.TRANSACTION_COMMIT);
			this.onCreateChildAssociation = new JavaBehaviour(this, "onCreateChildAssociation", NotificationFrequency.TRANSACTION_COMMIT);
			
			this.beforeDeleteNode = new JavaBehaviour(this, "beforeDeleteNode", NotificationFrequency.FIRST_EVENT); 
			this.onDeleteAssociation = new JavaBehaviour(this, "onDeleteAssociation", NotificationFrequency.TRANSACTION_COMMIT); 
			this.onDeleteChildAssociation = new JavaBehaviour(this, "onDeleteChildAssociation", NotificationFrequency.TRANSACTION_COMMIT);
	
			// Bind behaviours to node policies
			policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), this,this.onCreateNode);
			policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onUpdateProperties"), this,this.onUpdateProperties);
			policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "beforeDeleteNode"), this,this.beforeDeleteNode);
			policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateAssociation"), this,this.onCreateAssociation);
			policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateChildAssociation"), this,this.onCreateChildAssociation);
			policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onDeleteChildAssociation"), this,this.onDeleteChildAssociation);
			policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onDeleteAssociation"), this,this.onDeleteAssociation);
		} else {
			logger.warn("Synchronization was deactivated since schema is marked as not ready");
		}
	}

	public void onCreateNode(ChildAssociationRef childAssociationRef) {
		NodeRef nodeRef = childAssociationRef.getChildRef();

		// Only process bluexml nodes
		if (acceptNodeRef(nodeRef)) {
			logger.debug("Synchronization policy, CREATE NODE");
			/*
			 * Here we cannot determine whether this creation originated from a creation of a new node
			 * or from a restored archived-node. We thus call the create-with-associations method instead
			 * of the simple create one.
			 */
			synchroNodeService.createWithAssociations(nodeRef);
		}

	}

	public void beforeDeleteNode(NodeRef nodeRef) {
		if (acceptNodeRef(nodeRef)) {
			logger.debug("Synchronization policy, DELETE NODE");
			synchroNodeService.delete(nodeRef);
		}
	}

	public void onUpdateProperties(NodeRef nodeRef, Map<QName, Serializable> before_properties, Map<QName, Serializable> after_properties) {
		if (acceptNodeRef(nodeRef)) {
			Map<QName, Serializable> changes = new HashMap<QName, Serializable>();
			for (QName key : after_properties.keySet()) {
				if (nodeFilterer.acceptOnName(key)) {
					if (
							!before_properties.containsKey(key) || // new property
							before_properties.get(key) == null ||  // a property with null value: trick to avoid null pointer exception on the next checking
							!before_properties.get(key).equals(after_properties.get(key)) // old value is different from new value 
						) 
					{
						// Hack : if before_properties.get(key) == null, we accepted the value for a change
						// to avoid nullpointerexception with the equals statement
						if (!(before_properties.get(key) == null && after_properties.get(key) == null))
							changes.put(key, after_properties.get(key));
					}
				}
			}

			if (changes.size() > 0) {
				logger.debug("Synchronization policy, UPDATE PROPERTIES");
				synchroNodeService.updateProperties(nodeRef, changes.keySet());
			}
		}

	}

	public void onCreateAssociation(AssociationRef associationRef) {
		if (nodeFilterer.acceptOnName(associationRef.getTypeQName())) {
			logger.debug("Synchronization policy, CREATE ASSOCIATION");
			synchroNodeService.createAssociation(associationRef.getSourceRef(), associationRef.getTargetRef(), associationRef.getTypeQName());
		}
	}
	
	public void onDeleteAssociation(AssociationRef associationRef) {
		if (nodeFilterer.acceptOnName(associationRef.getTypeQName())) {
			logger.debug("Synchronization policy, DELETE ASSOCIATION");
			synchroNodeService.deleteAssociation(associationRef.getSourceRef(), associationRef.getTargetRef(), associationRef.getTypeQName());
		}
	}

	public void onCreateChildAssociation(ChildAssociationRef associationRef,
			boolean isNewNode) {
		if (nodeFilterer.acceptOnName(associationRef.getTypeQName())) {
			logger.debug("Synchronization policy, CREATE CHILD ASSOCIATION");
			synchroNodeService.createAssociation(associationRef.getParentRef(), associationRef.getChildRef(), associationRef.getTypeQName());
		}
	}

	public void onDeleteChildAssociation(ChildAssociationRef associationRef) {
		if (nodeFilterer.acceptOnName(associationRef.getTypeQName())) {

			logger.debug("Synchronization policy, DELETE CHILD ASSOCIATION");
			synchroNodeService.deleteAssociation(associationRef.getParentRef(), associationRef.getChildRef(), associationRef.getTypeQName());
		}
	}

	/*
	 * Helper methods
	 */
	
	/*
	 * Nodes are accepted if they belongs to the workspace space store and if the name is correctly filtered by the node filterer
	 */
	private boolean acceptNodeRef (NodeRef nodeRef) {
		return StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.equals(nodeRef.getStoreRef()) && nodeFilterer.acceptOnName(nodeService.getType(nodeRef));
	}
	
//	private void createAllRelatedAssociations(NodeRef nodeRef) {
//		Set<AssociationRef> assocs  = new HashSet<AssociationRef>();
//		assocs.addAll(nodeService.getSourceAssocs(nodeRef, RegexQNamePattern.MATCH_ALL));
//		assocs.addAll(nodeService.getTargetAssocs(nodeRef, RegexQNamePattern.MATCH_ALL));
//		
//		for (AssociationRef assoc : assocs) {
//			if (
//					nodeFilterer.acceptOnName(assoc.getTypeQName()) && 
//					assoc.getSourceRef().getStoreRef() == StoreRef.STORE_REF_WORKSPACE_SPACESSTORE &&
//					assoc.getTargetRef().getStoreRef() == StoreRef.STORE_REF_WORKSPACE_SPACESSTORE
//				) {
//				synchroNodeService.createAssociation(assoc.getSourceRef(), assoc.getTargetRef(), assoc.getTypeQName());
//			}
//		}
//		
//		Set<ChildAssociationRef> childAssocs = new HashSet<ChildAssociationRef>();
//		childAssocs.addAll(nodeService.getChildAssocs(nodeRef));
//		childAssocs.addAll(nodeService.getParentAssocs(nodeRef));
//
//		for (ChildAssociationRef assoc : childAssocs) {
//			if (
//					nodeFilterer.acceptOnName(assoc.getTypeQName()) && 
//					assoc.getParentRef().getStoreRef() == StoreRef.STORE_REF_WORKSPACE_SPACESSTORE &&
//					assoc.getChildRef().getStoreRef() == StoreRef.STORE_REF_WORKSPACE_SPACESSTORE
//				) {
//				synchroNodeService.createAssociation(assoc.getParentRef(), assoc.getChildRef(), assoc.getTypeQName());
//			}
//		}
//	}

	
	/*
	 * Spring IoC/DI material
	 */
	private PolicyComponent policyComponent;
	private org.alfresco.service.cmr.repository.NodeService nodeService;
	private NodeFilterer nodeFilterer;
	private NodeService synchroNodeService; /* BlueXML NodeService */
	private SchemaCreation schemaCreation;
	
	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}
	
	public void setNodeService(org.alfresco.service.cmr.repository.NodeService nodeService_) {
		nodeService = nodeService_;
	}
	
	public void setNodeFilterer(NodeFilterer nodeFilterer_) {
		nodeFilterer = nodeFilterer_;
	}
	
	public void setSynchroNodeService(NodeService nodeService_) {
		synchroNodeService = nodeService_;
	}

	public void setSchemaCreation(SchemaCreation schemaCreation_) {
		schemaCreation = schemaCreation_;
	}


}
