package com.bluexml.side.Workflow.modeler.actions.popup;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bluexml.side.Workflow.modeler.actions.popup.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	/**
	 * Return the string for the given key using objects to bind {x} param.
	 * @param key
	 * @param objects
	 * @return
	 */
	public static String getString(String key, Object[] objects) {
		return bind(getString(key),objects);
	}

	public static String getString(String key, Object object) {
		return bind(getString(key),object);
	}
}
