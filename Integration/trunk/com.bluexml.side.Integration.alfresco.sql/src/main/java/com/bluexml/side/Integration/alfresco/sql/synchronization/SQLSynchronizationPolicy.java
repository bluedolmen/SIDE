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
			this.onDeleteChildAssociation = new JavaBehaviour(this, "onDeleteChildAssociation", NotificationFrequency.EVERY_EVENT);
	
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
		if (nodeFilterer.accept(nodeRef)) {
			logger.debug("Synchronization policy, CREATE NODE");
			nodeService.create(nodeRef);
		}
	}

	public void beforeDeleteNode(NodeRef nodeRef) {
		if (nodeFilterer.accept(nodeRef)) {
			logger.debug("Synchronization policy, DELETE NODE");
			nodeService.delete(nodeRef);
		}
	}

	public void onUpdateProperties(NodeRef nodeRef, Map<QName, Serializable> before_properties, Map<QName, Serializable> after_properties) {
		if (nodeFilterer.accept(nodeRef)) {
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
				nodeService.updateProperties(nodeRef, changes.keySet());
			}
		}

	}

	public void onCreateAssociation(AssociationRef associationRef) {
		if (nodeFilterer.acceptOnName(associationRef.getTypeQName())) {
			logger.debug("Synchronization policy, CREATE ASSOCIATION");
			nodeService.createAssociation(associationRef.getSourceRef(), associationRef.getTargetRef(), associationRef.getTypeQName());
		}
	}
	
	public void onDeleteAssociation(AssociationRef associationRef) {
		if (nodeFilterer.acceptOnName(associationRef.getTypeQName())) {
			logger.debug("Synchronization policy, DELETE ASSOCIATION");
			nodeService.deleteAssociation(associationRef.getSourceRef(), associationRef.getTargetRef(), associationRef.getTypeQName());
		}
	}

	public void onCreateChildAssociation(ChildAssociationRef associationRef,
			boolean isNewNode) {
		if (nodeFilterer.acceptOnName(associationRef.getTypeQName())) {
			logger.debug("Synchronization policy, CREATE CHILD ASSOCIATION");
			nodeService.createAssociation(associationRef.getParentRef(), associationRef.getChildRef(), associationRef.getTypeQName());
		}
	}

	public void onDeleteChildAssociation(ChildAssociationRef associationRef) {
		if (nodeFilterer.acceptOnName(associationRef.getTypeQName())) {

			logger.debug("Synchronization policy, DELETE CHILD ASSOCIATION");
			nodeService.deleteAssociation(associationRef.getParentRef(), associationRef.getChildRef(), associationRef.getTypeQName());
		}
	}

	
	/*
	 * Spring IoC/DI material
	 */
	private PolicyComponent policyComponent;
	private NodeFilterer nodeFilterer;
	private NodeService nodeService;
	private SchemaCreation schemaCreation;
	
	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}
	
	public void setNodeFilterer(NodeFilterer nodeFilterer_) {
		nodeFilterer = nodeFilterer_;
	}
	
	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public void setSchemaCreation(SchemaCreation schemaCreation_) {
		schemaCreation = schemaCreation_;
	}
}
