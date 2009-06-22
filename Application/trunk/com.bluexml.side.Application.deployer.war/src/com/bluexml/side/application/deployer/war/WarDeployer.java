package com.bluexml.side.application.deployer.war;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.bluexml.side.application.deployer.Deployer;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public abstract class WarDeployer extends Deployer {
	public static String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME";
	static final String webapps = "webapps";
	protected String webappName = null;
	protected String warToPatchExt = ".war";
	protected String backupWarExt = ".war.org";
	File backupWarFile = null;
	File warToPatchFile = null;
	File deployedWebbAppFolder = null;

	
	public void initialize(String webappName, String cleanKey,Map<String, String> configurationParameters, Map<String, String> generationParameters, List<String> options) {
		this.initialize(configurationParameters, generationParameters, options);
		this.webappName = webappName;
		this.cleanKey = cleanKey;
	}

	public File getBackupWarFile() {
		if (backupWarFile == null) {
			backupWarFile = new File(getTomcatHome() + File.separator + webapps + File.separator + webappName + backupWarExt);
		}
		return backupWarFile;
	}

	public File getWarToPatchFile() {
		if (warToPatchFile == null) {
			warToPatchFile = new File(getTomcatHome() + File.separator + webapps + File.separator + webappName + warToPatchExt);
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
	protected void clean(File fileToDeploy) {
		// remove existing deployed alfresco webapp.
		FileHelper.deleteFile(getDeployedWebbAppFolder());
		// clean war file
		if (getBackupWarFile().exists()) {
			// restore from backup
			try {
				FileHelper.copyFiles(getBackupWarFile(), getWarToPatchFile(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public File initWarToPatch(File tomcatHome) throws Exception {
		if (!getBackupWarFile().exists()) {
			// buid backup
			FileHelper.copyFiles(getWarToPatchFile(), getBackupWarFile(), true);
		}
		return getWarToPatchFile();
	}

	public String getTomcatHome() {
		return getGenerationParameters().get(CONFIGURATION_PARAMETER_CATALINA_HOME);
	}

	@Override
	protected final void preProcess(File fileToDeploy) throws Exception {
		initWarToPatch(new File(getTomcatHome()));
		if (!fileToDeploy.exists()) {
			throw new Exception("No files to deploy !");
		}
	}
	
	protected void deployProcess(java.io.File fileToDeploy) throws Exception {
		boolean succes=true;
		// copy all files in the package into the WAR
		TrueZipHelper fh = new TrueZipHelper("zip");
		if (fileToDeploy.isDirectory()) {
			for (File f: fileToDeploy.listFiles(new FileExtFilter("zip"))) {
				succes &= fh.copyFiles(f, getWarToPatchFile(), true);
			}
		} else {
			succes = fh.copyFiles(fileToDeploy, getWarToPatchFile(), true);
		}
		
		if (!succes) {
			throw new Exception("Error during Updating War");
		}
	}
	
	public boolean check() {
		// TODO Auto-generated method stub
		return true;
	}
	class FileExtFilter implements FileFilter {
		String exts[];
		FileExtFilter(String exts) {
			this.exts = exts.split("\\|");
		}
		public boolean accept(File file) {
			boolean ok = true;
			try {
				String ext = FileHelper.getFileExt(file);
				for (String ext_ : exts) {
					ok &= ext_.equals(ext);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ok;
		}
	}

}
