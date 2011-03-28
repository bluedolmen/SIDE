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

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.ModelChoiceWidgetType;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Choice Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getReal_class <em>Real class</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getMin_bound <em>Min bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getMax_bound <em>Max bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getAssociation_formClass <em>Association form Class</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getWidget <em>Widget</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#isShow_actions <em>Show actions</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getFormat_pattern <em>Format pattern</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl#getLabel_length <em>Label length</em>}</li>
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
	protected AbstractClass real_class;

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
	protected static final ModelChoiceWidgetType WIDGET_EDEFAULT = ModelChoiceWidgetType.SELECT;

	/**
	 * The cached value of the '{@link #getWidget() <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidget()
	 * @generated
	 * @ordered
	 */
	protected ModelChoiceWidgetType widget = WIDGET_EDEFAULT;

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
	 * The default value of the '{@link #getFormat_pattern() <em>Format pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormat_pattern()
	 * @generated
	 * @ordered
	 */
	protected static final String FORMAT_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFormat_pattern() <em>Format pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormat_pattern()
	 * @generated
	 * @ordered
	 */
	protected String format_pattern = FORMAT_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel_length() <em>Label length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel_length()
	 * @generated
	 * @ordered
	 */
	protected static final int LABEL_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLabel_length() <em>Label length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel_length()
	 * @generated
	 * @ordered
	 */
	protected int label_length = LABEL_LENGTH_EDEFAULT;

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
	public AbstractClass getReal_class() {
		if (real_class != null && real_class.eIsProxy()) {
			InternalEObject oldReal_class = (InternalEObject)real_class;
			real_class = (AbstractClass)eResolveProxy(oldReal_class);
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
	public AbstractClass basicGetReal_class() {
		return real_class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReal_class(AbstractClass newReal_class) {
		AbstractClass oldReal_class = real_class;
		real_class = newReal_class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__REAL_CLASS, oldReal_class, real_class));
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
	public ModelChoiceWidgetType getWidget() {
		return widget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidget(ModelChoiceWidgetType newWidget) {
		ModelChoiceWidgetType oldWidget = widget;
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
	public String getFormat_pattern() {
		return format_pattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormat_pattern(String newFormat_pattern) {
		String oldFormat_pattern = format_pattern;
		format_pattern = newFormat_pattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__FORMAT_PATTERN, oldFormat_pattern, format_pattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLabel_length() {
		return label_length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel_length(int newLabel_length) {
		int oldLabel_length = label_length;
		label_length = newLabel_length;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MODEL_CHOICE_FIELD__LABEL_LENGTH, oldLabel_length, label_length));
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
			case FormPackage.MODEL_CHOICE_FIELD__MIN_BOUND:
				return getMin_bound();
			case FormPackage.MODEL_CHOICE_FIELD__MAX_BOUND:
				return getMax_bound();
			case FormPackage.MODEL_CHOICE_FIELD__TARGET:
				return getTarget();
			case FormPackage.MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS:
				return getAssociation_formClass();
			case FormPackage.MODEL_CHOICE_FIELD__WIDGET:
				return getWidget();
			case FormPackage.MODEL_CHOICE_FIELD__SHOW_ACTIONS:
				return isShow_actions();
			case FormPackage.MODEL_CHOICE_FIELD__FORMAT_PATTERN:
				return getFormat_pattern();
			case FormPackage.MODEL_CHOICE_FIELD__LABEL_LENGTH:
				return getLabel_length();
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
				setReal_class((AbstractClass)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__MIN_BOUND:
				setMin_bound((Integer)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__MAX_BOUND:
				setMax_bound((Integer)newValue);
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
				setWidget((ModelChoiceWidgetType)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__SHOW_ACTIONS:
				setShow_actions((Boolean)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__FORMAT_PATTERN:
				setFormat_pattern((String)newValue);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__LABEL_LENGTH:
				setLabel_length((Integer)newValue);
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
				setReal_class((AbstractClass)null);
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
			case FormPackage.MODEL_CHOICE_FIELD__FORMAT_PATTERN:
				setFormat_pattern(FORMAT_PATTERN_EDEFAULT);
				return;
			case FormPackage.MODEL_CHOICE_FIELD__LABEL_LENGTH:
				setLabel_length(LABEL_LENGTH_EDEFAULT);
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
			case FormPackage.MODEL_CHOICE_FIELD__FORMAT_PATTERN:
				return FORMAT_PATTERN_EDEFAULT == null ? format_pattern != null : !FORMAT_PATTERN_EDEFAULT.equals(format_pattern);
			case FormPackage.MODEL_CHOICE_FIELD__LABEL_LENGTH:
				return label_length != LABEL_LENGTH_EDEFAULT;
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
		result.append(", format_pattern: ");
		result.append(format_pattern);
		result.append(", label_length: ");
		result.append(label_length);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //ModelChoiceFieldImpl
