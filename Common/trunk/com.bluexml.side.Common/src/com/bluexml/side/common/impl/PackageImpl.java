/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common.impl;

import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.Stereotype;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.common.impl.PackageImpl#getStereotypeSet <em>Stereotype Set</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.PackageImpl#getPackageSet <em>Package Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageImpl extends NamedModelElementImpl implements com.bluexml.side.common.Package {
	/**
	 * The cached value of the '{@link #getStereotypeSet() <em>Stereotype Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotypeSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Stereotype> stereotypeSet;

	/**
	 * The cached value of the '{@link #getPackageSet() <em>Package Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageSet()
	 * @generated
	 * @ordered
	 */
	protected EList<com.bluexml.side.common.Package> packageSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Stereotype> getStereotypeSet() {
		if (stereotypeSet == null) {
			stereotypeSet = new EObjectContainmentEList<Stereotype>(Stereotype.class, this, CommonPackage.PACKAGE__STEREOTYPE_SET);
		}
		return stereotypeSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<com.bluexml.side.common.Package> getPackageSet() {
		if (packageSet == null) {
			packageSet = new EObjectContainmentEList<com.bluexml.side.common.Package>(com.bluexml.side.common.Package.class, this, CommonPackage.PACKAGE__PACKAGE_SET);
		}
		return packageSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				return ((InternalEList<?>)getStereotypeSet()).basicRemove(otherEnd, msgs);
			case CommonPackage.PACKAGE__PACKAGE_SET:
				return ((InternalEList<?>)getPackageSet()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				return getStereotypeSet();
			case CommonPackage.PACKAGE__PACKAGE_SET:
				return getPackageSet();
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
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				getStereotypeSet().clear();
				getStereotypeSet().addAll((Collection<? extends Stereotype>)newValue);
				return;
			case CommonPackage.PACKAGE__PACKAGE_SET:
				getPackageSet().clear();
				getPackageSet().addAll((Collection<? extends com.bluexml.side.common.Package>)newValue);
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
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				getStereotypeSet().clear();
				return;
			case CommonPackage.PACKAGE__PACKAGE_SET:
				getPackageSet().clear();
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
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				return stereotypeSet != null && !stereotypeSet.isEmpty();
			case CommonPackage.PACKAGE__PACKAGE_SET:
				return packageSet != null && !packageSet.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PackageImpl
