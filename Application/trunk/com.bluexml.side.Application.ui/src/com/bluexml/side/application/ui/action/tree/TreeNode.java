package com.bluexml.side.application.ui.action.tree;

import java.util.Collection;

public abstract class TreeNode extends TreeElement {
	protected String id;
	public abstract Collection<TreeNode> getChildren();
	public String getId() {
		return id;
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
		for (TreeNode c: getChildren()) {
			if (tn.equals(c.getId())) {
				return c;
			}
		}
		return null;
	}
	
	
}
