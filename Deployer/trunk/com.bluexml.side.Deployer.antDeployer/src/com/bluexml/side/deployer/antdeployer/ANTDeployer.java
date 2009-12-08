package com.bluexml.side.deployer.antdeployer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.helper.ProjectHelper2;
import org.apache.tools.ant.helper.ProjectHelperImpl;

import com.bluexml.side.util.deployer.Deployer;

public class ANTDeployer extends Deployer {

	public ANTDeployer() {
		super("cleankey", "logChangesKey");
	}

	private static boolean processExecuted;
	private static String KEY_ANTFILE = "com.bluexml.side.Deployer.antDeployer.param.antFile";

	@Override
	protected void clean(File arg0) throws Exception {
		// Nothing to do
	}

	@Override
	protected void deployProcess(File arg0) throws Exception {
		String antFile = getGenerationParameters().get(KEY_ANTFILE);
		if (antFile != null) {
			File f = new File(antFile);

			Project antProject = null;
			ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			if (!f.exists())
				monitor.addWarningText("The file " + f.getAbsolutePath() + " doesn't exist. ANT deployment is cancelled.");
			else {
				antProject = new Project();

				org.apache.tools.ant.DefaultLogger log = new org.apache.tools.ant.DefaultLogger();
				log.setErrorPrintStream(new PrintStream(errorStream));
				log.setOutputPrintStream(new PrintStream(outputStream));
				log.setMessageOutputLevel(Project.MSG_INFO);
				antProject.addBuildListener(log);

				antProject.init();
			}

			if (processExecuted) {
				monitor.addTextAndLog("Deploy post process", "");

				if (antProject != null) {
					//ProjectHelper helper = new ProjectHelperImpl();
					ProjectHelper helper = new ProjectHelper2();
					helper.parse(antProject, f);
					antProject.executeTarget("post-build");
					monitor.addTextAndLog("standard output "+outputStream.toString(), "");
					antError(errorStream);
				}

				processExecuted = false;
			} else {
				monitor.addTextAndLog("Deploy pre process", "");

				if (antProject != null) {
					//ProjectHelper helper = new ProjectHelperImpl();
					ProjectHelper helper = new ProjectHelper2();
					helper.parse(antProject, f);
					antProject.setProperty("directory", arg0.toString());
					antProject.executeTarget("pre-build");
					monitor.addTextAndLog("standard output "+outputStream.toString(), "");
					antError(errorStream);
				}

				processExecuted = true;
			}
		}
	}

	private void antError(ByteArrayOutputStream errorStream) throws Exception {
		if (errorStream.toString().length() > 0) {
			monitor.addErrorTextAndLog("Error"+"\n" + errorStream.toString(), null, "");
			throw new Exception("Ant error");
		}
	}

	@Override
	protected void postProcess(File arg0) throws Exception {
		// Nothing to do
	}

	@Override
	protected void preProcess(File arg0) throws Exception {
		// Nothing to do
	}

	/**
	 * This method check if the user have the license to use this deployer.
	 * 
	 * @return true if the deployer can be used.
	 */
	public boolean check() {
		return true;
	}

}
