/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.common.Container;

import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ViewPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract View Of</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.AbstractViewOfImpl#getViewOf <em>View Of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractViewOfImpl extends AbstractViewImpl implements AbstractViewOf {
	/**
	 * The cached value of the '{@link #getViewOf() <em>View Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewOf()
	 * @generated
	 * @ordered
	 */
	protected Container viewOf;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractViewOfImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.ABSTRACT_VIEW_OF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container getViewOf() {
		if (viewOf != null && viewOf.eIsProxy()) {
			InternalEObject oldViewOf = (InternalEObject)viewOf;
			viewOf = (Container)eResolveProxy(oldViewOf);
			if (viewOf != oldViewOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF, oldViewOf, viewOf));
			}
		}
		return viewOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container basicGetViewOf() {
		return viewOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewOf(Container newViewOf) {
		Container oldViewOf = viewOf;
		viewOf = newViewOf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF, oldViewOf, viewOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF:
				if (resolve) return getViewOf();
				return basicGetViewOf();
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
			case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF:
				setViewOf((Container)newValue);
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
			case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF:
				setViewOf((Container)null);
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
			case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF:
				return viewOf != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractViewOfImpl
