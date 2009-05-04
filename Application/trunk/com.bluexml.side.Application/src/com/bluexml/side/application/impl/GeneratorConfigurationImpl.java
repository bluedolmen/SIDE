/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application.impl;

import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.GeneratorConfiguration;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generator Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.application.impl.GeneratorConfigurationImpl#getId_metamodel <em>Id metamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GeneratorConfigurationImpl extends ComponantConfigurationImpl implements GeneratorConfiguration {
	/**
	 * The default value of the '{@link #getId_metamodel() <em>Id metamodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_metamodel()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_METAMODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId_metamodel() <em>Id metamodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_metamodel()
	 * @generated
	 * @ordered
	 */
	protected String id_metamodel = ID_METAMODEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeneratorConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.GENERATOR_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId_metamodel() {
		return id_metamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId_metamodel(String newId_metamodel) {
		String oldId_metamodel = id_metamodel;
		id_metamodel = newId_metamodel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL, oldId_metamodel, id_metamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL:
				return getId_metamodel();
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
			case ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL:
				setId_metamodel((String)newValue);
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
			case ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL:
				setId_metamodel(ID_METAMODEL_EDEFAULT);
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
			case ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL:
				return ID_METAMODEL_EDEFAULT == null ? id_metamodel != null : !ID_METAMODEL_EDEFAULT.equals(id_metamodel);
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
		result.append(" (id_metamodel: ");
		result.append(id_metamodel);
		result.append(')');
		return result.toString();
	}

} //GeneratorConfigurationImpl
