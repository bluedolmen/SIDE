package com.bluexml.side.Framework.alfresco.signature.repo.action.executer;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public abstract class BasePDFActionExecuter extends ActionExecuterAbstractBase {
    
    protected static final String FILE_EXTENSION = ".pdf";
    protected static final String FILE_MIMETYPE = "application/pdf";
    protected ServiceRegistry serviceRegistry;
    
    /**
     * Set a service registry to use, this will do away with all of the 
     * individual service registrations
     * @param serviceRegistry
     */
    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
    	this.serviceRegistry = serviceRegistry;
    }
    
    /**
     * @param actionedUponNodeRef
     * @return
     */
    protected ContentReader getReader(NodeRef nodeRef)
    {
        // First check that the node is a sub-type of content
        QName typeQName = serviceRegistry.getNodeService().getType(nodeRef);
        if (serviceRegistry.getDictionaryService().isSubClass(typeQName,
                ContentModel.TYPE_CONTENT) == false)
        {
            // it is not content, so can't transform
            return null;
        }

        // Get the content reader
        ContentReader contentReader = serviceRegistry.getContentService().getReader(
                nodeRef, ContentModel.PROP_CONTENT);

        return contentReader;
    }

    /**
     * @param ruleAction
     * @param filename
     * @return
     */
    protected ContentWriter getWriter(String filename, NodeRef destinationParent)
    {

        FileInfo fileInfo = serviceRegistry.getFileFolderService().create(destinationParent,
                filename, ContentModel.TYPE_CONTENT);

        // get the writer and set it up
        ContentWriter contentWriter = serviceRegistry.getContentService().getWriter(fileInfo
                .getNodeRef(), ContentModel.PROP_CONTENT, true);

        return contentWriter;
    }
}
