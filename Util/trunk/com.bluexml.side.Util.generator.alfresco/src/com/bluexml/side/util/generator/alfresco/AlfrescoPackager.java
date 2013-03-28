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
package com.bluexml.side.util.generator.alfresco;

import java.util.Properties;

import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.generator.packager.AbstractMultiPackager;
import com.bluexml.side.util.generator.packager.WarPatchPackager;

public class AlfrescoPackager extends AbstractMultiPackager {

	public AlfrescoPackager(IFolder folder, Properties moduleProperties, String technoV) throws Exception {
		super(folder, moduleProperties, technoV);		
		// construct all packager

		IFolder techVFolder = ((IFolder) folder.getParent().getParent()).getFolder(technoV);

		// AMP packager
		AmpPackager ampP = new AmpPackager(folder, moduleProperties, techVFolder);
		
		addPackager("amp", ampP);

		WarPatchPackager wpackager = new WarPatchPackager(folder, moduleProperties.getProperty("module.id"), techVFolder, "share");
		addPackager("wpackager", wpackager);
	}

}
