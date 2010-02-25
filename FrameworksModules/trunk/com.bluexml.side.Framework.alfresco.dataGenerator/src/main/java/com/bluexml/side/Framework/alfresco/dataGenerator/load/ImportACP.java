/**
 * This class manage the deployment of the generated .acp
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.load;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.importer.ACPImportPackageHandler;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.view.ImporterService;
import org.alfresco.service.cmr.view.Location;

/**
 * @author davidchevrier
 * 
 */
public class ImportACP {
	
	private ImportACPServices services;

	private FileFolderService fileFolderService;
	private ServiceRegistry serviceRegistry;
	
	private String login;
	private String password;
	
	private class CustomACPImportPackageHandler extends ACPImportPackageHandler{

	    public CustomACPImportPackageHandler(File zipFile, String dataFileEncoding) {
	        super(zipFile, dataFileEncoding);
	    }

	    @Override
	    public void endImport() {
	        super.endImport();
	        try {
	            this.zipFile.close();
	        } catch (IOException e) {
	            throw new RuntimeException("Unable to close the zip file", e);
	        }
	    }
	}
	
	/**
	 * @return the services
	 */
	public ImportACPServices getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(ImportACPServices services) {
		this.services = services;
	}

	/**
	 * @return the fileFolderService
	 */
	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	/**
	 * @param fileFolderService
	 *            the fileFolderService to set
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
	 * @param serviceRegistry
	 *            the serviceRegistry to set
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @param pathToAlfrescoRepository
	 * @return the NodeRef's repository having the given path in Alfresco 
	 * @throws Exception
	 */
	public NodeRef manageAlfrescoRepository(String pathToAlfrescoRepository) throws Exception {
		// If folder of given path exists, we delete its contents before importing the .acp;
		// If not, it is created.
		
		services.authenticate();
		
		NodeRef container = createOrGiveMeFolder(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, pathToAlfrescoRepository);
		
		List<FileInfo> contentFiles = fileFolderService.list(container);
		if (contentFiles.size() > 0) {
			for (FileInfo file : contentFiles) {
				if (fileFolderService.exists(file.getNodeRef())){
					fileFolderService.delete(file.getNodeRef());
				}
			}
		}		
		return container;
	}
	
	/**
	 * manage the generated .acp deployment in the given Alfresco repository
	 * @param acp
	 * @param repository
	 * @return true if the process is successful
	 */
	public boolean importACP(File acp, NodeRef repository) throws Exception {
		ImporterService importerService = serviceRegistry.getImporterService();

		CustomACPImportPackageHandler importerHandler = new CustomACPImportPackageHandler(acp, ACPImportPackageHandler.DEFAULT_ENCODING);
		importerService.importView(importerHandler, new Location(repository), null, null);
		importerHandler.endImport();
		
		return true;
	}
	
	/**
	 * save the generated .acp in the given Alfresco repository
	 * @param acp
	 * @param parent
	 * @return true if the process is successful
	 * @throws IOException
	 */
	public boolean saveACP(File acp, NodeRef parent) throws IOException {
		//Choice has been made to save the generated .acp in order to reproduce manually a data set
		NodeRef acpFile = fileFolderService.create(parent, acp.getName(), ContentModel.TYPE_CONTENT).getNodeRef();
		ContentWriter writer = fileFolderService.getWriter(acpFile);
		writer.putContent(acp);
		return true;
	}
	
	/**
	 * 
	 * @param store
	 * @param xpath
	 * @return the NodeRef's folder if exists; else the folder and its reference are created
	 */
	public NodeRef createOrGiveMeFolder(StoreRef store, String xpath) {
		ResultSet rs = serviceRegistry.getSearchService().query(store, SearchService.LANGUAGE_XPATH, xpath);
		NodeRef nr = null;
		if (rs.length() == 1) {
			nr = rs.getNodeRefs().get(0);
		} else {
			// must create the folder
			String[] tokenizedXpath = xpath.split("/");
			String folderTitle = (tokenizedXpath[tokenizedXpath.length-1].split(":"))[1];
			NodeRef parent = createOrGiveMeFolder(store, xpath.replaceFirst("/[^/]*$", ""));
			FileInfo acpHome = serviceRegistry.getFileFolderService().create(parent, folderTitle, ContentModel.TYPE_FOLDER);
			nr = acpHome.getNodeRef();
		}
		return nr;
	}
}
