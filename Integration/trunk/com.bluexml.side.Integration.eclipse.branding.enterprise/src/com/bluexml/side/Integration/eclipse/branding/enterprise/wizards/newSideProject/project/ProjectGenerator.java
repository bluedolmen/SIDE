package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.project;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.velocity.VelocityGenerator;

public final class ProjectGenerator {


	public static void generate(IProject project) throws Exception {
		
		String templates = "/com/bluexml/side/Integration/eclipse/branding/enterprise/wizards/newSideProject/project/templates";
		String property_userName = System.getProperty("user.name");
		
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("velocity_projectName", project.getName());
		context.put("velocity_userName", property_userName);
		
		context.put("velocity_currentDate", new Date());

		// register VelocityAction for build.xml
		IFile ifile = IFileHelper.createFile(project, "build.xml");
		File f = IFileHelper.convertIRessourceToFile(ifile);
		VelocityGenerator vg = new VelocityGeneratorImp(templates + "/build.xml.vm", f.getAbsolutePath(), context);

		// register VelocityAction for license.txt
		ifile = IFileHelper.createFile(project, "src/license.txt");
		f = IFileHelper.convertIRessourceToFile(ifile);
		vg.addAction(templates + "/SRC_license.txt.vm", f.getAbsolutePath(), context);
		
		// register VelocityAction for license.txt
		ifile = IFileHelper.createFile(project, "license.txt");
		f = IFileHelper.convertIRessourceToFile(ifile);
		vg.addAction(templates + "/license.txt.vm", f.getAbsolutePath(), context);


		// register VelocityAction for pom.xml
		ifile = IFileHelper.createFile(project.getFolder("src/modules/mavenProjects"), "pom.xml");
		f = IFileHelper.convertIRessourceToFile(ifile);
		vg.addAction(templates + "/pom.xml.vm", f.getAbsolutePath(), context);

		// register VelocityAction for README.txt
		ifile = IFileHelper.createFile(project, "README.txt");
		f = IFileHelper.convertIRessourceToFile(ifile);
		vg.addAction(templates + "/README.txt.vm", f.getAbsolutePath(), context);

		// register VelocityAction for src/README.txt
		ifile = IFileHelper.createFile(project, "src/README.txt");
		f = IFileHelper.convertIRessourceToFile(ifile);
		vg.addAction(templates + "/SRC_README.txt.vm", f.getAbsolutePath(), context);

		// register VelocityAction for src/build.user.properties		
		ifile = IFileHelper.createFile(project, "build." + property_userName + ".properties");
		f = IFileHelper.convertIRessourceToFile(ifile);
		vg.addAction(templates + "/build.user.properties.vm", f.getAbsolutePath(), context);

		// register VelocityAction for src/build.user.properties		
		ifile = IFileHelper.createFile(project, ".settings/com.bluexml.side.builder.properties");
		f = IFileHelper.convertIRessourceToFile(ifile);
		vg.addAction(templates + "/com.bluexml.side.builder.properties.vm", f.getAbsolutePath(), context);

		
		vg.generateAll();
	}

	private ProjectGenerator() {} // Utility class
}
