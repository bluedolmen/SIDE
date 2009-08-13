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
import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Status;

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
			File f = getAntBuildFile(dest);
			if (f != null && f.exists()) {
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
			} else {
				throw new Exception("DocumentationDeployer : build.xml file isn't found.");
			}
		}
	}

	private File getAntBuildFile(IFolder dest) throws URISyntaxException, IOException {
		String folderPath = dest.getLocation().toOSString() + File.separator;
		String folderSource = "src/com/bluexml/side/deployer/documentation/"; //$NON-NLS-1$
		File ant = null;
		try {
			ant = moveFile(folderPath, "build.xml", folderSource); //$NON-NLS-1$
		} catch(Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error while moving file.", e)); //$NON-NLS-1$
		}
		return ant;
	}

	private static File moveFile(String folderDest, String fileName,
			String folderSource) throws IOException  {
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
		return file;
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

