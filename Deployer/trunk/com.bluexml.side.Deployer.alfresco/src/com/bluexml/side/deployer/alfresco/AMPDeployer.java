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

	protected String CONFIGURATION_PARAMETER_MMT_PATH = "com.bluexml.side.deployer.alfresco.mmtPath"; //$NON-NLS-1$

	public AMPDeployer() {
		this.webappName = "alfresco"; //$NON-NLS-1$
		this.cleanKey = "com.bluexml.side.deployer.alfresco.clean"; //$NON-NLS-1$
		this.logChanges = "com.bluexml.side.deployer.alfresco.logChanges"; //$NON-NLS-1$
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

			argss.add("java"); //$NON-NLS-1$
			argss.add("-jar"); //$NON-NLS-1$
			argss.add(getMMtPath());
			argss.add("install"); //$NON-NLS-1$
			argss.add(fileToDeployString);
			argss.add(filetoPatchString);
			argss.add("-nobackup"); //$NON-NLS-1$
			argss.add("-force"); //$NON-NLS-1$
			if (fileToDeploy.isDirectory()) {
				argss.add("-directory"); //$NON-NLS-1$
			}
			String[] args = new String[argss.size()];
			args = argss.toArray(args);
			ExecHelper status = ExecHelper.exec(args);
			try {
				if (status.getOutput().length() > 0) {
					throw new Exception(Activator.Messages.getString("AMPDeployer.10") + status.getError() + "\n" + status.getOutput()); //$NON-NLS-1$ //$NON-NLS-2$
				} else if (logChanges()) {
					File warOrg = TrueZipHelper.getTzFile(getBackupWarFile());
					File finalwar = TrueZipHelper.getTzFile(getWarToPatchFile());
					StringWriter sr = new StringWriter();
					FileHelper.diffFolder(warOrg, finalwar, sr, FileHelper.COMPARE_ADDED + FileHelper.COMPARE_DELETED);
					monitor.getLog().addInfoLog(this.logChangesMsg, sr.toString(), ""); //$NON-NLS-1$
				}
			} catch (Exception e) {
				monitor.addErrorTextAndLog(Activator.Messages.getString("AMPDeployer.13"), e, null); //$NON-NLS-1$
				e.printStackTrace();
			}
		} else {
			throw new Exception(Activator.Messages.getString("AMPDeployer.14")); //$NON-NLS-1$
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
