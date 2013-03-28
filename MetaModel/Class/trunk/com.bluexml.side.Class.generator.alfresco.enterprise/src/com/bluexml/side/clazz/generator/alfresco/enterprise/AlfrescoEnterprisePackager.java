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
package com.bluexml.side.clazz.generator.alfresco.enterprise;

import java.util.Properties;

import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.generator.alfresco.AlfrescoPackager;
import com.bluexml.side.util.generator.packager.JavaProjectPackager;
import com.bluexml.side.util.generator.packager.WarPatchPackager;

public class AlfrescoEnterprisePackager extends AlfrescoPackager {

	public AlfrescoEnterprisePackager(IFolder folder, Properties moduleProperties, String technoV) throws Exception {
		super(folder, moduleProperties, technoV);

		IFolder techVFolder = ((IFolder) folder.getParent().getParent()).getFolder(technoV + "_generated_api");
		JavaProjectPackager jpp = new JavaProjectPackager(folder, techVFolder, moduleProperties.getProperty("module.id"), "modelAPI");
		addPackager("JavaProjectPackager", jpp);
		JavaProjectPackager jpp2 = new JavaProjectPackager(folder, techVFolder, moduleProperties.getProperty("module.id"), "WebServicesAPI");
		addPackager("JavaProjectPackager2", jpp2);
		JavaProjectPackager jpp3 = new JavaProjectPackager(folder, techVFolder, moduleProperties.getProperty("module.id"), "EmbeddedAPI");
		addPackager("JavaProjectPackager3", jpp3);

		techVFolder = ((IFolder) folder.getParent().getParent()).getFolder("side-demo");
		WarPatchPackager wpackager = new WarPatchPackager(folder, moduleProperties.getProperty("module.id"), techVFolder, "side-demo");
		addPackager("wpackager2", wpackager);
	}
}
