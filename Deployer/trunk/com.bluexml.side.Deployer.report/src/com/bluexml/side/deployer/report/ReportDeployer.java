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
package com.bluexml.side.deployer.report;

import java.io.File;

import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;

public class ReportDeployer extends Deployer {

	public static String CONFIGURATION_TOMCAT_INSTALLATION = "CATALINA_HOME";
	static final String webapps = "webapps";

	public String getParam(String ParamName) {
		return getGenerationParameters().get(ParamName);
	}

	// Emplacements des fichiers
	public String getLocationInTomcat() throws Exception{
		String path = getParam(CONFIGURATION_TOMCAT_INSTALLATION) + File.separator + webapps + File.separator + "birt" + File.separator;
		System.out.println("Tomcat path: "+path);
		if(!new File(path).exists()){
			Exception e = new Exception("Birt Webapp not deployed");
		monitor.getLog().addErrorLog("Birt Webapp not deployed", e.getStackTrace(), "");
			throw e;
		}

		return path;
	}

	public String getLocationInGeneration() {
		System.out.println("Path location"+IFileHelper.getSystemFolderPath(getTargetPath()+File.separator+getTechVersion())+File.separator + "Content_type_report.rptdesign");
		return IFileHelper.getSystemFolderPath(getTargetPath()+File.separator+getTechVersion())+File.separator + "Content_type_report.rptdesign";
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		// Getting Files
		File birtReport =  new File (fileToDeploy +File.separator + "Content_type_report.rptdesign" );
		//Test if missing file
		Boolean filemissing = Boolean.FALSE;
		String list_of_missing_files = "";
		if (!birtReport.exists()){
			filemissing = Boolean.TRUE;
			list_of_missing_files += "\n"+birtReport.getAbsolutePath();
		}
		if (filemissing){
			Exception e = new Exception("Missing the following generated files:"+list_of_missing_files+"\n deployment aborted.");
		monitor.getLog().addErrorLog("Missing files", e.getStackTrace(), "");
			throw e;
		}
		// Path where the files need to be copied
		String path = getLocationInTomcat()+ "Content_type_report.rptdesign";

		//Deleting
		if(new File(getLocationInTomcat() + "Content_type_report.rptdesign.bak").exists())
			FileHelper.deleteFile(new File(getLocationInTomcat() + "Content_type_report.rptdesign.bak"));

		//Renaming
		if(new File(getLocationInTomcat() + "Content_type_report.rptdesign").exists())
			new File(getLocationInTomcat() + "Content_type_report.rptdesign").renameTo(new File(getLocationInTomcat() + "Content_type_report.rptdesign.bak"));

		// Copying
		FileHelper.copyFiles(birtReport, new File(path), true);
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
