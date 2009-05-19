package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.action.ApplicationDialog;

public class Deployer extends ImplNode {

	public Deployer(IConfigurationElement elt, TechnologyVersion tv) {
		parent = tv;
		id = elt.getAttribute("id");
		version = elt.getAttribute("version");
		launchClass = elt.getAttribute("class");
		options = new HashSet<TreeNode>();
	}
	
	public void updateApplication() {
		if (!ApplicationDialog.loadingTree) {
			ApplicationDialog.modificationMade();
			Configuration config = ApplicationDialog.getCurrentConfiguration();
			if (config != null) {
				// Delete all linked elements
				Set<ComponantConfiguration> elts = new HashSet<ComponantConfiguration>();
				for (ComponantConfiguration elt : config.getDeployerConfigurations()) {
					if (elt.getId_techno_version().equals(parent.getId()))
						elts.add(elt);
				}
				config.getDeployerConfigurations().removeAll(elts);

				// Add the new element
				if (isChecked() && isEnabled()) {
					
					DeployerConfiguration elt = ApplicationFactory.eINSTANCE.createDeployerConfiguration();
					elt.setId(getId());
					elt.setId_techno_version(parent.getId());
					elt.setImpl_class(getLaunchClass());

					// Launch options
					for (TreeNode tn : options) {
						OptionComponant o = (OptionComponant)tn;
						if (o.isChecked() && o.isEnabled()) {
							Option opt = ApplicationFactory.eINSTANCE.createOption();
							opt.setKey(o.getKey());
							elt.getOptions().add(opt);
						}
					}

					
					config.getDeployerConfigurations().add(elt);
				}
			}
		}
	}

	@Override
	public void addChildren(TreeNode child) {
		options.add(child);
	}


}