/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common.impl;

import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.CustomDataType;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.common.impl.CustomDataTypeImpl#getDataTypeImp <em>Data Type Imp</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomDataTypeImpl extends NamedModelElementImpl implements CustomDataType {
	/**
	 * The default value of the '{@link #getDataTypeImp() <em>Data Type Imp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTypeImp()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TYPE_IMP_EDEFAULT = "java.lang.String";

	/**
	 * The cached value of the '{@link #getDataTypeImp() <em>Data Type Imp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTypeImp()
	 * @generated
	 * @ordered
	 */
	protected String dataTypeImp = DATA_TYPE_IMP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.CUSTOM_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataTypeImp() {
		return dataTypeImp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataTypeImp(String newDataTypeImp) {
		String oldDataTypeImp = dataTypeImp;
		dataTypeImp = newDataTypeImp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CUSTOM_DATA_TYPE__DATA_TYPE_IMP, oldDataTypeImp, dataTypeImp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.CUSTOM_DATA_TYPE__DATA_TYPE_IMP:
				return getDataTypeImp();
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
			case CommonPackage.CUSTOM_DATA_TYPE__DATA_TYPE_IMP:
				setDataTypeImp((String)newValue);
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
			case CommonPackage.CUSTOM_DATA_TYPE__DATA_TYPE_IMP:
				setDataTypeImp(DATA_TYPE_IMP_EDEFAULT);
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
			case CommonPackage.CUSTOM_DATA_TYPE__DATA_TYPE_IMP:
				return DATA_TYPE_IMP_EDEFAULT == null ? dataTypeImp != null : !DATA_TYPE_IMP_EDEFAULT.equals(dataTypeImp);
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
		result.append(" (dataTypeImp: ");
		result.append(dataTypeImp);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //CustomDataTypeImpl
