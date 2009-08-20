package com.bluexml.side.util.dependencies;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.execution.MavenExecutionResult;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.util.libs.FileHelper;

public class MavenTmpProject {
	private File pomFile;
	private File projectFolder;
	private MavenUtil mavenUtil;
	private static final String TARGET_ARTIFACT = "tmpProject_";

	private List<ModuleConstraint> dm;
	private static final SAXBuilder sxb = new SAXBuilder();
	private static final XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

	public MavenTmpProject(File workingFolder, String tech_version, List<ModuleConstraint> mc) throws Exception {
		this.dm = mc;
		projectFolder = new File(workingFolder, TARGET_ARTIFACT+tech_version);
		boolean deleted = FileHelper.deleteFile(projectFolder);
		boolean created = projectFolder.mkdirs();
	}

	/**
	 * build pom dependency fragment from ModuleConstraint
	 * 
	 * @param n
	 * @return
	 */
	private Element buildPomDependency(Namespace n, ModuleConstraint mc) {
		Element depends = new Element("dependency", n.getURI());
		Element groupId = new Element("groupId", n.getURI()).setText(mc.getGroupId());
		Element artifactId = new Element("artifactId", n.getURI()).setText(mc.getArtifactId());
		Element version = new Element("version", n.getURI()).setText(mc.getVersionRange());
		Element type = new Element("type", n.getURI()).setText(mc.getModuleType());

		depends.addContent(groupId);
		depends.addContent(artifactId);
		depends.addContent(version);
		depends.addContent(type);
		return depends;
	}

	/**
	 * create a maven project including dependencies from the generated modules
	 * 
	 * @return
	 * @throws Exception
	 */
	private void createProject() throws Exception {
		pomFile = new File(projectFolder, "pom.xml");
		InputStream in = this.getClass().getResourceAsStream("model.pom.xml");
		// copy the default pom to the tmpProject
		FileHelper.writeStreamInFile(pomFile, in);

		// add dependencies entries
		Document pom = sxb.build(pomFile);
		Element project = pom.getRootElement();
		Namespace n = project.getNamespace();
		Element dependencies = project.getChild("dependencies", n);
		for (ModuleConstraint mc : dm) {
			Element depends = buildPomDependency(n, mc);
			dependencies.addContent(depends);
		}
		FileOutputStream os = new FileOutputStream(pomFile);
		outputter.output(pom, os);
		os.close();
	}

	public void copyAllDependencies(File whereTocopy) throws Exception {
		createProject();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("outputDirectory", whereTocopy.getAbsolutePath());
		MavenExecutionResult result = getMavenUtil().doMavenGoal(projectFolder, "dependency:copy-dependencies", params);
		if (result.getExceptions().size() > 0) {
			List<?> exceps = result.getExceptions();
			System.err.println("Exception occured during maven process :" + exceps);
			throw new Exception("Exception occured during maven process :" + exceps);
		}
	}

	private MavenUtil getMavenUtil() {
		if (mavenUtil == null) {
			mavenUtil = new MavenUtil();
		}
		return mavenUtil;
	}

}
