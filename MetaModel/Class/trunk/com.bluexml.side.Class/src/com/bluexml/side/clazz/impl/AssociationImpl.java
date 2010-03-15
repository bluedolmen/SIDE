/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

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

import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getAssociationType <em>Association Type</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getFirstEnd <em>First End</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getSecondEnd <em>Second End</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#isOrdered <em>Ordered</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationImpl extends TitledNamedClassModelElementImpl implements Association {
	/**
	 * The default value of the '{@link #getAssociationType() <em>Association Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationType()
	 * @generated
	 * @ordered
	 */
	protected static final AssociationType ASSOCIATION_TYPE_EDEFAULT = AssociationType.DIRECT;

	/**
	 * The cached value of the '{@link #getAssociationType() <em>Association Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationType()
	 * @generated
	 * @ordered
	 */
	protected AssociationType associationType = ASSOCIATION_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFirstEnd() <em>First End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstEnd()
	 * @generated
	 * @ordered
	 */
	protected AssociationEnd firstEnd;

	/**
	 * The cached value of the '{@link #getSecondEnd() <em>Second End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondEnd()
	 * @generated
	 * @ordered
	 */
	protected AssociationEnd secondEnd;

	/**
	 * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDERED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected boolean ordered = ORDERED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationType getAssociationType() {
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationType(AssociationType newAssociationType) {
		AssociationType oldAssociationType = associationType;
		associationType = newAssociationType == null ? ASSOCIATION_TYPE_EDEFAULT : newAssociationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE, oldAssociationType, associationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationEnd getFirstEnd() {
		return firstEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFirstEnd(AssociationEnd newFirstEnd, NotificationChain msgs) {
		AssociationEnd oldFirstEnd = firstEnd;
		firstEnd = newFirstEnd;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__FIRST_END, oldFirstEnd, newFirstEnd);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstEnd(AssociationEnd newFirstEnd) {
		if (newFirstEnd != firstEnd) {
			NotificationChain msgs = null;
			if (firstEnd != null)
				msgs = ((InternalEObject)firstEnd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClazzPackage.ASSOCIATION__FIRST_END, null, msgs);
			if (newFirstEnd != null)
				msgs = ((InternalEObject)newFirstEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClazzPackage.ASSOCIATION__FIRST_END, null, msgs);
			msgs = basicSetFirstEnd(newFirstEnd, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__FIRST_END, newFirstEnd, newFirstEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationEnd getSecondEnd() {
		return secondEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecondEnd(AssociationEnd newSecondEnd, NotificationChain msgs) {
		AssociationEnd oldSecondEnd = secondEnd;
		secondEnd = newSecondEnd;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__SECOND_END, oldSecondEnd, newSecondEnd);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecondEnd(AssociationEnd newSecondEnd) {
		if (newSecondEnd != secondEnd) {
			NotificationChain msgs = null;
			if (secondEnd != null)
				msgs = ((InternalEObject)secondEnd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClazzPackage.ASSOCIATION__SECOND_END, null, msgs);
			if (newSecondEnd != null)
				msgs = ((InternalEObject)newSecondEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClazzPackage.ASSOCIATION__SECOND_END, null, msgs);
			msgs = basicSetSecondEnd(newSecondEnd, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__SECOND_END, newSecondEnd, newSecondEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrdered(boolean newOrdered) {
		boolean oldOrdered = ordered;
		ordered = newOrdered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__ORDERED, oldOrdered, ordered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean equalsForMerge(Association other) {
		if (equalsForMergeBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION, eOperation);
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
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReflexive() {
		if (isReflexiveBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isReflexiveBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isReflexiveBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #isReflexive <em>Is Reflexive</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReflexive
	 * @generated
	 */
	private static OCLExpression<EClassifier> isReflexiveBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getSource() {
		if (getSourceBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getSourceBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getSourceBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Clazz> result = (Collection<Clazz>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Clazz>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getSource <em>Get Source</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource
	 * @generated
	 */
	private static OCLExpression<EClassifier> getSourceBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getTarget() {
		if (getTargetBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION.getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getTargetBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getTargetBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Clazz> result = (Collection<Clazz>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Clazz>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getTarget <em>Get Target</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget
	 * @generated
	 */
	private static OCLExpression<EClassifier> getTargetBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssociationEnd> getAssociationEnd(Clazz clazz) {
		if (getAssociationEndBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION.getEOperations().get(4);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAssociationEndBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAssociationEndBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("clazz", clazz);
	  
		@SuppressWarnings("unchecked")
		Collection<AssociationEnd> result = (Collection<AssociationEnd>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AssociationEnd>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAssociationEnd <em>Get Association End</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationEnd
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAssociationEndBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClazzPackage.ASSOCIATION__FIRST_END:
				return basicSetFirstEnd(null, msgs);
			case ClazzPackage.ASSOCIATION__SECOND_END:
				return basicSetSecondEnd(null, msgs);
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
			case ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE:
				return getAssociationType();
			case ClazzPackage.ASSOCIATION__FIRST_END:
				return getFirstEnd();
			case ClazzPackage.ASSOCIATION__SECOND_END:
				return getSecondEnd();
			case ClazzPackage.ASSOCIATION__ORDERED:
				return isOrdered();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE:
				setAssociationType((AssociationType)newValue);
				return;
			case ClazzPackage.ASSOCIATION__FIRST_END:
				setFirstEnd((AssociationEnd)newValue);
				return;
			case ClazzPackage.ASSOCIATION__SECOND_END:
				setSecondEnd((AssociationEnd)newValue);
				return;
			case ClazzPackage.ASSOCIATION__ORDERED:
				setOrdered((Boolean)newValue);
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
			case ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE:
				setAssociationType(ASSOCIATION_TYPE_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__FIRST_END:
				setFirstEnd((AssociationEnd)null);
				return;
			case ClazzPackage.ASSOCIATION__SECOND_END:
				setSecondEnd((AssociationEnd)null);
				return;
			case ClazzPackage.ASSOCIATION__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
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
			case ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE:
				return associationType != ASSOCIATION_TYPE_EDEFAULT;
			case ClazzPackage.ASSOCIATION__FIRST_END:
				return firstEnd != null;
			case ClazzPackage.ASSOCIATION__SECOND_END:
				return secondEnd != null;
			case ClazzPackage.ASSOCIATION__ORDERED:
				return ordered != ORDERED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (associationType: ");
		result.append(associationType);
		result.append(", ordered: ");
		result.append(ordered);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //AssociationImpl
