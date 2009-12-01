package org.rwm.m2t;


import java.util.ArrayList;
import java.util.List;

import org.acceleo.engine.IModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Utility class to handle source model.
 * 
 * @author Benjamin CHEVALLEREAU
 * 
 */
public class Model implements IModel {

	/**
	 * Model resource.
	 */
	private Resource resource;

	/**
	 * Model nsUri.
	 */
	private String nsURI;

	/**
	 * Constructor.
	 * 
	 * @param resourceSet
	 *            EMF resourceSet containing source model resource
	 * @param metaModelclassName
	 *            Metamodel class name
	 * @param mmURI 
	 * @param modelFile
	 *            File containing source model
	 * @throws Exception
	 */
	public Model(org.eclipse.emf.ecore.resource.ResourceSet resourceSet, URI modelURI, String mmURI) throws Exception {
		// Register the metamodel
		//Class<?> vClass = Class.forName(metaModelclassName);
		//Object vObject = vClass.getField("eINSTANCE"); //$NON-NLS-1$

		nsURI = mmURI;

		// Demand load the resource for this file.
		resource = resourceSet.getResource(modelURI, true);

	}

	/**
	 * {@inheritDoc}
	 */
	public String getNsURI() {
		return nsURI;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EObject> getRoots() {
		List<EObject> ret = new ArrayList<EObject>();
		for (int i = 0; i < resource.getContents().size(); ++i) {
			ret.add(resource.getContents().get(i));
		}
		return ret;
	}

}
