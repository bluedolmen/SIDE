package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

public abstract class OptionComponant  extends TreeNode {
	
	private String key;
	private String label;
	private String description;

	public OptionComponant(IConfigurationElement elt,ImplNode implNode) {
		parent = (TreeNode)implNode;
		id = elt.getAttribute("key");
		key = elt.getAttribute("key");
		label = elt.getAttribute("label");
		description = elt.getAttribute("documentation");
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
		((ImplNode)parent).updateApplication();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		((ImplNode)parent).updateApplication();
	}
	public Set<TreeNode> getChildren() {
		return new HashSet<TreeNode>();
	}
	
	public void addChildren() {
		
	}
}
