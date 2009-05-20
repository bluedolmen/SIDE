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
import java.util.List;

import com.bluexml.side.application.deployer.Deployer;
import com.bluexml.side.util.libs.FileHelper;



/**
 * 
 * @author <a href="mailto:pbertrand@bluexml.com"> Pierre BERTRAND </a>
 * 
 */
public class ClassFacetMapDeployer extends Deployer {
	public static String CONFIGURATION_PARAMETER_FACETMAP_HOME = "FACETMAP_FOLDER";
	static final String webapps = "webapps";
	static final String webappName = "facetmap";
	static final String cmis2xfml_filename = "cmis2xfml.xsl";
	
	public String getFacetmapHome() {
		return getGenerationParameters().get(CONFIGURATION_PARAMETER_FACETMAP_HOME);
	}
	

	public String getXslLocation() {
			return getFacetmapHome() + File.separator + "WEB-INF" + File.separator + "xsl" + File.separator;
	}

	
	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// remove existing deployed alfresco webapp.
		FileHelper.deleteFile(new File(getXslLocation()+cmis2xfml_filename));
	}
	
	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		if (!fileToDeploy.exists()) {
			throw new Exception("No files to deploy !");
		}
		// build command line
		String fileToDeployString = fileToDeploy.getAbsolutePath();
		List<String> argss = new ArrayList<String>();
		argss.add("install");
		argss.add(fileToDeployString);
		argss.add(getFacetmapHome());
		
		String[] args = new String[argss.size()];
		args = argss.toArray(args);

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
