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

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.impl.EClassImpl;

/**
 * @author Constantin Madola
 * 
 */
public class EOperationUtils {

	/**
	 * This method retrun strue if otehrEop is similar to eop method is
	 * transitive (normally) EOperations are similar if
	 * <ol>
	 * <p>
	 * EOperations are similar if
	 * </p>
	 * <li>They have exactly the same return type</li>
	 * <li>They have the same name</li>
	 * <li>they have the same number of parameters</li>
	 * <li><code>each parameter</code> from an <code>EOperation</code> is of
	 * same type or is subType from the same parameter at the same position in
	 * the other <code>EOperation</code></li>
	 * </ol>
	 * 
	 * @param eop
	 *            first Eoperation
	 * @param otherEop
	 *            other Eoperation
	 * @return
	 */
	protected static boolean isTheSameEoperation(EOperation eop, EOperation otherEop) {
		boolean result = true;
		result &= eop.getName().equals(otherEop.getName());
		result &= eop.getEType().getName().equals(otherEop.getEType().getName());
		EList<EParameter> eopParamList = eop.getEParameters();
		EList<EParameter> otherEopParamList = otherEop.getEParameters();
		result &= eopParamList.size() == otherEopParamList.size();
		int limit = eopParamList.size();
		int cpt = 0;
		boolean stop = false;
		while ((cpt < limit) && !stop) {
			stop = true;
			// compare parameters number before made advanced compare this to avoid BasicIndexOutOfBoundsException
			if (eopParamList.size() == otherEopParamList.size()) {
				EParameter eopParam = eopParamList.get(cpt);
				EParameter otherEopParam = otherEopParamList.get(cpt);
				if (isSubType(eopParam, otherEopParam) || isSubType(otherEopParam, eopParam)) {
					stop = false;
				}
			}
			cpt++;
		}
		result &= !stop;
		return result;
	}

	/**
	 * returns true, if second method argument is the same type or a subType of
	 * first method argument
	 * 
	 * @param epaType
	 * @param epaSubType
	 * @return
	 */
	protected static boolean isSubType(EParameter epaType, EParameter epaSubType) {
		EClassifier eClassifierSubType = epaSubType.getEType();

		boolean result = epaType.getEType().getName().equals(epaSubType.getEType().getName());

		// cast in EClassImpl because its impossible to get SuperType from an
		// EClassifier
		if (eClassifierSubType instanceof EClassImpl && !result) {
			EClassImpl eci = (EClassImpl) eClassifierSubType;
			EList<EClass> elistSubTypeSuperType = eci.getEAllSuperTypes();
			Iterator<EClass> iterListSuperType = elistSubTypeSuperType.iterator();
			while (iterListSuperType.hasNext() && !result) {
				result = iterListSuperType.next().getName().equals(epaType.getEType().getName());

			}
		}

		return result;
	}
}
