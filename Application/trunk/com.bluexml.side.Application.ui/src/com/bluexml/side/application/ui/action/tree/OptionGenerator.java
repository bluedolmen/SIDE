package com.bluexml.side.application.ui.action.tree;

import org.eclipse.core.runtime.IConfigurationElement;

public class OptionGenerator extends TreeElement {
	
	private String key;
	private String label;
	private String description;
	private Generator generator;

	public OptionGenerator(IConfigurationElement elt, Generator g) {
		key = elt.getAttribute("key");
		label = elt.getAttribute("label");
		description = elt.getAttribute("documentation");
		generator = g;
		generator.addOption(this);
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public void setChecked(boolean checked) {
		super.setChecked(checked);
		generator.updateApplication();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		generator.updateApplication();
	}
}
