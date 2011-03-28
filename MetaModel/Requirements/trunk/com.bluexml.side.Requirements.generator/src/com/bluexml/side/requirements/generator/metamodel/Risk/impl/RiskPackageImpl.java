/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Risk.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.bluexml.side.requirements.generator.metamodel.Risk.Diagnostic;
import com.bluexml.side.requirements.generator.metamodel.Risk.Estimation;
import com.bluexml.side.requirements.generator.metamodel.Risk.RiskFactory;
import com.bluexml.side.requirements.generator.metamodel.Risk.RiskPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RiskPackageImpl extends EPackageImpl implements RiskPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass estimationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagnosticEClass = null;

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
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.RiskPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RiskPackageImpl() {
		super(eNS_URI, RiskFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RiskPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RiskPackage init() {
		if (isInited) return (RiskPackage)EPackage.Registry.INSTANCE.getEPackage(RiskPackage.eNS_URI);

		// Obtain or create and register package
		RiskPackageImpl theRiskPackage = (RiskPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RiskPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RiskPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theRiskPackage.createPackageContents();

		// Initialize created meta-data
		theRiskPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRiskPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RiskPackage.eNS_URI, theRiskPackage);
		return theRiskPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEstimation() {
		return estimationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEstimation_Value() {
		return (EAttribute)estimationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEstimation_ElementType() {
		return (EAttribute)estimationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEstimation_ElementName() {
		return (EAttribute)estimationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagnostic() {
		return diagnosticEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagnostic_Estimation() {
		return (EReference)diagnosticEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RiskFactory getRiskFactory() {
		return (RiskFactory)getEFactoryInstance();
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
		estimationEClass = createEClass(ESTIMATION);
		createEAttribute(estimationEClass, ESTIMATION__VALUE);
		createEAttribute(estimationEClass, ESTIMATION__ELEMENT_TYPE);
		createEAttribute(estimationEClass, ESTIMATION__ELEMENT_NAME);

		diagnosticEClass = createEClass(DIAGNOSTIC);
		createEReference(diagnosticEClass, DIAGNOSTIC__ESTIMATION);
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

		// Initialize classes and features; add operations and parameters
		initEClass(estimationEClass, Estimation.class, "Estimation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEstimation_Value(), ecorePackage.getEDouble(), "value", null, 1, 1, Estimation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getEstimation_ElementType(), ecorePackage.getEString(), "elementType", null, 1, 1, Estimation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getEstimation_ElementName(), ecorePackage.getEString(), "elementName", null, 1, 1, Estimation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(diagnosticEClass, Diagnostic.class, "Diagnostic", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagnostic_Estimation(), this.getEstimation(), null, "estimation", null, 0, -1, Diagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //RiskPackageImpl
