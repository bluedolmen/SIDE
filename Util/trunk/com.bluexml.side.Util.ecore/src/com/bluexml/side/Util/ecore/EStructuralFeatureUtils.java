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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * This class has utility method for EstructuralFeature
 * @author Constantin Madola
 */

public class EStructuralFeatureUtils {

	
	/**
	 * Check if the given EstructuralFeature name property is equal
	 * to the given name in parameter
	 * @param esf
	 * @param name
	 * @return
	 */
	public static boolean isEStructuralFeatureNamed(EStructuralFeature esf, String name){
		boolean result = false;
		if(esf !=null){
			System.out.println("esf get name trim "+esf.getName().trim());
			result = esf.getName().trim().equalsIgnoreCase(name);
		}
		return result;
	}
	
	/**
	 * Check if the given EstructuralFeature is a ERefenrence
	 * EReference are property wich link the EClass to another EClass
	 * @param esf
	 * @return
	 */
	public static boolean isEReference(EStructuralFeature esf){
		return esf instanceof EReference;
		
	}
	
	/**
	 * Check if the given EstructuralFeature is a EAttribute
	 * EAttribute are property of the current EClass(EObject)
	 * @param esf
	 * @return
	 */
	public static boolean isEAttribute(EStructuralFeature esf){
		return esf instanceof EAttribute;
		
	}
	
	/**
	 * Return the target of a EReferene
	 * @param esf
	 * @return EClassifier
	 */
	public static EClassifier getTarget(EStructuralFeature esf){
		return esf.getEType();
		
	}
	
	/**
	 * Return the source of a EReferene (normaly the classifier of the current object)
	 * @param esf
	 * @return EClassifier
	 */
	public static EClassifier getSource(EStructuralFeature esf){
		return esf.getEContainingClass();
		
		
	}
	
	
}
