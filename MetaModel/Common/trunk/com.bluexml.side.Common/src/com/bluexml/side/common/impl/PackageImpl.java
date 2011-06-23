/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common.impl;

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
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.NameSpace;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.common.impl.PackageImpl#getStereotypeSet <em>Stereotype Set</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.PackageImpl#getPackageSet <em>Package Set</em>}</li>
 *   <li>{@link com.bluexml.side.common.impl.PackageImpl#getNamespaceSet <em>Namespace Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageImpl extends NamedModelElementImpl implements com.bluexml.side.common.Package {
	/**
	 * The cached value of the '{@link #getStereotypeSet() <em>Stereotype Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotypeSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Stereotype> stereotypeSet;

	/**
	 * The cached value of the '{@link #getPackageSet() <em>Package Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageSet()
	 * @generated
	 * @ordered
	 */
	protected EList<com.bluexml.side.common.Package> packageSet;

	/**
	 * The cached value of the '{@link #getNamespaceSet() <em>Namespace Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespaceSet()
	 * @generated
	 * @ordered
	 */
	protected EList<NameSpace> namespaceSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Stereotype> getStereotypeSet() {
		if (stereotypeSet == null) {
			stereotypeSet = new EObjectContainmentEList<Stereotype>(Stereotype.class, this, CommonPackage.PACKAGE__STEREOTYPE_SET);
		}
		return stereotypeSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<com.bluexml.side.common.Package> getPackageSet() {
		if (packageSet == null) {
			packageSet = new EObjectContainmentEList<com.bluexml.side.common.Package>(com.bluexml.side.common.Package.class, this, CommonPackage.PACKAGE__PACKAGE_SET);
		}
		return packageSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NameSpace> getNamespaceSet() {
		if (namespaceSet == null) {
			namespaceSet = new EObjectContainmentEList<NameSpace>(NameSpace.class, this, CommonPackage.PACKAGE__NAMESPACE_SET);
		}
		return namespaceSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean equalsForMerge(com.bluexml.side.common.Package other) {
		if (equalsForMergeBodyOCL == null) {
			EOperation eOperation = CommonPackage.Literals.PACKAGE.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(CommonPackage.Literals.PACKAGE, eOperation);
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
	public com.bluexml.side.common.Package getRootPackage() {
		if (getRootPackageBodyOCL == null) {
			EOperation eOperation = CommonPackage.Literals.PACKAGE.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(CommonPackage.Literals.PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getRootPackageBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getRootPackageBodyOCL);
	
		return (com.bluexml.side.common.Package) query.evaluate(this);
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getRootPackage <em>Get Root Package</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootPackage
	 * @generated
	 */
	private static OCLExpression<EClassifier> getRootPackageBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NameSpace> getAllNamespaces() {
		if (getAllNamespacesBodyOCL == null) {
			EOperation eOperation = CommonPackage.Literals.PACKAGE.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(CommonPackage.Literals.PACKAGE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllNamespacesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllNamespacesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<NameSpace> result = (Collection<NameSpace>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<NameSpace>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllNamespaces <em>Get All Namespaces</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllNamespaces
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllNamespacesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				return ((InternalEList<?>)getStereotypeSet()).basicRemove(otherEnd, msgs);
			case CommonPackage.PACKAGE__PACKAGE_SET:
				return ((InternalEList<?>)getPackageSet()).basicRemove(otherEnd, msgs);
			case CommonPackage.PACKAGE__NAMESPACE_SET:
				return ((InternalEList<?>)getNamespaceSet()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				return getStereotypeSet();
			case CommonPackage.PACKAGE__PACKAGE_SET:
				return getPackageSet();
			case CommonPackage.PACKAGE__NAMESPACE_SET:
				return getNamespaceSet();
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
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				getStereotypeSet().clear();
				getStereotypeSet().addAll((Collection<? extends Stereotype>)newValue);
				return;
			case CommonPackage.PACKAGE__PACKAGE_SET:
				getPackageSet().clear();
				getPackageSet().addAll((Collection<? extends com.bluexml.side.common.Package>)newValue);
				return;
			case CommonPackage.PACKAGE__NAMESPACE_SET:
				getNamespaceSet().clear();
				getNamespaceSet().addAll((Collection<? extends NameSpace>)newValue);
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
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				getStereotypeSet().clear();
				return;
			case CommonPackage.PACKAGE__PACKAGE_SET:
				getPackageSet().clear();
				return;
			case CommonPackage.PACKAGE__NAMESPACE_SET:
				getNamespaceSet().clear();
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
			case CommonPackage.PACKAGE__STEREOTYPE_SET:
				return stereotypeSet != null && !stereotypeSet.isEmpty();
			case CommonPackage.PACKAGE__PACKAGE_SET:
				return packageSet != null && !packageSet.isEmpty();
			case CommonPackage.PACKAGE__NAMESPACE_SET:
				return namespaceSet != null && !namespaceSet.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //PackageImpl
