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
