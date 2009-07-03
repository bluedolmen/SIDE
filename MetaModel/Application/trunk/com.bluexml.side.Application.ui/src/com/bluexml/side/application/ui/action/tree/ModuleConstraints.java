package com.bluexml.side.application.ui.action.tree;

import org.eclipse.core.runtime.IConfigurationElement;

public class ModuleConstraints {
	String id;
	String versionNumMin;
	String versionNumMax;
	private TreeNode parent;

	public ModuleConstraints(String id) {
		this.id = id;
	}

	public ModuleConstraints(IConfigurationElement config, TreeNode parent) {
		this.parent = parent;
		this.id = config.getAttribute("moduleId");
		this.versionNumMin = config.getAttribute("versionMin");
		this.versionNumMax = config.getAttribute("versionMax");
	}
}
