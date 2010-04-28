/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.view.AbstractView;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Portlet Internal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.PortletInternal#getType <em>Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PortletInternal#getView <em>View</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PortletInternal#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPortletInternal()
 * @model annotation="http://www.bluexml.com/OCL haveType='not self.type.oclIsUndefined()' isConsistent='not (self.form.oclIsUndefined() and self.view.oclIsUndefined())'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='haveType'"
 * @generated
 */
public interface PortletInternal extends PortalModelElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.portal.InternalPortletType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.bluexml.side.portal.InternalPortletType
	 * @see #setType(InternalPortletType)
	 * @see com.bluexml.side.portal.PortalPackage#getPortletInternal_Type()
	 * @model
	 * @generated
	 */
	InternalPortletType getType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletInternal#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.bluexml.side.portal.InternalPortletType
	 * @see #getType()
	 * @generated
	 */
	void setType(InternalPortletType value);

	/**
	 * Returns the value of the '<em><b>View</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View</em>' reference.
	 * @see #setView(AbstractView)
	 * @see com.bluexml.side.portal.PortalPackage#getPortletInternal_View()
	 * @model
	 * @generated
	 */
	AbstractView getView();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletInternal#getView <em>View</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View</em>' reference.
	 * @see #getView()
	 * @generated
	 */
	void setView(AbstractView value);

	/**
	 * Returns the value of the '<em><b>Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form</em>' reference.
	 * @see #setForm(FormCollection)
	 * @see com.bluexml.side.portal.PortalPackage#getPortletInternal_Form()
	 * @model
	 * @generated
	 */
	FormCollection getForm();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletInternal#getForm <em>Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form</em>' reference.
	 * @see #getForm()
	 * @generated
	 */
	void setForm(FormCollection value);

} // PortletInternal
