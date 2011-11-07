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

import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PositionGroup;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Have Portlet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.HavePortletImpl#getAssociationPortlet <em>Association Portlet</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.HavePortletImpl#getAssociationPage <em>Association Page</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.HavePortletImpl#getPositionGroup <em>Position Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HavePortletImpl extends EObjectImpl implements HavePortlet {
	/**
	 * The cached value of the '{@link #getAssociationPortlet() <em>Association Portlet</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationPortlet()
	 * @generated
	 * @ordered
	 */
	protected Portlet associationPortlet;

	/**
	 * The cached value of the '{@link #getAssociationPage() <em>Association Page</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationPage()
	 * @generated
	 * @ordered
	 */
	protected Page associationPage;

	/**
	 * The cached value of the '{@link #getPositionGroup() <em>Position Group</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPositionGroup()
	 * @generated
	 * @ordered
	 */
	protected EList<PositionGroup> positionGroup;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HavePortletImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.HAVE_PORTLET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Portlet getAssociationPortlet() {
		if (associationPortlet != null && associationPortlet.eIsProxy()) {
			InternalEObject oldAssociationPortlet = (InternalEObject)associationPortlet;
			associationPortlet = (Portlet)eResolveProxy(oldAssociationPortlet);
			if (associationPortlet != oldAssociationPortlet) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.HAVE_PORTLET__ASSOCIATION_PORTLET, oldAssociationPortlet, associationPortlet));
			}
		}
		return associationPortlet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Portlet basicGetAssociationPortlet() {
		return associationPortlet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationPortlet(Portlet newAssociationPortlet) {
		Portlet oldAssociationPortlet = associationPortlet;
		associationPortlet = newAssociationPortlet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.HAVE_PORTLET__ASSOCIATION_PORTLET, oldAssociationPortlet, associationPortlet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page getAssociationPage() {
		if (associationPage != null && associationPage.eIsProxy()) {
			InternalEObject oldAssociationPage = (InternalEObject)associationPage;
			associationPage = (Page)eResolveProxy(oldAssociationPage);
			if (associationPage != oldAssociationPage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.HAVE_PORTLET__ASSOCIATION_PAGE, oldAssociationPage, associationPage));
			}
		}
		return associationPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page basicGetAssociationPage() {
		return associationPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationPage(Page newAssociationPage) {
		Page oldAssociationPage = associationPage;
		associationPage = newAssociationPage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.HAVE_PORTLET__ASSOCIATION_PAGE, oldAssociationPage, associationPage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PositionGroup> getPositionGroup() {
		if (positionGroup == null) {
			positionGroup = new EObjectContainmentEList<PositionGroup>(PositionGroup.class, this, PortalPackage.HAVE_PORTLET__POSITION_GROUP);
		}
		return positionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortalPackage.HAVE_PORTLET__POSITION_GROUP:
				return ((InternalEList<?>)getPositionGroup()).basicRemove(otherEnd, msgs);
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
			case PortalPackage.HAVE_PORTLET__ASSOCIATION_PORTLET:
				if (resolve) return getAssociationPortlet();
				return basicGetAssociationPortlet();
			case PortalPackage.HAVE_PORTLET__ASSOCIATION_PAGE:
				if (resolve) return getAssociationPage();
				return basicGetAssociationPage();
			case PortalPackage.HAVE_PORTLET__POSITION_GROUP:
				return getPositionGroup();
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
			case PortalPackage.HAVE_PORTLET__ASSOCIATION_PORTLET:
				setAssociationPortlet((Portlet)newValue);
				return;
			case PortalPackage.HAVE_PORTLET__ASSOCIATION_PAGE:
				setAssociationPage((Page)newValue);
				return;
			case PortalPackage.HAVE_PORTLET__POSITION_GROUP:
				getPositionGroup().clear();
				getPositionGroup().addAll((Collection<? extends PositionGroup>)newValue);
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
			case PortalPackage.HAVE_PORTLET__ASSOCIATION_PORTLET:
				setAssociationPortlet((Portlet)null);
				return;
			case PortalPackage.HAVE_PORTLET__ASSOCIATION_PAGE:
				setAssociationPage((Page)null);
				return;
			case PortalPackage.HAVE_PORTLET__POSITION_GROUP:
				getPositionGroup().clear();
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
			case PortalPackage.HAVE_PORTLET__ASSOCIATION_PORTLET:
				return associationPortlet != null;
			case PortalPackage.HAVE_PORTLET__ASSOCIATION_PAGE:
				return associationPage != null;
			case PortalPackage.HAVE_PORTLET__POSITION_GROUP:
				return positionGroup != null && !positionGroup.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //HavePortletImpl
