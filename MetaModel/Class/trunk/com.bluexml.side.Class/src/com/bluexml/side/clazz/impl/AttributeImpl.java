/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.common.CustomDataType;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.Visibility;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.AttributeImpl#getTyp <em>Typ</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AttributeImpl#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AttributeImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AttributeImpl#getValueList <em>Value List</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AttributeImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AttributeImpl#getMockup <em>Mockup</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AttributeImpl#getCustomType <em>Custom Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeImpl extends TitledNamedClassModelElementImpl implements Attribute {
	/**
	 * The default value of the '{@link #getTyp() <em>Typ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTyp()
	 * @generated
	 * @ordered
	 */
	protected static final DataType TYP_EDEFAULT = DataType.STRING;

	/**
	 * The cached value of the '{@link #getTyp() <em>Typ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTyp()
	 * @generated
	 * @ordered
	 */
	protected DataType typ = TYP_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitialValue() <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialValue()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIAL_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitialValue() <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialValue()
	 * @generated
	 * @ordered
	 */
	protected String initialValue = INITIAL_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final Visibility VISIBILITY_EDEFAULT = Visibility.PRIVATE;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected Visibility visibility = VISIBILITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getValueList() <em>Value List</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueList()
	 * @generated
	 * @ordered
	 */
	protected Enumeration valueList;

	/**
	 * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean unique = UNIQUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMockup() <em>Mockup</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMockup()
	 * @generated
	 * @ordered
	 */
	protected EList<String> mockup;

	/**
	 * The cached value of the '{@link #getCustomType() <em>Custom Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomType()
	 * @generated
	 * @ordered
	 */
	protected CustomDataType customType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.ATTRIBUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getTyp() {
		return typ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTyp(DataType newTyp) {
		DataType oldTyp = typ;
		typ = newTyp == null ? TYP_EDEFAULT : newTyp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ATTRIBUTE__TYP, oldTyp, typ));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInitialValue() {
		return initialValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialValue(String newInitialValue) {
		String oldInitialValue = initialValue;
		initialValue = newInitialValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ATTRIBUTE__INITIAL_VALUE, oldInitialValue, initialValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(Visibility newVisibility) {
		Visibility oldVisibility = visibility;
		visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ATTRIBUTE__VISIBILITY, oldVisibility, visibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration getValueList() {
		if (valueList != null && valueList.eIsProxy()) {
			InternalEObject oldValueList = (InternalEObject)valueList;
			valueList = (Enumeration)eResolveProxy(oldValueList);
			if (valueList != oldValueList) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.ATTRIBUTE__VALUE_LIST, oldValueList, valueList));
			}
		}
		return valueList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration basicGetValueList() {
		return valueList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueList(Enumeration newValueList) {
		Enumeration oldValueList = valueList;
		valueList = newValueList;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ATTRIBUTE__VALUE_LIST, oldValueList, valueList));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnique(boolean newUnique) {
		boolean oldUnique = unique;
		unique = newUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ATTRIBUTE__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMockup() {
		if (mockup == null) {
			mockup = new EDataTypeUniqueEList<String>(String.class, this, ClazzPackage.ATTRIBUTE__MOCKUP);
		}
		return mockup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomDataType getCustomType() {
		if (customType != null && customType.eIsProxy()) {
			InternalEObject oldCustomType = (InternalEObject)customType;
			customType = (CustomDataType)eResolveProxy(oldCustomType);
			if (customType != oldCustomType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.ATTRIBUTE__CUSTOM_TYPE, oldCustomType, customType));
			}
		}
		return customType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomDataType basicGetCustomType() {
		return customType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustomType(CustomDataType newCustomType) {
		CustomDataType oldCustomType = customType;
		customType = newCustomType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ATTRIBUTE__CUSTOM_TYPE, oldCustomType, customType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClazzPackage.ATTRIBUTE__TYP:
				return getTyp();
			case ClazzPackage.ATTRIBUTE__INITIAL_VALUE:
				return getInitialValue();
			case ClazzPackage.ATTRIBUTE__VISIBILITY:
				return getVisibility();
			case ClazzPackage.ATTRIBUTE__VALUE_LIST:
				if (resolve) return getValueList();
				return basicGetValueList();
			case ClazzPackage.ATTRIBUTE__UNIQUE:
				return isUnique();
			case ClazzPackage.ATTRIBUTE__MOCKUP:
				return getMockup();
			case ClazzPackage.ATTRIBUTE__CUSTOM_TYPE:
				if (resolve) return getCustomType();
				return basicGetCustomType();
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
			case ClazzPackage.ATTRIBUTE__TYP:
				setTyp((DataType)newValue);
				return;
			case ClazzPackage.ATTRIBUTE__INITIAL_VALUE:
				setInitialValue((String)newValue);
				return;
			case ClazzPackage.ATTRIBUTE__VISIBILITY:
				setVisibility((Visibility)newValue);
				return;
			case ClazzPackage.ATTRIBUTE__VALUE_LIST:
				setValueList((Enumeration)newValue);
				return;
			case ClazzPackage.ATTRIBUTE__UNIQUE:
				setUnique((Boolean)newValue);
				return;
			case ClazzPackage.ATTRIBUTE__MOCKUP:
				getMockup().clear();
				getMockup().addAll((Collection<? extends String>)newValue);
				return;
			case ClazzPackage.ATTRIBUTE__CUSTOM_TYPE:
				setCustomType((CustomDataType)newValue);
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
			case ClazzPackage.ATTRIBUTE__TYP:
				setTyp(TYP_EDEFAULT);
				return;
			case ClazzPackage.ATTRIBUTE__INITIAL_VALUE:
				setInitialValue(INITIAL_VALUE_EDEFAULT);
				return;
			case ClazzPackage.ATTRIBUTE__VISIBILITY:
				setVisibility(VISIBILITY_EDEFAULT);
				return;
			case ClazzPackage.ATTRIBUTE__VALUE_LIST:
				setValueList((Enumeration)null);
				return;
			case ClazzPackage.ATTRIBUTE__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case ClazzPackage.ATTRIBUTE__MOCKUP:
				getMockup().clear();
				return;
			case ClazzPackage.ATTRIBUTE__CUSTOM_TYPE:
				setCustomType((CustomDataType)null);
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
			case ClazzPackage.ATTRIBUTE__TYP:
				return typ != TYP_EDEFAULT;
			case ClazzPackage.ATTRIBUTE__INITIAL_VALUE:
				return INITIAL_VALUE_EDEFAULT == null ? initialValue != null : !INITIAL_VALUE_EDEFAULT.equals(initialValue);
			case ClazzPackage.ATTRIBUTE__VISIBILITY:
				return visibility != VISIBILITY_EDEFAULT;
			case ClazzPackage.ATTRIBUTE__VALUE_LIST:
				return valueList != null;
			case ClazzPackage.ATTRIBUTE__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case ClazzPackage.ATTRIBUTE__MOCKUP:
				return mockup != null && !mockup.isEmpty();
			case ClazzPackage.ATTRIBUTE__CUSTOM_TYPE:
				return customType != null;
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
		result.append(" (typ: ");
		result.append(typ);
		result.append(", initialValue: ");
		result.append(initialValue);
		result.append(", visibility: ");
		result.append(visibility);
		result.append(", unique: ");
		result.append(unique);
		result.append(", mockup: ");
		result.append(mockup);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //AttributeImpl
