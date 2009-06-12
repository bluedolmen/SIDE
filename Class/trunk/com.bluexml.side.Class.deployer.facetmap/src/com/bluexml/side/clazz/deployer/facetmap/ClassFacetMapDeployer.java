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
import java.util.ArrayList;

import com.bluexml.side.application.deployer.Deployer;
import com.bluexml.side.clazz.generator.facetmap.utils.FacetmapConstants;
import com.bluexml.side.util.libs.FileHelper;



/**
 * 
 * @author <a href="mailto:pbertrand@bluexml.com"> Pierre BERTRAND </a>
 * 
 */
public class ClassFacetMapDeployer extends Deployer implements FacetmapConstants {
	
	String TOMCAT_REPERTORY = getParam(CONFIGURATION_TOMCAT_INSTALLATION)+tomcat;
	String CMIS_REPERTORY = getParam(CONFIGURATION_TOMCAT_INSTALLATION)+ tomcat+ alfresco + webinf + "classes"+FILESEP + alfresco + "webscripts"+FILESEP + "com"+FILESEP + "bluexml"+FILESEP;
	String FACETMAP_FACETS = getParam(CONFIGURATION_FACETMAP_FACET_HOME);
	String FACETMAP_CONTENT = getParam(CONFIGURATION_FACETMAP_CONTENT_HOME);
	String FACETMAP_FACETS_REPERTORY = TOMCAT_REPERTORY + webapps + FACETMAP_FACETS + FILESEP;
	String FACETMAP_CONTENT_REPERTORY = TOMCAT_REPERTORY + webapps + FACETMAP_CONTENT + FILESEP;
	
	//
	public String getParam(String ParamName) {
		return getGenerationParameters().get(ParamName);
	}
	
	@Override
	protected void clean(File fileToDeploy) throws Exception {}
	
	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		//CommonFiles
		ArrayList<File> commonDeployedFiles = new ArrayList<File>();
		String genpathcomon = fileToDeploy.getPath() + common;
		File cmisjs =  new File (genpathcomon + scriptcmis + cmis2xfml_filename);
		File cmis2xfml = new File(genpathcomon + facetmap + webinf + xsl + cmis2xfml_filename);
		File cmis2xfml_properties = new File(genpathcomon + facetmap + webinf + cmis2xfml_properties_filename);
		File facetmap_properties = new File(genpathcomon + facetmap + webinf + facetmap_configurationproperties_filename);
		commonDeployedFiles.add(cmisjs);
		commonDeployedFiles.add(cmis2xfml);
		commonDeployedFiles.add(cmis2xfml_properties);
		commonDeployedFiles.add(facetmap_properties);
		//FacetsFiles
		ArrayList<File> facetsDeployedFiles = new ArrayList<File>();
		String genpathfacets = fileToDeploy.getPath() + facets;
		File buildProperties = new File(genpathfacets + facetmap + webinf + buildProperties_filename);
		File basicFacets = new File(genpathfacets + facetmap + webinf + xsl + display + includes + basicFacets_filename);
		File rightnav = new File(genpathfacets + facetmap + webinf + xsl + display + rightnav_properties_filename);
		facetsDeployedFiles.add(buildProperties);
		facetsDeployedFiles.add(basicFacets);
		facetsDeployedFiles.add(rightnav);
		//Check
		ArrayList<File> allFiles = new ArrayList<File>();
		allFiles.addAll(commonDeployedFiles);
		allFiles.addAll(facetsDeployedFiles);
		checkfiles(allFiles);
		//Deploy
		deployFacetMapCommon(commonDeployedFiles);
		deployFacets(facetsDeployedFiles);
	}
	
	private void checkfiles(ArrayList<File> files) throws Exception {
		String list_of_missing_files = "";
		Boolean filemissing = Boolean.FALSE;
		for (File f : files) {
			if (!f.exists()){
				filemissing = Boolean.TRUE;
				list_of_missing_files += "\n"+f.getAbsolutePath();
			}
			if (filemissing)
				throw new Exception("Missing the following generated files:"+list_of_missing_files+"\n deployment aborted.");
		}
	}
	
	private void deployFacetMapCommon(ArrayList<File> commonDeployedFiles) throws Exception {
		// Getting Files in generation
		// Path where the files need to be copied
		String path_cmisjs = CMIS_REPERTORY + cmisjs_filename;
		String cmis2xfml_path_facets =  FACETMAP_FACETS_REPERTORY + webinf + xsl + cmis2xfml_filename;
		String cmis2xfml_path_content =  FACETMAP_CONTENT_REPERTORY + webinf + xsl +cmis2xfml_filename;
		String cmis2xfml_properties_path_facets = FACETMAP_CONTENT_REPERTORY + webinf + cmis2xfml_properties_filename;
		String cmis2xfml_properties_path_content =  FACETMAP_CONTENT_REPERTORY + webinf + cmis2xfml_properties_filename;
		String facetmap_configuration_properties_path_facets = FACETMAP_CONTENT_REPERTORY + webinf + facetmap_configurationproperties_filename;
		String facetmap_configuration_properties_path_content =  FACETMAP_CONTENT_REPERTORY + webinf + facetmap_configurationproperties_filename;
		// Copying
		FileHelper.copyFiles(commonDeployedFiles.get(0), new File(path_cmisjs), true);
		FileHelper.copyFiles(commonDeployedFiles.get(1), new File(cmis2xfml_path_facets), true);
		FileHelper.copyFiles(commonDeployedFiles.get(1), new File(cmis2xfml_path_content), true);
		FileHelper.copyFiles(commonDeployedFiles.get(2), new File(cmis2xfml_properties_path_facets), true);
		FileHelper.copyFiles(commonDeployedFiles.get(2), new File(cmis2xfml_properties_path_content), true);
		FileHelper.copyFiles(commonDeployedFiles.get(3), new File(facetmap_configuration_properties_path_facets), true);
		FileHelper.copyFiles(commonDeployedFiles.get(3), new File(facetmap_configuration_properties_path_content), true);
	}
	
	private void deployFacets(ArrayList<File> facetsDeployedFiles) throws Exception {
		// Path where the files need to be copied
		String buildProperties_path = FACETMAP_FACETS + webinf + buildProperties_filename;
		String basicFacets_path = FACETMAP_FACETS + webinf + xsl + display + includes +  basicFacets_filename;
		String presentation_path = FACETMAP_FACETS + webinf + xsl + display + rightnav_properties_filename;
		// Copying
		FileHelper.copyFiles(facetsDeployedFiles.get(0), new File(buildProperties_path), true);
		FileHelper.copyFiles(facetsDeployedFiles.get(1), new File(basicFacets_path), true);
		FileHelper.copyFiles(facetsDeployedFiles.get(2), new File(presentation_path), true);
	}
	
	@Override
	protected void postProcess(File fileToDeploy) throws Exception {}

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
