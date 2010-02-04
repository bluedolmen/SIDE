/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.SearchOperatorConfiguration;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import java.util.Collection;

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
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getField_size <em>Field size</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#getMockup <em>Mockup</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FieldImpl#isDisabled <em>Disabled</em>}</li>
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
	 * The default value of the '{@link #getField_size() <em>Field size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getField_size()
	 * @generated
	 * @ordered
	 */
	protected static final int FIELD_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getField_size() <em>Field size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getField_size()
	 * @generated
	 * @ordered
	 */
	protected int field_size = FIELD_SIZE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMockup() <em>Mockup</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMockup()
	 * @generated
	 * @ordered
	 */
	protected EList<String> mockup;

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
	public int getField_size() {
		return field_size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setField_size(int newField_size) {
		int oldField_size = field_size;
		field_size = newField_size;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FIELD__FIELD_SIZE, oldField_size, field_size));
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
	public EList<String> getMockup() {
		if (mockup == null) {
			mockup = new EDataTypeUniqueEList<String>(String.class, this, FormPackage.FIELD__MOCKUP);
		}
		return mockup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns all the proposed operators for a given field.
	 * This method works through convention by looking an enumeration of name 
	 * "FieldType" + "SearchOperators". For example, if ask for proposed operators
	 * of the "CharField" class, we have to look for a enumeration of name "CharFieldSearchOperators".
	 * This method works on inherited classes, thus the returned search operator set is the
	 * closure of the search operators on all the inherited classes.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<String> getInitialProposedOperators() {
		EList<String> result = new UniqueEList<String>();
		EList<EClass> classesToProcess = new BasicEList<EClass>();
		classesToProcess.addAll(eClass().getEAllSuperTypes());
		classesToProcess.removeAll(FormPackage.eINSTANCE.getField().getEAllSuperTypes());
		classesToProcess.add(eClass());

		for (EClass currentClass : classesToProcess) {
			String enumerationName = currentClass.getName() + "SearchOperators";
		
			EClassifier eClassifier = FormPackage.eINSTANCE.getEClassifier(enumerationName);
		
			if (eClassifier != null) {
				EEnum eEnum = (EEnum) eClassifier;
				List<EEnumLiteral> enumLiterals = eEnum.getELiterals();
				for (EEnumLiteral enumLiteral : enumLiterals) {
					result.add(enumLiteral.getName());
				}
			}
		}
			
		return result;
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
				return isMandatory();
			case FormPackage.FIELD__ERROR_MESSAGES:
				return getError_messages();
			case FormPackage.FIELD__INITIAL:
				return getInitial();
			case FormPackage.FIELD__FIELD_SIZE:
				return getField_size();
			case FormPackage.FIELD__MOCKUP:
				return getMockup();
			case FormPackage.FIELD__DISABLED:
				return isDisabled();
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
				setMandatory((Boolean)newValue);
				return;
			case FormPackage.FIELD__ERROR_MESSAGES:
				setError_messages((Map<String, String>)newValue);
				return;
			case FormPackage.FIELD__INITIAL:
				setInitial((String)newValue);
				return;
			case FormPackage.FIELD__FIELD_SIZE:
				setField_size((Integer)newValue);
				return;
			case FormPackage.FIELD__MOCKUP:
				getMockup().clear();
				getMockup().addAll((Collection<? extends String>)newValue);
				return;
			case FormPackage.FIELD__DISABLED:
				setDisabled((Boolean)newValue);
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
			case FormPackage.FIELD__FIELD_SIZE:
				setField_size(FIELD_SIZE_EDEFAULT);
				return;
			case FormPackage.FIELD__MOCKUP:
				getMockup().clear();
				return;
			case FormPackage.FIELD__DISABLED:
				setDisabled(DISABLED_EDEFAULT);
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
			case FormPackage.FIELD__FIELD_SIZE:
				return field_size != FIELD_SIZE_EDEFAULT;
			case FormPackage.FIELD__MOCKUP:
				return mockup != null && !mockup.isEmpty();
			case FormPackage.FIELD__DISABLED:
				return disabled != DISABLED_EDEFAULT;
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
		result.append(", field_size: ");
		result.append(field_size);
		result.append(", mockup: ");
		result.append(mockup);
		result.append(", disabled: ");
		result.append(disabled);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //FieldImpl
