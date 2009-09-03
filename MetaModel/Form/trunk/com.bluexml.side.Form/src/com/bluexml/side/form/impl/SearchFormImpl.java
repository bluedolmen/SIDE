/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.SearchForm;

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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.SEARCH_FORM__DATA_FORM:
				if (resolve) return getDataForm();
				return basicGetDataForm();
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
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //SearchFormImpl
