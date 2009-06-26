/**
 * 
 */
package com.bluexml.side.util.deployer;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bluexml.side.application.StaticConfigurationParameters;
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
	protected String techVersion = null;

	public SIDELog getLog() {
		return log;
	}

	public static String DEPLOYER_CODE = null;
	protected String cleanKey = null;
	protected String cleanKeyMsg ="target cleaned";
	protected String logChanges =  null;
	protected String logChangesMsg="detail about target changes made by this deployer";
	protected List<String> options = null;

	public void initialize(Map<String, String> configurationParameters, Map<String, String> generationParameters, List<String> options) {
		this.configurationParameters = configurationParameters;
		this.options = options;
		this.generationParameters = generationParameters;
		this.techVersion = configurationParameters.get("technologyVersion");
		log = new SIDELog(configurationParameters.get("deployerName"), configurationParameters.get("technologyVersionName"), configurationParameters.get("technologyName"), configurationParameters.get("metaModelName"), new Date(), LogType.DEPLOYEMENT);
	}

	public Map<String, String> getGenerationParameters() {
		return generationParameters;
	}

	public String getTechVersion() {
		return techVersion;
	}

	public void deploy() throws Exception {
		String IfilewkDirPath = getTargetPath();
		String absoluteWKDirePath = IFileHelper.getSystemFolderPath(IfilewkDirPath);
		File fileToDeploy = getFileToDeploy(absoluteWKDirePath);
		preProcess(fileToDeploy);
		if (doClean()) {
			clean(fileToDeploy);
		}
		deployProcess(fileToDeploy);
		postProcess(fileToDeploy);
	}

	public File getFileToDeploy(String absoluteWKDirePath) {
		return new File(absoluteWKDirePath + File.separator + techVersion);
	}
	
	protected abstract void deployProcess(File fileToDeploy) throws Exception;

	protected abstract void clean(File fileToDeploy) throws Exception;

	protected abstract void postProcess(File fileToDeploy) throws Exception;

	protected abstract void preProcess(File fileToDeploy) throws Exception;

	public final String getTargetPath() {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
	}

	public Map<String, String> getConfigurationParameters() {
		return configurationParameters;
	}

	protected boolean logChanges() {
		return options != null && options.contains(logChanges);
	}
	
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
	protected void addLog(String title, String description, String uri, LogEntryType logEntryType) {
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
	public void addErrorLog(String title, StackTraceElement[] stackTrace, String uri) {
		String description = "";
		if (stackTrace != null && stackTrace.length > 0) {
			for (StackTraceElement se : stackTrace) {
				description += System.getProperty("line.separator") + se.toString();
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
		addLog(title, description, uri, LogEntryType.DEPLOYEMENT_INFORMATION);
	}
}
