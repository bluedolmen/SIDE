/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.bluexml.side.workflow.Assignment;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.AssignmentImpl#getActorid <em>Actorid</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.AssignmentImpl#getPooledactors <em>Pooledactors</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.AssignmentImpl#getClazz <em>Clazz</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentImpl extends EObjectImpl implements Assignment {
	/**
	 * The default value of the '{@link #getActorid() <em>Actorid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActorid()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTORID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActorid() <em>Actorid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActorid()
	 * @generated
	 * @ordered
	 */
	protected String actorid = ACTORID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPooledactors() <em>Pooledactors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPooledactors()
	 * @generated
	 * @ordered
	 */
	protected static final String POOLEDACTORS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPooledactors() <em>Pooledactors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPooledactors()
	 * @generated
	 * @ordered
	 */
	protected String pooledactors = POOLEDACTORS_EDEFAULT;

	/**
	 * The default value of the '{@link #getClazz() <em>Clazz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClazz()
	 * @generated
	 * @ordered
	 */
	protected static final String CLAZZ_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClazz() <em>Clazz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClazz()
	 * @generated
	 * @ordered
	 */
	protected String clazz = CLAZZ_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssignmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.ASSIGNMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActorid() {
		return actorid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActorid(String newActorid) {
		String oldActorid = actorid;
		actorid = newActorid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.ASSIGNMENT__ACTORID, oldActorid, actorid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPooledactors() {
		return pooledactors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPooledactors(String newPooledactors) {
		String oldPooledactors = pooledactors;
		pooledactors = newPooledactors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.ASSIGNMENT__POOLEDACTORS, oldPooledactors, pooledactors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClazz(String newClazz) {
		String oldClazz = clazz;
		clazz = newClazz;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.ASSIGNMENT__CLAZZ, oldClazz, clazz));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WorkflowPackage.ASSIGNMENT__ACTORID:
				return getActorid();
			case WorkflowPackage.ASSIGNMENT__POOLEDACTORS:
				return getPooledactors();
			case WorkflowPackage.ASSIGNMENT__CLAZZ:
				return getClazz();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WorkflowPackage.ASSIGNMENT__ACTORID:
				setActorid((String)newValue);
				return;
			case WorkflowPackage.ASSIGNMENT__POOLEDACTORS:
				setPooledactors((String)newValue);
				return;
			case WorkflowPackage.ASSIGNMENT__CLAZZ:
				setClazz((String)newValue);
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
			case WorkflowPackage.ASSIGNMENT__ACTORID:
				setActorid(ACTORID_EDEFAULT);
				return;
			case WorkflowPackage.ASSIGNMENT__POOLEDACTORS:
				setPooledactors(POOLEDACTORS_EDEFAULT);
				return;
			case WorkflowPackage.ASSIGNMENT__CLAZZ:
				setClazz(CLAZZ_EDEFAULT);
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
			case WorkflowPackage.ASSIGNMENT__ACTORID:
				return ACTORID_EDEFAULT == null ? actorid != null : !ACTORID_EDEFAULT.equals(actorid);
			case WorkflowPackage.ASSIGNMENT__POOLEDACTORS:
				return POOLEDACTORS_EDEFAULT == null ? pooledactors != null : !POOLEDACTORS_EDEFAULT.equals(pooledactors);
			case WorkflowPackage.ASSIGNMENT__CLAZZ:
				return CLAZZ_EDEFAULT == null ? clazz != null : !CLAZZ_EDEFAULT.equals(clazz);
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
		result.append(" (actorid: ");
		result.append(actorid);
		result.append(", pooledactors: ");
		result.append(pooledactors);
		result.append(", clazz: ");
		result.append(clazz);
		result.append(')');
		return result.toString();
	}

} //AssignmentImpl
