package com.bluexml.side.application.ui.action.table;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.application.ui.action.tree.Generator;

public class GeneratorParameter implements Comparable<GeneratorParameter>{
	private String key;
	private String value;
	private String label;
	private String documentation;

	public GeneratorParameter(String key, String label, String value, String documentation) {
		this.key = key;
		this.label = label;
		this.value = value;
		this.documentation = documentation;
	}

	public GeneratorParameter(IConfigurationElement confParam, Generator g) {
		this.key = confParam.getAttribute("key");
		this.label = confParam.getAttribute("label");
		this.value = null;
		this.documentation = confParam.getAttribute("documentation");
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

	public int compareTo(GeneratorParameter o) {
		return label.compareTo(o.getLabel());
	}
}
