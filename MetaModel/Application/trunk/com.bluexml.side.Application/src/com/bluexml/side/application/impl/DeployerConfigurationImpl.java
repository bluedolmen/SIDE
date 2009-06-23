/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.DeployerConfiguration;
import org.eclipse.emf.common.notify.Notification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployer Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.application.impl.DeployerConfigurationImpl#getDeployerName <em>Deployer Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeployerConfigurationImpl extends ComponantConfigurationImpl implements DeployerConfiguration {
	/**
	 * The default value of the '{@link #getDeployerName() <em>Deployer Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployerName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEPLOYER_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getDeployerName() <em>Deployer Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployerName()
	 * @generated
	 * @ordered
	 */
	protected String deployerName = DEPLOYER_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeployerConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.DEPLOYER_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeployerName() {
		return deployerName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeployerName(String newDeployerName) {
		String oldDeployerName = deployerName;
		deployerName = newDeployerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.DEPLOYER_CONFIGURATION__DEPLOYER_NAME, oldDeployerName, deployerName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.DEPLOYER_CONFIGURATION__DEPLOYER_NAME:
				return getDeployerName();
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
			case ApplicationPackage.DEPLOYER_CONFIGURATION__DEPLOYER_NAME:
				setDeployerName((String)newValue);
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
			case ApplicationPackage.DEPLOYER_CONFIGURATION__DEPLOYER_NAME:
				setDeployerName(DEPLOYER_NAME_EDEFAULT);
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
			case ApplicationPackage.DEPLOYER_CONFIGURATION__DEPLOYER_NAME:
				return DEPLOYER_NAME_EDEFAULT == null ? deployerName != null : !DEPLOYER_NAME_EDEFAULT.equals(deployerName);
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
		result.append(" (deployerName: ");
		result.append(deployerName);
		result.append(')');
		return result.toString();
	}

} //DeployerConfigurationImpl
