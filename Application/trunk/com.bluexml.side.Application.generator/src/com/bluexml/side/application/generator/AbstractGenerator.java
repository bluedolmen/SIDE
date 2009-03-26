package com.bluexml.side.application.generator;

import java.util.Map;

import com.bluexml.side.application.StaticConfigurationParameters;

public abstract class AbstractGenerator implements IGenerator {

	/**
	 * generationParameters : the list of selected options for the generation (clean,
	 *            verbose...), shared by all generators.
	 * generatorOptions : the list of selected options for the generator.(templates subset ...)
	 * configurationParameters : the list of technical parameters, shared by all generators.
	 */
	protected Map<String, String> generationParameters;
	protected Map<String, Boolean> generatorOptions;
	protected Map<String,String> configurationParameters;
	
	public void initialize(Map<String, String> generationParameters,
			Map<String, Boolean> generatorOptions,
			Map<String, String> configurationParameters) {
		this.generationParameters = generationParameters;
		this.generatorOptions = generatorOptions;
		this.configurationParameters = configurationParameters;
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
	
	protected final String getTargetPath(){
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
	}
	
	protected boolean getGeneratorOptionValue(String key) {
		if (generatorOptions.containsKey(key)) {
			return generatorOptions.get(key);
		}
		return false;
	}
	protected String getGenerationParameter(String key) {
		if (generationParameters.containsKey(key)) {
			return generationParameters.get(key);
		}
		return null;
	}
	protected String getConfigurationParameter(String key) {
		if (configurationParameters.containsKey(key)) {
			return configurationParameters.get(key);
		}
		return null;
	}
}
