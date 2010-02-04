/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormGroupPresentationType;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.SearchField;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Form Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.FormGroupImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FormGroupImpl#getPresentation <em>Presentation</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.FormGroupImpl#getDisabled <em>Disabled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormGroupImpl extends FormElementImpl implements FormGroup {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<FormElement> children;

	/**
	 * The default value of the '{@link #getPresentation() <em>Presentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresentation()
	 * @generated
	 * @ordered
	 */
	protected static final FormGroupPresentationType PRESENTATION_EDEFAULT = FormGroupPresentationType.AUTO;

	/**
	 * The cached value of the '{@link #getPresentation() <em>Presentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresentation()
	 * @generated
	 * @ordered
	 */
	protected FormGroupPresentationType presentation = PRESENTATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDisabled() <em>Disabled</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabled()
	 * @generated
	 * @ordered
	 */
	protected EList<FormElement> disabled;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.FORM_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FormElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<FormElement>(FormElement.class, this, FormPackage.FORM_GROUP__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormGroupPresentationType getPresentation() {
		return presentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPresentation(FormGroupPresentationType newPresentation) {
		FormGroupPresentationType oldPresentation = presentation;
		presentation = newPresentation == null ? PRESENTATION_EDEFAULT : newPresentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_GROUP__PRESENTATION, oldPresentation, presentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FormElement> getDisabled() {
		if (disabled == null) {
			disabled = new EObjectContainmentEList<FormElement>(FormElement.class, this, FormPackage.FORM_GROUP__DISABLED);
		}
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getFields() {
		if (getFieldsBodyOCL == null) {
			EOperation eOperation = FormPackage.Literals.FORM_GROUP.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(FormPackage.Literals.FORM_GROUP, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getFieldsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getFieldsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getFields <em>Get Fields</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields
	 * @generated
	 */
	private static OCLExpression<EClassifier> getFieldsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SearchField> getSearchFields() {
		if (getSearchFieldsBodyOCL == null) {
			EOperation eOperation = FormPackage.Literals.FORM_GROUP.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(FormPackage.Literals.FORM_GROUP, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getSearchFieldsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getSearchFieldsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<SearchField> result = (Collection<SearchField>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<SearchField>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getSearchFields <em>Get Search Fields</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearchFields
	 * @generated
	 */
	private static OCLExpression<EClassifier> getSearchFieldsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FormPackage.FORM_GROUP__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case FormPackage.FORM_GROUP__DISABLED:
				return ((InternalEList<?>)getDisabled()).basicRemove(otherEnd, msgs);
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
			case FormPackage.FORM_GROUP__CHILDREN:
				return getChildren();
			case FormPackage.FORM_GROUP__PRESENTATION:
				return getPresentation();
			case FormPackage.FORM_GROUP__DISABLED:
				return getDisabled();
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
			case FormPackage.FORM_GROUP__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends FormElement>)newValue);
				return;
			case FormPackage.FORM_GROUP__PRESENTATION:
				setPresentation((FormGroupPresentationType)newValue);
				return;
			case FormPackage.FORM_GROUP__DISABLED:
				getDisabled().clear();
				getDisabled().addAll((Collection<? extends FormElement>)newValue);
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
			case FormPackage.FORM_GROUP__CHILDREN:
				getChildren().clear();
				return;
			case FormPackage.FORM_GROUP__PRESENTATION:
				setPresentation(PRESENTATION_EDEFAULT);
				return;
			case FormPackage.FORM_GROUP__DISABLED:
				getDisabled().clear();
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
			case FormPackage.FORM_GROUP__CHILDREN:
				return children != null && !children.isEmpty();
			case FormPackage.FORM_GROUP__PRESENTATION:
				return presentation != PRESENTATION_EDEFAULT;
			case FormPackage.FORM_GROUP__DISABLED:
				return disabled != null && !disabled.isEmpty();
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
		result.append(" (presentation: ");
		result.append(presentation);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //FormGroupImpl
