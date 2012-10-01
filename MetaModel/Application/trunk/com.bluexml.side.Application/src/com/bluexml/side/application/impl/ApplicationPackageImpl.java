/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.ModuleConstraint;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.StaticConfigurationParameters;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ApplicationPackageImpl extends EPackageImpl implements ApplicationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generatorConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass optionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationParametersEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployerConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componantConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum staticConfigurationParametersEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.bluexml.side.application.ApplicationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ApplicationPackageImpl() {
		super(eNS_URI, ApplicationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ApplicationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ApplicationPackage init() {
		if (isInited) return (ApplicationPackage)EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);

		// Obtain or create and register package
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ApplicationPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theApplicationPackage.createPackageContents();

		// Initialize created meta-data
		theApplicationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theApplicationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ApplicationPackage.eNS_URI, theApplicationPackage);
		return theApplicationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelElement() {
		return modelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElement_Name() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElement_Description() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplication() {
		return applicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplication_Elements() {
		return (EReference)applicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModel_File() {
		return (EAttribute)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfiguration() {
		return configurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguration_GeneratorConfigurations() {
		return (EReference)configurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguration_Parameters() {
		return (EReference)configurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguration_DeployerConfigurations() {
		return (EReference)configurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneratorConfiguration() {
		return generatorConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratorConfiguration_Id_metamodel() {
		return (EAttribute)generatorConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratorConfiguration_GeneratorName() {
		return (EAttribute)generatorConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOption() {
		return optionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOption_Key() {
		return (EAttribute)optionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfigurationParameters() {
		return configurationParametersEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigurationParameters_Key() {
		return (EAttribute)configurationParametersEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigurationParameters_Value() {
		return (EAttribute)configurationParametersEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigurationParameters_DataType() {
		return (EAttribute)configurationParametersEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployerConfiguration() {
		return deployerConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeployerConfiguration_DeployerName() {
		return (EAttribute)deployerConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeployerConfiguration_Shared() {
		return (EAttribute)deployerConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponantConfiguration() {
		return componantConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponantConfiguration_Id_techno_version() {
		return (EAttribute)componantConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponantConfiguration_Options() {
		return (EReference)componantConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponantConfiguration_Impl_class() {
		return (EAttribute)componantConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponantConfiguration_Id() {
		return (EAttribute)componantConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponantConfiguration_MetaModelName() {
		return (EAttribute)componantConfigurationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponantConfiguration_TechnologyVersionName() {
		return (EAttribute)componantConfigurationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponantConfiguration_TechnologyName() {
		return (EAttribute)componantConfigurationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponantConfiguration_ModuleContraints() {
		return (EReference)componantConfigurationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponantConfiguration_ContributorId() {
		return (EAttribute)componantConfigurationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModuleConstraint() {
		return moduleConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleConstraint_ModuleId() {
		return (EAttribute)moduleConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleConstraint_VersionMin() {
		return (EAttribute)moduleConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleConstraint_VersionMax() {
		return (EAttribute)moduleConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleConstraint_ModuleType() {
		return (EAttribute)moduleConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleConstraint_TechnologyVersion() {
		return (EAttribute)moduleConstraintEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleConstraint_Classifier() {
		return (EAttribute)moduleConstraintEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStaticConfigurationParameters() {
		return staticConfigurationParametersEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationFactory getApplicationFactory() {
		return (ApplicationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		modelElementEClass = createEClass(MODEL_ELEMENT);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__NAME);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__DESCRIPTION);

		applicationEClass = createEClass(APPLICATION);
		createEReference(applicationEClass, APPLICATION__ELEMENTS);

		modelEClass = createEClass(MODEL);
		createEAttribute(modelEClass, MODEL__FILE);

		configurationEClass = createEClass(CONFIGURATION);
		createEReference(configurationEClass, CONFIGURATION__GENERATOR_CONFIGURATIONS);
		createEReference(configurationEClass, CONFIGURATION__PARAMETERS);
		createEReference(configurationEClass, CONFIGURATION__DEPLOYER_CONFIGURATIONS);

		generatorConfigurationEClass = createEClass(GENERATOR_CONFIGURATION);
		createEAttribute(generatorConfigurationEClass, GENERATOR_CONFIGURATION__ID_METAMODEL);
		createEAttribute(generatorConfigurationEClass, GENERATOR_CONFIGURATION__GENERATOR_NAME);

		optionEClass = createEClass(OPTION);
		createEAttribute(optionEClass, OPTION__KEY);

		configurationParametersEClass = createEClass(CONFIGURATION_PARAMETERS);
		createEAttribute(configurationParametersEClass, CONFIGURATION_PARAMETERS__KEY);
		createEAttribute(configurationParametersEClass, CONFIGURATION_PARAMETERS__VALUE);
		createEAttribute(configurationParametersEClass, CONFIGURATION_PARAMETERS__DATA_TYPE);

		deployerConfigurationEClass = createEClass(DEPLOYER_CONFIGURATION);
		createEAttribute(deployerConfigurationEClass, DEPLOYER_CONFIGURATION__DEPLOYER_NAME);
		createEAttribute(deployerConfigurationEClass, DEPLOYER_CONFIGURATION__SHARED);

		componantConfigurationEClass = createEClass(COMPONANT_CONFIGURATION);
		createEAttribute(componantConfigurationEClass, COMPONANT_CONFIGURATION__ID_TECHNO_VERSION);
		createEReference(componantConfigurationEClass, COMPONANT_CONFIGURATION__OPTIONS);
		createEAttribute(componantConfigurationEClass, COMPONANT_CONFIGURATION__IMPL_CLASS);
		createEAttribute(componantConfigurationEClass, COMPONANT_CONFIGURATION__ID);
		createEAttribute(componantConfigurationEClass, COMPONANT_CONFIGURATION__META_MODEL_NAME);
		createEAttribute(componantConfigurationEClass, COMPONANT_CONFIGURATION__TECHNOLOGY_VERSION_NAME);
		createEAttribute(componantConfigurationEClass, COMPONANT_CONFIGURATION__TECHNOLOGY_NAME);
		createEReference(componantConfigurationEClass, COMPONANT_CONFIGURATION__MODULE_CONTRAINTS);
		createEAttribute(componantConfigurationEClass, COMPONANT_CONFIGURATION__CONTRIBUTOR_ID);

		moduleConstraintEClass = createEClass(MODULE_CONSTRAINT);
		createEAttribute(moduleConstraintEClass, MODULE_CONSTRAINT__MODULE_ID);
		createEAttribute(moduleConstraintEClass, MODULE_CONSTRAINT__VERSION_MIN);
		createEAttribute(moduleConstraintEClass, MODULE_CONSTRAINT__VERSION_MAX);
		createEAttribute(moduleConstraintEClass, MODULE_CONSTRAINT__MODULE_TYPE);
		createEAttribute(moduleConstraintEClass, MODULE_CONSTRAINT__TECHNOLOGY_VERSION);
		createEAttribute(moduleConstraintEClass, MODULE_CONSTRAINT__CLASSIFIER);

		// Create enums
		staticConfigurationParametersEEnum = createEEnum(STATIC_CONFIGURATION_PARAMETERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		applicationEClass.getESuperTypes().add(this.getModelElement());
		modelEClass.getESuperTypes().add(this.getModelElement());
		configurationEClass.getESuperTypes().add(this.getModelElement());
		generatorConfigurationEClass.getESuperTypes().add(this.getComponantConfiguration());
		deployerConfigurationEClass.getESuperTypes().add(this.getComponantConfiguration());

		// Initialize classes and features; add operations and parameters
		initEClass(modelElementEClass, ModelElement.class, "ModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationEClass, Application.class, "Application", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplication_Elements(), this.getModelElement(), null, "elements", null, 1, -1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModel_File(), ecorePackage.getEString(), "file", null, 1, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configurationEClass, Configuration.class, "Configuration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConfiguration_GeneratorConfigurations(), this.getGeneratorConfiguration(), null, "generatorConfigurations", null, 0, -1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfiguration_Parameters(), this.getConfigurationParameters(), null, "parameters", null, 0, -1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfiguration_DeployerConfigurations(), this.getDeployerConfiguration(), null, "deployerConfigurations", null, 0, -1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generatorConfigurationEClass, GeneratorConfiguration.class, "GeneratorConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeneratorConfiguration_Id_metamodel(), ecorePackage.getEString(), "id_metamodel", null, 0, 1, GeneratorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorConfiguration_GeneratorName(), ecorePackage.getEString(), "generatorName", null, 0, 1, GeneratorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(optionEClass, Option.class, "Option", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOption_Key(), ecorePackage.getEString(), "key", null, 0, 1, Option.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configurationParametersEClass, ConfigurationParameters.class, "ConfigurationParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfigurationParameters_Key(), ecorePackage.getEString(), "key", null, 0, 1, ConfigurationParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfigurationParameters_Value(), ecorePackage.getEString(), "value", null, 0, 1, ConfigurationParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfigurationParameters_DataType(), ecorePackage.getEString(), "dataType", null, 0, 1, ConfigurationParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deployerConfigurationEClass, DeployerConfiguration.class, "DeployerConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeployerConfiguration_DeployerName(), ecorePackage.getEString(), "deployerName", null, 0, 1, DeployerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeployerConfiguration_Shared(), ecorePackage.getEBoolean(), "shared", "false", 0, 1, DeployerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componantConfigurationEClass, ComponantConfiguration.class, "ComponantConfiguration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponantConfiguration_Id_techno_version(), ecorePackage.getEString(), "id_techno_version", null, 0, 1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponantConfiguration_Options(), this.getOption(), null, "options", null, 0, -1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponantConfiguration_Impl_class(), ecorePackage.getEString(), "impl_class", null, 0, 1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponantConfiguration_Id(), ecorePackage.getEString(), "id", null, 0, 1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponantConfiguration_MetaModelName(), ecorePackage.getEString(), "metaModelName", null, 0, 1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponantConfiguration_TechnologyVersionName(), ecorePackage.getEString(), "technologyVersionName", null, 0, 1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponantConfiguration_TechnologyName(), ecorePackage.getEString(), "technologyName", null, 0, 1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponantConfiguration_ModuleContraints(), this.getModuleConstraint(), null, "moduleContraints", null, 0, -1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponantConfiguration_ContributorId(), ecorePackage.getEString(), "contributorId", null, 0, 1, ComponantConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleConstraintEClass, ModuleConstraint.class, "ModuleConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModuleConstraint_ModuleId(), ecorePackage.getEString(), "moduleId", null, 1, 1, ModuleConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModuleConstraint_VersionMin(), ecorePackage.getEString(), "versionMin", null, 0, 1, ModuleConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModuleConstraint_VersionMax(), ecorePackage.getEString(), "versionMax", null, 0, 1, ModuleConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModuleConstraint_ModuleType(), ecorePackage.getEString(), "moduleType", null, 1, 1, ModuleConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModuleConstraint_TechnologyVersion(), ecorePackage.getEString(), "technologyVersion", null, 1, 1, ModuleConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModuleConstraint_Classifier(), ecorePackage.getEString(), "classifier", null, 0, 1, ModuleConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(staticConfigurationParametersEEnum, StaticConfigurationParameters.class, "StaticConfigurationParameters");
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATIONOPTIONSCLEAN);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATIONOPTIONSDOCUMENTATION);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.UPDATE_DEPENDENCIES);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.FM_DEV);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATIONOPTION_SKIP_VALIDATION);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATION_OPTION_OFFLINE_MODE);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATION_OPTION_GENERATE);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATION_OPTION_COMPLETE);
		addEEnumLiteral(staticConfigurationParametersEEnum, StaticConfigurationParameters.GENERATION_OPTION_RESOLVE_DEPENDENCIES);

		// Create resource
		createResource(eNS_URI);
	}

} //ApplicationPackageImpl
