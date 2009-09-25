/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.form.FloatField;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Float Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.FloatFieldImpl#getMin_value <em>Min value</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FloatFieldImpl#getMax_value <em>Max value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FloatFieldImpl extends NumericalFieldImpl implements FloatField {
	/**
	 * The default value of the '{@link #getMin_value() <em>Min value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_value()
	 * @generated
	 * @ordered
	 */
	protected static final float MIN_VALUE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getMin_value() <em>Min value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_value()
	 * @generated
	 * @ordered
	 */
	protected float min_value = MIN_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax_value() <em>Max value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_value()
	 * @generated
	 * @ordered
	 */
	protected static final float MAX_VALUE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getMax_value() <em>Max value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_value()
	 * @generated
	 * @ordered
	 */
	protected float max_value = MAX_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.FLOAT_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getMin_value() {
		return min_value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin_value(float newMin_value) {
		float oldMin_value = min_value;
		min_value = newMin_value;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FLOAT_FIELD__MIN_VALUE, oldMin_value, min_value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getMax_value() {
		return max_value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax_value(float newMax_value) {
		float oldMax_value = max_value;
		max_value = newMax_value;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FLOAT_FIELD__MAX_VALUE, oldMax_value, max_value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.FLOAT_FIELD__MIN_VALUE:
				return new Float(getMin_value());
			case FormPackage.FLOAT_FIELD__MAX_VALUE:
				return new Float(getMax_value());
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
			case FormPackage.FLOAT_FIELD__MIN_VALUE:
				setMin_value(((Float)newValue).floatValue());
				return;
			case FormPackage.FLOAT_FIELD__MAX_VALUE:
				setMax_value(((Float)newValue).floatValue());
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
			case FormPackage.FLOAT_FIELD__MIN_VALUE:
				setMin_value(MIN_VALUE_EDEFAULT);
				return;
			case FormPackage.FLOAT_FIELD__MAX_VALUE:
				setMax_value(MAX_VALUE_EDEFAULT);
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
			case FormPackage.FLOAT_FIELD__MIN_VALUE:
				return min_value != MIN_VALUE_EDEFAULT;
			case FormPackage.FLOAT_FIELD__MAX_VALUE:
				return max_value != MAX_VALUE_EDEFAULT;
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
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //FloatFieldImpl
