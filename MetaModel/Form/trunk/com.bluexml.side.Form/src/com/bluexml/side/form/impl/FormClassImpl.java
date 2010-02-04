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
 *   <li>{@link com.bluexml.side.form.impl.FormClassImpl#isContent_enabled <em>Content enabled</em>}</li>
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
	 * The default value of the '{@link #isContent_enabled() <em>Content enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContent_enabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTENT_ENABLED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isContent_enabled() <em>Content enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContent_enabled()
	 * @generated
	 * @ordered
	 */
	protected boolean content_enabled = CONTENT_ENABLED_EDEFAULT;

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
	public boolean isContent_enabled() {
		return content_enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContent_enabled(boolean newContent_enabled) {
		boolean oldContent_enabled = content_enabled;
		content_enabled = newContent_enabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_CLASS__CONTENT_ENABLED, oldContent_enabled, content_enabled));
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
			case FormPackage.FORM_CLASS__CONTENT_ENABLED:
				return isContent_enabled();
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
			case FormPackage.FORM_CLASS__CONTENT_ENABLED:
				setContent_enabled((Boolean)newValue);
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
			case FormPackage.FORM_CLASS__CONTENT_ENABLED:
				setContent_enabled(CONTENT_ENABLED_EDEFAULT);
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
			case FormPackage.FORM_CLASS__CONTENT_ENABLED:
				return content_enabled != CONTENT_ENABLED_EDEFAULT;
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
		result.append(" (content_enabled: ");
		result.append(content_enabled);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

	

} //FormClassImpl
