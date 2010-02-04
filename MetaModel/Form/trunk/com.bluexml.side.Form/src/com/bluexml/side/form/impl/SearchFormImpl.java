/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.form.CombinationOperators;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.SearchForm;

import com.bluexml.side.form.SearchFormCollection;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Search Form</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.SearchFormImpl#getDataForm <em>Data Form</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.SearchFormImpl#getCombinationOperator <em>Combination Operator</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.SearchFormImpl#getEReference0 <em>EReference0</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SearchFormImpl extends FormContainerImpl implements SearchForm {
	/**
	 * The cached value of the '{@link #getDataForm() <em>Data Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataForm()
	 * @generated
	 * @ordered
	 */
	protected FormClass dataForm;

	/**
	 * The default value of the '{@link #getCombinationOperator() <em>Combination Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombinationOperator()
	 * @generated
	 * @ordered
	 */
	protected static final CombinationOperators COMBINATION_OPERATOR_EDEFAULT = CombinationOperators.AND;
	/**
	 * The cached value of the '{@link #getCombinationOperator() <em>Combination Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombinationOperator()
	 * @generated
	 * @ordered
	 */
	protected CombinationOperators combinationOperator = COMBINATION_OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected SearchFormCollection eReference0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SearchFormImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.SEARCH_FORM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormClass getDataForm() {
		if (dataForm != null && dataForm.eIsProxy()) {
			InternalEObject oldDataForm = (InternalEObject)dataForm;
			dataForm = (FormClass)eResolveProxy(oldDataForm);
			if (dataForm != oldDataForm) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.SEARCH_FORM__DATA_FORM, oldDataForm, dataForm));
			}
		}
		return dataForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormClass basicGetDataForm() {
		return dataForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataForm(FormClass newDataForm) {
		FormClass oldDataForm = dataForm;
		dataForm = newDataForm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.SEARCH_FORM__DATA_FORM, oldDataForm, dataForm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CombinationOperators getCombinationOperator() {
		return combinationOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCombinationOperator(CombinationOperators newCombinationOperator) {
		CombinationOperators oldCombinationOperator = combinationOperator;
		combinationOperator = newCombinationOperator == null ? COMBINATION_OPERATOR_EDEFAULT : newCombinationOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.SEARCH_FORM__COMBINATION_OPERATOR, oldCombinationOperator, combinationOperator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchFormCollection getEReference0() {
		if (eReference0 != null && eReference0.eIsProxy()) {
			InternalEObject oldEReference0 = (InternalEObject)eReference0;
			eReference0 = (SearchFormCollection)eResolveProxy(oldEReference0);
			if (eReference0 != oldEReference0) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.SEARCH_FORM__EREFERENCE0, oldEReference0, eReference0));
			}
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchFormCollection basicGetEReference0() {
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEReference0(SearchFormCollection newEReference0) {
		SearchFormCollection oldEReference0 = eReference0;
		eReference0 = newEReference0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.SEARCH_FORM__EREFERENCE0, oldEReference0, eReference0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.SEARCH_FORM__DATA_FORM:
				if (resolve) return getDataForm();
				return basicGetDataForm();
			case FormPackage.SEARCH_FORM__COMBINATION_OPERATOR:
				return getCombinationOperator();
			case FormPackage.SEARCH_FORM__EREFERENCE0:
				if (resolve) return getEReference0();
				return basicGetEReference0();
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
			case FormPackage.SEARCH_FORM__DATA_FORM:
				setDataForm((FormClass)newValue);
				return;
			case FormPackage.SEARCH_FORM__COMBINATION_OPERATOR:
				setCombinationOperator((CombinationOperators)newValue);
				return;
			case FormPackage.SEARCH_FORM__EREFERENCE0:
				setEReference0((SearchFormCollection)newValue);
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
			case FormPackage.SEARCH_FORM__DATA_FORM:
				setDataForm((FormClass)null);
				return;
			case FormPackage.SEARCH_FORM__COMBINATION_OPERATOR:
				setCombinationOperator(COMBINATION_OPERATOR_EDEFAULT);
				return;
			case FormPackage.SEARCH_FORM__EREFERENCE0:
				setEReference0((SearchFormCollection)null);
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
			case FormPackage.SEARCH_FORM__DATA_FORM:
				return dataForm != null;
			case FormPackage.SEARCH_FORM__COMBINATION_OPERATOR:
				return combinationOperator != COMBINATION_OPERATOR_EDEFAULT;
			case FormPackage.SEARCH_FORM__EREFERENCE0:
				return eReference0 != null;
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
		result.append(" (combinationOperator: ");
		result.append(combinationOperator);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //SearchFormImpl
