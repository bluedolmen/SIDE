package com.bluexml.side.forms.generator.alfresco.chiba;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.generator.packager.WarPatchPackager;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;
import com.bluexml.xforms.controller.messages.MsgPool;
import com.bluexml.xforms.generator.DataGenerator;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.mapping.MappingGenerator;

public class FormGenerator extends AbstractGenerator {
	private static final String GENERATOR_CODE = "CODE_GED_G_F_CHIBA";

	private static final String defaultModelID = "xformsModel";
	private static final String webappName = "xforms";

	private List<File> clazzModels;
	private List<File> formModels;
	private File xformGenerationFolder;
	private File mappingGenerationFolder;
	private File messagesFile;
	private boolean successfulInit;
	private ComponentMonitor monitor;
	private String webappContext;
	private List<DataGenerator> generators = new ArrayList<DataGenerator>();

	@Override
	public void initialize(Map<String, String> generationParameters_,
			Map<String, Boolean> generatorOptions_, Map<String, String> configurationParameters_,
			DependencesManager dm, ComponentMonitor monitor) throws Exception {
		super.initialize(generationParameters_, generatorOptions_, configurationParameters_, dm,
				monitor);

		this.monitor = monitor;

		successfulInit = false;
		setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + defaultModelID);
		File webappFolder = new File(getTemporarySystemFile(), "webapps" + File.separator
				+ webappName);
		xformGenerationFolder = new File(webappFolder.getAbsolutePath() + File.separator + "forms");
		mappingGenerationFolder = new File(webappFolder.getAbsolutePath() + File.separator
				+ "WEB-INF" + File.separator + "classes");

		// boolean shouldClean = getGeneratorOptionValue("clean");
		// if (shouldClean) {
		// FileUtils.forceDelete(mappingGenerationFolder);
		// FileUtils.forceDelete(xformGenerationFolder);
		// FileUtils.forceDelete(webappFolder);
		// }

		FileUtils.forceMkdir(xformGenerationFolder);
		FileUtils.forceMkdir(mappingGenerationFolder);

		String baseDir = xformGenerationFolder.getAbsolutePath();
		String resDir = mappingGenerationFolder.getAbsolutePath();

		File generateMappingFile = new File(resDir + File.separator + "mapping.xml");
		File generateRedirectFile = new File(resDir + File.separator + "redirect.xml");
		File generateCSSFile = new File(resDir + File.separator + "styles.css");

		XFormsGenerator xformsGenerator = new XFormsGenerator();
		MappingGenerator mappingGenerator = new MappingGenerator();
		generators.add(mappingGenerator);
		generators.add(xformsGenerator);

		xformsGenerator.setOutputFolder(baseDir);
		mappingGenerator.setOutputMappingFile(generateMappingFile.getAbsolutePath());
		mappingGenerator.setOutputCSSFile(generateCSSFile.getAbsolutePath());
		mappingGenerator.setOutputRedirectFile(generateRedirectFile.getAbsolutePath());

		// deal with messages.properties file
		String messagesFilePath = generationParameters
				.get("com.bluexml.side.Form.generator.xforms.chiba.messagesFilePath");
		if (messagesFilePath == null) {
			monitor.addWarningText("No messages file.");
		} else {
			try {
				messagesFile = new File(messagesFilePath);
				MsgPool.setMessagesFile(messagesFile.getAbsolutePath());
			} catch (Exception e) {
				monitor.addErrorText("Problem opening the messages file.");
			}
		}
		// deal with the webapp address (protocol, host, port, context)
		webappContext = generationParameters
				.get("com.bluexml.side.Form.generator.xforms.chiba.webappContext");
		if (StringUtils.trimToNull(webappContext) != null) {
			int pos = webappContext.lastIndexOf('/');
			int len = webappContext.length();
			// if trailing "/", remove it
			if (pos == (len - 1)) {
				webappContext = webappContext.substring(0, len - 1);
			}
			len = webappContext.length();
			pos = webappContext.lastIndexOf('/');
			String context = webappContext.substring(pos + 1, len);
			if (context.equals("forms")) {
				monitor.addErrorText("The context of your webapp SHOULD NOT be 'forms'!");
			}
		}
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
		// must build package
		// build archive from tmp folder
		WarPatchPackager wpp = new WarPatchPackager(IFileHelper.getIFolder(getTemporaryFolder()),
				buildModuleProperties(defaultModelID), techVersion, webappName);

		IFile chibaPackage = wpp.buildPackage();
		ArrayList<IFile> result = new ArrayList<IFile>();
		result.add(chibaPackage);
		return result;
	}

	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String id_mm)
			throws Exception {

		if (successfulInit == false) {
			return null;
		}
		clazzModels = getModels(modelsInfo, ClazzPackage.eNS_URI);
		formModels = getModels(modelsInfo, FormPackage.eNS_URI);

		File[] clazzFiles = clazzModels.toArray(new File[clazzModels.size()]);
		File[] formsFiles = formModels.toArray(new File[formModels.size()]);
		boolean simplifyClasses = true;
		boolean renderDataBeforeWorkflow = true;
		try {
			com.bluexml.xforms.generator.FormGenerator formGenerator = new com.bluexml.xforms.generator.FormGenerator(
					clazzFiles, formsFiles, LogFactory.getLog(FormGenerator.class),
					simplifyClasses, renderDataBeforeWorkflow);
			formGenerator.generate(generators, monitor);
		} catch (RuntimeException e) {
			monitor.addErrorTextAndLog("ERROR :" + e.getMessage(), e, "");
		}
		return new ArrayList<IFile>();
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

	public Properties buildModuleProperties(String rootPackage) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); //$NON-NLS-1$
		Properties props = new Properties();
		props.put("module.id", "SIDE_xformsExtension_" + rootPackage); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("module.version", ""); //$NON-NLS-1$
		props.put("module.title", ""); //$NON-NLS-1$
		props.put("module.description", "xForm plugin generated at " + sdf.format(now)); //$NON-NLS-1$

		return props;
	}

}
