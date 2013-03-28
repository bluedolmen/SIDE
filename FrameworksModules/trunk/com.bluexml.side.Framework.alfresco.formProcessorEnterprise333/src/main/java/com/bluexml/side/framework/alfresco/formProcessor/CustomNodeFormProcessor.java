/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
