/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import com.bluexml.side.view.PaginationStyle;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.ViewPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Paging</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.PagingImpl#getPaginationStyle <em>Pagination Style</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.PagingImpl#getMaxItems <em>Max Items</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.PagingImpl#getMaxPage <em>Max Page</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PagingImpl extends EObjectImpl implements Paging {
	/**
	 * The default value of the '{@link #getPaginationStyle() <em>Pagination Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaginationStyle()
	 * @generated
	 * @ordered
	 */
	protected static final PaginationStyle PAGINATION_STYLE_EDEFAULT = PaginationStyle.PAGE;

	/**
	 * The cached value of the '{@link #getPaginationStyle() <em>Pagination Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaginationStyle()
	 * @generated
	 * @ordered
	 */
	protected PaginationStyle paginationStyle = PAGINATION_STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxItems() <em>Max Items</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxItems()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_ITEMS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxItems() <em>Max Items</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxItems()
	 * @generated
	 * @ordered
	 */
	protected int maxItems = MAX_ITEMS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxPage() <em>Max Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxPage()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_PAGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxPage() <em>Max Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxPage()
	 * @generated
	 * @ordered
	 */
	protected int maxPage = MAX_PAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PagingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.PAGING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaginationStyle getPaginationStyle() {
		return paginationStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPaginationStyle(PaginationStyle newPaginationStyle) {
		PaginationStyle oldPaginationStyle = paginationStyle;
		paginationStyle = newPaginationStyle == null ? PAGINATION_STYLE_EDEFAULT : newPaginationStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.PAGING__PAGINATION_STYLE, oldPaginationStyle, paginationStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxItems() {
		return maxItems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxItems(int newMaxItems) {
		int oldMaxItems = maxItems;
		maxItems = newMaxItems;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.PAGING__MAX_ITEMS, oldMaxItems, maxItems));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxPage() {
		return maxPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxPage(int newMaxPage) {
		int oldMaxPage = maxPage;
		maxPage = newMaxPage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.PAGING__MAX_PAGE, oldMaxPage, maxPage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViewPackage.PAGING__PAGINATION_STYLE:
				return getPaginationStyle();
			case ViewPackage.PAGING__MAX_ITEMS:
				return new Integer(getMaxItems());
			case ViewPackage.PAGING__MAX_PAGE:
				return new Integer(getMaxPage());
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
			case ViewPackage.PAGING__PAGINATION_STYLE:
				setPaginationStyle((PaginationStyle)newValue);
				return;
			case ViewPackage.PAGING__MAX_ITEMS:
				setMaxItems(((Integer)newValue).intValue());
				return;
			case ViewPackage.PAGING__MAX_PAGE:
				setMaxPage(((Integer)newValue).intValue());
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
			case ViewPackage.PAGING__PAGINATION_STYLE:
				setPaginationStyle(PAGINATION_STYLE_EDEFAULT);
				return;
			case ViewPackage.PAGING__MAX_ITEMS:
				setMaxItems(MAX_ITEMS_EDEFAULT);
				return;
			case ViewPackage.PAGING__MAX_PAGE:
				setMaxPage(MAX_PAGE_EDEFAULT);
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
			case ViewPackage.PAGING__PAGINATION_STYLE:
				return paginationStyle != PAGINATION_STYLE_EDEFAULT;
			case ViewPackage.PAGING__MAX_ITEMS:
				return maxItems != MAX_ITEMS_EDEFAULT;
			case ViewPackage.PAGING__MAX_PAGE:
				return maxPage != MAX_PAGE_EDEFAULT;
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
		result.append(" (paginationStyle: ");
		result.append(paginationStyle);
		result.append(", maxItems: ");
		result.append(maxItems);
		result.append(", maxPage: ");
		result.append(maxPage);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //PagingImpl
