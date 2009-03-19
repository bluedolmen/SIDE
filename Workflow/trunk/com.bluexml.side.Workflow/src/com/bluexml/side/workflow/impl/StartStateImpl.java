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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.BPMAssignmentType;
import com.bluexml.side.workflow.Event;
import com.bluexml.side.workflow.StartState;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.StartStateImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.StartStateImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.StartStateImpl#getAssignmentType <em>Assignment Type</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.StartStateImpl#getInitiator <em>Initiator</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.StartStateImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.StartStateImpl#getClazz <em>Clazz</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StartStateImpl extends StateImpl implements StartState {
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
	 * The default value of the '{@link #getAssignmentType() <em>Assignment Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignmentType()
	 * @generated
	 * @ordered
	 */
	protected static final BPMAssignmentType ASSIGNMENT_TYPE_EDEFAULT = BPMAssignmentType.ONE_USER;

	/**
	 * The cached value of the '{@link #getAssignmentType() <em>Assignment Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignmentType()
	 * @generated
	 * @ordered
	 */
	protected BPMAssignmentType assignmentType = ASSIGNMENT_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInitiator() <em>Initiator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitiator()
	 * @generated
	 * @ordered
	 */
	protected Swimlane initiator;

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
	protected StartStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.START_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transition> getTransition() {
		if (transition == null) {
			transition = new EObjectContainmentEList<Transition>(Transition.class, this, WorkflowPackage.START_STATE__TRANSITION);
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
			event = new EObjectContainmentEList<Event>(Event.class, this, WorkflowPackage.START_STATE__EVENT);
		}
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BPMAssignmentType getAssignmentType() {
		return assignmentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignmentType(BPMAssignmentType newAssignmentType) {
		BPMAssignmentType oldAssignmentType = assignmentType;
		assignmentType = newAssignmentType == null ? ASSIGNMENT_TYPE_EDEFAULT : newAssignmentType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.START_STATE__ASSIGNMENT_TYPE, oldAssignmentType, assignmentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Swimlane getInitiator() {
		if (initiator != null && initiator.eIsProxy()) {
			InternalEObject oldInitiator = (InternalEObject)initiator;
			initiator = (Swimlane)eResolveProxy(oldInitiator);
			if (initiator != oldInitiator) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkflowPackage.START_STATE__INITIATOR, oldInitiator, initiator));
			}
		}
		return initiator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Swimlane basicGetInitiator() {
		return initiator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitiator(Swimlane newInitiator) {
		Swimlane oldInitiator = initiator;
		initiator = newInitiator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.START_STATE__INITIATOR, oldInitiator, initiator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this, WorkflowPackage.START_STATE__ATTRIBUTES);
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
			clazz = new EObjectResolvingEList<Clazz>(Clazz.class, this, WorkflowPackage.START_STATE__CLAZZ);
		}
		return clazz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.START_STATE__TRANSITION:
				return ((InternalEList<?>)getTransition()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.START_STATE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.START_STATE__ATTRIBUTES:
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
			case WorkflowPackage.START_STATE__TRANSITION:
				return getTransition();
			case WorkflowPackage.START_STATE__EVENT:
				return getEvent();
			case WorkflowPackage.START_STATE__ASSIGNMENT_TYPE:
				return getAssignmentType();
			case WorkflowPackage.START_STATE__INITIATOR:
				if (resolve) return getInitiator();
				return basicGetInitiator();
			case WorkflowPackage.START_STATE__ATTRIBUTES:
				return getAttributes();
			case WorkflowPackage.START_STATE__CLAZZ:
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
			case WorkflowPackage.START_STATE__TRANSITION:
				getTransition().clear();
				getTransition().addAll((Collection<? extends Transition>)newValue);
				return;
			case WorkflowPackage.START_STATE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends Event>)newValue);
				return;
			case WorkflowPackage.START_STATE__ASSIGNMENT_TYPE:
				setAssignmentType((BPMAssignmentType)newValue);
				return;
			case WorkflowPackage.START_STATE__INITIATOR:
				setInitiator((Swimlane)newValue);
				return;
			case WorkflowPackage.START_STATE__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends Attribute>)newValue);
				return;
			case WorkflowPackage.START_STATE__CLAZZ:
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
			case WorkflowPackage.START_STATE__TRANSITION:
				getTransition().clear();
				return;
			case WorkflowPackage.START_STATE__EVENT:
				getEvent().clear();
				return;
			case WorkflowPackage.START_STATE__ASSIGNMENT_TYPE:
				setAssignmentType(ASSIGNMENT_TYPE_EDEFAULT);
				return;
			case WorkflowPackage.START_STATE__INITIATOR:
				setInitiator((Swimlane)null);
				return;
			case WorkflowPackage.START_STATE__ATTRIBUTES:
				getAttributes().clear();
				return;
			case WorkflowPackage.START_STATE__CLAZZ:
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
			case WorkflowPackage.START_STATE__TRANSITION:
				return transition != null && !transition.isEmpty();
			case WorkflowPackage.START_STATE__EVENT:
				return event != null && !event.isEmpty();
			case WorkflowPackage.START_STATE__ASSIGNMENT_TYPE:
				return assignmentType != ASSIGNMENT_TYPE_EDEFAULT;
			case WorkflowPackage.START_STATE__INITIATOR:
				return initiator != null;
			case WorkflowPackage.START_STATE__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case WorkflowPackage.START_STATE__CLAZZ:
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
		result.append(" (assignmentType: ");
		result.append(assignmentType);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //StartStateImpl
