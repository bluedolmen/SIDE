package com.bluexml.side.application.ui.action.utils;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.ui.action.ApplicationDialog;

public class ApplicationUtil {
	/**
	 * Return the configuration corresponding to the given key in the current
	 * configuration. Return null if not found.
	 * 
	 * @param key
	 * @return
	 */
	public static ConfigurationParameters getConfigurationParmeterByKey(
			String key) {
		ConfigurationParameters result = null;
		Configuration config = ApplicationDialog.getCurrentConfiguration();
		int i = 0;
		int size = config.getParameters().size();
		while (i < size && result == null) {
			ConfigurationParameters param = config.getParameters().get(i);
			if (param.getKey().equals(key)) {
				result = param;
			}
			i++;
		}
		return result;
	}
}
