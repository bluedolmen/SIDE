/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.application.impl.ConfigurationImpl#getGeneratorConfigurations <em>Generator Configurations</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ConfigurationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ConfigurationImpl#getDeployerConfigurations <em>Deployer Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConfigurationImpl extends ModelElementImpl implements Configuration {
	/**
	 * The cached value of the '{@link #getGeneratorConfigurations() <em>Generator Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratorConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneratorConfiguration> generatorConfigurations;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<ConfigurationParameters> parameters;

	/**
	 * The cached value of the '{@link #getDeployerConfigurations() <em>Deployer Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployerConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<DeployerConfiguration> deployerConfigurations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneratorConfiguration> getGeneratorConfigurations() {
		if (generatorConfigurations == null) {
			generatorConfigurations = new EObjectContainmentEList<GeneratorConfiguration>(GeneratorConfiguration.class, this, ApplicationPackage.CONFIGURATION__GENERATOR_CONFIGURATIONS);
		}
		return generatorConfigurations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConfigurationParameters> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<ConfigurationParameters>(ConfigurationParameters.class, this, ApplicationPackage.CONFIGURATION__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeployerConfiguration> getDeployerConfigurations() {
		if (deployerConfigurations == null) {
			deployerConfigurations = new EObjectContainmentEList<DeployerConfiguration>(DeployerConfiguration.class, this, ApplicationPackage.CONFIGURATION__DEPLOYER_CONFIGURATIONS);
		}
		return deployerConfigurations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.CONFIGURATION__GENERATOR_CONFIGURATIONS:
				return ((InternalEList<?>)getGeneratorConfigurations()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.CONFIGURATION__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.CONFIGURATION__DEPLOYER_CONFIGURATIONS:
				return ((InternalEList<?>)getDeployerConfigurations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.CONFIGURATION__GENERATOR_CONFIGURATIONS:
				return getGeneratorConfigurations();
			case ApplicationPackage.CONFIGURATION__PARAMETERS:
				return getParameters();
			case ApplicationPackage.CONFIGURATION__DEPLOYER_CONFIGURATIONS:
				return getDeployerConfigurations();
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
			case ApplicationPackage.CONFIGURATION__GENERATOR_CONFIGURATIONS:
				getGeneratorConfigurations().clear();
				getGeneratorConfigurations().addAll((Collection<? extends GeneratorConfiguration>)newValue);
				return;
			case ApplicationPackage.CONFIGURATION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends ConfigurationParameters>)newValue);
				return;
			case ApplicationPackage.CONFIGURATION__DEPLOYER_CONFIGURATIONS:
				getDeployerConfigurations().clear();
				getDeployerConfigurations().addAll((Collection<? extends DeployerConfiguration>)newValue);
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
			case ApplicationPackage.CONFIGURATION__GENERATOR_CONFIGURATIONS:
				getGeneratorConfigurations().clear();
				return;
			case ApplicationPackage.CONFIGURATION__PARAMETERS:
				getParameters().clear();
				return;
			case ApplicationPackage.CONFIGURATION__DEPLOYER_CONFIGURATIONS:
				getDeployerConfigurations().clear();
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
			case ApplicationPackage.CONFIGURATION__GENERATOR_CONFIGURATIONS:
				return generatorConfigurations != null && !generatorConfigurations.isEmpty();
			case ApplicationPackage.CONFIGURATION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case ApplicationPackage.CONFIGURATION__DEPLOYER_CONFIGURATIONS:
				return deployerConfigurations != null && !deployerConfigurations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConfigurationImpl
