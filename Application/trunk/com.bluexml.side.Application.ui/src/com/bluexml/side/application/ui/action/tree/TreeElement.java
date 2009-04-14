package com.bluexml.side.application.ui.action.tree;


public class TreeElement {

	private boolean checked;
	private boolean enabled;
	
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
