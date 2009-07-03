package com.bluexml.side.application.ui.action.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

public class MustBechecked {
	private String pluginsId;
	private List<String> optionsIds = new ArrayList<String>();
	private Object parent;

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

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public MustBechecked(IConfigurationElement config, Object parent) {
		this.parent = parent;
		this.pluginsId = config.getAttribute("pluginId");
		for (IConfigurationElement child : config.getChildren()) {
			if (child.getName().equalsIgnoreCase("optionRef")) {
				String op = child.getAttribute("optionId");
				optionsIds.add(op);
			}
		}
	}

}
