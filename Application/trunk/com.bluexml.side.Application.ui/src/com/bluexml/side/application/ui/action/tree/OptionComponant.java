package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

public abstract class OptionComponant  extends TreeNode {
	
	private String key;
	private String label;
	private String description;
	ImplNode impl;

	public OptionComponant(IConfigurationElement elt,ImplNode implNode) {
		parent = (TreeNode)implNode;
		key = elt.getAttribute("key");
		label = elt.getAttribute("label");
		description = elt.getAttribute("documentation");
		impl = implNode;
		impl.addOption(this);
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
		impl.updateApplication();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		impl.updateApplication();
	}
	public Set<TreeNode> getChildren() {
		// TODO Auto-generated method stub
		return new HashSet<TreeNode>();
	}
}
