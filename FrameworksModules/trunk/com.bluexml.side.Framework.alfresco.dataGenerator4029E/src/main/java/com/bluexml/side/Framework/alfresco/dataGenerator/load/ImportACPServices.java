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
