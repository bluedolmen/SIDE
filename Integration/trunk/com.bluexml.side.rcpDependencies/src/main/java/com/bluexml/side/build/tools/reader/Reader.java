package com.bluexml.side.build.tools.reader;

import java.util.Properties;

import org.apache.log4j.Logger;

public class Reader {
	Logger logger = Logger.getLogger(this.getClass());
	ComponantsRegisters registries;
	Properties props;

	protected Reader(ComponantsRegisters registries, Properties props) {
		this.registries = registries;
		this.props = props;
	}

	protected String getPropertyKey(String optionKey) {
		return "reader." + this.getClass().getSimpleName() + "." + optionKey;
	}

	protected String getPropertyValueFor(String optionKey) {
		String propertyKey = getPropertyKey(optionKey);
		logger.debug("get value for " + propertyKey);
		return props.getProperty(propertyKey);
	}

	protected boolean getBooleanPropertyValueFor(String optionKey, boolean defaultValue) {
		String propertyValueFor = getPropertyValueFor(optionKey);
		if (propertyValueFor == null) {
			logger.debug("use default value for " + optionKey);
			return defaultValue;
		}
		return Boolean.parseBoolean(propertyValueFor.trim());
	}
}
