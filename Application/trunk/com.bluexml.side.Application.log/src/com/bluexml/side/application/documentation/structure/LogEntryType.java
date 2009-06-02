package com.bluexml.side.application.documentation.structure;

public enum LogEntryType {
	GENERATION_INFORMATION("GenerationInformation"), ERROR("Error"), WARNING(
			"Warning"), DEPLOYEMENT_INFORMATION("DeployementInformation");

	private final String name;

	private LogEntryType(String p_name) {
		this.name = p_name;
	}

	public String getName() {
		return name;
	}
}
