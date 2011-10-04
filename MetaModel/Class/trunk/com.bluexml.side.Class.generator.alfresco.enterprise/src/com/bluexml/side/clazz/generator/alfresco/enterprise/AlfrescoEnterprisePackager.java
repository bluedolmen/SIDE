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
