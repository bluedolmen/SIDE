package com.bluexml.side.util.metamodel.validate.OCLextension;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.ecore.SendSignalAction;

class KerblueEnvironment extends EcoreEnvironment {
	EOperation regexMatch;
	EOperation getContainer;

	final static String OP_REGEXMATCH="regexMatch";
	final static String OP_GETCONTENER="getContainer";
	final static String KERBLUEENV="KerblueEnvironment";
	// this constructor is used to initialize the root environment
	KerblueEnvironment(EPackage.Registry registry) {
		super(registry);

		defineCustomOperations();
	}

	// this constructor is used to initialize child environments
	KerblueEnvironment(KerblueEnvironment parent) {
		super(parent);

		// get the parent's custom operations
		regexMatch = parent.regexMatch;
		getContainer = parent.getContainer;
	}

	// override this to provide visibility of the inherited protected method
	@Override
	protected void setFactory(EnvironmentFactory<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> factory) {
		super.setFactory(factory);
	}

	// use the AbstractEnvironment's mechanism for defining
	// "additional operations"
	// to add our custom operation to OCL's String primitive type
	private void defineCustomOperations() {
		defineRegExpMatchOperation();
		defineGetContainerOperation();
	}

	private void defineRegExpMatchOperation() {
		// pattern-matching operation
		regexMatch = EcoreFactory.eINSTANCE.createEOperation();
		regexMatch.setName(OP_REGEXMATCH);
		regexMatch.setEType(getOCLStandardLibrary().getString());
		EParameter parm = EcoreFactory.eINSTANCE.createEParameter();
		parm.setName("pattern");
		parm.setEType(getOCLStandardLibrary().getString());
		regexMatch.getEParameters().add(parm);

		// annotate it so that we will recognize it in the evaluation
		// environment
		EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
		annotation.setSource(KERBLUEENV);
		regexMatch.getEAnnotations().add(annotation);

		// define it as an additional operation on OCL String
		addOperation(getOCLStandardLibrary().getString(), regexMatch);
	}
	
	private void defineGetContainerOperation() {
		// pattern-matching operation
		getContainer = EcoreFactory.eINSTANCE.createEOperation();
		getContainer.setName(OP_GETCONTENER);
		getContainer.setEType(getOCLStandardLibrary().getOclAny());
		
		// annotate it so that we will recognize it in the evaluation
		// environment
		EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
		annotation.setSource(KERBLUEENV);
		getContainer.getEAnnotations().add(annotation);

		// define it as an additional operation on OCL String
		addOperation(getOCLStandardLibrary().getOclAny(), getContainer);
	}
}
