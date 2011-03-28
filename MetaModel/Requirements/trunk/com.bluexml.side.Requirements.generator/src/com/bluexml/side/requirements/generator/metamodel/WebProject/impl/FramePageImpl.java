/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Page;
import com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Frame Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FramePageImpl#getCol1 <em>Col1</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FramePageImpl extends PageImpl implements FramePage {
	/**
	 * The cached value of the '{@link #getCol1() <em>Col1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCol1()
	 * @generated
	 * @ordered
	 */
	protected Page col1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FramePageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebProjectPackage.Literals.FRAME_PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page getCol1() {
		if (col1 != null && col1.eIsProxy()) {
			InternalEObject oldCol1 = (InternalEObject)col1;
			col1 = (Page)eResolveProxy(oldCol1);
			if (col1 != oldCol1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebProjectPackage.FRAME_PAGE__COL1, oldCol1, col1));
			}
		}
		return col1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page basicGetCol1() {
		return col1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCol1(Page newCol1) {
		Page oldCol1 = col1;
		col1 = newCol1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.FRAME_PAGE__COL1, oldCol1, col1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebProjectPackage.FRAME_PAGE__COL1:
				if (resolve) return getCol1();
				return basicGetCol1();
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
			case WebProjectPackage.FRAME_PAGE__COL1:
				setCol1((Page)newValue);
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
			case WebProjectPackage.FRAME_PAGE__COL1:
				setCol1((Page)null);
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
			case WebProjectPackage.FRAME_PAGE__COL1:
				return col1 != null;
		}
		return super.eIsSet(featureID);
	}

} //FramePageImpl
