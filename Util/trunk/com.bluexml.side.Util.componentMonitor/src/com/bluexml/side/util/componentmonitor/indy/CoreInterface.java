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
 * 
 */
package com.bluexml.side.util.componentmonitor.indy;

/**
 * Interface added for making the generation independent of the "as an Eclipse plugin" feature
 * so that the worker can also be a Maven plugin.<br/>
 * Because of svn:externals, this file should be the only file in its package.
 * 
 * @author Amenel
 * 
 */
public interface CoreInterface {
	void addText(String text);

	void addErrorTextAndLog(String title, Throwable error, String uri);

	void addServiceLog(String title, String description, String uri);
	
	void setTaskName(String name);

}
