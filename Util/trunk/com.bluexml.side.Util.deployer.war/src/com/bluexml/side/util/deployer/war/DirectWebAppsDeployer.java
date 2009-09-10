package com.bluexml.side.util.deployer.war;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.componentmonitor.MonitorWriter;
import com.bluexml.side.util.libs.FileExtensionFilter;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public abstract class DirectWebAppsDeployer extends WarDeployer {
	protected TrueZipHelper tzh = null;
	protected File wkdir = null;
	protected String packageExt = null;

	public File getWorkingDir() throws Exception {
		if (wkdir == null) {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.1")); //$NON-NLS-1$
		}
		return wkdir;
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		monitor.beginTask(Activator.Messages.getString("DirectWebAppsDeployer.0")); //$NON-NLS-1$
		boolean result = true;
		// delete exploded webapps
		if (getDeployedWebbAppFolder().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("WarDeployer.2") + getDeployedWebbAppFolder().getName(), ""); //$NON-NLS-1$ //$NON-NLS-2$
			FileHelper.deleteFile(getDeployedWebbAppFolder(), false);
		}

		// unzip .war or .org if exist
		if (getBackupWarFile().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("DirectWebAppsDeployer.4") + getBackupWarFile().getName(), ""); //$NON-NLS-1$ //$NON-NLS-2$
			TrueZipHelper tzh2 = new TrueZipHelper(backupWarExt);
			result = tzh2.copyFiles(getBackupWarFile(), getDeployedWebbAppFolder(), true);
		} else if (getWarToPatchFile().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.5"), Activator.Messages.getString("DirectWebAppsDeployer.6") + getWarToPatchFile().getName(), ""); //$NON-NLS-1$ //$NON-NLS-2$
			TrueZipHelper tzh2 = new TrueZipHelper(warToPatchExt);
			result = tzh2.copyFiles(getWarToPatchFile(), getDeployedWebbAppFolder(), true);
		} else {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.7") + webappName + "." + warToPatchExt + Activator.Messages.getString("DirectWebAppsDeployer.9")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		if (!result) {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.10")); //$NON-NLS-1$
		}
		monitor.taskDone(Activator.Messages.getString("DirectWebAppsDeployer.2")); //$NON-NLS-1$
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		if (!getDeployedWebbAppFolder().exists()) {
			this.clean(fileToDeploy);
		}
		if (fileToDeploy.isDirectory()) {
			for (File f : fileToDeploy.listFiles(new FileExtensionFilter(packageExt))) {
				monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.6"), Activator.Messages.getString("WarDeployer.7") + f.getName(), ""); //$NON-NLS-1$ //$NON-NLS-2$
				File explodedPackage = new File(getWorkingDir(), f.getName().replaceAll("\\." + packageExt, "")); //$NON-NLS-1$ //$NON-NLS-2$

				explodedPackage.mkdirs();
				// unzip files in tmp
				tzh.copyFiles(f, explodedPackage, true);

				Map<String, File> map = createMapper(explodedPackage);
				List<File> fileList = FileHelper.listAll(explodedPackage);

				dispatchFiles(fileList, map);
			}
		}
		if (logChanges()) {

		}
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		if (fileToDeploy.isDirectory()) {
			this.wkdir = new File(fileToDeploy.getParent(), this.getClass().getName());

		} else {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.15")); //$NON-NLS-1$
		}
		if (getWorkingDir().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("DirectWebAppsDeployer.5"), Activator.Messages.getString("WarDeployer.17") + getWorkingDir().getName(), ""); //$NON-NLS-1$ //$NON-NLS-2$
			FileHelper.deleteFile(getWorkingDir(), false);
		}
		monitor.getLog().addInfoLog(Activator.Messages.getString("DirectWebAppsDeployer.5"), Activator.Messages.getString("WarDeployer.19") + getWorkingDir().getName(), ""); //$NON-NLS-1$ //$NON-NLS-2$
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
		map.put(key.replace("/", File.separator), createTargetFolders(ampRoot, target.replace("/", File.separator))); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected File createTargetFolders(File ampRoot, String p) {
		File dir = new File(ampRoot, p);
		dir.mkdirs();
		return dir;
	}

	protected void dispatchFiles(List<File> files, Map<String, File> mapper) throws Exception {
		MonitorWriter mw = new MonitorWriter(monitor,Activator.Messages.getString("DirectWebAppsDeployer.3"),""); //$NON-NLS-1$ //$NON-NLS-2$
		for (File f : files) {
			for (Map.Entry<String, File> ent : mapper.entrySet()) {
				if (f.getAbsolutePath().indexOf(ent.getKey()) != -1) {
					String path = f.getAbsolutePath();
					String pathIn = ent.getValue().getAbsolutePath() + File.separator + path.substring(path.indexOf(ent.getKey()) + ent.getKey().length());
					File dest = new File(pathIn);
					dest.getParentFile().mkdirs();
					// put to this dir
					FileHelper.copyFiles(f, dest, true, mw);
				}
			}
		}
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

}
