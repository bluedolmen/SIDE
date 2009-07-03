package com.bluexml.side.application.ui.action.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

public class Metamodel extends TreeNode {

	private String label;
	private String url;
	private List<TreeNode> technology;
	private String description;

	public Metamodel(IConfigurationElement elt, TreeView root) {
		super(root);
		parent = null;
		id = elt.getAttribute("id");
		label = elt.getAttribute("name");
		url = elt.getAttribute("url");
		description = elt.getAttribute("description");
		technology = new ArrayList<TreeNode>();

		setEnabled(true);
	}

	public String getURL() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public Metamodel(String id, TreeView root) {
		super(root);
		this.id = id;
		label = "";
		technology = new ArrayList<TreeNode>();
	}

	public Metamodel(String id, String label, TreeView root) {
		super(root);
		this.id = id;
		this.label = label;
		technology = new ArrayList<TreeNode>();
	}

	public Collection<TreeNode> getChildren() {
		return technology;
	}

	public String getLabel() {
		return label;
	}

	public String getId() {
		return id;
	}

	public void addTechnology(Technology t) {
		this.technology.add(t);
	}

	@Override
	public void addChildren(TreeNode child) {
		technology.add(child);
	}

}