/**
 * This class contains useful services for import .acp management
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.load;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.security.AuthenticationService;

/**
 * @author dchevrier
 *
 */
public class ImportACPServices {
	
	private ImportACP importer;
	
	private ServiceRegistry serviceRegistry;

	/**
	 * @return the importer
	 */
	public ImportACP getImporter() {
		return importer;
	}

	/**
	 * @param importer the importer to set
	 */
	public void setImporter(ImportACP importer) {
		this.importer = importer;
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

	/**
	 * allows the webscript's user authentication 
	 * @throws Exception
	 */
	public void authenticate() throws Exception{
		String login = importer.getLogin();
		String passwd = importer.getPassword();
		if (!("").equals(login) && !("").equals(passwd)){
			AuthenticationService serviceAuthentification = serviceRegistry.getAuthenticationService();
			char[] password = importer.getPassword().toCharArray();
			serviceAuthentification.authenticate(login,password);
		}
		else{
			throw new Exception("Alfresco login and/or Alfresco password must be set.");
		}
	}

}
