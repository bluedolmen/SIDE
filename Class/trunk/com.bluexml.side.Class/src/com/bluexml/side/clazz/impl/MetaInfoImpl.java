/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.clazz.AttributeType;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.MetaInfo;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Meta Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.MetaInfoImpl#getKey <em>Key</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.MetaInfoImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.MetaInfoImpl#getValueType <em>Value Type</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.MetaInfoImpl#getConstraintType <em>Constraint Type</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.MetaInfoImpl#getValueSet <em>Value Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetaInfoImpl extends EObjectImpl implements MetaInfo {
	
	private boolean defaultValueBoolean = false;
	
	/**
	 * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getValueType() <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueType()
	 * @generated
	 * @ordered
	 */
	protected Class<?> valueType;

	/**
	 * The default value of the '{@link #getConstraintType() <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintType()
	 * @generated
	 * @ordered
	 */
	protected static final AttributeType CONSTRAINT_TYPE_EDEFAULT = AttributeType.BOOLEAN;

	/**
	 * The cached value of the '{@link #getConstraintType() <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintType()
	 * @generated
	 * @ordered
	 */
	protected AttributeType constraintType = CONSTRAINT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getValueSet() <em>Value Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueSet()
	 * @generated
	 * @ordered
	 */
	protected static final Object VALUE_SET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValueSet() <em>Value Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueSet()
	 * @generated
	 * @ordered
	 */
	protected Object valueSet = VALUE_SET_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetaInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.META_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.META_INFO__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.META_INFO__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Class<?> getValueType() {
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueType(Class<?> newValueType) {
		Class<?> oldValueType = valueType;
		valueType = newValueType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.META_INFO__VALUE_TYPE, oldValueType, valueType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeType getConstraintType() {
		return constraintType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintType(AttributeType newConstraintType) {
		AttributeType oldConstraintType = constraintType;
		constraintType = newConstraintType == null ? CONSTRAINT_TYPE_EDEFAULT : newConstraintType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.META_INFO__CONSTRAINT_TYPE, oldConstraintType, constraintType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getValueSet() {
		return valueSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueSet(Object newValueSet) {
		Object oldValueSet = valueSet;
		valueSet = newValueSet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.META_INFO__VALUE_SET, oldValueSet, valueSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClazzPackage.META_INFO__KEY:
				return getKey();
			case ClazzPackage.META_INFO__VALUE:
				return getValue();
			case ClazzPackage.META_INFO__VALUE_TYPE:
				return getValueType();
			case ClazzPackage.META_INFO__CONSTRAINT_TYPE:
				return getConstraintType();
			case ClazzPackage.META_INFO__VALUE_SET:
				return getValueSet();
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
			case ClazzPackage.META_INFO__KEY:
				setKey((String)newValue);
				return;
			case ClazzPackage.META_INFO__VALUE:
				setValue((String)newValue);
				return;
			case ClazzPackage.META_INFO__VALUE_TYPE:
				setValueType((Class<?>)newValue);
				return;
			case ClazzPackage.META_INFO__CONSTRAINT_TYPE:
				setConstraintType((AttributeType)newValue);
				return;
			case ClazzPackage.META_INFO__VALUE_SET:
				setValueSet(newValue);
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
			case ClazzPackage.META_INFO__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case ClazzPackage.META_INFO__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case ClazzPackage.META_INFO__VALUE_TYPE:
				setValueType((Class<?>)null);
				return;
			case ClazzPackage.META_INFO__CONSTRAINT_TYPE:
				setConstraintType(CONSTRAINT_TYPE_EDEFAULT);
				return;
			case ClazzPackage.META_INFO__VALUE_SET:
				setValueSet(VALUE_SET_EDEFAULT);
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
			case ClazzPackage.META_INFO__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case ClazzPackage.META_INFO__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case ClazzPackage.META_INFO__VALUE_TYPE:
				return valueType != null;
			case ClazzPackage.META_INFO__CONSTRAINT_TYPE:
				return constraintType != CONSTRAINT_TYPE_EDEFAULT;
			case ClazzPackage.META_INFO__VALUE_SET:
				return VALUE_SET_EDEFAULT == null ? valueSet != null : !VALUE_SET_EDEFAULT.equals(valueSet);
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
		result.append(" (key: ");
		result.append(key);
		result.append(", value: ");
		result.append(value);
		result.append(", valueType: ");
		result.append(valueType);
		result.append(", constraintType: ");
		result.append(constraintType);
		result.append(", valueSet: ");
		result.append(valueSet);
		result.append(')');
		return result.toString();
	}

	/**
	 * Clone the meta-information
	 * 
	 * @param mi The meta-information to clone
	 * @_generated
	 */
	public void clone(MetaInfo m) {
		setKey(m.getKey());
		setValue(m.getValue());
		setConstraintType(m.getConstraintType());
		setValueSet(m.getValueSet());
		setValueType(m.getValueType());
	}
	
	/**
	 * @_generated
	 */
	public void setDefaultValueBoolean(boolean b) {
		defaultValueBoolean = b;
	}

	public boolean getDefaultValueBoolean() {
		return defaultValueBoolean;
	}
} //MetaInfoImpl
