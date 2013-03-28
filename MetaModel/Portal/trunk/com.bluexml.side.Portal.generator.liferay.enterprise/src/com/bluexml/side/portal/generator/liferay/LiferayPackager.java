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
package com.bluexml.side.portal.generator.liferay;

import java.util.Properties;

import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.generator.packager.AbstractMultiPackager;
import com.bluexml.side.util.generator.packager.ZipPackager;

public class LiferayPackager extends AbstractMultiPackager {

	public LiferayPackager(IFolder folder, Properties moduleProperties, String technoV) throws Exception {
		super(folder, moduleProperties, technoV);

		// construct all packager

		IFolder techVFolder = ((IFolder)folder.getParent().getParent()).getFolder(technoV);
		ZipPackager zpackager = new ZipPackager(folder.getFolder("layout"), techVFolder, moduleProperties.getProperty("module.id") + "_layout.war");
		addPackager("wpackager", zpackager);

		ZipPackager larPackager = new ZipPackager(folder.getFolder("portal"), techVFolder, moduleProperties.getProperty("module.id") + "_portal.lar");
		addPackager("larPackager", larPackager);

	}

}
