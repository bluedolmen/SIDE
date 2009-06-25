/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.ocl.ecore.OCL;
import com.bluexml.side.form.DateField;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Date Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.DateFieldImpl#getInput_formats <em>Input formats</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.DateFieldImpl#getMin_date <em>Min date</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.DateFieldImpl#getMax_date <em>Max date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DateFieldImpl extends FieldImpl implements DateField {
	/**
	 * The cached value of the '{@link #getInput_formats() <em>Input formats</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInput_formats()
	 * @generated
	 * @ordered
	 */
	protected EList<String> input_formats;

	/**
	 * The default value of the '{@link #getMin_date() <em>Min date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_date()
	 * @generated
	 * @ordered
	 */
	protected static final Date MIN_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMin_date() <em>Min date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_date()
	 * @generated
	 * @ordered
	 */
	protected Date min_date = MIN_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax_date() <em>Max date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_date()
	 * @generated
	 * @ordered
	 */
	protected static final Date MAX_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMax_date() <em>Max date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_date()
	 * @generated
	 * @ordered
	 */
	protected Date max_date = MAX_DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DateFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.DATE_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getInput_formats() {
		if (input_formats == null) {
			input_formats = new EDataTypeUniqueEList<String>(String.class, this, FormPackage.DATE_FIELD__INPUT_FORMATS);
		}
		return input_formats;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getMin_date() {
		return min_date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin_date(Date newMin_date) {
		Date oldMin_date = min_date;
		min_date = newMin_date;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DATE_FIELD__MIN_DATE, oldMin_date, min_date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getMax_date() {
		return max_date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax_date(Date newMax_date) {
		Date oldMax_date = max_date;
		max_date = newMax_date;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DATE_FIELD__MAX_DATE, oldMax_date, max_date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.DATE_FIELD__INPUT_FORMATS:
				return getInput_formats();
			case FormPackage.DATE_FIELD__MIN_DATE:
				return getMin_date();
			case FormPackage.DATE_FIELD__MAX_DATE:
				return getMax_date();
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
			case FormPackage.DATE_FIELD__INPUT_FORMATS:
				getInput_formats().clear();
				getInput_formats().addAll((Collection<? extends String>)newValue);
				return;
			case FormPackage.DATE_FIELD__MIN_DATE:
				setMin_date((Date)newValue);
				return;
			case FormPackage.DATE_FIELD__MAX_DATE:
				setMax_date((Date)newValue);
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
			case FormPackage.DATE_FIELD__INPUT_FORMATS:
				getInput_formats().clear();
				return;
			case FormPackage.DATE_FIELD__MIN_DATE:
				setMin_date(MIN_DATE_EDEFAULT);
				return;
			case FormPackage.DATE_FIELD__MAX_DATE:
				setMax_date(MAX_DATE_EDEFAULT);
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
			case FormPackage.DATE_FIELD__INPUT_FORMATS:
				return input_formats != null && !input_formats.isEmpty();
			case FormPackage.DATE_FIELD__MIN_DATE:
				return MIN_DATE_EDEFAULT == null ? min_date != null : !MIN_DATE_EDEFAULT.equals(min_date);
			case FormPackage.DATE_FIELD__MAX_DATE:
				return MAX_DATE_EDEFAULT == null ? max_date != null : !MAX_DATE_EDEFAULT.equals(max_date);
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
		result.append(" (input_formats: ");
		result.append(input_formats);
		result.append(", min_date: ");
		result.append(min_date);
		result.append(", max_date: ");
		result.append(max_date);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //DateFieldImpl
