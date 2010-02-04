package com.bluexml.side.framework.alfresco.formProcessor;
 
import java.util.regex.Pattern;

import org.alfresco.repo.forms.processor.node.NodeFormProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CustomNodeFormProcessor extends NodeFormProcessor {
	private static Log logger = LogFactory.getLog(CustomNodeFormProcessor.class);
	public CustomNodeFormProcessor() {		
		propertyNamePattern = Pattern.compile(PROP_DATA_PREFIX + "([^_]*){1}?_(.*){1}?");
		transientPropertyPattern = Pattern.compile(PROP_DATA_PREFIX + "([^_]*){1}?");
		associationNamePattern = Pattern.compile(ASSOC_DATA_PREFIX + "([^_]*){1}?_(.*){1}?(_[a-zA-Z]+)");
		logger.info("[X] Custom Node Processor loaded ...[X]");
	}
}
