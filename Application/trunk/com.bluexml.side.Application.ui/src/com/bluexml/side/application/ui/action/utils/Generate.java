package com.bluexml.side.application.ui.action.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationElement;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.generator.AbstractGenerator;
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;

public class Generate {

	/**
	 * Launch generation on all generator version selected
	 * 
	 * @param configuration
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void launch(Configuration configuration, List<String> staticParameters)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		// First we seek the generator parameters, and separate static fields of dynamic fields
		Map<String, String> configurationParameters = new HashMap<String, String>();
		Map<String, String> generationParameters = new HashMap<String, String>();
		for (ConfigurationParameters param : configuration.getParameters()) {
			if (staticParameters.contains(param.getKey())) {
				generationParameters.put(param.getKey(), param.getValue());
			} else {
				configurationParameters.put(param.getKey(), param.getValue());
			}
		}
		
		// Second for all generator version we will call generation method
		for (ConfigurationElement elem : configuration.getElements()) {
			String idGenerator = elem.getId_generator();

			Class<?> gen = Class.forName(idGenerator);
			Object genObj = gen.newInstance();

			// We get the option for this generator
			Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
			for (Option option : elem.getOptions()) {
				generatorOptions.put(option.getKey(), true);
			}

			// We initialize the generator with all data collected in application model
			if (genObj instanceof AbstractGenerator) {
				AbstractGenerator generator = (AbstractGenerator) genObj;
				generator.initialize(generationParameters, generatorOptions,
						configurationParameters);
			}
		}
	}
}
