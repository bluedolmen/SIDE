/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
