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

import org.eclipse.core.runtime.IConfigurationElement;

public class CheckConstraints {
	private String pluginsId;
	private List<String> optionsIds = new ArrayList<String>();
	private TreeNode parent;

	public String getPluginsId() {
		return pluginsId;
	}

	public void setPluginsId(String pluginsId) {
		this.pluginsId = pluginsId;
	}

	public List<String> getOptionsIds() {
		return optionsIds;
	}

	public void setOptionsIds(List<String> optionsIds) {
		this.optionsIds = optionsIds;
	}

	public Object getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public CheckConstraints(IConfigurationElement config, TreeNode parent) {
		this.parent = parent;
		this.pluginsId = config.getAttribute("pluginId");
		for (IConfigurationElement child : config.getChildren()) {
			if (child.getName().equalsIgnoreCase("optionRef")) {
				String op = child.getAttribute("optionId");
				String id=pluginsId+"_"+op;
				optionsIds.add(id);
			}
		}
	}

}
