/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application.impl;

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

import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.ConfigurationElement;
import com.bluexml.side.application.Option;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.application.impl.ConfigurationElementImpl#getId_metamodel <em>Id metamodel</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ConfigurationElementImpl#getId_generator <em>Id generator</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ConfigurationElementImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ConfigurationElementImpl#getClass_generator <em>Class generator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConfigurationElementImpl extends EObjectImpl implements ConfigurationElement {
	/**
	 * The default value of the '{@link #getId_metamodel() <em>Id metamodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_metamodel()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_METAMODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId_metamodel() <em>Id metamodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_metamodel()
	 * @generated
	 * @ordered
	 */
	protected String id_metamodel = ID_METAMODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getId_generator() <em>Id generator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_generator()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_GENERATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId_generator() <em>Id generator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_generator()
	 * @generated
	 * @ordered
	 */
	protected String id_generator = ID_GENERATOR_EDEFAULT;

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
	 * The default value of the '{@link #getClass_generator() <em>Class generator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_generator()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_GENERATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClass_generator() <em>Class generator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_generator()
	 * @generated
	 * @ordered
	 */
	protected String class_generator = CLASS_GENERATOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigurationElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.CONFIGURATION_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId_metamodel() {
		return id_metamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId_metamodel(String newId_metamodel) {
		String oldId_metamodel = id_metamodel;
		id_metamodel = newId_metamodel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.CONFIGURATION_ELEMENT__ID_METAMODEL, oldId_metamodel, id_metamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId_generator() {
		return id_generator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId_generator(String newId_generator) {
		String oldId_generator = id_generator;
		id_generator = newId_generator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.CONFIGURATION_ELEMENT__ID_GENERATOR, oldId_generator, id_generator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Option> getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList<Option>(Option.class, this, ApplicationPackage.CONFIGURATION_ELEMENT__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClass_generator() {
		return class_generator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass_generator(String newClass_generator) {
		String oldClass_generator = class_generator;
		class_generator = newClass_generator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.CONFIGURATION_ELEMENT__CLASS_GENERATOR, oldClass_generator, class_generator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.CONFIGURATION_ELEMENT__OPTIONS:
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
			case ApplicationPackage.CONFIGURATION_ELEMENT__ID_METAMODEL:
				return getId_metamodel();
			case ApplicationPackage.CONFIGURATION_ELEMENT__ID_GENERATOR:
				return getId_generator();
			case ApplicationPackage.CONFIGURATION_ELEMENT__OPTIONS:
				return getOptions();
			case ApplicationPackage.CONFIGURATION_ELEMENT__CLASS_GENERATOR:
				return getClass_generator();
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
			case ApplicationPackage.CONFIGURATION_ELEMENT__ID_METAMODEL:
				setId_metamodel((String)newValue);
				return;
			case ApplicationPackage.CONFIGURATION_ELEMENT__ID_GENERATOR:
				setId_generator((String)newValue);
				return;
			case ApplicationPackage.CONFIGURATION_ELEMENT__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection<? extends Option>)newValue);
				return;
			case ApplicationPackage.CONFIGURATION_ELEMENT__CLASS_GENERATOR:
				setClass_generator((String)newValue);
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
			case ApplicationPackage.CONFIGURATION_ELEMENT__ID_METAMODEL:
				setId_metamodel(ID_METAMODEL_EDEFAULT);
				return;
			case ApplicationPackage.CONFIGURATION_ELEMENT__ID_GENERATOR:
				setId_generator(ID_GENERATOR_EDEFAULT);
				return;
			case ApplicationPackage.CONFIGURATION_ELEMENT__OPTIONS:
				getOptions().clear();
				return;
			case ApplicationPackage.CONFIGURATION_ELEMENT__CLASS_GENERATOR:
				setClass_generator(CLASS_GENERATOR_EDEFAULT);
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
			case ApplicationPackage.CONFIGURATION_ELEMENT__ID_METAMODEL:
				return ID_METAMODEL_EDEFAULT == null ? id_metamodel != null : !ID_METAMODEL_EDEFAULT.equals(id_metamodel);
			case ApplicationPackage.CONFIGURATION_ELEMENT__ID_GENERATOR:
				return ID_GENERATOR_EDEFAULT == null ? id_generator != null : !ID_GENERATOR_EDEFAULT.equals(id_generator);
			case ApplicationPackage.CONFIGURATION_ELEMENT__OPTIONS:
				return options != null && !options.isEmpty();
			case ApplicationPackage.CONFIGURATION_ELEMENT__CLASS_GENERATOR:
				return CLASS_GENERATOR_EDEFAULT == null ? class_generator != null : !CLASS_GENERATOR_EDEFAULT.equals(class_generator);
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
		result.append(" (id_metamodel: ");
		result.append(id_metamodel);
		result.append(", id_generator: ");
		result.append(id_generator);
		result.append(", class_generator: ");
		result.append(class_generator);
		result.append(')');
		return result.toString();
	}

} //ConfigurationElementImpl
