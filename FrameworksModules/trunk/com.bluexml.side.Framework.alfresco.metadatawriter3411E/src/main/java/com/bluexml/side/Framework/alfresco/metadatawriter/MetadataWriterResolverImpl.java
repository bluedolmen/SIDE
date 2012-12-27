package com.bluexml.side.Framework.alfresco.metadatawriter;

import java.io.Serializable;

import com.bluexml.side.framework.alfresco.commons.configurations.PolicyPropertiesConfiguration;

public class MetadataWriterResolverImpl  extends PolicyPropertiesConfiguration {
	public Serializable resolve(String propName) {
		if (dictionary.containsKey(propName)) {
			return dictionary.get(propName);
		} else {
			return null;
		}
	}
	
	public boolean containsKey(String key) {
		return dictionary.containsKey(key);
	}

	public boolean notNull(String key) {
		// TODO Auto-generated method stub
		String test = dictionary.get(key);
		if (test != null) {
			if ( !test.equals("") && !test.equals(" ")) {
			return true;
			}
		}
			return false;
	}
}
