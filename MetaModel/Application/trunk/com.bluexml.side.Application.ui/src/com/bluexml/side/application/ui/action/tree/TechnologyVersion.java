package com.bluexml.side.application.ui.action.tree;

import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IConfigurationElement;

public class TechnologyVersion extends TreeNode implements Comparable<TechnologyVersion> {
	private String id;
	private String version;
	private Set<Generator> generator = new TreeSet<Generator>();
	private Set<Deployer> deployer = new TreeSet<Deployer>();

	public TechnologyVersion(IConfigurationElement elt, Technology t, TreeView root) {
		super(root);
		parent = t;
		id = elt.getAttribute("id");
		version = elt.getAttribute("version");
		description = elt.getAttribute("description");
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
		Set<TreeNode> childrens = new TreeSet<TreeNode>();
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
		} else if (child instanceof Deployer) {
			getDeployer().add((Deployer) child);
		}
	}

	public int compareTo(TechnologyVersion o) {
		return this.getVersion().compareTo(o.getVersion());
	}

//	public boolean equals(Object o) {
//		return (o instanceof TechnologyVersion) && ((TechnologyVersion) o).getId().equals(this.getId());
//	}
}