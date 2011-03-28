/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty;
import com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentPropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentPropertyImpl#isCanRead <em>Can Read</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentPropertyImpl#isCanUpdate <em>Can Update</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ComponentPropertyImpl extends EObjectImpl implements ComponentProperty {
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
	 * The default value of the '{@link #isCanRead() <em>Can Read</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanRead()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CAN_READ_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCanRead() <em>Can Read</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanRead()
	 * @generated
	 * @ordered
	 */
	protected boolean canRead = CAN_READ_EDEFAULT;

	/**
	 * The default value of the '{@link #isCanUpdate() <em>Can Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanUpdate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CAN_UPDATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCanUpdate() <em>Can Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanUpdate()
	 * @generated
	 * @ordered
	 */
	protected boolean canUpdate = CAN_UPDATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebProjectPackage.Literals.COMPONENT_PROPERTY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_PROPERTY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCanRead() {
		return canRead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanRead(boolean newCanRead) {
		boolean oldCanRead = canRead;
		canRead = newCanRead;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_PROPERTY__CAN_READ, oldCanRead, canRead));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCanUpdate() {
		return canUpdate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanUpdate(boolean newCanUpdate) {
		boolean oldCanUpdate = canUpdate;
		canUpdate = newCanUpdate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_PROPERTY__CAN_UPDATE, oldCanUpdate, canUpdate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebProjectPackage.COMPONENT_PROPERTY__NAME:
				return getName();
			case WebProjectPackage.COMPONENT_PROPERTY__CAN_READ:
				return isCanRead();
			case WebProjectPackage.COMPONENT_PROPERTY__CAN_UPDATE:
				return isCanUpdate();
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
			case WebProjectPackage.COMPONENT_PROPERTY__NAME:
				setName((String)newValue);
				return;
			case WebProjectPackage.COMPONENT_PROPERTY__CAN_READ:
				setCanRead((Boolean)newValue);
				return;
			case WebProjectPackage.COMPONENT_PROPERTY__CAN_UPDATE:
				setCanUpdate((Boolean)newValue);
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
			case WebProjectPackage.COMPONENT_PROPERTY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT_PROPERTY__CAN_READ:
				setCanRead(CAN_READ_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT_PROPERTY__CAN_UPDATE:
				setCanUpdate(CAN_UPDATE_EDEFAULT);
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
			case WebProjectPackage.COMPONENT_PROPERTY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WebProjectPackage.COMPONENT_PROPERTY__CAN_READ:
				return canRead != CAN_READ_EDEFAULT;
			case WebProjectPackage.COMPONENT_PROPERTY__CAN_UPDATE:
				return canUpdate != CAN_UPDATE_EDEFAULT;
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
		result.append(", canRead: ");
		result.append(canRead);
		result.append(", canUpdate: ");
		result.append(canUpdate);
		result.append(')');
		return result.toString();
	}

} //ComponentPropertyImpl
