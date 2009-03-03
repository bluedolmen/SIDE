/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.impl;

import com.bluexml.side.workflow.Action;
import com.bluexml.side.workflow.Event;
import com.bluexml.side.workflow.Script;
import com.bluexml.side.workflow.Timer;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.ActionImpl#getJavaClass <em>Java Class</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ActionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ActionImpl#getScript <em>Script</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ActionImpl#getParentEvent <em>Parent Event</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ActionImpl#getParentTimer <em>Parent Timer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionImpl extends EObjectImpl implements Action {
	/**
	 * The default value of the '{@link #getJavaClass() <em>Java Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaClass()
	 * @generated
	 * @ordered
	 */
	protected static final String JAVA_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaClass() <em>Java Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaClass()
	 * @generated
	 * @ordered
	 */
	protected String javaClass = JAVA_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected String expression = EXPRESSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected EList<Script> script;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJavaClass() {
		return javaClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaClass(String newJavaClass) {
		String oldJavaClass = javaClass;
		javaClass = newJavaClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.ACTION__JAVA_CLASS, oldJavaClass, javaClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(String newExpression) {
		String oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.ACTION__EXPRESSION, oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Script> getScript() {
		if (script == null) {
			script = new EObjectContainmentEList<Script>(Script.class, this, WorkflowPackage.ACTION__SCRIPT);
		}
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event getParentEvent() {
		if (eContainerFeatureID != WorkflowPackage.ACTION__PARENT_EVENT) return null;
		return (Event)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentEvent(Event newParentEvent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParentEvent, WorkflowPackage.ACTION__PARENT_EVENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentEvent(Event newParentEvent) {
		if (newParentEvent != eInternalContainer() || (eContainerFeatureID != WorkflowPackage.ACTION__PARENT_EVENT && newParentEvent != null)) {
			if (EcoreUtil.isAncestor(this, newParentEvent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParentEvent != null)
				msgs = ((InternalEObject)newParentEvent).eInverseAdd(this, WorkflowPackage.EVENT__ACTION, Event.class, msgs);
			msgs = basicSetParentEvent(newParentEvent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.ACTION__PARENT_EVENT, newParentEvent, newParentEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Timer getParentTimer() {
		if (eContainerFeatureID != WorkflowPackage.ACTION__PARENT_TIMER) return null;
		return (Timer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentTimer(Timer newParentTimer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParentTimer, WorkflowPackage.ACTION__PARENT_TIMER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentTimer(Timer newParentTimer) {
		if (newParentTimer != eInternalContainer() || (eContainerFeatureID != WorkflowPackage.ACTION__PARENT_TIMER && newParentTimer != null)) {
			if (EcoreUtil.isAncestor(this, newParentTimer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParentTimer != null)
				msgs = ((InternalEObject)newParentTimer).eInverseAdd(this, WorkflowPackage.TIMER__ACTION, Timer.class, msgs);
			msgs = basicSetParentTimer(newParentTimer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.ACTION__PARENT_TIMER, newParentTimer, newParentTimer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.ACTION__PARENT_EVENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParentEvent((Event)otherEnd, msgs);
			case WorkflowPackage.ACTION__PARENT_TIMER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParentTimer((Timer)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.ACTION__SCRIPT:
				return ((InternalEList<?>)getScript()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.ACTION__PARENT_EVENT:
				return basicSetParentEvent(null, msgs);
			case WorkflowPackage.ACTION__PARENT_TIMER:
				return basicSetParentTimer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case WorkflowPackage.ACTION__PARENT_EVENT:
				return eInternalContainer().eInverseRemove(this, WorkflowPackage.EVENT__ACTION, Event.class, msgs);
			case WorkflowPackage.ACTION__PARENT_TIMER:
				return eInternalContainer().eInverseRemove(this, WorkflowPackage.TIMER__ACTION, Timer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WorkflowPackage.ACTION__JAVA_CLASS:
				return getJavaClass();
			case WorkflowPackage.ACTION__EXPRESSION:
				return getExpression();
			case WorkflowPackage.ACTION__SCRIPT:
				return getScript();
			case WorkflowPackage.ACTION__PARENT_EVENT:
				return getParentEvent();
			case WorkflowPackage.ACTION__PARENT_TIMER:
				return getParentTimer();
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
			case WorkflowPackage.ACTION__JAVA_CLASS:
				setJavaClass((String)newValue);
				return;
			case WorkflowPackage.ACTION__EXPRESSION:
				setExpression((String)newValue);
				return;
			case WorkflowPackage.ACTION__SCRIPT:
				getScript().clear();
				getScript().addAll((Collection<? extends Script>)newValue);
				return;
			case WorkflowPackage.ACTION__PARENT_EVENT:
				setParentEvent((Event)newValue);
				return;
			case WorkflowPackage.ACTION__PARENT_TIMER:
				setParentTimer((Timer)newValue);
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
			case WorkflowPackage.ACTION__JAVA_CLASS:
				setJavaClass(JAVA_CLASS_EDEFAULT);
				return;
			case WorkflowPackage.ACTION__EXPRESSION:
				setExpression(EXPRESSION_EDEFAULT);
				return;
			case WorkflowPackage.ACTION__SCRIPT:
				getScript().clear();
				return;
			case WorkflowPackage.ACTION__PARENT_EVENT:
				setParentEvent((Event)null);
				return;
			case WorkflowPackage.ACTION__PARENT_TIMER:
				setParentTimer((Timer)null);
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
			case WorkflowPackage.ACTION__JAVA_CLASS:
				return JAVA_CLASS_EDEFAULT == null ? javaClass != null : !JAVA_CLASS_EDEFAULT.equals(javaClass);
			case WorkflowPackage.ACTION__EXPRESSION:
				return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
			case WorkflowPackage.ACTION__SCRIPT:
				return script != null && !script.isEmpty();
			case WorkflowPackage.ACTION__PARENT_EVENT:
				return getParentEvent() != null;
			case WorkflowPackage.ACTION__PARENT_TIMER:
				return getParentTimer() != null;
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
		result.append(" (javaClass: ");
		result.append(javaClass);
		result.append(", expression: ");
		result.append(expression);
		result.append(')');
		return result.toString();
	}

} //ActionImpl
