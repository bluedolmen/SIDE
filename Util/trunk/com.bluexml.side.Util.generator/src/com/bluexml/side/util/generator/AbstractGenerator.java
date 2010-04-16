package com.bluexml.side.util.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.Checkable;

public abstract class AbstractGenerator implements IGenerator, Checkable {

	/**
	 * generationParameters : the list of selected options for the generation
	 * (clean, verbose...), shared by all generators. generatorOptions : the
	 * list of selected options for the generator.(templates subset ...)
	 * configurationParameters : the list of technical parameters, shared by all
	 * generators.
	 */
	protected static Map<String, String> generationParameters = new HashMap<String, String>();

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	protected static Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
	protected static Map<String, String> configurationParameters = new HashMap<String, String>();

	protected ComponentMonitor monitor;
	protected String id;
	public String TEMP_FOLDER = "tmp";
	public static String GENERATOR_CODE = null;
	protected static String techVersion = null;
	protected DependencesManager dm;

	public ComponentMonitor getMonitor() {
		return monitor;
	}

	public String getTechVersion() {
		return techVersion;
	}

	public void setTechVersion(String techVersion_) {
		techVersion = techVersion_;
	}

	public void setTEMP_FOLDER(String s) {
		TEMP_FOLDER = s;
	}

	public String getTEMP_FOLDER() {
		return TEMP_FOLDER;
	}

	/**
	 * Return if this generator is a documentation generator.
	 * 
	 * @return
	 */
	public boolean isDocumentationGenerator() {
		return false;
	}

	public void initialize(Map<String, String> generationParameters_, Map<String, Boolean> generatorOptions_, Map<String, String> configurationParameters_, DependencesManager dm,
			ComponentMonitor monitor) throws Exception {
		this.monitor = monitor;
		generationParameters = generationParameters_;
		generatorOptions = generatorOptions_;
		configurationParameters = configurationParameters_;
		techVersion = configurationParameters_.get("technologyVersion");
		id = configurationParameters_.get("generatorId");
		this.dm = dm;

		this.dm.setGeneratorID(id);

		// check parameters add warning if not set or empty
		// check generationParameters :
		for (Map.Entry<String, String> iterable_element : generationParameters.entrySet()) {
			if (iterable_element.getValue() == null || iterable_element.getValue().length() == 0) {
				monitor.getLog().addWarningLog("Generation Parameter not set", "parameter (" + iterable_element.getKey() + ") has not been set generation may be corrupted", "");
			}
		}
		// for (Map.Entry<String, String> iterable_element :
		// configurationParameters.entrySet()) {
		// if (iterable_element.getValue() != null &&
		// iterable_element.getValue().length() > 0) {
		// monitor.getLog().addWarningLog("Configuration Parameter not set",
		// "this parameter ("+iterable_element.getKey()+") has not been set generation may be corrupted",
		// "");
		// }
		// }

	}

	/**
	 * This method must be call after a successful generation to put an XML file
	 * into the gen path that will be used to know what have been deployed.
	 * Mainly use for log purpose.
	 * 
	 * @throws CoreException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public final void createStampFile() throws CoreException, FileNotFoundException, IOException {
		IFileHelper.refreshFolder(getTargetPath());
		IFolder ff = IFileHelper.createFolder(getTargetPath() + File.separator + techVersion);
		Element racine = new Element("toDeploy"); //$NON-NLS-1$
		Attribute classe = new Attribute("id", this.id); //$NON-NLS-1$
		racine.setAttribute(classe);
		Attribute date = new Attribute("date", new Date().toString()); //$NON-NLS-1$
		racine.setAttribute(date);
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(racine, new FileOutputStream(IFileHelper.getFile(IFileHelper.createFile(ff, this.id + "-stamp.xml")))); //$NON-NLS-1$
	}

	/**
	 * Return the absolute path to the generation target path
	 * 
	 * @return
	 */
	protected final String getTargetSystemPath() {
		IFolder ff = IFileHelper.getIFolder(getTargetPath());
		return ff.getRawLocation().makeAbsolute().toOSString();
	}

	/**
	 * Return the generation target folder (as File).
	 * 
	 * @return
	 */
	protected final File getTargetSystemFile() {
		IFolder ff = IFileHelper.getIFolder(getTargetPath());
		return ff.getRawLocation().makeAbsolute().toFile();
	}

	/**
	 * Return the target path (workspace path)
	 * 
	 * @return
	 */
	public final String getTargetPath() {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
	}

	public static boolean getGeneratorOptionValue(String key) {
		if (AbstractGenerator.generatorOptions.containsKey(key)) {
			return AbstractGenerator.generatorOptions.get(key);
		}
		return false;
	}

	public static String getConfigurationParameter(String key) {
		if (AbstractGenerator.configurationParameters.containsKey(key)) {
			return AbstractGenerator.configurationParameters.get(key);
		}
		return "";
	}

	public static String getGenerationParameter(String key) {
		if (AbstractGenerator.generationParameters.containsKey(key)) {
			return AbstractGenerator.generationParameters.get(key);
		}
		return "";
	}

	/**
	 * Return the absolute path for temp folder of generation
	 * 
	 * @return
	 */
	protected final String getTemporarySystemFolder() {
		return getTargetSystemPath() + File.separator + getTEMP_FOLDER();
	}

	protected final File getTemporarySystemFile() {
		return new File(getTargetSystemPath() + File.separator + getTEMP_FOLDER());
	}

	/**
	 * Return the tempory path to temp folder of generation (wokspace path)
	 * 
	 * @return
	 */
	protected final String getTemporaryFolder() {
		return getTargetPath() + File.separator + getTEMP_FOLDER();
	}

	protected final File getFinalFolder() {
		return new File(getTargetSystemPath() + File.separator + techVersion);
	}

	/**
	 * Print out information on generator (using System.out).
	 */
	public static void printConfiguration() {
		System.out.println("GenerationOptions :" + generatorOptions);
		System.out.println("GenerationParameters :" + generationParameters);
		System.out.println("ConfigurationParameters :" + configurationParameters);
		System.out.println("TechVersion :" + techVersion);
	}

	/**
	 * This method check if the user have the license to use this component.
	 * 
	 * @return true if the component can be used.
	 */
	public boolean check() {
		return true;
	}

	/**
	 * use DependencesManager to get files required by the generated package and
	 * copy them in the technology version folder
	 * 
	 * @throws Exception
	 */
	public void addDependences() throws Exception {
		// get dependences
		dm.copyDependencies(getTemporarySystemFile(), getTargetSystemFile());
		// dependences packages is now with other resources in the target folder
	}
}
