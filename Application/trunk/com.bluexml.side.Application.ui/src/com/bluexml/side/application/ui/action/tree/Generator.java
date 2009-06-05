package com.bluexml.side.application.ui.action.tree;

import java.util.HashSet;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

public class Generator extends ImplNode {

	public Generator(IConfigurationElement elt, TechnologyVersion tv) {
		parent = (TreeNode)tv;
		id = elt.getAttribute("id");
		version = elt.getAttribute("version");
		launchClass = elt.getAttribute("class");
		description = elt.getAttribute("description");
		options = new HashSet<TreeNode>();
	}

	public void updateApplication() {
		if (!ApplicationDialog.loadingTree) {
			ApplicationDialog.modificationMade();
			Configuration config = ApplicationDialog.getCurrentConfiguration();
			if (config != null) {
				// Remove element
				ApplicationUtil.deleteGeneratorFromConf(config,this);
				
				// Add the new element
				if (isChecked() && isEnabled()) {
					GeneratorConfiguration elt = ApplicationFactory.eINSTANCE.createGeneratorConfiguration();
					elt.setId(getId());
					elt.setId_techno_version(parent.getId());
					elt.setImpl_class(getLaunchClass());
					elt.setId_metamodel(parent.getParent().getParent().getId());
					elt.setMetaModelName(((Metamodel)parent.getParent().getParent()).getLabel());
					elt.setTechnologyName(((Technology)parent.getParent()).getLabel());
					elt.setTechnologyVersionName(((TechnologyVersion)parent).getVersion());
					elt.setGeneratorName(this.getVersion());
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

	@Override
	public void addChildren(TreeNode child) {
		options.add(child);
	}

}