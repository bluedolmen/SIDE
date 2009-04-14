package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

public class Technology extends TreeElement {

	private String id;
	private String label;
	private String url;
	private String description;
	private Metamodel parent;
	private Set<TechnologyVersion> versions;

	public Technology(IConfigurationElement elt, Metamodel m) {
		parent = m;
		parent.addTechnology(this);
		id = elt.getAttribute("id");
		label = elt.getAttribute("name");
		url = elt.getAttribute("url");
		description = elt.getAttribute("description");
		versions = new HashSet<TechnologyVersion>();
	}

	public String getURL() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public Set<TechnologyVersion> getTechnologyVersion() {
		return versions;
	}

	public String getLabel() {
		return label;
	}
	
	public Metamodel getMetamodel() {
		return parent;
	}

	public String getId() {
		return id;
	}

	public void addTechnologyVersion(TechnologyVersion technologyVersion) {
		versions.add(technologyVersion);
	}

}