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

import org.eclipse.core.runtime.IConfigurationElement;

public class ModuleConstraint extends com.bluexml.side.util.dependencies.ModuleConstraint {

	public ModuleConstraint(IConfigurationElement config, TreeNode parent) {
		super(config.getAttribute("moduleId"), config.getAttribute("technologyVersion"), config.getAttribute("moduleType"), config.getAttribute("versionMin"), config.getAttribute("versionMax"));
	}

}
