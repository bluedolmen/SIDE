/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import com.bluexml.side.common.impl.ModelElementImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.PositionGroup;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Position Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.PositionGroupImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PositionGroupImpl#getOnColumn <em>On Column</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PositionGroupImpl#getOnLayout <em>On Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PositionGroupImpl extends ModelElementImpl implements PositionGroup {
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
	 * The cached value of the '{@link #getOnColumn() <em>On Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnColumn()
	 * @generated
	 * @ordered
	 */
	protected Column onColumn;

	/**
	 * The cached value of the '{@link #getOnLayout() <em>On Layout</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnLayout()
	 * @generated
	 * @ordered
	 */
	protected PortalLayout onLayout;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PositionGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.POSITION_GROUP;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.POSITION_GROUP__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column getOnColumn() {
		if (onColumn != null && onColumn.eIsProxy()) {
			InternalEObject oldOnColumn = (InternalEObject)onColumn;
			onColumn = (Column)eResolveProxy(oldOnColumn);
			if (onColumn != oldOnColumn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.POSITION_GROUP__ON_COLUMN, oldOnColumn, onColumn));
			}
		}
		return onColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column basicGetOnColumn() {
		return onColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnColumn(Column newOnColumn) {
		Column oldOnColumn = onColumn;
		onColumn = newOnColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.POSITION_GROUP__ON_COLUMN, oldOnColumn, onColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalLayout getOnLayout() {
		if (onLayout != null && onLayout.eIsProxy()) {
			InternalEObject oldOnLayout = (InternalEObject)onLayout;
			onLayout = (PortalLayout)eResolveProxy(oldOnLayout);
			if (onLayout != oldOnLayout) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.POSITION_GROUP__ON_LAYOUT, oldOnLayout, onLayout));
			}
		}
		return onLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalLayout basicGetOnLayout() {
		return onLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnLayout(PortalLayout newOnLayout) {
		PortalLayout oldOnLayout = onLayout;
		onLayout = newOnLayout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.POSITION_GROUP__ON_LAYOUT, oldOnLayout, onLayout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PortalPackage.POSITION_GROUP__POSITION:
				return getPosition();
			case PortalPackage.POSITION_GROUP__ON_COLUMN:
				if (resolve) return getOnColumn();
				return basicGetOnColumn();
			case PortalPackage.POSITION_GROUP__ON_LAYOUT:
				if (resolve) return getOnLayout();
				return basicGetOnLayout();
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
			case PortalPackage.POSITION_GROUP__POSITION:
				setPosition((Integer)newValue);
				return;
			case PortalPackage.POSITION_GROUP__ON_COLUMN:
				setOnColumn((Column)newValue);
				return;
			case PortalPackage.POSITION_GROUP__ON_LAYOUT:
				setOnLayout((PortalLayout)newValue);
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
			case PortalPackage.POSITION_GROUP__POSITION:
				setPosition(POSITION_EDEFAULT);
				return;
			case PortalPackage.POSITION_GROUP__ON_COLUMN:
				setOnColumn((Column)null);
				return;
			case PortalPackage.POSITION_GROUP__ON_LAYOUT:
				setOnLayout((PortalLayout)null);
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
			case PortalPackage.POSITION_GROUP__POSITION:
				return position != POSITION_EDEFAULT;
			case PortalPackage.POSITION_GROUP__ON_COLUMN:
				return onColumn != null;
			case PortalPackage.POSITION_GROUP__ON_LAYOUT:
				return onLayout != null;
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
		result.append(" (position: ");
		result.append(position);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //PositionGroupImpl
