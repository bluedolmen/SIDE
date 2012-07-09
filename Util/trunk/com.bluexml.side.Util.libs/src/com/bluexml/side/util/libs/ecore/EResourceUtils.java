/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 *******************************************************************************/
package com.bluexml.side.util.libs.ecore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

//import com.bluexml.generator.alfresco.chain.Model;

/**
 * This class has utility method for model resource
 * It is only aimed to load,save or manage Resource coming from a model file
 * Resource are always loaded and saved using {@link XMIResourceFactoryImpl}
 * 
 * @author Constantin Madola
 */

public class EResourceUtils {

	// TODO VisibilityCheck

	/**********************
	 * <b>CREATING</b> ************************
	 * <b>Creating :<b> a resource means returning a new resource
	 * referencing a given file (existing or not)
	 * The content <b><i>IS NOT LOADED</i></b> after a "create"
	 * the resource is just "initialized"
	 * NOTE :Enhancement can be made by using InputStream
	 ***************************************************************/

	/**
	 * Create A Resource From a model
	 * note that the resource is not loaded, just created
	 * 
	 * @param model
	 * @param metaModelUri
	 * @return
	 * @throws IOException
	 *             _________________ ABANDON
	 *             ____________________________________
	 *             public static Resource createResource(Model model) throws
	 *             IOException{
	 *             Resource result = null;
	 *             if(model !=null){
	 *             // model aperas whith a relative path
	 *             // to folowing manipulation means only to get an absolute
	 *             path
	 *             IPath rawModelFileLocation =
	 *             toIFile(model.getPath()).getRawLocation();
	 *             File modelFile = rawModelFileLocation.toFile();
	 *             result = createResource(modelFile.getAbsolutePath());
	 *             }
	 *             else{
	 *             throw new NullPointerException("model is null");
	 *             }
	 *             return result;
	 *             }
	 */

	/**
	 * Create A Resource From a model absolute path as String
	 * the method does verify that a file exist
	 * note that the resource is not loaded, just created
	 * 
	 * @param model
	 * @param metaModelUri
	 * @return
	 * @throws IOException
	 */
	public static Resource createResource(String modelAbsolutePath) throws IOException {
		Resource result = null;
		if (modelAbsolutePath != null) {
			URI absoluteModelFileUri = URI.createFileURI(modelAbsolutePath);
			result = createResource(absoluteModelFileUri);
		} else {
			throw new NullPointerException("model is null");
		}
		return result;
	}

	/**
	 * Create a Resource From an URI in a Default ResouceSet
	 * 
	 * @param modelFileUri
	 *            the URI of the file we wish to load
	 * @return a resource
	 */
	public static Resource createResource(URI modelFileUri) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		return createResource(modelFileUri, resourceSet);
	}

	/**
	 * Create a Resource From an URI in a given ResourceSet
	 * Will load XMiFactoryResourceImpl for default extension if
	 * not already the case
	 * 
	 * @param modelFileUri
	 *            the URI of the file we wish to load
	 * @param resourceSet
	 *            the resourceSet from wich we create the Resource
	 * @return a resource
	 */
	public static Resource createResource(URI modelFileUri, ResourceSet resourceSet) {
		if (!isFactoryXmiResourceImplLoaded(resourceSet)) {
			loadFactoryXmiResourceImpl(resourceSet);
		}
		return resourceSet.createResource(modelFileUri);
	}

	/********************
	 * OPENING METHOD ********************
	 * Opening means to return a new resource
	 * this reosurce <b><i>IS LOADED</i></b>
	 * If the map is Empty, We use A default map
	 * Opening methods are rewriting in the same manners as
	 * creating method
	 * Note :Enhancement can be made by using InputSteam
	 *********************************************************** 
	 _________________ ABANDON ____________________________________
	 * Return a new resource created with the given model
	 * the resource is loaded using the given map
	 * 
	 * @param resourceURI
	 * @param resourceSet
	 * @param map
	 * @return
	 * @throws IOException
	 */
	/*
	 * public static Resource openModel(Model model,Map<?,?> map) throws
	 * IOException{
	 * // model aperas whitha relative path
	 * // to folowing manipulation mains only to get an aabsolute path
	 * IPath rawModelFileLocation = toIFile(model.getPath()).getRawLocation();
	 * File modelFile = rawModelFileLocation.toFile();
	 * Resource result =
	 * openModel(URI.createFileURI(modelFile.getAbsolutePath()),new
	 * ResourceSetImpl(),map);
	 * return result;
	 * }
	 */
	/**
	 * Return a new resource created with the given model
	 * the resource is loaded using the given map
	 * 
	 * @param resourceURI
	 * @param resourceSet
	 * @param map
	 * @return
	 * @throws IOException
	 *             _________________ ABANDON
	 *             ____________________________________
	 *             public static Resource openModel(Model model,Map<?,?>
	 *             map,ResourceSet rs) throws IOException{
	 *             // model aperas whitha relative path
	 *             // to folowing manipulation mains only to get an aabsolute
	 *             path
	 *             IPath rawModelFileLocation =
	 *             toIFile(model.getPath()).getRawLocation();
	 *             File modelFile = rawModelFileLocation.toFile();
	 *             Resource result =
	 *             openModel(URI.createFileURI(modelFile.getAbsolutePath
	 *             ()),rs,map);
	 *             return result;
	 *             }
	 */

	/**
	 * Return a new resource created with the given file absoltue path
	 * the resource is laoded using the given map
	 * 
	 * @param resourceURI
	 * @param resourceSet
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static Resource openModel(String absoluteModelPath, Map<?, ?> map) throws Exception {
		Resource result = openModel(URI.createFileURI(absoluteModelPath), new ResourceSetImpl(), map);
		return result;

	}

	/**
	 * Return a new resource created with the given file absoltue path
	 * the resource is laoded using the given map
	 * 
	 * @param resourceURI
	 * @param resourceSet
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static Resource openModel(String absoluteModelPath, Map<?, ?> map, ResourceSet rs) throws Exception {
		Resource result = openModel(URI.createFileURI(absoluteModelPath), rs, map);
		return result;

	}

	/**
	 * Return a new resource created with the given uri and loaded whith the
	 * given map
	 * if the given map is null default map is used
	 * 
	 * @param resourceURI
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static Resource openModel(URI resourceURI, Map<?, ?> map) throws Exception {
		Resource result = openModel(resourceURI, new ResourceSetImpl(), map);
		return result;

	}

	/**
	 * Return a new resource created with the given uri, in the given resource
	 * set
	 * and loaded whith the given map if the given map is null default map is
	 * used
	 * 
	 * @param resourceURI
	 * @param resourceSet
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static Resource openModel(URI resourceURI, ResourceSet resourceSet, Map<?, ?> map) throws Exception {
		Resource result = createResource(resourceURI, resourceSet);
		if (map != null && map.size() > 0) {
			result.load(map);
		} else {
			result.load(getDefaultMapForIO());
		}
		return result;
	}

	/**
	 * Open the specified model.
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	public static EList<EObject> openModel(IFile model) throws IOException {
		ResourceSetImpl set = new ResourceSetImpl();
		URI uri = URI.createPlatformResourceURI(model.getFullPath().toString(), true);
		Resource inputResource = set.createResource(uri);
		inputResource.load(null);
		EList<EObject> l = inputResource.getContents();
		return l;
	}

	/************************
	 * SAVING ***************************
	 * Method that helps to store the resource
	 * TODO, Serialize, dans outputStream
	 ***********************************************************/

	/**
	 * Method wich save the model in th sfile system
	 * 
	 * @param resource
	 * @throws IOException
	 */
	public static void export(Resource resource) throws IOException {
		Map map = new HashMap();
		//	OblPackage p = OblPackage.eINSTANCE;
		//	map.put(p.getNsURI(), p);
		map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
		//root.eResource().save(fos, map);
		resource.save(map);
	}

	/****************************
	 * CHECKING ***************************
	 * Method that helps to know the state of the resource
	 *****************************************************************/

	/**
	 * Querying method which returns true
	 * if the resource has no content
	 * 
	 * @param resource
	 *            Resource to check
	 * @return
	 */
	public static boolean isResourceEmptyContent(Resource resource) {
		return isResourceEmptyContent(resource.getContents());
	}

	/**
	 * Querying method which returns true content is empty
	 * 
	 * @param content
	 *            , the content of the resource
	 * @return
	 */
	private static boolean isResourceEmptyContent(EList<EObject> content) {
		boolean result = false;
		result = content == null || content.size() == 0;
		return result;
	}

	/******************************************
	 * DIVERSE
	 * Various help method
	 ******************************************/

	/**
	 * Returning an iFile located a the given path
	 * the path is relative of the worspace
	 * 
	 * @param path
	 * @return
	 */
	public static IFile toIFile(String path) {
		return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path.trim()));
	}

	/**
	 * just simplifiyng writing
	 * 
	 * @return
	 */
	public static String getDefaultExtensionFromRegistry() {
		return Resource.Factory.Registry.DEFAULT_EXTENSION;
	}

	/**
	 * Save an XMIResourceFactoryImpl for default extension in the
	 * resourceSet Extension Factory Map if not loaded
	 * 
	 * @param resourceSet
	 */

	private static void loadFactoryXmiResourceImpl(ResourceSet resourceSet) {
		if (resourceSet == null) {
			throw new NullPointerException(" ResourceSet is null");
		} else {
			if (!isFactoryXmiResourceImplLoaded(resourceSet)) {
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(getDefaultExtensionFromRegistry(), new XMIResourceFactoryImpl());
			}
		}
	}

	/**
	 * check the resourceSet Extension Factory Map to see if
	 * XMIResourceFactoryImpl is present
	 * for default extension
	 * 
	 * @param resourceSet
	 *            the resourceset to check
	 * @return true if resourceset ExtensionToFactoryMap contains
	 *         Resource.Factory.Registry.DEFAULT_EXTENSION
	 */
	private static boolean isFactoryXmiResourceImplLoaded(ResourceSet resourceSet) {
		boolean result = false;
		if (resourceSet != null) {
			result = (resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().get(getDefaultExtensionFromRegistry()) instanceof XMIResourceFactoryImpl);
		}
		return result;
	}

	/**
	 * return a defaultMap for Resource IO
	 */
	private static Map<String, Object> getDefaultMapForIO() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
		return result;
	}

	/**
	 * method wich returns true if uri in parameters is amongst on of the
	 * resource's uri of the resourceSet
	 * this method was written because
	 * "EcoreUtil.resolve(InternalEobject,ResourceSet)" resolve the proxy
	 * even if the proxy does not refer a resource included in the resourceSet
	 * 
	 * @param rs
	 * @param uri
	 * @return
	 */
	public static boolean resourceSetDoContainsUri(ResourceSet rs, URI uri) {
		boolean result = false;
		//precondition
		if (uri != null && uri.devicePath().length() > 0 && rs != null) {

			Iterator<Resource> iterResource = rs.getResources().iterator();
			while (iterResource.hasNext() && !result) {
				Resource r = iterResource.next();
				result = r.getURI().devicePath().equals(uri.devicePath());
			}

		}
		return result;
	}

	/**
	 * Save a new model in file.
	 * 
	 * @param file
	 * @param rootObject
	 * @throws IOException
	 */
	public static void saveModel(File file, EObject rootObject) throws IOException {
		FileOutputStream os = new FileOutputStream(file);
		ResourceSetImpl set = new ResourceSetImpl();
		String canonicalPath = file.getCanonicalPath();
		URI createFileURI = URI.createFileURI(canonicalPath);
		Resource outputResource = set.createResource(createFileURI);
		outputResource.getContents().add(rootObject);
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		outputResource.save(os, options);
		os.close();
	}

	public static void saveModel(IFile model, EObject rootObject) throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI fileURI = URI.createPlatformResourceURI(model.getFullPath().toString(), true);
		Resource resource = resourceSet.createResource(fileURI);
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$	
		resource.getContents().add(rootObject);
		resource.save(options);
	}

}
