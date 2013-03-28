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
import java.util.Collection;
import java.util.List;

import com.bluexml.side.util.dependencies.ModuleConstraint;
public abstract class TreeNode extends TreeElement {
	
	protected String id;
	protected TreeNode parent;
	protected boolean toHidde = false;

	public boolean isToHidde() {
		return toHidde;
	}

	public void setToHidde(boolean toHidde) {
		this.toHidde = toHidde;
	}

	protected List<CheckConstraints> mustbechecked = new ArrayList<CheckConstraints>();
	protected List<CheckConstraints> mustbeUnchecked = new ArrayList<CheckConstraints>();
	protected List<ModuleConstraint> integrationModules = new ArrayList<ModuleConstraint>();

	public List<ModuleConstraint> getIntegrationModules() {
		return integrationModules;
	}

	public abstract Collection<TreeNode> getChildren();

	public abstract void addChildren(TreeNode child);

	public TreeNode(TreeView root) {
		super(root);
	}

	public String getId() {
		return id;
	}

	/**
	 * Return a full qualified ID
	 *
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
			for (TreeNode c : getChildren()) {
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

	public List<CheckConstraints> getMustbechecked() {
		return mustbechecked;
	}

	public List<CheckConstraints> getMustbeUnchecked() {
		return mustbeUnchecked;
	}
	
	public String toString() {
		return getFullId("#");
	}
}
