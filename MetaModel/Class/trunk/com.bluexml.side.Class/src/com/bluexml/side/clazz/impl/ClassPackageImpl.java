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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.common.Constraint;
import com.bluexml.side.common.CustomDataType;
import com.bluexml.side.common.impl.PackageImpl;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getClassSet <em>Class Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getAssociationSet <em>Association Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getAspectSet <em>Aspect Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getEnumerationSet <em>Enumeration Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getConstraintSet <em>Constraint Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ClassPackageImpl#getCustomDataTypeSet <em>Custom Data Type Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassPackageImpl extends PackageImpl implements ClassPackage {
	/**
	 * The cached value of the '{@link #getClassSet() <em>Class Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Clazz> classSet;

	/**
	 * The cached value of the '{@link #getAssociationSet() <em>Association Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> associationSet;

	/**
	 * The cached value of the '{@link #getAspectSet() <em>Aspect Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAspectSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Aspect> aspectSet;

	/**
	 * The cached value of the '{@link #getEnumerationSet() <em>Enumeration Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Enumeration> enumerationSet;

	/**
	 * The cached value of the '{@link #getConstraintSet() <em>Constraint Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> constraintSet;

	/**
	 * The cached value of the '{@link #getCustomDataTypeSet() <em>Custom Data Type Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomDataTypeSet()
	 * @generated
	 * @ordered
	 */
	protected EList<CustomDataType> customDataTypeSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.CLASS_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getClassSet() {
		if (classSet == null) {
			classSet = new EObjectContainmentEList<Clazz>(Clazz.class, this, ClazzPackage.CLASS_PACKAGE__CLASS_SET);
		}
		return classSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAssociationSet() {
		if (associationSet == null) {
			associationSet = new EObjectContainmentEList<Association>(Association.class, this, ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET);
		}
		return associationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Aspect> getAspectSet() {
		if (aspectSet == null) {
			aspectSet = new EObjectContainmentEList<Aspect>(Aspect.class, this, ClazzPackage.CLASS_PACKAGE__ASPECT_SET);
		}
		return aspectSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Enumeration> getEnumerationSet() {
		if (enumerationSet == null) {
			enumerationSet = new EObjectContainmentEList<Enumeration>(Enumeration.class, this, ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET);
		}
		return enumerationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Constraint> getConstraintSet() {
		if (constraintSet == null) {
			constraintSet = new EObjectContainmentEList<Constraint>(Constraint.class, this, ClazzPackage.CLASS_PACKAGE__CONSTRAINT_SET);
		}
		return constraintSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CustomDataType> getCustomDataTypeSet() {
		if (customDataTypeSet == null) {
			customDataTypeSet = new EObjectContainmentEList<CustomDataType>(CustomDataType.class, this, ClazzPackage.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET);
		}
		return customDataTypeSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassPackage> getAllPackages() {
		if (getAllPackagesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllPackagesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllPackagesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<ClassPackage> result = (Collection<ClassPackage>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<ClassPackage>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllPackages <em>Get All Packages</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllPackages
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllPackagesBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getAllClasses() {
		if (getAllClassesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllClassesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllClassesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Clazz> result = (Collection<Clazz>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Clazz>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllClasses <em>Get All Classes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllClasses
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllClassesBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Enumeration> getAllEnumerations() {
		if (getAllEnumerationsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllEnumerationsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllEnumerationsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Enumeration> result = (Collection<Enumeration>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Enumeration>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllEnumerations <em>Get All Enumerations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllEnumerations
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllEnumerationsBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Aspect> getAllAspects() {
		if (getAllAspectsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
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
	public EList<Association> getAllAssociations() {
		if (getAllAssociationsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(4);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllAssociationsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAssociationsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Association> result = (Collection<Association>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Association>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllAssociations <em>Get All Associations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAssociations
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllAssociationsBodyOCL;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractClass> getAllAbstractClasses() {
		if (getAllAbstractClassesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(5);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllAbstractClassesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAbstractClassesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AbstractClass> result = (Collection<AbstractClass>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractClass>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllAbstractClasses <em>Get All Abstract Classes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAbstractClasses
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllAbstractClassesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getAllClassesFromEveryWhere() {
		if (getAllClassesFromEveryWhereBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(6);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllClassesFromEveryWhereBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllClassesFromEveryWhereBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Clazz> result = (Collection<Clazz>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Clazz>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllClassesFromEveryWhere <em>Get All Classes From Every Where</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllClassesFromEveryWhere
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllClassesFromEveryWhereBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Aspect> getAllAspectsFromEveryWhere() {
		if (getAllAspectsFromEveryWhereBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(7);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllAspectsFromEveryWhereBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAspectsFromEveryWhereBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Aspect> result = (Collection<Aspect>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Aspect>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllAspectsFromEveryWhere <em>Get All Aspects From Every Where</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAspectsFromEveryWhere
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllAspectsFromEveryWhereBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Constraint> getAllConstraints() {
		if (getAllConstraintsBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(8);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllConstraintsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllConstraintsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Constraint> result = (Collection<Constraint>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Constraint>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllConstraints <em>Get All Constraints</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllConstraints
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllConstraintsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractClass> getAllAbstractClassesAndLinked() {
		if (getAllAbstractClassesAndLinkedBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(9);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllAbstractClassesAndLinkedBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAbstractClassesAndLinkedBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AbstractClass> result = (Collection<AbstractClass>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractClass>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllAbstractClassesAndLinked <em>Get All Abstract Classes And Linked</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAbstractClassesAndLinked
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllAbstractClassesAndLinkedBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractClass> getAllAbstractClassesAndReferences() {
		if (getAllAbstractClassesAndReferencesBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.CLASS_PACKAGE.getEOperations().get(10);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.CLASS_PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllAbstractClassesAndReferencesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllAbstractClassesAndReferencesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AbstractClass> result = (Collection<AbstractClass>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractClass>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllAbstractClassesAndReferences <em>Get All Abstract Classes And References</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAbstractClassesAndReferences
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllAbstractClassesAndReferencesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				return ((InternalEList<?>)getClassSet()).basicRemove(otherEnd, msgs);
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				return ((InternalEList<?>)getAssociationSet()).basicRemove(otherEnd, msgs);
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				return ((InternalEList<?>)getAspectSet()).basicRemove(otherEnd, msgs);
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				return ((InternalEList<?>)getEnumerationSet()).basicRemove(otherEnd, msgs);
			case ClazzPackage.CLASS_PACKAGE__CONSTRAINT_SET:
				return ((InternalEList<?>)getConstraintSet()).basicRemove(otherEnd, msgs);
			case ClazzPackage.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET:
				return ((InternalEList<?>)getCustomDataTypeSet()).basicRemove(otherEnd, msgs);
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
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				return getClassSet();
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				return getAssociationSet();
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				return getAspectSet();
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				return getEnumerationSet();
			case ClazzPackage.CLASS_PACKAGE__CONSTRAINT_SET:
				return getConstraintSet();
			case ClazzPackage.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET:
				return getCustomDataTypeSet();
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
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				getClassSet().clear();
				getClassSet().addAll((Collection<? extends Clazz>)newValue);
				return;
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				getAssociationSet().clear();
				getAssociationSet().addAll((Collection<? extends Association>)newValue);
				return;
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				getAspectSet().clear();
				getAspectSet().addAll((Collection<? extends Aspect>)newValue);
				return;
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				getEnumerationSet().clear();
				getEnumerationSet().addAll((Collection<? extends Enumeration>)newValue);
				return;
			case ClazzPackage.CLASS_PACKAGE__CONSTRAINT_SET:
				getConstraintSet().clear();
				getConstraintSet().addAll((Collection<? extends Constraint>)newValue);
				return;
			case ClazzPackage.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET:
				getCustomDataTypeSet().clear();
				getCustomDataTypeSet().addAll((Collection<? extends CustomDataType>)newValue);
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
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				getClassSet().clear();
				return;
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				getAssociationSet().clear();
				return;
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				getAspectSet().clear();
				return;
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				getEnumerationSet().clear();
				return;
			case ClazzPackage.CLASS_PACKAGE__CONSTRAINT_SET:
				getConstraintSet().clear();
				return;
			case ClazzPackage.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET:
				getCustomDataTypeSet().clear();
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
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
				return classSet != null && !classSet.isEmpty();
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
				return associationSet != null && !associationSet.isEmpty();
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
				return aspectSet != null && !aspectSet.isEmpty();
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
				return enumerationSet != null && !enumerationSet.isEmpty();
			case ClazzPackage.CLASS_PACKAGE__CONSTRAINT_SET:
				return constraintSet != null && !constraintSet.isEmpty();
			case ClazzPackage.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET:
				return customDataTypeSet != null && !customDataTypeSet.isEmpty();
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //ClassPackageImpl
