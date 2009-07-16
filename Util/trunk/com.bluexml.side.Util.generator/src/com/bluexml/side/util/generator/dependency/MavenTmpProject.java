package com.bluexml.side.util.generator.dependency;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.embedder.Configuration;
import org.apache.maven.embedder.DefaultConfiguration;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.embedder.MavenEmbedderException;
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
	private File workFolder;
	private MavenEmbedder embedder;
	private Configuration configuration;
	public static final Namespace NAMESPACE_MAVENPOM = Namespace.getNamespace("pom", "http://maven.apache.org/POM/4.0.0");

	private static final String TARGET_ARTIFACT = "tmpProject";

	private DependencesManager dm;
	private static final SAXBuilder sxb = new SAXBuilder();
	private static final XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	File tpmProject;

	public MavenTmpProject() throws Exception {
		this.workFolder = new File(".");
		initMaven();
	}

	public MavenTmpProject(File workingFolder) throws Exception {
		this.workFolder = workingFolder;
		initMaven();
	}

	public MavenTmpProject(File workingFolder, DependencesManager dm) throws Exception {
		this.workFolder = workingFolder;
		this.dm = dm;
		initMaven();
	}

	private void createAndGetDependencies() throws Exception {
		FileHelper.deleteFile(tpmProject);
		createProject();
		prepareProject(dm);
		getDependences();
	}

	private void initMaven() throws MavenEmbedderException {
		configuration = new DefaultConfiguration();
		configuration.setClassLoader(Thread.currentThread().getContextClassLoader());
		embedder = new MavenEmbedder(configuration);
		tpmProject = new File(workFolder.getAbsolutePath() + File.separator + TARGET_ARTIFACT);
	}

	/**
	 * update pom file to add wanted dependencies
	 * 
	 * @throws Exception
	 */
	private void prepareProject(DependencesManager dm) throws Exception {
		Document pom = sxb.build(pomFile);
		Element project = pom.getRootElement();
		Namespace n = project.getNamespace();
		Element dependencies = project.getChild("dependencies", n);
		for (ModuleConstraint mc : dm.getContraints()) {
			Element depends = buildPomDependency(n, mc);
			dependencies.addContent(depends);
		}

		FileOutputStream os = new FileOutputStream(pomFile);
		outputter.output(pom, os);
		os.close();

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
	 * call package maven lifeCircle, so maven get all dependencies from
	 * repositories to local
	 * 
	 * @return
	 * @throws Exception
	 */
	private void getDependences() throws Exception {
		// resolve version ranges for dependencies (replace by used version
		// number)
		MavenExecutionResult result = MavenUtil.doMavenGoal(tpmProject, "versions:resolve-ranges");
		if (result.getExceptions().size() > 0) {
			List<?> exceps = result.getExceptions();
			System.err.println("Exception occure during maven process :" + exceps);
			throw new Exception("Exception occure during maven process :" + exceps);
		}

		// call compile goal to get dependencies in local repository
		MavenExecutionResult result2 = MavenUtil.doMavenGoal(tpmProject, "compile");
		if (result2.getExceptions().size() > 0) {
			List<?> exceps = result2.getExceptions();
			System.err.println("Exception occure during maven process :" + exceps);
			throw new Exception("Exception occure during maven process :" + exceps);
		}

		/*
		 * DefaultMavenExecutionRequest archetypeCreateRequest = new
		 * DefaultMavenExecutionRequest();
		 * archetypeCreateRequest.setBaseDirectory(tpmProject);
		 * archetypeCreateRequest.setGoals(Arrays.asList(new String[] {
		 * "compile" })); archetypeCreateRequest.setProperty("interactiveMode",
		 * "false"); archetypeCreateRequest.setProperty("basedir",
		 * workFolder.getAbsolutePath() + File.separator + TARGET_ARTIFACT);
		 * 
		 * 
		 * archetypeCreateRequest.setUpdateSnapshots(true); MavenExecutionResult
		 * result = embedder.execute(archetypeCreateRequest); return result;
		 */
	}

	/**
	 * call maven to build a maven project
	 * 
	 * @return
	 * @throws Exception
	 */
	private void createProject() throws Exception {
		projectFolder = new File(workFolder, TARGET_ARTIFACT);
		projectFolder.mkdirs();

		pomFile = new File(projectFolder, "pom.xml");
		InputStream in = this.getClass().getResourceAsStream("model.pom.xml");
		// copy the default pom to the tmpProject
		FileHelper.writeStreamInFile(pomFile, in);

	}

	public static void printDoc(File xml) throws Exception {
		SAXBuilder sxb = new SAXBuilder();
		Document doc = sxb.build(xml);
		printDoc(doc);
	}

	public static void printDoc(Document doc) throws Exception {
		try {
			XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
			String element_string = output.outputString(doc);
			System.out.println(element_string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Element createElement(String eleName, Document doc) {
		return new Element(eleName, doc.getBaseURI());
	}

	public List<File> getAllDependenciesResources() throws Exception {
		List<File> dependencies = new ArrayList<File>();
		createAndGetDependencies();
		Document pom = sxb.build(pomFile);
		ArtifactRepository artRep = embedder.getLocalRepository();
		System.out.println(artRep.getBasedir());
		System.out.println(artRep.getUrl());
		File f = new File(artRep.getBasedir());
		if (f.exists()) {
			List<ModuleConstraint> l = dm.getContraints();
			for (ModuleConstraint moduleConstraint : l) {
				String path = moduleConstraint.getGroupId().replaceAll("\\.", File.separator) + File.separator + moduleConstraint.getArtifactId();
				// String version = moduleConstraint.getVersionMin().toString();
				String version = getVersionOf(pom, moduleConstraint.getGroupId(), moduleConstraint.getArtifactId());
				path += File.separator + version;
				String moduleName = moduleConstraint.getArtifactId() + "-" + version + "." + moduleConstraint.getModuleType();
				File localmodule = new File(f.getAbsolutePath() + File.separator + path + File.separator + moduleName);
				if (localmodule.exists()) {
					dependencies.add(localmodule);
				} else {
					System.err.println("local package not found ! " + localmodule);
				}
			}
		} else {
			System.err.println("local repository not found !");
		}
		return dependencies;
	}

	public static String getVersionOf(Document pom, String groupId, String artifactId) throws Exception {
		Element project = pom.getRootElement();
		Namespace n = project.getNamespace();
		// get the dependence version
		List<Element> l = pom.getRootElement().getChild("dependencies", n).getChildren("dependency", n);
		for (Element element : l) {
			if (element.getChild("groupId", n).getText().equals(groupId) && element.getChild("artifactId", n).getText().equals(artifactId)) {
				return element.getChild("version", n).getText();
			}
		}
		throw new Exception("version number not found please report as bug");
	}

}
