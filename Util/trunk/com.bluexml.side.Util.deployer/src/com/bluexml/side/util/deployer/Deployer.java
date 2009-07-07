/**
 *
 */
package com.bluexml.side.util.deployer;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.documentation.structure.LogEntry;
import com.bluexml.side.util.documentation.structure.SIDELog;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.Checkable;

/**
 * @author davidabad generic Deployer must be implemented to match with target
 *         technology
 */
public abstract class Deployer implements Checkable {
	String workingDirKey = "generation.options.destinationPath";
	private Map<String, String> configurationParameters;
	private Map<String, String> generationParameters;
	protected SIDELog log;
	protected String id;
	protected String techVersion = null;

	public SIDELog getLog() {
		return log;
	}

	public String getCleanKey() {
		return cleanKey;
	}

	public void setCleanKey(String cleanKey) {
		this.cleanKey = cleanKey;
	}

	public String getLogChanges() {
		return logChanges;
	}

	public void setLogChanges(String logChanges) {
		this.logChanges = logChanges;
	}

	public static String DEPLOYER_CODE = null;
	protected String cleanKey = null;
	protected String cleanKeyMsg = "target cleaned";
	protected String logChanges = null;
	protected String logChangesMsg = "detail about target changes made by this deployer";
	protected List<String> options = null;

	/**
	 * Use to setup all properties, ordinary used by deployer luncher
	 *
	 * @param configurationParameters
	 * @param generationParameters
	 * @param options
	 */
	public void initialize(Map<String, String> configurationParameters,
			Map<String, String> generationParameters, List<String> options) {
		this.configurationParameters = configurationParameters;
		this.options = options;
		this.generationParameters = generationParameters;
		this.techVersion = configurationParameters.get("technologyVersion");
		this.id = configurationParameters.get("deployerId");
		log = new SIDELog(configurationParameters.get("deployerName"), id,
				configurationParameters.get("technologyVersionName"),
				configurationParameters.get("technologyName"),
				configurationParameters.get("metaModelName"), new Date(),
				LogType.DEPLOYMENT);
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
		String IfilewkDirPath = getTargetPath();
		String absoluteWKDirePath = IFileHelper
				.getSystemFolderPath(IfilewkDirPath);
		File fileToDeploy = getFileToDeploy(absoluteWKDirePath);
		preProcess(fileToDeploy);
		if (doClean()) {
			clean(fileToDeploy);
		}
		deployProcess(fileToDeploy);
		postProcess(fileToDeploy);
	}

	/**
	 * default method to get the File to deploy
	 *
	 * @param absoluteWKDirPath
	 * @return
	 */
	public File getFileToDeploy(String absoluteWKDirPath) {
		return new File(absoluteWKDirPath + File.separator + techVersion);
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
	 * @return
	 */
	public final String getTargetPath() {
		return configurationParameters
				.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH
						.getLiteral());
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
		return options != null && options.contains(logChanges);
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
	 * Add a Log
	 *
	 * @param title
	 * @param description
	 * @param uri
	 * @param logEntryType
	 */
	protected void addLog(String title, String description, String uri,
			LogEntryType logEntryType) {
		log.addLogEntry(new LogEntry(title, description, uri, logEntryType));
	}

	/**
	 * Add an Error Log
	 *
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addErrorLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.ERROR);
	}

	/**
	 * Add an error log using a stracktrace instead of a string description
	 *
	 * @param title
	 * @param stackTrace
	 * @param uri
	 */
	public void addErrorLog(String title, StackTraceElement[] stackTrace,
			String uri) {
		String description = "";
		if (stackTrace != null && stackTrace.length > 0) {
			for (StackTraceElement se : stackTrace) {
				description += System.getProperty("line.separator")
						+ se.toString();
			}
		}
		addErrorLog(title, description, uri);
	}

	/**
	 * Add a warning log
	 *
	 * @param title
	 * @param description
	 * @param uri
	 *            : null if no uri
	 */
	public void addWarningLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.WARNING);
	}

	/**
	 * Add information log
	 *
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addInfoLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.DEPLOYMENT_INFORMATION);
	}

	/**
	 * Move the stamp file added by the generator to the directory into log path
	 * to be used for log purpose.
	 * @throws Exception
	 */
	final public void moveStampFile(String logPath) throws Exception {
		// Seek all .xml files into gen directory
		IFolder source = IFileHelper.getIFolder(getTargetPath() + System.getProperty("file.separator") + getTechVersion());
		if (source.exists()) {
			IFileHelper.refreshFolder(source);
			IFolder dest = IFileHelper.createFolder(logPath + System.getProperty("file.separator") + LogSave.LOG_STAMP_FOLDER + System.getProperty("file.separator"));
			if (dest.exists()) {
				IFileHelper.refreshFolder(dest);
				List<IFile> toMove = IFileHelper.getAllFiles(source);
				for (IFile xmlFile : toMove) {
					if (xmlFile.getName().endsWith(".xml")) {
						IFileHelper.moveFile(xmlFile, dest, true);
					}
				}
			}
		}
	}
}
