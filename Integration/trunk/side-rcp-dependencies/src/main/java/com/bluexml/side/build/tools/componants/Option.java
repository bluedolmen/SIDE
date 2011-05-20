package com.bluexml.side.build.tools.componants;

public class Option extends Configuration {

	String key;
	String label;
	String fullKey;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getFullKey() {
		return fullKey;
	}
	public void setFullKey(String fullKey) {
		this.fullKey = fullKey;
	}
	
	public String toString() {
		return fullKey;
	}
	
}
