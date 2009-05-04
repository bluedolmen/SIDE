package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.action.ApplicationDialog;

public class Generator extends ImplNode {

	public Generator(IConfigurationElement elt, TechnologyVersion tv) {
		technologyVersion = tv;
		technologyVersion.addGenerator(this);
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
				for (GeneratorConfiguration elt : config.getGeneratorConfigurations()) {
					if (elt.getId_metamodel().equals(technologyVersion.getTechnology().getMetamodel().getId())) {
						elts.add(elt);
					}
				}
				config.getGeneratorConfigurations().removeAll(elts);

				// Add the new element
				if (isChecked() && isEnabled()) {
					GeneratorConfiguration elt = ApplicationFactory.eINSTANCE.createGeneratorConfiguration();
					elt.setId(getId());
					elt.setId_techno_version(technologyVersion.getId());
					elt.setImpl_class(getLaunchClass());
					elt.setId_metamodel(technologyVersion.getTechnology().getMetamodel().getId());
					// Launch options
					for (TreeNode tn : options) {
						OptionComponant o = (OptionComponant) tn;
						if (o.isChecked() && o.isEnabled()) {
							Option opt = ApplicationFactory.eINSTANCE.createOption();
							opt.setKey(o.getKey());
							elt.getOptions().add(opt);
						}
					}

					config.getGeneratorConfigurations().add(elt);
				}
			}
		}
	}

}