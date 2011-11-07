/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Field#getPatern <em>Patern</em>}</li>
 *   <li>{@link com.bluexml.side.view.Field#getPaternLanguage <em>Patern Language</em>}</li>
 *   <li>{@link com.bluexml.side.view.Field#getPath <em>Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getField()
 * @model abstract="true"
 *        annotation="http://www.bluexml.com/OCL noFieldMapped='not self.mapTo.oclIsUndefined()'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='noFieldMapped'"
 * @generated
 */
public interface Field extends FieldElement {
	/**
	 * Returns the value of the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Patern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Patern</em>' attribute.
	 * @see #setPatern(String)
	 * @see com.bluexml.side.view.ViewPackage#getField_Patern()
	 * @model
	 * @generated
	 */
	String getPatern();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Field#getPatern <em>Patern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Patern</em>' attribute.
	 * @see #getPatern()
	 * @generated
	 */
	void setPatern(String value);

	/**
	 * Returns the value of the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Patern Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Patern Language</em>' attribute.
	 * @see #setPaternLanguage(String)
	 * @see com.bluexml.side.view.ViewPackage#getField_PaternLanguage()
	 * @model
	 * @generated
	 */
	String getPaternLanguage();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Field#getPaternLanguage <em>Patern Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Patern Language</em>' attribute.
	 * @see #getPaternLanguage()
	 * @generated
	 */
	void setPaternLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' reference list.
	 * @see com.bluexml.side.view.ViewPackage#getField_Path()
	 * @model
	 * @generated
	 */
	EList<EObject> getPath();
		
} // Field
