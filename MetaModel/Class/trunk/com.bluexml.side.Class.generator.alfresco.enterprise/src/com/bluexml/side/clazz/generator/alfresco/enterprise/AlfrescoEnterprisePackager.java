package com.bluexml.side.clazz.generator.alfresco.enterprise;

import java.util.Properties;

import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.generator.alfresco.AlfrescoPackager;
import com.bluexml.side.util.generator.packager.JavaProjectPackager;

public class AlfrescoEnterprisePackager extends AlfrescoPackager {

	public AlfrescoEnterprisePackager(IFolder folder, Properties moduleProperties, String technoV) {
		super(folder, moduleProperties, technoV);
		
		JavaProjectPackager jpp = new JavaProjectPackager(folder, technoV, moduleProperties.getProperty("module.id"),"modelAPI");
		packagers.put("JavaProjectPackager", jpp);
		JavaProjectPackager jpp2 = new JavaProjectPackager(folder, technoV, moduleProperties.getProperty("module.id"),"WebServicesAPI");
		packagers.put("JavaProjectPackager2", jpp2);
	}

}
