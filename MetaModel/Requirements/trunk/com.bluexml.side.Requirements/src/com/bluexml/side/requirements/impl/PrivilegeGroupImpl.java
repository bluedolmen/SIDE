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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.Privilege;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.RequirementsPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Privilege Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.impl.PrivilegeGroupImpl#getEntryPoint <em>Entry Point</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.PrivilegeGroupImpl#getPrivileges <em>Privileges</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.PrivilegeGroupImpl#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PrivilegeGroupImpl extends ModelElementImpl implements PrivilegeGroup {
	/**
	 * The cached value of the '{@link #getEntryPoint() <em>Entry Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntryPoint()
	 * @generated
	 * @ordered
	 */
	protected Entity entryPoint;

	/**
	 * The cached value of the '{@link #getPrivileges() <em>Privileges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivileges()
	 * @generated
	 * @ordered
	 */
	protected EList<Privilege> privileges;

	/**
	 * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected String documentation = DOCUMENTATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrivilegeGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.PRIVILEGE_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getEntryPoint() {
		if (entryPoint != null && entryPoint.eIsProxy()) {
			InternalEObject oldEntryPoint = (InternalEObject)entryPoint;
			entryPoint = (Entity)eResolveProxy(oldEntryPoint);
			if (entryPoint != oldEntryPoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RequirementsPackage.PRIVILEGE_GROUP__ENTRY_POINT, oldEntryPoint, entryPoint));
			}
		}
		return entryPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetEntryPoint() {
		return entryPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntryPoint(Entity newEntryPoint) {
		Entity oldEntryPoint = entryPoint;
		entryPoint = newEntryPoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.PRIVILEGE_GROUP__ENTRY_POINT, oldEntryPoint, entryPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Privilege> getPrivileges() {
		if (privileges == null) {
			privileges = new EObjectContainmentEList<Privilege>(Privilege.class, this, RequirementsPackage.PRIVILEGE_GROUP__PRIVILEGES);
		}
		return privileges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(String newDocumentation) {
		String oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.PRIVILEGE_GROUP__DOCUMENTATION, oldDocumentation, documentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RequirementsPackage.PRIVILEGE_GROUP__PRIVILEGES:
				return ((InternalEList<?>)getPrivileges()).basicRemove(otherEnd, msgs);
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
			case RequirementsPackage.PRIVILEGE_GROUP__ENTRY_POINT:
				if (resolve) return getEntryPoint();
				return basicGetEntryPoint();
			case RequirementsPackage.PRIVILEGE_GROUP__PRIVILEGES:
				return getPrivileges();
			case RequirementsPackage.PRIVILEGE_GROUP__DOCUMENTATION:
				return getDocumentation();
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
			case RequirementsPackage.PRIVILEGE_GROUP__ENTRY_POINT:
				setEntryPoint((Entity)newValue);
				return;
			case RequirementsPackage.PRIVILEGE_GROUP__PRIVILEGES:
				getPrivileges().clear();
				getPrivileges().addAll((Collection<? extends Privilege>)newValue);
				return;
			case RequirementsPackage.PRIVILEGE_GROUP__DOCUMENTATION:
				setDocumentation((String)newValue);
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
			case RequirementsPackage.PRIVILEGE_GROUP__ENTRY_POINT:
				setEntryPoint((Entity)null);
				return;
			case RequirementsPackage.PRIVILEGE_GROUP__PRIVILEGES:
				getPrivileges().clear();
				return;
			case RequirementsPackage.PRIVILEGE_GROUP__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
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
			case RequirementsPackage.PRIVILEGE_GROUP__ENTRY_POINT:
				return entryPoint != null;
			case RequirementsPackage.PRIVILEGE_GROUP__PRIVILEGES:
				return privileges != null && !privileges.isEmpty();
			case RequirementsPackage.PRIVILEGE_GROUP__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
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
		result.append(" (documentation: ");
		result.append(documentation);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //PrivilegeGroupImpl
