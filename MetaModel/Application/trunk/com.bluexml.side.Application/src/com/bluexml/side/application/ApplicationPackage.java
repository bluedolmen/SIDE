/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Use to add optionals setting like generation part or clean options.
 * <!-- end-model-doc -->
 * @see com.bluexml.side.application.ApplicationFactory
 * @model kind="package"
 * @generated
 */
public interface ApplicationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "application";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bluexml.com/application/1.0/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "app";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplicationPackage eINSTANCE = com.bluexml.side.application.impl.ApplicationPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.ModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.ModelElementImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.ApplicationImpl <em>Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.ApplicationImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getApplication()
	 * @generated
	 */
	int APPLICATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__NAME = MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__ELEMENTS = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.ModelImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__NAME = MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__FILE = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.ConfigurationImpl <em>Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.ConfigurationImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getConfiguration()
	 * @generated
	 */
	int CONFIGURATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__NAME = MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Generator Configurations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__GENERATOR_CONFIGURATIONS = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__PARAMETERS = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Deployer Configurations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__DEPLOYER_CONFIGURATIONS = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.ComponantConfigurationImpl <em>Componant Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.ComponantConfigurationImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getComponantConfiguration()
	 * @generated
	 */
	int COMPONANT_CONFIGURATION = 8;

	/**
	 * The feature id for the '<em><b>Id techno version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__ID_TECHNO_VERSION = 0;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__OPTIONS = 1;

	/**
	 * The feature id for the '<em><b>Impl class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__IMPL_CLASS = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__ID = 3;

	/**
	 * The feature id for the '<em><b>Meta Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__META_MODEL_NAME = 4;

	/**
	 * The feature id for the '<em><b>Technology Version Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__TECHNOLOGY_VERSION_NAME = 5;

	/**
	 * The feature id for the '<em><b>Technology Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__TECHNOLOGY_NAME = 6;

	/**
	 * The feature id for the '<em><b>Module Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__MODULE_CONTRAINTS = 7;

	/**
	 * The feature id for the '<em><b>Contributor Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION__CONTRIBUTOR_ID = 8;

	/**
	 * The number of structural features of the '<em>Componant Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONANT_CONFIGURATION_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.GeneratorConfigurationImpl <em>Generator Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.GeneratorConfigurationImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getGeneratorConfiguration()
	 * @generated
	 */
	int GENERATOR_CONFIGURATION = 4;

	/**
	 * The feature id for the '<em><b>Id techno version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__ID_TECHNO_VERSION = COMPONANT_CONFIGURATION__ID_TECHNO_VERSION;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__OPTIONS = COMPONANT_CONFIGURATION__OPTIONS;

	/**
	 * The feature id for the '<em><b>Impl class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__IMPL_CLASS = COMPONANT_CONFIGURATION__IMPL_CLASS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__ID = COMPONANT_CONFIGURATION__ID;

	/**
	 * The feature id for the '<em><b>Meta Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__META_MODEL_NAME = COMPONANT_CONFIGURATION__META_MODEL_NAME;

	/**
	 * The feature id for the '<em><b>Technology Version Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__TECHNOLOGY_VERSION_NAME = COMPONANT_CONFIGURATION__TECHNOLOGY_VERSION_NAME;

	/**
	 * The feature id for the '<em><b>Technology Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__TECHNOLOGY_NAME = COMPONANT_CONFIGURATION__TECHNOLOGY_NAME;

	/**
	 * The feature id for the '<em><b>Module Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__MODULE_CONTRAINTS = COMPONANT_CONFIGURATION__MODULE_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Contributor Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__CONTRIBUTOR_ID = COMPONANT_CONFIGURATION__CONTRIBUTOR_ID;

	/**
	 * The feature id for the '<em><b>Id metamodel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__ID_METAMODEL = COMPONANT_CONFIGURATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Generator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION__GENERATOR_NAME = COMPONANT_CONFIGURATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generator Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIGURATION_FEATURE_COUNT = COMPONANT_CONFIGURATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.OptionImpl <em>Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.OptionImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getOption()
	 * @generated
	 */
	int OPTION = 5;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTION__KEY = 0;

	/**
	 * The number of structural features of the '<em>Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTION_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.ConfigurationParametersImpl <em>Configuration Parameters</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.ConfigurationParametersImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getConfigurationParameters()
	 * @generated
	 */
	int CONFIGURATION_PARAMETERS = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETERS__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETERS__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETERS__DATA_TYPE = 2;

	/**
	 * The number of structural features of the '<em>Configuration Parameters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETERS_FEATURE_COUNT = 3;


	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.DeployerConfigurationImpl <em>Deployer Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.DeployerConfigurationImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getDeployerConfiguration()
	 * @generated
	 */
	int DEPLOYER_CONFIGURATION = 7;

	/**
	 * The feature id for the '<em><b>Id techno version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__ID_TECHNO_VERSION = COMPONANT_CONFIGURATION__ID_TECHNO_VERSION;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__OPTIONS = COMPONANT_CONFIGURATION__OPTIONS;

	/**
	 * The feature id for the '<em><b>Impl class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__IMPL_CLASS = COMPONANT_CONFIGURATION__IMPL_CLASS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__ID = COMPONANT_CONFIGURATION__ID;

	/**
	 * The feature id for the '<em><b>Meta Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__META_MODEL_NAME = COMPONANT_CONFIGURATION__META_MODEL_NAME;

	/**
	 * The feature id for the '<em><b>Technology Version Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__TECHNOLOGY_VERSION_NAME = COMPONANT_CONFIGURATION__TECHNOLOGY_VERSION_NAME;

	/**
	 * The feature id for the '<em><b>Technology Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__TECHNOLOGY_NAME = COMPONANT_CONFIGURATION__TECHNOLOGY_NAME;

	/**
	 * The feature id for the '<em><b>Module Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__MODULE_CONTRAINTS = COMPONANT_CONFIGURATION__MODULE_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Contributor Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__CONTRIBUTOR_ID = COMPONANT_CONFIGURATION__CONTRIBUTOR_ID;

	/**
	 * The feature id for the '<em><b>Deployer Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__DEPLOYER_NAME = COMPONANT_CONFIGURATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Shared</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION__SHARED = COMPONANT_CONFIGURATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Deployer Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYER_CONFIGURATION_FEATURE_COUNT = COMPONANT_CONFIGURATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.impl.ModuleConstraintImpl <em>Module Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.impl.ModuleConstraintImpl
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getModuleConstraint()
	 * @generated
	 */
	int MODULE_CONSTRAINT = 9;

	/**
	 * The feature id for the '<em><b>Module Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONSTRAINT__MODULE_ID = 0;

	/**
	 * The feature id for the '<em><b>Version Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONSTRAINT__VERSION_MIN = 1;

	/**
	 * The feature id for the '<em><b>Version Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONSTRAINT__VERSION_MAX = 2;

	/**
	 * The feature id for the '<em><b>Module Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONSTRAINT__MODULE_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Technology Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONSTRAINT__TECHNOLOGY_VERSION = 4;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONSTRAINT__CLASSIFIER = 5;

	/**
	 * The number of structural features of the '<em>Module Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONSTRAINT_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link com.bluexml.side.application.StaticConfigurationParameters <em>Static Configuration Parameters</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.application.StaticConfigurationParameters
	 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getStaticConfigurationParameters()
	 * @generated
	 */
	int STATIC_CONFIGURATION_PARAMETERS = 10;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see com.bluexml.side.application.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ModelElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.application.ModelElement#getName()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ModelElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.bluexml.side.application.ModelElement#getDescription()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Description();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.Application <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application</em>'.
	 * @see com.bluexml.side.application.Application
	 * @generated
	 */
	EClass getApplication();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.application.Application#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see com.bluexml.side.application.Application#getElements()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_Elements();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see com.bluexml.side.application.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.Model#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see com.bluexml.side.application.Model#getFile()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_File();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see com.bluexml.side.application.Configuration
	 * @generated
	 */
	EClass getConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.application.Configuration#getGeneratorConfigurations <em>Generator Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Generator Configurations</em>'.
	 * @see com.bluexml.side.application.Configuration#getGeneratorConfigurations()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_GeneratorConfigurations();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.application.Configuration#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see com.bluexml.side.application.Configuration#getParameters()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Parameters();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.application.Configuration#getDeployerConfigurations <em>Deployer Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deployer Configurations</em>'.
	 * @see com.bluexml.side.application.Configuration#getDeployerConfigurations()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_DeployerConfigurations();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.GeneratorConfiguration <em>Generator Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generator Configuration</em>'.
	 * @see com.bluexml.side.application.GeneratorConfiguration
	 * @generated
	 */
	EClass getGeneratorConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.GeneratorConfiguration#getId_metamodel <em>Id metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id metamodel</em>'.
	 * @see com.bluexml.side.application.GeneratorConfiguration#getId_metamodel()
	 * @see #getGeneratorConfiguration()
	 * @generated
	 */
	EAttribute getGeneratorConfiguration_Id_metamodel();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.GeneratorConfiguration#getGeneratorName <em>Generator Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generator Name</em>'.
	 * @see com.bluexml.side.application.GeneratorConfiguration#getGeneratorName()
	 * @see #getGeneratorConfiguration()
	 * @generated
	 */
	EAttribute getGeneratorConfiguration_GeneratorName();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.Option <em>Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Option</em>'.
	 * @see com.bluexml.side.application.Option
	 * @generated
	 */
	EClass getOption();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.Option#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.bluexml.side.application.Option#getKey()
	 * @see #getOption()
	 * @generated
	 */
	EAttribute getOption_Key();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.ConfigurationParameters <em>Configuration Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration Parameters</em>'.
	 * @see com.bluexml.side.application.ConfigurationParameters
	 * @generated
	 */
	EClass getConfigurationParameters();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ConfigurationParameters#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.bluexml.side.application.ConfigurationParameters#getKey()
	 * @see #getConfigurationParameters()
	 * @generated
	 */
	EAttribute getConfigurationParameters_Key();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ConfigurationParameters#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.application.ConfigurationParameters#getValue()
	 * @see #getConfigurationParameters()
	 * @generated
	 */
	EAttribute getConfigurationParameters_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ConfigurationParameters#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.bluexml.side.application.ConfigurationParameters#getDataType()
	 * @see #getConfigurationParameters()
	 * @generated
	 */
	EAttribute getConfigurationParameters_DataType();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.DeployerConfiguration <em>Deployer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployer Configuration</em>'.
	 * @see com.bluexml.side.application.DeployerConfiguration
	 * @generated
	 */
	EClass getDeployerConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.DeployerConfiguration#getDeployerName <em>Deployer Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deployer Name</em>'.
	 * @see com.bluexml.side.application.DeployerConfiguration#getDeployerName()
	 * @see #getDeployerConfiguration()
	 * @generated
	 */
	EAttribute getDeployerConfiguration_DeployerName();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.DeployerConfiguration#isShared <em>Shared</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shared</em>'.
	 * @see com.bluexml.side.application.DeployerConfiguration#isShared()
	 * @see #getDeployerConfiguration()
	 * @generated
	 */
	EAttribute getDeployerConfiguration_Shared();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.ComponantConfiguration <em>Componant Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Componant Configuration</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration
	 * @generated
	 */
	EClass getComponantConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ComponantConfiguration#getId_techno_version <em>Id techno version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id techno version</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getId_techno_version()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EAttribute getComponantConfiguration_Id_techno_version();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.application.ComponantConfiguration#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getOptions()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EReference getComponantConfiguration_Options();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ComponantConfiguration#getImpl_class <em>Impl class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Impl class</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getImpl_class()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EAttribute getComponantConfiguration_Impl_class();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ComponantConfiguration#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getId()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EAttribute getComponantConfiguration_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ComponantConfiguration#getMetaModelName <em>Meta Model Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Meta Model Name</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getMetaModelName()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EAttribute getComponantConfiguration_MetaModelName();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ComponantConfiguration#getTechnologyVersionName <em>Technology Version Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Technology Version Name</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getTechnologyVersionName()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EAttribute getComponantConfiguration_TechnologyVersionName();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ComponantConfiguration#getTechnologyName <em>Technology Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Technology Name</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getTechnologyName()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EAttribute getComponantConfiguration_TechnologyName();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.application.ComponantConfiguration#getModuleContraints <em>Module Contraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Module Contraints</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getModuleContraints()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EReference getComponantConfiguration_ModuleContraints();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ComponantConfiguration#getContributorId <em>Contributor Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contributor Id</em>'.
	 * @see com.bluexml.side.application.ComponantConfiguration#getContributorId()
	 * @see #getComponantConfiguration()
	 * @generated
	 */
	EAttribute getComponantConfiguration_ContributorId();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.application.ModuleConstraint <em>Module Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Constraint</em>'.
	 * @see com.bluexml.side.application.ModuleConstraint
	 * @generated
	 */
	EClass getModuleConstraint();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ModuleConstraint#getModuleId <em>Module Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Module Id</em>'.
	 * @see com.bluexml.side.application.ModuleConstraint#getModuleId()
	 * @see #getModuleConstraint()
	 * @generated
	 */
	EAttribute getModuleConstraint_ModuleId();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ModuleConstraint#getVersionMin <em>Version Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version Min</em>'.
	 * @see com.bluexml.side.application.ModuleConstraint#getVersionMin()
	 * @see #getModuleConstraint()
	 * @generated
	 */
	EAttribute getModuleConstraint_VersionMin();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ModuleConstraint#getVersionMax <em>Version Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version Max</em>'.
	 * @see com.bluexml.side.application.ModuleConstraint#getVersionMax()
	 * @see #getModuleConstraint()
	 * @generated
	 */
	EAttribute getModuleConstraint_VersionMax();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ModuleConstraint#getModuleType <em>Module Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Module Type</em>'.
	 * @see com.bluexml.side.application.ModuleConstraint#getModuleType()
	 * @see #getModuleConstraint()
	 * @generated
	 */
	EAttribute getModuleConstraint_ModuleType();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ModuleConstraint#getTechnologyVersion <em>Technology Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Technology Version</em>'.
	 * @see com.bluexml.side.application.ModuleConstraint#getTechnologyVersion()
	 * @see #getModuleConstraint()
	 * @generated
	 */
	EAttribute getModuleConstraint_TechnologyVersion();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.application.ModuleConstraint#getClassifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Classifier</em>'.
	 * @see com.bluexml.side.application.ModuleConstraint#getClassifier()
	 * @see #getModuleConstraint()
	 * @generated
	 */
	EAttribute getModuleConstraint_Classifier();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.application.StaticConfigurationParameters <em>Static Configuration Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Static Configuration Parameters</em>'.
	 * @see com.bluexml.side.application.StaticConfigurationParameters
	 * @generated
	 */
	EEnum getStaticConfigurationParameters();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ApplicationFactory getApplicationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.ModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.ModelElementImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__NAME = eINSTANCE.getModelElement_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__DESCRIPTION = eINSTANCE.getModelElement_Description();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.ApplicationImpl <em>Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.ApplicationImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getApplication()
		 * @generated
		 */
		EClass APPLICATION = eINSTANCE.getApplication();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__ELEMENTS = eINSTANCE.getApplication_Elements();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.ModelImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL__FILE = eINSTANCE.getModel_File();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.ConfigurationImpl <em>Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.ConfigurationImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getConfiguration()
		 * @generated
		 */
		EClass CONFIGURATION = eINSTANCE.getConfiguration();

		/**
		 * The meta object literal for the '<em><b>Generator Configurations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__GENERATOR_CONFIGURATIONS = eINSTANCE.getConfiguration_GeneratorConfigurations();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__PARAMETERS = eINSTANCE.getConfiguration_Parameters();

		/**
		 * The meta object literal for the '<em><b>Deployer Configurations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__DEPLOYER_CONFIGURATIONS = eINSTANCE.getConfiguration_DeployerConfigurations();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.GeneratorConfigurationImpl <em>Generator Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.GeneratorConfigurationImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getGeneratorConfiguration()
		 * @generated
		 */
		EClass GENERATOR_CONFIGURATION = eINSTANCE.getGeneratorConfiguration();

		/**
		 * The meta object literal for the '<em><b>Id metamodel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_CONFIGURATION__ID_METAMODEL = eINSTANCE.getGeneratorConfiguration_Id_metamodel();

		/**
		 * The meta object literal for the '<em><b>Generator Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_CONFIGURATION__GENERATOR_NAME = eINSTANCE.getGeneratorConfiguration_GeneratorName();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.OptionImpl <em>Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.OptionImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getOption()
		 * @generated
		 */
		EClass OPTION = eINSTANCE.getOption();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPTION__KEY = eINSTANCE.getOption_Key();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.ConfigurationParametersImpl <em>Configuration Parameters</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.ConfigurationParametersImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getConfigurationParameters()
		 * @generated
		 */
		EClass CONFIGURATION_PARAMETERS = eINSTANCE.getConfigurationParameters();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION_PARAMETERS__KEY = eINSTANCE.getConfigurationParameters_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION_PARAMETERS__VALUE = eINSTANCE.getConfigurationParameters_Value();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION_PARAMETERS__DATA_TYPE = eINSTANCE.getConfigurationParameters_DataType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.DeployerConfigurationImpl <em>Deployer Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.DeployerConfigurationImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getDeployerConfiguration()
		 * @generated
		 */
		EClass DEPLOYER_CONFIGURATION = eINSTANCE.getDeployerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Deployer Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYER_CONFIGURATION__DEPLOYER_NAME = eINSTANCE.getDeployerConfiguration_DeployerName();

		/**
		 * The meta object literal for the '<em><b>Shared</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYER_CONFIGURATION__SHARED = eINSTANCE.getDeployerConfiguration_Shared();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.ComponantConfigurationImpl <em>Componant Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.ComponantConfigurationImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getComponantConfiguration()
		 * @generated
		 */
		EClass COMPONANT_CONFIGURATION = eINSTANCE.getComponantConfiguration();

		/**
		 * The meta object literal for the '<em><b>Id techno version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONANT_CONFIGURATION__ID_TECHNO_VERSION = eINSTANCE.getComponantConfiguration_Id_techno_version();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONANT_CONFIGURATION__OPTIONS = eINSTANCE.getComponantConfiguration_Options();

		/**
		 * The meta object literal for the '<em><b>Impl class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONANT_CONFIGURATION__IMPL_CLASS = eINSTANCE.getComponantConfiguration_Impl_class();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONANT_CONFIGURATION__ID = eINSTANCE.getComponantConfiguration_Id();

		/**
		 * The meta object literal for the '<em><b>Meta Model Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONANT_CONFIGURATION__META_MODEL_NAME = eINSTANCE.getComponantConfiguration_MetaModelName();

		/**
		 * The meta object literal for the '<em><b>Technology Version Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONANT_CONFIGURATION__TECHNOLOGY_VERSION_NAME = eINSTANCE.getComponantConfiguration_TechnologyVersionName();

		/**
		 * The meta object literal for the '<em><b>Technology Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONANT_CONFIGURATION__TECHNOLOGY_NAME = eINSTANCE.getComponantConfiguration_TechnologyName();

		/**
		 * The meta object literal for the '<em><b>Module Contraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONANT_CONFIGURATION__MODULE_CONTRAINTS = eINSTANCE.getComponantConfiguration_ModuleContraints();

		/**
		 * The meta object literal for the '<em><b>Contributor Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONANT_CONFIGURATION__CONTRIBUTOR_ID = eINSTANCE.getComponantConfiguration_ContributorId();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.impl.ModuleConstraintImpl <em>Module Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.impl.ModuleConstraintImpl
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getModuleConstraint()
		 * @generated
		 */
		EClass MODULE_CONSTRAINT = eINSTANCE.getModuleConstraint();

		/**
		 * The meta object literal for the '<em><b>Module Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_CONSTRAINT__MODULE_ID = eINSTANCE.getModuleConstraint_ModuleId();

		/**
		 * The meta object literal for the '<em><b>Version Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_CONSTRAINT__VERSION_MIN = eINSTANCE.getModuleConstraint_VersionMin();

		/**
		 * The meta object literal for the '<em><b>Version Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_CONSTRAINT__VERSION_MAX = eINSTANCE.getModuleConstraint_VersionMax();

		/**
		 * The meta object literal for the '<em><b>Module Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_CONSTRAINT__MODULE_TYPE = eINSTANCE.getModuleConstraint_ModuleType();

		/**
		 * The meta object literal for the '<em><b>Technology Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_CONSTRAINT__TECHNOLOGY_VERSION = eINSTANCE.getModuleConstraint_TechnologyVersion();

		/**
		 * The meta object literal for the '<em><b>Classifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_CONSTRAINT__CLASSIFIER = eINSTANCE.getModuleConstraint_Classifier();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.application.StaticConfigurationParameters <em>Static Configuration Parameters</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.application.StaticConfigurationParameters
		 * @see com.bluexml.side.application.impl.ApplicationPackageImpl#getStaticConfigurationParameters()
		 * @generated
		 */
		EEnum STATIC_CONFIGURATION_PARAMETERS = eINSTANCE.getStaticConfigurationParameters();

	}

} //ApplicationPackage
