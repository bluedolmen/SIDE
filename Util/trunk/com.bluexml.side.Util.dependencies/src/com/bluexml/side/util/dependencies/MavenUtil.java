package com.bluexml.side.util.dependencies;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.maven.DefaultMaven;
import org.apache.maven.Maven;
import org.apache.maven.cli.MavenCli;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.DefaultMavenExecutionResult;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

public class MavenUtil {

	public MavenExecutionResult doMavenGoal(File baseDir, List<String> goals, Map<String, String> parameters, List<String> profiles, Boolean offline) throws Exception {
		return doMavenGoalUsingMavenCli(baseDir, goals, parameters, profiles, offline);
	}

	@SuppressWarnings("unchecked")
	private MavenExecutionResult doMavenGoalUsingMavenCli(File baseDir, List<String> goals, Map<String, String> parameters, List<String> profiles, Boolean offline) throws Exception {
		// save the current classloader ... maven play with thread classloader Grrr
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		// Instantiate MavenClient
		MavenCli mci = new MavenCli();

		String workingDirectory = baseDir.getAbsolutePath();

		// build arguments list
		List<String> argsL = new ArrayList<String>();
		argsL.addAll(goals);
		// Additional parameters
		// disable interactive mode
		argsL.add("-B");
		// display stacktrace if error occur 
		argsL.add("-e");
		argsL.add("-X");

		// offline mode activated
		if (offline) {
			argsL.add("-o");
		}

		// active profile parameter
		if (profiles != null && profiles.size() > 0) {
			String profileParam = "";
			Iterator<String> iterator = profiles.iterator();
			while (iterator.hasNext()) {
				profileParam += iterator.next();

				if (iterator.hasNext()) {
					profileParam += ",";
				}
			}
			argsL.add("-P " + profileParam);
		}

		// user Properties
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			argsL.add("-D" + entry.getKey() + "=" + entry.getValue());
		}
		
		// define streams
		// TODO use PrintStreamLogger to implement maven logging and error detection
		File mvOutFile = new File(baseDir, "log.txt");
		PrintStream stdout = new PrintStream(mvOutFile);
		File mvOutErrFile = new File(baseDir, "log-err.txt");
		PrintStream stderr = new PrintStream(mvOutErrFile);
		
		stdout.println("MavenUtil execute maven request :");
		stdout.println("** args :" + getCommandFromMavenExecutionArgs(argsL));
		stdout.println("** working directory :" + workingDirectory);
		
		String[] args = argsL.toArray(new String[argsL.size()]);
		// execute maven request
		mci.doMain(args, workingDirectory, stdout, stderr);

		// build a MavenEcecutionResult
		DefaultMavenExecutionResult defaultMavenExecutionResult = new DefaultMavenExecutionResult();
		
		// restore classloader
		Thread.currentThread().setContextClassLoader(cl);

		// search in output for errors
		Iterator<String> it = FileUtils.lineIterator(mvOutFile);
		List<String> errorLines = new ArrayList<String>();
		String errors = "";
		while (it.hasNext()) {
			String line = it.next();
			if (line.startsWith("[ERROR]")) {
				errorLines.add(line);
				errors += line;
				errors += "\n";
			}
		}
		if (errorLines.size() > 0) {
			defaultMavenExecutionResult.addException(new Exception(errors));
		}

		return defaultMavenExecutionResult;
	}

	private MavenExecutionResult doMavenGoal_old(File baseDir, List<String> goals, Map<String, String> parameters, List<String> profiles, Boolean offline) throws Exception {
		System.out.println(baseDir.getAbsolutePath());
		DefaultMavenExecutionRequest archetypeCreateRequest = new DefaultMavenExecutionRequest();

		archetypeCreateRequest.setBaseDirectory(baseDir);
		archetypeCreateRequest.setGoals(goals);
		archetypeCreateRequest.setInteractiveMode(false);
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
			Properties props = new Properties();
			for (Map.Entry<String, String> param : parameters.entrySet()) {
				props.setProperty(param.getKey(), param.getValue());
			}
			archetypeCreateRequest.setUserProperties(props);
		}
		Maven embedder = getEmbedder();

		archetypeCreateRequest.setUpdateSnapshots(false);

		//System.out.println("Active profiles :"+archetypeCreateRequest.getActiveProfiles());
		if (archetypeCreateRequest.getActiveProfiles().size() == 0) {
			throw new Exception("No active profile founded, reports this bug to SIDE developers team");
		}

		System.out.println(getCommandFromMavenExecutionRequest(archetypeCreateRequest));
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

	public Maven getEmbedder() throws Exception {
		Maven embedder = new DefaultMaven();

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

	public static String getCommandFromMavenExecutionRequest(MavenExecutionRequest req) {
		String rt = "mvn " + req.getGoals().toString().replaceAll("[\\[\\]]", "").replace(",", " ");
		for (Object key : req.getUserProperties().keySet()) {
			rt += " -D" + key + "=" + req.getUserProperties().getProperty((String) key);
		}

		return rt;
	}

	public static String getCommandFromMavenExecutionArgs(List<String> args) {
		String rt = "mvn " + args.toString().replaceAll("[\\[\\]]", "").replace(",", "");
		return rt;
	}
}
