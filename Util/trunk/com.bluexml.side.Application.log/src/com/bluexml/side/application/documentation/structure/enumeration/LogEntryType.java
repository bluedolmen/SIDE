package com.bluexml.side.application.documentation.structure.enumeration;

public enum LogEntryType {
	GENERATION_INFORMATION("GenerationInformation"), ERROR("Error"), WARNING(
			"Warning"), DEPLOYEMENT_INFORMATION("DeployementInformation"), GENERATED_FILE("GeneratedFile");

	private final String name;

	private LogEntryType(String p_name) {
		this.name = p_name;
	}

	public String getName() {
		return name;
	}
}
