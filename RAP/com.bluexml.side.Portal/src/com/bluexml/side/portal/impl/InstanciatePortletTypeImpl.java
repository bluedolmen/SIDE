/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

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
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.PortletAttributeInstance;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instanciate Portlet Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.InstanciatePortletTypeImpl#getPortletType <em>Portlet Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.InstanciatePortletTypeImpl#getInstances <em>Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstanciatePortletTypeImpl extends EObjectImpl implements InstanciatePortletType {
	/**
	 * The cached value of the '{@link #getPortletType() <em>Portlet Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortletType()
	 * @generated
	 * @ordered
	 */
	protected PortletType portletType;

	/**
	 * The cached value of the '{@link #getInstances() <em>Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<PortletAttributeInstance> instances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanciatePortletTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.INSTANCIATE_PORTLET_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletType getPortletType() {
		if (portletType != null && portletType.eIsProxy()) {
			InternalEObject oldPortletType = (InternalEObject)portletType;
			portletType = (PortletType)eResolveProxy(oldPortletType);
			if (portletType != oldPortletType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE, oldPortletType, portletType));
			}
		}
		return portletType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletType basicGetPortletType() {
		return portletType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortletType(PortletType newPortletType) {
		PortletType oldPortletType = portletType;
		portletType = newPortletType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE, oldPortletType, portletType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortletAttributeInstance> getInstances() {
		if (instances == null) {
			instances = new EObjectContainmentEList<PortletAttributeInstance>(PortletAttributeInstance.class, this, PortalPackage.INSTANCIATE_PORTLET_TYPE__INSTANCES);
		}
		return instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__INSTANCES:
				return ((InternalEList<?>)getInstances()).basicRemove(otherEnd, msgs);
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
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE:
				if (resolve) return getPortletType();
				return basicGetPortletType();
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__INSTANCES:
				return getInstances();
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
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE:
				setPortletType((PortletType)newValue);
				return;
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__INSTANCES:
				getInstances().clear();
				getInstances().addAll((Collection<? extends PortletAttributeInstance>)newValue);
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
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE:
				setPortletType((PortletType)null);
				return;
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__INSTANCES:
				getInstances().clear();
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
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE:
				return portletType != null;
			case PortalPackage.INSTANCIATE_PORTLET_TYPE__INSTANCES:
				return instances != null && !instances.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //InstanciatePortletTypeImpl
