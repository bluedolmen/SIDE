package com.bluexml.side.util.model.merge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.RunElement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.bluexml.side.Util.ecore.EResourceUtils;

public class MergeNew {
	private static final String MMUSECASE_PACKAGE_ID = "Package";
	private static final String MMUSECASE_PACKAGE_NAME_ID = "name";
	private static final String MMUSECASE_PACKAGE_SET_ID = "packageSet";
		
	private Resource mergedResource = null;
	
	public MergeNew(File destinationFile) {
		if (destinationFile.exists()) {
			destinationFile.delete();
		}
		try {
			mergedResource = EResourceUtils.createResource(destinationFile.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Cannot create resource with file path \"" + destinationFile.getAbsolutePath() + "\"");
		}
	}
	
	public void merge(File[] models) {
		// get all root elements from each model
		List<EObject> rootElements = __loadResources(models);
		// copy all elements at the root of the merged models (thus creating multiple root in the content of the merged file)
		__copyAll(rootElements);
		// merge similar packages at the root (and recursively)
		__mergeModels();
		// export the new created model in the associated file
		try {
			EResourceUtils.export(mergedResource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Copy all the objects in the list into the content of the merged resource
	 * Use of the EcoreUtil.Copier utility function that enables to make a fresh deep copy of an existing model
	 * Using this function enables to manage correctly the copy of proxied elements (do not make references to original models)
	 */
	private void __copyAll(List<EObject> rootElements) {
		Copier copier = new Copier();
		Collection<EObject> rootObjectCopies = copier.copyAll(rootElements);
		copier.copyReferences();
		mergedResource.getContents().addAll(rootObjectCopies);		
	}

	/*
	 * Load resources given a set of model files
	 * Returns a list of EObject-s representing the roots of each of the loaded model files
	 * Uses material from previous merging procedure
	 */
	private List<EObject> __loadResources(File[] modelFiles) {
		ResourceSet rs = mergedResource.getResourceSet();

		ArrayList<EObject> rootElements = new ArrayList<EObject>();
		
		EPackage metaModelPackage = null;
		try {
		for (int i = 0; i < modelFiles.length; i++) {
				Resource modelResource = EResourceUtils.openModel(modelFiles[i].getAbsolutePath(), null, rs);
				if (metaModelPackage == null) {
					metaModelPackage = getMetaModelEpackage(modelResource);
					metaModelPackage.eClass();
				} else {
					if (!metaModelPackage.getNsPrefix().equals(getMetaModelEpackage(modelResource).getNsPrefix())) {
						throw new RuntimeException("Trying to merge models from more than one metamodel");
					}
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				// TODO , 2 reload of the same ressource ===== bad !!s
				map.put(metaModelPackage.getNsURI(), metaModelPackage);
				map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
				modelResource.load(map);
	
				rootElements.add(modelResource.getContents().get(0));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rootElements;
	}
	
	/*
	 * Main procedure for merging all the elements in the content of the merged resource
	 */
	private void __mergeModels() {
		List<EObject> rootPackageList = __getRootPackageList();
		
		// Create a map of list of EObject representing the set of unique names
		// that can be found at the top level. The list contains the set of packages
		// that have the same name
		Map<String, List<EObject>> rootPackageMap = new HashMap<String, List<EObject>>();
		for (EObject p_ : rootPackageList) {
			String packageName = __getPackageName(p_);
			if (! rootPackageMap.containsKey(packageName)) {
				rootPackageMap.put(packageName, new ArrayList<EObject>());
			}
			rootPackageMap.get(packageName).add(p_);
		}
		
		// Iterates over each root package name and process merging on similar packages	
		for (String packageName : rootPackageMap.keySet()) {
			Iterator<EObject> i = rootPackageMap.get(packageName).iterator();
			
			// Get the first root element being a package
			EObject currentElement_ = i.next();
			EObject followingElement_ = null;
			
			while (i.hasNext()){
				followingElement_ = i.next();
				if (__isPackage(followingElement_)) {
					__mergePackages(currentElement_,followingElement_);
				}
			}
		}
		
		// Keep only the packages having at least one child
		for (EObject rootPackage : rootPackageList) {
			if (rootPackage.eContents().isEmpty()) {
				mergedResource.getContents().remove(rootPackage);
			}
		}
		// Manage the case where several root names exist
		if (rootPackageMap.keySet().size() > 1) {
			System.err.println("Several root packages exist so creating a default one");
			throw new RuntimeException("Not implemented");
		}
	}	
	
	/*
	 * Filters root elements in content of the merged resource to return only packages elements
	 */
	private List<EObject> __getRootPackageList() {
		Iterator<EObject> i = mergedResource.getContents().iterator();
		ArrayList<EObject> result = new ArrayList<EObject>();
		
		EObject currentElement_ = null;
		do {
			currentElement_ = i.next();
			if (__isPackage(currentElement_)) {
				result.add(currentElement_);
			}
		} while (i.hasNext());

		return result;
	}

	/*
	 * Pre-condition : source and target are packages
	 * Merge packages from source to target
	 */
	private static void __mergePackages(EObject target, EObject source) {
		//System.out.println("Trying to merge packages \"" + sourceName + "\" and \"" + targetName + "\"...");
		if (__isEqualPackage(target, source) ) {
			//System.out.println("Same name... performing merge...");
			__basicMergePackages(target, source);
		}
	}
	
	/*
	 * Pre-condition : source and target are packages
	 * Perform merging of packages from source to target
	 * Only copy interesting things => EContainment and EReferences
	 * target and sources must be packages and have the same name
	 */
	@SuppressWarnings("unchecked")
	private static void __basicMergePackages(EObject target, EObject source) {
		for (EReference containmentFeature : source.eClass().getEAllContainments()) {
			EList<EObject> containedElements = (EList<EObject>) source.eGet(containmentFeature);
			// If the feature exists in the target, then we add the references to the target
			if (target.eIsSet(containmentFeature)) {
				EList<EObject> targetList = (EList<EObject>) target.eGet(containmentFeature);
				
				if ( MMUSECASE_PACKAGE_SET_ID.equals(containmentFeature.getName()) ) {
					// if this is the package set, then we check whether there exist similar packages
					for (EObject targetPackage : targetList) {
						EObject similarPackage = __findSimilarPackage(targetPackage, containedElements);
						
						if (similarPackage != null) {
							__basicMergePackages(targetPackage, similarPackage);
							containedElements.remove(similarPackage);
						}
					}
				}
				targetList.addAll(containedElements);
			} else {
				target.eSet(containmentFeature, containedElements);
			}
		}
	}
	
	/*
	 * Precondition : p1 and p2 are two EObject-s being packages
	 * Compare two packages 
	 * Packages are similar if they got the same name
	 */
	private static boolean __isEqualPackage(EObject p1, EObject p2) {
		String packageName1 = __getPackageName(p1);
		String packageName2 = __getPackageName(p2);
		return packageName1.equals(packageName2);
	}
	
	/*
	 * Precondition : package_ is identified as an EOBject being package
	 * Find the EObject into the list
	 */
	private static EObject __findSimilarPackage(EObject package_, EList<EObject> list) {

		EObject result = null;
		for (int i = 0 ; i < list.size(); i++) {
			EObject otherPackage = list.get(i);			
			if (__isEqualPackage(package_, otherPackage)) {
				result = otherPackage;
				break;
			}
		}
		
		return result;
	}
	
	
	/*
	 * HELPER FUNCTIONS
	 */
	
	public static EPackage getMetaModelEpackage(Resource r) {
		EPackage result = null;
		if (r != null) {
			result = (EPackage) r.getContents().get(0).eClass().getEPackage();
		}
		return result;
	}

	private static boolean __isPackage(EObject o) {
		return MMUSECASE_PACKAGE_ID.equals(o.eClass().getName());
	}
	
	private static String __getPackageName(EObject package_) {
		String result = "";
		
		if (__isPackage(package_)) {
			EStructuralFeature packageNameFeature = package_.eClass().getEStructuralFeature(MMUSECASE_PACKAGE_NAME_ID);
			result = (String) package_.eGet(packageNameFeature);
		} else {
			System.err.println("[__getPackageName] EObject \"" + package_ + "\" is not a package");
		}
		
		return result;
	}

	
}