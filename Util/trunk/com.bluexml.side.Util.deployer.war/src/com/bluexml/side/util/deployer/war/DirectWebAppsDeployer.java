package com.bluexml.side.util.deployer.war;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.bluexml.side.util.componentmonitor.MonitorWriter;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.zip.ZipManager;

public abstract class DirectWebAppsDeployer extends WarDeployer {
	boolean cleanned = false;
	protected List<String> deployedFiles = new ArrayList<String>();

	public DirectWebAppsDeployer(String cleanKey, String webappName, String webappKeyName, String packageExt) {
		super(cleanKey, null, webappName, webappKeyName);
		this.packageExt = packageExt;
		//		tzh = new TrueZipHelper(packageExt);
	}

	//	protected TrueZipHelper tzh = null;
	protected File wkdir = null;
	protected String packageExt = null;
	protected boolean incremental = true;
	protected boolean webappReloading = false;

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
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("WarDeployer.2", getDeployedWebbAppFolder().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			FileHelper.deleteFile(getDeployedWebbAppFolder(), false);
		}

		// unzip .war or .org if exist
		if (getBackupWarFile().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("DirectWebAppsDeployer.4", getWarToPatchFile().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$

			ZipManager.unzip(getBackupWarFile(), getDeployedWebbAppFolder(), true, true);
			//			TrueZipHelper tzh2 = new TrueZipHelper(backupWarExt);
			//			result = tzh2.copyFiles(getBackupWarFile(), getDeployedWebbAppFolder(), true);
		} else if (getWarToPatchFile().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.5"), Activator.Messages.getString("DirectWebAppsDeployer.4", getWarToPatchFile().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			ZipManager.unzip(getWarToPatchFile(), getDeployedWebbAppFolder(), true, true);
			//			TrueZipHelper tzh2 = new TrueZipHelper(warToPatchExt);
			//			result = tzh2.copyFiles(getWarToPatchFile(), getDeployedWebbAppFolder(), true);
		} else {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.7") + getWarToPatchFile().getAbsolutePath() + Activator.Messages.getString("DirectWebAppsDeployer.9")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (!result) {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.10")); //$NON-NLS-1$
		}
		cleanned = true;
		monitor.taskDone(Activator.Messages.getString("DirectWebAppsDeployer.2")); //$NON-NLS-1$
	}

	public FileFilter getFileFilter(final DeployMode mode) {
		FileFilter incrementalFileFilter = new FileFilter() {

			public boolean accept(File pathname) {
				try {
					String fileExt = FileHelper.getFileExt(pathname);
					if (fileExt.equals(packageExt)) {
						// test if package is newer than the deployed webapp
						if (cleanned || !incremental || mode.equals(DeployMode.CUSTOM)) {
							return true;
						} else {
							// incremental allowed
							long module = pathname.lastModified();
							File deployedWebbAppFolder = getIncrementalLastDeployedFlag();
							long webapp = deployedWebbAppFolder.lastModified();

							if (module > webapp) {
								System.out.println("module is newer ");
								// module must be deployed
								return true;
							} else {
								System.out.println("module skipped by incremental deployer :" + pathname);
								Date dateModule = new Date(module);
								Date dateWebapp = new Date(webapp);
								System.out.println("module :" + pathname + "[" + dateModule + "]");
								System.out.println("webapp :" + deployedWebbAppFolder + "[" + dateWebapp + "]");
							}
						}
					}
				} catch (Exception e) {
					System.out.println("fileName :" + pathname);
					e.printStackTrace();

				}

				return false;
			}
		};

		return incrementalFileFilter;
	}

	@Override
	protected void deployProcess(File fileToDeploy, DeployMode mode) throws Exception {
		System.out.println("DirectWebAppsDeployer.deployProcess() :" + this);
		FileFilter fileFilter = getFileFilter(mode);
		File deployedWebbAppFolder = getDeployedWebbAppFolder();
		if (!deployedWebbAppFolder.exists()) {
			this.clean(fileToDeploy);
		}
		if (fileToDeploy.exists() && fileToDeploy.isDirectory()) {
			for (File f : fileToDeploy.listFiles(fileFilter)) {
				List<String> list = deployFile(f).getList();

				deployedFiles.addAll(list);

				// check if files are copied more than one time, this detect file collision
//				for (String file : deployedFiles) {
//					int frequency = Collections.frequency(deployedFiles, file);
//					if (frequency > 1) {
//						// conflict detected ...
//						monitor.addWarningTextAndLog("Beware the file "+file+"have been overrided by module :"+fileToDeploy, "");
//					}
//				}

			}
		} else if (fileToDeploy.exists() && fileToDeploy.isFile() && fileFilter.accept(fileToDeploy)) {
			deployedFiles.addAll(deployFile(fileToDeploy).getList());
		} else {
			monitor.addWarningTextAndLog(Activator.Messages.getString("WarDeployer.5"), "");
		}

		// deploy process is done, we need to mark the deployed webapp		
		File incrementalLastDeploeydFlag = getIncrementalLastDeployedFlag();
		System.out.println("DirectWebAppsDeployer.deployProcess() touch " + incrementalLastDeploeydFlag);
		FileUtils.touch(incrementalLastDeploeydFlag);

		if (webappReloading) {
			System.out.println("DirectWebAppsDeployer.deployProcess() touch " + getWebAppXMLFile());
			// we touch web.xml too to let tomcat reload the webapp, some webapps should not be restarted
			FileUtils.touch(getWebAppXMLFile());
		}

	}

	private MonitorWriter deployFile(File f) throws Exception {
		monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.6"), Activator.Messages.getString("WarDeployer.7", f.getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
		File explodedPackage = new File(getWorkingDir(), f.getName().replaceAll("\\." + packageExt, "")); //$NON-NLS-1$ //$NON-NLS-2$

		explodedPackage.mkdirs();
		// unzip files in tmp
		//		tzh.copyFiles(f, explodedPackage, true);
		ZipManager.unzip(f, explodedPackage, true, true);

		Map<String, File> map = createMapper(explodedPackage);
		List<File> fileList = FileHelper.listAll(explodedPackage);

		return dispatchFiles(fileList, map);
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		File out = IFileHelper.getFile(IFileHelper.getIFolder(getTargetPath()));
		this.wkdir = new File(out, "deployer_" + getClass().getName());
		if (getWorkingDir().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("DirectWebAppsDeployer.5"), Activator.Messages.getString("DirectWebAppsDeployer.17", getWorkingDir().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			FileHelper.deleteFile(getWorkingDir(), false);
		}
		monitor.getLog().addInfoLog(Activator.Messages.getString("DirectWebAppsDeployer.5"), Activator.Messages.getString("WarDeployer.19", getWorkingDir().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
		getWorkingDir().mkdirs();
	}

	public Map<String, File> createMapper(File packageDirectory) {
		File packageFolder = getDeployedWebbAppFolder();

		Map<String, File> mapper = new HashMap<String, File>();
		// all files just copied in same directory organization, so filter is on
		// packageDirectory
		addToMap(mapper, packageDirectory.getAbsolutePath(), packageFolder, "/");
		return mapper;
	}

	protected void addToMap(Map<String, File> map, String key, File ampRoot, String target) {
		map.put(key.replace("/", File.separator), createTargetFolders(ampRoot, target.replace("/", File.separator))); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected File createTargetFolders(File ampRoot, String p) {
		File dir = new File(ampRoot, p);
		dir.mkdirs();
		return dir;
	}

	protected MonitorWriter dispatchFiles(List<File> files, Map<String, File> mapper) throws Exception {
		MonitorWriter mw = new MonitorWriter(monitor, Activator.Messages.getString("DirectWebAppsDeployer.3"), ""); //$NON-NLS-1$ //$NON-NLS-2$
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
		return mw;
	}

	public File getWebAppXMLFile() {
		String path = FilenameUtils.separatorsToSystem("/WEB-INF/web.xml");
		return new File(getDeployedWebbAppFolder().getAbsolutePath() + path);
	}

	public File getIncrementalLastDeployedFlag() {
		String path = FilenameUtils.separatorsToSystem("/META-INF/lastDeployed.txt");
		return new File(getDeployedWebbAppFolder().getAbsolutePath() + path);
	}

}
