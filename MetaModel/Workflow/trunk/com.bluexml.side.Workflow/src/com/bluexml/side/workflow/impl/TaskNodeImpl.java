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
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
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
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.TaskNodeImpl#getSwimlane <em>Swimlane</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskNodeImpl extends UserTaskImpl implements TaskNode {
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
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
			case WorkflowPackage.TASK_NODE__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				return basicSetSwimlane(null, msgs);
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
			case WorkflowPackage.TASK_NODE__TIMER:
				return getTimer();
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				if (resolve) return getSwimlane();
				return basicGetSwimlane();
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
			case WorkflowPackage.TASK_NODE__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends Timer>)newValue);
				return;
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				setSwimlane((Swimlane)newValue);
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
			case WorkflowPackage.TASK_NODE__TIMER:
				getTimer().clear();
				return;
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				setSwimlane((Swimlane)null);
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
			case WorkflowPackage.TASK_NODE__TIMER:
				return timer != null && !timer.isEmpty();
			case WorkflowPackage.TASK_NODE__SWIMLANE:
				return swimlane != null;
		}
		return super.eIsSet(featureID);
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //TaskNodeImpl
