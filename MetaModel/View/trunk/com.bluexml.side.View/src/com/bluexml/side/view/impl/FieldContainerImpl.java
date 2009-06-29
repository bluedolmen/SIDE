/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.FieldContainer;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.FieldContainerImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldContainerImpl#getDisabled <em>Disabled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FieldContainerImpl extends FieldElementImpl implements FieldContainer {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<FieldElement> children;

	/**
	 * The cached value of the '{@link #getDisabled() <em>Disabled</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabled()
	 * @generated
	 * @ordered
	 */
	protected EList<FieldElement> disabled;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.FIELD_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.FIELD_CONTAINER__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getDisabled() {
		if (disabled == null) {
			disabled = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.FIELD_CONTAINER__DISABLED);
		}
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.FIELD_CONTAINER__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ViewPackage.FIELD_CONTAINER__DISABLED:
				return ((InternalEList<?>)getDisabled()).basicRemove(otherEnd, msgs);
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
			case ViewPackage.FIELD_CONTAINER__CHILDREN:
				return getChildren();
			case ViewPackage.FIELD_CONTAINER__DISABLED:
				return getDisabled();
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
			case ViewPackage.FIELD_CONTAINER__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.FIELD_CONTAINER__DISABLED:
				getDisabled().clear();
				getDisabled().addAll((Collection<? extends FieldElement>)newValue);
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
			case ViewPackage.FIELD_CONTAINER__CHILDREN:
				getChildren().clear();
				return;
			case ViewPackage.FIELD_CONTAINER__DISABLED:
				getDisabled().clear();
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
			case ViewPackage.FIELD_CONTAINER__CHILDREN:
				return children != null && !children.isEmpty();
			case ViewPackage.FIELD_CONTAINER__DISABLED:
				return disabled != null && !disabled.isEmpty();
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //FieldContainerImpl
