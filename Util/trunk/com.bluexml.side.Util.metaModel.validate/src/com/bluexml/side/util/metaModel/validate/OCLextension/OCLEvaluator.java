package com.bluexml.side.util.metaModel.validate.OCLextension;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCLExpression;

public class OCLEvaluator {
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

	public <T> EList<T> eval(Object context, String body) throws Exception {
		OCL.Helper helper = OCL_ENV.createOCLHelper();
		
//		EClass CLASS_PACKAGE = null;
//		EOperation eOperation = CLASS_PACKAGE.getEOperations().get(10);
		
//		helper.setOperationContext((EClassifier) CLASS_PACKAGE, eOperation);

		

		OCLExpression queryOCLExpression = helper.createQuery(body);

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(queryOCLExpression);

		@SuppressWarnings("unchecked")
		Collection<T> result = (Collection<T>) query.evaluate(context);
		return new BasicEList.UnmodifiableEList<T>(result.size(), result.toArray());
	}
}
