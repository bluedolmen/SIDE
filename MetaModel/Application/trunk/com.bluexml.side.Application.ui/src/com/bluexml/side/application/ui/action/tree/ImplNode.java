package com.bluexml.side.application.ui.action.tree;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IConfigurationElement;
/**
 * this class match to all Plugins that implements extension for Generator or Deployer and other plugins like thats
 * @author davidabad
 *
 */
public abstract class ImplNode extends TreeNode implements Comparable<ImplNode> { 
	protected String version;
	protected String launchClass;
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ImplNode o) {
		return this.getVersion().compareTo(o.getVersion());
	}

	protected String contributorId;

	protected Set<TreeNode> options = new TreeSet<TreeNode>();

	public ImplNode(IConfigurationElement elt, TechnologyVersion tv,TreeView root) {
		super(root);
		root.addOption(this);
		// set ImplNode attribute
		parent = (TreeNode) tv;
		id = elt.getAttribute("id");
		version = elt.getAttribute("version");
		launchClass = elt.getAttribute("class");
		contributorId = elt.getContributor().getName();
		description = elt.getAttribute("description");
		
		
		for (IConfigurationElement child : elt.getChildren()) {
			if (child.getName().equalsIgnoreCase("mustBeChecked")) {
				mustbechecked.add(new CheckConstraints(child,this));
			}
			if (child.getName().equalsIgnoreCase("mustBeUnChecked")) {
				mustbeUnchecked.add(new CheckConstraints(child,this));
			}
			if (child.getName().equalsIgnoreCase("moduleDependence")) {
				integrationModules.add(new ModuleConstraint(child,this));
			}
		}
	}


	@Override
	public void setChecked(boolean checked) {
		super.setChecked(checked);
		updateApplication();
	}

	public String getContributorId() {
		return contributorId;
	}
	
	
	public void setContributorId(String contributorId) {
		this.contributorId = contributorId;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		updateApplication();
	}
	public abstract void updateApplication();

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLaunchClass() {
		return launchClass;
	}

	public void setLaunchClass(String launchClass) {
		this.launchClass = launchClass;
	}

	public void addOption(OptionComponant option) {
		options.add(option);
	}

	public Collection<TreeNode> getChildren() {
		return options;
	}
	
//	public boolean equals(Object o) {
//		return (o instanceof ImplNode) && ((ImplNode)o).getId().equals(this.getId());
//	}
}
