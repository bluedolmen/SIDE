package com.bluexml.side.Integration.alfresco.customAssociationManagement.associationSynchronization;

import org.alfresco.repo.node.NodeServicePolicies.OnCreateAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteAssociationPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.transaction.AlfrescoTransactionSupport;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.model.ContentModel;

/*
 * This implementation uses Alfresco policies to trigger the duplication of the association.
 * A first implementation tried to use NodeDAOService to check for an existing opposite association, resulting in 
 * non-explained null pointer exceptions (commented lines).
 * The current implementation uses Alfresco transaction support to store the current association being duplicated.
 * If a preceding context cannot be found, we create one and call the nodeService to create the opposite association
 * (this process triggering the call of this policy).
 */

public class AssociationSynchronizationPolicy implements 
	OnCreateAssociationPolicy, 
	OnDeleteAssociationPolicy
{
	private static final String CONTEXT_KEY_SEPARATOR = "/";

	// Behaviours
	private Behaviour onCreateAssociation;
	private Behaviour onDeleteAssociation;

	private Logger logger = Logger.getLogger(getClass());

	public void init() {

		logger.debug("[init] Initializing association synchronisation");

		// Create behaviours
		this.onCreateAssociation = new JavaBehaviour(this, "onCreateAssociation", NotificationFrequency.TRANSACTION_COMMIT);
		this.onDeleteAssociation = new JavaBehaviour(this, "onDeleteAssociation", NotificationFrequency.TRANSACTION_COMMIT); 

		// Bind behaviours to node policies
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateAssociation"), this,this.onCreateAssociation);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onDeleteAssociation"), this,this.onDeleteAssociation);
	}


	public void onCreateAssociation(AssociationRef associationRef) {
		if (associationRef.getTypeQName().getNamespaceURI().startsWith(ContentModel.BXCONTENT_NAMESPACE_PREFIX) ) {
			QName oppositeAssociationTypeQName = resolver.resolve(associationRef.getTypeQName());
		
			if (oppositeAssociationTypeQName != null) {
				if (! oppositeAssociationInProcess(associationRef)) {
					logger.debug("Association synchronisation policy, CREATE ASSOCIATION");
					
					// Register the association in the transaction
					setContext(associationRef);
					
					NodeRef sourceRef = associationRef.getSourceRef();
					NodeRef targetRef = associationRef.getTargetRef();
					nodeService.createAssociation(targetRef, sourceRef, oppositeAssociationTypeQName);
					
				} else {
					logger.debug("Association already in process, removing context");
					removeContext(associationRef);					
				}
			}
		}
	}
	
	public void onDeleteAssociation(AssociationRef associationRef) {
		if (associationRef.getTypeQName().getNamespaceURI().startsWith(ContentModel.BXCONTENT_NAMESPACE_PREFIX) ) {
			QName oppositeAssociationTypeQName = resolver.resolve(associationRef.getTypeQName());
			
			if (oppositeAssociationTypeQName != null) {
				if (! oppositeAssociationInProcess(associationRef)) {
					logger.debug("Association synchronisation policy, DELETE ASSOCIATION");
					
					// Register the association in the transaction
					setContext(associationRef);
					
					NodeRef sourceRef = associationRef.getSourceRef();
					NodeRef targetRef = associationRef.getTargetRef();
					nodeService.removeAssociation(targetRef, sourceRef, oppositeAssociationTypeQName);
					
				} else {
					logger.debug("Association already in process, removing context");
					removeContext(associationRef);					
				}
			}
		}
	}

	/*
	 * Helper methods
	 */
	
	private boolean oppositeAssociationInProcess(AssociationRef associationRef) {
		String key = buildKey(associationRef.getTypeQName(), associationRef.getSourceRef(), associationRef.getTargetRef());
		return AlfrescoTransactionSupport.getResource(key) != null;
		
//		Pair<Long, NodeRef> source = nodeDaoService.getNodePair(associationRef.getSourceRef());
//		Pair<Long, NodeRef> target = nodeDaoService.getNodePair(associationRef.getTargetRef());
//		QName oppositeAssociationTypeQName = resolver.resolve(associationRef.getTypeQName());
//		
//		Pair<Long, AssociationRef> assoc = nodeDaoService.getNodeAssoc(target.getFirst(), source.getFirst(), oppositeAssociationTypeQName);
//		
//		return assoc != null;
	}
		
	private void setContext(AssociationRef associationRef) {
		QName oppositeAssociationTypeQName = resolver.resolve(associationRef.getTypeQName());
		String key = buildKey(oppositeAssociationTypeQName, associationRef.getTargetRef(), associationRef.getSourceRef());
		AlfrescoTransactionSupport.bindResource(key, associationRef);
	}
	
	private void removeContext(AssociationRef associationRef) {
		String key = buildKey(associationRef.getTypeQName(), associationRef.getSourceRef(), associationRef.getTargetRef());
		AlfrescoTransactionSupport.unbindResource(key);		
	}
	
	private String buildKey(QName associationName, NodeRef firstRef, NodeRef secondRef) {
		StringBuffer key = new StringBuffer();
		key.append(associationName.getLocalName());
		key.append(CONTEXT_KEY_SEPARATOR);
		key.append(firstRef.getId());
		key.append(CONTEXT_KEY_SEPARATOR);
		key.append(secondRef.getId());
		
		return key.toString();
	}
	
	/*
	 * Spring IoC/DI material
	 */
	private PolicyComponent policyComponent;
	private NodeService nodeService;
	private AssociationSynchronizationResolver resolver;
	
	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}
	
	public void setResolver(AssociationSynchronizationResolver resolver_) {
		resolver = resolver_;
	}
	

}
