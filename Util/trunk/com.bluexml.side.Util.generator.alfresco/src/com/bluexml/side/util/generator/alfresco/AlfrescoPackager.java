package com.bluexml.side.util.generator.alfresco;

import java.util.HashMap;
import java.util.Properties;

import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.generator.packager.AbstractMultiPackager;
import com.bluexml.side.util.generator.packager.AbstractPackager;
import com.bluexml.side.util.generator.packager.WarPatchPackager;

public class AlfrescoPackager extends AbstractMultiPackager {

	public AlfrescoPackager(IFolder folder, Properties moduleProperties, String technoV) {
		super(folder, moduleProperties, technoV);
		this.packagers = new HashMap<String, AbstractPackager>();
		// construct all packager

		// AMP packager
		AmpPackager ampP = new AmpPackager(folder, moduleProperties, technoV);
		packagers.put("amp", ampP);

		WarPatchPackager wpackager = new WarPatchPackager(folder, moduleProperties, technoV, "share");
		packagers.put("wpackager", wpackager);
	}

}
