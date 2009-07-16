package com.bluexml.side.util.generator.dependency;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.embedder.Configuration;
import org.apache.maven.embedder.DefaultConfiguration;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.embedder.MavenEmbedderException;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;

public class MavenUtil {

	public static MavenEmbedder getMavenEmbedder() throws MavenEmbedderException {
		Configuration configuration;
		MavenEmbedder embedder;
		configuration = new DefaultConfiguration();
		configuration.setClassLoader(Thread.currentThread().getContextClassLoader());
		embedder = new MavenEmbedder(configuration);
		return embedder;
	}

	public static MavenExecutionResult doMavenGoal(File baseDir, List<String> goals) throws Exception {
		DefaultMavenExecutionRequest archetypeCreateRequest = new DefaultMavenExecutionRequest();
		archetypeCreateRequest.setBaseDirectory(baseDir);
		archetypeCreateRequest.setGoals(goals);
		archetypeCreateRequest.setProperty("interactiveMode", "false");
		archetypeCreateRequest.setProperty("basedir", baseDir.getAbsolutePath().toString());

		MavenEmbedder embedder = getMavenEmbedder();
		archetypeCreateRequest.setUpdateSnapshots(true);
		MavenExecutionResult result = embedder.execute(archetypeCreateRequest);
		return result;
	}

	public static MavenExecutionResult doMavenGoal(File baseDir, String goal) throws Exception {
		return doMavenGoal(baseDir, new String[] { goal });
	}

	public static MavenExecutionResult doMavenGoal(File baseDir, String[] goals) throws Exception {
		return doMavenGoal(baseDir, Arrays.asList(goals));
	}

}
