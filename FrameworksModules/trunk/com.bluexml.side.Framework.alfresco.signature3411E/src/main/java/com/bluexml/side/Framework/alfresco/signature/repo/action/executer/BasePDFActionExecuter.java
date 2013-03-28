/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.Framework.alfresco.signature.repo.action.executer;

import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
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
    	List<ChildAssociationRef> myChildren = serviceRegistry.getNodeService().getChildAssocs(destinationParent);
    	for (ChildAssociationRef myChild : myChildren) {
    		if (serviceRegistry.getNodeService().getProperty(myChild.getChildRef(), ContentModel.PROP_NAME).equals(filename)) {
    			//serviceRegistry.getFileFolderService().delete(myChild.getChildRef());
    			serviceRegistry.getPermissionService().getPermissions(myChild.getChildRef());
    			try {
    				serviceRegistry.getNodeService().deleteNode(myChild.getChildRef());
    			} catch(Exception e) {
    				serviceRegistry.getNodeService().setProperty(myChild.getChildRef(), ContentModel.PROP_NAME, "filename" + ".old");
    			}
    			break;
    		}
    	}
    	FileInfo fileInfo = serviceRegistry.getFileFolderService().create(destinationParent,
                filename, ContentModel.TYPE_CONTENT);

        // get the writer and set it up
        ContentWriter contentWriter = serviceRegistry.getContentService().getWriter(fileInfo
                .getNodeRef(), ContentModel.PROP_CONTENT, true);

        return contentWriter;
    }
}
