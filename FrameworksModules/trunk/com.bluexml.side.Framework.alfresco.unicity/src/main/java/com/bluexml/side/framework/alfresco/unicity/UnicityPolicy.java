package com.bluexml.side.framework.alfresco.unicity;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.repo.node.NodeServicePolicies.OnCreateNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnUpdatePropertiesPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.transaction.AlfrescoTransactionException;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

public class UnicityPolicy implements OnCreateNodePolicy, OnUpdatePropertiesPolicy {
	private Logger logger = Logger.getLogger(getClass());
	

	// Behaviours
	private Behaviour onCreateNode;
	private Behaviour onUpdateProperties;

	/**
	 * @param unicityCheckerBean
	 *            the unicityCheckerBean to set
	 */
	public void setUnicityCheckerBean(UnicityChecker unicityCheckerBean) {
		this.unicityCheckerBean = unicityCheckerBean;
	}

	

	/**
	 * @return the nodeService
	 */
	public NodeService getNodeService() {
		return nodeService;
	}

	/**
	 * @param nodeService
	 *            the nodeService to set
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void onCreateNode(ChildAssociationRef arg0) {
		NodeRef nodeRef = arg0.getChildRef();
		Map<QName, Serializable> nodeProperties = nodeService.getProperties(nodeRef);
		unicityCheck(nodeService.getType(nodeRef), nodeProperties, nodeRef.getId());
	}

	public void onUpdateProperties(NodeRef nodeRef, Map<QName, Serializable> arg1, Map<QName, Serializable> arg2) {
		unicityCheck(nodeService.getType(nodeRef), arg2, nodeRef.getId());
	}

	private void unicityCheck(QName nodeType, Map<QName, Serializable> prop, String uuid) {
		boolean exist = false;

		try {
			exist = getUnicityCheckerBean().exist(nodeType, prop, uuid);
		} catch (Exception e) {
			logger.debug("[unicityCheck] Unicity Checking error", e);
		}
		if (exist) {
			
			throw new AlfrescoTransactionException("Unicity Checking Error: Entry already exists in the database.");
		}
	}

	public UnicityChecker getUnicityCheckerBean() {
		return unicityCheckerBean;
	}

	public void init() {
		logger.debug("[init] Initializing Unicity");

		// Create behaviours
		onCreateNode = new JavaBehaviour(this, "onCreateNode", NotificationFrequency.TRANSACTION_COMMIT);
		onUpdateProperties = new JavaBehaviour(this, "onUpdateProperties", NotificationFrequency.TRANSACTION_COMMIT);

		// Bind behaviours to node policies
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), this, onCreateNode);
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onUpdateProperties"), this, onUpdateProperties);

	}

	/*
	 * Spring IoC/DI material
	 */
	protected UnicityChecker unicityCheckerBean;
	/**
	 * @return the policyComponent
	 */
	public PolicyComponent getPolicyComponent() {
		return policyComponent;
	}

	/**
	 * @param policyComponent the policyComponent to set
	 */
	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}

	private PolicyComponent policyComponent;
	protected NodeService nodeService;
}
