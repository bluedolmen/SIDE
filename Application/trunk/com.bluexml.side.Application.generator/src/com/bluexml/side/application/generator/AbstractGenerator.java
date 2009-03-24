package com.bluexml.side.application.generator;

import java.util.Map;

import com.bluexml.side.application.StaticConfigurationParameters;

public abstract class AbstractGenerator implements IGenerator {

	protected String[] generationParameters;
	protected String[] generatorOptions;
	protected Map<String,String> configurationParameters;
	
	public void initialize(String[] generationParameters,
			String[] generatorOptions,
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
}
