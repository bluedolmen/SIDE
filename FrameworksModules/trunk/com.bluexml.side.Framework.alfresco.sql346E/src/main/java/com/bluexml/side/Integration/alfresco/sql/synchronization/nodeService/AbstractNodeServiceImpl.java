package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.policy.AssociationPolicyDelegate;
import org.alfresco.repo.policy.ClassPolicyDelegate;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeServicePolicies.OnCreateAssociationPolicy;
import com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeServicePolicies.OnCreateNodePolicy;
import com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeServicePolicies.OnDeleteAssociationPolicy;
import com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeServicePolicies.OnDeleteNodePolicy;
import com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeServicePolicies.OnUpdatePropertiesPolicy;

public abstract class AbstractNodeServiceImpl implements com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeService {

	private ClassPolicyDelegate<OnCreateNodePolicy> onCreateNodeDelegate;
	private ClassPolicyDelegate<OnDeleteNodePolicy> onDeleteNodeDelegate;
	private ClassPolicyDelegate<OnUpdatePropertiesPolicy> onUpdatePropertiesDelegate;
	private AssociationPolicyDelegate<OnCreateAssociationPolicy> onCreateAssociationDelegate;
	private AssociationPolicyDelegate<OnDeleteAssociationPolicy> onDeleteAssociationDelegate;
	
	public void init() {
        onCreateNodeDelegate = policyComponent.registerClassPolicy(NodeServicePolicies.OnCreateNodePolicy.class);
        onDeleteNodeDelegate = policyComponent.registerClassPolicy(NodeServicePolicies.OnDeleteNodePolicy.class);
        onUpdatePropertiesDelegate = policyComponent.registerClassPolicy(NodeServicePolicies.OnUpdatePropertiesPolicy.class);
        onCreateAssociationDelegate = policyComponent.registerAssociationPolicy(NodeServicePolicies.OnCreateAssociationPolicy.class);
        onDeleteAssociationDelegate = policyComponent.registerAssociationPolicy(NodeServicePolicies.OnDeleteAssociationPolicy.class);
	}
	
	protected void invokeOnCreateNode(NodeRef nodeRef) {
        Set<QName> qnames = getTypeAndAspectQNames(nodeRef);
        NodeServicePolicies.OnCreateNodePolicy policy = onCreateNodeDelegate.get(nodeRef, qnames);
        policy.onCreateNode(nodeRef);
	}

	protected void invokeOnDeleteNode(NodeRef nodeRef) {
        Set<QName> qnames = getTypeAndAspectQNames(nodeRef);
        NodeServicePolicies.OnDeleteNodePolicy policy = onDeleteNodeDelegate.get(nodeRef, qnames);
        policy.onDeleteNode(nodeRef);
	}

	protected void invokeOnUpdateProperties(NodeRef nodeRef, Collection<QName> changes) {
        Set<QName> qnames = getTypeAndAspectQNames(nodeRef);
        NodeServicePolicies.OnUpdatePropertiesPolicy policy = onUpdatePropertiesDelegate.get(nodeRef, qnames);
        policy.onUpdateProperties(nodeRef, changes);	
	}
	
    protected void invokeOnCreateAssociation(NodeRef sourceNodeRef, NodeRef targetNodeRef, QName typeQName)
    {
        // get qnames to invoke against
        Set<QName> qnames = getTypeAndAspectQNames(sourceNodeRef);
        // execute policy for node type and aspects
        NodeServicePolicies.OnCreateAssociationPolicy policy = onCreateAssociationDelegate.get(sourceNodeRef, qnames, typeQName);
        policy.onCreateAssociation(sourceNodeRef, targetNodeRef, typeQName);
    }

    protected void invokeOnDeleteAssociation(NodeRef sourceNodeRef, NodeRef targetNodeRef, QName typeQName)
    {
        // get qnames to invoke against
        Set<QName> qnames = getTypeAndAspectQNames(sourceNodeRef);
        // execute policy for node type and aspects
        NodeServicePolicies.OnDeleteAssociationPolicy policy = onDeleteAssociationDelegate.get(sourceNodeRef, qnames, typeQName);
        policy.onDeleteAssociation(sourceNodeRef, targetNodeRef, typeQName);
    }

	/*
	 * Helper methods
	 */
    /**
     * Get all aspect and node type qualified names
     * 
     * @param nodeRef
     *            the node we are interested in
     * @return Returns a set of qualified names containing the node type and all
     *         the node aspects, or null if the node no longer exists
     */
    protected Set<QName> getTypeAndAspectQNames(NodeRef nodeRef)
    {
        Set<QName> qnames = null;
        try
        {
            Set<QName> aspectQNames = nodeService.getAspects(nodeRef);
            
            QName typeQName = nodeService.getType(nodeRef);
            
            qnames = new HashSet<QName>(aspectQNames.size() + 1);
            qnames.addAll(aspectQNames);
            qnames.add(typeQName);
        }
        catch (InvalidNodeRefException e)
        {
            qnames = Collections.emptySet();
        }
        // done
        return qnames;
    }

    protected Serializable getDbId(NodeRef nodeRef) {
    	return nodeService.getProperty(nodeRef, ContentModel.PROP_NODE_DBID);
    }
    
	/*
	 * IoC/DI Spring
	 */
	protected NodeService nodeService;
	private PolicyComponent policyComponent;
	
	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}


}
