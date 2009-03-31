/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.ocl.ecore.OCL;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.formPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.ClassReferenceImpl#getReal_class <em>Real class</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ClassReferenceImpl#getAssociation_class <em>Association class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ClassReferenceImpl extends EObjectImpl implements ClassReference {
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
	 * The cached value of the '{@link #getAssociation_class() <em>Association class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociation_class()
	 * @generated
	 * @ordered
	 */
	protected Clazz association_class;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return formPackage.Literals.CLASS_REFERENCE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, formPackage.CLASS_REFERENCE__REAL_CLASS, oldReal_class, real_class));
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
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CLASS_REFERENCE__REAL_CLASS, oldReal_class, real_class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz getAssociation_class() {
		if (association_class != null && association_class.eIsProxy()) {
			InternalEObject oldAssociation_class = (InternalEObject)association_class;
			association_class = (Clazz)eResolveProxy(oldAssociation_class);
			if (association_class != oldAssociation_class) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, formPackage.CLASS_REFERENCE__ASSOCIATION_CLASS, oldAssociation_class, association_class));
			}
		}
		return association_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz basicGetAssociation_class() {
		return association_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociation_class(Clazz newAssociation_class) {
		Clazz oldAssociation_class = association_class;
		association_class = newAssociation_class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CLASS_REFERENCE__ASSOCIATION_CLASS, oldAssociation_class, association_class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case formPackage.CLASS_REFERENCE__REAL_CLASS:
				if (resolve) return getReal_class();
				return basicGetReal_class();
			case formPackage.CLASS_REFERENCE__ASSOCIATION_CLASS:
				if (resolve) return getAssociation_class();
				return basicGetAssociation_class();
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
			case formPackage.CLASS_REFERENCE__REAL_CLASS:
				setReal_class((Clazz)newValue);
				return;
			case formPackage.CLASS_REFERENCE__ASSOCIATION_CLASS:
				setAssociation_class((Clazz)newValue);
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
			case formPackage.CLASS_REFERENCE__REAL_CLASS:
				setReal_class((Clazz)null);
				return;
			case formPackage.CLASS_REFERENCE__ASSOCIATION_CLASS:
				setAssociation_class((Clazz)null);
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
			case formPackage.CLASS_REFERENCE__REAL_CLASS:
				return real_class != null;
			case formPackage.CLASS_REFERENCE__ASSOCIATION_CLASS:
				return association_class != null;
		}
		return super.eIsSet(featureID);
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //ClassReferenceImpl
