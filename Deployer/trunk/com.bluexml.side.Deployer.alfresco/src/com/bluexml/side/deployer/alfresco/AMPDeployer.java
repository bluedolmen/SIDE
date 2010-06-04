package com.bluexml.side.deployer.alfresco;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.Ostermiller.util.ExecHelper;
import com.bluexml.side.util.deployer.war.Activator;
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
		super("com.bluexml.side.deployer.alfresco.clean", "com.bluexml.side.deployer.alfresco.logChanges", "alfresco", "deployer.webappName.alfresco");
	}

	public String getMMtPath() {
		if (getGenerationParameters().get(CONFIGURATION_PARAMETER_MMT_PATH) != null) {
			return getGenerationParameters().get(CONFIGURATION_PARAMETER_MMT_PATH).trim();
		}
		return "";
	}

	protected void deployProcess(File fileToDeploy) throws Exception {
		if (getMMtPath() != null && new File(getMMtPath()).exists()) {
			if (fileToDeploy.exists()) {
				_deployProcess(fileToDeploy);
			} else {
				monitor.addWarningTextAndLog(Activator.Messages.getString("WarDeployer.5"), "");
			}
			// FIXME : use a unique call to alfresco-mmt.jar to avoid bug on
			// FileHelper.diffFolder method with File is not java.io.File but
			// trueZip subclass
			// if (fileToDeploy.isFile() &&
			// fileToDeploy.getName().endsWith(".amp"))
			// _deployProcess(fileToDeploy);
			// else if (fileToDeploy.isDirectory()) {
			// for (File f : fileToDeploy.listFiles()) {
			// deployProcess(f);
			// }
			// }
		} else {
			throw new Exception(Activator.Messages.getString("AMPDeployer.14", getMMtPath())); //$NON-NLS-1$
		}
	}

	private void _deployProcess(File fileToDeploy) throws IOException {
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
			// test made on standard output because alfresco-mmt do not use
			// error output stream
			if (status.getOutput().length() > 0) {
				throw new Exception(Activator.Messages.getString("AMPDeployer.10") + status.getError() + "\n" + status.getOutput()); //$NON-NLS-1$ //$NON-NLS-2$
			} else if (logChanges()) {
				de.schlichtherle.io.File.update();
				File warOrg = TrueZipHelper.getTzFile(getBackupWarFile());
				File finalwar = TrueZipHelper.getTzFile(getWarToPatchFile());
				StringWriter sr = new StringWriter();
				de.schlichtherle.io.File.update((de.schlichtherle.io.File) finalwar);
				FileHelper.diffFolder(warOrg, finalwar, sr, FileHelper.COMPARE_ADDED + FileHelper.COMPARE_DELETED);
				monitor.getLog().addInfoLog(this.logChangesMsg, sr.toString(), ""); //$NON-NLS-1$
			}
		} catch (Exception e) {
			monitor.addErrorTextAndLog(Activator.Messages.getString("AMPDeployer.13"), e, null); //$NON-NLS-1$
			e.printStackTrace();
		}
	}	

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

}
