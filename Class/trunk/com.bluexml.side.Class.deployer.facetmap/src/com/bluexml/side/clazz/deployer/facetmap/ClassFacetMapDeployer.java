/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2009
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.clazz.deployer.facetmap;

import java.io.File;

import com.bluexml.side.application.deployer.Deployer;



/**
 * 
 * @author <a href="mailto:pbertrand@bluexml.com"> Pierre BERTRAND </a>
 * 
 */
public class ClassFacetMapDeployer extends Deployer {
	public static String CONFIGURATION_FACETMAP_FACET_HOME = "FACETMAP_FACET_NAME";
	public static String CONFIGURATION_FACETMAP_CONTENT_HOME = "FACETMAP_CONTENT_NAME";
	public static String CONFIGURATION_TOMCAT_INSTALLATION = "CATALINA_HOME";
	static final String webapps = "webapps";
	static final String webappName = "facetmap";
	static final String cmis2xfml_filename = "cmis2xfml.xsl";
	static final String cmisjs_properties_filename = "index.get.js";
	static final String cmisjs_filename = "cmisTransformProperties.xml";
	
	//
	public String getParam(String ParamName) {
		return getGenerationParameters().get(ParamName);
	}
	
	// Emplacements de la xsl générée
	public String getXslLocationInTomcat(String FacetmapParam) {
			return getParam(CONFIGURATION_TOMCAT_INSTALLATION) + File.separator + webapps + File.separator + getParam(FacetmapParam) + File.separator + "WEB-INF" + File.separator + "xsl" + File.separator;
	}
	
	public String getXslLocationInGeneration() {
		return File.separator+ "xsl" + File.separator + cmis2xfml_filename;
	}
	
	//Emplacements des paramètres de la XSL
	public String getXslPropertiesLocationInTomcat(String FacetmapParam) {
			return getParam(CONFIGURATION_TOMCAT_INSTALLATION) + File.separator + webapps + File.separator + getParam(FacetmapParam) + File.separator + "WEB-INF" + File.separator;
	}
	
	public String getXslPropertiesLocationInGeneration() {
		return File.separator + cmisjs_properties_filename;
	}
	//Emplacements du fichier javascript CMIS
	public String getCMISLocationInTomcat() {
		return getParam(CONFIGURATION_TOMCAT_INSTALLATION) + File.separator + webapps + File.separator + "alfresco" + File.separator + "WEB-INF" + File.separator;
	}
	
	public String getCMISLocationInGeneration() {
		return File.separator + cmisjs_filename;
	}

	
	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// remove existing deployed alfresco webapp.
//		FileHelper.deleteFile(new File(getCMISLocationInTomcat()));
//		FileHelper.deleteFile(new File(getXslLocationInTomcat(CONFIGURATION_FACETMAP_CONTENT_HOME)));
//		FileHelper.deleteFile(new File(getXslPropertiesLocationInTomcat(CONFIGURATION_FACETMAP_FACET_HOME)));
//		FileHelper.deleteFile(new File(getXslLocationInTomcat(CONFIGURATION_FACETMAP_CONTENT_HOME)));
//		FileHelper.deleteFile(new File(getXslPropertiesLocationInTomcat(CONFIGURATION_FACETMAP_FACET_HOME)));
	}
	
	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		// Getting Files
		File cmisjs =  new File (fileToDeploy.getPath()+getCMISLocationInGeneration());
		File cmis2xfml = new File(fileToDeploy.getPath()+getXslLocationInGeneration());
		File xslproperties = new File(fileToDeploy.getPath()+getXslPropertiesLocationInGeneration());
		//Test if missing file
		Boolean filemissing = Boolean.FALSE;
		String list_of_missing_files = "";
		if (!cmisjs.exists()){
			filemissing = Boolean.TRUE;
			list_of_missing_files += "\n"+cmisjs.getAbsolutePath();
		}if (!cmis2xfml.exists()){
			filemissing = Boolean.TRUE;
			list_of_missing_files += "\n"+cmis2xfml.getAbsolutePath();
		}if (!xslproperties.exists()) {
			filemissing = Boolean.TRUE;
			list_of_missing_files += "\n"+xslproperties.getAbsolutePath();
		}
		if (filemissing)
			throw new Exception("Missing files :"+list_of_missing_files);
		System.out.println("Files found need to copy to :");
		// Path where the files need to be copied
		String path_cmisjs = getCMISLocationInTomcat()+cmisjs_filename;
		String path_cmis2xfml1 = getXslLocationInTomcat(CONFIGURATION_FACETMAP_CONTENT_HOME)+cmis2xfml_filename;
		String path_cmis2xfml2 = getXslLocationInTomcat(CONFIGURATION_FACETMAP_FACET_HOME)+cmis2xfml_filename;
		String path_xslproperties1 = getXslPropertiesLocationInTomcat(CONFIGURATION_FACETMAP_CONTENT_HOME)+cmisjs_properties_filename;
		String path_xslproperties2 = getXslPropertiesLocationInTomcat(CONFIGURATION_FACETMAP_FACET_HOME)+cmisjs_properties_filename;
		System.out.println(path_cmisjs);
		System.out.println(path_cmis2xfml1);
		System.out.println(path_cmis2xfml2);
		System.out.println(path_xslproperties1);
		System.out.println(path_xslproperties2);
		

	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {}

	/**
	 * This method check if the user have the license to use this deployer.
	 * 
	 * @return true if the deployer can be used.
	 */
	public boolean check() {
		return true;
	}

}
