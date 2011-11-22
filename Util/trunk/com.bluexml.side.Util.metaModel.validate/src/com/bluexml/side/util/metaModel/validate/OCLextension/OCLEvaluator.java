package com.bluexml.side.util.metaModel.validate.OCLextension;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCLExpression;

public class OCLEvaluator {
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

	public static <T> EList<T> eval(Object context, String body) throws Exception {
		OCL.Helper helper = OCL_ENV.createOCLHelper();

		helper.setInstanceContext(context);
		OCLExpression queryOCLExpression = helper.createQuery(body);

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(queryOCLExpression);
		Collection<T> result = null;
		Object evaluate = query.evaluate(context);
		if (evaluate instanceof Collection<?>) {
			result = (Collection<T>) evaluate;
		} else {
			result = new ArrayList<T>();
			result.add((T) evaluate);
		}

		return new BasicEList.UnmodifiableEList<T>(result.size(), result.toArray());
	}
}
