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
package com.bluexml.side.util.libs;

import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

public class Messages extends NLS{
	private ResourceBundle RESOURCE_BUNDLE;

	protected String BUNDLE_NAME;

	public Messages(String pluginId, String bundleName) {
		BUNDLE_NAME = bundleName;
		Bundle bundle = Platform.getBundle(pluginId);
		String activator = (String)bundle.getHeaders().get(Constants.BUNDLE_ACTIVATOR);

		try {
			Class activatorClass = bundle.loadClass(activator);
			RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME,Locale.getDefault(),activatorClass.getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return the string for the given key
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (Exception e) {
			return '!' + key + '!';
		}
	}

	/**
	 * Return the string for the given key using objects to bind {x} param.
	 * @param key
	 * @param objects
	 * @return
	 */
	public String getString(String key, Object[] objects) {
		return bind(getString(key),objects);
	}

	public String getString(String key, Object object) {
		return bind(getString(key),object);
	}
}
