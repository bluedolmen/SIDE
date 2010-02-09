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
import com.bluexml.side.form.FormSearch;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Search</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.FormSearchImpl#getDataForm <em>Data Form</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FormSearchImpl#getCombinationOperator <em>Combination Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormSearchImpl extends FormContainerImpl implements FormSearch {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormSearchImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.FORM_SEARCH;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.FORM_SEARCH__DATA_FORM, oldDataForm, dataForm));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_SEARCH__DATA_FORM, oldDataForm, dataForm));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_SEARCH__COMBINATION_OPERATOR, oldCombinationOperator, combinationOperator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.FORM_SEARCH__DATA_FORM:
				if (resolve) return getDataForm();
				return basicGetDataForm();
			case FormPackage.FORM_SEARCH__COMBINATION_OPERATOR:
				return getCombinationOperator();
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
			case FormPackage.FORM_SEARCH__DATA_FORM:
				setDataForm((FormClass)newValue);
				return;
			case FormPackage.FORM_SEARCH__COMBINATION_OPERATOR:
				setCombinationOperator((CombinationOperators)newValue);
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
			case FormPackage.FORM_SEARCH__DATA_FORM:
				setDataForm((FormClass)null);
				return;
			case FormPackage.FORM_SEARCH__COMBINATION_OPERATOR:
				setCombinationOperator(COMBINATION_OPERATOR_EDEFAULT);
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
			case FormPackage.FORM_SEARCH__DATA_FORM:
				return dataForm != null;
			case FormPackage.FORM_SEARCH__COMBINATION_OPERATOR:
				return combinationOperator != COMBINATION_OPERATOR_EDEFAULT;
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
} //FormSearchImpl
