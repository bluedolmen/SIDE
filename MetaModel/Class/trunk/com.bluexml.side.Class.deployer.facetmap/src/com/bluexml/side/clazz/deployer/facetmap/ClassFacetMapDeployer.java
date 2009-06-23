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
import java.io.IOException;
import java.util.ArrayList;

import com.bluexml.side.clazz.generator.facetmap.utils.FacetmapConstants;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.libs.FileHelper;



/**
 * 
 * @author <a href="mailto:pbertrand@bluexml.com"> Pierre BERTRAND </a>
 * 
 */
public class ClassFacetMapDeployer extends Deployer implements FacetmapConstants {
	
	// System locations where the file will be deployed
	String TOMCAT_REPERTORY;
	String CMIS_REPERTORY;
	String FACETMAP_FACETS;
	String FACETMAP_CONTENT;
	String FACETMAP_FACETS_REPERTORY;
	String FACETMAP_CONTENT_REPERTORY;
	// Arrays with the files to deploy
	ArrayList<File> commonDeployedFiles = new ArrayList<File>();
	ArrayList<File> facetsDeployedFiles = new ArrayList<File>();
	
	
	public String getParam(String ParamName) {
		return getGenerationParameters().get(ParamName);
	}
	
	private void initParams(){
		String tomcatInstallation = getParam(CONFIGURATION_TOMCAT_INSTALLATION);
		if (!tomcatInstallation.endsWith(FILESEP))
			tomcatInstallation += FILESEP;
		TOMCAT_REPERTORY = tomcatInstallation + tomcat;
		CMIS_REPERTORY = tomcatInstallation + tomcat+ alfresco + webinf + "classes"+FILESEP + alfresco + "webscripts"+FILESEP + "com"+FILESEP + "bluexml"+FILESEP;
		FACETMAP_FACETS = getParam(CONFIGURATION_FACETMAP_FACET_HOME);
		FACETMAP_CONTENT = getParam(CONFIGURATION_FACETMAP_CONTENT_HOME);
		FACETMAP_FACETS_REPERTORY = TOMCAT_REPERTORY + webapps + FACETMAP_FACETS + FILESEP;
		FACETMAP_CONTENT_REPERTORY = TOMCAT_REPERTORY + webapps + FACETMAP_CONTENT + FILESEP;
	}
	
	private void checkfiles() throws Exception {
		String list_of_missing_files = "";
		Boolean filemissing = Boolean.FALSE;
		for (File f : commonDeployedFiles) {
			if (!f.exists()){
				filemissing = Boolean.TRUE;
				list_of_missing_files += "\n"+f.getAbsolutePath();
			}
		}
		for (File f : facetsDeployedFiles) {
			if (!f.exists()){
				filemissing = Boolean.TRUE;
				list_of_missing_files += "\n"+f.getAbsolutePath();
			}
		}
		if (filemissing)
			throw new Exception("Missing the following generated files:"+list_of_missing_files+"\n deployment aborted.");
	}
	
	private void copyFile(File src, File dest) throws IOException {
		FileHelper.copyFiles(src, dest, true);
	}
	
	@Override
	protected void clean(File fileToDeploy) throws Exception {}
	
	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		//Deploy
		deployFacetMapCommon();
		deployFacets();
	}
	
	private void deployFacetMapCommon() throws Exception {
		// Getting Files in generation
		// Path where the files need to be copied
		String path_cmisjs = CMIS_REPERTORY + cmisjs_filename;
		String cmis2xfml_path_facets =  FACETMAP_FACETS_REPERTORY + webinf + xsl + cmis2xfml_filename;
		String cmis2xfml_path_content =  FACETMAP_CONTENT_REPERTORY + webinf + xsl +cmis2xfml_filename;
		String cmis2xfml_properties_path_facets = FACETMAP_FACETS_REPERTORY + webinf + cmis2xfml_properties_filename;
		String cmis2xfml_properties_path_content =  FACETMAP_CONTENT_REPERTORY + webinf + cmis2xfml_properties_filename;
		String facetmap_configuration_properties_path_facets = FACETMAP_FACETS_REPERTORY + webinf + facetmap_configurationproperties_filename;
		String facetmap_configuration_properties_path_content =  FACETMAP_CONTENT_REPERTORY + webinf + facetmap_configurationproperties_filename;
		// Copying
		copyFile(commonDeployedFiles.get(0), new File(path_cmisjs));
		copyFile(commonDeployedFiles.get(1), new File(cmis2xfml_path_facets));
		copyFile(commonDeployedFiles.get(1), new File(cmis2xfml_path_content));
		copyFile(commonDeployedFiles.get(2), new File(cmis2xfml_properties_path_facets));
		copyFile(commonDeployedFiles.get(2), new File(cmis2xfml_properties_path_content));
		copyFile(commonDeployedFiles.get(3), new File(facetmap_configuration_properties_path_facets));
		copyFile(commonDeployedFiles.get(3), new File(facetmap_configuration_properties_path_content));
	}
	
	private void deployFacets() throws Exception {
		// Path where the files need to be copied
		String buildProperties_path = FACETMAP_FACETS_REPERTORY + webinf + buildProperties_filename;
		String basicFacets_path = FACETMAP_FACETS_REPERTORY + xsl + display + includes +  basicFacets_filename;
		String presentation_path = FACETMAP_FACETS_REPERTORY + xsl + display + rightnav_properties_filename;
		// Copying
		copyFile(facetsDeployedFiles.get(0), new File(buildProperties_path));
		copyFile(facetsDeployedFiles.get(1), new File(basicFacets_path));
		copyFile(facetsDeployedFiles.get(2), new File(presentation_path));
	}
	
	@Override
	protected void postProcess(File fileToDeploy) throws Exception {	}

	@Override
	/**
	 * Initialise the params of the deployer and check if the generation has completed successfully
	 */
	protected void preProcess(File fileToDeploy) throws Exception {
		this.initParams();
		//CommonFiles
		String genpathcomon = fileToDeploy.getPath() + FILESEP + common;
		File cmisjs =  new File (genpathcomon + scriptcmis + cmisjs_filename);
		File cmis2xfml = new File(genpathcomon + facetmap + webinf + xsl + cmis2xfml_filename);
		File cmis2xfml_properties = new File(genpathcomon + facetmap + webinf + cmis2xfml_properties_filename);
		File facetmap_properties = new File(genpathcomon + facetmap + webinf + facetmap_configurationproperties_filename);
		commonDeployedFiles.add(cmisjs);
		commonDeployedFiles.add(cmis2xfml);
		commonDeployedFiles.add(cmis2xfml_properties);
		commonDeployedFiles.add(facetmap_properties);
		//FacetsFiles
		String genpathfacets = fileToDeploy.getPath() + FILESEP + facets;
		File buildProperties = new File(genpathfacets + facetmap + webinf + buildProperties_filename);
		File basicFacets = new File(genpathfacets + facetmap + xsl + display + includes + basicFacets_filename);
		File rightnav = new File(genpathfacets + facetmap + xsl + display + rightnav_properties_filename);
		facetsDeployedFiles.add(buildProperties);
		facetsDeployedFiles.add(basicFacets);
		facetsDeployedFiles.add(rightnav);
		//Check
		checkfiles();
	}

	/**
	 * This method check if the user have the license to use this deployer.
	 * 
	 * @return true if the deployer can be used.
	 */
	public boolean check() {
		return true;
	}

}
