package com.bluexml.side.forms.generator.alfresco.chiba;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;
import com.bluexml.xforms.generator.DataGenerator;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.mapping.MappingGenerator;

public class FormGenerator extends AbstractGenerator {
	private static final String GENERATOR_CODE = "CODE_GED_G_F_CHIBA";

	private List<File> clazzModels;
	private List<File> formModels;
	private File workFolder;
	private File resourcesFolder;
	private File messagesFilePath;
	private boolean successfulInit;
	private List<DataGenerator> generators = new ArrayList<DataGenerator>();

	@Override
	public void initialize(Map<String, String> generationParameters_,
			Map<String, Boolean> generatorOptions_, Map<String, String> configurationParameters_,
			DependencesManager dm, ComponentMonitor monitor) throws Exception {
		super.initialize(generationParameters_, generatorOptions_, configurationParameters_, dm,
				monitor);

		successfulInit = false;
		try {
			initWorkFolder();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String baseDir = workFolder.getAbsolutePath();
		String resDir = resourcesFolder.getAbsolutePath();

		File generateMappingFile = new File(resDir + File.separator + "mapping.xml");
		File generateRedirectFile = new File(resDir + File.separator + "redirect.xml");
		File generateCSSFile = new File(resDir + File.separator + "styles.css");

		XFormsGenerator xformsGenerator = new XFormsGenerator();
		MappingGenerator mappingGenerator = new MappingGenerator();
		generators.add(mappingGenerator);
		generators.add(xformsGenerator);

		xformsGenerator.setOutputFolder(baseDir);
//		MsgPool.setMessagesFile(messagesFilePath.getAbsolutePath());
		mappingGenerator.setOutputMappingFile(generateMappingFile.getAbsolutePath());
		mappingGenerator.setOutputCSSFile(generateCSSFile.getAbsolutePath());
		mappingGenerator.setOutputRedirectFile(generateRedirectFile.getAbsolutePath());

		successfulInit = true;
	}

	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo, String id_metamodel) {
		return modelsInfo.containsKey(ClazzPackage.eNS_URI)
				|| modelsInfo.containsKey(FormPackage.eNS_URI);
	}

	public Collection<IFile> complete() throws Exception {
		// FIXME should return WAR IFile
		return new ArrayList<IFile>();
	}

	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String id_mm) {

		if (successfulInit == false) {
			return null;
		}
		clazzModels = getModels(modelsInfo, ClazzPackage.eNS_URI);
		formModels = getModels(modelsInfo, FormPackage.eNS_URI);

		File[] clazzFiles = clazzModels.toArray(new File[clazzModels.size()]);
		File[] formsFiles = formModels.toArray(new File[formModels.size()]);
		boolean simplifyClasses = true;
		boolean renderDataBeforeWorkflow = true;

		com.bluexml.xforms.generator.FormGenerator formGenerator = new com.bluexml.xforms.generator.FormGenerator(
				clazzFiles, formsFiles, LogFactory.getLog(FormGenerator.class), simplifyClasses,
				renderDataBeforeWorkflow);
		formGenerator.generate(generators);

		return null;
	}

	/**
	 * Sets up the generation environment. Create the folders necessary for the generation.
	 * 
	 * @throws IOException
	 */
	private void initWorkFolder() throws IOException {
		String path = getTargetSystemPath();
		if (path == null || path.length() == 0)
			throw new RuntimeException("Target path must be set !");
		path += File.separatorChar + getTechVersion();
		workFolder = new File(path);
		resourcesFolder = new File(path + File.separator + "resources");
		FileUtils.forceMkdir(workFolder);
		FileUtils.forceMkdir(resourcesFolder);
//		messagesFilePath = new File(generationParameters.get("messagesFilePath"));
	}

	/**
	 * Filters a list of models wrt a URI and returns a list of files.
	 * 
	 * @param modelsInfo
	 * @param nsURI
	 * @return the list of files from the models that match the URI
	 */
	private List<File> getModels(Map<String, List<IFile>> modelsInfo, String nsURI) {
		List<File> modelsFiles = new ArrayList<File>();
		List<IFile> modelsIFile = modelsInfo.get(nsURI);
		if (modelsIFile != null) {
			for (IFile file : modelsIFile) {
				String fileLocation = file.getLocation().toFile().getAbsolutePath();
				modelsFiles.add(new File(fileLocation));
			}
		}
		return modelsFiles;
	}

}
