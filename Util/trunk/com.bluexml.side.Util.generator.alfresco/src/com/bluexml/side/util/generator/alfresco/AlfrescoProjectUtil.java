package com.bluexml.side.util.generator.alfresco;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;

import org.apache.maven.execution.MavenExecutionResult;

import com.bluexml.side.util.dependencies.MavenUtil;

public class AlfrescoProjectUtil {
	HashMap<String, String> archetype_amp = new HashMap<String, String>();
	HashMap<String, String> archetype_share = new HashMap<String, String>();
	File workingDirectory;
	final String goal = "archetype:generate";
	public AlfrescoProjectUtil(File currentProject) {
		this.workingDirectory = currentProject;
		archetype_amp.put("archetypeGroupId", "com.bluexml.side.Framework.maven");
		archetype_amp.put("archetypeArtifactId", "ampArchetypeForSide");
		archetype_amp.put("archetypeVersion", "1.0.5");
		archetype_amp.put("interactive", "false");
		// archetype_amp.put("archetypeRepository", ARCHETYPE_REPO);

		archetype_share.put("archetypeGroupId", "com.bluexml.side.Framework.maven");
		archetype_share.put("archetypeArtifactId", "warPatchArchetypeForSide");
		archetype_share.put("archetypeVersion", "1.0.5");
		archetype_share.put("webapp-name", "share");
		archetype_share.put("interactive", "false");
		// archetype_share.put("archetypeRepository", ARCHETYPE_REPO);
	}

	public File mavenPackage(File baseDir, String artifactId, String version, ModuleType type) throws Exception {
		MavenUtil mu = new MavenUtil();
		mu.doMavenGoal(baseDir, "package");
		return new File(getArtifactPath(baseDir, artifactId, version, type));
	}

	public void createAMPProject(String artifactId, String groupId, String version, String projectName) throws Exception {
		createProject(artifactId, groupId, version, ModuleType.AMP, projectName);
	}

	public void createSHAREProject(String artifactId, String groupId, String version, String projectName) throws Exception {
		createProject(artifactId, groupId, version, ModuleType.SHARE, projectName);
	}

	private void createProject(String artifactId, String groupId, String version, ModuleType moduleType, String projectName) throws Exception {
		// execute maven goal
		final MavenUtil mu = new MavenUtil();

		
		if (!workingDirectory.exists()) {
			workingDirectory.mkdirs();
		}

		final File projectFile = new File(workingDirectory, artifactId);
		if (projectFile.exists()) {
			// error project already exist
		}

		// setParameters
		final HashMap<String, String> params = new HashMap<String, String>();
		params.put("groupId", groupId);

		params.put("artifactId", artifactId);
		params.put("version", version);
		params.put("project-name", projectName);
		if (moduleType.equals(ModuleType.AMP)) {
			params.putAll(archetype_amp);
		} else if (moduleType.equals(ModuleType.SHARE)) {
			params.putAll(archetype_share);
		} else {

		}

		MavenExecutionResult result = mu.doMavenGoal(workingDirectory, new String[] { goal }, params, new String[] {}, false);

		if (result.getExceptions().size() > 0) {
			throw new Exception(result.getExceptions().get(0));
		}

	}

	private String getArtifactPath(File baseDir, String artifactId, String version, ModuleType moduleType) {
		String path = baseDir.getAbsolutePath() + "target" + File.separator + artifactId + "-" + version;
		String ext = "";
		if (moduleType.equals(ModuleType.AMP)) {
			ext = ".zip";
		} else if (moduleType.equals(ModuleType.SHARE)) {
			ext = ".amp";
		}
		return path + ext;
	}

	public enum ModuleType {
		AMP, SHARE
	}
}
