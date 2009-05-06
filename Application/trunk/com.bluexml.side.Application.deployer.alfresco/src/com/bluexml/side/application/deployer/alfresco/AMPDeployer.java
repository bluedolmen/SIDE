package com.bluexml.side.application.deployer.alfresco;

import java.io.File;

import org.alfresco.repo.module.tool.ModuleManagementTool;

import com.bluexml.side.application.deployer.Deployer;
import com.bluexml.side.application.security.SecurityHelper;
import com.bluexml.side.settings.SidePreferences;
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
	static String webappName = "alfresco";
	static final String alfrescoWar = webappName + ".war";
	static final String alfrescoWarOrg = webappName + ".war.org";
	//TODO mettre le vrai code
	public static String DEPLOYER_CODE = "CODE_GED_G_W_ALFRESCO_30L";

	

	@Override
	protected void clean(File fileToDeploy) {
		// remove existing deployed alfresco webapp.
		File alfWebapp = new File(getTomcatHome() + File.separator + webapps + File.separator + webappName);
		FileHelper.deleteFile(alfWebapp);
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		File filetoPatch = getWarToPath(new File(getTomcatHome()));
		if (!fileToDeploy.exists()) {
			throw new Exception("No files to deploy !");
		}
		String argInstall = "install " + fileToDeploy.getAbsolutePath() + " " + filetoPatch.getAbsolutePath() + " -nobackup -force";
		if (fileToDeploy.isDirectory()) {
			argInstall +=" -directory";
		}
		String[] args = argInstall.split(" ");
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


	public static File getWarToPath(File tomcatHome) throws Exception {
		File orgWar = new File(tomcatHome + File.separator + webapps + File.separator + alfrescoWarOrg);
		File alfWar = new File(tomcatHome + File.separator + webapps + File.separator + alfrescoWar);
		if (!orgWar.exists()) {
			// buid backup
			FileHelper.copyFiles(alfWar, orgWar, true);
		}
		return alfWar;
	}
	
	/**
	 * This method check if the user have the license to use this deployer.
	 * 
	 * @return true if the deployer can be used.
	 */
	public boolean check(){
		return SecurityHelper.check(DEPLOYER_CODE,SidePreferences.getKey());
	}

}
