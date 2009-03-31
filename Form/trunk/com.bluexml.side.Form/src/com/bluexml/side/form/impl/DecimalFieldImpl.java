/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;
import com.bluexml.side.form.DecimalField;
import com.bluexml.side.form.formPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Decimal Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.DecimalFieldImpl#getMin_value <em>Min value</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.DecimalFieldImpl#getMax_value <em>Max value</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.DecimalFieldImpl#getMax_digits <em>Max digits</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.DecimalFieldImpl#getDecimal_places <em>Decimal places</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DecimalFieldImpl extends FieldImpl implements DecimalField {
	/**
	 * The default value of the '{@link #getMin_value() <em>Min value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_value()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_VALUE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMin_value() <em>Min value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_value()
	 * @generated
	 * @ordered
	 */
	protected int min_value = MIN_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax_value() <em>Max value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_value()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_VALUE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMax_value() <em>Max value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_value()
	 * @generated
	 * @ordered
	 */
	protected int max_value = MAX_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax_digits() <em>Max digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_digits()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_DIGITS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMax_digits() <em>Max digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_digits()
	 * @generated
	 * @ordered
	 */
	protected int max_digits = MAX_DIGITS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDecimal_places() <em>Decimal places</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecimal_places()
	 * @generated
	 * @ordered
	 */
	protected static final int DECIMAL_PLACES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDecimal_places() <em>Decimal places</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecimal_places()
	 * @generated
	 * @ordered
	 */
	protected int decimal_places = DECIMAL_PLACES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DecimalFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return formPackage.Literals.DECIMAL_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMin_value() {
		return min_value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin_value(int newMin_value) {
		int oldMin_value = min_value;
		min_value = newMin_value;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.DECIMAL_FIELD__MIN_VALUE, oldMin_value, min_value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMax_value() {
		return max_value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax_value(int newMax_value) {
		int oldMax_value = max_value;
		max_value = newMax_value;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.DECIMAL_FIELD__MAX_VALUE, oldMax_value, max_value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMax_digits() {
		return max_digits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax_digits(int newMax_digits) {
		int oldMax_digits = max_digits;
		max_digits = newMax_digits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.DECIMAL_FIELD__MAX_DIGITS, oldMax_digits, max_digits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDecimal_places() {
		return decimal_places;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDecimal_places(int newDecimal_places) {
		int oldDecimal_places = decimal_places;
		decimal_places = newDecimal_places;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.DECIMAL_FIELD__DECIMAL_PLACES, oldDecimal_places, decimal_places));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case formPackage.DECIMAL_FIELD__MIN_VALUE:
				return new Integer(getMin_value());
			case formPackage.DECIMAL_FIELD__MAX_VALUE:
				return new Integer(getMax_value());
			case formPackage.DECIMAL_FIELD__MAX_DIGITS:
				return new Integer(getMax_digits());
			case formPackage.DECIMAL_FIELD__DECIMAL_PLACES:
				return new Integer(getDecimal_places());
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
			case formPackage.DECIMAL_FIELD__MIN_VALUE:
				setMin_value(((Integer)newValue).intValue());
				return;
			case formPackage.DECIMAL_FIELD__MAX_VALUE:
				setMax_value(((Integer)newValue).intValue());
				return;
			case formPackage.DECIMAL_FIELD__MAX_DIGITS:
				setMax_digits(((Integer)newValue).intValue());
				return;
			case formPackage.DECIMAL_FIELD__DECIMAL_PLACES:
				setDecimal_places(((Integer)newValue).intValue());
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
			case formPackage.DECIMAL_FIELD__MIN_VALUE:
				setMin_value(MIN_VALUE_EDEFAULT);
				return;
			case formPackage.DECIMAL_FIELD__MAX_VALUE:
				setMax_value(MAX_VALUE_EDEFAULT);
				return;
			case formPackage.DECIMAL_FIELD__MAX_DIGITS:
				setMax_digits(MAX_DIGITS_EDEFAULT);
				return;
			case formPackage.DECIMAL_FIELD__DECIMAL_PLACES:
				setDecimal_places(DECIMAL_PLACES_EDEFAULT);
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
			case formPackage.DECIMAL_FIELD__MIN_VALUE:
				return min_value != MIN_VALUE_EDEFAULT;
			case formPackage.DECIMAL_FIELD__MAX_VALUE:
				return max_value != MAX_VALUE_EDEFAULT;
			case formPackage.DECIMAL_FIELD__MAX_DIGITS:
				return max_digits != MAX_DIGITS_EDEFAULT;
			case formPackage.DECIMAL_FIELD__DECIMAL_PLACES:
				return decimal_places != DECIMAL_PLACES_EDEFAULT;
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
		result.append(" (min_value: ");
		result.append(min_value);
		result.append(", max_value: ");
		result.append(max_value);
		result.append(", max_digits: ");
		result.append(max_digits);
		result.append(", decimal_places: ");
		result.append(decimal_places);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //DecimalFieldImpl
