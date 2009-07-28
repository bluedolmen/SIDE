package com.bluexml.side.clazz.generator.alfresco.services;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
/**
 * WARN : Not tested yet
 * @author davidabad
 *
 */
public class OCLService {
	private static final OCL OCL_ENV = KerblueOCL.newInstance();
	
	public static Object executeOCLForObject(EObject context,String body) {
		OCLExpression<EClassifier> getAllAssociationsBodyOCL;
		OCL.Helper helper = OCL_ENV.createOCLHelper();
		try {
			getAllAssociationsBodyOCL = helper.createQuery(body);
		} catch (ParserException e) {
			throw new RuntimeException(e);
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAssociationsBodyOCL);
		Object result = query.evaluate(context);
		return result;		
	}
	
	public static EList<EObject> executeOCLForCollection(EObject context,String body) {
		Object result =executeOCLForObject(context, body);
		@SuppressWarnings("unchecked")
		Collection<EObject> results = (Collection<EObject>) result;
		return new BasicEList.UnmodifiableEList<EObject>(results.size(), results.toArray());
	}
	
	
}
