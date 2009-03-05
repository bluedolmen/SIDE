package com.bluexml.side.Utils.MetaModel.validate.OCLextension;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;

class KerblueEvaluationEnvironment extends EcoreEvaluationEnvironment {

	public KerblueEvaluationEnvironment() {
		super();
	}

	public KerblueEvaluationEnvironment(
			EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent) {
		super(parent);
	}

	public Object callOperation(EOperation operation, int opcode,
			Object source, Object[] args) {
		if (operation.getEAnnotation("KerblueEnvironment") == null) {
			// not our custom regex operation
			return super.callOperation(operation, opcode, source, args);
		}

		if ("regexMatch".equals(operation.getName())) {
			Pattern pattern = Pattern.compile((String) args[0]);
			Matcher matcher = pattern.matcher((String) source);
			return matcher.matches() ? matcher.group() : null;
		}

		throw new UnsupportedOperationException(); // unknown operation
	}
}
