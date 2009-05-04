/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application.impl;

import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.Option;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Componant Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.application.impl.ComponantConfigurationImpl#getId_techno_version <em>Id techno version</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ComponantConfigurationImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ComponantConfigurationImpl#getImpl_class <em>Impl class</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ComponantConfigurationImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ComponantConfigurationImpl extends EObjectImpl implements ComponantConfiguration {
	/**
	 * The default value of the '{@link #getId_techno_version() <em>Id techno version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_techno_version()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_TECHNO_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId_techno_version() <em>Id techno version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_techno_version()
	 * @generated
	 * @ordered
	 */
	protected String id_techno_version = ID_TECHNO_VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Option> options;

	/**
	 * The default value of the '{@link #getImpl_class() <em>Impl class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpl_class()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPL_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImpl_class() <em>Impl class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpl_class()
	 * @generated
	 * @ordered
	 */
	protected String impl_class = IMPL_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponantConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.COMPONANT_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId_techno_version() {
		return id_techno_version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId_techno_version(String newId_techno_version) {
		String oldId_techno_version = id_techno_version;
		id_techno_version = newId_techno_version;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.COMPONANT_CONFIGURATION__ID_TECHNO_VERSION, oldId_techno_version, id_techno_version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Option> getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList<Option>(Option.class, this, ApplicationPackage.COMPONANT_CONFIGURATION__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImpl_class() {
		return impl_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImpl_class(String newImpl_class) {
		String oldImpl_class = impl_class;
		impl_class = newImpl_class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.COMPONANT_CONFIGURATION__IMPL_CLASS, oldImpl_class, impl_class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.COMPONANT_CONFIGURATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.COMPONANT_CONFIGURATION__OPTIONS:
				return ((InternalEList<?>)getOptions()).basicRemove(otherEnd, msgs);
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
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID_TECHNO_VERSION:
				return getId_techno_version();
			case ApplicationPackage.COMPONANT_CONFIGURATION__OPTIONS:
				return getOptions();
			case ApplicationPackage.COMPONANT_CONFIGURATION__IMPL_CLASS:
				return getImpl_class();
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID:
				return getId();
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
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID_TECHNO_VERSION:
				setId_techno_version((String)newValue);
				return;
			case ApplicationPackage.COMPONANT_CONFIGURATION__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection<? extends Option>)newValue);
				return;
			case ApplicationPackage.COMPONANT_CONFIGURATION__IMPL_CLASS:
				setImpl_class((String)newValue);
				return;
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID:
				setId((String)newValue);
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
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID_TECHNO_VERSION:
				setId_techno_version(ID_TECHNO_VERSION_EDEFAULT);
				return;
			case ApplicationPackage.COMPONANT_CONFIGURATION__OPTIONS:
				getOptions().clear();
				return;
			case ApplicationPackage.COMPONANT_CONFIGURATION__IMPL_CLASS:
				setImpl_class(IMPL_CLASS_EDEFAULT);
				return;
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID:
				setId(ID_EDEFAULT);
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
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID_TECHNO_VERSION:
				return ID_TECHNO_VERSION_EDEFAULT == null ? id_techno_version != null : !ID_TECHNO_VERSION_EDEFAULT.equals(id_techno_version);
			case ApplicationPackage.COMPONANT_CONFIGURATION__OPTIONS:
				return options != null && !options.isEmpty();
			case ApplicationPackage.COMPONANT_CONFIGURATION__IMPL_CLASS:
				return IMPL_CLASS_EDEFAULT == null ? impl_class != null : !IMPL_CLASS_EDEFAULT.equals(impl_class);
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (id_techno_version: ");
		result.append(id_techno_version);
		result.append(", impl_class: ");
		result.append(impl_class);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ComponantConfigurationImpl
