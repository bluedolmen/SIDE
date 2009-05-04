package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

public class TechnologyVersion extends TreeNode {
	private String id;
	private String version;
	private Technology technology;
	private Set<Generator> generator;
	private Set<Deployer> deployer;

	public TechnologyVersion(IConfigurationElement elt, Technology t) {
		technology = t;
		technology.addTechnologyVersion(this);
		id = elt.getAttribute("id");
		version = elt.getAttribute("version");
		generator = new HashSet<Generator>();
		deployer = new HashSet<Deployer>();
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

	public Set<Deployer> getDeployer() {
		return deployer;
	}

	public void addDeployer(Deployer d) {
		deployer.add(d);
	}
	
	public Set<TreeNode> getChildren() {
		Set<TreeNode> childrens = new HashSet<TreeNode>();
		childrens.addAll(getGenerator());
		childrens.addAll(getDeployer());		
		return childrens;
	}

	public String getId() {
		return id;
	}

	public Technology getTechnology() {
		return technology;
	}
}