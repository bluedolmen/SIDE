package com.bluexml.side.deployer.antdeployer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.helper.ProjectHelper2;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

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
	protected void deployProcess(File fileToDeploy) throws Exception {
		String antFile = getGenerationParameters().get(KEY_ANTFILE);
		if (antFile != null) {
			File f = new File(antFile);
			
			Project antProject = null;
			ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			if (!f.exists()) {
				//Trying to search the file in the workspace
				Path p = new Path(antFile);
				if (p.segmentCount() > 0) {
					String projectName = p.segment(0);
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					IProject project = workspace.getRoot().getProject(projectName);
					if (project.exists()) {
						IFile file = project.getFile(p.removeFirstSegments(1));
						if (file.exists())
							f = file.getRawLocation().toFile();
					}
				}
			}
			
			if (!f.exists()) {
				//Trying to search the file in the same project than the file to deploy
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				String wsLocation = workspace.getRoot().getLocation().toString();
				
				if (fileToDeploy.getAbsolutePath().startsWith(wsLocation)) {
					String wsPath = fileToDeploy.getAbsolutePath().substring(wsLocation.length());
					Path p = new Path(wsPath);
					if (p.segmentCount() > 0 && p.segment(0).length() == 0)
						p.removeFirstSegments(1);
					String projectName = p.segment(0);
					IProject project = workspace.getRoot().getProject(projectName);
					if (project.exists()) {
						IFile file = project.getFile(antFile);
						if (file.exists())
							f = file.getRawLocation().toFile();
					}
				}
			}
			
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
					antProject.setProperty("directory", fileToDeploy.toString());
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
