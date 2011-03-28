/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Diagnostic.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.bluexml.side.requirements.generator.metamodel.Diagnostic.Diagnostic;
import com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticFactory;
import com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage;
import com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem;
import com.bluexml.side.requirements.generator.metamodel.Diagnostic.Severity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiagnosticPackageImpl extends EPackageImpl implements DiagnosticPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass problemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagnosticEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum severityEEnum = null;

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
	 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DiagnosticPackageImpl() {
		super(eNS_URI, DiagnosticFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DiagnosticPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DiagnosticPackage init() {
		if (isInited) return (DiagnosticPackage)EPackage.Registry.INSTANCE.getEPackage(DiagnosticPackage.eNS_URI);

		// Obtain or create and register package
		DiagnosticPackageImpl theDiagnosticPackage = (DiagnosticPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DiagnosticPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DiagnosticPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theDiagnosticPackage.createPackageContents();

		// Initialize created meta-data
		theDiagnosticPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDiagnosticPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DiagnosticPackage.eNS_URI, theDiagnosticPackage);
		return theDiagnosticPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProblem() {
		return problemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProblem_Severity() {
		return (EAttribute)problemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProblem_ElementType() {
		return (EAttribute)problemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProblem_ElementName() {
		return (EAttribute)problemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProblem_Description() {
		return (EAttribute)problemEClass.getEStructuralFeatures().get(3);
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
	public EReference getDiagnostic_Problem() {
		return (EReference)diagnosticEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSeverity() {
		return severityEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagnosticFactory getDiagnosticFactory() {
		return (DiagnosticFactory)getEFactoryInstance();
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
		problemEClass = createEClass(PROBLEM);
		createEAttribute(problemEClass, PROBLEM__SEVERITY);
		createEAttribute(problemEClass, PROBLEM__ELEMENT_TYPE);
		createEAttribute(problemEClass, PROBLEM__ELEMENT_NAME);
		createEAttribute(problemEClass, PROBLEM__DESCRIPTION);

		diagnosticEClass = createEClass(DIAGNOSTIC);
		createEReference(diagnosticEClass, DIAGNOSTIC__PROBLEM);

		// Create enums
		severityEEnum = createEEnum(SEVERITY);
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
		initEClass(problemEClass, Problem.class, "Problem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProblem_Severity(), this.getSeverity(), "severity", null, 1, 1, Problem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getProblem_ElementType(), ecorePackage.getEString(), "elementType", null, 1, 1, Problem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getProblem_ElementName(), ecorePackage.getEString(), "elementName", null, 1, 1, Problem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getProblem_Description(), ecorePackage.getEString(), "description", null, 1, 1, Problem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(diagnosticEClass, Diagnostic.class, "Diagnostic", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagnostic_Problem(), this.getProblem(), null, "problem", null, 0, -1, Diagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(severityEEnum, Severity.class, "Severity");
		addEEnumLiteral(severityEEnum, Severity.ERROR);
		addEEnumLiteral(severityEEnum, Severity.WARNING);
		addEEnumLiteral(severityEEnum, Severity.CRITIC);

		// Create resource
		createResource(eNS_URI);
	}

} //DiagnosticPackageImpl
