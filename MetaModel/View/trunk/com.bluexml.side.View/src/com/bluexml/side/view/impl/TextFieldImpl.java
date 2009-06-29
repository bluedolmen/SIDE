/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.TextField;
import com.bluexml.side.view.ViewPackage;
import com.bluexml.side.view.WidgetTextType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Text Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.TextFieldImpl#getWidgetType <em>Widget Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TextFieldImpl extends FieldImpl implements TextField {
	/**
	 * The default value of the '{@link #getWidgetType() <em>Widget Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidgetType()
	 * @generated
	 * @ordered
	 */
	protected static final WidgetTextType WIDGET_TYPE_EDEFAULT = WidgetTextType.MONOLINE;

	/**
	 * The cached value of the '{@link #getWidgetType() <em>Widget Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidgetType()
	 * @generated
	 * @ordered
	 */
	protected WidgetTextType widgetType = WIDGET_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.TEXT_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WidgetTextType getWidgetType() {
		return widgetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidgetType(WidgetTextType newWidgetType) {
		WidgetTextType oldWidgetType = widgetType;
		widgetType = newWidgetType == null ? WIDGET_TYPE_EDEFAULT : newWidgetType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TEXT_FIELD__WIDGET_TYPE, oldWidgetType, widgetType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViewPackage.TEXT_FIELD__WIDGET_TYPE:
				return getWidgetType();
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
			case ViewPackage.TEXT_FIELD__WIDGET_TYPE:
				setWidgetType((WidgetTextType)newValue);
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
			case ViewPackage.TEXT_FIELD__WIDGET_TYPE:
				setWidgetType(WIDGET_TYPE_EDEFAULT);
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
			case ViewPackage.TEXT_FIELD__WIDGET_TYPE:
				return widgetType != WIDGET_TYPE_EDEFAULT;
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
		result.append(" (widgetType: ");
		result.append(widgetType);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //TextFieldImpl
