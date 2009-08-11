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
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.TaskNode;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Swimlane</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.SwimlaneImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.SwimlaneImpl#getManage <em>Manage</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.SwimlaneImpl#getActorid <em>Actorid</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.SwimlaneImpl#getPooledactors <em>Pooledactors</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.SwimlaneImpl#getClazz <em>Clazz</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwimlaneImpl extends WorkflowModelElementImpl implements Swimlane {
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
	 * The cached value of the '{@link #getManage() <em>Manage</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManage()
	 * @generated
	 * @ordered
	 */
	protected EList<TaskNode> manage;

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
	protected SwimlaneImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.SWIMLANE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.SWIMLANE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskNode> getManage() {
		if (manage == null) {
			manage = new EObjectWithInverseResolvingEList<TaskNode>(TaskNode.class, this, WorkflowPackage.SWIMLANE__MANAGE, WorkflowPackage.TASK_NODE__SWIMLANE);
		}
		return manage;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.SWIMLANE__ACTORID, oldActorid, actorid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.SWIMLANE__POOLEDACTORS, oldPooledactors, pooledactors));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.SWIMLANE__CLAZZ, oldClazz, clazz));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean EqualsForMerge(Swimlane other) {
		if (EqualsForMergeBodyOCL == null) {
			EOperation eOperation = WorkflowPackage.Literals.SWIMLANE.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(WorkflowPackage.Literals.SWIMLANE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				EqualsForMergeBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(EqualsForMergeBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("other", other);
	  
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #EqualsForMerge <em>Equals For Merge</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EqualsForMerge
	 * @generated
	 */
	private static OCLExpression<EClassifier> EqualsForMergeBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.SWIMLANE__MANAGE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getManage()).basicAdd(otherEnd, msgs);
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
			case WorkflowPackage.SWIMLANE__MANAGE:
				return ((InternalEList<?>)getManage()).basicRemove(otherEnd, msgs);
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
			case WorkflowPackage.SWIMLANE__NAME:
				return getName();
			case WorkflowPackage.SWIMLANE__MANAGE:
				return getManage();
			case WorkflowPackage.SWIMLANE__ACTORID:
				return getActorid();
			case WorkflowPackage.SWIMLANE__POOLEDACTORS:
				return getPooledactors();
			case WorkflowPackage.SWIMLANE__CLAZZ:
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
			case WorkflowPackage.SWIMLANE__NAME:
				setName((String)newValue);
				return;
			case WorkflowPackage.SWIMLANE__MANAGE:
				getManage().clear();
				getManage().addAll((Collection<? extends TaskNode>)newValue);
				return;
			case WorkflowPackage.SWIMLANE__ACTORID:
				setActorid((String)newValue);
				return;
			case WorkflowPackage.SWIMLANE__POOLEDACTORS:
				setPooledactors((String)newValue);
				return;
			case WorkflowPackage.SWIMLANE__CLAZZ:
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
			case WorkflowPackage.SWIMLANE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WorkflowPackage.SWIMLANE__MANAGE:
				getManage().clear();
				return;
			case WorkflowPackage.SWIMLANE__ACTORID:
				setActorid(ACTORID_EDEFAULT);
				return;
			case WorkflowPackage.SWIMLANE__POOLEDACTORS:
				setPooledactors(POOLEDACTORS_EDEFAULT);
				return;
			case WorkflowPackage.SWIMLANE__CLAZZ:
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
			case WorkflowPackage.SWIMLANE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WorkflowPackage.SWIMLANE__MANAGE:
				return manage != null && !manage.isEmpty();
			case WorkflowPackage.SWIMLANE__ACTORID:
				return ACTORID_EDEFAULT == null ? actorid != null : !ACTORID_EDEFAULT.equals(actorid);
			case WorkflowPackage.SWIMLANE__POOLEDACTORS:
				return POOLEDACTORS_EDEFAULT == null ? pooledactors != null : !POOLEDACTORS_EDEFAULT.equals(pooledactors);
			case WorkflowPackage.SWIMLANE__CLAZZ:
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
		result.append(" (name: ");
		result.append(name);
		result.append(", actorid: ");
		result.append(actorid);
		result.append(", pooledactors: ");
		result.append(pooledactors);
		result.append(", clazz: ");
		result.append(clazz);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //SwimlaneImpl
