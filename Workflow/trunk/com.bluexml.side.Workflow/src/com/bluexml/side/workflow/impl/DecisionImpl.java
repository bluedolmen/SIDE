/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.impl;

import com.bluexml.side.workflow.Decision;
import com.bluexml.side.workflow.Event;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.WorkflowPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Decision</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.DecisionImpl#getTransitioncondition <em>Transitioncondition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.DecisionImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.DecisionImpl#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DecisionImpl extends StateImpl implements Decision {
	/**
	 * The default value of the '{@link #getTransitioncondition() <em>Transitioncondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitioncondition()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSITIONCONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransitioncondition() <em>Transitioncondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitioncondition()
	 * @generated
	 * @ordered
	 */
	protected String transitioncondition = TRANSITIONCONDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTransition() <em>Transition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransition()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> transition;

	/**
	 * The cached value of the '{@link #getEvent() <em>Event</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvent()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> event;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DecisionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.DECISION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransitioncondition() {
		return transitioncondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitioncondition(String newTransitioncondition) {
		String oldTransitioncondition = transitioncondition;
		transitioncondition = newTransitioncondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.DECISION__TRANSITIONCONDITION, oldTransitioncondition, transitioncondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transition> getTransition() {
		if (transition == null) {
			transition = new EObjectContainmentEList<Transition>(Transition.class, this, WorkflowPackage.DECISION__TRANSITION);
		}
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getEvent() {
		if (event == null) {
			event = new EObjectContainmentEList<Event>(Event.class, this, WorkflowPackage.DECISION__EVENT);
		}
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.DECISION__TRANSITION:
				return ((InternalEList<?>)getTransition()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.DECISION__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
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
			case WorkflowPackage.DECISION__TRANSITIONCONDITION:
				return getTransitioncondition();
			case WorkflowPackage.DECISION__TRANSITION:
				return getTransition();
			case WorkflowPackage.DECISION__EVENT:
				return getEvent();
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
			case WorkflowPackage.DECISION__TRANSITIONCONDITION:
				setTransitioncondition((String)newValue);
				return;
			case WorkflowPackage.DECISION__TRANSITION:
				getTransition().clear();
				getTransition().addAll((Collection<? extends Transition>)newValue);
				return;
			case WorkflowPackage.DECISION__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends Event>)newValue);
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
			case WorkflowPackage.DECISION__TRANSITIONCONDITION:
				setTransitioncondition(TRANSITIONCONDITION_EDEFAULT);
				return;
			case WorkflowPackage.DECISION__TRANSITION:
				getTransition().clear();
				return;
			case WorkflowPackage.DECISION__EVENT:
				getEvent().clear();
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
			case WorkflowPackage.DECISION__TRANSITIONCONDITION:
				return TRANSITIONCONDITION_EDEFAULT == null ? transitioncondition != null : !TRANSITIONCONDITION_EDEFAULT.equals(transitioncondition);
			case WorkflowPackage.DECISION__TRANSITION:
				return transition != null && !transition.isEmpty();
			case WorkflowPackage.DECISION__EVENT:
				return event != null && !event.isEmpty();
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
		result.append(" (transitioncondition: ");
		result.append(transitioncondition);
		result.append(')');
		return result.toString();
	}

} //DecisionImpl
