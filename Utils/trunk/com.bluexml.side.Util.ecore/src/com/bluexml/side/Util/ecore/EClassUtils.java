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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
		result =(EReference) EClassUtils.findEStructuralFeature(null,target.getName() , source.getName(), source);
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
	

	

}
