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
	static String webappName ="alfresco";
	static final String alfrescoWar = webappName+".war";
	static final String alfrescoWarOrg = webappName+".war.org";

	public static File getWarToPath(File tomcatHome,boolean doClean) throws Exception {
		File orgWar = new File(tomcatHome + File.separator + webapps + File.separator + alfrescoWarOrg);
		File alfWar = new File(tomcatHome + File.separator + webapps + File.separator + alfrescoWar);
		if (!orgWar.exists()) {
			// buid backup
			FileHelper.copyFiles(alfWar, orgWar, true);
		} else if(doClean) {
			// TODO : Clean MUST BE DONE AFTER ALL GENERATION PROCESS so is't a generator job
			// purge
			//FileHelper.deleteFile(alfWar);
			//FileHelper.copyFiles(orgWar, alfWar, true);
		}
		return alfWar;
	}

	public static void deploy(File ampPath, File tomcatHome,boolean doClean) throws Exception {
		boolean deleted = false;
		if (doClean) {
			// remove existing deployed alfresco webapp.
			File alfWebapp = new File(tomcatHome + File.separator + webapps + File.separator + webappName);
			deleted = FileHelper.deleteFile(alfWebapp);
		}
		if (!deleted) {
			System.out.println("Alfresco webapp not cleaned !");
		}
		File filetoPatch = getWarToPath(tomcatHome,doClean);
		String argInstall = "install " + ampPath + " " + filetoPatch.getAbsolutePath() + " -nobackup -force";
		String[] args = argInstall.split(" ");
		ModuleManagementTool.main(args);

		// String argList = "list " + ampPath;
		// String[] argsList = argList.split(" ");

		// ModuleManagementTool.main(argsList);

	}

}
