package com.bluexml.side.application.ui.action.tree;


public class TreeElement {

	protected boolean checked;
	protected boolean enabled;
	protected String description;
	
	public TreeElement() {
		checked = false;
		enabled = false;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
