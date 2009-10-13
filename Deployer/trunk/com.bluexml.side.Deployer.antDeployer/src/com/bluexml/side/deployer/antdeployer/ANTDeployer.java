package com.bluexml.side.deployer.antdeployer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.helper.ProjectHelperImpl;

import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;

public class ANTDeployer extends Deployer {

	private static boolean processExecuted;
	private static String KEY_ANTFILE = "com.bluexml.side.Deployer.antDeployer.param.antFile";
	
	@Override
	protected void clean(File arg0) throws Exception {
		//Nothing to do
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
				monitor.addWarningText("The file "+f.getAbsolutePath()+" doesn't exist. ANT deployment is cancelled.");
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
				System.out.println("Deploy post process");

				if (antProject != null) {
					try {
						ProjectHelper helper = new ProjectHelperImpl();
						helper.parse(antProject, f);
						antProject.executeTarget("post-build");
						monitor.logConsole(outputStream.toString(), LogEntryType.DEPLOYMENT_INFORMATION);
						monitor.addWarningText(outputStream.toString());
						monitor.logConsole(errorStream.toString(), LogEntryType.DEPLOYMENT_INFORMATION);
						monitor.addWarningText(errorStream.toString());
					} catch (Exception e) {
						e.printStackTrace();
						monitor.addErrorTextAndLog(e.getMessage(), e, "");
						//TODO Add in the global log
					}
				}

				processExecuted = false;
			} else {
				System.out.println("Deploy pre process");

				if (antProject != null) {
					try {
						ProjectHelper helper = new ProjectHelperImpl();
						helper.parse(antProject, f);
						antProject.setProperty("directory", arg0.toString());
						antProject.executeTarget("pre-build");
						monitor.logConsole(outputStream.toString(), LogEntryType.DEPLOYMENT_INFORMATION);
						monitor.addWarningText(outputStream.toString());
						monitor.logConsole(errorStream.toString(), LogEntryType.DEPLOYMENT_INFORMATION);
						monitor.addWarningText(errorStream.toString());
					} catch (Exception e) {
						e.printStackTrace();
						monitor.addErrorTextAndLog(e.getMessage(), e, "");
						//TODO Add in the global log
					}
				}

				processExecuted = true;
			}
		}
	}

	@Override
	protected void postProcess(File arg0) throws Exception {
		//Nothing to do
	}

	@Override
	protected void preProcess(File arg0) throws Exception {
		//Nothing to do
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
