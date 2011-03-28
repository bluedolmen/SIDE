/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Risk.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.bluexml.side.requirements.generator.metamodel.Risk.Diagnostic;
import com.bluexml.side.requirements.generator.metamodel.Risk.Estimation;
import com.bluexml.side.requirements.generator.metamodel.Risk.RiskPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagnostic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Risk.impl.DiagnosticImpl#getEstimation <em>Estimation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagnosticImpl extends EObjectImpl implements Diagnostic {
	/**
	 * The cached value of the '{@link #getEstimation() <em>Estimation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEstimation()
	 * @generated
	 * @ordered
	 */
	protected EList<Estimation> estimation;

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
		return RiskPackage.Literals.DIAGNOSTIC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Estimation> getEstimation() {
		if (estimation == null) {
			estimation = new EObjectContainmentEList<Estimation>(Estimation.class, this, RiskPackage.DIAGNOSTIC__ESTIMATION);
		}
		return estimation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RiskPackage.DIAGNOSTIC__ESTIMATION:
				return ((InternalEList<?>)getEstimation()).basicRemove(otherEnd, msgs);
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
			case RiskPackage.DIAGNOSTIC__ESTIMATION:
				return getEstimation();
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
			case RiskPackage.DIAGNOSTIC__ESTIMATION:
				getEstimation().clear();
				getEstimation().addAll((Collection<? extends Estimation>)newValue);
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
			case RiskPackage.DIAGNOSTIC__ESTIMATION:
				getEstimation().clear();
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
			case RiskPackage.DIAGNOSTIC__ESTIMATION:
				return estimation != null && !estimation.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DiagnosticImpl
