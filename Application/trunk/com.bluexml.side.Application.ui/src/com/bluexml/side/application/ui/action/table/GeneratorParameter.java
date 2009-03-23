package com.bluexml.side.application.ui.action.table;

public class GeneratorParameter {
	private String key;
	private String value;
	private String label;
	private String documentation;

	public GeneratorParameter(String key, String label, String value, String documentation) {
		super();
		this.key = key;
		this.label = label;
		this.value = value;
		this.documentation = documentation;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
