/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Form Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.FormClassImpl#getReal_class <em>Real class</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FormClassImpl#isHas_content <em>Has content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormClassImpl extends FormContainerImpl implements FormClass {
	/**
	 * The cached value of the '{@link #getReal_class() <em>Real class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReal_class()
	 * @generated
	 * @ordered
	 */
	protected Clazz real_class;

	/**
	 * The default value of the '{@link #isHas_content() <em>Has content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHas_content()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_CONTENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHas_content() <em>Has content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHas_content()
	 * @generated
	 * @ordered
	 */
	protected boolean has_content = HAS_CONTENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.FORM_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz getReal_class() {
		if (real_class != null && real_class.eIsProxy()) {
			InternalEObject oldReal_class = (InternalEObject)real_class;
			real_class = (Clazz)eResolveProxy(oldReal_class);
			if (real_class != oldReal_class) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.FORM_CLASS__REAL_CLASS, oldReal_class, real_class));
			}
		}
		return real_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz basicGetReal_class() {
		return real_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReal_class(Clazz newReal_class) {
		Clazz oldReal_class = real_class;
		real_class = newReal_class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_CLASS__REAL_CLASS, oldReal_class, real_class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHas_content() {
		return has_content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHas_content(boolean newHas_content) {
		boolean oldHas_content = has_content;
		has_content = newHas_content;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_CLASS__HAS_CONTENT, oldHas_content, has_content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.FORM_CLASS__REAL_CLASS:
				if (resolve) return getReal_class();
				return basicGetReal_class();
			case FormPackage.FORM_CLASS__HAS_CONTENT:
				return isHas_content();
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
			case FormPackage.FORM_CLASS__REAL_CLASS:
				setReal_class((Clazz)newValue);
				return;
			case FormPackage.FORM_CLASS__HAS_CONTENT:
				setHas_content((Boolean)newValue);
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
			case FormPackage.FORM_CLASS__REAL_CLASS:
				setReal_class((Clazz)null);
				return;
			case FormPackage.FORM_CLASS__HAS_CONTENT:
				setHas_content(HAS_CONTENT_EDEFAULT);
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
			case FormPackage.FORM_CLASS__REAL_CLASS:
				return real_class != null;
			case FormPackage.FORM_CLASS__HAS_CONTENT:
				return has_content != HAS_CONTENT_EDEFAULT;
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
				case FormPackage.FORM_CLASS__REAL_CLASS: return FormPackage.CLASS_REFERENCE__REAL_CLASS;
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
				case FormPackage.CLASS_REFERENCE__REAL_CLASS: return FormPackage.FORM_CLASS__REAL_CLASS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (Has_content: ");
		result.append(has_content);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

	

} //FormClassImpl
