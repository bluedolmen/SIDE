/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.isChildPage;
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
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.PageImpl#getID <em>ID</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PageImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PageImpl#getUseLayout <em>Use Layout</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PageImpl#getPortlets <em>Portlets</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PageImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PageImpl#getIsChildPageOf <em>Is Child Page Of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PageImpl extends PortalModelElementImpl implements Page {
	/**
	 * The default value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUseLayout() <em>Use Layout</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseLayout()
	 * @generated
	 * @ordered
	 */
	protected PortalLayout useLayout;

	/**
	 * The cached value of the '{@link #getPortlets() <em>Portlets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortlets()
	 * @generated
	 * @ordered
	 */
	protected EList<HavePortlet> portlets;

	/**
	 * The default value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected static final int POSITION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected int position = POSITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIsChildPageOf() <em>Is Child Page Of</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsChildPageOf()
	 * @generated
	 * @ordered
	 */
	protected isChildPage isChildPageOf;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getID() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setID(String newID) {
		String oldID = id;
		id = newID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PAGE__ID, oldID, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PAGE__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalLayout getUseLayout() {
		if (useLayout != null && useLayout.eIsProxy()) {
			InternalEObject oldUseLayout = (InternalEObject)useLayout;
			useLayout = (PortalLayout)eResolveProxy(oldUseLayout);
			if (useLayout != oldUseLayout) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.PAGE__USE_LAYOUT, oldUseLayout, useLayout));
			}
		}
		return useLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalLayout basicGetUseLayout() {
		return useLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUseLayout(PortalLayout newUseLayout) {
		PortalLayout oldUseLayout = useLayout;
		useLayout = newUseLayout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PAGE__USE_LAYOUT, oldUseLayout, useLayout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HavePortlet> getPortlets() {
		if (portlets == null) {
			portlets = new EObjectContainmentEList<HavePortlet>(HavePortlet.class, this, PortalPackage.PAGE__PORTLETS);
		}
		return portlets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosition(int newPosition) {
		int oldPosition = position;
		position = newPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PAGE__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public isChildPage getIsChildPageOf() {
		return isChildPageOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIsChildPageOf(isChildPage newIsChildPageOf, NotificationChain msgs) {
		isChildPage oldIsChildPageOf = isChildPageOf;
		isChildPageOf = newIsChildPageOf;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PortalPackage.PAGE__IS_CHILD_PAGE_OF, oldIsChildPageOf, newIsChildPageOf);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsChildPageOf(isChildPage newIsChildPageOf) {
		if (newIsChildPageOf != isChildPageOf) {
			NotificationChain msgs = null;
			if (isChildPageOf != null)
				msgs = ((InternalEObject)isChildPageOf).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PortalPackage.PAGE__IS_CHILD_PAGE_OF, null, msgs);
			if (newIsChildPageOf != null)
				msgs = ((InternalEObject)newIsChildPageOf).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PortalPackage.PAGE__IS_CHILD_PAGE_OF, null, msgs);
			msgs = basicSetIsChildPageOf(newIsChildPageOf, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PAGE__IS_CHILD_PAGE_OF, newIsChildPageOf, newIsChildPageOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortalPackage.PAGE__PORTLETS:
				return ((InternalEList<?>)getPortlets()).basicRemove(otherEnd, msgs);
			case PortalPackage.PAGE__IS_CHILD_PAGE_OF:
				return basicSetIsChildPageOf(null, msgs);
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
			case PortalPackage.PAGE__ID:
				return getID();
			case PortalPackage.PAGE__TITLE:
				return getTitle();
			case PortalPackage.PAGE__USE_LAYOUT:
				if (resolve) return getUseLayout();
				return basicGetUseLayout();
			case PortalPackage.PAGE__PORTLETS:
				return getPortlets();
			case PortalPackage.PAGE__POSITION:
				return getPosition();
			case PortalPackage.PAGE__IS_CHILD_PAGE_OF:
				return getIsChildPageOf();
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
			case PortalPackage.PAGE__ID:
				setID((String)newValue);
				return;
			case PortalPackage.PAGE__TITLE:
				setTitle((String)newValue);
				return;
			case PortalPackage.PAGE__USE_LAYOUT:
				setUseLayout((PortalLayout)newValue);
				return;
			case PortalPackage.PAGE__PORTLETS:
				getPortlets().clear();
				getPortlets().addAll((Collection<? extends HavePortlet>)newValue);
				return;
			case PortalPackage.PAGE__POSITION:
				setPosition((Integer)newValue);
				return;
			case PortalPackage.PAGE__IS_CHILD_PAGE_OF:
				setIsChildPageOf((isChildPage)newValue);
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
			case PortalPackage.PAGE__ID:
				setID(ID_EDEFAULT);
				return;
			case PortalPackage.PAGE__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case PortalPackage.PAGE__USE_LAYOUT:
				setUseLayout((PortalLayout)null);
				return;
			case PortalPackage.PAGE__PORTLETS:
				getPortlets().clear();
				return;
			case PortalPackage.PAGE__POSITION:
				setPosition(POSITION_EDEFAULT);
				return;
			case PortalPackage.PAGE__IS_CHILD_PAGE_OF:
				setIsChildPageOf((isChildPage)null);
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
			case PortalPackage.PAGE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PortalPackage.PAGE__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case PortalPackage.PAGE__USE_LAYOUT:
				return useLayout != null;
			case PortalPackage.PAGE__PORTLETS:
				return portlets != null && !portlets.isEmpty();
			case PortalPackage.PAGE__POSITION:
				return position != POSITION_EDEFAULT;
			case PortalPackage.PAGE__IS_CHILD_PAGE_OF:
				return isChildPageOf != null;
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
		result.append(" (ID: ");
		result.append(id);
		result.append(", title: ");
		result.append(title);
		result.append(", position: ");
		result.append(position);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //PageImpl
