/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.impl;

import com.bluexml.side.workflow.ProcessState;
import com.bluexml.side.workflow.Variable;
import com.bluexml.side.workflow.WorkflowPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessStateImpl#getSubprocess <em>Subprocess</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessStateImpl#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessStateImpl extends EObjectImpl implements ProcessState {
	/**
	 * The cached value of the '{@link #getSubprocess() <em>Subprocess</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubprocess()
	 * @generated
	 * @ordered
	 */
	protected com.bluexml.side.workflow.Process subprocess;

	/**
	 * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> variable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.PROCESS_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public com.bluexml.side.workflow.Process getSubprocess() {
		if (subprocess != null && subprocess.eIsProxy()) {
			InternalEObject oldSubprocess = (InternalEObject)subprocess;
			subprocess = (com.bluexml.side.workflow.Process)eResolveProxy(oldSubprocess);
			if (subprocess != oldSubprocess) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkflowPackage.PROCESS_STATE__SUBPROCESS, oldSubprocess, subprocess));
			}
		}
		return subprocess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public com.bluexml.side.workflow.Process basicGetSubprocess() {
		return subprocess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubprocess(com.bluexml.side.workflow.Process newSubprocess) {
		com.bluexml.side.workflow.Process oldSubprocess = subprocess;
		subprocess = newSubprocess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.PROCESS_STATE__SUBPROCESS, oldSubprocess, subprocess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getVariable() {
		if (variable == null) {
			variable = new EObjectContainmentEList<Variable>(Variable.class, this, WorkflowPackage.PROCESS_STATE__VARIABLE);
		}
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.PROCESS_STATE__VARIABLE:
				return ((InternalEList<?>)getVariable()).basicRemove(otherEnd, msgs);
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
			case WorkflowPackage.PROCESS_STATE__SUBPROCESS:
				if (resolve) return getSubprocess();
				return basicGetSubprocess();
			case WorkflowPackage.PROCESS_STATE__VARIABLE:
				return getVariable();
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
			case WorkflowPackage.PROCESS_STATE__SUBPROCESS:
				setSubprocess((com.bluexml.side.workflow.Process)newValue);
				return;
			case WorkflowPackage.PROCESS_STATE__VARIABLE:
				getVariable().clear();
				getVariable().addAll((Collection<? extends Variable>)newValue);
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
			case WorkflowPackage.PROCESS_STATE__SUBPROCESS:
				setSubprocess((com.bluexml.side.workflow.Process)null);
				return;
			case WorkflowPackage.PROCESS_STATE__VARIABLE:
				getVariable().clear();
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
			case WorkflowPackage.PROCESS_STATE__SUBPROCESS:
				return subprocess != null;
			case WorkflowPackage.PROCESS_STATE__VARIABLE:
				return variable != null && !variable.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProcessStateImpl
