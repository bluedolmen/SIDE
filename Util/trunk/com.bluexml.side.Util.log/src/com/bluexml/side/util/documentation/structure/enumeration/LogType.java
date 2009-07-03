package com.bluexml.side.util.documentation.structure.enumeration;

public enum LogType {
	GENERATION("Generation"), DEPLOYMENT("Deployment");
	
	private final String name;

	private LogType(String p_name) {
		this.name = p_name;
	}

	public String getName() {
		return name;
	}
}
