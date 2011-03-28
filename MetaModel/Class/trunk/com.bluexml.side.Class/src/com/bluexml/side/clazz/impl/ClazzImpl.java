/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Clazz</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#getAspects <em>Aspects</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#isDeprecated <em>Deprecated</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClazzImpl extends AbstractClassImpl implements Clazz {
	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationComponent> operations;

	/**
	 * The cached value of the '{@link #getAspects() <em>Aspects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAspects()
	 * @generated
	 * @ordered
	 */
	protected EList<Aspect> aspects;

	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #isDeprecated() <em>Deprecated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeprecated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEPRECATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeprecated() <em>Deprecated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeprecated()
	 * @generated
	 * @ordered
	 */
	protected boolean deprecated = DEPRECATED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClazzImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.CLAZZ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationComponent> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<OperationComponent>(OperationComponent.class, this, ClazzPackage.CLAZZ__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Aspect> getAspects() {
		if (aspects == null) {
			aspects = new EObjectResolvingEList<Aspect>(Aspect.class, this, ClazzPackage.CLAZZ__ASPECTS);
		}
		return aspects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.CLAZZ__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeprecated() {
		return deprecated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeprecated(boolean newDeprecated) {
		boolean oldDeprecated = deprecated;
		deprecated = newDeprecated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.CLAZZ__DEPRECATED, oldDeprecated, deprecated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAllAttributes() {
		if (getAllAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllAttributesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAttributesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Attribute> result = (Collection<Attribute>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Attribute>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllAttributes <em>Get All Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAttributes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllAttributesBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getClassAndAspectAttributes() {
		if (getClassAndAspectAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getClassAndAspectAttributesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getClassAndAspectAttributesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Attribute> result = (Collection<Attribute>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Attribute>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getClassAndAspectAttributes <em>Get Class And Aspect Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassAndAspectAttributes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getClassAndAspectAttributesBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAspectAttributes() {
		if (getAspectAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAspectAttributesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAspectAttributesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Attribute> result = (Collection<Attribute>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Attribute>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAspectAttributes <em>Get Aspect Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAspectAttributes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAspectAttributesBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAllInheritedClassAndAspectAttributes() {
		if (getAllInheritedClassAndAspectAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllInheritedClassAndAspectAttributesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllInheritedClassAndAspectAttributesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Attribute> result = (Collection<Attribute>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Attribute>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllInheritedClassAndAspectAttributes <em>Get All Inherited Class And Aspect Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllInheritedClassAndAspectAttributes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllInheritedClassAndAspectAttributesBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Aspect> getAllInheritedAspects() {
		if (getAllInheritedAspectsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(4);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllInheritedAspectsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllInheritedAspectsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Aspect> result = (Collection<Aspect>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Aspect>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllInheritedAspects <em>Get All Inherited Aspects</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllInheritedAspects
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllInheritedAspectsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Aspect> getAllAspects() {
		if (getAllAspectsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(7);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllAspectsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAspectsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Aspect> result = (Collection<Aspect>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Aspect>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllAspects <em>Get All Aspects</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAspects
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllAspectsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Aspect> getHasAspects() {
		if (getHasAspectsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(8);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getHasAspectsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getHasAspectsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Aspect> result = (Collection<Aspect>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Aspect>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getHasAspects <em>Get Has Aspects</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasAspects
	 * @generated
	 */
	private static OCLExpression<EClassifier> getHasAspectsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAllSourceAssociationsIncludingAspect() {
		if (getAllSourceAssociationsIncludingAspectBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(5);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllSourceAssociationsIncludingAspectBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllSourceAssociationsIncludingAspectBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Association> result = (Collection<Association>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Association>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllSourceAssociationsIncludingAspect <em>Get All Source Associations Including Aspect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllSourceAssociationsIncludingAspect
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllSourceAssociationsIncludingAspectBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAllTargetAssociationsIncludingAspect() {
		if (getAllTargetAssociationsIncludingAspectBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(6);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllTargetAssociationsIncludingAspectBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllTargetAssociationsIncludingAspectBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Association> result = (Collection<Association>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Association>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllTargetAssociationsIncludingAspect <em>Get All Target Associations Including Aspect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllTargetAssociationsIncludingAspect
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllTargetAssociationsIncludingAspectBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClazzPackage.CLAZZ__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
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
			case ClazzPackage.CLAZZ__OPERATIONS:
				return getOperations();
			case ClazzPackage.CLAZZ__ASPECTS:
				return getAspects();
			case ClazzPackage.CLAZZ__ABSTRACT:
				return isAbstract();
			case ClazzPackage.CLAZZ__DEPRECATED:
				return isDeprecated();
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
			case ClazzPackage.CLAZZ__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends OperationComponent>)newValue);
				return;
			case ClazzPackage.CLAZZ__ASPECTS:
				getAspects().clear();
				getAspects().addAll((Collection<? extends Aspect>)newValue);
				return;
			case ClazzPackage.CLAZZ__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case ClazzPackage.CLAZZ__DEPRECATED:
				setDeprecated((Boolean)newValue);
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
			case ClazzPackage.CLAZZ__OPERATIONS:
				getOperations().clear();
				return;
			case ClazzPackage.CLAZZ__ASPECTS:
				getAspects().clear();
				return;
			case ClazzPackage.CLAZZ__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case ClazzPackage.CLAZZ__DEPRECATED:
				setDeprecated(DEPRECATED_EDEFAULT);
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
			case ClazzPackage.CLAZZ__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ClazzPackage.CLAZZ__ASPECTS:
				return aspects != null && !aspects.isEmpty();
			case ClazzPackage.CLAZZ__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case ClazzPackage.CLAZZ__DEPRECATED:
				return deprecated != DEPRECATED_EDEFAULT;
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
		result.append(" (abstract: ");
		result.append(abstract_);
		result.append(", deprecated: ");
		result.append(deprecated);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //ClazzImpl
