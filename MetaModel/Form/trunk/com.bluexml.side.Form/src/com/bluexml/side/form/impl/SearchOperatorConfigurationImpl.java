/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.SearchOperatorConfiguration;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Search Operator Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.SearchOperatorConfigurationImpl#getDefaultOperator <em>Default Operator</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.SearchOperatorConfigurationImpl#getProposedOperators <em>Proposed Operators</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SearchOperatorConfigurationImpl extends EObjectImpl implements SearchOperatorConfiguration {
	/**
	 * The default value of the '{@link #getDefaultOperator() <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultOperator()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_OPERATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultOperator() <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultOperator()
	 * @generated
	 * @ordered
	 */
	protected String defaultOperator = DEFAULT_OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProposedOperators() <em>Proposed Operators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProposedOperators()
	 * @generated
	 * @ordered
	 */
	protected EList<String> proposedOperators;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SearchOperatorConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.SEARCH_OPERATOR_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultOperator() {
		return defaultOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultOperator(String newDefaultOperator) {
		String oldDefaultOperator = defaultOperator;
		defaultOperator = newDefaultOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.SEARCH_OPERATOR_CONFIGURATION__DEFAULT_OPERATOR, oldDefaultOperator, defaultOperator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getProposedOperators() {
		if (proposedOperators == null) {
			proposedOperators = new EDataTypeUniqueEList<String>(String.class, this, FormPackage.SEARCH_OPERATOR_CONFIGURATION__PROPOSED_OPERATORS);
		}
		return proposedOperators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.SEARCH_OPERATOR_CONFIGURATION__DEFAULT_OPERATOR:
				return getDefaultOperator();
			case FormPackage.SEARCH_OPERATOR_CONFIGURATION__PROPOSED_OPERATORS:
				return getProposedOperators();
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
			case FormPackage.SEARCH_OPERATOR_CONFIGURATION__DEFAULT_OPERATOR:
				setDefaultOperator((String)newValue);
				return;
			case FormPackage.SEARCH_OPERATOR_CONFIGURATION__PROPOSED_OPERATORS:
				getProposedOperators().clear();
				getProposedOperators().addAll((Collection<? extends String>)newValue);
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
			case FormPackage.SEARCH_OPERATOR_CONFIGURATION__DEFAULT_OPERATOR:
				setDefaultOperator(DEFAULT_OPERATOR_EDEFAULT);
				return;
			case FormPackage.SEARCH_OPERATOR_CONFIGURATION__PROPOSED_OPERATORS:
				getProposedOperators().clear();
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
			case FormPackage.SEARCH_OPERATOR_CONFIGURATION__DEFAULT_OPERATOR:
				return DEFAULT_OPERATOR_EDEFAULT == null ? defaultOperator != null : !DEFAULT_OPERATOR_EDEFAULT.equals(defaultOperator);
			case FormPackage.SEARCH_OPERATOR_CONFIGURATION__PROPOSED_OPERATORS:
				return proposedOperators != null && !proposedOperators.isEmpty();
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
		result.append(" (defaultOperator: ");
		result.append(defaultOperator);
		result.append(", proposedOperators: ");
		result.append(proposedOperators);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //SearchOperatorConfigurationImpl
