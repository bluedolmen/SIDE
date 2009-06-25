/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.ocl.ecore.OCL;
import com.bluexml.side.form.TimeField;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class TimeFieldImpl extends DateFieldImpl implements TimeField {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.TIME_FIELD;
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //TimeFieldImpl
