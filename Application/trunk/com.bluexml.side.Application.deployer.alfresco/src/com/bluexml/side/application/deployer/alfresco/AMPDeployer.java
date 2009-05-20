package com.bluexml.side.application.deployer.alfresco;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.repo.module.tool.ModuleManagementTool;

import com.bluexml.side.application.deployer.Deployer;
import com.bluexml.side.util.libs.FileHelper;

/**
 * The ModuleManagementTool is part of alfresco-mmt.jar tool
 * 
 * @author davidabad
 * 
 */
public class AMPDeployer extends Deployer {
	public static String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME";
	static final String webapps = "webapps";
	static final String webappName = "alfresco";
	static final String alfrescoWar = webappName + ".war";
	static final String alfrescoWarOrg = webappName + ".war.org";
	File orgWar = null;
	File alfWar = null;
	File alfWebapp = null;

	public File getOrgWar() {
		if (orgWar == null) {
			orgWar = new File(getTomcatHome() + File.separator + webapps + File.separator + alfrescoWarOrg);
		}
		return orgWar;
	}

	public File getAlfWar() {
		if (alfWar == null) {
			alfWar = new File(getTomcatHome() + File.separator + webapps + File.separator + alfrescoWar);
		}
		return alfWar;
	}

	public File getAlfWebapp() {
		if (alfWebapp == null) {
			alfWebapp = new File(getTomcatHome() + File.separator + webapps + File.separator + webappName);
		}
		return alfWebapp;
	}

	@Override
	protected void clean(File fileToDeploy) {
		// remove existing deployed alfresco webapp.
		FileHelper.deleteFile(getAlfWebapp());
		// clean war file
		if (getOrgWar().exists()) {
			// restore from backup
			try {
				FileHelper.copyFiles(getOrgWar(), getAlfWar(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		File filetoPatch = getWarToPath(new File(getTomcatHome()));
		if (!fileToDeploy.exists()) {
			throw new Exception("No files to deploy !");
		}
		// build command line
		String fileToDeployString = fileToDeploy.getAbsolutePath();
		String filetoPatchString = filetoPatch.getAbsolutePath();
		List<String> argss = new ArrayList<String>();
		argss.add("install");
		argss.add(fileToDeployString);
		argss.add(filetoPatchString);
		argss.add("-nobackup");
		argss.add("-force");
		if (fileToDeploy.isDirectory()) {
			argss.add("-directory");
		}

		String[] args = new String[argss.size()];
		args = argss.toArray(args);

		ModuleManagementTool.main(args);
	}

	@Override
	protected void postProcess(File fileToDeploy) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void preProcess(File fileToDeploy) {
		// TODO Auto-generated method stub
	}

	public String getTomcatHome() {
		return getGenerationParameters().get(CONFIGURATION_PARAMETER_CATALINA_HOME);
	}

	public File getWarToPath(File tomcatHome) throws Exception {
		if (!getOrgWar().exists()) {
			// buid backup
			FileHelper.copyFiles(getAlfWar(), getOrgWar(), true);
		}
		return getAlfWar();
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
