/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.AbstractView;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Portlet Internal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.PortletInternalImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletInternalImpl#getView <em>View</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletInternalImpl#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortletInternalImpl extends PortalModelElementImpl implements PortletInternal {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final InternalPortletType TYPE_EDEFAULT = InternalPortletType.VIEW;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected InternalPortletType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getView() <em>View</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getView()
	 * @generated
	 * @ordered
	 */
	protected AbstractView view;

	/**
	 * The cached value of the '{@link #getForm() <em>Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForm()
	 * @generated
	 * @ordered
	 */
	protected FormCollection form;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortletInternalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.PORTLET_INTERNAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalPortletType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(InternalPortletType newType) {
		InternalPortletType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET_INTERNAL__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractView getView() {
		if (view != null && view.eIsProxy()) {
			InternalEObject oldView = (InternalEObject)view;
			view = (AbstractView)eResolveProxy(oldView);
			if (view != oldView) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.PORTLET_INTERNAL__VIEW, oldView, view));
			}
		}
		return view;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractView basicGetView() {
		return view;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setView(AbstractView newView) {
		AbstractView oldView = view;
		view = newView;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET_INTERNAL__VIEW, oldView, view));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormCollection getForm() {
		if (form != null && form.eIsProxy()) {
			InternalEObject oldForm = (InternalEObject)form;
			form = (FormCollection)eResolveProxy(oldForm);
			if (form != oldForm) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.PORTLET_INTERNAL__FORM, oldForm, form));
			}
		}
		return form;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormCollection basicGetForm() {
		return form;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForm(FormCollection newForm) {
		FormCollection oldForm = form;
		form = newForm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET_INTERNAL__FORM, oldForm, form));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PortalPackage.PORTLET_INTERNAL__TYPE:
				return getType();
			case PortalPackage.PORTLET_INTERNAL__VIEW:
				if (resolve) return getView();
				return basicGetView();
			case PortalPackage.PORTLET_INTERNAL__FORM:
				if (resolve) return getForm();
				return basicGetForm();
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
			case PortalPackage.PORTLET_INTERNAL__TYPE:
				setType((InternalPortletType)newValue);
				return;
			case PortalPackage.PORTLET_INTERNAL__VIEW:
				setView((AbstractView)newValue);
				return;
			case PortalPackage.PORTLET_INTERNAL__FORM:
				setForm((FormCollection)newValue);
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
			case PortalPackage.PORTLET_INTERNAL__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case PortalPackage.PORTLET_INTERNAL__VIEW:
				setView((AbstractView)null);
				return;
			case PortalPackage.PORTLET_INTERNAL__FORM:
				setForm((FormCollection)null);
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
			case PortalPackage.PORTLET_INTERNAL__TYPE:
				return type != TYPE_EDEFAULT;
			case PortalPackage.PORTLET_INTERNAL__VIEW:
				return view != null;
			case PortalPackage.PORTLET_INTERNAL__FORM:
				return form != null;
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //PortletInternalImpl
