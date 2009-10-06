/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Risk;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see com.bluexml.side.requirements.generator.metamodel.Risk.RiskFactory
 * @model kind="package"
 * @generated
 */
public interface RiskPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Risk";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bluexml.com/rwm/risk/1.0/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Risk";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RiskPackage eINSTANCE = com.bluexml.side.requirements.generator.metamodel.Risk.impl.RiskPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Risk.impl.EstimationImpl <em>Estimation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.impl.EstimationImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.impl.RiskPackageImpl#getEstimation()
	 * @generated
	 */
	int ESTIMATION = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION__ELEMENT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION__ELEMENT_NAME = 2;

	/**
	 * The number of structural features of the '<em>Estimation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Risk.impl.DiagnosticImpl <em>Diagnostic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.impl.DiagnosticImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.impl.RiskPackageImpl#getDiagnostic()
	 * @generated
	 */
	int DIAGNOSTIC = 1;

	/**
	 * The feature id for the '<em><b>Estimation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGNOSTIC__ESTIMATION = 0;

	/**
	 * The number of structural features of the '<em>Diagnostic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGNOSTIC_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Risk.Estimation <em>Estimation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Estimation</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.Estimation
	 * @generated
	 */
	EClass getEstimation();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Risk.Estimation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.Estimation#getValue()
	 * @see #getEstimation()
	 * @generated
	 */
	EAttribute getEstimation_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Risk.Estimation#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Type</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.Estimation#getElementType()
	 * @see #getEstimation()
	 * @generated
	 */
	EAttribute getEstimation_ElementType();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Risk.Estimation#getElementName <em>Element Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Name</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.Estimation#getElementName()
	 * @see #getEstimation()
	 * @generated
	 */
	EAttribute getEstimation_ElementName();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Risk.Diagnostic <em>Diagnostic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagnostic</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.Diagnostic
	 * @generated
	 */
	EClass getDiagnostic();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Risk.Diagnostic#getEstimation <em>Estimation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Estimation</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.Diagnostic#getEstimation()
	 * @see #getDiagnostic()
	 * @generated
	 */
	EReference getDiagnostic_Estimation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RiskFactory getRiskFactory();

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
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Risk.impl.EstimationImpl <em>Estimation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Risk.impl.EstimationImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Risk.impl.RiskPackageImpl#getEstimation()
		 * @generated
		 */
		EClass ESTIMATION = eINSTANCE.getEstimation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTIMATION__VALUE = eINSTANCE.getEstimation_Value();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTIMATION__ELEMENT_TYPE = eINSTANCE.getEstimation_ElementType();

		/**
		 * The meta object literal for the '<em><b>Element Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTIMATION__ELEMENT_NAME = eINSTANCE.getEstimation_ElementName();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Risk.impl.DiagnosticImpl <em>Diagnostic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Risk.impl.DiagnosticImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Risk.impl.RiskPackageImpl#getDiagnostic()
		 * @generated
		 */
		EClass DIAGNOSTIC = eINSTANCE.getDiagnostic();

		/**
		 * The meta object literal for the '<em><b>Estimation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGNOSTIC__ESTIMATION = eINSTANCE.getDiagnostic_Estimation();

	}

} //RiskPackage
