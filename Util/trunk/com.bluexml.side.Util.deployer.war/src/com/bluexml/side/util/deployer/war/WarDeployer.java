package com.bluexml.side.util.deployer.war;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.deployer.Deployer;
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

	public String getWebappName() {
		return webappName;
	}

	public void setWebappName(String webappName) {
		this.webappName = webappName;
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
			throw new Exception("No files to deploy !"+fileToDeploy);
		}
	}

	protected void deployProcess(java.io.File fileToDeploy) throws Exception {
		boolean succes = true;
		// copy all files in the package into the WAR
		TrueZipHelper fh = new TrueZipHelper("zip");
		if (fileToDeploy.isDirectory()) {
			for (File f : fileToDeploy.listFiles(new FileExtFilter("zip"))) {
				succes &= fh.copyFiles(f, getWarToPatchFile(), true);
			}
		} else {
			succes = fh.copyFiles(fileToDeploy, getWarToPatchFile(), true);
		}

		if (!succes) {
			addErrorLog("War deployer", "Error during Updating War", null);
			throw new Exception("Error during Updating War");
		}
		if (logChanges()) {
			File warOrg = TrueZipHelper.getTzFile(getBackupWarFile());
			File finalwar = TrueZipHelper.getTzFile(getWarToPatchFile());
			StringWriter sr = new StringWriter();
			diffFolder(warOrg, finalwar, sr, FileHelper.COMPARE_ADDED + FileHelper.COMPARE_DELETED);
			addInfoLog(this.logChangesMsg, sr.toString(), null);
		}
	}

	public void diffFolder(File folder1, File folder2, Writer log, String filter) throws IOException {
		Map<String, List<String>> diff = FileHelper.diffFolder(folder1, folder2, filter);

		String header = "DIFF " + folder1.getAbsolutePath() + " --> " + folder2.getAbsolutePath() + "\n";
		log.write(header);
		//addInfoLog(this.logChangesMsg, header, null);
		for (Map.Entry<String, List<String>> ent : diff.entrySet()) {
			for (String v : ent.getValue()) {
				String body = ent.getKey() + " file://" + v + "\n";
				log.write(body);
				//addInfoLog(this.logChangesMsg, body, null);
			}
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
