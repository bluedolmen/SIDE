package com.bluexml.side.application.ui.action.tree;

import java.util.Collection;
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

	public Technology(IConfigurationElement elt, Collection<Metamodel> metamodelSet) {
		parent = searchMetamodel(elt.getAttribute("idMetamodel"),metamodelSet);
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

	private Metamodel searchMetamodel(String idMetamodel, Collection<Metamodel> metamodelSet) {
		for (Metamodel m : metamodelSet)
			if (m.getId().equalsIgnoreCase(idMetamodel))
				return m;
		return null;
	}

	public String getId() {
		return id;
	}

	public void addTechnologyVersion(TechnologyVersion technologyVersion) {
		versions.add(technologyVersion);
	}

}