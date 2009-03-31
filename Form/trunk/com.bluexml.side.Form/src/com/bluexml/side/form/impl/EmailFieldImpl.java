/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.ocl.ecore.OCL;
import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.formPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Email Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EmailFieldImpl extends CharFieldImpl implements EmailField {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EmailFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return formPackage.Literals.EMAIL_FIELD;
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //EmailFieldImpl
