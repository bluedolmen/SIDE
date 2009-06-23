package com.bluexml.side.requirements.transformation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import fr.obeo.acceleo.chain.ActionSet;
import fr.obeo.acceleo.chain.ChainFactory;
import fr.obeo.acceleo.chain.EmfMetamodel;
import fr.obeo.acceleo.chain.Folder;
import fr.obeo.acceleo.chain.Generate;
import fr.obeo.acceleo.chain.Generator;
import fr.obeo.acceleo.chain.Log;
import fr.obeo.acceleo.chain.Model;
import fr.obeo.acceleo.chain.Repository;
import fr.obeo.acceleo.chain.impl.spec.CChain;
import fr.obeo.acceleo.ecore.factories.EFactory;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;
import fr.obeo.acceleo.tools.resources.Resources;

public class GenerateModel {

	private Set<String> templates = new HashSet<String>();
	
	private static final IGenFilter genFilter = new IGenFilter() {
		public boolean filter(java.io.File script, IFile targetFile, EObject object) throws CoreException {
			return true;
		}
	};
	
	public void execute(String model, IPath outputFolder, Resource metamodel) throws Exception {
		// Create chain
		CChain chain = new CChain();

		// Repository
		Repository repository = ChainFactory.eINSTANCE.createRepository();
		EFactory.eSet(chain, "repository", repository);

		// Action Set
		ActionSet actionSet = ChainFactory.eINSTANCE.createActionSet();
		EFactory.eAdd(chain, "actions", actionSet);

		// Model file
		Model amodel = ChainFactory.eINSTANCE.createModel();
		EFactory.eAdd(repository, "files", amodel);
		EFactory.eSet(amodel, "path", model);

		// Target folder
		Folder folder = ChainFactory.eINSTANCE.createFolder();

		EFactory.eAdd(repository, "files", folder);
		EFactory.eSet(folder, "path", outputFolder.toString());

		// Log
		Log log = ChainFactory.eINSTANCE.createLog();
		EFactory.eAdd(repository, "files", log);
		EFactory.eSet(log, "path", model + ".log");

		// Load metamodel
		EPackage ePackage = (EPackage) metamodel.getContents().get(0);
		metamodel.setURI(org.eclipse.emf.common.util.URI.createURI(ePackage.getNsURI()));
		EPackage.Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);

		// Set metamodel
		EmfMetamodel pim = ChainFactory.eINSTANCE.createEmfMetamodel();
		EFactory.eAdd(repository, "files", pim);
		EFactory.eSet(pim, "path", ePackage.getNsURI());

		for (String template : templates) {
			// Generator
			Generator generator = ChainFactory.eINSTANCE.createGenerator();
			EFactory.eAdd(repository, "files", generator);
			EFactory.eSet(generator, "path", template);

			// Action
			Generate gAction = ChainFactory.eINSTANCE.createGenerate();
			EFactory.eAdd(actionSet, "actions", gAction);
			EFactory.eSet(gAction, "folder", folder);
			EFactory.eSet(gAction, "log", log);
			EFactory.eSet(gAction, "metamodel", pim);
			EFactory.eSet(gAction, "model", amodel);
			EFactory.eSet(gAction, "generator", generator);
		}

		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile fchain = myWorkspaceRoot.getProject(outputFolder.segment(0)).getFile(model.substring(model.lastIndexOf("/")+1) + ".chain");
		org.eclipse.emf.common.util.URI chainURI = Resources.createPlatformResourceURI(fchain.getFullPath().toString());
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource chainResource = resourceSet.createResource(chainURI);
		chainResource.getContents().add(chain);
		chain.setFile(fchain);
		chainResource.save(Collections.EMPTY_MAP);

		chain.launch(genFilter, new NullProgressMonitor(), LaunchManager.create("run", true));
	}

	public void addTemplate(String template) {
		templates.add(template);
	}
}
