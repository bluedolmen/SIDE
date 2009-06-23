package com.bluexml.side.deployer.alfresco;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.repo.module.tool.ModuleManagementTool;

import com.bluexml.side.util.deployer.war.WarDeployer;

/**
 * The ModuleManagementTool is part of alfresco-mmt.jar tool
 * 
 * @author davidabad
 * 
 */
public class AMPDeployer extends WarDeployer {

	public AMPDeployer() {
		this.webappName = "alfresco";
		this.cleanKey = "com.bluexml.side.Application.deployer.alfresco.clean";
	}

	protected void deployProcess(File fileToDeploy) {
		// build command line
		String fileToDeployString = fileToDeploy.getAbsolutePath();
		String filetoPatchString = getWarToPatchFile().getAbsolutePath();
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

	/**
	 * This method check if the user have the license to use this deployer.
	 * 
	 * @return true if the deployer can be used.
	 */
	public boolean check() {
		return true;
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

}
