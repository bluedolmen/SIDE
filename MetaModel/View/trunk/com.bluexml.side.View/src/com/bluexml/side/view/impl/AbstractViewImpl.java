/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.common.Container;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.AbstractViewImpl#getViewOf <em>View Of</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractViewImpl#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractViewImpl extends FieldContainerImpl implements AbstractView {
	/**
	 * The cached value of the '{@link #getViewOf() <em>View Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewOf()
	 * @generated
	 * @ordered
	 */
	protected Container viewOf;

	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent operations;

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
	public Container getViewOf() {
		if (viewOf != null && viewOf.eIsProxy()) {
			InternalEObject oldViewOf = (InternalEObject)viewOf;
			viewOf = (Container)eResolveProxy(oldViewOf);
			if (viewOf != oldViewOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.ABSTRACT_VIEW__VIEW_OF, oldViewOf, viewOf));
			}
		}
		return viewOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container basicGetViewOf() {
		return viewOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewOf(Container newViewOf) {
		Container oldViewOf = viewOf;
		viewOf = newViewOf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_VIEW__VIEW_OF, oldViewOf, viewOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getOperations() {
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperations(OperationComponent newOperations, NotificationChain msgs) {
		OperationComponent oldOperations = operations;
		operations = newOperations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_VIEW__OPERATIONS, oldOperations, newOperations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperations(OperationComponent newOperations) {
		if (newOperations != operations) {
			NotificationChain msgs = null;
			if (operations != null)
				msgs = ((InternalEObject)operations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_VIEW__OPERATIONS, null, msgs);
			if (newOperations != null)
				msgs = ((InternalEObject)newOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_VIEW__OPERATIONS, null, msgs);
			msgs = basicSetOperations(newOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_VIEW__OPERATIONS, newOperations, newOperations));
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
	public EList<Col> getCols() {
		if (getColsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getColsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getColsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Col> result = (Collection<Col>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Col>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getCols <em>Get Cols</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols
	 * @generated
	 */
	private static OCLExpression<EClassifier> getColsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDirectChildFields() {
		if (getDirectChildFieldsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(2);
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
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(3);
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
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(4);
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
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(5);
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.ABSTRACT_VIEW__OPERATIONS:
				return basicSetOperations(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViewPackage.ABSTRACT_VIEW__VIEW_OF:
				if (resolve) return getViewOf();
				return basicGetViewOf();
			case ViewPackage.ABSTRACT_VIEW__OPERATIONS:
				return getOperations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ViewPackage.ABSTRACT_VIEW__VIEW_OF:
				setViewOf((Container)newValue);
				return;
			case ViewPackage.ABSTRACT_VIEW__OPERATIONS:
				setOperations((OperationComponent)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ViewPackage.ABSTRACT_VIEW__VIEW_OF:
				setViewOf((Container)null);
				return;
			case ViewPackage.ABSTRACT_VIEW__OPERATIONS:
				setOperations((OperationComponent)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ViewPackage.ABSTRACT_VIEW__VIEW_OF:
				return viewOf != null;
			case ViewPackage.ABSTRACT_VIEW__OPERATIONS:
				return operations != null;
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //AbstractViewImpl
