package com.bluexml.side.util.generator.acceleo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.generator.ConflitResolverHelper;
import com.bluexml.side.util.generator.acceleo.chain.CustomCChain;
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
import fr.obeo.acceleo.tools.resources.Resources;

public abstract class AbstractAcceleoGenerator extends AbstractGenerator {
	protected String mergedFilePath = "mergedFile";
	protected Map<String, List<IFile>> groupedModels = null;
	protected List<IFile> generatedFiles;
	ConflitResolverHelper cresolver;
	protected final String projectName = ".side_generation";
	private static final String DEFAULT_ENCODING = "ISO-8859-1";
	private String fileEncoding = System.getProperty("file.encoding");
	protected static final String versionProperty = null;
	
	/**
	 * use to give an version number to this generation package
	 * @return
	 */
	public String getVersioNumber() {
		String vn = getGenerationParameter(versionProperty);
		if (vn == null || vn.equals("")) {
			vn = "1.0";
		}
		return vn;
	}
	
	private static final IGenFilter genFilter = new IGenFilter() {
		public boolean filter(java.io.File script, IFile targetFile, EObject object) throws CoreException {
			return true;
		}
	};

	

	abstract protected List<String> getTemplates();

	abstract protected String getMetamodelURI();

	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo, String id_metamodel) {
		return modelsInfo.containsKey(id_metamodel);
	}

	/**
	 * this implementation take care of multi-model
	 */

	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String id_metamodel) throws Exception {
		Collection<IFile> results = new ArrayList<IFile>();
		if (modelsInfo.get(id_metamodel) != null && modelsInfo.get(id_metamodel).size() > 0) {
			List<IFile> models = modelsInfo.get(id_metamodel);
			groupedModels = MergeUtil.groupByRootPackage(models);
			for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
				String rootName = l.getKey();
				List<IFile> models_ = l.getValue();
				IFile mergedModel = merging(models_);
				// initialize generator we must change the TEMP_FOLDER
				setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName);
				// generate
				results.addAll(generate(mergedModel));
			}
		}
		return results;
	}

	protected IFile merging(List<IFile> models) throws IOException {
		if (models.size() == 1) {
			return models.get(0);
		} else {
			// create resource for merged file
			IFile mergedIFile = IFileHelper.getIFile(models.get(0).getParent().getRawLocation().makeAbsolute().toOSString() + File.separator + mergedFilePath + File.separator + models.get(0).getFileExtension());
			// do merge
			MergeUtils.merge(mergedIFile, models, this.getClass().getClassLoader());
			return mergedIFile;
		}
	}

	public Collection<IFile> generate(IFile model) throws Exception {

		// References to files in the project
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		// Temporary project
		IProject tmpProject = myWorkspaceRoot.getProject(projectName);

		// create and open if necessary
		if (!tmpProject.exists()) {
			tmpProject.create(null);
		}
		if (!tmpProject.isOpen()) {
			tmpProject.open(null);
		}

		// CChain chain = new ChainFactory.eINSTANCE.createChain();
		CustomCChain chain = new CustomCChain();

		// Repository
		Repository repository = ChainFactory.eINSTANCE.createRepository();
		EFactory.eSet(chain, "repository", repository);

		// Action Set
		ActionSet actionSet = ChainFactory.eINSTANCE.createActionSet();
		EFactory.eAdd(chain, "actions", actionSet);

		// Model file
		Model modelPath = ChainFactory.eINSTANCE.createModel();
		EFactory.eAdd(repository, "files", modelPath);
		EFactory.eSet(modelPath, "path", model.getFullPath().toString());

		// Target folder
		Folder folder = ChainFactory.eINSTANCE.createFolder();

		EFactory.eAdd(repository, "files", folder);
		String path = getTemporaryFolder();
		if (path == null || path.length() == 0) {
			addErrorLog("No Target path setted.", "There is no target path setted for generation.", null);
			throw new Exception("Target path must be setted !");
		}

		new File(IFileHelper.getSystemFolderPath(path)).mkdirs();
		EFactory.eSet(folder, "path", path);

		// Log
		Log log = ChainFactory.eINSTANCE.createLog();
		EFactory.eAdd(repository, "files", log);
		EFactory.eSet(log, "path", getLogFile());

		// Metamodel file
		EmfMetamodel pim = ChainFactory.eINSTANCE.createEmfMetamodel();
		EFactory.eAdd(repository, "files", pim);
		EFactory.eSet(pim, "path", getMetamodelURI());

		for (String templateFile : getTemplates()) {
			// Generator
			Generator generator = ChainFactory.eINSTANCE.createGenerator();
			EFactory.eAdd(repository, "files", generator);
			EFactory.eSet(generator, "path", templateFile);

			// Action
			Generate gAction = ChainFactory.eINSTANCE.createGenerate();
			EFactory.eAdd(actionSet, "actions", gAction);
			EFactory.eSet(gAction, "folder", folder);
			EFactory.eSet(gAction, "log", log);
			EFactory.eSet(gAction, "metamodel", pim);
			EFactory.eSet(gAction, "model", modelPath);
			EFactory.eSet(gAction, "generator", generator);
		}

		// Register the default resource factory -- only needed for
		// stand-alone!
		IFile fchain = tmpProject.getFile("side_generation.chain");
		URI chainURI = Resources.createPlatformResourceURI(fchain.getFullPath().toString());
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource chainResource = resourceSet.createResource(chainURI);
		chainResource.getContents().add(chain);
		chain.setFile(fchain);
		chainResource.save(Collections.EMPTY_MAP);
		// System.out.println("BeforeGen");
		// AbstractGenerator.printConfiguration();
		chain.launch(genFilter, new NullProgressMonitor(), LaunchManager.create("run", true));
		// System.out.println("AfterGen");
		// AbstractGenerator.printConfiguration();
		generatedFiles = (List<IFile>) chain.getGeneratedFiles();
		List<?> generatedFiles = chain.getGeneratedFiles();
		for (Object file : generatedFiles) {
			filter((IFile) file);
		}

		IProject myproject = myWorkspaceRoot.getProject(".side_generation");
		myproject.delete(true, null);

		Collection<IFile> result = new ArrayList<IFile>();
		for (Object o : chain.getGeneratedFiles())
			if (o instanceof IFile)
				result.add((IFile) o);

		return result;
	}

	public void filter(IFile file) {
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			File source = file.getLocation().toFile();

			InputStream is = new FileInputStream(source);
			BufferedInputStream bis = new BufferedInputStream(is);
			InputStreamReader isr = new InputStreamReader(bis, fileEncoding);
			br = new BufferedReader(isr);

			File destination = new File(source.getAbsolutePath() + ".fixed");
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
		}
	}

	
	abstract public Collection<IFile> buildPackages(String modelId) throws Exception;
	
	public Collection<IFile> complete() throws Exception {
		for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
			String rootName = l.getKey();
			setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName);
			Collection<IFile> packageFile = buildPackages(rootName);
			generatedFiles.addAll(packageFile);
			for (IFile p : packageFile) {
				addFileGeneratedLog(p.getName() + " created.", p.getName() + " created in " + p.getFullPath(), IFileHelper.getFile(p).toURI());
			}
			
		}
		for (IFile f : generatedFiles) {
			addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI());
		}
		return generatedFiles;
	}
	
	public ConflitResolverHelper getCresolver() {
		if (cresolver == null) {
			cresolver = new ConflitResolverHelper(getTargetPath(), getTemporaryFolder());
		}
		return cresolver;
	}

	protected List<IFile> searchForConflict() {
		return getCresolver().searchForConflict(generatedFiles);
	}

}
