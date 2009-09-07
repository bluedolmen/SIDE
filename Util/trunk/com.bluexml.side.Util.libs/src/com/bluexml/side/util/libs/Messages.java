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
