package com.bluexml.side.util.documentation.structure.enumeration;

public enum LogEntryType {
	GENERATION_INFORMATION("GenerationInformation"), ERROR("Error"), WARNING(
			"Warning"), DEPLOYMENT_INFORMATION("DeploymentInformation"), GENERATED_FILE("GeneratedFile"), SERVICE("Service"), CONSOLE("ConsoleOutput");

	private final String name;

	private LogEntryType(String p_name) {
		this.name = p_name;
	}

	public String getName() {
		return name;
	}
}
