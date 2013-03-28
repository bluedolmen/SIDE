/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.application.ui.action.tree;

import java.util.Set;

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
		System.out.println("Generator.updateApplication() " + getId());
		if (!ApplicationDialog.loadingTree) {
			ApplicationDialog.modificationMade();
			Configuration config = ApplicationDialog.getCurrentConfiguration();
			boolean configurationsForGeneratorExists = ApplicationUtil.getConfigurationsForGenerator(config, this).size() > 0;
			System.out.println("Generator.updateApplication() conf exists ?" + configurationsForGeneratorExists);
			boolean customModuleGenerator = ApplicationUtil.isCustomModuleGenerator(getId());
			if (config != null && (!customModuleGenerator || (customModuleGenerator && !configurationsForGeneratorExists))) {

				// Remove element
				ApplicationUtil.deleteGeneratorFromConf(config, this);

				// Add the new element
				if (isChecked() && isEnabled()) {
					GeneratorConfiguration elt = ApplicationFactory.eINSTANCE.createGeneratorConfiguration();
					System.out.println("Generator.updateApplication() addToConfiguration" + getId());
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
			} else {
				System.out.println("Generator.updateApplication() Is Custom Module Generator");
			}

		}
	}

}
