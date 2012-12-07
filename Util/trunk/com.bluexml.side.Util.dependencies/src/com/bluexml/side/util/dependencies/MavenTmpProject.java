package com.bluexml.side.util.dependencies;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

	// String[] inline_profiles = new String[] { "public", "local" };
	String[] online_profiles = new String[] { "public" }; //$NON-NLS-1$
	String[] offline_profiles = new String[] { "offline" }; //$NON-NLS-1$
	private static final String TARGET_ARTIFACT = "tmpProject_"; //$NON-NLS-1$
	private Boolean offline = false;
	private List<ModuleConstraint> dm;
	private static final SAXBuilder sxb = new SAXBuilder();
	private static final XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

	public MavenTmpProject(File workingFolder, String tech_version, List<ModuleConstraint> mc, boolean offline) throws Exception {
		this.dm = mc;
		projectFolder = new File(workingFolder, TARGET_ARTIFACT + tech_version);
		boolean deleted = FileHelper.deleteFile(projectFolder, false);
		boolean created = projectFolder.mkdirs();
		this.offline = offline;
	}

	/**
	 * build pom dependency fragment from ModuleConstraint
	 * 
	 * @param n
	 * @return
	 */
	private Element buildPomDependency(Namespace n, ModuleConstraint mc) {
		Element depends = new Element("dependency", n.getURI()); //$NON-NLS-1$
		Element groupId = new Element("groupId", n.getURI()).setText(mc.getGroupId()); //$NON-NLS-1$
		Element artifactId = new Element("artifactId", n.getURI()).setText(mc.getArtifactId()); //$NON-NLS-1$
		Element version = new Element("version", n.getURI()).setText(mc.getVersionRange()); //$NON-NLS-1$
		Element type = new Element("type", n.getURI()).setText(mc.getModuleType()); //$NON-NLS-1$

		depends.addContent(groupId);
		depends.addContent(artifactId);
		depends.addContent(version);
		depends.addContent(type);

		String classifierString = mc.getClassifier();
		if (StringUtils.trimToNull(classifierString) != null) {
			Element classifier = new Element("classifier", n.getURI()).setText(classifierString); //$NON-NLS-1$
			depends.addContent(classifier);
		}
		return depends;
	}

	/**
	 * create a maven project including dependencies from the generated modules
	 * 
	 * @return
	 * @throws Exception
	 */
	private void createProject(String artifactId) throws Exception {
		pomFile = new File(projectFolder, "pom.xml"); //$NON-NLS-1$
		InputStream in = this.getClass().getResourceAsStream("model.pom.xml"); //$NON-NLS-1$
		// copy the default pom to the tmpProject
		FileHelper.writeStreamInFile(pomFile, in);

		// add dependencies entries
		Document pom = sxb.build(pomFile);
		Element project = pom.getRootElement();
		Namespace n = project.getNamespace();
		Element dependencies = project.getChild("dependencies", n); //$NON-NLS-1$
		for (ModuleConstraint mc : dm) {
			Element depends = buildPomDependency(n, mc);
			dependencies.addContent(depends);
		}

		// setArtifactId
		Element artifactIdE = project.getChild("artifactId", n); //$NON-NLS-1$
		artifactIdE.setText(artifactId);

		FileOutputStream os = new FileOutputStream(pomFile);
		outputter.output(pom, os);
		os.close();
	}

	public void copyAllDependencies(File whereTocopy, String artifactId) throws Exception {
		copyAllDependencies(whereTocopy, artifactId, true);
	}

	public void copyAllDependencies(File whereTocopy, String artifactId, boolean offline) throws Exception {
		createProject(artifactId);
		Map<String, String> params = DependenciesDeployer.getDefaultMavenPropertyMap();
		params.put("outputDirectory", whereTocopy.getAbsolutePath()); //$NON-NLS-1$
		params.put("excludeScope", "provided"); //$NON-NLS-1$ //$NON-NLS-2$
		params.put("excludeTypes", "jar"); //$NON-NLS-1$ //$NON-NLS-2$

		String[] profiles = offline_profiles;

		if (offline) {
			profiles = offline_profiles;
		} else {
			profiles = online_profiles;
		}
		MavenExecutionResult result = getMavenUtil().doMavenGoal(projectFolder, "dependency:copy-dependencies", params, profiles, offline); //$NON-NLS-1$
		if (result.getExceptions().size() > 0) {
			List<?> exceps = result.getExceptions();
			System.err.println(Messages.MavenTmpProject_18 + exceps);
			throw new Exception(Messages.MavenTmpProject_18 + exceps);
		}
	}

	private MavenUtil getMavenUtil() {
		if (mavenUtil == null) {
			mavenUtil = new MavenUtil();
		}
		return mavenUtil;
	}

	public void goOffline(String artifactId) throws Exception {
		createProject(artifactId);
		Map<String, String> params = DependenciesDeployer.getDefaultMavenPropertyMap();

		MavenExecutionResult result = getMavenUtil().doMavenGoal(projectFolder, "dependency:go-offline", params, online_profiles, false); //$NON-NLS-1$
		if (result.getExceptions().size() > 0) {
			System.err.println(this);
			List<?> exceps = result.getExceptions();
			System.err.println(Messages.MavenTmpProject_18 + exceps);
			throw new Exception(Messages.MavenTmpProject_18 + exceps);
		}
	}

	public void setOffline(Boolean offline) {
		this.offline = offline;
	}

	public Boolean getOffline() {
		return offline;
	}

	public String toString() {
		String result = ""; //$NON-NLS-1$
		result += this.TARGET_ARTIFACT + "\n"; //$NON-NLS-1$
		result += this.dm + "\n"; //$NON-NLS-1$
		result += this.offline + "\n"; //$NON-NLS-1$
		result += this.pomFile + "\n"; //$NON-NLS-1$
		result += this.projectFolder + "\n"; //$NON-NLS-1$
		return result;
	}
}
