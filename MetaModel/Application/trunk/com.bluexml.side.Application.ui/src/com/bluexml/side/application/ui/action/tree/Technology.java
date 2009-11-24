package com.bluexml.side.application.ui.action.tree;

import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IConfigurationElement;

public class Technology extends TreeNode implements Comparable<Technology> {

	private String label;
	private String url;
	private Set<TreeNode> versions = new TreeSet<TreeNode>();

	public Technology(IConfigurationElement elt, Metamodel m, TreeView root) {
		super(root);
		parent = m;
		id = elt.getAttribute("id");
		label = elt.getAttribute("name");
		url = elt.getAttribute("url");
		description = elt.getAttribute("description");		
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
		return (Metamodel) parent;
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

	public int compareTo(Technology o) {
		return this.getLabel().compareTo(o.getLabel());
	}
	
	public boolean equals(Object o) {
		return (o instanceof Technology) && ((Technology)o).getId().equals(this.getId());
	}

}