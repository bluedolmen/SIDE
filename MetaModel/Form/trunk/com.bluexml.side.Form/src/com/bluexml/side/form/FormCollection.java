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
 * Definition: This is the root element which is the first created element of the form model. All forms will be child of this element. 
 * It has no corresponding elements in the Data model and is usually not generated.
 * Operations:
 * - The operation ‘Synchronize with Class Diagram’ might be called when your Data model has been changed to propagate these changes in your forms. 
 * The changes which will be propagated are: 
 *  . New attribute (in Data Diagram) are added to the form.
 *  . Deleted attribute (in Data Diagram) are removed from the form.
 *  . Attribute which have been put as mandatory (in Data Diagram) will be put in form if they have been deleted in field’s form.
 *  . If an attribute (in Data Diagram) have been changed its type (eg : String to Date) the kind of field will be changed (CharFiled to DateField).
 * All the other changes which are not propagated imply a manual update in the concerned forms.
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
 * @model abstract="true"
 * @generated
 */
public interface FormCollection extends com.bluexml.side.common.Package {
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
