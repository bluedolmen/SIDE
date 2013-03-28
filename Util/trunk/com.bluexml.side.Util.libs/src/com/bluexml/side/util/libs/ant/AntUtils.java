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
package com.bluexml.side.util.libs.ant;

import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.Project;

public class AntUtils {
	public static void setProperties(Project ant, Properties p) {
		for (Map.Entry<Object, Object> it : p.entrySet()) {
			ant.setProperty(it.getKey().toString(), it.getValue().toString());
		}
	}

	public static void setProperties(Project ant, Map<String, String> properties) {
		for (Map.Entry<String, String> item : properties.entrySet()) {
			ant.setProperty(item.getKey(), item.getValue());
		}		
	}
	
}
