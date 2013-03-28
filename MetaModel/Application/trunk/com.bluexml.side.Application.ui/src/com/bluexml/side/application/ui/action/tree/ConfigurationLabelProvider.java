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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import com.bluexml.side.application.ui.action.ApplicationDialog;

public class ConfigurationLabelProvider implements ILabelProvider {

	private List<Object> listeners;

	/**
	 * Constructs a InterpretationLabelProvider
	 */
	public ConfigurationLabelProvider() {
		listeners = new ArrayList<Object>();
	}

	public Image getImage(Object object) {
		String suffix = "";
		if (object instanceof TreeElement) {
			TreeElement te = (TreeElement) object;
			if (te.isEnabled())
				suffix += "_enabled";
			else
				suffix += "_disabled";
			if (te.isChecked())
				suffix += "_checked";
			else
				suffix += "_unchecked";
		}

		if (object instanceof Metamodel) {
			return new Image(null, ApplicationDialog.class.getResourceAsStream("tree/img/metamodel/metamodel" + suffix + ".png"));
		} else if (object instanceof Technology) {
			return new Image(null, ApplicationDialog.class.getResourceAsStream("tree/img/technology/technology" + suffix + ".png"));
		} else if (object instanceof TechnologyVersion) {
			return new Image(null, ApplicationDialog.class.getResourceAsStream("tree/img/technologyVersion/technologyVersion" + suffix + ".png"));
		} else if (object instanceof Generator) {
			return new Image(null, ApplicationDialog.class.getResourceAsStream("tree/img/generator/generator" + suffix + ".png"));
		} else if (object instanceof OptionGenerator) {
			return new Image(null, ApplicationDialog.class.getResourceAsStream("tree/img/optionGenerator/options" + suffix + ".png"));
		} else if (object instanceof Deployer) {
			return new Image(null, ApplicationDialog.class.getResourceAsStream("tree/img/deployer/deployer" + suffix + ".png"));
		} else if (object instanceof OptionDeployer) {
			return new Image(null, ApplicationDialog.class.getResourceAsStream("tree/img/optionDeployer/options" + suffix + ".png"));
		}
		return null;
	}

	public String getText(Object object) {
		if (object instanceof IConfigurationElement) {
			IConfigurationElement elt = (IConfigurationElement) object;
			return elt.getAttribute("label");
		} else if (object instanceof Metamodel) {
			Metamodel elt = (Metamodel) object;
			return elt.getLabel();
		} else if (object instanceof Technology) {
			Technology elt = (Technology) object;
			return elt.getLabel();
		} else if (object instanceof TechnologyVersion) {
			TechnologyVersion elt = (TechnologyVersion) object;
			return elt.getVersion();
		} else if (object instanceof Generator) {
			Generator elt = (Generator) object;
			return elt.getVersion();
		} else if (object instanceof OptionGenerator) {
			OptionGenerator elt = (OptionGenerator) object;
			return elt.getLabel();
		} else if (object instanceof Deployer) {
			Deployer elt = (Deployer) object;
			return elt.getVersion();
		} else if (object instanceof OptionDeployer) {
			OptionDeployer elt = (OptionDeployer) object;
			return elt.getLabel();
		}

		return "";
	}

	public void addListener(ILabelProviderListener arg0) {
		listeners.add(arg0);
	}

	public boolean isLabelProperty(Object arg0, String arg1) {
		return false;
	}

	public void removeListener(ILabelProviderListener arg0) {
		listeners.remove(arg0);
	}

	public void dispose() {
	}

}
