package com.bluexml.side.application.ui.action.tree;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationElement;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.action.ApplicationDialog;

public class Generator extends TreeElement {
	private String id;
	private String version;
	private TechnologyVersion technologyVersion;
	private Set<OptionGenerator> options;

	public Generator(IConfigurationElement elt, Collection<TechnologyVersion> technologyVersionSet) {
		technologyVersion = searchTechnologyVersion(elt
				.getAttribute("idTechnologyVersion"), technologyVersionSet);
		technologyVersion.addGenerator(this);
		id = elt.getAttribute("id");
		version = elt.getAttribute("version");
		options = new HashSet<OptionGenerator>();
	}

	public TechnologyVersion getTechnologyVersion() {
		return technologyVersion;
	}

	public String getVersion() {
		return version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private TechnologyVersion searchTechnologyVersion(
			String idTechnologyVersion, Collection<TechnologyVersion> technologyVersionSet) {
		for (TechnologyVersion tv : technologyVersionSet)
			if (tv.getId().equalsIgnoreCase(idTechnologyVersion))
				return tv;
		return null;
	}
	
	@Override
	public void setChecked(boolean checked) {
		super.setChecked(checked);
		updateApplication();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		updateApplication();
	}

	public void updateApplication() {
		if (!ApplicationDialog.loadingTree) {
			Configuration config = ApplicationDialog.getCurrentConfiguration();
			if (config != null) {
				//Delete all linked elements
				Set<ConfigurationElement> elts = new HashSet<ConfigurationElement>();
				for (ConfigurationElement elt : config.getElements()) {
					if (elt.getId_metamodel().equals(technologyVersion.getTechnology().getMetamodel().getId()))
						elts.add(elt);
				}
				config.getElements().removeAll(elts);
				
				//Add the new element
				if (isChecked() && isEnabled()) {
					ConfigurationElement elt = ApplicationFactory.eINSTANCE.createConfigurationElement();
					elt.setId_generator(getId());
					elt.setId_metamodel(technologyVersion.getTechnology().getMetamodel().getId());
					
					//Launch options
					for (OptionGenerator o : options) {
						if (o.isChecked() && o.isEnabled()) {
							Option opt = ApplicationFactory.eINSTANCE.createOption();
							opt.setKey(o.getKey());
							elt.getOptions().add(opt);
						}
					}
					
					config.getElements().add(elt);
				}
			}
		}
	}

	public Set<OptionGenerator> getOptions() {
		return options;
	}

	public void addOption(OptionGenerator optionGenerator) {
		options.add(optionGenerator);
	}
}