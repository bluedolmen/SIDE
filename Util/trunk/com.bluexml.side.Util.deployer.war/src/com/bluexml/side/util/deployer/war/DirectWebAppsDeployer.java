package com.bluexml.side.util.deployer.war;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.libs.FileExtensionFilter;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public abstract class DirectWebAppsDeployer extends WarDeployer {
	protected TrueZipHelper tzh = null;
	protected File wkdir = null;
	protected String packageExt = null;

	public File getWorkingDir() throws Exception {
		if (wkdir == null) {
			throw new Exception("Only available after preProcess");
		}
		return wkdir;
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		boolean result = true;
		// delete exploded webapps
		if (getDeployedWebbAppFolder().exists()) {
			FileHelper.deleteFile(getDeployedWebbAppFolder(), false);
		}
		// unzip .war or .org if exist
		if (getBackupWarFile().exists()) {
			TrueZipHelper tzh2 = new TrueZipHelper(backupWarExt);
			result = tzh2.copyFiles(getBackupWarFile(), getDeployedWebbAppFolder(), true);
		} else if (getWarToPatchFile().exists()) {
			TrueZipHelper tzh2 = new TrueZipHelper(warToPatchExt);
			result = tzh2.copyFiles(getWarToPatchFile(), getDeployedWebbAppFolder(), true);
		} else {
			System.out.println("can't clean because " + webappName + "." + backupWarExt + " not found");

			throw new Exception("can't clean because " + webappName + "." + warToPatchExt + " not found");
		}
		if (!result) {
			throw new Exception("Unable to copy files");
		}

	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		if (!getDeployedWebbAppFolder().exists()) {
			this.clean(fileToDeploy);
		}
		if (fileToDeploy.isDirectory()) {
			for (File f : fileToDeploy.listFiles(new FileExtensionFilter(packageExt))) {
				File explodedPackage = new File(getWorkingDir(), f.getName().replaceAll("\\." + packageExt, ""));

				explodedPackage.mkdirs();
				// unzip files in tmp
				tzh.copyFiles(f, explodedPackage, true);

				Map<String, File> map = createMapper(explodedPackage);
				List<File> fileList = FileHelper.listAll(explodedPackage);

				dispatchFiles(fileList, map);
			}
		}
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		if (fileToDeploy.isDirectory()) {
			this.wkdir = new File(fileToDeploy.getParent(), this.getClass().getName());

		} else {
			throw new Exception("fileToDeploy must be a directory !");
		}
		if (getWorkingDir().exists()) {
			FileHelper.deleteFile(getWorkingDir(), false);
		}
		getWorkingDir().mkdirs();
	}

	/**
	 * Use to map package filePath to corresponding exploded webapp directory
	 * Exemple of createMapper :<br/>
	 * File packageFolder = getDeployedWebbAppFolder();<br/>
	 * Map<String, File> mapper = new HashMap<String, File>();<br/>
	 * addToMap(mapper, "/config/", packageFolder, "/WEB-INF/classes/");<br/>
	 * return mapper;<br/>
	 * 
	 * @return
	 */
	public abstract Map<String, File> createMapper(File packageDirectory);

	protected void addToMap(Map<String, File> map, String key, File ampRoot, String target) {
		map.put(key.replace("/", File.separator), createTargetFolders(ampRoot, target.replace("/", File.separator)));
	}

	protected File createTargetFolders(File ampRoot, String p) {
		File dir = new File(ampRoot, p);
		dir.mkdirs();
		return dir;
	}

	protected void dispatchFiles(List<File> files, Map<String, File> mapper) throws Exception {
		for (File f : files) {
			for (Map.Entry<String, File> ent : mapper.entrySet()) {
				if (f.getAbsolutePath().indexOf(ent.getKey()) != -1) {
					String path = f.getAbsolutePath();
					String pathIn = ent.getValue().getAbsolutePath() + File.separator + path.substring(path.indexOf(ent.getKey()) + ent.getKey().length());
					File dest = new File(pathIn);
					dest.getParentFile().mkdirs();
					// put to this dir
					FileHelper.copyFiles(f, dest, true);
				}
			}
		}
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

}
