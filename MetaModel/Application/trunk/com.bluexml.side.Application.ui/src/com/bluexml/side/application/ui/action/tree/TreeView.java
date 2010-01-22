package com.bluexml.side.application.ui.action.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

public class TreeView extends TreeViewer {

	List<TreeNode> allOptions = new ArrayList<TreeNode>();

	public TreeView(Composite parent, int style) {
		super(parent, style);
	}

	public void addOption(TreeNode op) {
		allOptions.add(op);
	}

	public TreeItem getTreeItemOf(TreeElement o) {
		return ((TreeItem) this.findItem(o));
	}

	public TreeItem getOptionTreeItemById(String id) {
//		System.out.println("*** SEARCH *** :"+id);
		for (TreeNode op : allOptions) {
//			System.out.println("## currentNode ##"+op.id);
			if (op.getId().equals(id)) {
				return getTreeItemOf(op);
			}
		}
		return null;
	}
}
