package com.bluexml.side.util.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.documentation.structure.LogEntry;
import com.bluexml.side.util.documentation.structure.SIDELog;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.Checkable;

public abstract class AbstractGenerator implements IGenerator, Checkable {

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
	protected String id;
	public String TEMP_FOLDER = "tmp";
	public static String GENERATOR_CODE = null;
	protected static String techVersion = null;
	protected DependencesManager dm;

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

	/**
	 * Return if this generator is a documentation generator.
	 * @return
	 */
	public boolean isDocumentationGenerator() {
		return false;
	}

	public void initialize(Map<String, String> generationParameters_, Map<String, Boolean> generatorOptions_, Map<String, String> configurationParameters_, DependencesManager dm) throws Exception {
		generationParameters = generationParameters_;
		generatorOptions = generatorOptions_;
		configurationParameters = configurationParameters_;
		techVersion = configurationParameters_.get("technologyVersion");
		id = configurationParameters_.get("generatorId");
		log = new SIDELog(configurationParameters_.get("generatorName"), this.id, configurationParameters_.get("technologyVersionName"), configurationParameters_.get("technologyName"), configurationParameters_.get("metaModelName"), new Date(), LogType.GENERATION);
		this.dm = dm;
	}

	/**
	 * This method must be call after a successful generation to put an XML file
	 * into the gen path that will be used to know what have been deployed.
	 * Mainly use for log purpose.
	 *
	 * @throws CoreException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public final void createStampFile() throws CoreException, FileNotFoundException, IOException {
		IFileHelper.refreshFolder(getTargetPath());
		IFolder	ff = IFileHelper.createFolder(getTargetPath() + File.separator + techVersion);
		Element racine = new Element("toDeploy"); //$NON-NLS-1$
		Attribute classe = new Attribute("id", this.id); //$NON-NLS-1$
		racine.setAttribute(classe);
		Attribute date = new Attribute("date", new Date().toString()); //$NON-NLS-1$
		racine.setAttribute(date);
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(racine, new FileOutputStream(IFileHelper.getFile(IFileHelper.createFile(ff, this.id + ".xml")))); //$NON-NLS-1$
	}

	/**
	 * Add log to know on each model have been launch generation
	 * @param name
	 */
	public void addModelLog (String name){
		log.addModel(name);
	}

	public void addModelLog (List<String> names){
		for (String name : names) {
			addModelLog(name);
		}
	}

	public void addModelsLog (List<IFile> models){
		for (IFile model : models) {
			addModelLog(model.getName());
		}
	}

	/**
	 * Add a Log
	 *
	 * @param title
	 * @param description
	 * @param uri
	 * @param logEntryType
	 */
	private void addLog(String title, String description, URI uri, LogEntryType logEntryType) {
		log.addLogEntry(new LogEntry(title, description, uri, logEntryType));
	}

	private void addLog(String title, String description, String uri, LogEntryType logEntryType) {
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

	public void addWarningLog(String title, String description, URI uri) {
		addLog(title, description, uri, LogEntryType.WARNING);
	}

	/**
	 * Add information log
	 *
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addInfoLog(String title, String description, URI uri) {
		addLog(title, description, uri, LogEntryType.GENERATION_INFORMATION);
	}

	public void addInfoLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.GENERATION_INFORMATION);
	}

	/**
	 * Add a service log (service : a webpage or a file that can be acceeded by
	 * user to test application).
	 *
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addServiceLog(String title, String description, URI uri) {
		addLog(title, description, uri, LogEntryType.SERVICE);
	}

	public void addServiceLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.SERVICE);
	}

	/**
	 * Use to log generated file
	 *
	 * @param path
	 * @param description
	 * @param uri
	 */
	public void addFileGeneratedLog(String path, String description, String uri) {
		addLog(path, description, uri, LogEntryType.GENERATED_FILE);
	}

	public void addFileGeneratedLog(String path, String description, URI uri) {
		addLog(path, description, uri, LogEntryType.GENERATED_FILE);
	}

	public SIDELog getLog() {
		return log;
	}

	public void setLog(SIDELog log) {
		this.log = log;
	}

	/**
	 * Return the log target file (only generator)
	 *
	 * @return
	 */
	protected String getLogFile() {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + File.separator + getClass().getName() + ".txt"; //$NON-NLS-1$

	}

	/**
	 * Return the absolute path to the generation target path
	 *
	 * @return
	 */
	protected final String getTargetSystemPath() {
		IFolder ff = IFileHelper.getIFolder(getTargetPath());
		return ff.getRawLocation().makeAbsolute().toOSString();
	}

	/**
	 * Return the generation target folder (as File).
	 *
	 * @return
	 */
	protected final File getTargetSystemFile() {
		IFolder ff = IFileHelper.getIFolder(getTargetPath());
		return ff.getRawLocation().makeAbsolute().toFile();
	}

	/**
	 * Return the target path (workspace path)
	 *
	 * @return
	 */
	public final String getTargetPath() {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
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
	 *
	 * @return
	 */
	protected final String getTemporarySystemFolder() {
		return getTargetSystemPath() + File.separator + getTEMP_FOLDER();
	}

	protected final File getTemporarySystemFile() {
		return new File(getTargetSystemPath() + File.separator + getTEMP_FOLDER());
	}

	/**
	 * Return the tempory path to temp folder of generation (wokspace path)
	 *
	 * @return
	 */
	protected final String getTemporaryFolder() {
		return getTargetPath() + File.separator + getTEMP_FOLDER();
	}

	protected final File getFinalFolder() {
		return new File(getTargetSystemPath() + File.separator + techVersion);
	}

	/**
	 * Print out information on generator (using System.out).
	 */
	public static void printConfiguration() {
		System.out.println("GenerationOptions :" + generatorOptions);
		System.out.println("GenerationParameters :" + generationParameters);
		System.out.println("ConfigurationParameters :" + configurationParameters);
		System.out.println("TechVersion :" + techVersion);
	}

	/**
	 * This method check if the user have the license to use this component.
	 *
	 * @return true if the component can be used.
	 */
	public boolean check() {
		return true;
	}

	/**
	 * use DependencesManager to get files required by the generated package and
	 * copy them in the technology version folder
	 *
	 * @throws Exception
	 */
	public void addDependences() throws Exception {
		// get dependences
		dm.copyDependencies(getTemporarySystemFile(),getTargetSystemFile());
		// dependences packages is now with other resources in the target folder


	}
}
