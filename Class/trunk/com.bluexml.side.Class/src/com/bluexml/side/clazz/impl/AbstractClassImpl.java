/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.ClazzPackage;

import java.util.Collection;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class AbstractClassImpl extends AbstractContainerImpl implements AbstractClass {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.ABSTRACT_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean equalsForMerge(AbstractClass other) {
		if (equalsForMergeBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				equalsForMergeBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(equalsForMergeBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("other", other);
	  
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #equalsForMerge <em>Equals For Merge</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #equalsForMerge
	 * @generated
	 */
	private static OCLExpression<EClassifier> equalsForMergeBodyOCL;
	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //AbstractClassImpl
