package com.bluexml.side.util.dependencies;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.maven.embedder.Configuration;
import org.apache.maven.embedder.DefaultConfiguration;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

public class MavenUtil {
	private MavenEmbedder embedder;

	public MavenExecutionResult doMavenGoal(File baseDir, List<String> goals, Map<String, String> parameters, List<String> profiles, Boolean offline) throws Exception {
		System.out.println(baseDir.getAbsolutePath());
		DefaultMavenExecutionRequest archetypeCreateRequest = new DefaultMavenExecutionRequest();
		archetypeCreateRequest.setBaseDirectory(baseDir);
		archetypeCreateRequest.setGoals(goals);
		archetypeCreateRequest.setInteractiveMode(false);
		archetypeCreateRequest.setProperty("basedir", baseDir.getAbsolutePath().toString());
		
		// set active profile
		if (profiles != null && !profiles.isEmpty()) {
			archetypeCreateRequest.addActiveProfiles(profiles);
		}
		// set offline
		if (offline != null) {
			archetypeCreateRequest.setOffline(offline);
		}
		if (parameters != null) {
			// manage additional parameters
			for (Map.Entry<String, String> param : parameters.entrySet()) {
				archetypeCreateRequest.setProperty(param.getKey(), param.getValue());
			}
		}
		MavenEmbedder embedder = getEmbedder();		
		archetypeCreateRequest.setUpdateSnapshots(true);
		
		//System.out.println("Active profiles :"+archetypeCreateRequest.getActiveProfiles());
		if (archetypeCreateRequest.getActiveProfiles().size() == 0) {
			throw new Exception("No active profile found report this bug to SIDE developers team");
		}
		
		MavenExecutionResult result = embedder.execute(archetypeCreateRequest);
		return result;
	}

	public MavenExecutionResult doMavenGoal(File baseDir, String goal) throws Exception {
		return doMavenGoal(baseDir, new String[] { goal });
	}

	public MavenExecutionResult doMavenGoal(File baseDir, String goal, Map<String, String> parameters, String[] profiles, Boolean offline) throws Exception {
		return doMavenGoal(baseDir, new String[] { goal }, parameters, profiles, offline);
	}

	public MavenExecutionResult doMavenGoal(File baseDir, String goal, Map<String, String> parameters) throws Exception {
		return doMavenGoal(baseDir, new String[] { goal }, parameters);
	}

	public MavenExecutionResult doMavenGoal(File baseDir, String[] goals) throws Exception {
		return doMavenGoal(baseDir, Arrays.asList(goals), null, null, null);
	}

	public MavenExecutionResult doMavenGoal(File baseDir, String[] goals, Map<String, String> parameters) throws Exception {
		return doMavenGoal(baseDir, Arrays.asList(goals), parameters, null, null);
	}

	public MavenExecutionResult doMavenGoal(File baseDir, String[] goals, Map<String, String> parameters, String[] profiles, Boolean offline) throws Exception {
		List<String> profilesList = null;
		if (profiles != null) {
			profilesList = Arrays.asList(profiles);
		}
		return doMavenGoal(baseDir, Arrays.asList(goals), parameters, profilesList, offline);
	}

	public MavenEmbedder getEmbedder() throws Exception {
		if (embedder == null) {
			Configuration configuration;
			configuration = new DefaultConfiguration();
			// load user configuration file
			// configuration.setUserSettingsFile( MavenEmbedder.DEFAULT_USER_SETTINGS_FILE );

			configuration.setClassLoader(Thread.currentThread().getContextClassLoader());
			embedder = new MavenEmbedder(configuration);
		}
		return embedder;
	}

	/**
	 * get the version number of the dependency in the given pom
	 * 
	 * @param pom
	 *            , the maven pom document
	 * @param groupId
	 *            , the groupId of the dependency
	 * @param artifactId
	 *            , the artifactId of the dependency
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
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
