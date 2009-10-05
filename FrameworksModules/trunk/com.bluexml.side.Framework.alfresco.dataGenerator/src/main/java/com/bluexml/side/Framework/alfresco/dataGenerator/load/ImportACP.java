/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.importer.ACPImportPackageHandler;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.view.ImporterService;
import org.alfresco.service.cmr.view.Location;

/**
 * @author davidchevrier
 *
 */
public class ImportACP {
	
	private FileFolderService fileFolderService;
	private ServiceRegistry serviceRegistry;
	private NodeService nodeService;

	/**
	 * @return the fileFolderService
	 */
	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	/**
	 * @param fileFolderService the fileFolderService to set
	 */
	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	/**
	 * @param nodeService the nodeService to set
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	
	/**
	 * @return the serviceRegistry
	 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	/**
	 * @param serviceRegistry the serviceRegistry to set
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public NodeRef manageAlfrescoRepository(String pathToAlfrescoRepository){
		NodeRef rootNode = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		
		List<String> foldersOnPathRepository = Arrays.asList(pathToAlfrescoRepository.split("/"));
		
		FileInfo lastFolder = fileFolderService.makeFolders(rootNode, foldersOnPathRepository, ContentModel.TYPE_FOLDER);
		List<FileInfo> contentFiles = fileFolderService.listFiles(lastFolder.getNodeRef());
		if (contentFiles.size() > 0){
			for (FileInfo file : contentFiles){
				fileFolderService.delete(file.getNodeRef());
			}
		}
		//TODO copy of ACP (but where?)
		return lastFolder.getNodeRef();
	}
	
	public void importACP(File acp,NodeRef repository){
		ImporterService importerService = serviceRegistry.getImporterService();
		ACPImportPackageHandler importerHandler = new ACPImportPackageHandler(acp,ACPImportPackageHandler.DEFAULT_ENCODING);
		importerService.importView(importerHandler, new Location(repository), null, null);
	}
	
	public void saveACP(File acp, NodeRef folder) throws IOException{
        FileInfo fileNode = fileFolderService.create(folder,acp.getName(),ContentModel.TYPE_CONTENT);
        ContentWriter writer = fileFolderService.getWriter(fileNode.getNodeRef());
        writer.putContent(acp);
	}

}
