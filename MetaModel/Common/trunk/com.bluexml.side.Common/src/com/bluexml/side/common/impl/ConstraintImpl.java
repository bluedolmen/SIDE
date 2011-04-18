/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common.impl;

import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.Constraint;
import com.bluexml.side.common.ConstraintParam;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.common.impl.ConstraintImpl#getConstraintType <em>Constraint Type</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.ConstraintImpl#getParams <em>Params</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintImpl extends NamedModelElementImpl implements Constraint {
	/**
	 * The default value of the '{@link #getConstraintType() <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintType()
	 * @generated
	 * @ordered
	 */
	protected static final String CONSTRAINT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstraintType() <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintType()
	 * @generated
	 * @ordered
	 */
	protected String constraintType = CONSTRAINT_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParams() <em>Params</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParams()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintParam> params;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConstraintType() {
		return constraintType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintType(String newConstraintType) {
		String oldConstraintType = constraintType;
		constraintType = newConstraintType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CONSTRAINT__CONSTRAINT_TYPE, oldConstraintType, constraintType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConstraintParam> getParams() {
		if (params == null) {
			params = new EObjectContainmentEList<ConstraintParam>(ConstraintParam.class, this, CommonPackage.CONSTRAINT__PARAMS);
		}
		return params;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.CONSTRAINT__PARAMS:
				return ((InternalEList<?>)getParams()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.CONSTRAINT__CONSTRAINT_TYPE:
				return getConstraintType();
			case CommonPackage.CONSTRAINT__PARAMS:
				return getParams();
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
			case CommonPackage.CONSTRAINT__CONSTRAINT_TYPE:
				setConstraintType((String)newValue);
				return;
			case CommonPackage.CONSTRAINT__PARAMS:
				getParams().clear();
				getParams().addAll((Collection<? extends ConstraintParam>)newValue);
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
			case CommonPackage.CONSTRAINT__CONSTRAINT_TYPE:
				setConstraintType(CONSTRAINT_TYPE_EDEFAULT);
				return;
			case CommonPackage.CONSTRAINT__PARAMS:
				getParams().clear();
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
			case CommonPackage.CONSTRAINT__CONSTRAINT_TYPE:
				return CONSTRAINT_TYPE_EDEFAULT == null ? constraintType != null : !CONSTRAINT_TYPE_EDEFAULT.equals(constraintType);
			case CommonPackage.CONSTRAINT__PARAMS:
				return params != null && !params.isEmpty();
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
		result.append(" (constraintType: ");
		result.append(constraintType);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //ConstraintImpl
