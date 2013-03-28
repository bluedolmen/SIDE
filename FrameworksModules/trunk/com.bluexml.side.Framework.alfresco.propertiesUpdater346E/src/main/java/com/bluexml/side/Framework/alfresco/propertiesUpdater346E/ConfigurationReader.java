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
package com.bluexml.side.Framework.alfresco.propertiesUpdater346E;

import org.jdom.Element;

import com.bluexml.side.Framework.alfresco.propertiesUpdater346E.ConfigurationReader.PropertyPattern;
import com.bluexml.side.framework.alfresco.commons.configurations.MultiValuesConfigurationXMLFile;

public class ConfigurationReader extends MultiValuesConfigurationXMLFile<String, PropertyPattern> {

	private static final String QNAME = "qname";

	public ConfigurationReader() throws Exception {
		super("type", QNAME, "property");
	}

	@Override
	protected PropertyPattern getValueObject(Element value) {
		// TODO Auto-generated method stub
		return new PropertyPattern(value.getAttributeValue(QNAME), value.getTextNormalize());
	}

	@Override
	protected String getKeyObject(String keyString) {
		return keyString;
	}

	class PropertyPattern {
		final public String propertyQname;
		final public String pattern;

		public PropertyPattern(String propertyQname, String pattern) {
			this.propertyQname = propertyQname;
			this.pattern = pattern;

		}
	}

}
