package com.bluexml.side.integration.eclipse.builder.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class SIDEBuilderConfiguration {

	IFile applicationFile;
	Application app;
	Configuration conf;
	String configurationName;
	String applicationRessourcePath;
	IProject project;

	/**
	 * @return the applicationRessourcePath
	 */
	public String getApplicationRessourcePath() {
		return applicationRessourcePath;
	}

	/**
	 * @param applicationRessourcePath
	 *            the applicationRessourcePath to set
	 */
	public void setApplicationRessourcePath(String applicationRessourcePath) {
		this.applicationRessourcePath = applicationRessourcePath;
	}

	/**
	 * @param configurationName
	 *            the configurationName to set
	 */
	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public SIDEBuilderConfiguration(IProject project) {
		this.project = project;
	}

	public SIDEBuilderConfiguration(IProject project, String portableString) throws Exception {
		this.project = project;
		this.applicationFile = getIFileFromPortableString(ApplicationUtil.eclipseVariableSubstitution(portableString));
		this.applicationRessourcePath = portableString;
	}

	public List<Configuration> getConfigurations() throws IOException {
		List<Configuration> confNames = new ArrayList<Configuration>();
		Application app = getApplication();
		EList<ModelElement> elements = app.getElements();
		for (ModelElement modelElement : elements) {
			if (modelElement instanceof Configuration) {
				confNames.add(((Configuration) modelElement));
			}
		}
		return confNames;
	}

	public Application getApplication() throws IOException {
		if (app == null) {
			EList<EObject> openModel = EResourceUtils.openModel(applicationFile);
			app = (Application) openModel.get(0);
		}
		return app;
	}

	/**
	 * @return the configurationName
	 */
	public String getConfigurationName() {
		return configurationName;
	}

	public void selectConfiguration(String name) throws IOException {
		conf = getApplication().getConfiguration(name);
		configurationName = conf.getName();
	}

	public boolean canPersists() {
		return configurationName != null && applicationRessourcePath != null;
	}

	public void persist() throws IOException, CoreException {
		// serialize data and persists them
		if (canPersists()) {
			// write configurationName and applicationRessourcePath
			Properties props = new Properties();
			props.put("applicationIPath", applicationRessourcePath);
			props.put("activeConfiguration", configurationName);

			// get stream from resource
			OutputStream out = new FileOutputStream(getFileStore());
			// persists
			props.store(out, "");
		}
	}

	public boolean load() {
		// read persisted data and load resources
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(getFileStore()));

			applicationRessourcePath = (String) props.get("applicationIPath");
			configurationName = (String) props.get("activeConfiguration");
			if (applicationRessourcePath == null || configurationName == null) {
				applicationRessourcePath = null;
				configurationName = null;
				return false;
			}
			// load Objects
			// load application IFile
			applicationFile = getIFileFromPortableString(ApplicationUtil.eclipseVariableSubstitution(applicationRessourcePath));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean reload() {
		if (applicationRessourcePath == null || configurationName == null) {
			applicationRessourcePath = null;
			configurationName = null;
			return false;
		}
		// load application IFile
		try {
			applicationFile = getIFileFromPortableString(ApplicationUtil.eclipseVariableSubstitution(applicationRessourcePath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private IFile getIFileFromPortableString(String portableString) {
		IPath p = Path.fromPortableString(portableString);
		IFile iFile = IFileHelper.getIFile(new File(p.toOSString()));
		//		iFile=project.getFile(p);
		return iFile;
	}

	public File getFileStore() throws IOException, CoreException {
		IPath path = project.getFullPath();
		path = path.append(".settings");
		path = path.append("com.bluexml.side.builder");
		path = path.addFileExtension("properties");
		IFile iFile = IFileHelper.getIFile(path);
		File file = IFileHelper.getFile(iFile);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

		return file;
	}

	/**
	 * @return the applicationFile
	 */
	public IFile getApplicationFile() {
		return applicationFile;
	}

}
