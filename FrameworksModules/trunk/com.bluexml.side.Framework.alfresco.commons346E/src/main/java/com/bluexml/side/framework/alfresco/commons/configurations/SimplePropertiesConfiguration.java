package com.bluexml.side.framework.alfresco.commons.configurations;

import java.util.Properties;

public class SimplePropertiesConfiguration extends PropertiesConfiguration {

	@Override
	public boolean isValidePropertiesResource(Properties props) {
		return true;
	}

}
