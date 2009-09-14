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

	public static String CONFIGURATION_TOMCAT_INSTALLATION = "CATALINA_HOME"; //$NON-NLS-1$
	static final String webapps = "webapps"; //$NON-NLS-1$

	public String getParam(String ParamName) {
		return getGenerationParameters().get(ParamName);
	}

	// Emplacements des fichiers
	public String getLocationInTomcat() throws Exception {
		String path = getParam(CONFIGURATION_TOMCAT_INSTALLATION) + File.separator + webapps + File.separator + "birt" + File.separator; //$NON-NLS-1$
		// System.out.println("Tomcat path: " + path);
		if (!new File(path).exists()) {
			Exception e = new Exception(Activator.Messages.getString("ReportDeployer.3")); //$NON-NLS-1$
			monitor.addErrorTextAndLog(Activator.Messages.getString("ReportDeployer.3"), e, null); //$NON-NLS-1$
			throw e;
		}

		return path;
	}

	public String getLocationInGeneration() {
		// System.out.println("Path location" +
		// IFileHelper.getSystemFolderPath(getTargetPath() + File.separator +
		// getTechVersion()) + File.separator +
		// "Content_type_report.rptdesign");
		return IFileHelper.getSystemFolderPath(getTargetPath() + File.separator + getTechVersion()) + File.separator + "Content_type_report.rptdesign"; //$NON-NLS-1$
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		// Getting Files
		File birtReport = new File(fileToDeploy + File.separator + "Content_type_report.rptdesign"); //$NON-NLS-1$
		// Test if missing file
		Boolean filemissing = Boolean.FALSE;
		String list_of_missing_files = ""; //$NON-NLS-1$
		if (!birtReport.exists()) {
			filemissing = Boolean.TRUE;
			list_of_missing_files += "\n" + birtReport.getAbsolutePath(); //$NON-NLS-1$
		}
		if (filemissing) {
			Exception e = new Exception(Activator.Messages.getString("ReportDeployer.9", list_of_missing_files)); //$NON-NLS-1$ //$NON-NLS-2$
			monitor.addErrorTextAndLog(Activator.Messages.getString("ReportDeployer.11"), e, ""); //$NON-NLS-1$ //$NON-NLS-2$
			throw e;
		}
		// Path where the files need to be copied
		String path = getLocationInTomcat() + "Content_type_report.rptdesign"; //$NON-NLS-1$

		// Deleting
		if (new File(getLocationInTomcat() + "Content_type_report.rptdesign.bak").exists()) //$NON-NLS-1$
			FileHelper.deleteFile(new File(getLocationInTomcat() + "Content_type_report.rptdesign.bak")); //$NON-NLS-1$

		// Renaming
		if (new File(getLocationInTomcat() + "Content_type_report.rptdesign").exists()) //$NON-NLS-1$
			new File(getLocationInTomcat() + "Content_type_report.rptdesign").renameTo(new File(getLocationInTomcat() + "Content_type_report.rptdesign.bak")); //$NON-NLS-1$ //$NON-NLS-2$

		// Copying
		FileHelper.copyFiles(birtReport, new File(path), true);
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
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
