/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Meta Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.common.impl.MetaInfoImpl#getKey <em>Key</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.MetaInfoImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.MetaInfoImpl#getValueType <em>Value Type</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.MetaInfoImpl#getConstraintType <em>Constraint Type</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.MetaInfoImpl#getValueSet <em>Value Set</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.MetaInfoImpl#getMultilineValue <em>Multiline Value</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.MetaInfoImpl#getEObjectValue <em>EObject Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetaInfoImpl extends MetaDataImpl implements MetaInfo {
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

	private boolean defaultValueBoolean = false;
	
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
	protected static final DataType CONSTRAINT_TYPE_EDEFAULT = DataType.CUSTOM;

	/**
	 * The cached value of the '{@link #getConstraintType() <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintType()
	 * @generated
	 * @ordered
	 */
	protected DataType constraintType = CONSTRAINT_TYPE_EDEFAULT;

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
	 * The default value of the '{@link #getMultilineValue() <em>Multiline Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultilineValue()
	 * @generated
	 * @ordered
	 */
	protected static final String MULTILINE_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMultilineValue() <em>Multiline Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultilineValue()
	 * @generated
	 * @ordered
	 */
	protected String multilineValue = MULTILINE_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEObjectValue() <em>EObject Value</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEObjectValue()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> eObjectValue;

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
		return CommonPackage.Literals.META_INFO;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.META_INFO__KEY, oldKey, key));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.META_INFO__VALUE, oldValue, value));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.META_INFO__VALUE_TYPE, oldValueType, valueType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getConstraintType() {
		return constraintType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintType(DataType newConstraintType) {
		DataType oldConstraintType = constraintType;
		constraintType = newConstraintType == null ? CONSTRAINT_TYPE_EDEFAULT : newConstraintType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.META_INFO__CONSTRAINT_TYPE, oldConstraintType, constraintType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.META_INFO__VALUE_SET, oldValueSet, valueSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMultilineValue() {
		return multilineValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultilineValue(String newMultilineValue) {
		String oldMultilineValue = multilineValue;
		multilineValue = newMultilineValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.META_INFO__MULTILINE_VALUE, oldMultilineValue, multilineValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getEObjectValue() {
		if (eObjectValue == null) {
			eObjectValue = new EObjectResolvingEList<EObject>(EObject.class, this, CommonPackage.META_INFO__EOBJECT_VALUE);
		}
		return eObjectValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean equalsForMerge(MetaInfo other) {
		if (equalsForMergeBodyOCL == null) {
			EOperation eOperation = CommonPackage.Literals.META_INFO.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(CommonPackage.Literals.META_INFO, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				equalsForMergeBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(equalsForMergeBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("other", other);
	  
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #equalsForMerge <em>Equals For Merge</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #equalsForMerge
	 * @generated
	 */
	private static OCLExpression<EClassifier> equalsForMergeBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.META_INFO__KEY:
				return getKey();
			case CommonPackage.META_INFO__VALUE:
				return getValue();
			case CommonPackage.META_INFO__VALUE_TYPE:
				return getValueType();
			case CommonPackage.META_INFO__CONSTRAINT_TYPE:
				return getConstraintType();
			case CommonPackage.META_INFO__VALUE_SET:
				return getValueSet();
			case CommonPackage.META_INFO__MULTILINE_VALUE:
				return getMultilineValue();
			case CommonPackage.META_INFO__EOBJECT_VALUE:
				return getEObjectValue();
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
			case CommonPackage.META_INFO__KEY:
				setKey((String)newValue);
				return;
			case CommonPackage.META_INFO__VALUE:
				setValue((String)newValue);
				return;
			case CommonPackage.META_INFO__VALUE_TYPE:
				setValueType((Class<?>)newValue);
				return;
			case CommonPackage.META_INFO__CONSTRAINT_TYPE:
				setConstraintType((DataType)newValue);
				return;
			case CommonPackage.META_INFO__VALUE_SET:
				setValueSet(newValue);
				return;
			case CommonPackage.META_INFO__MULTILINE_VALUE:
				setMultilineValue((String)newValue);
				return;
			case CommonPackage.META_INFO__EOBJECT_VALUE:
				getEObjectValue().clear();
				getEObjectValue().addAll((Collection<? extends EObject>)newValue);
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
			case CommonPackage.META_INFO__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case CommonPackage.META_INFO__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case CommonPackage.META_INFO__VALUE_TYPE:
				setValueType((Class<?>)null);
				return;
			case CommonPackage.META_INFO__CONSTRAINT_TYPE:
				setConstraintType(CONSTRAINT_TYPE_EDEFAULT);
				return;
			case CommonPackage.META_INFO__VALUE_SET:
				setValueSet(VALUE_SET_EDEFAULT);
				return;
			case CommonPackage.META_INFO__MULTILINE_VALUE:
				setMultilineValue(MULTILINE_VALUE_EDEFAULT);
				return;
			case CommonPackage.META_INFO__EOBJECT_VALUE:
				getEObjectValue().clear();
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
			case CommonPackage.META_INFO__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case CommonPackage.META_INFO__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case CommonPackage.META_INFO__VALUE_TYPE:
				return valueType != null;
			case CommonPackage.META_INFO__CONSTRAINT_TYPE:
				return constraintType != CONSTRAINT_TYPE_EDEFAULT;
			case CommonPackage.META_INFO__VALUE_SET:
				return VALUE_SET_EDEFAULT == null ? valueSet != null : !VALUE_SET_EDEFAULT.equals(valueSet);
			case CommonPackage.META_INFO__MULTILINE_VALUE:
				return MULTILINE_VALUE_EDEFAULT == null ? multilineValue != null : !MULTILINE_VALUE_EDEFAULT.equals(multilineValue);
			case CommonPackage.META_INFO__EOBJECT_VALUE:
				return eObjectValue != null && !eObjectValue.isEmpty();
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
		result.append(", multilineValue: ");
		result.append(multilineValue);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();
		
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
