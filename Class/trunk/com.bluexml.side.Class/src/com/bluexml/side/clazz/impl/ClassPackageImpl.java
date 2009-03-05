/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;

import com.bluexml.side.common.impl.PackageImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getClassSet <em>Class Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getAssociationSet <em>Association Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getAspectSet <em>Aspect Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getEnumerationSet <em>Enumeration Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassPackageImpl extends PackageImpl implements ClassPackage {
	/**
	 * The cached value of the '{@link #getClassSet() <em>Class Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Clazz> classSet;

	/**
	 * The cached value of the '{@link #getAssociationSet() <em>Association Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> associationSet;

	/**
	 * The cached value of the '{@link #getAspectSet() <em>Aspect Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAspectSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Aspect> aspectSet;

	/**
	 * The cached value of the '{@link #getEnumerationSet() <em>Enumeration Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Enumeration> enumerationSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.CLASS_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getClassSet() {
		if (classSet == null) {
			classSet = new EObjectContainmentEList<Clazz>(Clazz.class, this, ClazzPackage.CLASS_PACKAGE__CLASS_SET);
		}
		return classSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAssociationSet() {
		if (associationSet == null) {
			associationSet = new EObjectContainmentEList<Association>(Association.class, this, ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET);
		}
		return associationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Aspect> getAspectSet() {
		if (aspectSet == null) {
			aspectSet = new EObjectContainmentEList<Aspect>(Aspect.class, this, ClazzPackage.CLASS_PACKAGE__ASPECT_SET);
		}
		return aspectSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Enumeration> getEnumerationSet() {
		if (enumerationSet == null) {
			enumerationSet = new EObjectContainmentEList<Enumeration>(Enumeration.class, this, ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET);
		}
		return enumerationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				return ((InternalEList<?>)getClassSet()).basicRemove(otherEnd, msgs);
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				return ((InternalEList<?>)getAssociationSet()).basicRemove(otherEnd, msgs);
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				return ((InternalEList<?>)getAspectSet()).basicRemove(otherEnd, msgs);
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				return ((InternalEList<?>)getEnumerationSet()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				return getClassSet();
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				return getAssociationSet();
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				return getAspectSet();
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				return getEnumerationSet();
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
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				getClassSet().clear();
				getClassSet().addAll((Collection<? extends Clazz>)newValue);
				return;
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				getAssociationSet().clear();
				getAssociationSet().addAll((Collection<? extends Association>)newValue);
				return;
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				getAspectSet().clear();
				getAspectSet().addAll((Collection<? extends Aspect>)newValue);
				return;
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				getEnumerationSet().clear();
				getEnumerationSet().addAll((Collection<? extends Enumeration>)newValue);
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
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				getClassSet().clear();
				return;
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				getAssociationSet().clear();
				return;
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				getAspectSet().clear();
				return;
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				getEnumerationSet().clear();
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
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				return classSet != null && !classSet.isEmpty();
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				return associationSet != null && !associationSet.isEmpty();
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				return aspectSet != null && !aspectSet.isEmpty();
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				return enumerationSet != null && !enumerationSet.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ClassPackageImpl
