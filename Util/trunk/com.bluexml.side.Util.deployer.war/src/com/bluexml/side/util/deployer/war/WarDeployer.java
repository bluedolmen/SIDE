package com.bluexml.side.util.deployer.war;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.libs.FileExtensionFilter;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public abstract class WarDeployer extends Deployer {
	public static String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME"; //$NON-NLS-1$

	static final String webapps = "webapps"; //$NON-NLS-1$
	protected String webappName = null;
	protected static String warToPatchExt = "war"; //$NON-NLS-1$
	protected static String backupWarExt = "war.org"; //$NON-NLS-1$
	File backupWarFile = null;
	File warToPatchFile = null;
	File deployedWebbAppFolder = null;

	public String getWebappName() {
		return webappName;
	}

	public void setWebappName(String webappName) {
		this.webappName = webappName;
	}

	
	

	public File getBackupWarFile() {
		if (backupWarFile == null) {
			backupWarFile = new File(getTomcatHome() + File.separator + webapps + File.separator + webappName + "." +backupWarExt); //$NON-NLS-1$
		}
		return backupWarFile;
	}

	public File getWarToPatchFile() {
		if (warToPatchFile == null) {
			warToPatchFile = new File(getTomcatHome() + File.separator + webapps + File.separator + webappName + "." +warToPatchExt); //$NON-NLS-1$
		}
		return warToPatchFile;
	}

	public File getDeployedWebbAppFolder() {
		if (deployedWebbAppFolder == null) {
			deployedWebbAppFolder = new File(getTomcatHome() + File.separator + webapps + File.separator + webappName);
		}
		return deployedWebbAppFolder;
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// remove existing deployed alfresco webapp.
		if (getDeployedWebbAppFolder().exists()) {
			addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("WarDeployer.2")+getDeployedWebbAppFolder().getName(), null); //$NON-NLS-1$ //$NON-NLS-2$
			FileHelper.deleteFile(getDeployedWebbAppFolder());
		}
		// clean war file
		if (getBackupWarFile().exists()) {
			addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("WarDeployer.3")+getBackupWarFile().getName(), null); //$NON-NLS-1$ //$NON-NLS-2$
			// restore from backup		
			FileHelper.copyFiles(getBackupWarFile(), getWarToPatchFile(), true);
		}
	}

	public File initWarToPatch(File tomcatHome) throws Exception {
		if (!getBackupWarFile().exists()) {
			addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("WarDeployer.4")+getBackupWarFile().getName(), null); //$NON-NLS-1$ //$NON-NLS-2$
			// buid backup
			FileHelper.copyFiles(getWarToPatchFile(), getBackupWarFile(), true);
		}
		return getWarToPatchFile();
	}

	public String getTomcatHome() {
		return getGenerationParameters().get(CONFIGURATION_PARAMETER_CATALINA_HOME);
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		initWarToPatch(new File(getTomcatHome()));
		if (!fileToDeploy.exists()) {
			throw new Exception(Activator.Messages.getString("WarDeployer.5")+fileToDeploy); //$NON-NLS-1$
		}
	}

	protected void deployProcess(java.io.File fileToDeploy) throws Exception {
		boolean succes = true;
		// copy all files in the package into the WAR
		TrueZipHelper fh = new TrueZipHelper("zip"); //$NON-NLS-1$
		if (fileToDeploy.isDirectory()) {
			for (File f : fileToDeploy.listFiles(new FileExtensionFilter("zip"))) { //$NON-NLS-1$
				addInfoLog(Activator.Messages.getString("WarDeployer.6"), Activator.Messages.getString("WarDeployer.7")+f.getName(), null); //$NON-NLS-1$ //$NON-NLS-2$
				succes &= fh.copyFiles(f, getWarToPatchFile(), true);
			}
		} else {
			addInfoLog(Activator.Messages.getString("WarDeployer.6"), Activator.Messages.getString("WarDeployer.7")+fileToDeploy.getName(), null); //$NON-NLS-1$ //$NON-NLS-2$
			succes = fh.copyFiles(fileToDeploy, getWarToPatchFile(), true);
		}

		if (!succes) {
			addErrorLog(Activator.Messages.getString("WarDeployer.8"), Activator.Messages.getString("WarDeployer.9"), null); //$NON-NLS-1$ //$NON-NLS-2$
			throw new Exception(Activator.Messages.getString("WarDeployer.9")); //$NON-NLS-1$
		}
		if (logChanges()) {
			addInfoLog(Activator.Messages.getString("WarDeployer.6"), Activator.Messages.getString("DirectWebAppsDeployer."), null); //$NON-NLS-1$ //$NON-NLS-2$
			File warOrg = TrueZipHelper.getTzFile(getBackupWarFile());
			File finalwar = TrueZipHelper.getTzFile(getWarToPatchFile());
			StringWriter sr = new StringWriter();
			diffFolder(warOrg, finalwar, sr, FileHelper.COMPARE_ADDED + FileHelper.COMPARE_DELETED);
			addInfoLog(this.logChangesMsg, sr.toString(), null);
		}
	}

	public void diffFolder(File folder1, File folder2, Writer log, String filter) throws IOException {
		Map<String, List<String>> diff = FileHelper.diffFolder(folder1, folder2, filter);

		String header = Activator.Messages.getString("WarDeployer.11") + folder1.getAbsolutePath() + " --> " + folder2.getAbsolutePath() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		log.write(header);
		//addInfoLog(this.logChangesMsg, header, null);
		for (Map.Entry<String, List<String>> ent : diff.entrySet()) {
			for (String v : ent.getValue()) {
				String body = ent.getKey() + " file://" + v + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
				log.write(body);
			}
		}

	}

	public boolean check() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
