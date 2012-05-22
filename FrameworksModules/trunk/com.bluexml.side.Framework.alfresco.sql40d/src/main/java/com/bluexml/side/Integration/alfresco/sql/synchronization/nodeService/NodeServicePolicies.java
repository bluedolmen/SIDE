package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

import java.util.Collection;

import org.alfresco.repo.policy.AssociationPolicy;
import org.alfresco.repo.policy.ClassPolicy;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.SqlCommon;

public class NodeServicePolicies {

	
    public interface OnCreateNodePolicy extends ClassPolicy
    {
    	static String NAMESPACE = SqlCommon.BLUEXML_SQL_EXTENSION_URI;
    	
        /**
         * Called when a new node has been created.
         * 
         * @param nodeRef  the created node reference
         */
        public void onCreateNode(NodeRef nodeRef);
    }
    
    public interface OnDeleteNodePolicy extends ClassPolicy
    {
    	static String NAMESPACE = SqlCommon.BLUEXML_SQL_EXTENSION_URI;
    	
        /**
         * Called when a node has been deleted.
         * 
         * @param nodeRef  the deleted node reference
         */
        public void onDeleteNode(NodeRef nodeRef);
    }
    
    public interface OnUpdatePropertiesPolicy extends ClassPolicy
    {
    	static String NAMESPACE = SqlCommon.BLUEXML_SQL_EXTENSION_URI;
    	
        /**
         * Called after a node's properties have been changed.
         * 
         * @param nodeRef reference to the updated node
         * @param changes the node properties that change
         */
        public void onUpdateProperties(
                NodeRef nodeRef,
                Collection<QName> changes);
        
    }
    
    public interface OnCreateAssociationPolicy extends AssociationPolicy
    {
    	static String NAMESPACE = SqlCommon.BLUEXML_SQL_EXTENSION_URI;

    	/**
         * Called after a regular node association is created.
         * 
         * @param nodeAssocRef the regular node association that was created
         */
        public void onCreateAssociation(NodeRef sourceNodeRef, NodeRef targetNodeRef, QName typeQName);
    }

    public interface OnDeleteAssociationPolicy extends AssociationPolicy
    {
    	static String NAMESPACE = SqlCommon.BLUEXML_SQL_EXTENSION_URI;

    	/**
         * Called after a regular node association is deleted.
         * 
         * @param nodeAssocRef the regular node association that was removed
         */
        public void onDeleteAssociation(NodeRef sourceNodeRef, NodeRef targetNodeRef, QName typeQName);
    }

}
