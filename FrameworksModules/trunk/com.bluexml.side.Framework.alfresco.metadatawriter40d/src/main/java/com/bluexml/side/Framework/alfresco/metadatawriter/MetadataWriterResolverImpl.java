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
package com.bluexml.side.Framework.alfresco.metadatawriter;

import java.io.Serializable;

import com.bluexml.side.framework.alfresco.commons.configurations.PolicyPropertiesConfiguration;

public class MetadataWriterResolverImpl  extends PolicyPropertiesConfiguration {
	public Serializable resolve(String propName) {
		if (dictionary.containsKey(propName)) {
			return dictionary.get(propName);
		} else {
			return null;
		}
	}
	
	public boolean containsKey(String key) {
		return dictionary.containsKey(key);
	}

	public boolean notNull(String key) {
		// TODO Auto-generated method stub
		String test = dictionary.get(key);
		if (test != null) {
			if ( !test.equals("") && !test.equals(" ")) {
			return true;
			}
		}
			return false;
	}
}
