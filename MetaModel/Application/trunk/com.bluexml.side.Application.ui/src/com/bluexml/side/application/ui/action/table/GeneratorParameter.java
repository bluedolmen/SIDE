package com.bluexml.side.application.ui.action.table;

import org.eclipse.core.runtime.IConfigurationElement;

public class GeneratorParameter implements Comparable<GeneratorParameter> {
	private String key;
	private String value;
	private String label;
	private String documentation;
	private String dataType;

	public GeneratorParameter(String key, String label, String value, String documentation, String dataType) {
		this.key = key;
		this.label = label;
		this.value = value;
		this.documentation = documentation;
		this.dataType = dataType;
	}

	public GeneratorParameter(IConfigurationElement confParam) {
		this.key = confParam.getAttribute("key");
		this.label = confParam.getAttribute("label");
		String defaultValueAtt = confParam.getAttribute("defaultValue");
		if (defaultValueAtt != null && !defaultValueAtt.equals("")) {
			this.value = defaultValueAtt;
		} else {
			this.value = null;
		}

		this.documentation = confParam.getAttribute("documentation");
		this.dataType = confParam.getAttribute("dataType");

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

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataType() {
		return dataType;
	}

	public int compareTo(GeneratorParameter o) {
		return label.compareTo(o.getLabel());
	}
}
