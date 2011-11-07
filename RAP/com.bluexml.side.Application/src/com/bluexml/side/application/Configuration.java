/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This element contains all data that define a configuration :
 * –	a set of parameters shared and visible by all components (generators, deployers ).
 * –	a set of GeneratorConfiguration
 * –	a set of DeployerConfiguration
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.application.Configuration#getGeneratorConfigurations <em>Generator Configurations</em>}</li>
 *   <li>{@link com.bluexml.side.application.Configuration#getParameters <em>Parameters</em>}</li>
 *   <li>{@link com.bluexml.side.application.Configuration#getDeployerConfigurations <em>Deployer Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.application.ApplicationPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Generator Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.application.GeneratorConfiguration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generator Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generator Configurations</em>' containment reference list.
	 * @see com.bluexml.side.application.ApplicationPackage#getConfiguration_GeneratorConfigurations()
	 * @model containment="true"
	 * @generated
	 */
	EList<GeneratorConfiguration> getGeneratorConfigurations();

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.application.ConfigurationParameters}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see com.bluexml.side.application.ApplicationPackage#getConfiguration_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConfigurationParameters> getParameters();

	/**
	 * Returns the value of the '<em><b>Deployer Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.application.DeployerConfiguration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployer Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployer Configurations</em>' containment reference list.
	 * @see com.bluexml.side.application.ApplicationPackage#getConfiguration_DeployerConfigurations()
	 * @model containment="true"
	 * @generated
	 */
	EList<DeployerConfiguration> getDeployerConfigurations();

} // Configuration
