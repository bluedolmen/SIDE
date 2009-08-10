package com.bluexml.side.deployer.documentation;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.libs.IFileHelper;

public class DocumentationDeployer extends Deployer {

	public static String DOC_FOLDER_NAME = "doc"; //$NON-NLS-1$

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		FileUtils.deleteDirectory(fileToDeploy);
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		IContainer src = IFileHelper.getIFolder(fileToDeploy);
		if (src != null) {
			IFolder dest = IFileHelper.createFolder(getConfigurationParameters().get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + File.separator + getConfigurationParameters().get("configurationName") + File.separator + LogSave.LOG_DOC_FOLDER + File.separator); //$NON-NLS-1$
			IFileHelper.refreshFolder((IFolder)src);
			AntRunner runner = new AntRunner();
			Map<String,String> properties = new HashMap<String, String>();
			runner.addUserProperties(properties);
			File f = getAntBuildFile();

			if (src.getType() == IFile.FOLDER) {
				List<IFolder> srcFiles = IFileHelper.getAllFolderForFolder((IFolder)src);
				for (IFolder file : srcFiles) {
					String name = file.getName();
					List<IFolder> docFiles = IFileHelper.getAllFolderForFolder(file);
					for (IFolder file2 : docFiles) {
						if (file2.getName().equals(DOC_FOLDER_NAME)) {
							runner.setBuildFileLocation(f.getAbsolutePath());
							properties.put("destDir", dest.getLocation().toFile().getAbsolutePath()); //$NON-NLS-1$
							properties.put("sourceDir", file.getLocation().toFile().getAbsolutePath()); //$NON-NLS-1$
							properties.put("docName", name); //$NON-NLS-1$
							runner.addUserProperties(properties);
							runner.run();
						}
					}
				}
			}
		}
	}

	private File getAntBuildFile() throws URISyntaxException, IOException {
		Bundle b = Platform.getBundle(Activator.PLUGIN_ID);
		URL entry = b.getEntry("src/com/bluexml/side/deployer/documentation/build.xml"); //$NON-NLS-1$
		File f = new File(FileLocator.toFileURL(entry).toURI());
		return f;
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

}

