package com.bluexml.side.application.ui.action.tree;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.ModuleConstraint;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

public class Generator extends ImplNode {

	public Generator(IConfigurationElement elt, TechnologyVersion tv, TreeView root) {
		super(elt, tv, root);
	}

	@Override
	public void updateApplication() {
		System.out.println("Generator.updateApplication()");
		if (!ApplicationDialog.loadingTree) {
			ApplicationDialog.modificationMade();
			Configuration config = ApplicationDialog.getCurrentConfiguration();
			if (config != null) {
				// Remove element
				ApplicationUtil.deleteGeneratorFromConf(config, this);

				// Add the new element
				if (isChecked() && isEnabled()) {
					GeneratorConfiguration elt = ApplicationFactory.eINSTANCE.createGeneratorConfiguration();
					System.out.println("Generator.updateApplication() " + getId());
					elt.setId(getId());
					elt.setId_techno_version(parent.getId());
					elt.setImpl_class(getLaunchClass());
					elt.setContributorId(getContributorId());
					elt.setId_metamodel(parent.getParent().getParent().getId());
					elt.setMetaModelName(((Metamodel) parent.getParent().getParent()).getLabel());
					elt.setTechnologyName(((Technology) parent.getParent()).getLabel());
					elt.setTechnologyVersionName(((TechnologyVersion) parent).getVersion());
					elt.setGeneratorName(this.getVersion());

					for (com.bluexml.side.util.dependencies.ModuleConstraint module : integrationModules) {
						ModuleConstraint mc = ApplicationFactory.eINSTANCE.createModuleConstraint();
						mc.setModuleId(module.getModuleId());
						if (module.getVersionMin() != null) {
							mc.setVersionMin(module.getVersionMin().toString());
						}
						if (module.getVersionMax() != null) {
							mc.setVersionMax(module.getVersionMax().toString());
						}
						mc.setModuleType(module.getModuleType());
						mc.setTechnologyVersion(module.getTech_version());
						elt.getModuleContraints().add(mc);
					}

					// Launch options
					for (TreeNode tn : options) {
						System.out.println("Generator.updateApplication() option :" + tn);
						OptionComponant o = (OptionComponant) tn;
						String key = o.getKey();
						if (key.endsWith("main")) {
							System.out.println(key);
							System.out.println("checked " + o.isChecked());
							System.out.println("enabled " + o.isEnabled());
						}
						if (o.isChecked() && o.isEnabled()) {
							Option opt = ApplicationFactory.eINSTANCE.createOption();
							opt.setKey(key);
							elt.getOptions().add(opt);

							for (com.bluexml.side.util.dependencies.ModuleConstraint module : o.getIntegrationModules()) {
								ModuleConstraint mc = ApplicationFactory.eINSTANCE.createModuleConstraint();
								mc.setModuleId(module.getModuleId());
								if (module.getVersionMin() != null) {
									mc.setVersionMin(module.getVersionMin().toString());
								}
								if (module.getVersionMax() != null) {
									mc.setVersionMax(module.getVersionMax().toString());
								}
								mc.setModuleType(module.getModuleType());
								mc.setTechnologyVersion(module.getTech_version());
								elt.getModuleContraints().add(mc);
							}
						}
					}

					config.getGeneratorConfigurations().add(elt);
				}
			}
		}
	}

}