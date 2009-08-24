/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.ocl.ecore.OCL;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.ReferenceWidgetType;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Choice Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getReal_class <em>Real class</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getAssociation_class <em>Association class</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getMin_bound <em>Min bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getMax_bound <em>Max bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getAssociation_formClass <em>Association form Class</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getWidget <em>Widget</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#isShow_actions <em>Show actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelChoiceFieldImpl extends FieldImpl implements ModelChoiceField {
	/**
	 * The cached value of the '{@link #getReal_class() <em>Real class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReal_class()
	 * @generated
	 * @ordered
	 */
	protected Clazz real_class;

	/**
	 * The cached value of the '{@link #getAssociation_class() <em>Association class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociation_class()
	 * @generated
	 * @ordered
	 */
	protected Clazz association_class;

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
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected EList<FormContainer> target;

	/**
	 * The cached value of the '{@link #getAssociation_formClass() <em>Association form Class</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociation_formClass()
	 * @generated
	 * @ordered
	 */
	protected EList<FormContainer> association_formClass;

	/**
	 * The default value of the '{@link #getWidget() <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidget()
	 * @generated
	 * @ordered
	 */
	protected static final ChoiceWidgetType WIDGET_EDEFAULT = ChoiceWidgetType.LIST_ALL;

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
	 * The default value of the '{@link #isShow_actions() <em>Show actions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShow_actions()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHOW_ACTIONS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isShow_actions() <em>Show actions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShow_actions()
	 * @generated
	 * @ordered
	 */
	protected boolean show_actions = SHOW_ACTIONS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelChoiceFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.MODEL_CHOICE_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz getReal_class() {
		if (real_class != null && real_class.eIsProxy()) {
			InternalEObject oldReal_class = (InternalEObject)real_class;
			real_class = (Clazz)eResolveProxy(oldReal_class);
			if (real_class != oldReal_class) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS, oldReal_class, real_class));
			}
		}
		return real_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz basicGetReal_class() {
		return real_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReal_class(Clazz newReal_class) {
		Clazz oldReal_class = real_class;
		real_class = newReal_class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS, oldReal_class, real_class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz getAssociation_class() {
		if (association_class != null && association_class.eIsProxy()) {
			InternalEObject oldAssociation_class = (InternalEObject)association_class;
			association_class = (Clazz)eResolveProxy(oldAssociation_class);
			if (association_class != oldAssociation_class) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_CLASS, oldAssociation_class, association_class));
			}
		}
		return association_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz basicGetAssociation_class() {
		return association_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociation_class(Clazz newAssociation_class) {
		Clazz oldAssociation_class = association_class;
		association_class = newAssociation_class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_CLASS, oldAssociation_class, association_class));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__MIN_BOUND, oldMin_bound, min_bound));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__MAX_BOUND, oldMax_bound, max_bound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FormContainer> getTarget() {
		if (target == null) {
			target = new EObjectResolvingEList<FormContainer>(FormContainer.class, this, FormPackage.MODEL_CHOICE_FIELD__TARGET);
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FormContainer> getAssociation_formClass() {
		if (association_formClass == null) {
			association_formClass = new EObjectResolvingEList<FormContainer>(FormContainer.class, this, FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS);
		}
		return association_formClass;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__WIDGET, oldWidget, widget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isShow_actions() {
		return show_actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShow_actions(boolean newShow_actions) {
		boolean oldShow_actions = show_actions;
		show_actions = newShow_actions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__SHOW_ACTIONS, oldShow_actions, show_actions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS:
				if (resolve) return getReal_class();
				return basicGetReal_class();
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_CLASS:
				if (resolve) return getAssociation_class();
				return basicGetAssociation_class();
			case FormPackage.MODEL_CHOICE_FIELD__MIN_BOUND:
				return new Integer(getMin_bound());
			case FormPackage.MODEL_CHOICE_FIELD__MAX_BOUND:
				return new Integer(getMax_bound());
			case FormPackage.MODEL_CHOICE_FIELD__TARGET:
				return getTarget();
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS:
				return getAssociation_formClass();
			case FormPackage.MODEL_CHOICE_FIELD__WIDGET:
				return getWidget();
			case FormPackage.MODEL_CHOICE_FIELD__SHOW_ACTIONS:
				return isShow_actions() ? Boolean.TRUE : Boolean.FALSE;
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
			case FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS:
				setReal_class((Clazz)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_CLASS:
				setAssociation_class((Clazz)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__MIN_BOUND:
				setMin_bound(((Integer)newValue).intValue());
				return;
			case FormPackage.MODEL_CHOICE_FIELD__MAX_BOUND:
				setMax_bound(((Integer)newValue).intValue());
				return;
			case FormPackage.MODEL_CHOICE_FIELD__TARGET:
				getTarget().clear();
				getTarget().addAll((Collection<? extends FormContainer>)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS:
				getAssociation_formClass().clear();
				getAssociation_formClass().addAll((Collection<? extends FormContainer>)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__WIDGET:
				setWidget((ChoiceWidgetType)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__SHOW_ACTIONS:
				setShow_actions(((Boolean)newValue).booleanValue());
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
			case FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS:
				setReal_class((Clazz)null);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_CLASS:
				setAssociation_class((Clazz)null);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__MIN_BOUND:
				setMin_bound(MIN_BOUND_EDEFAULT);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__MAX_BOUND:
				setMax_bound(MAX_BOUND_EDEFAULT);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__TARGET:
				getTarget().clear();
				return;
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS:
				getAssociation_formClass().clear();
				return;
			case FormPackage.MODEL_CHOICE_FIELD__WIDGET:
				setWidget(WIDGET_EDEFAULT);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__SHOW_ACTIONS:
				setShow_actions(SHOW_ACTIONS_EDEFAULT);
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
			case FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS:
				return real_class != null;
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_CLASS:
				return association_class != null;
			case FormPackage.MODEL_CHOICE_FIELD__MIN_BOUND:
				return min_bound != MIN_BOUND_EDEFAULT;
			case FormPackage.MODEL_CHOICE_FIELD__MAX_BOUND:
				return max_bound != MAX_BOUND_EDEFAULT;
			case FormPackage.MODEL_CHOICE_FIELD__TARGET:
				return target != null && !target.isEmpty();
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS:
				return association_formClass != null && !association_formClass.isEmpty();
			case FormPackage.MODEL_CHOICE_FIELD__WIDGET:
				return widget != WIDGET_EDEFAULT;
			case FormPackage.MODEL_CHOICE_FIELD__SHOW_ACTIONS:
				return show_actions != SHOW_ACTIONS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ClassReference.class) {
			switch (derivedFeatureID) {
				case FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS: return FormPackage.CLASS_REFERENCE__REAL_CLASS;
				case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_CLASS: return FormPackage.CLASS_REFERENCE__ASSOCIATION_CLASS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ClassReference.class) {
			switch (baseFeatureID) {
				case FormPackage.CLASS_REFERENCE__REAL_CLASS: return FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS;
				case FormPackage.CLASS_REFERENCE__ASSOCIATION_CLASS: return FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_CLASS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", show_actions: ");
		result.append(show_actions);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //ModelChoiceFieldImpl
