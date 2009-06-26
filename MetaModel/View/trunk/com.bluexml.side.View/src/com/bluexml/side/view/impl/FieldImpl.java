/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.ViewPackage;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.FieldImpl#getPatern <em>Patern</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldImpl#getPaternLanguage <em>Patern Language</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldImpl#getPath <em>Path</em>}</li>
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
	 * The default value of the '{@link #getPaternLanguage() <em>Patern Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaternLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String PATERN_LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPaternLanguage() <em>Patern Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaternLanguage()
	 * @generated
	 * @ordered
	 */
	protected String paternLanguage = PATERN_LANGUAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> path;

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
	public String getPaternLanguage() {
		return paternLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPaternLanguage(String newPaternLanguage) {
		String oldPaternLanguage = paternLanguage;
		paternLanguage = newPaternLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD__PATERN_LANGUAGE, oldPaternLanguage, paternLanguage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getPath() {
		if (path == null) {
			path = new EObjectResolvingEList<EObject>(EObject.class, this, ViewPackage.FIELD__PATH);
		}
		return path;
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
			case ViewPackage.FIELD__PATERN_LANGUAGE:
				return getPaternLanguage();
			case ViewPackage.FIELD__PATH:
				return getPath();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ViewPackage.FIELD__PATERN:
				setPatern((String)newValue);
				return;
			case ViewPackage.FIELD__PATERN_LANGUAGE:
				setPaternLanguage((String)newValue);
				return;
			case ViewPackage.FIELD__PATH:
				getPath().clear();
				getPath().addAll((Collection<? extends EObject>)newValue);
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
			case ViewPackage.FIELD__PATERN_LANGUAGE:
				setPaternLanguage(PATERN_LANGUAGE_EDEFAULT);
				return;
			case ViewPackage.FIELD__PATH:
				getPath().clear();
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
			case ViewPackage.FIELD__PATERN_LANGUAGE:
				return PATERN_LANGUAGE_EDEFAULT == null ? paternLanguage != null : !PATERN_LANGUAGE_EDEFAULT.equals(paternLanguage);
			case ViewPackage.FIELD__PATH:
				return path != null && !path.isEmpty();
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
		result.append(", paternLanguage: ");
		result.append(paternLanguage);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //FieldImpl
