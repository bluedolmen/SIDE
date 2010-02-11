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
package com.bluexml.side.Util.ecore;

import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class has utility methods for EClass
 * @author Constantin Madola
 */
public class EClassUtils {
	
	
	/**
	 * @see EStructuralFeature findEStructuralFeature(String name,String eType,String econtainingClass, EClass source )
	 * @param name
	 * @param eType
	 * @param econtainingClass
	 * @param source
	 * @return
	 */
	public static EStructuralFeature findEStructuralFeature(String name,String eType,String econtainingClass, EObject source ){
		
		return findEStructuralFeature(name,eType,econtainingClass,source.eClass() );
	}
	
	
	/**
	 * browse source EStructural Feature list and 
	 * Return null, or an the first eStructuralFeature which match :
	 * <p><b>getName()</b> = {name}</p>
	 * <p><b>eType.getName()</b> = {etype}(target for ereference)</p>
	 * <p><b>econtainingClass().getName()</b> = {econtainingClass}</p>
	 * each parameter null is ignored in the test
	 * For example : findEStructuralFeature("azerty",null,null,null) will return the structural feature named "azerty"
	 * @param name
	 * @param eType
	 * @param econtainingClass
	 * @param source
	 * @return
	 */
	public static EReference findERefWichLinkSourceToTarget(EClass source, EClass target ){
		EReference result = null;
		result =(EReference) EClassUtils.findEStructuralFeature(null,target,source);
		return result;
	}
	
	/**
	 * browse source EStructural Feature list and 
	 * Return null, or an the first eStructuralFeature which match :
	 * <p><b>getName()</b> = {name}</p>
	 * <p><b>eType.getName()</b> = {etype}(target for ereference)</p>
	 * <p><b>econtainingClass().getName()</b> = {econtainingClass}</p>
	 * each parameter null is ignored in the test
	 * For example : findEStructuralFeature("azerty",null,null,null) will return the structural feature named "azerty"
	 * @param name
	 * @param eType
	 * @param econtainingClass
	 * @param source
	 * @return
	 */
	public static EStructuralFeature findEStructuralFeature(String name,String eType,String econtainingClass, EClass source ){
		EStructuralFeature result = null;
		ListIterator<EStructuralFeature> lisf = source.getEAllStructuralFeatures().listIterator();
		boolean founded = false;
		while(lisf.hasNext() && !founded){
			boolean[] truth = new boolean[3];
			EStructuralFeature esf = lisf.next();
			if(name != null){
				truth[0] = esf.getName().equals(name);
			}else{
				truth[0] = true;
			}
			if(eType != null && esf.getEType()!=null){
				truth[1] = esf.getEType().getName().equals(eType);
			}else{
				truth[1] = true;
			}
			if(econtainingClass != null && esf.getEContainingClass() !=null){
				truth[2] = esf.getEContainingClass().getName().equals(econtainingClass);
			}else{
				truth[2] = true;
			}
			if(truth[0] && truth[1] && truth[2]){
				founded = true;
				result =esf;
			}
		}
		return result;
	}
	
	/**
	 * browse source EStructural Feature list and 
	 * Return null, or an the first eStructuralFeature which match :
	 * <p><b>getName()</b> = {name}</p>
	 * <p><b>eType.getName()</b> = {etype}(target for ereference)</p>
	 * <p><b>econtainingClass().getName()</b> = {econtainingClass}</p>
	 * each parameter null is ignored in the test
	 * For example : findEStructuralFeature("azerty",null,null,null) will return the structural feature named "azerty"
	 * @param name
	 * @param target
	 * @param source
	 * @return
	 */
	public static EStructuralFeature findEStructuralFeature(String name,EClass target,EClass source){
		EStructuralFeature result = null;
		ListIterator<EStructuralFeature> lisf = source.getEAllStructuralFeatures().listIterator();
		boolean founded = false;
		while(lisf.hasNext() && !founded){
			boolean[] truth = new boolean[2];
			EStructuralFeature esf = lisf.next();
			if(name != null){
				truth[0] = esf.getName().equals(name);
			}else{
				truth[0] = true;
			}
			if(target != null && esf.getEType()!=null){
				for (EGenericType et : target.getEAllGenericSuperTypes())
					if (et.getERawType().getName().equals(esf.getEType().getName()))
						truth[1] = true;
			}else{
				truth[1] = true;
			}
			if(truth[0] && truth[1]){
				founded = true;
				result =esf;
			}
		}
		return result;
	}
	
	/**
	 * Method semi recursive, which search for the first EOperation similar to the first method arguments
	 * and nearest to the second method argument (inheritance path).
	 * Beware, an EClass can inherit from more than one EClass
	 * @param operation the similar eoperation to find
	 * @param receiverEclass the context from which to search the EOperation
	 * @return
	 */
	public static EOperation getEOperationFor(EOperation operation, EClass receiverEclass) {
		boolean founded = false;
		EOperation resolved = null;
		if(receiverEclass != null){
			// looking into receiverEClass Eoperation
			// >!< getEoperations returns operation declared in the EClass
			// >!< getEALLEoperation returns operations declared in the EClass and all superTypes
			ListIterator<EOperation> eoplist = receiverEclass.getEOperations().listIterator();
			while(eoplist.hasNext() && !founded){
				EOperation current = eoplist.next();
				if(EOperationUtils.isTheSameEoperation(operation, current)){
					founded = true;
					resolved = current;
				}
			}
			// if the Eoperation is not directly founded in the current EClass
			// recursive call on all DIRECT superType
			// >!< getESuperTypes returns direct superTypes
			// >!< getEAllSuperTypes returns DIRECT SUperTypes and their superTypes and so on
			if(!founded){
				EList<EClass> superTypesList = receiverEclass.getESuperTypes();
				if(superTypesList !=null && superTypesList.size()>0 ){
					int cpt = 0;
					int limit = superTypesList.size();

					while (resolved == null && cpt<limit){
						EClass superType = superTypesList.get(cpt);
						resolved = getEOperationFor(operation,superType);
						cpt++;
					}
				} 
			}
		}
		return resolved;
	}

	

}
