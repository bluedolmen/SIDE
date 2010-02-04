/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.NumericalFieldSearchOperators;
import com.bluexml.side.form.NumericalSearchField;

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
 * An implementation of the model object '<em><b>Numerical Search Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.NumericalSearchFieldImpl#getOperators <em>Operators</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.NumericalSearchFieldImpl#getDefaultOperator <em>Default Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NumericalSearchFieldImpl extends SearchFieldImpl implements NumericalSearchField {
	/**
	 * The cached value of the '{@link #getOperators() <em>Operators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperators()
	 * @generated
	 * @ordered
	 */
	protected EList<NumericalFieldSearchOperators> operators;

	/**
	 * The default value of the '{@link #getDefaultOperator() <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultOperator()
	 * @generated
	 * @ordered
	 */
	protected static final NumericalFieldSearchOperators DEFAULT_OPERATOR_EDEFAULT = NumericalFieldSearchOperators.BETWEEN;

	/**
	 * The cached value of the '{@link #getDefaultOperator() <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultOperator()
	 * @generated
	 * @ordered
	 */
	protected NumericalFieldSearchOperators defaultOperator = DEFAULT_OPERATOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NumericalSearchFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.NUMERICAL_SEARCH_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NumericalFieldSearchOperators> getOperators() {
		if (operators == null) {
			operators = new EDataTypeUniqueEList<NumericalFieldSearchOperators>(NumericalFieldSearchOperators.class, this, FormPackage.NUMERICAL_SEARCH_FIELD__OPERATORS);
		}
		return operators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumericalFieldSearchOperators getDefaultOperator() {
		return defaultOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultOperator(NumericalFieldSearchOperators newDefaultOperator) {
		NumericalFieldSearchOperators oldDefaultOperator = defaultOperator;
		defaultOperator = newDefaultOperator == null ? DEFAULT_OPERATOR_EDEFAULT : newDefaultOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.NUMERICAL_SEARCH_FIELD__DEFAULT_OPERATOR, oldDefaultOperator, defaultOperator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.NUMERICAL_SEARCH_FIELD__OPERATORS:
				return getOperators();
			case FormPackage.NUMERICAL_SEARCH_FIELD__DEFAULT_OPERATOR:
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
			case FormPackage.NUMERICAL_SEARCH_FIELD__OPERATORS:
				getOperators().clear();
				getOperators().addAll((Collection<? extends NumericalFieldSearchOperators>)newValue);
				return;
			case FormPackage.NUMERICAL_SEARCH_FIELD__DEFAULT_OPERATOR:
				setDefaultOperator((NumericalFieldSearchOperators)newValue);
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
			case FormPackage.NUMERICAL_SEARCH_FIELD__OPERATORS:
				getOperators().clear();
				return;
			case FormPackage.NUMERICAL_SEARCH_FIELD__DEFAULT_OPERATOR:
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
			case FormPackage.NUMERICAL_SEARCH_FIELD__OPERATORS:
				return operators != null && !operators.isEmpty();
			case FormPackage.NUMERICAL_SEARCH_FIELD__DEFAULT_OPERATOR:
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
} //NumericalSearchFieldImpl
