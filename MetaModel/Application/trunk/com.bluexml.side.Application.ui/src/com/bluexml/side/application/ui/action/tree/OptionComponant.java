package com.bluexml.side.application.ui.action.tree;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IConfigurationElement;

public abstract class OptionComponant extends TreeNode implements Comparable<OptionComponant> {

	private String key;
	private String label;
	private String description;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(OptionComponant o) {
		return this.getLabel().compareTo(o.getLabel());
	}

//	public boolean equals(Object o) {
//		return (o instanceof OptionComponant) && ((OptionComponant) o).getId().equals(this.getId());
//	}

	private boolean isDefault = false;

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public OptionComponant(IConfigurationElement elt, ImplNode implNode, TreeView root) {
		super(root);
		root.addOption(this);
		parent = (TreeNode) implNode;
		id = parent.id+"_"+elt.getAttribute("key");
		key = elt.getAttribute("key");
		label = elt.getAttribute("label");
		if (elt.getAttribute("defaultOption") != null) {
			isDefault = Boolean.parseBoolean(elt.getAttribute("defaultOption"));
		}
		description = elt.getAttribute("documentation");
		mustbechecked = new ArrayList<CheckConstraints>();
		mustbeUnchecked = new ArrayList<CheckConstraints>();
		for (IConfigurationElement child : elt.getChildren()) {
			if (child.getName().equalsIgnoreCase("mustBeChecked")) {
				mustbechecked.add(new CheckConstraints(child, this));
			}
			if (child.getName().equalsIgnoreCase("mustBeUnChecked")) {
				mustbeUnchecked.add(new CheckConstraints(child, this));
			}
			if (child.getName().equalsIgnoreCase("moduleDependence")) {
				integrationModules.add(new ModuleConstraint(child, this));
			}
		}

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
		((ImplNode) parent).updateApplication();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		((ImplNode) parent).updateApplication();
	}

	public Set<TreeNode> getChildren() {
		return new TreeSet<TreeNode>();
	}

	
}
