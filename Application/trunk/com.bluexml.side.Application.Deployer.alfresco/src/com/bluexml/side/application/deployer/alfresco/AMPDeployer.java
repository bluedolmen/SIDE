package com.bluexml.side.application.deployer.alfresco;

import java.io.File;

import org.alfresco.repo.module.tool.ModuleManagementTool;

import com.bluexml.side.util.libs.FileHelper;

/**
 * The ModuleManagementTool is part of alfresco-mmt.jar tool
 * 
 * @author davidabad
 * 
 */
public class AMPDeployer {
	static final String webapps = "webapps";
	static final String alfrescoWar = "alfresco.war";
	static final String alfrescoWarOrg = "alfresco.war.org";

	public static File getWarToPath(File tomcatHome) throws Exception {
		File orgWar = new File(tomcatHome + File.separator + webapps + File.separator + alfrescoWarOrg);
		File alfWar = new File(tomcatHome + File.separator + webapps + File.separator + alfrescoWar);
		if (!orgWar.exists()) {
			// buid backup
			FileHelper.copyFiles(alfWar, orgWar, true);
		} else {
			// purge
			//FileHelper.deleteFile(alfWar);
			//FileHelper.copyFiles(orgWar, alfWar, true);
		}
		return alfWar;
	}

	public static void deploy(File ampPath, File tomcatHome) throws Exception {
		File filetoPatch = getWarToPath(tomcatHome);
		String argInstall = "install " + ampPath + " " + filetoPatch.getAbsolutePath() + " -nobackup -force";
		String[] args = argInstall.split(" ");

		ModuleManagementTool.main(args);

		// String argList = "list " + ampPath;
		// String[] argsList = argList.split(" ");

		// ModuleManagementTool.main(argsList);

	}

}
