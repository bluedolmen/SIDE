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

import com.bluexml.side.KerblueForms.ChoiceField;
import com.bluexml.side.KerblueForms.ChoiceWidgetType;
import com.bluexml.side.form.formPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Choice Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link KerblueForms.impl.ChoiceFieldImpl#getMin_bound <em>Min bound</em>}</li>
 *   <li>{@link KerblueForms.impl.ChoiceFieldImpl#getMax_bound <em>Max bound</em>}</li>
 *   <li>{@link KerblueForms.impl.ChoiceFieldImpl#getWidget <em>Widget</em>}</li>
 *   <li>{@link KerblueForms.impl.ChoiceFieldImpl#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link KerblueForms.impl.ChoiceFieldImpl#getFilterParent <em>Filter Parent</em>}</li>
 *   <li>{@link KerblueForms.impl.ChoiceFieldImpl#getFilterData <em>Filter Data</em>}</li>
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
	protected static final ChoiceWidgetType WIDGET_EDEFAULT = ChoiceWidgetType.SELECT;

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
	 * The default value of the '{@link #getFilterParent() <em>Filter Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterParent()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_PARENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilterParent() <em>Filter Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterParent()
	 * @generated
	 * @ordered
	 */
	protected String filterParent = FILTER_PARENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFilterData() <em>Filter Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterData()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilterData() <em>Filter Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterData()
	 * @generated
	 * @ordered
	 */
	protected String filterData = FILTER_DATA_EDEFAULT;

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
		return formPackage.Literals.CHOICE_FIELD;
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
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CHOICE_FIELD__MIN_BOUND, oldMin_bound, min_bound));
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
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CHOICE_FIELD__MAX_BOUND, oldMax_bound, max_bound));
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
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CHOICE_FIELD__WIDGET, oldWidget, widget));
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
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CHOICE_FIELD__MULTIPLE, oldMultiple, multiple));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilterParent() {
		return filterParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterParent(String newFilterParent) {
		String oldFilterParent = filterParent;
		filterParent = newFilterParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CHOICE_FIELD__FILTER_PARENT, oldFilterParent, filterParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilterData() {
		return filterData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterData(String newFilterData) {
		String oldFilterData = filterData;
		filterData = newFilterData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, formPackage.CHOICE_FIELD__FILTER_DATA, oldFilterData, filterData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case formPackage.CHOICE_FIELD__MIN_BOUND:
				return new Integer(getMin_bound());
			case formPackage.CHOICE_FIELD__MAX_BOUND:
				return new Integer(getMax_bound());
			case formPackage.CHOICE_FIELD__WIDGET:
				return getWidget();
			case formPackage.CHOICE_FIELD__MULTIPLE:
				return isMultiple() ? Boolean.TRUE : Boolean.FALSE;
			case formPackage.CHOICE_FIELD__FILTER_PARENT:
				return getFilterParent();
			case formPackage.CHOICE_FIELD__FILTER_DATA:
				return getFilterData();
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
			case formPackage.CHOICE_FIELD__MIN_BOUND:
				setMin_bound(((Integer)newValue).intValue());
				return;
			case formPackage.CHOICE_FIELD__MAX_BOUND:
				setMax_bound(((Integer)newValue).intValue());
				return;
			case formPackage.CHOICE_FIELD__WIDGET:
				setWidget((ChoiceWidgetType)newValue);
				return;
			case formPackage.CHOICE_FIELD__MULTIPLE:
				setMultiple(((Boolean)newValue).booleanValue());
				return;
			case formPackage.CHOICE_FIELD__FILTER_PARENT:
				setFilterParent((String)newValue);
				return;
			case formPackage.CHOICE_FIELD__FILTER_DATA:
				setFilterData((String)newValue);
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
			case formPackage.CHOICE_FIELD__MIN_BOUND:
				setMin_bound(MIN_BOUND_EDEFAULT);
				return;
			case formPackage.CHOICE_FIELD__MAX_BOUND:
				setMax_bound(MAX_BOUND_EDEFAULT);
				return;
			case formPackage.CHOICE_FIELD__WIDGET:
				setWidget(WIDGET_EDEFAULT);
				return;
			case formPackage.CHOICE_FIELD__MULTIPLE:
				setMultiple(MULTIPLE_EDEFAULT);
				return;
			case formPackage.CHOICE_FIELD__FILTER_PARENT:
				setFilterParent(FILTER_PARENT_EDEFAULT);
				return;
			case formPackage.CHOICE_FIELD__FILTER_DATA:
				setFilterData(FILTER_DATA_EDEFAULT);
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
			case formPackage.CHOICE_FIELD__MIN_BOUND:
				return min_bound != MIN_BOUND_EDEFAULT;
			case formPackage.CHOICE_FIELD__MAX_BOUND:
				return max_bound != MAX_BOUND_EDEFAULT;
			case formPackage.CHOICE_FIELD__WIDGET:
				return widget != WIDGET_EDEFAULT;
			case formPackage.CHOICE_FIELD__MULTIPLE:
				return multiple != MULTIPLE_EDEFAULT;
			case formPackage.CHOICE_FIELD__FILTER_PARENT:
				return FILTER_PARENT_EDEFAULT == null ? filterParent != null : !FILTER_PARENT_EDEFAULT.equals(filterParent);
			case formPackage.CHOICE_FIELD__FILTER_DATA:
				return FILTER_DATA_EDEFAULT == null ? filterData != null : !FILTER_DATA_EDEFAULT.equals(filterData);
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
		result.append(" (min_bound: "); //$NON-NLS-1$
		result.append(min_bound);
		result.append(", max_bound: "); //$NON-NLS-1$
		result.append(max_bound);
		result.append(", widget: "); //$NON-NLS-1$
		result.append(widget);
		result.append(", multiple: "); //$NON-NLS-1$
		result.append(multiple);
		result.append(", filterParent: "); //$NON-NLS-1$
		result.append(filterParent);
		result.append(", filterData: "); //$NON-NLS-1$
		result.append(filterData);
		result.append(')');
		return result.toString();
	}

} //ChoiceFieldImpl
