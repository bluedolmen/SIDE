package com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.RegexQNamePattern;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.NodeFilterer;
import com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeService;

public class BasicContentReplication implements ContentReplication {

	public Logger logger = Logger.getLogger(getClass());
	
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.schemaManagement.ContentReplication#removeExistingData()
	 */
	public void removeExistingData(QName modelName) {
		throw new UnsupportedOperationException();
	}
	
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.schemaManagement.ContentReplication#addExistingData()
	 */
	public void addExistingData(QName modelName) {
		
		// TODO : implement a better strategy to get the whole set of existing nodes
		ResultSet nodes = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_XPATH, "//*");
		Iterator<ResultSetRow> it = nodes.iterator();

		List<AssociationRef> associationsToAdd = new ArrayList<AssociationRef>();
		List<ChildAssociationRef> childAssociationsToAdd = new ArrayList<ChildAssociationRef>();
		
		HashSet<QName> currentDictionaryTypes = new HashSet<QName>();
		currentDictionaryTypes.addAll(dictionaryService.getTypes(modelName));
		
		while (it.hasNext()) {
			ResultSetRow resultSetRow = it.next();
			NodeRef nodeRef = resultSetRow.getNodeRef();
			
			/*
			 * only processes filtered nodes and nodes that are defined in the current model definition (TypeDefinition in the Alfresco dictionary is not null)
			 */
			final QName typeQName = nodeService.getType(nodeRef);
			if (nodeFilterer.acceptOnName(typeQName) && currentDictionaryTypes.contains(typeQName) ) {
				addNode(nodeRef);
				
				customActionManager.doInContentReplication(nodeRef);
				
				List<AssociationRef> associations = nodeService.getTargetAssocs(nodeRef, RegexQNamePattern.MATCH_ALL);
				for (AssociationRef association : associations) {
					/*
					 * if the target of the association is an acceptable node, then the association will be created
					 */
					if (nodeFilterer.acceptOnName(association.getTypeQName()) && 
							nodeFilterer.acceptOnName(nodeService.getType(association.getTargetRef())) ) 
					{
						associationsToAdd.add(association);
					}
				}

				List<ChildAssociationRef> childAssociations = nodeService.getChildAssocs(nodeRef);
				for (ChildAssociationRef association : childAssociations) {
					/*
					 * if the target of the association is an acceptable node, then the association will be created
					 */
					if (nodeFilterer.acceptOnName(association.getTypeQName()) && 
							nodeFilterer.acceptOnName(nodeService.getType(association.getChildRef())) ) 
					{
						childAssociationsToAdd.add(association);
					}
				}

				
			}
		}
		
		nodes.close();
		
		for (AssociationRef associationRef : associationsToAdd) {
			addAssociation(associationRef);
		}
		
		for (ChildAssociationRef associationRef : childAssociationsToAdd) {
			addChildAssociation(associationRef);
		}
	}
	
	/*
	 * Helper methods
	 */
	
	private void addNode(NodeRef nodeRef) {
		logger.debug("Replicating node with id " + nodeRef.getId());
		synchroNodeService.create(nodeRef);
	}
	
	private void addAssociation(AssociationRef associationRef) {
		logger.debug("Replicating association " + associationRef);
		synchroNodeService.createAssociation(associationRef.getSourceRef(), associationRef.getTargetRef(), associationRef.getTypeQName());
	}
	
	private void addChildAssociation(ChildAssociationRef associationRef) {
		logger.debug("Replicating child association " + associationRef);
		synchroNodeService.createAssociation(associationRef.getParentRef(), associationRef.getChildRef(), associationRef.getTypeQName());
	}
	
	/*
	 * Spring IoC/DI material
	 */
	
	private SearchService searchService;
	private NodeFilterer nodeFilterer;
	private NodeService synchroNodeService; /* BlueXML Synchronization NodeService */
	private org.alfresco.service.cmr.repository.NodeService nodeService;
	private DictionaryService dictionaryService;
	private CustomActionManager customActionManager;
	
	public void setSearchService (SearchService searchService_) {
		searchService = searchService_;
	}
	
	public void setNodeFilterer (NodeFilterer nodeFilterer_) {
		nodeFilterer = nodeFilterer_;
	}
	
	public void setSynchroNodeService (NodeService nodeService_) {
		synchroNodeService = nodeService_;
	}
	
	public void setNodeService (org.alfresco.service.cmr.repository.NodeService nodeService_) {
		nodeService = nodeService_;
	}
	
	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setCustomActionManager(CustomActionManager customActionManager_) {
		customActionManager = customActionManager_;
	}
}

