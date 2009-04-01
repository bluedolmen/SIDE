/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.formPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getError_messages <em>Error messages</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getInitial <em>Initial</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getFieldSize <em>Field Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FieldImpl extends FormElementImpl implements Field {
	/**
	 * The default value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANDATORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected boolean mandatory = MANDATORY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getError_messages() <em>Error messages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getError_messages()
	 * @generated
	 * @ordered
	 */
	protected Map<String, String> error_messages;

	/**
	 * The default value of the '{@link #getInitial() <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitial()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitial() <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitial()
	 * @generated
	 * @ordered
	 */
	protected String initial = INITIAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISABLED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected boolean disabled = DISABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldSize() <em>Field Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldSize()
	 * @generated
	 * @ordered
	 */
	protected static final Integer FIELD_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldSize() <em>Field Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldSize()
	 * @generated
	 * @ordered
	 */
	protected Integer fieldSize = FIELD_SIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return formPackage.Literals.FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMandatory(boolean newMandatory) {
		boolean oldMandatory = mandatory;
		mandatory = newMandatory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.FIELD__MANDATORY, oldMandatory, mandatory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, String> getError_messages() {
		return error_messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setError_messages(Map<String, String> newError_messages) {
		Map<String, String> oldError_messages = error_messages;
		error_messages = newError_messages;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.FIELD__ERROR_MESSAGES, oldError_messages, error_messages));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInitial() {
		return initial;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitial(String newInitial) {
		String oldInitial = initial;
		initial = newInitial;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.FIELD__INITIAL, oldInitial, initial));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisabled(boolean newDisabled) {
		boolean oldDisabled = disabled;
		disabled = newDisabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.FIELD__DISABLED, oldDisabled, disabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getFieldSize() {
		return fieldSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldSize(Integer newFieldSize) {
		Integer oldFieldSize = fieldSize;
		fieldSize = newFieldSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.FIELD__FIELD_SIZE, oldFieldSize, fieldSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case formPackage.FIELD__MANDATORY:
				return isMandatory() ? Boolean.TRUE : Boolean.FALSE;
			case formPackage.FIELD__ERROR_MESSAGES:
				return getError_messages();
			case formPackage.FIELD__INITIAL:
				return getInitial();
			case formPackage.FIELD__DISABLED:
				return isDisabled() ? Boolean.TRUE : Boolean.FALSE;
			case formPackage.FIELD__FIELD_SIZE:
				return getFieldSize();
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
			case formPackage.FIELD__MANDATORY:
				setMandatory(((Boolean)newValue).booleanValue());
				return;
			case formPackage.FIELD__ERROR_MESSAGES:
				setError_messages((Map<String, String>)newValue);
				return;
			case formPackage.FIELD__INITIAL:
				setInitial((String)newValue);
				return;
			case formPackage.FIELD__DISABLED:
				setDisabled(((Boolean)newValue).booleanValue());
				return;
			case formPackage.FIELD__FIELD_SIZE:
				setFieldSize((Integer)newValue);
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
			case formPackage.FIELD__MANDATORY:
				setMandatory(MANDATORY_EDEFAULT);
				return;
			case formPackage.FIELD__ERROR_MESSAGES:
				setError_messages((Map<String, String>)null);
				return;
			case formPackage.FIELD__INITIAL:
				setInitial(INITIAL_EDEFAULT);
				return;
			case formPackage.FIELD__DISABLED:
				setDisabled(DISABLED_EDEFAULT);
				return;
			case formPackage.FIELD__FIELD_SIZE:
				setFieldSize(FIELD_SIZE_EDEFAULT);
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
			case formPackage.FIELD__MANDATORY:
				return mandatory != MANDATORY_EDEFAULT;
			case formPackage.FIELD__ERROR_MESSAGES:
				return error_messages != null;
			case formPackage.FIELD__INITIAL:
				return INITIAL_EDEFAULT == null ? initial != null : !INITIAL_EDEFAULT.equals(initial);
			case formPackage.FIELD__DISABLED:
				return disabled != DISABLED_EDEFAULT;
			case formPackage.FIELD__FIELD_SIZE:
				return FIELD_SIZE_EDEFAULT == null ? fieldSize != null : !FIELD_SIZE_EDEFAULT.equals(fieldSize);
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
		result.append(" (mandatory: ");
		result.append(mandatory);
		result.append(", error_messages: ");
		result.append(error_messages);
		result.append(", initial: ");
		result.append(initial);
		result.append(", disabled: ");
		result.append(disabled);
		result.append(", fieldSize: ");
		result.append(fieldSize);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //FieldImpl
