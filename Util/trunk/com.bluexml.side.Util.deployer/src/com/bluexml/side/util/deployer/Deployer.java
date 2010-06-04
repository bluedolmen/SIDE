/**
 *
 */
package com.bluexml.side.util.deployer;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.Checkable;

/**
 * @author davidabad generic Deployer must be implemented to match with target
 *         technology
 */
public abstract class Deployer implements Checkable {
	public static String workingDirKey = "generation.options.destinationPath"; //$NON-NLS-1$
	private Map<String, String> configurationParameters;
	private Map<String, String> generationParameters;
	protected ComponentMonitor monitor;
	protected File fileToDeploy;
	protected String id;
	protected String techVersion = null;

	public void setFileToDeploy(File fileToDeploy) {
		this.fileToDeploy = fileToDeploy;
	}

	/**
	 * 
	 * @param cleanKey
	 *            the option key use in extension point
	 * @param logChangesKey
	 *            the option key use in extension point
	 */
	public Deployer(String cleanKey, String logChangesKey) {
		this.cleanKey = cleanKey;
		this.logChangesKey = logChangesKey;
	}

	public String getCleanKey() {
		return cleanKey;
	}

	public void setCleanKey(String cleanKey) {
		this.cleanKey = cleanKey;
	}

	public String getLogChanges() {
		return logChangesKey;
	}

	public void setLogChanges(String logChanges) {
		this.logChangesKey = logChanges;
	}

	public static String DEPLOYER_CODE = null;
	protected String cleanKey = null;
	protected String cleanKeyMsg = Activator.Messages.getString("Deployer.0"); //$NON-NLS-1$
	protected String logChangesKey = null;
	protected String logChangesMsg = Activator.Messages.getString("Deployer.1"); //$NON-NLS-1$
	protected List<String> options = null;

	/**
	 * Use to setup all properties, ordinary used by deployer luncher
	 * 
	 * @param configurationParameters
	 * @param generationParameters
	 * @param options
	 */
	public void initialize(Map<String, String> configurationParameters, Map<String, String> generationParameters, List<String> options, ComponentMonitor monitor) {
		this.monitor = monitor;
		this.configurationParameters = configurationParameters;
		this.options = options;
		this.generationParameters = generationParameters;
		this.techVersion = configurationParameters.get("technologyVersion"); //$NON-NLS-1$
		this.id = configurationParameters.get("deployerId"); //$NON-NLS-1$

		for (Map.Entry<String, String> iterable_element : generationParameters.entrySet()) {
			if (iterable_element.getValue() == null || iterable_element.getValue().length() == 0) {
				monitor.getLog().addWarningLog(Activator.Messages.getString("Deployer.8"), Activator.Messages.getString("Deployer.9", iterable_element.getKey()), ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
		}
	}

	public Map<String, String> getGenerationParameters() {
		return generationParameters;
	}

	public String getTechVersion() {
		return techVersion;
	}

	/**
	 * do the whole deploy process
	 * 
	 * @throws Exception
	 */
	public void deploy() throws Exception {
		File fileToDeploy = getFileToDeploy();
		// addInfoLog("preProcessing ...", "", null);
		// monitor.beginTask("preProcess start");
		preProcess(fileToDeploy);
		monitor.taskDone(null);
		//monitor.taskDone(Activator.Messages.getString("Deployer.12")); //$NON-NLS-1$
		if (doClean()) {
			// addInfoLog("Cleaning ...", "", null);
			clean(fileToDeploy);
		}
		// addInfoLog("Processing ...", "", null);
		// monitor.customSubTask("main deploy process start");
		deployProcess(fileToDeploy);
		monitor.taskDone(null);
		//monitor.taskDone(Activator.Messages.getString("Deployer.13")); //$NON-NLS-1$
		// monitor.beginTask("postProcess start");
		postProcess(fileToDeploy);
		monitor.taskDone(null);
		//monitor.taskDone(Activator.Messages.getString("Deployer.14")); //$NON-NLS-1$
	}

	/**
	 * Return if the deployer is a documentation deployer.
	 * 
	 * @return
	 */
	public boolean isDocumentationDeployer() {
		return false;
	}

	/**
	 * default method to get the File to deploy
	 * 
	 * @param absoluteWKDirPath
	 * @return
	 */
	public File getFileToDeploy() {
		if (fileToDeploy == null) {
			String IfilewkDirPath = getTargetPath();
			String absoluteWKDirPath = IFileHelper.getSystemFolderPath(IfilewkDirPath);
			fileToDeploy = new File(absoluteWKDirPath + File.separator + techVersion);
		}
		return fileToDeploy;

	}

	/**
	 * the main deploy process
	 * 
	 * @param fileToDeploy
	 * @throws Exception
	 */
	protected abstract void deployProcess(File fileToDeploy) throws Exception;

	/**
	 * method that clean the target before deploy resources into
	 * 
	 * @param fileToDeploy
	 * @throws Exception
	 */
	protected abstract void clean(File fileToDeploy) throws Exception;

	/**
	 * Job to do after the main process
	 * 
	 * @param fileToDeploy
	 * @throws Exception
	 */
	protected abstract void postProcess(File fileToDeploy) throws Exception;

	/**
	 * Job to do before the main process
	 * 
	 * @param fileToDeploy
	 * @throws Exception
	 */
	protected abstract void preProcess(File fileToDeploy) throws Exception;

	/**
	 * Return the path where generator have outputed theirs files.
	 * 
	 * @return
	 */
	public final String getTargetPath() {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
	}

	public Map<String, String> getConfigurationParameters() {
		return configurationParameters;
	}

	/**
	 * check if changes made by the deploy process must be logged
	 * 
	 * @return
	 */
	protected boolean logChanges() {
		return options != null && options.contains(logChangesKey);
	}

	/**
	 * check if clean must be done
	 * 
	 * @return
	 */
	protected boolean doClean() {
		return options != null && options.contains(cleanKey);
	}

	/**
	 * Move the stamp file added by the generator to the directory into log path
	 * to be used for log purpose.
	 * 
	 * @throws Exception
	 */
	final public void moveStampFile(String logPath) throws Exception {
		// Seek all .xml files into gen directory
		IFolder source = IFileHelper.getIFolder(getTargetPath() + System.getProperty("file.separator") + getTechVersion()); //$NON-NLS-1$
		if (source.exists()) {
			IFileHelper.refreshFolder(source);
			IFolder dest = IFileHelper.createFolder(logPath + System.getProperty("file.separator") + LogSave.LOG_STAMP_FOLDER + System.getProperty("file.separator")); //$NON-NLS-1$ //$NON-NLS-2$
			if (dest.exists()) {
				IFileHelper.refreshFolder(dest);
				List<IFile> toMove = IFileHelper.getAllFiles(source);
				for (IFile xmlFile : toMove) {
					if (xmlFile.getName().endsWith("-stamp.xml")) { //$NON-NLS-1$
						IFileHelper.moveFile(xmlFile, dest, true);
					}
				}
			}
		}
	}
	
	public boolean check() {
		return true;
	}
	public boolean checkOption(String optionID) {
		return true;
	}
	
}
