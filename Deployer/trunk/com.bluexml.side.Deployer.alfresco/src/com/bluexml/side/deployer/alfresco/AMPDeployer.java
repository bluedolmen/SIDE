package com.bluexml.side.deployer.alfresco;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.repo.module.tool.ModuleManagementTool;

import com.bluexml.side.util.deployer.war.WarDeployer;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

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
		this.logChanges = "com.bluexml.side.Application.deployer.alfresco.logChanges";
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

		if (logChanges()) {

			try {
				File warOrg = TrueZipHelper.getTzFile(getBackupWarFile());
				File finalwar = TrueZipHelper.getTzFile(getWarToPatchFile());
				StringWriter sr = new StringWriter();
				FileWriter fr = new FileWriter(new File("/Users/davidabad/Workspace2.0/test/diff.txt"));
				FileHelper.diffFolder(warOrg, finalwar, fr, FileHelper.COMPARE_ADDED + FileHelper.COMPARE_DELETED);
				addInfoLog(this.logChangesMsg, sr.toString(), null);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
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
