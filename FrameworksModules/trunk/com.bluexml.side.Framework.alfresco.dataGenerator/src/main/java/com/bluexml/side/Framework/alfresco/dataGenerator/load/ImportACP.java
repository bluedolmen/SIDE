/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.load;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.alfresco.cmis.CMISDictionaryModel;
import org.alfresco.repo.importer.ACPImportPackageHandler;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.view.ImporterService;
import org.alfresco.service.cmr.view.Location;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

/**
 * @author davidchevrier
 *
 */
public class ImportACP {
	
	private static final String STORE_ROOT_ID = "f32610b1-19a3-4e98-bba8-2c99ed6c50e5";
	
	private FileFolderService fileFolderService;
	private ServiceRegistry serviceRegistry;

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
		NodeRef rootNode = new NodeRef(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,STORE_ROOT_ID);
		String[] pathRepository = pathToAlfrescoRepository.split("/");
		List<String> foldersOnPathRepository = new ArrayList<String>();
		for (int indexReposirory = 1; indexReposirory < pathRepository.length; indexReposirory++){
			foldersOnPathRepository.add(pathRepository[indexReposirory]);
		}
		QName folderType = QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI,CMISDictionaryModel.FOLDER_OBJECT_TYPE);
		
		FileInfo lastFolder = fileFolderService.makeFolders(rootNode, foldersOnPathRepository, folderType);
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
        FileInfo fileNode = fileFolderService.create(folder,acp.getName(),QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, "content"));
        ContentWriter writer = fileFolderService.getWriter(fileNode.getNodeRef());
        writer.putContent(acp);
	}

}
