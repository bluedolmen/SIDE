/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Choice Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.ChoiceFieldImpl#getMin_bound <em>Min bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ChoiceFieldImpl#getMax_bound <em>Max bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ChoiceFieldImpl#getWidget <em>Widget</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ChoiceFieldImpl#isMultiple <em>Multiple</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChoiceFieldImpl extends FieldImpl implements ChoiceField {
	/**
	 * The default value of the '{@link #getMin_bound() <em>Min bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_bound()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMin_bound() <em>Min bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_bound()
	 * @generated
	 * @ordered
	 */
	protected int min_bound = MIN_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax_bound() <em>Max bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_bound()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMax_bound() <em>Max bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_bound()
	 * @generated
	 * @ordered
	 */
	protected int max_bound = MAX_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getWidget() <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidget()
	 * @generated
	 * @ordered
	 */
	protected static final ChoiceWidgetType WIDGET_EDEFAULT = ChoiceWidgetType.SHOW_ONE;

	/**
	 * The cached value of the '{@link #getWidget() <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidget()
	 * @generated
	 * @ordered
	 */
	protected ChoiceWidgetType widget = WIDGET_EDEFAULT;

	/**
	 * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected boolean multiple = MULTIPLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChoiceFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.CHOICE_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMin_bound() {
		return min_bound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin_bound(int newMin_bound) {
		int oldMin_bound = min_bound;
		min_bound = newMin_bound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.CHOICE_FIELD__MIN_BOUND, oldMin_bound, min_bound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMax_bound() {
		return max_bound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax_bound(int newMax_bound) {
		int oldMax_bound = max_bound;
		max_bound = newMax_bound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.CHOICE_FIELD__MAX_BOUND, oldMax_bound, max_bound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChoiceWidgetType getWidget() {
		return widget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidget(ChoiceWidgetType newWidget) {
		ChoiceWidgetType oldWidget = widget;
		widget = newWidget == null ? WIDGET_EDEFAULT : newWidget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.CHOICE_FIELD__WIDGET, oldWidget, widget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiple(boolean newMultiple) {
		boolean oldMultiple = multiple;
		multiple = newMultiple;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.CHOICE_FIELD__MULTIPLE, oldMultiple, multiple));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.CHOICE_FIELD__MIN_BOUND:
				return getMin_bound();
			case FormPackage.CHOICE_FIELD__MAX_BOUND:
				return getMax_bound();
			case FormPackage.CHOICE_FIELD__WIDGET:
				return getWidget();
			case FormPackage.CHOICE_FIELD__MULTIPLE:
				return isMultiple();
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
			case FormPackage.CHOICE_FIELD__MIN_BOUND:
				setMin_bound((Integer)newValue);
				return;
			case FormPackage.CHOICE_FIELD__MAX_BOUND:
				setMax_bound((Integer)newValue);
				return;
			case FormPackage.CHOICE_FIELD__WIDGET:
				setWidget((ChoiceWidgetType)newValue);
				return;
			case FormPackage.CHOICE_FIELD__MULTIPLE:
				setMultiple((Boolean)newValue);
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
			case FormPackage.CHOICE_FIELD__MIN_BOUND:
				setMin_bound(MIN_BOUND_EDEFAULT);
				return;
			case FormPackage.CHOICE_FIELD__MAX_BOUND:
				setMax_bound(MAX_BOUND_EDEFAULT);
				return;
			case FormPackage.CHOICE_FIELD__WIDGET:
				setWidget(WIDGET_EDEFAULT);
				return;
			case FormPackage.CHOICE_FIELD__MULTIPLE:
				setMultiple(MULTIPLE_EDEFAULT);
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
			case FormPackage.CHOICE_FIELD__MIN_BOUND:
				return min_bound != MIN_BOUND_EDEFAULT;
			case FormPackage.CHOICE_FIELD__MAX_BOUND:
				return max_bound != MAX_BOUND_EDEFAULT;
			case FormPackage.CHOICE_FIELD__WIDGET:
				return widget != WIDGET_EDEFAULT;
			case FormPackage.CHOICE_FIELD__MULTIPLE:
				return multiple != MULTIPLE_EDEFAULT;
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
		result.append(" (min_bound: ");
		result.append(min_bound);
		result.append(", max_bound: ");
		result.append(max_bound);
		result.append(", widget: ");
		result.append(widget);
		result.append(", multiple: ");
		result.append(multiple);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //ChoiceFieldImpl
