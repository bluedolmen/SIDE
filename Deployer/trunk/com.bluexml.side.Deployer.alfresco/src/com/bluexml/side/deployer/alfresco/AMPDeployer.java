package com.bluexml.side.deployer.alfresco;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.Ostermiller.util.ExecHelper;
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

	protected String CONFIGURATION_PARAMETER_MMT_PATH = "com.bluexml.side.deployer.alfresco.mmtPath";

	public AMPDeployer() {
		this.webappName = "alfresco";
		this.cleanKey = "com.bluexml.side.deployer.alfresco.clean";
		this.logChanges = "com.bluexml.side.deployer.alfresco.logChanges";
	}

	public String getMMtPath() {
		return getGenerationParameters().get(CONFIGURATION_PARAMETER_MMT_PATH);
	}

	protected void deployProcess(File fileToDeploy) throws Exception {
		if (getMMtPath() != null && new File(getMMtPath()).exists()) {
			// build command line
			String fileToDeployString = fileToDeploy.getAbsolutePath();
			String filetoPatchString = getWarToPatchFile().getAbsolutePath();
			List<String> argss = new ArrayList<String>();
			// argss.add("pwd");

			argss.add("java");
			argss.add("-jar");
			argss.add(getMMtPath());
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
			ExecHelper status = ExecHelper.exec(args);
			try {
				if (status.getOutput().length() > 0) {
					throw new Exception("alfresco-mmt.jar exit with errors :\n" + status.getError() + "\n" + status.getOutput());
				} else if (logChanges()) {
					File warOrg = TrueZipHelper.getTzFile(getBackupWarFile());
					File finalwar = TrueZipHelper.getTzFile(getWarToPatchFile());
					StringWriter sr = new StringWriter();
					FileHelper.diffFolder(warOrg, finalwar, sr, FileHelper.COMPARE_ADDED + FileHelper.COMPARE_DELETED);
					addInfoLog(this.logChangesMsg, sr.toString(), null);
				}
			} catch (Exception e) {
				addErrorLog("AMP deployer Error", e.getStackTrace(), null);
				e.printStackTrace();
			}
		} else {
			throw new Exception("deployment tool not found please check the alfresco-mmt.jar file path !");
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
