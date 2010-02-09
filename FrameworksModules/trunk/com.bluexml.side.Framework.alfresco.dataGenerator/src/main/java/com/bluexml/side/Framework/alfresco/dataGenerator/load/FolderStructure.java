/**
 * This class ensures, if the option is checked in the webscript's form, the organization of the content
 * instances grouped by types into folders
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.load;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.model.filefolder.FileFolderServiceImpl;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchService;

/**
 * @author dchevrier
 *
 */
public class FolderStructure {
	
	private ServiceRegistry service;
	
	/**
	 * @return the service
	 */
	public ServiceRegistry getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(ServiceRegistry service) {
		this.service = service;
	}

	public boolean manageFolders(NodeRef repository) throws Exception {
		List<FileInfo> nodes = getContentInstances(repository);
		for (int l = 0; l < nodes.size(); l++){
			NodeRef node = nodes.get(l).getNodeRef();
			String nodeName = getNodeName(node);
			if (nodeName.length() == 0){
				throw new Exception("Node " + node.getId() + "has no name ...");
			}
			String[] nameParts = nodeName.split("_|[.]");
			String folderName = nameParts[0];
			NodeRef folder = getFolder(folderName, repository); 
			if(folder == null){
				folder = service.getFileFolderService().create(repository, folderName, ContentModel.TYPE_FOLDER).getNodeRef();
			}
			service.getFileFolderService().move(node, folder, nodeName);
		}
		return true;
	}

	private NodeRef getFolder(String folderName, NodeRef repository) {
		NodeRef folder= null;
		List<FileInfo> folders = service.getFileFolderService().listFolders(repository);
		if (!folders.isEmpty()){
			for (int i = 0; i < folders.size(); i++){
				if (folders.get(i).getName().equals(folderName)){
					return folders.get(i).getNodeRef();
				}
			}
		}
		return folder;
	}

	private String getNodeName(NodeRef node) {
		Serializable nameProperty = service.getNodeService().getProperty(node, ContentModel.PROP_NAME);
		return nameProperty.toString();
	}

	private List<FileInfo> getContentInstances(NodeRef repository) {
		return service.getFileFolderService().listFiles(repository);
	}

}
