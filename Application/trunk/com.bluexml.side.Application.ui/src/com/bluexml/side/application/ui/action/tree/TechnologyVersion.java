package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

public class TechnologyVersion extends TreeElement {
	private String id;
	private String version;
	private Technology technology;
	private Set<Generator> generator;

	public TechnologyVersion(IConfigurationElement elt, Technology t) {
		technology = t;
		technology.addTechnologyVersion(this);
		id = elt.getAttribute("id");
		version = elt.getAttribute("version");
		generator = new HashSet<Generator>();
	}

	public String getVersion() {
		return version;
	}

	public Set<Generator> getGenerator() {
		return generator;
	}

	public void addGenerator(Generator g) {
		generator.add(g);
	}

	public String getId() {
		return id;
	}

	public Technology getTechnology() {
		return technology;
	}
}