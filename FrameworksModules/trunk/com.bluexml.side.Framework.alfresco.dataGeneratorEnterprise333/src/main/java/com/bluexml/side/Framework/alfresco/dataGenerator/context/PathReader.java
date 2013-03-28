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
/**
 * This class allows manipulations of path patterns to access files or folders
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.context;


/**
 * @author dchevrier
 *
 */
public class PathReader {

	private String pathPattern;

	/**
	 * @return the path
	 */
	public String getPathPattern() {
		return pathPattern;
	}

	/**
	 * @param path the path to set
	 */
	public void setPathPattern(String pathPattern) {
		this.pathPattern = pathPattern;
	}
	
}
