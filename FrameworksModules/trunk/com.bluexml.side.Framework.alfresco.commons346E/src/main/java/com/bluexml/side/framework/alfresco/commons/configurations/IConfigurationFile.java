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
package com.bluexml.side.framework.alfresco.commons.configurations;

import java.util.Map;

public interface IConfigurationFile<K, V> {

	/**
	 * get the value for the given key
	 * 
	 * @param key
	 * @return
	 */
	public abstract V getValue(K key);

	/**
	 * true if value exists for this key
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean hasValue(K key);

	/**
	 * get read only access to dictionary Map
	 * 
	 * @return
	 */
	public abstract Map<K, V> getDictionary();

}
