/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.SearchOperatorConfiguration;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getError_messages <em>Error messages</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getInitial <em>Initial</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getFieldSize <em>Field Size</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getSearchOperatorConfiguration <em>Search Operator Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FieldImpl extends FormElementImpl implements Field {
	/**
	 * The default value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANDATORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected boolean mandatory = MANDATORY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getError_messages() <em>Error messages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getError_messages()
	 * @generated
	 * @ordered
	 */
	protected Map<String, String> error_messages;

	/**
	 * The default value of the '{@link #getInitial() <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitial()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitial() <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitial()
	 * @generated
	 * @ordered
	 */
	protected String initial = INITIAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISABLED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected boolean disabled = DISABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldSize() <em>Field Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldSize()
	 * @generated
	 * @ordered
	 */
	protected static final int FIELD_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFieldSize() <em>Field Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldSize()
	 * @generated
	 * @ordered
	 */
	protected int fieldSize = FIELD_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected static final String STYLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected String style = STYLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSearchOperatorConfiguration() <em>Search Operator Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearchOperatorConfiguration()
	 * @generated
	 * @ordered
	 */
	protected SearchOperatorConfiguration searchOperatorConfiguration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMandatory(boolean newMandatory) {
		boolean oldMandatory = mandatory;
		mandatory = newMandatory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__MANDATORY, oldMandatory, mandatory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, String> getError_messages() {
		return error_messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setError_messages(Map<String, String> newError_messages) {
		Map<String, String> oldError_messages = error_messages;
		error_messages = newError_messages;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__ERROR_MESSAGES, oldError_messages, error_messages));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInitial() {
		return initial;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitial(String newInitial) {
		String oldInitial = initial;
		initial = newInitial;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__INITIAL, oldInitial, initial));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisabled(boolean newDisabled) {
		boolean oldDisabled = disabled;
		disabled = newDisabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__DISABLED, oldDisabled, disabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFieldSize() {
		return fieldSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldSize(int newFieldSize) {
		int oldFieldSize = fieldSize;
		fieldSize = newFieldSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__FIELD_SIZE, oldFieldSize, fieldSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyle(String newStyle) {
		String oldStyle = style;
		style = newStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchOperatorConfiguration getSearchOperatorConfiguration() {
		return searchOperatorConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSearchOperatorConfiguration(SearchOperatorConfiguration newSearchOperatorConfiguration, NotificationChain msgs) {
		SearchOperatorConfiguration oldSearchOperatorConfiguration = searchOperatorConfiguration;
		searchOperatorConfiguration = newSearchOperatorConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION, oldSearchOperatorConfiguration, newSearchOperatorConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSearchOperatorConfiguration(SearchOperatorConfiguration newSearchOperatorConfiguration) {
		if (newSearchOperatorConfiguration != searchOperatorConfiguration) {
			NotificationChain msgs = null;
			if (searchOperatorConfiguration != null)
				msgs = ((InternalEObject)searchOperatorConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION, null, msgs);
			if (newSearchOperatorConfiguration != null)
				msgs = ((InternalEObject)newSearchOperatorConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION, null, msgs);
			msgs = basicSetSearchOperatorConfiguration(newSearchOperatorConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION, newSearchOperatorConfiguration, newSearchOperatorConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getProposedOperators() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION:
				return basicSetSearchOperatorConfiguration(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.FIELD__MANDATORY:
				return isMandatory() ? Boolean.TRUE : Boolean.FALSE;
			case FormPackage.FIELD__ERROR_MESSAGES:
				return getError_messages();
			case FormPackage.FIELD__INITIAL:
				return getInitial();
			case FormPackage.FIELD__DISABLED:
				return isDisabled() ? Boolean.TRUE : Boolean.FALSE;
			case FormPackage.FIELD__FIELD_SIZE:
				return new Integer(getFieldSize());
			case FormPackage.FIELD__STYLE:
				return getStyle();
			case FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION:
				return getSearchOperatorConfiguration();
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
			case FormPackage.FIELD__MANDATORY:
				setMandatory(((Boolean)newValue).booleanValue());
				return;
			case FormPackage.FIELD__ERROR_MESSAGES:
				setError_messages((Map<String, String>)newValue);
				return;
			case FormPackage.FIELD__INITIAL:
				setInitial((String)newValue);
				return;
			case FormPackage.FIELD__DISABLED:
				setDisabled(((Boolean)newValue).booleanValue());
				return;
			case FormPackage.FIELD__FIELD_SIZE:
				setFieldSize(((Integer)newValue).intValue());
				return;
			case FormPackage.FIELD__STYLE:
				setStyle((String)newValue);
				return;
			case FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION:
				setSearchOperatorConfiguration((SearchOperatorConfiguration)newValue);
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
			case FormPackage.FIELD__MANDATORY:
				setMandatory(MANDATORY_EDEFAULT);
				return;
			case FormPackage.FIELD__ERROR_MESSAGES:
				setError_messages((Map<String, String>)null);
				return;
			case FormPackage.FIELD__INITIAL:
				setInitial(INITIAL_EDEFAULT);
				return;
			case FormPackage.FIELD__DISABLED:
				setDisabled(DISABLED_EDEFAULT);
				return;
			case FormPackage.FIELD__FIELD_SIZE:
				setFieldSize(FIELD_SIZE_EDEFAULT);
				return;
			case FormPackage.FIELD__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION:
				setSearchOperatorConfiguration((SearchOperatorConfiguration)null);
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
			case FormPackage.FIELD__MANDATORY:
				return mandatory != MANDATORY_EDEFAULT;
			case FormPackage.FIELD__ERROR_MESSAGES:
				return error_messages != null;
			case FormPackage.FIELD__INITIAL:
				return INITIAL_EDEFAULT == null ? initial != null : !INITIAL_EDEFAULT.equals(initial);
			case FormPackage.FIELD__DISABLED:
				return disabled != DISABLED_EDEFAULT;
			case FormPackage.FIELD__FIELD_SIZE:
				return fieldSize != FIELD_SIZE_EDEFAULT;
			case FormPackage.FIELD__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case FormPackage.FIELD__SEARCH_OPERATOR_CONFIGURATION:
				return searchOperatorConfiguration != null;
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
		result.append(" (mandatory: ");
		result.append(mandatory);
		result.append(", error_messages: ");
		result.append(error_messages);
		result.append(", initial: ");
		result.append(initial);
		result.append(", disabled: ");
		result.append(disabled);
		result.append(", fieldSize: ");
		result.append(fieldSize);
		result.append(", style: ");
		result.append(style);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //FieldImpl
