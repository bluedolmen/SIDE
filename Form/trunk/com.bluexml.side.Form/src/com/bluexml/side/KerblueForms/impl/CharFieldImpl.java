/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.KerblueForms.impl;

import com.bluexml.side.KerblueForms.CharField;
import com.bluexml.side.KerblueForms.formPackage;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Char Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.KerblueForms.impl.CharFieldImpl#getMin_length <em>Min length</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.impl.CharFieldImpl#getMax_length <em>Max length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CharFieldImpl extends FieldImpl implements CharField {
	/**
	 * The default value of the '{@link #getMin_length() <em>Min length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_length()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMin_length() <em>Min length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_length()
	 * @generated
	 * @ordered
	 */
	protected int min_length = MIN_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax_length() <em>Max length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_length()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMax_length() <em>Max length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_length()
	 * @generated
	 * @ordered
	 */
	protected int max_length = MAX_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CharFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return formPackage.Literals.CHAR_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMin_length() {
		return min_length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin_length(int newMin_length) {
		int oldMin_length = min_length;
		min_length = newMin_length;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CHAR_FIELD__MIN_LENGTH, oldMin_length, min_length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMax_length() {
		return max_length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax_length(int newMax_length) {
		int oldMax_length = max_length;
		max_length = newMax_length;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CHAR_FIELD__MAX_LENGTH, oldMax_length, max_length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case formPackage.CHAR_FIELD__MIN_LENGTH:
				return new Integer(getMin_length());
			case formPackage.CHAR_FIELD__MAX_LENGTH:
				return new Integer(getMax_length());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case formPackage.CHAR_FIELD__MIN_LENGTH:
				setMin_length(((Integer)newValue).intValue());
				return;
			case formPackage.CHAR_FIELD__MAX_LENGTH:
				setMax_length(((Integer)newValue).intValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case formPackage.CHAR_FIELD__MIN_LENGTH:
				setMin_length(MIN_LENGTH_EDEFAULT);
				return;
			case formPackage.CHAR_FIELD__MAX_LENGTH:
				setMax_length(MAX_LENGTH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case formPackage.CHAR_FIELD__MIN_LENGTH:
				return min_length != MIN_LENGTH_EDEFAULT;
			case formPackage.CHAR_FIELD__MAX_LENGTH:
				return max_length != MAX_LENGTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (min_length: ");
		result.append(min_length);
		result.append(", max_length: ");
		result.append(max_length);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //CharFieldImpl
