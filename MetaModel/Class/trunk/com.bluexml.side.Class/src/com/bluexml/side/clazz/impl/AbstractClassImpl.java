/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.AbstractClassImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AbstractClassImpl#getGeneralizations <em>Generalizations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractClassImpl extends TitledNamedClassModelElementImpl implements AbstractClass {
	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> attributes;

	/**
	 * The cached value of the '{@link #getGeneralizations() <em>Generalizations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneralizations()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractClass> generalizations;

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
	public EList<Attribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this, ClazzPackage.ABSTRACT_CLASS__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractClass> getGeneralizations() {
		if (generalizations == null) {
			generalizations = new EObjectResolvingEList<AbstractClass>(AbstractClass.class, this, ClazzPackage.ABSTRACT_CLASS__GENERALIZATIONS);
		}
		return generalizations;
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
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractClass> getInheritedClasses() {
		if (getInheritedClassesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
		Collection<AbstractClass> result = (Collection<AbstractClass>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractClass>(result.size(), result.toArray());
	
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
	public EList<AbstractClass> getAllSubTypes() {
		if (getAllSubTypesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllSubTypesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllSubTypesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AbstractClass> result = (Collection<AbstractClass>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractClass>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllSubTypes <em>Get All Sub Types</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllSubTypes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllSubTypesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAllInheritedAttributes() {
		if (getAllInheritedAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(4);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
	public EList<Association> getSourceAssociations() {
		if (getSourceAssociationsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(6);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(7);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(8);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(9);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
	public EList<AssociationEnd> getSourceAssociationEnds() {
		if (getSourceAssociationEndsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(10);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getSourceAssociationEndsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getSourceAssociationEndsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AssociationEnd> result = (Collection<AssociationEnd>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AssociationEnd>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getSourceAssociationEnds <em>Get Source Association Ends</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceAssociationEnds
	 * @generated
	 */
	private static OCLExpression<EClassifier> getSourceAssociationEndsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssociationEnd> getTargetAssociationEnds() {
		if (getTargetAssociationEndsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(11);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getTargetAssociationEndsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getTargetAssociationEndsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AssociationEnd> result = (Collection<AssociationEnd>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AssociationEnd>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getTargetAssociationEnds <em>Get Target Association Ends</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetAssociationEnds
	 * @generated
	 */
	private static OCLExpression<EClassifier> getTargetAssociationEndsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getSubTypes() {
		if (getSubTypesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
		Collection<Clazz> result = (Collection<Clazz>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Clazz>(result.size(), result.toArray());
	
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
	public EList<Association> getAllSourceAssociations() {
		if (getAllSourceAssociationsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(12);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(13);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
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
	public EList<AbstractClass> getLinkedClasses() {
		if (getLinkedClassesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(14);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getLinkedClassesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getLinkedClassesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AbstractClass> result = (Collection<AbstractClass>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractClass>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getLinkedClasses <em>Get Linked Classes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedClasses
	 * @generated
	 */
	private static OCLExpression<EClassifier> getLinkedClassesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssociationEnd> getAllSourceAssociationEnds() {
		if (getAllSourceAssociationEndsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(15);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllSourceAssociationEndsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllSourceAssociationEndsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AssociationEnd> result = (Collection<AssociationEnd>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AssociationEnd>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllSourceAssociationEnds <em>Get All Source Association Ends</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllSourceAssociationEnds
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllSourceAssociationEndsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssociationEnd> getAllTargetAssociationEnds() {
		if (getAllTargetAssociationEndsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(16);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllTargetAssociationEndsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllTargetAssociationEndsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AssociationEnd> result = (Collection<AssociationEnd>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AssociationEnd>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllTargetAssociationEnds <em>Get All Target Association Ends</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllTargetAssociationEnds
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllTargetAssociationEndsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAllAttributesWithoutAspectsAttributes() {
		if (getAllAttributesWithoutAspectsAttributesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ABSTRACT_CLASS.getEOperations().get(5);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ABSTRACT_CLASS, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllAttributesWithoutAspectsAttributesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAttributesWithoutAspectsAttributesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Attribute> result = (Collection<Attribute>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Attribute>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllAttributesWithoutAspectsAttributes <em>Get All Attributes Without Aspects Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAttributesWithoutAspectsAttributes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllAttributesWithoutAspectsAttributesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClazzPackage.ABSTRACT_CLASS__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
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
			case ClazzPackage.ABSTRACT_CLASS__ATTRIBUTES:
				return getAttributes();
			case ClazzPackage.ABSTRACT_CLASS__GENERALIZATIONS:
				return getGeneralizations();
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
			case ClazzPackage.ABSTRACT_CLASS__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends Attribute>)newValue);
				return;
			case ClazzPackage.ABSTRACT_CLASS__GENERALIZATIONS:
				getGeneralizations().clear();
				getGeneralizations().addAll((Collection<? extends AbstractClass>)newValue);
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
			case ClazzPackage.ABSTRACT_CLASS__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ClazzPackage.ABSTRACT_CLASS__GENERALIZATIONS:
				getGeneralizations().clear();
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
			case ClazzPackage.ABSTRACT_CLASS__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case ClazzPackage.ABSTRACT_CLASS__GENERALIZATIONS:
				return generalizations != null && !generalizations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //AbstractClassImpl
