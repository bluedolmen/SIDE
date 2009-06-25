package com.bluexml.side.util.metamodel.validate.OCLextension;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.SendSignalAction;

public class KerblueEcoreEnvironmentFactory extends EcoreEnvironmentFactory {
	
	

	public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject>
    createEnvironment() {
        KerblueEnvironment result = new KerblueEnvironment(getEPackageRegistry());
        result.setFactory(this);
        return result;
    }
    
    public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject>
    createEnvironment(Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent) {
        if (!(parent instanceof KerblueEnvironment)) {
            throw new IllegalArgumentException(
                "Parent environment must be my environment: " + parent);
        }
        
        KerblueEnvironment result = new KerblueEnvironment((KerblueEnvironment) parent);
        result.setFactory(this);
        return result;
    }

    public EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject>
    createEvaluationEnvironment() {
        return new KerblueEvaluationEnvironment();
    }

    public EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject>
    createEvaluationEnvironment(
            EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent) {
        return new KerblueEvaluationEnvironment(parent);
    }

}
