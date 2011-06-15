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
	Boolean default_offline = false;

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
		argsL.add("-B"); //$NON-NLS-1$
		// display stacktrace if error occur 
		argsL.add("-e"); //$NON-NLS-1$
		argsL.add("-X"); //$NON-NLS-1$
		if (offline == null) {
			offline = false;
		}
		// offline mode activated
		if (offline) {
			argsL.add("-o"); //$NON-NLS-1$
		}

		// active profile parameter
		if (profiles != null && profiles.size() > 0) {
			String profileParam = ""; //$NON-NLS-1$
			Iterator<String> iterator = profiles.iterator();
			while (iterator.hasNext()) {
				profileParam += iterator.next();

				if (iterator.hasNext()) {
					profileParam += ","; //$NON-NLS-1$
				}
			}
			argsL.add("-P " + profileParam); //$NON-NLS-1$
		}

		// user Properties
		if (parameters != null) {
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				argsL.add("-D" + entry.getKey() + "=" + entry.getValue()); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		// define streams
		// TODO use PrintStreamLogger to implement maven logging and error detection
		File mvOutFile = new File(baseDir, "log.txt"); //$NON-NLS-1$
		PrintStream stdout = new PrintStream(mvOutFile);
		File mvOutErrFile = new File(baseDir, "log-err.txt"); //$NON-NLS-1$
		PrintStream stderr = new PrintStream(mvOutErrFile);

		stdout.println("MavenUtil execute maven request :"); //$NON-NLS-1$
		stdout.println("** args :" + getCommandFromMavenExecutionArgs(argsL)); //$NON-NLS-1$
		stdout.println("** working directory :" + workingDirectory); //$NON-NLS-1$

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
		String errors = ""; //$NON-NLS-1$
		while (it.hasNext()) {
			String line = it.next();
			if (line.startsWith("[ERROR]")) { //$NON-NLS-1$
				errorLines.add(line);
				errors += line;
				errors += "\n"; //$NON-NLS-1$
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
			throw new Exception(Messages.MavenUtil_17);
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
		List<Element> l = pom.getRootElement().getChild("dependencies", n).getChildren("dependency", n); //$NON-NLS-1$ //$NON-NLS-2$
		for (Element element : l) {
			if (element.getChild("groupId", n).getText().equals(groupId) && element.getChild("artifactId", n).getText().equals(artifactId)) { //$NON-NLS-1$ //$NON-NLS-2$
				return element.getChild("version", n).getText(); //$NON-NLS-1$
			}
		}
		throw new Exception(Messages.MavenUtil_23);
	}

	public static String getCommandFromMavenExecutionRequest(MavenExecutionRequest req) {
		String rt = "mvn " + req.getGoals().toString().replaceAll("[\\[\\]]", "").replace(",", " "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		for (Object key : req.getUserProperties().keySet()) {
			rt += " -D" + key + "=" + req.getUserProperties().getProperty((String) key); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return rt;
	}

	public static String getCommandFromMavenExecutionArgs(List<String> args) {
		String rt = "mvn " + args.toString().replaceAll("[\\[\\]]", "").replace(",", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		return rt;
	}
}
