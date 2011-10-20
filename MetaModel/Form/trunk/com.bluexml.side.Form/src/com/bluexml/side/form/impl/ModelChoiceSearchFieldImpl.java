/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.clazz.AbstractClass;

import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.ModelChoiceSearchField;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Choice Search Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceSearchFieldImpl#getReal_class <em>Real class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelChoiceSearchFieldImpl extends SearchFieldImpl implements ModelChoiceSearchField {
	/**
	 * The cached value of the '{@link #getReal_class() <em>Real class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReal_class()
	 * @generated
	 * @ordered
	 */
	protected AbstractClass real_class;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelChoiceSearchFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.MODEL_CHOICE_SEARCH_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass getReal_class() {
		if (real_class != null && real_class.eIsProxy()) {
			InternalEObject oldReal_class = (InternalEObject)real_class;
			real_class = (AbstractClass)eResolveProxy(oldReal_class);
			if (real_class != oldReal_class) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.MODEL_CHOICE_SEARCH_FIELD__REAL_CLASS, oldReal_class, real_class));
			}
		}
		return real_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass basicGetReal_class() {
		return real_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReal_class(AbstractClass newReal_class) {
		AbstractClass oldReal_class = real_class;
		real_class = newReal_class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_SEARCH_FIELD__REAL_CLASS, oldReal_class, real_class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.MODEL_CHOICE_SEARCH_FIELD__REAL_CLASS:
				if (resolve) return getReal_class();
				return basicGetReal_class();
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
			case FormPackage.MODEL_CHOICE_SEARCH_FIELD__REAL_CLASS:
				setReal_class((AbstractClass)newValue);
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
			case FormPackage.MODEL_CHOICE_SEARCH_FIELD__REAL_CLASS:
				setReal_class((AbstractClass)null);
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
			case FormPackage.MODEL_CHOICE_SEARCH_FIELD__REAL_CLASS:
				return real_class != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ClassReference.class) {
			switch (derivedFeatureID) {
				case FormPackage.MODEL_CHOICE_SEARCH_FIELD__REAL_CLASS: return FormPackage.CLASS_REFERENCE__REAL_CLASS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ClassReference.class) {
			switch (baseFeatureID) {
				case FormPackage.CLASS_REFERENCE__REAL_CLASS: return FormPackage.MODEL_CHOICE_SEARCH_FIELD__REAL_CLASS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //ModelChoiceSearchFieldImpl
