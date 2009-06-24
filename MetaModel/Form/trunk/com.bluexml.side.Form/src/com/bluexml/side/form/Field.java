/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition : The form has a list of fields which inherits of the Field element. A Field is usually bound to an attribute of the class diagram (except for FreeText) and have some special features (by e.g. a Date Field can have min and max date). Each kind of field will have different generated input field or/and different kind of rules validation.
 * Operations:
 * - The Operation ‘Group in a new group’ allows to group fields in a FormGroup (logical group). This group can be specialized to change his display (in tab, row or column by e.g.).
 * Inherits: 
 * - FormElement.
 * Rules Validation :
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.Field#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getError_messages <em>Error messages</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getInitial <em>Initial</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getFieldSize <em>Field Size</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getStyle <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getField()
 * @model abstract="true"
 * @generated
 */
public interface Field extends FormElement {
	/**
	 * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'mandatory' attribute specifies if a field is required or not.
	 * 
	 * Constraint/limit: The field must be filled in order to validate the form. The message for all required fields will appear if the field is not filled.
	 * 
	 * Example:
	 * - 'false': the field is not mandatory.
	 * - 'true': the field is mandatory. The label of the field will be followed by a star.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mandatory</em>' attribute.
	 * @see #setMandatory(boolean)
	 * @see com.bluexml.side.form.FormPackage#getField_Mandatory()
	 * @model
	 * @generated
	 */
	boolean isMandatory();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#isMandatory <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mandatory</em>' attribute.
	 * @see #isMandatory()
	 * @generated
	 */
	void setMandatory(boolean value);

	/**
	 * Returns the value of the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error messages</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'error_messages' attribute is used to specify a text message in the case where the input is not valid.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Error messages</em>' attribute.
	 * @see #setError_messages(Map)
	 * @see com.bluexml.side.form.FormPackage#getField_Error_messages()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, String> getError_messages();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getError_messages <em>Error messages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error messages</em>' attribute.
	 * @see #getError_messages()
	 * @generated
	 */
	void setError_messages(Map<String, String> value);

	/**
	 * Returns the value of the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'initial' attribute specifies a defauld value for a field.
	 * 
	 * Constraint/limit: 
	 * - If the field is linked to an enumeration, the initial value must be the exact value of an item of the enumeration but there is not validation performed either at modeling or generation: if a wrong value has been provided, no default value will be set up in the enumeration widget.
	 * - To specify an initial value for a field, same for a form of class diagram: add to the url the parameter &XX=YY. YY is an initial value; it follows the same rules as the value of the attribute 'initial' in the form modeler. XX is an id name of the field to initialize. For more flexibility, there are two different ways to initialize a field. The first way is to used the name that appears in the element 'uniqueName' which can be found in the file mapping.xml under the tag 'field' of the target field (for example: field_11). The second way is to used id name of the element 'alfrescoName' of the file mappin.xml (for example: modelcyvel_Fiche_titre).
	 * - In the case where different fields have the same alfresco attribute, all these fields can have a same initial by setting '<alfrescoName>=<value>'. A particular field can have a different value to the other by setting '<uniqueName>=<value>'.
	 * 
	 * Example: initial=Book
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Initial</em>' attribute.
	 * @see #setInitial(String)
	 * @see com.bluexml.side.form.FormPackage#getField_Initial()
	 * @model
	 * @generated
	 */
	String getInitial();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getInitial <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial</em>' attribute.
	 * @see #getInitial()
	 * @generated
	 */
	void setInitial(String value);

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'disabled' attribute protects the field from being modified.
	 * 
	 * Constraint/limit: The value of the field can not be changed.
	 * 
	 * Example:
	 * - 'false': the field can be modified.
	 * - 'true': the field can not be modified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Disabled</em>' attribute.
	 * @see #setDisabled(boolean)
	 * @see com.bluexml.side.form.FormPackage#getField_Disabled()
	 * @model
	 * @generated
	 */
	boolean isDisabled();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#isDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #isDisabled()
	 * @generated
	 */
	void setDisabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'fieldSize' attribute specifies the number of element to show for a selection list.
	 * 
	 * Constraint/limit: When there are two relations R1 and R2 between two classes A and B, the value of field size set for R1 will be set for R2 too, even there is no value for field size of R2.
	 * 
	 * Example: Field Size = 10 will limit to 10 the number of elements in a generated selection list.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field Size</em>' attribute.
	 * @see #setFieldSize(int)
	 * @see com.bluexml.side.form.FormPackage#getField_FieldSize()
	 * @model
	 * @generated
	 */
	int getFieldSize();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getFieldSize <em>Field Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Size</em>' attribute.
	 * @see #getFieldSize()
	 * @generated
	 */
	void setFieldSize(int value);

	/**
	 * Returns the value of the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' attribute.
	 * @see #setStyle(String)
	 * @see com.bluexml.side.form.FormPackage#getField_Style()
	 * @model
	 * @generated
	 */
	String getStyle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' attribute.
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(String value);

} // Field
