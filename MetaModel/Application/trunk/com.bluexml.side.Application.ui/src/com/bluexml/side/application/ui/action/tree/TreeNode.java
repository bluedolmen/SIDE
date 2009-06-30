package com.bluexml.side.application.ui.action.tree;

import java.util.Collection;

public abstract class TreeNode extends TreeElement {
	protected String id;
	protected TreeNode parent;
	
	public abstract Collection<TreeNode> getChildren();
	public abstract void addChildren(TreeNode child);
	
	
	public TreeNode(){
		
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * Return a full qualified ID
	 * @return
	 */
	public String getFullId() {
		return getFullId(".");
	}
	
	public String getFullId(String separator) {
		String name = this.getId();
		name = getFullId(separator, this.parent) + name;
		return name;
	}
	
	private String getFullId(String separator, TreeNode node) {
		String name = "";
		if (node != null) {
			name = node.getId() + separator;
			name = getFullId(separator, node.parent) + name;
		}
		return name;
	}
	
	public boolean contains(TreeNode tn) {		
		return contains(tn.getId());		
	}
	public boolean contains(String tn) {
		TreeNode child = getChild(tn);
		if (child == null) {
			return false;
		}
		return true;
	}
	public TreeNode getChild(String tn) {
		if (tn != null) {
			for (TreeNode c: getChildren()) {
				if (tn.equals(c.getId())) {
					return c;
				}
			}
		}
		return null;
	}
	
	public TreeNode getParent() {
		return parent;
	}
}
