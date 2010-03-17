package com.bluexml.side.framework.alfresco.formProcessor;

import java.util.regex.Pattern;

import org.alfresco.repo.forms.processor.node.TypeFormProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CustomTypeFormProcessor extends TypeFormProcessor {
	private static Log logger = LogFactory.getLog(CustomTypeFormProcessor.class);
	public CustomTypeFormProcessor() {		
		propertyNamePattern = Pattern.compile(PROP_DATA_PREFIX + "(.*){1}?_(.*){1}?");
		transientPropertyPattern = Pattern.compile(PROP_DATA_PREFIX + "(.*){1}?");
		associationNamePattern = Pattern.compile(ASSOC_DATA_PREFIX + "(.*){1}?_(.*){1}?(_[a-zA-Z]+)");
		logger.info("[X] Custom Type Processor loaded ...[X]");
	}
}
