package com.bluexml.side.application.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFolder;

import com.bluexml.side.application.StaticConfigurationParameters;
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
	public static final String TEMP_FOLDER = "tmp";
	public static String GENERATOR_CODE = null;
	protected static String techVersion = null;

	public String getTechVersion() {
		return techVersion;
	}

	public void setTechVersion(String techVersion_) {
		techVersion = techVersion_;
	}

	public String getTEMP_FOLDER() {
		return TEMP_FOLDER + this.getClass().getName();
	}

	public void initialize(Map<String, String> generationParameters_, Map<String, Boolean> generatorOptions_, Map<String, String> configurationParameters_, String techVersion_) {
		generationParameters = generationParameters_;
		generatorOptions = generatorOptions_;
		configurationParameters = configurationParameters_;
		techVersion = techVersion_;

	}

	protected boolean doLog() {
		if (configurationParameters != null)
			return configurationParameters.containsKey(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral());
		return false;
	}

	protected String getLogFile() {
		if (doLog())
			return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral());
		return null;
	}

	protected final String getTargetSystemPath() {
		IFolder ff = IFileHelper.getIFolder(getTargetPath());
		return ff.getRawLocation().makeAbsolute().toOSString();
	}

	protected final File getTargetSystemFile() {
		IFolder ff = IFileHelper.getIFolder(getTargetPath());
		return ff.getRawLocation().makeAbsolute().toFile();
	}

	public final String getTargetPath() {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
	}

	protected static boolean doVerbose() {
		if (configurationParameters != null && configurationParameters.containsKey(StaticConfigurationParameters.GENERATIONOPTIONSVERBOSE.getLiteral())) {
			return Boolean.parseBoolean(configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSVERBOSE.getLiteral()));
		}
		return false;
	}

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

	protected final String getTemporarySystemFolder() {
		return getTargetSystemPath() + File.separator + getTEMP_FOLDER();
	}

	protected final String getTemporaryFolder() {
		return getTargetPath() + File.separator + getTEMP_FOLDER();
	}

	public static void printConfiguration() {
		System.out.println("GenerationOptions :" + generatorOptions);
		System.out.println("GenerationParameters :" + generationParameters);
		System.out.println("ConfigurationParameters :" + configurationParameters);
		System.out.println("TechVersion :" + techVersion);
		
	}
}
