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

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.UserTask;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.UserTaskImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.UserTaskImpl#getClazz <em>Clazz</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.UserTaskImpl#getAdvancedTaskDefinition <em>Advanced Task Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserTaskImpl extends TransitionTaskImpl implements UserTask {
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
	 * The cached value of the '{@link #getAdvancedTaskDefinition() <em>Advanced Task Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdvancedTaskDefinition()
	 * @generated
	 * @ordered
	 */
	protected Clazz advancedTaskDefinition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.USER_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this, WorkflowPackage.USER_TASK__ATTRIBUTES);
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
			clazz = new EObjectResolvingEList<Clazz>(Clazz.class, this, WorkflowPackage.USER_TASK__CLAZZ);
		}
		return clazz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz getAdvancedTaskDefinition() {
		if (advancedTaskDefinition != null && advancedTaskDefinition.eIsProxy()) {
			InternalEObject oldAdvancedTaskDefinition = (InternalEObject)advancedTaskDefinition;
			advancedTaskDefinition = (Clazz)eResolveProxy(oldAdvancedTaskDefinition);
			if (advancedTaskDefinition != oldAdvancedTaskDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkflowPackage.USER_TASK__ADVANCED_TASK_DEFINITION, oldAdvancedTaskDefinition, advancedTaskDefinition));
			}
		}
		return advancedTaskDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz basicGetAdvancedTaskDefinition() {
		return advancedTaskDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdvancedTaskDefinition(Clazz newAdvancedTaskDefinition) {
		Clazz oldAdvancedTaskDefinition = advancedTaskDefinition;
		advancedTaskDefinition = newAdvancedTaskDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.USER_TASK__ADVANCED_TASK_DEFINITION, oldAdvancedTaskDefinition, advancedTaskDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.USER_TASK__ATTRIBUTES:
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
			case WorkflowPackage.USER_TASK__ATTRIBUTES:
				return getAttributes();
			case WorkflowPackage.USER_TASK__CLAZZ:
				return getClazz();
			case WorkflowPackage.USER_TASK__ADVANCED_TASK_DEFINITION:
				if (resolve) return getAdvancedTaskDefinition();
				return basicGetAdvancedTaskDefinition();
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
			case WorkflowPackage.USER_TASK__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends Attribute>)newValue);
				return;
			case WorkflowPackage.USER_TASK__CLAZZ:
				getClazz().clear();
				getClazz().addAll((Collection<? extends Clazz>)newValue);
				return;
			case WorkflowPackage.USER_TASK__ADVANCED_TASK_DEFINITION:
				setAdvancedTaskDefinition((Clazz)newValue);
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
			case WorkflowPackage.USER_TASK__ATTRIBUTES:
				getAttributes().clear();
				return;
			case WorkflowPackage.USER_TASK__CLAZZ:
				getClazz().clear();
				return;
			case WorkflowPackage.USER_TASK__ADVANCED_TASK_DEFINITION:
				setAdvancedTaskDefinition((Clazz)null);
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
			case WorkflowPackage.USER_TASK__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case WorkflowPackage.USER_TASK__CLAZZ:
				return clazz != null && !clazz.isEmpty();
			case WorkflowPackage.USER_TASK__ADVANCED_TASK_DEFINITION:
				return advancedTaskDefinition != null;
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //UserTaskImpl
