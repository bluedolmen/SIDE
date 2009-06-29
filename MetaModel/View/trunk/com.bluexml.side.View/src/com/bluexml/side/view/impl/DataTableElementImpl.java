/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.DataTableElement;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Table Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class DataTableElementImpl extends EObjectImpl implements DataTableElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataTableElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.DATA_TABLE_ELEMENT;
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //DataTableElementImpl
