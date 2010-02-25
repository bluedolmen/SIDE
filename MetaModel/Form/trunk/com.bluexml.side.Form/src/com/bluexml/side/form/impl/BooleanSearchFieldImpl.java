/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.form.BooleanFieldSearchOperators;
import com.bluexml.side.form.BooleanSearchField;
import com.bluexml.side.form.FormPackage;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Search Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.BooleanSearchFieldImpl#getOperators <em>Operators</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.BooleanSearchFieldImpl#getDefaultOperator <em>Default Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanSearchFieldImpl extends SearchFieldImpl implements BooleanSearchField {
	/**
	 * The cached value of the '{@link #getOperators() <em>Operators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperators()
	 * @generated
	 * @ordered
	 */
	protected EList<BooleanFieldSearchOperators> operators;

	/**
	 * The default value of the '{@link #getDefaultOperator() <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultOperator()
	 * @generated
	 * @ordered
	 */
	protected static final BooleanFieldSearchOperators DEFAULT_OPERATOR_EDEFAULT = BooleanFieldSearchOperators.IGNORE;

	/**
	 * The cached value of the '{@link #getDefaultOperator() <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultOperator()
	 * @generated
	 * @ordered
	 */
	protected BooleanFieldSearchOperators defaultOperator = DEFAULT_OPERATOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanSearchFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.BOOLEAN_SEARCH_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BooleanFieldSearchOperators> getOperators() {
		if (operators == null) {
			operators = new EDataTypeUniqueEList<BooleanFieldSearchOperators>(BooleanFieldSearchOperators.class, this, FormPackage.BOOLEAN_SEARCH_FIELD__OPERATORS);
		}
		return operators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanFieldSearchOperators getDefaultOperator() {
		return defaultOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultOperator(BooleanFieldSearchOperators newDefaultOperator) {
		BooleanFieldSearchOperators oldDefaultOperator = defaultOperator;
		defaultOperator = newDefaultOperator == null ? DEFAULT_OPERATOR_EDEFAULT : newDefaultOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.BOOLEAN_SEARCH_FIELD__DEFAULT_OPERATOR, oldDefaultOperator, defaultOperator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.BOOLEAN_SEARCH_FIELD__OPERATORS:
				return getOperators();
			case FormPackage.BOOLEAN_SEARCH_FIELD__DEFAULT_OPERATOR:
				return getDefaultOperator();
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
			case FormPackage.BOOLEAN_SEARCH_FIELD__OPERATORS:
				getOperators().clear();
				getOperators().addAll((Collection<? extends BooleanFieldSearchOperators>)newValue);
				return;
			case FormPackage.BOOLEAN_SEARCH_FIELD__DEFAULT_OPERATOR:
				setDefaultOperator((BooleanFieldSearchOperators)newValue);
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
			case FormPackage.BOOLEAN_SEARCH_FIELD__OPERATORS:
				getOperators().clear();
				return;
			case FormPackage.BOOLEAN_SEARCH_FIELD__DEFAULT_OPERATOR:
				setDefaultOperator(DEFAULT_OPERATOR_EDEFAULT);
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
			case FormPackage.BOOLEAN_SEARCH_FIELD__OPERATORS:
				return operators != null && !operators.isEmpty();
			case FormPackage.BOOLEAN_SEARCH_FIELD__DEFAULT_OPERATOR:
				return defaultOperator != DEFAULT_OPERATOR_EDEFAULT;
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
		result.append(" (operators: ");
		result.append(operators);
		result.append(", defaultOperator: ");
		result.append(defaultOperator);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //BooleanSearchFieldImpl
