/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.bluexml.side.requirements.generator.metamodel.WebProject.Component;
import com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Table;
import com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl#isCanCreate <em>Can Create</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl#isCanRead <em>Can Read</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl#isCanUpdate <em>Can Update</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl#isCanDelete <em>Can Delete</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl#getTable <em>Table</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends EObjectImpl implements Component {
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
	 * The default value of the '{@link #isCanCreate() <em>Can Create</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanCreate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CAN_CREATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCanCreate() <em>Can Create</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanCreate()
	 * @generated
	 * @ordered
	 */
	protected boolean canCreate = CAN_CREATE_EDEFAULT;

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
	 * The default value of the '{@link #isCanDelete() <em>Can Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanDelete()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CAN_DELETE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCanDelete() <em>Can Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanDelete()
	 * @generated
	 * @ordered
	 */
	protected boolean canDelete = CAN_DELETE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected Table table;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentProperty> properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebProjectPackage.Literals.COMPONENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCanCreate() {
		return canCreate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanCreate(boolean newCanCreate) {
		boolean oldCanCreate = canCreate;
		canCreate = newCanCreate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT__CAN_CREATE, oldCanCreate, canCreate));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT__CAN_READ, oldCanRead, canRead));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT__CAN_UPDATE, oldCanUpdate, canUpdate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCanDelete() {
		return canDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanDelete(boolean newCanDelete) {
		boolean oldCanDelete = canDelete;
		canDelete = newCanDelete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT__CAN_DELETE, oldCanDelete, canDelete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getTable() {
		if (table != null && table.eIsProxy()) {
			InternalEObject oldTable = (InternalEObject)table;
			table = (Table)eResolveProxy(oldTable);
			if (table != oldTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebProjectPackage.COMPONENT__TABLE, oldTable, table));
			}
		}
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetTable() {
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(Table newTable) {
		Table oldTable = table;
		table = newTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT__TABLE, oldTable, table));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ComponentProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<ComponentProperty>(ComponentProperty.class, this, WebProjectPackage.COMPONENT__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebProjectPackage.COMPONENT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
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
			case WebProjectPackage.COMPONENT__NAME:
				return getName();
			case WebProjectPackage.COMPONENT__CAN_CREATE:
				return isCanCreate();
			case WebProjectPackage.COMPONENT__CAN_READ:
				return isCanRead();
			case WebProjectPackage.COMPONENT__CAN_UPDATE:
				return isCanUpdate();
			case WebProjectPackage.COMPONENT__CAN_DELETE:
				return isCanDelete();
			case WebProjectPackage.COMPONENT__TABLE:
				if (resolve) return getTable();
				return basicGetTable();
			case WebProjectPackage.COMPONENT__PROPERTIES:
				return getProperties();
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
			case WebProjectPackage.COMPONENT__NAME:
				setName((String)newValue);
				return;
			case WebProjectPackage.COMPONENT__CAN_CREATE:
				setCanCreate((Boolean)newValue);
				return;
			case WebProjectPackage.COMPONENT__CAN_READ:
				setCanRead((Boolean)newValue);
				return;
			case WebProjectPackage.COMPONENT__CAN_UPDATE:
				setCanUpdate((Boolean)newValue);
				return;
			case WebProjectPackage.COMPONENT__CAN_DELETE:
				setCanDelete((Boolean)newValue);
				return;
			case WebProjectPackage.COMPONENT__TABLE:
				setTable((Table)newValue);
				return;
			case WebProjectPackage.COMPONENT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ComponentProperty>)newValue);
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
			case WebProjectPackage.COMPONENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT__CAN_CREATE:
				setCanCreate(CAN_CREATE_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT__CAN_READ:
				setCanRead(CAN_READ_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT__CAN_UPDATE:
				setCanUpdate(CAN_UPDATE_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT__CAN_DELETE:
				setCanDelete(CAN_DELETE_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT__TABLE:
				setTable((Table)null);
				return;
			case WebProjectPackage.COMPONENT__PROPERTIES:
				getProperties().clear();
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
			case WebProjectPackage.COMPONENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WebProjectPackage.COMPONENT__CAN_CREATE:
				return canCreate != CAN_CREATE_EDEFAULT;
			case WebProjectPackage.COMPONENT__CAN_READ:
				return canRead != CAN_READ_EDEFAULT;
			case WebProjectPackage.COMPONENT__CAN_UPDATE:
				return canUpdate != CAN_UPDATE_EDEFAULT;
			case WebProjectPackage.COMPONENT__CAN_DELETE:
				return canDelete != CAN_DELETE_EDEFAULT;
			case WebProjectPackage.COMPONENT__TABLE:
				return table != null;
			case WebProjectPackage.COMPONENT__PROPERTIES:
				return properties != null && !properties.isEmpty();
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
		result.append(", canCreate: ");
		result.append(canCreate);
		result.append(", canRead: ");
		result.append(canRead);
		result.append(", canUpdate: ");
		result.append(canUpdate);
		result.append(", canDelete: ");
		result.append(canDelete);
		result.append(')');
		return result.toString();
	}

} //ComponentImpl
