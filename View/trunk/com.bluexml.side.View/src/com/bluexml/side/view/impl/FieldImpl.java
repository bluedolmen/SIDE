/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import com.bluexml.side.view.Field;
import com.bluexml.side.view.ViewPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.FieldImpl#getPatern <em>Patern</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldImpl#getPatrenLanguage <em>Patren Language</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FieldImpl extends FieldElementImpl implements Field {
	/**
	 * The default value of the '{@link #getPatern() <em>Patern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPatern()
	 * @generated
	 * @ordered
	 */
	protected static final String PATERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPatern() <em>Patern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPatern()
	 * @generated
	 * @ordered
	 */
	protected String patern = PATERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getPatrenLanguage() <em>Patren Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPatrenLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String PATREN_LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPatrenLanguage() <em>Patren Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPatrenLanguage()
	 * @generated
	 * @ordered
	 */
	protected String patrenLanguage = PATREN_LANGUAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPatern() {
		return patern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPatern(String newPatern) {
		String oldPatern = patern;
		patern = newPatern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD__PATERN, oldPatern, patern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPatrenLanguage() {
		return patrenLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPatrenLanguage(String newPatrenLanguage) {
		String oldPatrenLanguage = patrenLanguage;
		patrenLanguage = newPatrenLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD__PATREN_LANGUAGE, oldPatrenLanguage, patrenLanguage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViewPackage.FIELD__PATERN:
				return getPatern();
			case ViewPackage.FIELD__PATREN_LANGUAGE:
				return getPatrenLanguage();
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
			case ViewPackage.FIELD__PATERN:
				setPatern((String)newValue);
				return;
			case ViewPackage.FIELD__PATREN_LANGUAGE:
				setPatrenLanguage((String)newValue);
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
			case ViewPackage.FIELD__PATERN:
				setPatern(PATERN_EDEFAULT);
				return;
			case ViewPackage.FIELD__PATREN_LANGUAGE:
				setPatrenLanguage(PATREN_LANGUAGE_EDEFAULT);
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
			case ViewPackage.FIELD__PATERN:
				return PATERN_EDEFAULT == null ? patern != null : !PATERN_EDEFAULT.equals(patern);
			case ViewPackage.FIELD__PATREN_LANGUAGE:
				return PATREN_LANGUAGE_EDEFAULT == null ? patrenLanguage != null : !PATREN_LANGUAGE_EDEFAULT.equals(patrenLanguage);
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
		result.append(" (patern: ");
		result.append(patern);
		result.append(", patrenLanguage: ");
		result.append(patrenLanguage);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //FieldImpl
