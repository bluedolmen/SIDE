/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import com.bluexml.side.common.MetaInfoGroup;
import com.bluexml.side.common.NamedModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Styling</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * use to setup presentation options wath can not be lised because of technical dependencies (visualizer specification)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Styling#getStylesheetId <em>Stylesheet Id</em>}</li>
 *   <li>{@link com.bluexml.side.view.Styling#getHeight <em>Height</em>}</li>
 *   <li>{@link com.bluexml.side.view.Styling#getWidth <em>Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getStyling()
 * @model
 * @generated
 */
public interface Styling extends MetaInfoGroup {
	/**
	 * Returns the value of the '<em><b>Stylesheet Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * this can be an identifier for CSS, xsl file or anything else that can manage style maybe a theme id,
	 * nb : styles information are not available for generation purpose in this case
	 * if you need to store style information in the model use instead metaData or available Style properties
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stylesheet Id</em>' attribute.
	 * @see #setStylesheetId(String)
	 * @see com.bluexml.side.view.ViewPackage#getStyling_StylesheetId()
	 * @model
	 * @generated
	 */
	String getStylesheetId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Styling#getStylesheetId <em>Stylesheet Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stylesheet Id</em>' attribute.
	 * @see #getStylesheetId()
	 * @generated
	 */
	void setStylesheetId(String value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(int)
	 * @see com.bluexml.side.view.ViewPackage#getStyling_Height()
	 * @model
	 * @generated
	 */
	int getHeight();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Styling#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(int)
	 * @see com.bluexml.side.view.ViewPackage#getStyling_Width()
	 * @model
	 * @generated
	 */
	int getWidth();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Styling#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(int value);
		
} // Styling
