/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.isChildPage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>is Child Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.isChildPageImpl#isInherit <em>Inherit</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.isChildPageImpl#getIsChildPageOf <em>Is Child Page Of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class isChildPageImpl extends EObjectImpl implements isChildPage {
	/**
	 * The default value of the '{@link #isInherit() <em>Inherit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInherit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INHERIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInherit() <em>Inherit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInherit()
	 * @generated
	 * @ordered
	 */
	protected boolean inherit = INHERIT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIsChildPageOf() <em>Is Child Page Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsChildPageOf()
	 * @generated
	 * @ordered
	 */
	protected Page isChildPageOf;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected isChildPageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.IS_CHILD_PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInherit() {
		return inherit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInherit(boolean newInherit) {
		boolean oldInherit = inherit;
		inherit = newInherit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.IS_CHILD_PAGE__INHERIT, oldInherit, inherit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page getIsChildPageOf() {
		if (isChildPageOf != null && isChildPageOf.eIsProxy()) {
			InternalEObject oldIsChildPageOf = (InternalEObject)isChildPageOf;
			isChildPageOf = (Page)eResolveProxy(oldIsChildPageOf);
			if (isChildPageOf != oldIsChildPageOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.IS_CHILD_PAGE__IS_CHILD_PAGE_OF, oldIsChildPageOf, isChildPageOf));
			}
		}
		return isChildPageOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page basicGetIsChildPageOf() {
		return isChildPageOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsChildPageOf(Page newIsChildPageOf) {
		Page oldIsChildPageOf = isChildPageOf;
		isChildPageOf = newIsChildPageOf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.IS_CHILD_PAGE__IS_CHILD_PAGE_OF, oldIsChildPageOf, isChildPageOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PortalPackage.IS_CHILD_PAGE__INHERIT:
				return isInherit();
			case PortalPackage.IS_CHILD_PAGE__IS_CHILD_PAGE_OF:
				if (resolve) return getIsChildPageOf();
				return basicGetIsChildPageOf();
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
			case PortalPackage.IS_CHILD_PAGE__INHERIT:
				setInherit((Boolean)newValue);
				return;
			case PortalPackage.IS_CHILD_PAGE__IS_CHILD_PAGE_OF:
				setIsChildPageOf((Page)newValue);
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
			case PortalPackage.IS_CHILD_PAGE__INHERIT:
				setInherit(INHERIT_EDEFAULT);
				return;
			case PortalPackage.IS_CHILD_PAGE__IS_CHILD_PAGE_OF:
				setIsChildPageOf((Page)null);
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
			case PortalPackage.IS_CHILD_PAGE__INHERIT:
				return inherit != INHERIT_EDEFAULT;
			case PortalPackage.IS_CHILD_PAGE__IS_CHILD_PAGE_OF:
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
		result.append(" (inherit: ");
		result.append(inherit);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //isChildPageImpl
