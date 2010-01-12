/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Portlet Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.PortletTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletTypeImpl#getHaveAttribute <em>Have Attribute</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletTypeImpl#isInstanceable <em>Instanceable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortletTypeImpl extends PortalModelElementImpl implements PortletType {
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
	 * The cached value of the '{@link #getHaveAttribute() <em>Have Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHaveAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList<PortletAttribute> haveAttribute;

	/**
	 * The default value of the '{@link #isInstanceable() <em>Instanceable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstanceable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSTANCEABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isInstanceable() <em>Instanceable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstanceable()
	 * @generated
	 * @ordered
	 */
	protected boolean instanceable = INSTANCEABLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortletTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.PORTLET_TYPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortletAttribute> getHaveAttribute() {
		if (haveAttribute == null) {
			haveAttribute = new EObjectContainmentEList<PortletAttribute>(PortletAttribute.class, this, PortalPackage.PORTLET_TYPE__HAVE_ATTRIBUTE);
		}
		return haveAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInstanceable() {
		return instanceable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceable(boolean newInstanceable) {
		boolean oldInstanceable = instanceable;
		instanceable = newInstanceable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET_TYPE__INSTANCEABLE, oldInstanceable, instanceable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortalPackage.PORTLET_TYPE__HAVE_ATTRIBUTE:
				return ((InternalEList<?>)getHaveAttribute()).basicRemove(otherEnd, msgs);
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
			case PortalPackage.PORTLET_TYPE__ID:
				return getId();
			case PortalPackage.PORTLET_TYPE__NAME:
				return getName();
			case PortalPackage.PORTLET_TYPE__HAVE_ATTRIBUTE:
				return getHaveAttribute();
			case PortalPackage.PORTLET_TYPE__INSTANCEABLE:
				return isInstanceable();
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
			case PortalPackage.PORTLET_TYPE__ID:
				setId((String)newValue);
				return;
			case PortalPackage.PORTLET_TYPE__NAME:
				setName((String)newValue);
				return;
			case PortalPackage.PORTLET_TYPE__HAVE_ATTRIBUTE:
				getHaveAttribute().clear();
				getHaveAttribute().addAll((Collection<? extends PortletAttribute>)newValue);
				return;
			case PortalPackage.PORTLET_TYPE__INSTANCEABLE:
				setInstanceable((Boolean)newValue);
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
			case PortalPackage.PORTLET_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case PortalPackage.PORTLET_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PortalPackage.PORTLET_TYPE__HAVE_ATTRIBUTE:
				getHaveAttribute().clear();
				return;
			case PortalPackage.PORTLET_TYPE__INSTANCEABLE:
				setInstanceable(INSTANCEABLE_EDEFAULT);
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
			case PortalPackage.PORTLET_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PortalPackage.PORTLET_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PortalPackage.PORTLET_TYPE__HAVE_ATTRIBUTE:
				return haveAttribute != null && !haveAttribute.isEmpty();
			case PortalPackage.PORTLET_TYPE__INSTANCEABLE:
				return instanceable != INSTANCEABLE_EDEFAULT;
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", instanceable: ");
		result.append(instanceable);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //PortletTypeImpl
