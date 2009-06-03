package com.bluexml.side.application.generator;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFolder;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.documentation.structure.LogEntry;
import com.bluexml.side.application.documentation.structure.LogEntryType;
import com.bluexml.side.application.documentation.structure.LogType;
import com.bluexml.side.application.documentation.structure.SIDELog;
import com.bluexml.side.application.security.Checkable;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractGenerator implements IGenerator,Checkable {

	/**
	 * generationParameters : the list of selected options for the generation
	 * (clean, verbose...), shared by all generators. generatorOptions : the
	 * list of selected options for the generator.(templates subset ...)
	 * configurationParameters : the list of technical parameters, shared by all
	 * generators.
	 */
	protected static Map<String, String> generationParameters = new HashMap<String, String>();
	protected static Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
	protected static Map<String, String> configurationParameters = new HashMap<String, String>();
	protected SIDELog log;
	public String TEMP_FOLDER = "tmp";
	public static String GENERATOR_CODE = null;
	protected static String techVersion = null;

	public String getTechVersion() {
		return techVersion;
	}

	public void setTechVersion(String techVersion_) {
		techVersion = techVersion_;
	}

	public void setTEMP_FOLDER(String s) {
		TEMP_FOLDER = s;
	}
	public String getTEMP_FOLDER() {
		return TEMP_FOLDER;
	}

	public void initialize(Map<String, String> generationParameters_, Map<String, Boolean> generatorOptions_, Map<String, String> configurationParameters_, String techVersion_) throws Exception {
		generationParameters = generationParameters_;
		generatorOptions = generatorOptions_;
		configurationParameters = configurationParameters_;
		techVersion = techVersion_;
		log = new SIDELog(techVersion, new Date(),LogType.GENERATION);
	}

	/**
	 * Return if log might be done or not.
	 * @return
	 */
	protected boolean doLog() {
		if (configurationParameters != null)
			return configurationParameters.containsKey(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral());
		return false;
	}
	
	/**
	 * Add a Log
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
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addErrorLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.ERROR);
	}
	
	/**
	 * Add a warning log
	 * @param title
	 * @param description
	 * @param uri : null if no uri
	 */
	public void addWarningLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.WARNING);
	}
	
	/**
	 * Add information log
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addInfoLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.GENERATION_INFORMATION);
	}
	
	/**
	 * Use to log generated file
	 * @param path
	 * @param description
	 * @param uri
	 */
	public void addFileGeneratedLog(String path, String description, String uri) {
		addLog(path, description, null, LogEntryType.GENERATED_FILE);
	}
	

	public SIDELog getLog() {
		return log;
	}

	public void setLog(SIDELog log) {
		this.log = log;
	}

	/**
	 * Return the log target folder
	 * @return
	 */
	protected String getLogFile() {
		if (doLog())
			return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral());
		return null;
	}

	/**
	 * Return the absolute path to the generation target path
	 * @return
	 */
	protected final String getTargetSystemPath() {
		IFolder ff = IFileHelper.getIFolder(getTargetPath());
		return ff.getRawLocation().makeAbsolute().toOSString();
	}

	/**
	 * Return the generation target folder (as File).
	 * @return
	 */
	protected final File getTargetSystemFile() {
		IFolder ff = IFileHelper.getIFolder(getTargetPath());
		return ff.getRawLocation().makeAbsolute().toFile();
	}

	/**
	 * Return the target path (workspace path)
	 * @return
	 */
	public final String getTargetPath() {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
	}

	/**
	 * Return if generation might be verbose or not
	 * @return
	 */
	protected static boolean doVerbose() {
		if (configurationParameters != null && configurationParameters.containsKey(StaticConfigurationParameters.GENERATIONOPTIONSVERBOSE.getLiteral())) {
			return Boolean.parseBoolean(configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSVERBOSE.getLiteral()));
		}
		return false;
	}

	/**
	 * Return if cleaning might be done or not.
	 * @return
	 */
	protected static boolean doClean() {
		if (configurationParameters != null && configurationParameters.containsKey(StaticConfigurationParameters.GENERATIONOPTIONSCLEAN.getLiteral())) {
			return Boolean.parseBoolean(configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSCLEAN.getLiteral()));
		}
		return false;
	}

	public static boolean getGeneratorOptionValue(String key) {
		if (AbstractGenerator.generatorOptions.containsKey(key)) {
			return AbstractGenerator.generatorOptions.get(key);
		}
		return false;
	}

	public static String getConfigurationParameter(String key) {
		if (AbstractGenerator.configurationParameters.containsKey(key)) {
			return AbstractGenerator.configurationParameters.get(key);
		}
		return "";
	}

	public static String getGenerationParameter(String key) {
		if (AbstractGenerator.generationParameters.containsKey(key)) {
			return AbstractGenerator.generationParameters.get(key);
		}
		return "";
	}

	/**
	 * Return the absolute path for temp folder of generation 
	 * @return
	 */
	protected final String getTemporarySystemFolder() {
		return getTargetSystemPath() + File.separator + getTEMP_FOLDER();
	}

	/**
	 * Return the tempory path to temp folder of generation (wokspace path)
	 * @return
	 */
	protected final String getTemporaryFolder() {
		return getTargetPath() + File.separator + getTEMP_FOLDER();
	}

	/**
	 * Print ou information on generator (using System.out).
	 */
	public static void printConfiguration() {
		System.out.println("GenerationOptions :" + generatorOptions);
		System.out.println("GenerationParameters :" + generationParameters);
		System.out.println("ConfigurationParameters :" + configurationParameters);
		System.out.println("TechVersion :" + techVersion);
		
	}
}
