package com.bluexml.side.util.generator.acceleo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.generator.GeneratorException;
import com.bluexml.side.util.generator.acceleo.chain.CustomCChain;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.model.merge.MergeUtils;

import fr.obeo.acceleo.chain.ActionSet;
import fr.obeo.acceleo.chain.ChainFactory;
import fr.obeo.acceleo.chain.EmfMetamodel;
import fr.obeo.acceleo.chain.Folder;
import fr.obeo.acceleo.chain.Generate;
import fr.obeo.acceleo.chain.Generator;
import fr.obeo.acceleo.chain.Log;
import fr.obeo.acceleo.chain.Model;
import fr.obeo.acceleo.chain.Repository;
import fr.obeo.acceleo.ecore.factories.EFactory;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;
import fr.obeo.acceleo.tools.classloaders.AcceleoClassLoader;

public abstract class AbstractAcceleoGenerator extends AbstractGenerator {
	protected String mergedFilePath = "mergedFile"; //$NON-NLS-1$
	protected Map<String, List<IFile>> groupedModels = null;
	protected List<IFile> generatedFiles;

	protected final String projectName = ".side_generation"; //$NON-NLS-1$
	private static final String DEFAULT_ENCODING = "ISO-8859-1"; //$NON-NLS-1$
	private String fileEncoding = System.getProperty("file.encoding"); //$NON-NLS-1$
	protected String versionProperty = null;
	protected final static String MAIN_OPTION = "generator.main";
	protected boolean useOverhead = false;
	protected static final String OVER_HEAD_MODEL = "overHeadModel";
	/**
	 * use to give an version number to this generation package
	 * 
	 * @return
	 */
	public String getVersioNumber() {
		// default value is defined in extension plugin declaration see plugin.xml
		return getGenerationParameter(versionProperty);
	}

	private static final IGenFilter genFilter = new IGenFilter() {
		public boolean filter(java.io.File script, IFile targetFile, EObject object) throws CoreException {
			return true;
		}
	};

	protected final List<String> getTemplates() throws Exception {
		List<String> alltemplates = new ArrayList<String>();

		if (getGeneratorOptionValue(MAIN_OPTION)) {
			List<String> mainTemplates = getMainTemplates();

			alltemplates.addAll(mainTemplates);

		}
		List<String> optionalTemplates = getOptionalTemplates();

		alltemplates.addAll(optionalTemplates);

		// apply templates substitution in the right order
		boolean fails = false;
		List<Map<String, String>> templatesSubstitution = getTemplatesSubstitution();
		System.out.println("AbstractAcceleoGenerator.getTemplates() before substitution " + alltemplates.size());
		for (Map<String, String> overrideTemplates : templatesSubstitution) {
			Set<Entry<String, String>> entrySet = overrideTemplates.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
				boolean remove = alltemplates.remove(key);
				if (remove) {
					// so only add the template if the template to replace exists and have been removed
					// this avoid to add optional template even if the option is not selected 
					String value = entry.getValue();
					// if null the template is removed without replacement
					if (value != null) {
						alltemplates.add(value);
					}
				} else {
					fails = true;
					if (debugMode()) {
						System.err.println("AbstractAcceleoGenerator.getTemplates()\n" + "Fail to remove " + key + " for generator :\n" + this.getClass().getName());
						//					throw new Exception("Fail to remove " + key + " for generator :\n" + this.getClass().getName());
					}

				}
			}
		}

		if (fails && debugMode()) {
			throw new Exception("fail to remove some template ! (to avoid fake result select all generator options)");
		}
		System.out.println("AbstractAcceleoGenerator.getTemplates() after substitution " + alltemplates.size());
		return alltemplates;
	}

	/**
	 * method to build a substitution map
	 * 
	 * @return
	 */
	protected List<Map<String, String>> getTemplatesSubstitution() {
		return new ArrayList<Map<String, String>>();
	}

	/**
	 * @return
	 */
	protected List<String> getOptionalTemplates() {
		return new ArrayList<String>();
	}

	abstract protected List<String> getMainTemplates();

	abstract protected String getMetamodelURI();

	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo, String id_metamodel) {
		return modelsInfo.containsKey(id_metamodel);
	}

	/**
	 * this implementation take care of multi-model
	 */

	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String id_metamodel) throws Exception {

		// System.out.println("Generate Map String");

		Collection<IFile> results = new ArrayList<IFile>();
		if (modelsInfo.get(id_metamodel) != null && modelsInfo.get(id_metamodel).size() > 0) {
			List<IFile> models = modelsInfo.get(id_metamodel);
			groupedModels = MergeUtil.groupByRootPackage(models);
			List<IFile> models_merged = new ArrayList<IFile>();
			for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
				String rootName = l.getKey();
				List<IFile> models_ = l.getValue();
				monitor.getLog().addModelsLog(models_);
				IFile mergedModel = merging(models_, rootName);
				models_merged.add(mergedModel);

				if (!useOverhead) {
					// initialize generator we must change the TEMP_FOLDER
					prepareTempFolder(rootName);
					// generate
					results.addAll(generate(mergedModel));
				}
			}

			if (useOverhead) {
				prepareTempFolder(OVER_HEAD_MODEL);
				// we create a new model that contains all accepted models before execute the generation
				IFile overHeadModel = overHeadingModels(models_merged);
				// generate
				results.addAll(generate(overHeadModel));
			}
		}
		return results;
	}

	protected void prepareTempFolder(String rootName) throws Exception, CoreException {
		setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName); //$NON-NLS-1$
		// clean directory before generate, needed if cleaning option is
		// not enable
		File wkdir = getTemporarySystemFile();
		if (wkdir.exists()) {
			boolean result = FileHelper.deleteFile(wkdir);
			// update IFolder
			IFileHelper.refreshFolder(wkdir);
			if (!result) {
				monitor.getLog().addWarningLog(Activator.Messages.getString("AbstractAcceleoGenerator_7"), Activator.Messages.getString("AbstractAcceleoGenerator_8"), ""); //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
			}
		}
	}

	protected IFile overHeadingModels(List<IFile> models) throws Exception {
		throw new UnsupportedOperationException("This Implementation do not provide support of overheading generation");
	}

	protected IFile merging(List<IFile> models, String rootName) throws Exception {
		if (models.size() == 1) {
			return models.get(0);
		} else {
			monitor.addTextAndLog(Activator.Messages.getString("AbstractAcceleoGenerator_10"), ""); //$NON-NLS-1$//$NON-NLS-2$
			// create resource for merged file
			IPath p = models.get(0).getParent().getFullPath();
			p = p.append(mergedFilePath + "_" + rootName + "." + models.get(0).getFileExtension()); //$NON-NLS-1$
			IFile mergedIFile = IFileHelper.getIFile(p);
			// do merge
			MergeUtils.merge(mergedIFile, models, this.getClass().getClassLoader());
			monitor.addTextAndLog(Activator.Messages.getString("AbstractAcceleoGenerator_13"), ""); //$NON-NLS-1$//$NON-NLS-2$
			return mergedIFile;
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<IFile> generate(IFile model) throws Exception {
		AcceleoClassLoader.setPreferredClassLoader(this.getClass().getClassLoader());
		// System.out.println("Generate IFile model");

		// References to files in the project
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		// System.out.println("Iworkspace: " + myWorkspaceRoot );
		// Temporary project
		IProject tmpProject = myWorkspaceRoot.getProject(projectName);

		// System.out.println("tmpProject: " + tmpProject );
		// System.out.println("tmpProject.exists: " + tmpProject.exists() );

		if (tmpProject.exists()) {
			tmpProject.delete(true, null);
		}
		// create and open if necessary
		if (!tmpProject.exists()) {
			tmpProject.create(null);
		}

		// System.out.println("tmpProject.isOpen(): " + tmpProject.isOpen() );
		if (!tmpProject.isOpen()) {
			tmpProject.open(null);
		}

		// System.out.println("tmpProject.exists(): " + tmpProject.exists() +
		// " -> " + tmpProject.getFullPath());

		// CChain chain = new ChainFactory.eINSTANCE.createChain();
		CustomCChain chain = new CustomCChain();

		// Repository
		Repository repository = ChainFactory.eINSTANCE.createRepository();
		EFactory.eSet(chain, "repository", repository); //$NON-NLS-1$

		// Action Set
		ActionSet actionSet = ChainFactory.eINSTANCE.createActionSet();
		EFactory.eAdd(chain, "actions", actionSet); //$NON-NLS-1$

		// Model file
		Model modelPath = ChainFactory.eINSTANCE.createModel();
		EFactory.eAdd(repository, "files", modelPath); //$NON-NLS-1$
		EFactory.eSet(modelPath, "path", model.getFullPath().toString()); //$NON-NLS-1$
		model.refreshLocal(-1, null);

		// Target folder
		Folder folder = ChainFactory.eINSTANCE.createFolder();

		// System.out.println("folder: " + folder.getPath());

		EFactory.eAdd(repository, "files", folder); //$NON-NLS-1$
		String generationPath = getTemporaryFolder();
		if (generationPath == null || generationPath.length() == 0) {
			monitor.getLog().addErrorLog("No Target path setted.", "There is no target path setted for generation.", null); //$NON-NLS-1$ //$NON-NLS-2$
			throw new Exception("Target path must be setted !"); //$NON-NLS-1$
		}

		new File(IFileHelper.getSystemFolderPath(generationPath)).mkdirs();
		EFactory.eSet(folder, "path", generationPath); //$NON-NLS-1$

		// Log
		File flog = new File(IFileHelper.getSystemFolderPath(monitor.getLog().getGeneratorLogFile()));
		flog.delete();
		Log log = ChainFactory.eINSTANCE.createLog();
		EFactory.eAdd(repository, "files", log); //$NON-NLS-1$
		EFactory.eSet(log, "path", monitor.getLog().getGeneratorLogFile()); //$NON-NLS-1$

		// Metamodel file
		EmfMetamodel pim = ChainFactory.eINSTANCE.createEmfMetamodel();
		EFactory.eAdd(repository, "files", pim); //$NON-NLS-1$
		EFactory.eSet(pim, "path", getMetamodelURI()); //$NON-NLS-1$

		for (String templateFile : getTemplates()) {

			System.out.println("Templates: " + templateFile);
			// Generator
			Generator generator = ChainFactory.eINSTANCE.createGenerator();
			EFactory.eAdd(repository, "files", generator); //$NON-NLS-1$
			EFactory.eSet(generator, "path", templateFile); //$NON-NLS-1$

			// Action
			Generate gAction = ChainFactory.eINSTANCE.createGenerate();
			EFactory.eAdd(actionSet, "actions", gAction); //$NON-NLS-1$
			EFactory.eSet(gAction, "folder", folder); //$NON-NLS-1$
			EFactory.eSet(gAction, "log", log); //$NON-NLS-1$
			EFactory.eSet(gAction, "metamodel", pim); //$NON-NLS-1$
			EFactory.eSet(gAction, "model", modelPath); //$NON-NLS-1$
			EFactory.eSet(gAction, "generator", generator); //$NON-NLS-1$
		}

		// Register the default resource factory -- only needed for
		// stand-alone!
		IFile fchain = tmpProject.getFile("side_generation.chain"); //$NON-NLS-1$
		// System.out.println("fchain.getFullPath(): " + fchain.getFullPath());
		// URI chainURI =
		// Resources.createPlatformResourceURI(fchain.getFullPath().toString());
		// System.out.println("log1");
		ResourceSet resourceSet = new ResourceSetImpl();
		// System.out.println("log2");
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// System.out.println("log3");
		// Resource chainResource = resourceSet.createResource(chainURI);
		// System.out.println("log4");
		// chainResource.getContents().add(chain);
		// System.out.println("log5");
		chain.setFile(fchain);
		// System.out.println("log6");
		// chainResource.save(Collections.EMPTY_MAP);
		// System.out.println("log7");
		// System.out.println("BeforeGen");
		// AbstractGenerator.printConfiguration();
		// System.out.println("log8");
		// try {
		if (monitor != null) {
			chain.launch(genFilter, monitor, LaunchManager.create("run", false)); //$NON-NLS-1$
		} else {
			chain.launch(genFilter, new NullProgressMonitor(), LaunchManager.create("run", false)); //$NON-NLS-1$
		}

		// } catch (CoreException e) {
		// e.printStackTrace();
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		// System.out.println("log9");
		// System.out.println("AfterGen");
		// AbstractGenerator.printConfiguration();

		// files is generated we must update folder before do anything else

		generatedFiles = (List<IFile>) chain.getGeneratedFiles();
		// List<?> generatedFiles = chain.getGeneratedFiles();
		// System.out.println("log10");
		for (Object file : generatedFiles) {
			filter((IFile) file);
		}
		// System.out.println("generatedFiles: " + generatedFiles.toString());

		// IProject myproject = myWorkspaceRoot.getProject(".side_generation");
		tmpProject.delete(true, null);

		Collection<IFile> result = new ArrayList<IFile>();
		for (Object o : chain.getGeneratedFiles()) {
			if (o instanceof IFile) {
				result.add((IFile) o);
			}
		}
		// test if generator has error or not
		if (flog.exists()) {
			monitor.addTextAndLog(Activator.Messages.getString("AbstractAcceleoGenerator_5"), flog.getAbsolutePath()); //$NON-NLS-1$
			throw new GeneratorException(Activator.Messages.getString("AbstractAcceleoGenerator_39")); //$NON-NLS-1$
		}
		// System.out.println("result: " + result.toString());
		return result;
	}

	public void filter(IFile file) throws Exception {

		// System.out.println("filter file: " + file.toString());

		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			File source = IFileHelper.getFile(file);
			// System.out.println("FILTER :: file :" + source.getName());

			InputStream is = new FileInputStream(source);
			BufferedInputStream bis = new BufferedInputStream(is);
			InputStreamReader isr = new InputStreamReader(bis, fileEncoding);
			br = new BufferedReader(isr);

			File destination = new File(source.getAbsolutePath() + ".fixed"); //$NON-NLS-1$
			pw = new PrintWriter(destination, DEFAULT_ENCODING);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				pw.println(ligne);
			}
			pw.close();
			br.close();

			source.delete();
			destination.renameTo(source);
		} catch (Exception e) {
			e.printStackTrace();

			try {
				pw.close();
			} catch (Throwable e1) {
			}
			try {
				br.close();
			} catch (Throwable e1) {
			}
			throw new Exception(Activator.Messages.getString("AbstractAcceleoGenerator_42"), e); //$NON-NLS-1$
		}
	}

	public static String getProperty(String key, String defaultValue) throws FileNotFoundException, IOException {
		String result = getGenerationParameter(key);
		if (result == null || result.equals("")) {
			result = defaultValue;
		}
		return result;
	}

}
