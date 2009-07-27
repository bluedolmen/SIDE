package com.bluexml.side.application.ui.action.tree;

import org.eclipse.core.runtime.IConfigurationElement;

public class ModuleConstraint extends com.bluexml.side.util.dependencies.ModuleConstraint {

	public ModuleConstraint(IConfigurationElement config, TreeNode parent) {
		super(config.getAttribute("moduleId"), config.getAttribute("technologyVersion"), config.getAttribute("moduleType"), config.getAttribute("versionMin"), config.getAttribute("versionMax"));
	}

}
