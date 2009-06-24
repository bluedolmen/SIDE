/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: This is the root element which is the first create element. All forms will be child of this element.  It has no corresponding elements in the Class diagram and is usually not generated.
 * Operations:
 * - The operations ту might be called when your Class diagram have been changed (attribute addition, change of type и) to propagate this changes in your forms. The changes which will be propagated are: attribute addition, attribute removal. The changes which are not propagated implies a manual update in the concerned forms.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormCollection#getForms <em>Forms</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFormCollection()
 * @model
 * @generated
 */
public interface FormCollection extends EObject {
	/**
	 * Returns the value of the '<em><b>Forms</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.form.FormContainer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forms</em>' containment reference list.
	 * @see com.bluexml.side.form.FormPackage#getFormCollection_Forms()
	 * @model containment="true"
	 * @generated
	 */
	EList<FormContainer> getForms();

} // FormCollection
