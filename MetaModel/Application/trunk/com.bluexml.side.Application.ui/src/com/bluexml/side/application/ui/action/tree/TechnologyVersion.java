package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

public class TechnologyVersion extends TreeNode {
	private String id;
	private String version;
	private Set<Generator> generator;
	private Set<Deployer> deployer;

	public TechnologyVersion(IConfigurationElement elt, Technology t) {
		parent = t;
		id = elt.getAttribute("id");
		version = elt.getAttribute("version");
		description = elt.getAttribute("description");
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
		Set<Generator> generators = getGenerator();
		if (generators.size() > 0) {
			childrens.addAll(generators);
		}
		Set<Deployer> deployers = getDeployer();
		if (deployers.size() > 0) {
			childrens.addAll(deployers);
		}
		return childrens;
	}

	public String getId() {
		return id;
	}

	@Override
	public void addChildren(TreeNode child) {
		if (child instanceof Generator) {
			getGenerator().add((Generator) child);
		} else if (child instanceof Deployer){
			getDeployer().add((Deployer) child);
		}
	}
}