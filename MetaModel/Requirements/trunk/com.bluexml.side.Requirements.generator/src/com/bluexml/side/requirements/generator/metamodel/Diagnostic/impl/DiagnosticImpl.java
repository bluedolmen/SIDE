/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Diagnostic.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.bluexml.side.requirements.generator.metamodel.Diagnostic.Diagnostic;
import com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage;
import com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagnostic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.impl.DiagnosticImpl#getProblem <em>Problem</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagnosticImpl extends EObjectImpl implements Diagnostic {
	/**
	 * The cached value of the '{@link #getProblem() <em>Problem</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProblem()
	 * @generated
	 * @ordered
	 */
	protected EList<Problem> problem;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagnosticImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiagnosticPackage.Literals.DIAGNOSTIC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Problem> getProblem() {
		if (problem == null) {
			problem = new EObjectContainmentEList<Problem>(Problem.class, this, DiagnosticPackage.DIAGNOSTIC__PROBLEM);
		}
		return problem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiagnosticPackage.DIAGNOSTIC__PROBLEM:
				return ((InternalEList<?>)getProblem()).basicRemove(otherEnd, msgs);
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
			case DiagnosticPackage.DIAGNOSTIC__PROBLEM:
				return getProblem();
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
			case DiagnosticPackage.DIAGNOSTIC__PROBLEM:
				getProblem().clear();
				getProblem().addAll((Collection<? extends Problem>)newValue);
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
			case DiagnosticPackage.DIAGNOSTIC__PROBLEM:
				getProblem().clear();
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
			case DiagnosticPackage.DIAGNOSTIC__PROBLEM:
				return problem != null && !problem.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DiagnosticImpl
