package com.bluexml.side.Integration.alfresco.associationSynchronisation;

import org.alfresco.repo.node.NodeServicePolicies.OnCreateAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteAssociationPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

public class AssociationSynchronisationPolicy implements 
	OnCreateAssociationPolicy, 
	OnDeleteAssociationPolicy
{
	private static final String BLUEXML_NAMESPACE_URI="http://www.bluexml.com";

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
		if (associationRef.getTypeQName().getLocalName().startsWith(BLUEXML_NAMESPACE_URI)) {
			logger.debug("Association synchronisation policy, CREATE ASSOCIATION");
			QName oppositeAssociationTypeQName = resolver.resolve(associationRef.getTypeQName());
			if (oppositeAssociationTypeQName != null) {
				NodeRef sourceRef = associationRef.getSourceRef();
				NodeRef targetRef = associationRef.getTargetRef();
				nodeService.createAssociation(targetRef, sourceRef, oppositeAssociationTypeQName);
			}
		}
	}
	
	public void onDeleteAssociation(AssociationRef associationRef) {
		if (associationRef.getTypeQName().getLocalName().startsWith(BLUEXML_NAMESPACE_URI)) {
			logger.debug("Association synchronisation policy, DELETE ASSOCIATION");
			QName oppositeAssociationTypeQName = resolver.resolve(associationRef.getTypeQName());
			if (oppositeAssociationTypeQName != null) {
				NodeRef sourceRef = associationRef.getSourceRef();
				NodeRef targetRef = associationRef.getTargetRef();
				nodeService.removeAssociation(targetRef, sourceRef, oppositeAssociationTypeQName);
			}
		}
	}
	
	/*
	 * Spring IoC/DI material
	 */
	private PolicyComponent policyComponent;
	private NodeService nodeService;
	private AssociationSynchronisationResolver resolver;
	
	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}
	
	public void setResolver(AssociationSynchronisationResolver resolver_) {
		resolver = resolver_;
	}
	

}
