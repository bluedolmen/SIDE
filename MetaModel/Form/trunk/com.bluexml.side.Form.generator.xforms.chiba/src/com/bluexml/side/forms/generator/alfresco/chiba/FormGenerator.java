package com.bluexml.side.forms.generator.alfresco.chiba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.maven.embedder.Configuration;
import org.apache.maven.embedder.DefaultConfiguration;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.embedder.MavenEmbedderException;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.eclipse.core.resources.IFile;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class FormGenerator extends AbstractGenerator {
	public static final Namespace NAMESPACE_MAVENPOM = Namespace.getNamespace("pom", "http://maven.apache.org/POM/4.0.0");
	private static final String TARGET_VERSION = "1.0.0";
	private static final String TARGET_ARTIFACT = "xforms";
	private static final String TARGET_GROUP = "com.bluexml";
	private static final String ARCHETYPE_ARTIFACT = "xforms.archetype";
	private static final String ARCHETYPE_GROUP = "org.bluexml";
	private static final String ARCHETYPE_VERSION = "2.0.0-SNAPSHOT";
	private static final String SNAPSHOTREPOSITORY = "http://merry.bluexml.com/m2/snapshotrepository";
	private static final String REPOSITORY = "http://merry.bluexml.com/m2/repository";
	private static final String GENERATOR_CODE = "CODE_GED_G_F_CHIBA";
	private static final SAXBuilder sxb = new SAXBuilder();
	private static final XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

	private List<String> clazzModels;
	private List<String> formModels;
	private File workFolder;
	private Configuration configuration;
	private MavenEmbedder embedder;
	private File projectFolder;
	private File pomFile;
	private File alfrescoProperties;
	private File warFile;

	@Override
	public void initialize(Map<String, String> generationParameters_, Map<String, Boolean> generatorOptions_, Map<String, String> configurationParameters_, DependencesManager dm) throws Exception {
		super.initialize(generationParameters_, generatorOptions_, configurationParameters_, dm);
		try {
			initWorkFolder();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo, String id_metamodel) {
		return modelsInfo.containsKey(ClazzPackage.eNS_URI) || modelsInfo.containsKey(FormPackage.eNS_URI);
	}

	public Collection<IFile> complete() throws Exception {
		// FIXME should return WAR IFile
		return new ArrayList<IFile>();
	}

	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String id_mm) {

		getModels(modelsInfo);
		try {
			initMaven();
			MavenExecutionResult createProjectResult = createProject();
			if (createProjectResult.hasExceptions()) {
				throw new RuntimeException(((Exception) createProjectResult.getExceptions().get(0)));
			}
			prepareProject();
			MavenExecutionResult cleanPackageResult = buildProject();
			if (cleanPackageResult.hasExceptions()) {
				throw new RuntimeException(((Exception) cleanPackageResult.getExceptions().get(0)));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	private MavenExecutionResult buildProject() throws IOException {
		DefaultMavenExecutionRequest cleanPackageRequest = new DefaultMavenExecutionRequest();
		cleanPackageRequest.setBaseDirectory(projectFolder);
		cleanPackageRequest.setGoals(Arrays.asList(new String[] { "clean", "package" }));
		MavenExecutionResult cleanPackageResult = embedder.execute(cleanPackageRequest);

		warFile = new File(projectFolder, "target/" + TARGET_ARTIFACT + ".war");

		return cleanPackageResult;
	}

	/**
	 * Create the work folder
	 * 
	 * @throws IOException
	 */
	private void initWorkFolder() throws IOException {
		String path = getTargetSystemPath();
		if (path == null || path.length() == 0)
			throw new RuntimeException("Target path must be set !");
		path += File.separatorChar + getTechVersion();
		workFolder = new File(path);
		FileUtils.forceMkdir(workFolder);
	}

	private void initMaven() throws MavenEmbedderException {
		configuration = new DefaultConfiguration();
		configuration.setClassLoader(Thread.currentThread().getContextClassLoader());
		embedder = new MavenEmbedder(configuration);
	}

	private MavenExecutionResult createProject() throws Exception {

		DefaultMavenExecutionRequest archetypeCreateRequest = new DefaultMavenExecutionRequest();
		archetypeCreateRequest.setBaseDirectory(workFolder);
		archetypeCreateRequest.setGoals(Arrays.asList(new String[] { "archetype:generate" }));
		archetypeCreateRequest.setProperty("interactiveMode", "false");
		archetypeCreateRequest.setProperty("archetypeArtifactId", ARCHETYPE_ARTIFACT);
		archetypeCreateRequest.setProperty("archetypeGroupId", ARCHETYPE_GROUP);
		archetypeCreateRequest.setProperty("archetypeVersion", ARCHETYPE_VERSION);
		if (ARCHETYPE_VERSION.endsWith("SNAPSHOT")) {
			archetypeCreateRequest.setProperty("archetypeRepository", SNAPSHOTREPOSITORY);
		} else {
			archetypeCreateRequest.setProperty("archetypeRepository", REPOSITORY);
		}
		archetypeCreateRequest.setProperty("basedir", workFolder.getAbsolutePath());
		archetypeCreateRequest.setProperty("groupId", TARGET_GROUP);
		archetypeCreateRequest.setProperty("artifactId", TARGET_ARTIFACT);
		archetypeCreateRequest.setProperty("version", TARGET_VERSION);

		archetypeCreateRequest.setUpdateSnapshots(true);

		MavenExecutionResult result = embedder.execute(archetypeCreateRequest);

		projectFolder = new File(workFolder, TARGET_ARTIFACT);
		pomFile = new File(projectFolder, "pom.xml");
		alfrescoProperties = new File(projectFolder, "src/main/resources/alfresco.properties");

		return result;
	}

	private void prepareProject() throws Exception {
		Document pom = sxb.build(pomFile);
		Element project = pom.getRootElement();
		Element build = project.getChild("build", NAMESPACE_MAVENPOM);
		Element plugins = build.getChild("plugins", NAMESPACE_MAVENPOM);
		Element plugin = plugins.getChild("plugin", NAMESPACE_MAVENPOM);
		Element configurationElement = plugin.getChild("configuration", NAMESPACE_MAVENPOM);
		addFiles("clazzFiles", clazzModels, configurationElement);
		addFiles("formsFiles", formModels, configurationElement);
		FileOutputStream os = new FileOutputStream(pomFile);
		outputter.output(pom, os);
		os.close();

		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(alfrescoProperties);
		properties.load(in);
		in.close();
		properties.setProperty("temp.directory", "/");
		properties.setProperty("upload.directory", "/");
		os = new FileOutputStream(alfrescoProperties);
		properties.store(os, null);
		os.close();
	}

	private void addFiles(String name, List<String> models, Element configurationElement) {
		Element files = configurationElement.getChild(name, NAMESPACE_MAVENPOM);
		for (String model : models) {
			Element param = new Element("param");
			param.setText(model);
			files.addContent(param);
		}
	}

	private void getModels(Map<String, List<IFile>> modelsInfo) {
		clazzModels = getModels(modelsInfo, ClazzPackage.eNS_URI);
		formModels = getModels(modelsInfo, FormPackage.eNS_URI);
	}

	private List<String> getModels(Map<String, List<IFile>> modelsInfo, String nsURI) {
		List<String> models = new ArrayList<String>();
		List<IFile> modelsIFile = modelsInfo.get(nsURI);
		if (modelsIFile != null) {
			for (IFile file : modelsIFile) {
				models.add(file.getLocation().toFile().getAbsolutePath());
			}
		}
		return models;
	}

}
