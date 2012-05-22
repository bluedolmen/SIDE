package com.bluexml.side.framework.alfresco.commons.configurations;

import java.util.Properties;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public class PolicyPropertiesConfiguration extends PropertiesConfiguration {

	public static final String POLICY_ENABLED = "policy.enabled";

	@Override
	public boolean isValidePropertiesResource(Properties props) {
		String property = props.getProperty(POLICY_ENABLED);
		if (property == null) {
			property = "true";
		} else {
			// this key only used to tell if other key in bundle must be loaded, but has no signification for the policy
			props.remove(POLICY_ENABLED);
		}
		return property.trim().equals("true");
	}

}
