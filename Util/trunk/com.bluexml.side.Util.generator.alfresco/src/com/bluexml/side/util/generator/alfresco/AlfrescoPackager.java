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
