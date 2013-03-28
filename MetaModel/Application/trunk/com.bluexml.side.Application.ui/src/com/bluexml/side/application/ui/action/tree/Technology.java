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
	
//	public boolean equals(Object o) {
//		return (o instanceof Technology) && ((Technology)o).getId().equals(this.getId());
//	}

}
