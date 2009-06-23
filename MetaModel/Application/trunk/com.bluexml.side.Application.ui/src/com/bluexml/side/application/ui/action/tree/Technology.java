package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

public class Technology extends TreeNode {

	
	private String label;
	private String url;
	private Set<TreeNode> versions;

	public Technology(IConfigurationElement elt, Metamodel m) {
		parent = m;
		id = elt.getAttribute("id");
		label = elt.getAttribute("name");
		url = elt.getAttribute("url");
		description = elt.getAttribute("description");
		versions = new HashSet<TreeNode>();
	}

	public String getURL() {
		return url;
	}

	public String getDescription() {
		return description;
	}	

	public String getLabel() {
		return label;
	}
	
	public Metamodel getMetamodel() {
		return (Metamodel)parent;
	}

	

	public void addTechnologyVersion(TechnologyVersion technologyVersion) {
		versions.add(technologyVersion);
	}

	public Set<TreeNode> getChildren() {
		return versions;
	}

	@Override
	public void addChildren(TreeNode child) {
		versions.add(child);
	}

}