package org.rwm.m2t;

import org.acceleo.engine.EMT;
import org.acceleo.engine.eval.LaunchManager;
import org.acceleo.gen.AcceleoGenerate;
import org.acceleo.gen.extension.Extension;
import org.acceleo.tools.resources.Resources;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("ERROR : Need 4 arguments");
			System.out.println("1 - The template model path");
			System.out.println("2 - The model path");
			System.out.println("3 - The target meta model path");
			System.out.println("4 - The out directory path");
		} else {
			String templateModel = args[0];
			String model = args[1];
			String targetMetamodel = args[2];
			String targetDirectory = args[3];

			// Initialization
			ResourceSet resourceSet = Extension.getResourceSet();
			Model myModel = null;
			EMT emtFreemind = null;
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(org.eclipse.emf.ecore.resource.Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

			// Load template model
			URI emtURI = Resources.getResourceURI(templateModel);
			emtFreemind = new EMT(resourceSet, emtURI, Thread.currentThread().getContextClassLoader());

			// Load target metamodel
			URI mmURI = Resources.getResourceURI(targetMetamodel);
			Resource mm = resourceSet.getResource(mmURI, true);
			EPackage ePackage = (EPackage) mm.getContents().get(0);
			mm.setURI(URI.createURI(ePackage.getNsURI()));
			EPackage.Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);

			// Load input model
			URI modelURI = Resources.getResourceURI(model); //$NON-NLS-1$
			try {
				myModel = new Model(resourceSet, modelURI, ePackage.getNsURI());
				// Initialize generation
				String genRootDir = targetDirectory;
				Extension.setResourceSet(resourceSet);
				AcceleoGenerate generator = new AcceleoGenerate();
				generator.setGenRootDir(genRootDir);
				// Generate
				generator.generate(emtFreemind, myModel.getRoots().get(0), true, LaunchManager.create("run", true));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
