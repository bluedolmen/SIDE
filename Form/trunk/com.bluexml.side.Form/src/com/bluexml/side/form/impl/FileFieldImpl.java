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
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.formPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class FileFieldImpl extends FieldImpl implements FileField {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return formPackage.Literals.FILE_FIELD;
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //FileFieldImpl
