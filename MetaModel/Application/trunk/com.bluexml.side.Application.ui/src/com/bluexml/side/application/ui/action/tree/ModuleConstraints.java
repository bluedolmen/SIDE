package com.bluexml.side.application.ui.action.tree;

import org.eclipse.core.runtime.IConfigurationElement;

public class ModuleConstraints {
	public String getId() {
		return id;
	}

	public String getVersionNumMin() {
		return versionNumMin;
	}

	public String getVersionNumMax() {
		return versionNumMax;
	}

	String id;
	String versionNumMin;
	String versionNumMax;
	

	public ModuleConstraints(String id) {
		this.id = id;
	}

	public ModuleConstraints(IConfigurationElement config, TreeNode parent) {
		this.id = config.getAttribute("moduleId");
		this.versionNumMin = config.getAttribute("versionMin");
		this.versionNumMax = config.getAttribute("versionMax");
	}
}
