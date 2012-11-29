package com.bluexml.side.util.alfresco.tools;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.eclipse.EclipseUtils;
import com.bluexml.side.util.libs.eclipse.ExtensionPointUtils;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public class ToolingUtils {

	public static final String MODEL_LIBRARY_PROJECT_ID = "projectId";
	public static final String MODEL_LIBRARY_PLUGIN_ID = "pluginId";
	public static final String MODEL_LIBRARY_ACTIVATOR = "activator";
	public static final String MODEL_LIBRARY_ARCHIVEPATH = "archivePath";
	public static final String MODEL_LIBRARY_mavenFrameworkVersion = "mavenFrameworkVersion";
	public static final String MODEL_LIBRARY_mavenFrameworkClassifier = "mavenFrameworkClassifier";
	public static final String MODEL_LIBRARY_mavenFrameworkGroup = "mavenFrameworkGroup";
	
	

	public static List<IConfigurationElement> getAllToolingModuleExtensions() {
		String nodeName = "moduleDependence";
		return ExtensionPointUtils.getConfigurationElements(com.bluexml.side.util.alfresco.tools.Activator.EXTENSION_POINT_TOOLING, nodeName);
	}

	public static List<IConfigurationElement> getAllToolingModelLibraryExtensions() {
		String nodeName = "modelLibrary";
		return ExtensionPointUtils.getConfigurationElements(com.bluexml.side.util.alfresco.tools.Activator.EXTENSION_POINT_TOOLING, nodeName);
	}

	public static List<IConfigurationElement> getAllToolingDefaultGenerators() {
		String nodeName = "generatorVersion";
		return ExtensionPointUtils.getConfigurationElements(com.bluexml.side.util.alfresco.tools.Activator.EXTENSION_POINT_TOOLING, nodeName);
	}
	
	public static List<IConfigurationElement> getAllToolingDefaultDeployers(String id) {
		String nodeName = "deployerVersion";
		return ExtensionPointUtils.getConfigurationElements(com.bluexml.side.util.alfresco.tools.Activator.EXTENSION_POINT_TOOLING, nodeName);
	}
	
	public static IConfigurationElement getModelLibraryFromLabel_(String label) {
		return getModelLibraryFor("label", label);
	}

	public static IConfigurationElement getModelLibraryForId(String id) {
		return getModelLibraryFor("id", id);
	}
	public static IConfigurationElement getModelLibraryFor(String attibuteName, String value) {
		List<IConfigurationElement> founded = new ArrayList<IConfigurationElement>();
		List<IConfigurationElement> allToolingModelLibraryExtensions = getAllToolingModelLibraryExtensions();
		for (IConfigurationElement iConfigurationElement : allToolingModelLibraryExtensions) {
			if (value.equals(iConfigurationElement.getAttribute(attibuteName))) {
				founded.add(iConfigurationElement);
			}
		}
		if (founded.size() == 1) {
			return founded.get(0);
		} else if (founded.size() > 1) {
			// error but return the first one
			System.out.println("ToolingUtils.getModelLibraryFor() WARN more than one match");
			return founded.get(0);
		}
		return null;
	}

	public static IProject importLibrary(String libraryId) throws Exception {
		IConfigurationElement modelLibraryFromLabel = getModelLibraryForId(libraryId);

		String className = modelLibraryFromLabel.getAttribute(MODEL_LIBRARY_ACTIVATOR);
		String resourcePath = modelLibraryFromLabel.getAttribute(MODEL_LIBRARY_ARCHIVEPATH);
		String bundle = modelLibraryFromLabel.getAttribute(MODEL_LIBRARY_PLUGIN_ID);

		String projectId = modelLibraryFromLabel.getAttribute(MODEL_LIBRARY_PROJECT_ID);

		String archiveName = resourcePath.substring(resourcePath.lastIndexOf("/")+1);

		URI workspace = ResourcesPlugin.getWorkspace().getRoot().getLocationURI();
		File wkspaceFile = new File(workspace);
		File projectFile = new File(wkspaceFile, projectId);

		boolean exists = projectFile.exists();
		System.out.println(projectFile);
		System.out.println("exists ? " + exists);

		if (!exists) {
			// extract
			System.out.println("extract library into current workspace");
			File tmpZip = File.createTempFile("side_lib_", archiveName); //$NON-NLS-1$ //$NON-NLS-2$
			tmpZip.deleteOnExit();

			InputStream stream = ExtensionPointUtils.getStreamForBundleResource(bundle, className, resourcePath);
			FileHelper.writeStreamInFile(tmpZip, stream);
			System.out.println("file to unzip :" + tmpZip + ":" + tmpZip.length() + " b"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			TrueZipHelper tzh = new TrueZipHelper("zip"); //$NON-NLS-1$
			tzh.copyFiles(tmpZip, wkspaceFile, true);
		}

		return EclipseUtils.importEclipseProject(projectFile);

	}
}
