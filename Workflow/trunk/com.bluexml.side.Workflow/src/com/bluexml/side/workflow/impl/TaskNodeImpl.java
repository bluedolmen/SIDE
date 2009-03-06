/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.Event;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.TaskNode;
import com.bluexml.side.workflow.Timer;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getClazz <em>Clazz</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskNodeImpl extends StateImpl implements TaskNode {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getTimer() <em>Timer</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimer()
	 * @generated
	 * @ordered
	 */
	protected EList<Timer> timer;

	/**
	 * The cached value of the '{@link #getSwimlane() <em>Swimlane</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwimlane()
	 * @generated
	 * @ordered
	 */
	protected Swimlane swimlane;

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
	 * The cached value of the '{@link #getClazz() <em>Clazz</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClazz()
	 * @generated
	 * @ordered
	 */
	protected EList<Clazz> clazz;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.TASK_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.TASK_NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transition> getTransition() {
		if (transition == null) {
			transition = new EObjectContainmentWithInverseEList<Transition>(Transition.class, this, WorkflowPackage.TASK_NODE__TRANSITION, WorkflowPackage.TRANSITION__PARENT_TASK_NODE);
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
			event = new EObjectContainmentEList<Event>(Event.class, this, WorkflowPackage.TASK_NODE__EVENT);
		}
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Timer> getTimer() {
		if (timer == null) {
			timer = new EObjectContainmentEList<Timer>(Timer.class, this, WorkflowPackage.TASK_NODE__TIMER);
		}
		return timer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Swimlane getSwimlane() {
		if (swimlane != null && swimlane.eIsProxy()) {
			InternalEObject oldSwimlane = (InternalEObject)swimlane;
			swimlane = (Swimlane)eResolveProxy(oldSwimlane);
			if (swimlane != oldSwimlane) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkflowPackage.TASK_NODE__SWIMLANE, oldSwimlane, swimlane));
			}
		}
		return swimlane;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Swimlane basicGetSwimlane() {
		return swimlane;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSwimlane(Swimlane newSwimlane, NotificationChain msgs) {
		Swimlane oldSwimlane = swimlane;
		swimlane = newSwimlane;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WorkflowPackage.TASK_NODE__SWIMLANE, oldSwimlane, newSwimlane);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwimlane(Swimlane newSwimlane) {
		if (newSwimlane != swimlane) {
			NotificationChain msgs = null;
			if (swimlane != null)
				msgs = ((InternalEObject)swimlane).eInverseRemove(this, WorkflowPackage.SWIMLANE__MANAGE, Swimlane.class, msgs);
			if (newSwimlane != null)
				msgs = ((InternalEObject)newSwimlane).eInverseAdd(this, WorkflowPackage.SWIMLANE__MANAGE, Swimlane.class, msgs);
			msgs = basicSetSwimlane(newSwimlane, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.TASK_NODE__SWIMLANE, newSwimlane, newSwimlane));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this, WorkflowPackage.TASK_NODE__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getClazz() {
		if (clazz == null) {
			clazz = new EObjectResolvingEList<Clazz>(Clazz.class, this, WorkflowPackage.TASK_NODE__CLAZZ);
		}
		return clazz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.TASK_NODE__TRANSITION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTransition()).basicAdd(otherEnd, msgs);
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				if (swimlane != null)
					msgs = ((InternalEObject)swimlane).eInverseRemove(this, WorkflowPackage.SWIMLANE__MANAGE, Swimlane.class, msgs);
				return basicSetSwimlane((Swimlane)otherEnd, msgs);
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
			case WorkflowPackage.TASK_NODE__TRANSITION:
				return ((InternalEList<?>)getTransition()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.TASK_NODE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.TASK_NODE__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				return basicSetSwimlane(null, msgs);
			case WorkflowPackage.TASK_NODE__ATTRIBUTES:
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
			case WorkflowPackage.TASK_NODE__NAME:
				return getName();
			case WorkflowPackage.TASK_NODE__TRANSITION:
				return getTransition();
			case WorkflowPackage.TASK_NODE__EVENT:
				return getEvent();
			case WorkflowPackage.TASK_NODE__TIMER:
				return getTimer();
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				if (resolve) return getSwimlane();
				return basicGetSwimlane();
			case WorkflowPackage.TASK_NODE__ATTRIBUTES:
				return getAttributes();
			case WorkflowPackage.TASK_NODE__CLAZZ:
				return getClazz();
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
			case WorkflowPackage.TASK_NODE__NAME:
				setName((String)newValue);
				return;
			case WorkflowPackage.TASK_NODE__TRANSITION:
				getTransition().clear();
				getTransition().addAll((Collection<? extends Transition>)newValue);
				return;
			case WorkflowPackage.TASK_NODE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends Event>)newValue);
				return;
			case WorkflowPackage.TASK_NODE__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends Timer>)newValue);
				return;
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				setSwimlane((Swimlane)newValue);
				return;
			case WorkflowPackage.TASK_NODE__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends Attribute>)newValue);
				return;
			case WorkflowPackage.TASK_NODE__CLAZZ:
				getClazz().clear();
				getClazz().addAll((Collection<? extends Clazz>)newValue);
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
			case WorkflowPackage.TASK_NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WorkflowPackage.TASK_NODE__TRANSITION:
				getTransition().clear();
				return;
			case WorkflowPackage.TASK_NODE__EVENT:
				getEvent().clear();
				return;
			case WorkflowPackage.TASK_NODE__TIMER:
				getTimer().clear();
				return;
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				setSwimlane((Swimlane)null);
				return;
			case WorkflowPackage.TASK_NODE__ATTRIBUTES:
				getAttributes().clear();
				return;
			case WorkflowPackage.TASK_NODE__CLAZZ:
				getClazz().clear();
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
			case WorkflowPackage.TASK_NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WorkflowPackage.TASK_NODE__TRANSITION:
				return transition != null && !transition.isEmpty();
			case WorkflowPackage.TASK_NODE__EVENT:
				return event != null && !event.isEmpty();
			case WorkflowPackage.TASK_NODE__TIMER:
				return timer != null && !timer.isEmpty();
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				return swimlane != null;
			case WorkflowPackage.TASK_NODE__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case WorkflowPackage.TASK_NODE__CLAZZ:
				return clazz != null && !clazz.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TaskNodeImpl
