/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Risk.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.requirements.generator.metamodel.Risk.Diagnostic;
import com.bluexml.side.requirements.generator.metamodel.Risk.Estimation;
import com.bluexml.side.requirements.generator.metamodel.Risk.RiskFactory;
import com.bluexml.side.requirements.generator.metamodel.Risk.RiskPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RiskFactoryImpl extends EFactoryImpl implements RiskFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RiskFactory init() {
		try {
			RiskFactory theRiskFactory = (RiskFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.bluexml.com/rwm/risk/1.0/"); 
			if (theRiskFactory != null) {
				return theRiskFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RiskFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RiskFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RiskPackage.ESTIMATION: return createEstimation();
			case RiskPackage.DIAGNOSTIC: return createDiagnostic();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Estimation createEstimation() {
		EstimationImpl estimation = new EstimationImpl();
		return estimation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagnostic createDiagnostic() {
		DiagnosticImpl diagnostic = new DiagnosticImpl();
		return diagnostic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RiskPackage getRiskPackage() {
		return (RiskPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RiskPackage getPackage() {
		return RiskPackage.eINSTANCE;
	}

} //RiskFactoryImpl
