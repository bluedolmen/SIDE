/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ecore.OCL;
import com.bluexml.side.requirements.Agent;
import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.GoalStep;
import com.bluexml.side.requirements.PriorityLevel;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.RequirementsPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.impl.GoalImpl#getSubgoals <em>Subgoals</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.GoalImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.GoalImpl#getResponsible <em>Responsible</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.GoalImpl#getPrivilegeGroup <em>Privilege Group</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.GoalImpl#getStep <em>Step</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.GoalImpl#getSynopsis <em>Synopsis</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GoalImpl extends AnnotableElementImpl implements Goal {
	/**
	 * The cached value of the '{@link #getSubgoals() <em>Subgoals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubgoals()
	 * @generated
	 * @ordered
	 */
	protected EList<Goal> subgoals;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final PriorityLevel PRIORITY_EDEFAULT = PriorityLevel.VERY_HIGH;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected PriorityLevel priority = PRIORITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResponsible() <em>Responsible</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsible()
	 * @generated
	 * @ordered
	 */
	protected EList<Agent> responsible;

	/**
	 * The cached value of the '{@link #getPrivilegeGroup() <em>Privilege Group</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivilegeGroup()
	 * @generated
	 * @ordered
	 */
	protected EList<PrivilegeGroup> privilegeGroup;

	/**
	 * The cached value of the '{@link #getStep() <em>Step</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStep()
	 * @generated
	 * @ordered
	 */
	protected EList<GoalStep> step;

	/**
	 * The default value of the '{@link #getSynopsis() <em>Synopsis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynopsis()
	 * @generated
	 * @ordered
	 */
	protected static final String SYNOPSIS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSynopsis() <em>Synopsis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynopsis()
	 * @generated
	 * @ordered
	 */
	protected String synopsis = SYNOPSIS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GoalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.GOAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Goal> getSubgoals() {
		if (subgoals == null) {
			subgoals = new EObjectContainmentEList<Goal>(Goal.class, this, RequirementsPackage.GOAL__SUBGOALS);
		}
		return subgoals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PriorityLevel getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(PriorityLevel newPriority) {
		PriorityLevel oldPriority = priority;
		priority = newPriority == null ? PRIORITY_EDEFAULT : newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.GOAL__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Agent> getResponsible() {
		if (responsible == null) {
			responsible = new EObjectWithInverseResolvingEList.ManyInverse<Agent>(Agent.class, this, RequirementsPackage.GOAL__RESPONSIBLE, RequirementsPackage.AGENT__IS_RESPONSIBLE);
		}
		return responsible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PrivilegeGroup> getPrivilegeGroup() {
		if (privilegeGroup == null) {
			privilegeGroup = new EObjectContainmentEList<PrivilegeGroup>(PrivilegeGroup.class, this, RequirementsPackage.GOAL__PRIVILEGE_GROUP);
		}
		return privilegeGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GoalStep> getStep() {
		if (step == null) {
			step = new EObjectContainmentEList<GoalStep>(GoalStep.class, this, RequirementsPackage.GOAL__STEP);
		}
		return step;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynopsis(String newSynopsis) {
		String oldSynopsis = synopsis;
		synopsis = newSynopsis;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.GOAL__SYNOPSIS, oldSynopsis, synopsis));
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
			case RequirementsPackage.GOAL__RESPONSIBLE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResponsible()).basicAdd(otherEnd, msgs);
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
			case RequirementsPackage.GOAL__SUBGOALS:
				return ((InternalEList<?>)getSubgoals()).basicRemove(otherEnd, msgs);
			case RequirementsPackage.GOAL__RESPONSIBLE:
				return ((InternalEList<?>)getResponsible()).basicRemove(otherEnd, msgs);
			case RequirementsPackage.GOAL__PRIVILEGE_GROUP:
				return ((InternalEList<?>)getPrivilegeGroup()).basicRemove(otherEnd, msgs);
			case RequirementsPackage.GOAL__STEP:
				return ((InternalEList<?>)getStep()).basicRemove(otherEnd, msgs);
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
			case RequirementsPackage.GOAL__SUBGOALS:
				return getSubgoals();
			case RequirementsPackage.GOAL__PRIORITY:
				return getPriority();
			case RequirementsPackage.GOAL__RESPONSIBLE:
				return getResponsible();
			case RequirementsPackage.GOAL__PRIVILEGE_GROUP:
				return getPrivilegeGroup();
			case RequirementsPackage.GOAL__STEP:
				return getStep();
			case RequirementsPackage.GOAL__SYNOPSIS:
				return getSynopsis();
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
			case RequirementsPackage.GOAL__SUBGOALS:
				getSubgoals().clear();
				getSubgoals().addAll((Collection<? extends Goal>)newValue);
				return;
			case RequirementsPackage.GOAL__PRIORITY:
				setPriority((PriorityLevel)newValue);
				return;
			case RequirementsPackage.GOAL__RESPONSIBLE:
				getResponsible().clear();
				getResponsible().addAll((Collection<? extends Agent>)newValue);
				return;
			case RequirementsPackage.GOAL__PRIVILEGE_GROUP:
				getPrivilegeGroup().clear();
				getPrivilegeGroup().addAll((Collection<? extends PrivilegeGroup>)newValue);
				return;
			case RequirementsPackage.GOAL__STEP:
				getStep().clear();
				getStep().addAll((Collection<? extends GoalStep>)newValue);
				return;
			case RequirementsPackage.GOAL__SYNOPSIS:
				setSynopsis((String)newValue);
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
			case RequirementsPackage.GOAL__SUBGOALS:
				getSubgoals().clear();
				return;
			case RequirementsPackage.GOAL__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case RequirementsPackage.GOAL__RESPONSIBLE:
				getResponsible().clear();
				return;
			case RequirementsPackage.GOAL__PRIVILEGE_GROUP:
				getPrivilegeGroup().clear();
				return;
			case RequirementsPackage.GOAL__STEP:
				getStep().clear();
				return;
			case RequirementsPackage.GOAL__SYNOPSIS:
				setSynopsis(SYNOPSIS_EDEFAULT);
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
			case RequirementsPackage.GOAL__SUBGOALS:
				return subgoals != null && !subgoals.isEmpty();
			case RequirementsPackage.GOAL__PRIORITY:
				return priority != PRIORITY_EDEFAULT;
			case RequirementsPackage.GOAL__RESPONSIBLE:
				return responsible != null && !responsible.isEmpty();
			case RequirementsPackage.GOAL__PRIVILEGE_GROUP:
				return privilegeGroup != null && !privilegeGroup.isEmpty();
			case RequirementsPackage.GOAL__STEP:
				return step != null && !step.isEmpty();
			case RequirementsPackage.GOAL__SYNOPSIS:
				return SYNOPSIS_EDEFAULT == null ? synopsis != null : !SYNOPSIS_EDEFAULT.equals(synopsis);
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
		result.append(" (priority: ");
		result.append(priority);
		result.append(", synopsis: ");
		result.append(synopsis);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //GoalImpl
