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
