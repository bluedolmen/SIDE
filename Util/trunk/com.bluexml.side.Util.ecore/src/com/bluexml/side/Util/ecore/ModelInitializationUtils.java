package com.bluexml.side.Util.ecore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class ModelInitializationUtils {
	/**
	 * Save a new model in file.
	 * @param file
	 * @param rootObject
	 * @throws IOException
	 */
	public static void saveModel(File file, EObject rootObject) throws IOException {
		FileOutputStream os = new FileOutputStream(file);
		ResourceSetImpl set = new ResourceSetImpl();
		Resource outputResource = set.createResource(URI.createFileURI(file
				.getCanonicalPath()));
		outputResource.getContents().add(rootObject);
		outputResource.save(os, null);
		os.close();
	}

	/**
	 * Open the specified model.
	 * @param model
	 * @return
	 * @throws IOException
	 */
	public static EList<?> openModel(IFile model) throws IOException {
		ResourceSetImpl set = new ResourceSetImpl();
		Resource inputResource = set.createResource(URI
				.createFileURI(model.getRawLocation().toFile()
						.getCanonicalPath()));
		inputResource.load(null);
		EList<?> l = inputResource.getContents();
		return l;
	}
}
