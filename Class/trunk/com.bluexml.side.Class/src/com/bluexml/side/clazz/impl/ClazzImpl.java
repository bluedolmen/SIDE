/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Operation;

import com.bluexml.side.clazz.View;
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
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Clazz</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#getGeneralizations <em>Generalizations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#getAspects <em>Aspects</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#isIsDeprecated <em>Is Deprecated</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClazzImpl#getHasView <em>Has View</em>}</li>
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
	protected EList<Operation> operations;

	/**
	 * The cached value of the '{@link #getGeneralizations() <em>Generalizations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneralizations()
	 * @generated
	 * @ordered
	 */
	protected EList<Clazz> generalizations;

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
	 * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsDeprecated() <em>Is Deprecated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsDeprecated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_DEPRECATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsDeprecated() <em>Is Deprecated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsDeprecated()
	 * @generated
	 * @ordered
	 */
	protected boolean isDeprecated = IS_DEPRECATED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHasView() <em>Has View</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasView()
	 * @generated
	 * @ordered
	 */
	protected EList<View> hasView;

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
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, ClazzPackage.CLAZZ__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getGeneralizations() {
		if (generalizations == null) {
			generalizations = new EObjectResolvingEList<Clazz>(Clazz.class, this, ClazzPackage.CLAZZ__GENERALIZATIONS);
		}
		return generalizations;
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
	public boolean isIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAbstract(boolean newIsAbstract) {
		boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.CLAZZ__IS_ABSTRACT, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsDeprecated() {
		return isDeprecated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsDeprecated(boolean newIsDeprecated) {
		boolean oldIsDeprecated = isDeprecated;
		isDeprecated = newIsDeprecated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.CLAZZ__IS_DEPRECATED, oldIsDeprecated, isDeprecated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<View> getHasView() {
		if (hasView == null) {
			hasView = new EObjectContainmentEList<View>(View.class, this, ClazzPackage.CLAZZ__HAS_VIEW);
		}
		return hasView;
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
	public EList<Attribute> getAllInheritedAttributes() {
		if (getAllInheritedAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllInheritedAttributesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllInheritedAttributesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Attribute> result = (Collection<Attribute>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Attribute>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllInheritedAttributes <em>Get All Inherited Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllInheritedAttributes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllInheritedAttributesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getClassAndAspectAttributes() {
		if (getClassAndAspectAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(2);
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
	public EList<Clazz> getInheritedClasses() {
		if (getInheritedClassesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getInheritedClassesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getInheritedClassesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Clazz> result = (Collection<Clazz>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Clazz>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getInheritedClasses <em>Get Inherited Classes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritedClasses
	 * @generated
	 */
	private static OCLExpression<EClassifier> getInheritedClassesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAspectAttributes() {
		if (getAspectAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(4);
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
	public EList<Attribute> getSubTypes() {
		if (getSubTypesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(5);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getSubTypesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getSubTypesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Attribute> result = (Collection<Attribute>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Attribute>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getSubTypes <em>Get Sub Types</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTypes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getSubTypesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAllInheritedClassAndAspectAttributes() {
		if (getAllInheritedClassAndAspectAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(6);
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
	public EList<Association> getSourceAssociations() {
		if (getSourceAssociationsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(7);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getSourceAssociationsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getSourceAssociationsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Association> result = (Collection<Association>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Association>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getSourceAssociations <em>Get Source Associations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceAssociations
	 * @generated
	 */
	private static OCLExpression<EClassifier> getSourceAssociationsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSource(Association asso) {
		if (isSourceBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(8);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isSourceBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isSourceBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("asso", asso);
	  
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #isSource <em>Is Source</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSource
	 * @generated
	 */
	private static OCLExpression<EClassifier> isSourceBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTarget(Association asso) {
		if (isTargetBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(9);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isTargetBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isTargetBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("asso", asso);
	  
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #isTarget <em>Is Target</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTarget
	 * @generated
	 */
	private static OCLExpression<EClassifier> isTargetBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getTargetAssociations() {
		if (getTargetAssociationsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(10);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getTargetAssociationsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getTargetAssociationsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Association> result = (Collection<Association>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Association>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getTargetAssociations <em>Get Target Associations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetAssociations
	 * @generated
	 */
	private static OCLExpression<EClassifier> getTargetAssociationsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAllSourceAssociations() {
		if (getAllSourceAssociationsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(11);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllSourceAssociationsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllSourceAssociationsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Association> result = (Collection<Association>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Association>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllSourceAssociations <em>Get All Source Associations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllSourceAssociations
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllSourceAssociationsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAllTargetAssociations() {
		if (getAllTargetAssociationsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(12);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllTargetAssociationsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllTargetAssociationsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Association> result = (Collection<Association>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Association>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllTargetAssociations <em>Get All Target Associations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllTargetAssociations
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllTargetAssociationsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> isClassAssociationsIn() {
		if (isClassAssociationsInBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLAZZ.getEOperations().get(13);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLAZZ, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isClassAssociationsInBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isClassAssociationsInBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Association> result = (Collection<Association>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Association>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #isClassAssociationsIn <em>Is Class Associations In</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClassAssociationsIn
	 * @generated
	 */
	private static OCLExpression<EClassifier> isClassAssociationsInBodyOCL;

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
			case ClazzPackage.CLAZZ__HAS_VIEW:
				return ((InternalEList<?>)getHasView()).basicRemove(otherEnd, msgs);
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
			case ClazzPackage.CLAZZ__GENERALIZATIONS:
				return getGeneralizations();
			case ClazzPackage.CLAZZ__ASPECTS:
				return getAspects();
			case ClazzPackage.CLAZZ__IS_ABSTRACT:
				return isIsAbstract() ? Boolean.TRUE : Boolean.FALSE;
			case ClazzPackage.CLAZZ__IS_DEPRECATED:
				return isIsDeprecated() ? Boolean.TRUE : Boolean.FALSE;
			case ClazzPackage.CLAZZ__HAS_VIEW:
				return getHasView();
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
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ClazzPackage.CLAZZ__GENERALIZATIONS:
				getGeneralizations().clear();
				getGeneralizations().addAll((Collection<? extends Clazz>)newValue);
				return;
			case ClazzPackage.CLAZZ__ASPECTS:
				getAspects().clear();
				getAspects().addAll((Collection<? extends Aspect>)newValue);
				return;
			case ClazzPackage.CLAZZ__IS_ABSTRACT:
				setIsAbstract(((Boolean)newValue).booleanValue());
				return;
			case ClazzPackage.CLAZZ__IS_DEPRECATED:
				setIsDeprecated(((Boolean)newValue).booleanValue());
				return;
			case ClazzPackage.CLAZZ__HAS_VIEW:
				getHasView().clear();
				getHasView().addAll((Collection<? extends View>)newValue);
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
			case ClazzPackage.CLAZZ__GENERALIZATIONS:
				getGeneralizations().clear();
				return;
			case ClazzPackage.CLAZZ__ASPECTS:
				getAspects().clear();
				return;
			case ClazzPackage.CLAZZ__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case ClazzPackage.CLAZZ__IS_DEPRECATED:
				setIsDeprecated(IS_DEPRECATED_EDEFAULT);
				return;
			case ClazzPackage.CLAZZ__HAS_VIEW:
				getHasView().clear();
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
			case ClazzPackage.CLAZZ__GENERALIZATIONS:
				return generalizations != null && !generalizations.isEmpty();
			case ClazzPackage.CLAZZ__ASPECTS:
				return aspects != null && !aspects.isEmpty();
			case ClazzPackage.CLAZZ__IS_ABSTRACT:
				return isAbstract != IS_ABSTRACT_EDEFAULT;
			case ClazzPackage.CLAZZ__IS_DEPRECATED:
				return isDeprecated != IS_DEPRECATED_EDEFAULT;
			case ClazzPackage.CLAZZ__HAS_VIEW:
				return hasView != null && !hasView.isEmpty();
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
		result.append(" (isAbstract: ");
		result.append(isAbstract);
		result.append(", isDeprecated: ");
		result.append(isDeprecated);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //ClazzImpl
