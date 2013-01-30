package com.bluexml.side.framework.alfresco.webscriptExtension;

import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.policy.BehaviourFilter;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.log4j.Logger;

/**
 * Extend Alfresco Javascript API with rule service
 * siderule.getTasksDescriptionOfDocument(document) to get the workflow task on a document, including subprocess
 */
public class NodeScriptExtension extends BaseScopableProcessorExtension {

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * Method to set a new creator of a document
	 * 
	 * @param document
	 *            the document to update the creator
	 * @param newcreator 
	 * 			  the new creator username
	 * @param creator 
	 * 			  the current creator to change by newCreator
	 * @param children 
	 * 			  if true, change creator of children having the same creator than document
	 * @param setOwnership 
	 * 			  if true, set the ownership of the updated document to newCreator
	 *            
	 */
	public void setCreator(ScriptNode document, String newCreator, String creator, boolean children, boolean setOwnership) {
		if (document != null) {
			setCreator(document.getNodeRef(), newCreator, creator, children, setOwnership);
		} else {
        	logger.error("The document Parameter is mandatory and must not be null !!!");				        				
		}
	}
 
	/**
	 * Method to set a new creator of a document
	 * If the previous creator has been provided, only the document having this creator are changed.
	 * 
	 * @param document
	 *            the document to update the creator
	 * @param newcreator 
	 * 			  the new creator username
	 * @param creator 
	 * 			  the current creator to change by newCreator
	 * @param children 
	 * 			  if true, change creator of children having the same creator than document
	 * @param setOwnership 
	 * 			  if true, set the ownership of the updated document to newCreator
	 *            
	 */
	public void setCreator(NodeRef document, String newCreator, String creator, boolean children, boolean setOwnership) {
        if (document != null && newCreator != null) {
			if (serviceRegistry.getPersonService().getPerson(newCreator) != null) {
				String currentCreator = (String)nodeService.getProperty(document,ContentModel.PROP_CREATOR);
	        	if (!newCreator.equals(currentCreator)) {
		        	if (logger.isDebugEnabled()) logger.debug("Process document "+nodeService.getProperty(document, ContentModel.PROP_NAME));
					// we only change document having the same creator if creator has been provided
		        	if (creator == null || creator.equals(currentCreator)) {
			        	behaviourFilter.disableBehaviour(document, ContentModel.ASPECT_AUDITABLE); 
						nodeService.setProperty(document, ContentModel.PROP_CREATOR, newCreator);
						behaviourFilter.enableBehaviour(document, ContentModel.ASPECT_AUDITABLE); 
					    if (logger.isDebugEnabled()) logger.debug("New Document creator set up : "+ nodeService.getProperty(document, ContentModel.PROP_CREATOR));        
					}
					// we only change the ownership for document having the same owner than the provided creator if any
		        	String owner = serviceRegistry.getOwnableService().getOwner(document);		        	
		        	if (owner != null && (creator == null || creator.equals(owner))) {
		        		serviceRegistry.getOwnableService().setOwner(document, newCreator);
					    if (logger.isDebugEnabled()) logger.debug("New Document owner set up : "+ serviceRegistry.getOwnableService().getOwner(document));        
					}
				}
	        	List<ChildAssociationRef>  child = nodeService.getChildAssocs(document);
	        	if (children && child != null && !child.isEmpty()) {
	        		for (ChildAssociationRef assocNode : child) {
		        		NodeRef assocRef = assocNode.getChildRef();
	        			setCreator(assocRef,newCreator, creator, children, setOwnership);
	        		}
	        	}
	        } else {
	        	logger.error("Invalid username for the new creator '"+newCreator+"' to add on document  "+nodeService.getProperty(document, ContentModel.PROP_NAME)+ " !!!");				
			}
        } else {
        	logger.error("The 2 Parameters document and newCreator are mandatory and must not be null !!!");				        	
        }
	}


	/**
	 * Method to set a new creator of a list of document
	 * 
	 * @param documentList
	 *            the document list containing the documents to update the creator
	 * @param newcreator 
	 * 			  the new creator username
	 * @param creator 
	 * 			  the current creator to change by newCreator
	 * @param children 
	 * 			  if true, change creator of children having the same creator than document
	 * @param setOwnership 
	 * 			  if true, set the ownership of the updated document to newCreator
	 *            
	 */
	public void setCreator(List<ScriptNode> documentList, String newCreator, String creator, boolean children, boolean setOwnership) {
        if (documentList != null && documentList.size() > 0 && newCreator != null) {
			if (serviceRegistry.getPersonService().getPerson(newCreator) != null) {
				for (ScriptNode document : documentList) setCreator(document, newCreator, creator, children, setOwnership);
			} else {
	        	logger.error("Invalid username for the new creator '"+newCreator+"' to add on documentList  !!!");				
			}
        } else {
        	logger.error("The 2 Parameters documentList and newCreator are mandatory and must not be null; documentList must not be empty !!!");				        	
        }
	}

	
	private ServiceRegistry serviceRegistry;
	private NodeService nodeService;
    private BehaviourFilter behaviourFilter;

	/**
	 * @return the serviceRegistry
	 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	/**
	 * @param serviceRegistry
	 *            the serviceRegistry to set
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	/**
	 * @return the nodeService
	 */
	public NodeService getNodeService() {
		return nodeService;
	}

	/**
	 * @param nodeService
	 *            the Node Service to set
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * @return the nodeService
	 */
	public BehaviourFilter getBehaviourFilter() {
		return behaviourFilter;
	}

	/**
	 * @param behaviourFilter
	 *            the BehaviourFilter Service to set
	 */
    public void setBehaviourFilter(BehaviourFilter behaviourFilter)
    {
        this.behaviourFilter = behaviourFilter;
    }



}
