package com.bluexml.side.util.alfresco.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

public class ModelLibrary {

	protected List<String> generators = new ArrayList<String>();
	protected List<String> deployers = new ArrayList<String>();

	protected String className = null;
	protected String resourcePath = null;
	protected String bundle = null;
	protected String projectId = null;
	protected String archiveName = null;

	protected String mavenFrameworkVersion = null;
	protected String mavenFrameworkClassifier = null;
	protected String mavenFrameworkGroup = null;

	public ModelLibrary() {

	}

	public ModelLibrary(String id) {
		IConfigurationElement modelLibraryFromLabel = ToolingUtils.getModelLibraryForId(id);

		className = modelLibraryFromLabel.getAttribute(ToolingUtils.MODEL_LIBRARY_ACTIVATOR);
		resourcePath = modelLibraryFromLabel.getAttribute(ToolingUtils.MODEL_LIBRARY_ARCHIVEPATH);
		bundle = modelLibraryFromLabel.getAttribute(ToolingUtils.MODEL_LIBRARY_PLUGIN_ID);
		projectId = modelLibraryFromLabel.getAttribute(ToolingUtils.MODEL_LIBRARY_PROJECT_ID);
		mavenFrameworkClassifier = modelLibraryFromLabel.getAttribute(ToolingUtils.MODEL_LIBRARY_mavenFrameworkClassifier);
		mavenFrameworkVersion = modelLibraryFromLabel.getAttribute(ToolingUtils.MODEL_LIBRARY_mavenFrameworkVersion);
		mavenFrameworkGroup = modelLibraryFromLabel.getAttribute(ToolingUtils.MODEL_LIBRARY_mavenFrameworkGroup);
		archiveName = resourcePath.substring(resourcePath.lastIndexOf("/") + 1);

		// load defaultDeployers
		IConfigurationElement[] children_dep = modelLibraryFromLabel.getChildren("deployerVersion");
		for (IConfigurationElement iConfigurationElement : children_dep) {
			String attribute = iConfigurationElement.getAttribute("id");
			deployers.add(attribute);
		}
		// load default generators
		IConfigurationElement[] children_gen = modelLibraryFromLabel.getChildren("generatorVersion");
		for (IConfigurationElement iConfigurationElement : children_gen) {
			String attribute = iConfigurationElement.getAttribute("id");
			generators.add(attribute);
		}
	}

	/**
	 * @return the generators
	 */
	public List<String> getGenerators() {
		return generators;
	}

	/**
	 * @return the deployers
	 */
	public List<String> getDeployers() {
		return deployers;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @return the resourcePath
	 */
	public String getResourcePath() {
		return resourcePath;
	}

	/**
	 * @return the bundle
	 */
	public String getBundle() {
		return bundle;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @return the archiveName
	 */
	public String getArchiveName() {
		return archiveName;
	}

	/**
	 * @return the mavenFrameworkVersion
	 */
	public String getMavenFrameworkVersion() {
		return mavenFrameworkVersion;
	}

	/**
	 * @return the mavenFrameworkClassifier
	 */
	public String getMavenFrameworkClassifier() {
		return mavenFrameworkClassifier;
	}

	/**
	 * @return the mavenFrameworkGroup
	 */
	public String getMavenFrameworkGroup() {
		return mavenFrameworkGroup;
	}

}
