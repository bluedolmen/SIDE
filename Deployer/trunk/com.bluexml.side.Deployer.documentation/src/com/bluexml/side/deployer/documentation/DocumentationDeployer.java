package com.bluexml.side.deployer.documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.ant.AntUtils;

public class DocumentationDeployer extends Deployer {

	public DocumentationDeployer() {
		super("cleanKey", "logChangesKey");
	}

	public static String DOC_FOLDER_NAME = "doc"; //$NON-NLS-1$

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		FileUtils.deleteDirectory(fileToDeploy);
	}


	protected void deployProcess(File fileToDeploy) throws Exception {
		IContainer src = IFileHelper.getIFolder(fileToDeploy);
		if (src != null) {
			IFolder dest = IFileHelper.createFolder(getConfigurationParameters().get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + File.separator
					+ getConfigurationParameters().get("configurationName") + File.separator + LogSave.LOG_DOC_FOLDER + File.separator); //$NON-NLS-1$
			IFileHelper.refreshFolder((IFolder) src);
			Project ant = new Project();
			org.apache.tools.ant.DefaultLogger log = new org.apache.tools.ant.DefaultLogger();
			log.setOutputPrintStream(System.out);
			log.setMessageOutputLevel(Project.MSG_INFO);

			ant.addBuildListener(log);
			ant.init();
			

			Map<String, String> properties = new HashMap<String, String>();			
			File f = getAntBuildFile(dest);
			
			if (f != null && f.exists()) {
				ProjectHelper.configureProject(ant,f);
				boolean docCreated = false;
				if (src.getType() == IFile.FOLDER) {
					List<IFolder> srcFiles = IFileHelper.getAllFolderForFolder((IFolder) src);
					for (IFolder file : srcFiles) {
						String name = file.getName();
						List<IFolder> docFiles = IFileHelper.getAllFolderForFolder(file);
						for (IFolder file2 : docFiles) {
							//UIUtils.showAvert("Test", "Test for " + file2.getLocation().toFile().getAbsolutePath());
							if (file2.getName().equals(DOC_FOLDER_NAME)) {
								//UIUtils.showAvert("Test", "Ant run for " + file2.getName());
								
								properties.put("destDir", dest.getLocation().toFile().getAbsolutePath()); //$NON-NLS-1$
								properties.put("sourceDir", file.getLocation().toFile().getAbsolutePath()); //$NON-NLS-1$
								properties.put("docName", name); //$NON-NLS-1$

								//UIUtils.showAvert("Test", "Properties added ");
								try {
									AntUtils.setProperties(ant, properties);
									ant.executeTarget("createODT_file");								
									docCreated = true;
								} catch (Exception e) {
									Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Build run failed.", e)); //$NON-NLS-1$
								}
							}
						}
					}
					if (!docCreated) {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.PLUGIN_ID, "No doc have been generated.")); //$NON-NLS-1$
					}
				}
			} else {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Build.xml file not found.")); //$NON-NLS-1$
				throw new Exception("DocumentationDeployer : build.xml file isn't found.");
			}
		}
	}

	@Deprecated
	protected void deployProcess_(File fileToDeploy) throws Exception {
		IContainer src = IFileHelper.getIFolder(fileToDeploy);
		if (src != null) {
			IFolder dest = IFileHelper.createFolder(getConfigurationParameters().get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + File.separator
					+ getConfigurationParameters().get("configurationName") + File.separator + LogSave.LOG_DOC_FOLDER + File.separator); //$NON-NLS-1$
			IFileHelper.refreshFolder((IFolder) src);
			AntRunner runner = new AntRunner();

			Map<String, String> properties = new HashMap<String, String>();
			runner.addUserProperties(properties);
			File f = getAntBuildFile(dest);
			if (f != null && f.exists()) {
				boolean docCreated = false;
				if (src.getType() == IFile.FOLDER) {
					List<IFolder> srcFiles = IFileHelper.getAllFolderForFolder((IFolder) src);
					for (IFolder file : srcFiles) {
						String name = file.getName();
						List<IFolder> docFiles = IFileHelper.getAllFolderForFolder(file);
						for (IFolder file2 : docFiles) {
							//UIUtils.showAvert("Test", "Test for " + file2.getLocation().toFile().getAbsolutePath());
							if (file2.getName().equals(DOC_FOLDER_NAME)) {
								//UIUtils.showAvert("Test", "Ant run for " + file2.getName());
								runner.setBuildFileLocation(f.getAbsolutePath());
								properties.put("destDir", dest.getLocation().toFile().getAbsolutePath()); //$NON-NLS-1$
								properties.put("sourceDir", file.getLocation().toFile().getAbsolutePath()); //$NON-NLS-1$
								properties.put("docName", name); //$NON-NLS-1$
								runner.addUserProperties(properties);
								//UIUtils.showAvert("Test", "Properties added ");
								try {
									
									runner.run();
									runner.stop();
									docCreated = true;
								} catch (CoreException e) {
									Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Build run failed.", e)); //$NON-NLS-1$
								}
							}
						}
					}
					if (!docCreated) {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.PLUGIN_ID, "No doc have been generated.")); //$NON-NLS-1$
					}
				}
			} else {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Build.xml file not found.")); //$NON-NLS-1$
				throw new Exception("DocumentationDeployer : build.xml file isn't found.");
			}
		}
	}

	private File getAntBuildFile(IFolder dest) throws URISyntaxException, IOException {
		String folderPath = dest.getLocation().toOSString() + File.separator;
		String folderSource = "com/bluexml/side/deployer/documentation/"; //$NON-NLS-1$
		try {
			moveFile(folderPath, "build.xml", folderSource); //$NON-NLS-1$
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error while moving file.", e)); //$NON-NLS-1$
		}
		File ant = new File(folderPath + "build.xml");
		return ant;
	}

	private static void moveFile(String folderDest, String fileName, String folderSource) throws IOException {
		InputStream in = DocumentationDeployer.class.getClassLoader().getResourceAsStream(folderSource + fileName);

		File dest = new File(folderDest);
		if (!dest.exists()) {
			dest.mkdirs();
		}

		File file = new File(folderDest + fileName);

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "FileOutputStream can't be call.", e)); //$NON-NLS-1$
			throw e;
		}
		int data;
		try {
			data = in.read();
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Data can't be read", e)); //$NON-NLS-1$
			throw e;
		}
		while (data != -1) {
			try {
				fos.write(data);
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error during writing", e)); //$NON-NLS-1$
				throw e;
			}
			data = in.read();
		}
		fos.close();
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub
	}

	public boolean check() {
		return true;
	}

	public boolean isDocumentationDeployer() {
		return true;
	}

}
