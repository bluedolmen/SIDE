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
 * @author : gmadola
 *******************************************************************************/

package com.bluexml.side.util.model.merge;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.bluexml.side.Util.ecore.EClassUtils;
import com.bluexml.side.Util.ecore.EObjectUtils;
import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.Util.ecore.EStructuralFeatureUtils;

//import com.bluexml.generator.alfresco.chain.Chain;
//import com.bluexml.generator.alfresco.chain.Model;


/**************************************************************************************************
 * This class is used to merge models from the same metamodel
 * TODO should replace merge class, by a merge manager class, merge should use an object
 * TODO some information need to be stored through the merging process
 * 
 * @author Constantin Madola <a href="mailto:gmadola@bluexml.com">G�rard Constantin Madola</a>
 * TODO those info shoulb be reported in a changes.xml file
 * <p><b>META</b></p>
 * <li> Last modification date : </li>
 * <li> Last modification user : </li>
 * <li> BUGLIST <i>(BUGID[id of the bug ]|[tracker or link to the bug management system]</i></li>    :
 *  <p>bug1</p>
 *  <p>bugX</p>
 * 
 *************************************************************************************************/

public abstract class  MergeUtils {
	// --------------------------------------------------------------------------------------------------------- //
	public static String DEFAULT_MERGED_MODEL_NAME = "bx_side_merged_model.obl";
	public static final String DEFAULT_MERGED_ROOT_PACKAGE_NAME = "BX_S_IDE";
	public static final String DEFAULT_MERGED_ROOT_PACKAGE_DOCUMENTATION ="This is the root package resulted from merging";
	public static final String DEFAULT_MERGED_ROOT_PACKAGE_ATTRIBUTE_NAME = "name";
	public static final String DEFAULT_MERGED_ROOT_PACKAGE_ATTRIBUTE_DOCUMENTATION ="documentation";

	//public static List<EObject> listProxy = new ArrayList<EObject>();
	public static String padding="";
	//public static List<EObject> TOO_RESOLVE_LIST = new ArrayList<EObject>();

	// --------------------------------------------------------------------------------------------------------- //
	// TODO manage exception(mergeExeption)<==>(mergeExceptionMEssage)
	// TODO manage debug mesage
	// TODO manage options {force merge?export as file|outputstream?resolve links ?|
	// TODO Clever merging, from one model, loading all references to other model and merging it all
	// TODO remove system.outSSSSSSSSS

	/**<b>First level method to merge models</b>
	 * <li>creating resource for the result</li>
	 * <li>retrieving models from chain</li>
	 * <ul> 
	 * 	<i>for each model</i>
	 *	<li>load current model resource</li>
	 * 	<li>load <i>metamodel package</i> if not already loaded and register it</li>
	 *  <li>check that the current model's metamodel is the same has the loaded<i>metamodel package</i> 
	 * 	<li>call method wich merge current model wich merge resource</li>
	 * </ul>
	 * <li>export merge resource</li>
	 * @param chain IFile of the chain
	 * @param models File[] containing models
	 * @cl the classLoader of the generator
	 * @throws IOException
	 */
	public static String  merge(IFile chain, File[] models,ClassLoader cl) throws IOException{
		String pathToMergedFile="";
		// the model file resulted from the merge method is stored at the same level as the chain used
		//IFile iFileChain= EResourceUtils.toIFile(chain.eResource().getURI().toPlatformString(true));	
		pathToMergedFile = chain.getParent().getLocation().append(DEFAULT_MERGED_MODEL_NAME).toFile().getAbsolutePath();
		File mergedFile = new File(pathToMergedFile);
		return merge(mergedFile, models, cl);
	}
	
	/**<b>First level method to merge models</b>
	 * <li>creating resource for the result</li>
	 * <li>retrieving models from chain</li>
	 * <ul> 
	 * 	<i>for each model</i>
	 *	<li>load current model resource</li>
	 * 	<li>load <i>metamodel package</i> if not already loaded and register it</li>
	 *  <li>check that the current model's metamodel is the same has the loaded<i>metamodel package</i> 
	 * 	<li>call method wich merge current model wich merge resource</li>
	 * </ul>
	 * <li>export merge resource</li>
	 * @param chain IFile of the chain
	 * @param models File[] containing models
	 * @cl the classLoader of the generator
	 * @throws IOException
	 */
	public static String  merge(File mergedFile, File[] models,ClassLoader cl) throws IOException{
		EPackage metaModelPackage = null;
		// deletion of the previous result
		if (mergedFile.exists()){
			mergedFile.delete();
		}
		
		//EList<Model> models = chain.getRepository().getModels();
		// Creating mergemodel resource
		Resource mergedResource = EResourceUtils.createResource(mergedFile.getAbsolutePath());
		// We use the same resourceSet
		ResourceSet rs = mergedResource.getResourceSet();
		int compteur = 0;
		//browsing models list
		while(compteur < models.length){
			Resource modelResource = EResourceUtils.openModel(models[compteur].getAbsolutePath(),null,rs);
			if(metaModelPackage == null ){
				metaModelPackage = getMetaModelEpackage(modelResource);
				metaModelPackage.eClass();
			}else{
				// if metamodels do not correspond we throws exception
				if (!metaModelPackage.getNsPrefix().equals(getMetaModelEpackage(modelResource).getNsPrefix())){
					throw new RuntimeException("Trying to merge models from more than one metamodel");
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// TODO , 2 reload of the same ressource ===== bad !!s
			map.put(metaModelPackage.getNsURI(), metaModelPackage);
			map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
			modelResource.load(map);

			// Merging the current model
			System.out.println("_______________________________________________");
			System.out.println("Merging  "+modelResource.getURI().toString());
			// merging the current model
			mergeCurrentModelWithResource(mergedResource,modelResource,cl);
			compteur++;
		}
		// storing the merging result
		try {
			System.out.println(" resolving external references ");
			browse2(mergedResource.getContents().get(0), rs,mergedResource.getContents().get(0),cl);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EResourceUtils.export(mergedResource);
		// return the file name
		return DEFAULT_MERGED_MODEL_NAME;
	}

	/**
	 * <b>Second level method to merge model</b>
	 * this method will merge the mergeResource with the resourceModelTomerge
	 * <li>check preconditions (null pointer precondistions only)</li>
	 * <li>check if the mergeResource has been initialized (content is not empty/has a root)</li>
	 * 	<ul>initialize the resource if needed give a content (give @see initializeRootForMerge(Resource root,EObject packageCopyStructure)</ul>
	 * <li>load the root of the merge resource</li>
	 * <li> call indepth method</li>
	 *  Note that this method can be used to merge resource themselves(without any waranty :) ), knowing that the first
	 *  parameter will store the result.
	 * @param mergeResource the resource in which is stocked the reslut of the merge
	 * @param resourceModelToMerge the resource linked to the model to merge
	 * @throws IOException
	 */

	public static void mergeCurrentModelWithResource(Resource mergeResource, Resource resourceModelToMerge,ClassLoader cl) throws IOException{
		// Preconditions
		if(mergeResource == null || resourceModelToMerge == null || EResourceUtils.isResourceEmptyContent(resourceModelToMerge) ){
			throw new NullPointerException("MergeUtils : treat : trying to merge null resources");
		}
		List<EObject> mergedResourceContent = mergeResource.getContents();
		// model to merge  content
		List<EObject> listContentResourceModelToMerge = resourceModelToMerge.getContents();
		EObject rootResourceModelToMerge  = listContentResourceModelToMerge.get(0);
		// H1 . All contents is under a single package (other package included)
		if(EResourceUtils.isResourceEmptyContent(mergeResource)){
			// Which means we are merging empty root whith the first model
			System.out.println("initializing the root of the merge resource");
			initializeRootContainerForMergeResource(mergeResource,resourceModelToMerge);
		}
		EObject rootContentMergeResource = mergedResourceContent.get(0);
		doMerge(rootContentMergeResource,rootResourceModelToMerge,cl);

	}

	/**
	 * this method will add (if necessary) and EObject of the same "kind" as the EObject root of target
	 * to the root resource
	 * this EObject is setted whith a default attribute "name" :  "BX_S_IDE", and a "default" : "documentation"
	 * those attributes are EStructural <b><i>DEDUCED</i></b> from the EObject Eclass, if corresponding ESF
	 * are not present, we create default <li>@see static declaration in top of class</li>
	 * <li>@see initializeRootForMerge</li>
	 * <li> For example, let assume, root is empty and target has a root element of Eclass.getName() "toto".
	 * An empty "toto" EObject will be created and set as the root content of root resource whith
	 * 2 attributes, name and documentation </li>
	 * @param root the resource to intialize
	 * @param target the root element of the model to merge
	 */

	public static void initializeRootContainerForMergeResource(Resource root,Resource target){
		// Verify that resource does not already have a content
		if(target == null || root == null){
			throw new NullPointerException("MergeUtils : InitializeRooForMerge : no root or target");
		}
		EObject targetPackageCopyStructure = !EResourceUtils.isResourceEmptyContent(target)?EObjectUtils.simpleEObjectCopy(target.getContents().get(0).eClass(),getMetaModelEpackage(target)):null;
		if(targetPackageCopyStructure!=null){
			initializeRootForMerge(root,targetPackageCopyStructure);
		}else{
			throw new NullPointerException("MergeUtils : InitializeRooForMerge : targetHasNoContent");
		}
	}


	/**
	 * <p>initializeRootForMerge</p>
	 * <p>Initilizing the root element of the merged model (the result)
	 * Means to create the root container, it will contains the result of the merge thuss be the root patern
	 * of all generated model if {@link justifyModels} is not called at the end of the treatment
	 * @param root the resource we want to initialize
	 * @param packageCopyStructure An EObject wich have the same structure than the root Element of the model 
	 * @see simpleEobjectCopy
	 * @see justifyModels
	 */
	private static void initializeRootForMerge(Resource root,EObject packageCopyStructure){
		// Verify that resource does not already have a content
		if(EResourceUtils.isResourceEmptyContent(root)){
			int compteur = 0;
			boolean bName =false;
			boolean bDocumentation = false;
			// We will search into structural freature of Eclass
			// the structural feature "name" and "documentation"
			// and set these with the default value
			ListIterator<EAttribute> liEatt = packageCopyStructure.eClass().getEAllAttributes().listIterator();
			while (liEatt.hasNext() && !(bName && bDocumentation)){
				EStructuralFeature esf = liEatt.next();
				if(EStructuralFeatureUtils.isEStructuralFeatureNamed(esf,DEFAULT_MERGED_ROOT_PACKAGE_ATTRIBUTE_NAME)){
					packageCopyStructure.eSet(esf, DEFAULT_MERGED_ROOT_PACKAGE_NAME);
					bName = true;
				}
				if(EStructuralFeatureUtils.isEStructuralFeatureNamed(esf, DEFAULT_MERGED_ROOT_PACKAGE_ATTRIBUTE_DOCUMENTATION)){
					packageCopyStructure.eSet(esf, DEFAULT_MERGED_ROOT_PACKAGE_DOCUMENTATION);
					bDocumentation = true;

				}
			}
			root.getContents().add(packageCopyStructure);
		}
	}



	/**
	 * do the merge on the top  package level, if the current package is not already present
	 * it will add it, otherwise it will call doMergeSameDepth between current package and the similar package
	 * in the merge resource
	 * @param elMergeResource
	 * @param elModelToMerge
	 * @param esf
	 */
	public static void doMerge(EObject elMergeResource,EObject elModelToMerge,ClassLoader cl){
		// ON n'a pas d�j� ce package la quelque part dans le model actuellement
		EClass elMergeEClass = elMergeResource.eClass();
		EClass elModelEClass = elModelToMerge.eClass();

		// retrievening ereference in which we can store  element from model into element from merge resource
		EReference eRefLink  =  EClassUtils.findERefWichLinkSourceToTarget(elMergeEClass,elModelEClass);
		boolean isAlreadyPresent = EObjectUtils.isTargetPresentHasChildInSourceUsingERefLInk(elMergeResource,elModelToMerge,eRefLink,cl);
		if(isAlreadyPresent){
			EObject eObjectlMergeResourceToMerge = EObjectUtils.getEObjectFromSourceEqualsTarget(elMergeResource,elModelToMerge,eRefLink,cl);
			doMergeSameDepth(eObjectlMergeResourceToMerge,elModelToMerge,cl);

		}else{
			System.out.println("Package  "+ elModelToMerge.eClass().getName() +" "+EObjectUtils.eGetFromString(elModelToMerge,"name")+" "+ "was not already present--> Adding");
			doAddHasChild(elMergeResource,elModelToMerge,(EReference)eRefLink);
		}
	}

	/**
	 * Merge EObjects located at the same depth (tree browsing)
	 * The first paremeter represent the EObject from the merge Resource
	 * the Second parameter respresent the EObject from the model to merge
	 * <li>Method will Browse all elModelToMerge Ereference</li>
	 * <li>Browse Eboject list</li>
	 * 	<ul> for all Eobject in the resulted list </ul>
	 * 		<li>find if a similar eObject is not already present in the elMergeResource by the same Ereference</li>
	 * 			<ul> if similar EObject is founded </ul>
	 * 				<li> launch recursive call on the two similar EObject</li>
	 * 			<ul> else add the Eobject From the Model in the Eobejct from the mergeResource using ERef </ul>
	 * 
	 * @param elMergeResourceERefLinkResult
	 * @param elModelToMerge
	 * @param refLink
	 */
	private static void doMergeSameDepth(EObject elMergeResource,	EObject elModelToMerge,ClassLoader cl) {
		//Merge from elModelToMerge to elMergeResource
		padding = padding +" . ";
		System.out.println(padding+"Eclass : "+elMergeResource.eClass().getName() +" . "+EObjectUtils.eGetFromString(elMergeResource, "name") 
				+" <--> " +
				EObjectUtils.eGetFromString(elModelToMerge, "name"));
		//1) for all reference of elModelToMerge using eClass
		for(EReference eRef : elModelToMerge.eClass().getEAllReferences()){
			Object elModelToMergeERefResolvedValue = null;
			// if the current eReference is set for elModelToMerge
			if(elModelToMerge.eIsSet(eRef)){
				//get the contained list
				elModelToMergeERefResolvedValue = elModelToMerge.eGet(eRef);
				// System.out.println("Source "+ EStructuralFeatureUtils.getSource(eRef) +" target " +EStructuralFeatureUtils.getTarget(eRef));
				// if the retrieved object is really a list 
				if(elModelToMergeERefResolvedValue instanceof List<?>){
					List<EObject>  listResolvedModel  = (List<EObject>) elModelToMergeERefResolvedValue;
					int compteur = 0;
					// >NOTE<ListIterator<EObject> IteralistResolvedModel = listResolvedModel.listIterator();
					// Using ListIterator leads to mysterious concurent modification exception 
					// in fact adding an EObject in a EcontainmentElistEobject leads
					// to the removal of the same eObject in the initila list
					int limit = listResolvedModel.size();	
					while(compteur<limit){
						//>!<EObject eo = IteralistResolvedModel.next();
						EObject eo = listResolvedModel.get(compteur);
						System.out.println(padding+"TREAT-  "+eo.eClass().getName()+": "+EObjectUtils.eGetFromString(eo, "name") );
						// if the current eObejct is  already present in elMergeResource using the same eRef
						if(EObjectUtils.isTargetPresentHasChildInSourceUsingERefLInk(elMergeResource, eo, eRef,cl)){
							// call merge of the two similar eObject
							EObject eoMerge = EObjectUtils.getEObjectFromSourceEqualsTarget(elMergeResource, eo, eRef,cl);
							doMergeSameDepth(eoMerge,eo,cl);
						}else{
							// else simply add current to elMergeResource using eRef
							System.out.println(padding+"[IN]--> "+eo.eClass().getName()+": "+EObjectUtils.eGetFromString(eo, "name") );
							updateParentReferenceWithSource(elMergeResource, eo, elModelToMerge,padding);
							EObjectUtils.addTargetToSourceByEReference(elMergeResource, eo, eRef);
						}
						if(listResolvedModel.size() != limit){
							// Siouxie !!!
							limit--;
							compteur--;
						}	
						compteur++;
					}
				}
			}
		}
		padding = padding.length()>3?padding.substring(0, padding.length()-3):"";
	}

	/**
	 * Add target element has child of source element, link is made using the specified Ereference
	 * @param source
	 * @param target
	 * @param eRefLink
	 */
	public static void doAddHasChild(EObject source, EObject target, EReference eRefLink){
		EObjectUtils.addTargetToSourceByEReference(source,target,eRefLink);
	}

	public static String getMetaModelNsPrefix(EClass ec){
		return  ec.getEPackage().getNsPrefix();
	}
	public static String getMetaModelNsPrefix(EPackage ep){
		return  ep.getNsPrefix();
	}
	public static String getMetaModelNsPrefix(EObject ec){
		return  getMetaModelNsPrefix(ec.eClass());
	}
	/**
	 * return the meta model nsPrefix of the resource
	 * @param r
	 * @return
	 */
	public static String getMetaModelNSPrefix(Resource r){
		String result = "";
		if(r!=null){
			EPackage epackage = getMetaModelEpackage(r);
			if(epackage != null){
				result = epackage.getNsPrefix();
			}
		}
		return result;
	}

	/**
	 * return the meta model EPackage
	 * @param r
	 * @return
	 */
	public static EPackage getMetaModelEpackage(Resource r){
		EPackage result = null;
		if(r!=null){
			result = (EPackage)  r.getContents().get(0).eClass().getEPackage();
		}
		return result;
	}






	/**
	 * ABANDON
	 * @param source
	 * @param sourceParent
	 * @param sourceChild
	 * @param target
	 * @param targetParent
	 * @param targetChild
	 * @param Mclass
	 * @param Mmethod
	 * @param containerExecution
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Deprecated
	public static void browseTargetAndApplyMethodRecursive(EObject source,EObject sourceParent,EObject sourceChild,
			EObject target,EObject targetParent,EObject targetChild,String Mclass,String Mmethod,List<Object> containerExecution) 
	throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{

		for( EStructuralFeature esf :target.eClass().getEAllStructuralFeatures()){
			Class c = Class.forName(Mclass);
			java.lang.reflect.Method m = c.getMethod(Mmethod, EObject.class,EObject.class,EObject.class,EObject.class,EObject.class,EObject.class, List.class);
			Object o = target.eGet(esf);
			if (o instanceof EObject){
				EObject cur = (EObject)o;
				if(cur == source){
					m.invoke(null, null);
				}
			}else{
				if (o instanceof List<?> && (((List<EObject>) o).get(0) instanceof EObject)  ){
					browseTargetAndApplyMethodRecursive(source,sourceParent,sourceChild,target,targetParent,targetChild,Mclass,Mmethod,containerExecution);
				}
			}

		}


	}

	/**
	 * method used before insertion to resolve update links.
	 * the method will replace all occurence of parent EObject by source EObject in target EObject 
	 * @param source
	 * @param target
	 * @param parent
	 * @param eref
	 * @param pad
	 */
	public static void updateParentReferenceWithSource(EObject source, EObject target,EObject parent, String pad){
		for( EStructuralFeature esf :target.eClass().getEAllStructuralFeatures()){
			Object o = target.eGet(esf);
			if (o instanceof EObject){
				EObject cur = (EObject)o;
				if(cur == parent){
					System.out.println(pad+" "+"One link resolved");
					target.eSet(esf, source);
				}
			}else{
				if (o instanceof List<?>)  {
					List<EObject> listEobject = (List<EObject>) o;

					for(EObject eoChild : listEobject){
						updateParentReferenceWithSource(source,eoChild,parent,pad+" . ");
					}
				}
			}
		}
	}


	/**Method called after the merge process to resolve external links
	 * We browse all EObject and wath if they are proxies
	 * if it is the case we attempt to resolve them using the resourceSet used during the merging
	 * if object is resolvable we find the similar object in the  merge result and repalce it with it
	 * otherwise the object remains an external reference
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException */
	public static void browse2(EObject eo,ResourceSet rs,EObject mergeRoot,ClassLoader cl) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException{
		

		for(EStructuralFeature esf : eo.eClass().getEAllStructuralFeatures()){
			Object o = eo.eGet(esf,false);
			if(o instanceof List<?>){
				List<EObject> leo = (List<EObject> )o;
				for(EObject cur : leo){
					browse2(cur,rs,mergeRoot,cl);
				}

			}
			else{
				if(o !=null){
					if(o instanceof EObject){
						EObject eo2 = (EObject) o;
						if(eo2.eIsProxy()){
							if(eo2 instanceof InternalEObject){
								InternalEObject ieo = (InternalEObject) eo2;

								EObject resolved = EcoreUtil.resolve(eo2,rs);
								EObject copy = find(resolved,mergeRoot,cl);
								eo.eSet(esf, copy);
								System.out.println(ieo.eProxyURI());
							}
							System.out.println("proxy found deep");
							System.out.println(eo2.toString());

						}
					}
				}
			}
		}
	}


	/**
	 * Method called to find An EObject in a model 
	 * Method is recursih he Eobject we are looking for until equals for merger between the eboject returns true..
	 * Each object is compared wit
	 * @param toFind
	 * @param root
	 * @param cl
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 */
	public static EObject find(EObject toFind,EObject root,ClassLoader cl) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException{
		boolean founded =false;
		EObject result = null;
		TreeIterator<EObject> it = root.eAllContents();
		while(it.hasNext() && !founded){
			EObject current = it.next();
			if(EObjectUtils.equalsForMerge(current, toFind,cl)){
				founded = true;
				EObject browser = toFind.eContainer();
				EObject browser2 = current.eContainer();
				while(browser != null && browser2!=null && founded){
					founded = EObjectUtils.equalsForMerge(browser, browser2,cl);
					browser = browser.eContainer();
					browser2 = browser2.eContainer();
				}
				if(browser !=null && browser2 ==null){
					founded = false;
				}
			}
			if(founded){
				result = current;
			}
		}
		return result;

	}


}
