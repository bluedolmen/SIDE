package com.bluexml.side.framework.alfresco.searchassociation;

import java.io.Serializable;
import java.util.ArrayList;

import org.alfresco.repo.node.NodeServicePolicies.OnCreateAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteAssociationPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

public class AssociationSearchablePolicy implements OnCreateAssociationPolicy, OnDeleteAssociationPolicy {

	// Behaviours
	private Behaviour onCreateAssociation;
	private Behaviour onDeleteAssociation;

	private Logger logger = Logger.getLogger(getClass());

	public void init() {

		logger.info("[init] Initializing association indexation");

		// Create behaviours
		this.onCreateAssociation = new JavaBehaviour(this, "onCreateAssociation", NotificationFrequency.TRANSACTION_COMMIT);
		this.onDeleteAssociation = new JavaBehaviour(this, "onDeleteAssociation", NotificationFrequency.TRANSACTION_COMMIT);

		//this.onCreateAssociation = new JavaBehaviour(this, "onCreateAssociation", NotificationFrequency.FIRST_EVENT);
		//this.onDeleteAssociation = new JavaBehaviour(this, "onDeleteAssociation", NotificationFrequency.FIRST_EVENT);

		// Bind behaviours to node policies
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateAssociation"), this, this.onCreateAssociation);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onDeleteAssociation"), this, this.onDeleteAssociation);
	}

	public void onCreateAssociation(AssociationRef associationRef) {

		QName typeQName = associationRef.getTypeQName();
		QName hiddenSearchablePropertyQName = QName.createQName(typeQName.getNamespaceURI(), typeQName.getLocalName() + "search");

		if (logger.isDebugEnabled()) {
			logger.debug("Association synchronisation policy, ASSOCIATION '" + associationRef.toString() + "'");
			logger.debug("Association synchronisation policy, property '" + hiddenSearchablePropertyQName + "'");
		}
		if (dictionaryService.getProperty(hiddenSearchablePropertyQName) != null) {
			// Register the association in the transaction

			NodeRef sourceRef = associationRef.getSourceRef();
			NodeRef targetRef = associationRef.getTargetRef();

			Serializable property = nodeService.getProperty(sourceRef, hiddenSearchablePropertyQName);

			// update the property value (search in multivalue field if entry exists if not add it)
			if (logger.isDebugEnabled()) {
				logger.debug("Association Searchable policy, Add reference '" + targetRef + "' to " + sourceRef + "in property " + hiddenSearchablePropertyQName);
			}
			if (property == null) {
				property = new ArrayList<String>();
			}
			if (property instanceof ArrayList) {
				ArrayList<Serializable> l = (ArrayList<Serializable>) property;
				boolean exists = false;
				for (Serializable serializable : l) {
					if (serializable == targetRef.toString()) {
						exists = true;
					}
				}
				if (!exists) {
					l.add(targetRef);
					nodeService.setProperty(sourceRef, hiddenSearchablePropertyQName, l);
				}
			}
		} else if (logger.isDebugEnabled()) {
			logger.debug(" property " + hiddenSearchablePropertyQName + " not found in dictionary ...");
		}

	}

	public void onDeleteAssociation(AssociationRef associationRef) {

		QName typeQName = associationRef.getTypeQName();
		QName hiddenSearchablePropertyQName = QName.createQName(typeQName.getNamespaceURI(), typeQName.getLocalName() + "search");

		if (logger.isDebugEnabled()) {
			logger.debug("Association synchronisation policy, CREATE ASSOCIATION '" + associationRef.toString() + "'");
		}

		NodeRef sourceRef = associationRef.getSourceRef();
		NodeRef targetRef = associationRef.getTargetRef();

		if (dictionaryService.getProperty(hiddenSearchablePropertyQName) != null) {
			Serializable property = nodeService.getProperty(sourceRef, hiddenSearchablePropertyQName);

			// update the property value (search in multivalue field if entry exists if not add it)
			if (logger.isDebugEnabled()) {
				logger.debug("Association Searchable policy, remove reference '" + targetRef + "' to " + sourceRef + "in property " + hiddenSearchablePropertyQName);
			}
			if (property instanceof ArrayList) {
				ArrayList<Serializable> l = (ArrayList<Serializable>) property;
				if (logger.isDebugEnabled()) {
					logger.debug("Association Searchable policy, before removed refs :" + l.size());
				}
				l.remove(targetRef.toString());
				if (logger.isDebugEnabled()) {
					logger.debug("Association Searchable policy, removed refs :" + l.size());
				}
				nodeService.setProperty(sourceRef, hiddenSearchablePropertyQName, l);
			}
		}
	}

	/*
	 * Spring IoC/DI material
	 */
	private PolicyComponent policyComponent;
	private NodeService nodeService;

	private DictionaryService dictionaryService;

	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
