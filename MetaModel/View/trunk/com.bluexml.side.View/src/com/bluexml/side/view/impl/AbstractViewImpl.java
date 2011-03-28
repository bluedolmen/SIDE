/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class AbstractViewImpl extends FieldContainerImpl implements AbstractView {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.ABSTRACT_VIEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getFields() {
		if (getFieldsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getFieldsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getFieldsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getFields <em>Get Fields</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields
	 * @generated
	 */
	private static OCLExpression<EClassifier> getFieldsBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDirectChildFields() {
		if (getDirectChildFieldsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getDirectChildFieldsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getDirectChildFieldsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getDirectChildFields <em>Get Direct Child Fields</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectChildFields
	 * @generated
	 */
	private static OCLExpression<EClassifier> getDirectChildFieldsBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractView> getInnerView() {
		if (getInnerViewBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getInnerViewBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getInnerViewBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AbstractView> result = (Collection<AbstractView>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractView>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getInnerView <em>Get Inner View</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInnerView
	 * @generated
	 */
	private static OCLExpression<EClassifier> getInnerViewBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDisabledAndEnabledField() {
		if (getDisabledAndEnabledFieldBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getDisabledAndEnabledFieldBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getDisabledAndEnabledFieldBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getDisabledAndEnabledField <em>Get Disabled And Enabled Field</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabledAndEnabledField
	 * @generated
	 */
	private static OCLExpression<EClassifier> getDisabledAndEnabledFieldBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDisabledFields() {
		if (getDisabledFieldsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(4);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getDisabledFieldsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getDisabledFieldsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getDisabledFields <em>Get Disabled Fields</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabledFields
	 * @generated
	 */
	private static OCLExpression<EClassifier> getDisabledFieldsBodyOCL;
	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //AbstractViewImpl
