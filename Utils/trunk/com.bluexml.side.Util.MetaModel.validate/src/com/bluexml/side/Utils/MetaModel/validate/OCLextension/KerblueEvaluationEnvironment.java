package com.bluexml.side.Utils.MetaModel.validate.OCLextension;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;

import com.bluexml.side.Util.ecore.EClassUtils;

class KerblueEvaluationEnvironment extends EcoreEvaluationEnvironment {

	public KerblueEvaluationEnvironment() {
		super();
	}

	public KerblueEvaluationEnvironment(
			EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent) {
		super(parent);
	}
	/**
	 * Overides OCL enviroment evaluation method
	 * We overide this method because the operation return is always the oepration
	 * from the top superType of the context from wich is called the operation
	 * In this method, we will find the nearest corresponding method from the call context
	 * and forward this this operation to the former method
	 * @param operation the operation to resolve
	 * @param receiver the call context
	 */
	@Override
	protected Method getJavaMethodFor(EOperation operation, Object receiver) {
		Method result =null;
		EOperation resolved = null;
		EObject eo = (EObject) receiver;
		if(eo != null){

		EClass eoClass = eo.eClass();
		resolved = EClassUtils.getEOperationFor(operation,eoClass);
		}
		if(resolved == null){
			result = super.getJavaMethodFor(operation, receiver);
		}else{
			result = super.getJavaMethodFor(resolved, receiver);
		}
		return result;
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
