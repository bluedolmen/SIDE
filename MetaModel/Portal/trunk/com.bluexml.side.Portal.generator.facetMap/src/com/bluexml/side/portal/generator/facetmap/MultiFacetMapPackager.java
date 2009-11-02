package com.bluexml.side.portal.generator.facetmap;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;

import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.generator.packager.AbstractMultiPackager;
import com.bluexml.side.util.generator.packager.AbstractPackager;
import com.bluexml.side.util.generator.packager.WarPatchPackager;
import com.bluexml.side.util.libs.IFileHelper;

public class MultiFacetMapPackager extends AbstractMultiPackager {

	public MultiFacetMapPackager(IFolder folder, Properties moduleProperties, String technoV) {
		super(folder, moduleProperties, technoV);
		this.packagers = new HashMap<String, AbstractPackager>();

		// list webapp generation
		File[] l = new File(IFileHelper.getFile(folder), "webapps").listFiles();
		for (File file : l) {
			if (file.isDirectory()) {
				String string = file.getName();
				Properties prs = new Properties(moduleProperties);
				prs.setProperty("module.id", string);
				WarPatchPackager wpackager = new WarPatchPackager(folder, prs, technoV, string);
				packagers.put(string, wpackager);
			}
		}

	}

}
