package com.bluexml.side.framework.alfresco.singlePrimaryChildAssociation346E;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateChildAssociationPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluexml.side.framework.alfresco.commons.configurations.PropertiesConfiguration;

/**
 * This Policy only keep primary childAssociation, if a secondary
 * childAssociation is created, the association is remove and the primary is
 * "moved" instead to let the secondary
 * 
 * @author davidabad
 */
public class SinglePrimaryChildAssociationPolicy implements OnCreateChildAssociationPolicy {

	// Behaviours
	private Behaviour onCreateChildAssociation;

	Log logger = LogFactory.getLog(getClass());

	public void init() {

		logger.info("[init] Initializing SinglePrimaryChildAssociationPolicy");
		if (configuration.getDictionary().size() > 0) {

			// Create behaviours
			this.onCreateChildAssociation = new JavaBehaviour(this, "onCreateChildAssociation", NotificationFrequency.FIRST_EVENT);

			// Bind behaviours to node policies
			policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateChildAssociation"), this, this.onCreateChildAssociation);
		} else if (logger.isDebugEnabled()) {
			logger.info("Policy DISABLED !! no entries in dictionary");
		}

	}

	public void onCreateChildAssociation(ChildAssociationRef childAssocRef, boolean isNewNode) {
		try {
			logger.debug("SinglePrimaryChildAssociationPolicy.onCreateChildAssociation()");
			NodeRef childRef = childAssocRef.getChildRef();
			NodeRef parentRef = childAssocRef.getParentRef();
			QName typeC = getNodeService().getType(childRef);
			QName typeP = getNodeService().getType(parentRef);
			logger.debug("\tisNewNode :" + isNewNode);
			logger.debug("\tchildRef :" + childRef);
			logger.debug("\tparentRef :" + parentRef);
			logger.debug("\ttypeC :" + typeC);
			logger.debug("\ttypeP" + typeP);
			boolean hasValue = false;
			boolean hasValueC = configuration.hasValue(typeC.toPrefixString(this.getNamespaceService()));
			boolean hasValueP = configuration.hasValue(typeP.toPrefixString(this.getNamespaceService()));
			if (hasValueC || hasValueP) {
				// check value
				if (hasValueC) {
					hasValue = configuration.getAsBooleanValue(typeC.toPrefixString(this.getNamespaceService()));
				} else {
					hasValue = configuration.getAsBooleanValue(typeP.toPrefixString(this.getNamespaceService()));
				}
			}
			QName typeQName = childAssocRef.getTypeQName();
			logger.debug("\tassoType :" + typeQName);
			if (!isNewNode && hasValue && typeQName.equals(ContentModel.ASSOC_CONTAINS)) {

				if (!childAssocRef.isPrimary()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Policy move primary to the new child-association ...");
					}
					ChildAssociationRef primaryParent = getNodeService().getPrimaryParent(childRef);
					logger.debug("OLD primary Parent :" + primaryParent.getParentRef());
					// to avoid constraints error (two same association between parent -> child) wee need to remove the current childAssociation before to move
					FileInfo fileInfo = getServiceRegistry().getFileFolderService().getFileInfo(childRef);
					boolean linkB = fileInfo.isLink();
					logger.debug("new ref isLink ? " + linkB);

					getNodeService().removeChildAssociation(childAssocRef);
					FileInfo moveFrom = getServiceRegistry().getFileFolderService().moveFrom(childRef, primaryParent.getParentRef(), parentRef, null);
					boolean linkA = moveFrom.isLink();
					logger.debug("new ref isLink ? " + linkA);
					// need to move the primary association and remove the current childAssociation
					ChildAssociationRef newPrimaryParent = getNodeService().getPrimaryParent(childRef);
					logger.debug("NEW primary Parent :" + newPrimaryParent.getParentRef());
					//					getNodeService().moveNode(childRef, parentRef, ContentModel.ASSOC_CONTAINS, primaryParent.getQName());
					if (primaryParent.getParentRef().toString().equals(newPrimaryParent.getParentRef())) {
						// primary parent not changed
						logger.error("Very Bad the node is in the temp folder, move do nothing ...");
					}
				} else {
					logger.debug("\tnothing to do notPrimary asso");
				}

			} else {
				logger.debug("\tnothing to do");
				// do nothing this is the call that create the initial primary association
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}

	/*
	 * Spring IoC/DI material
	 */
	protected PolicyComponent policyComponent;

	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}

	protected NamespaceService namespaceService;

	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	protected NodeService nodeService;

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	PropertiesConfiguration configuration;

	public void setConfiguration(PropertiesConfiguration configuration) {
		this.configuration = configuration;
	}

	ServiceRegistry serviceRegistry;

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

}
