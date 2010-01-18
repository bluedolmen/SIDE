/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import java.util.Map;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The form has a list of fields which inherits of the 'Field' abstract element. A 'Field' is usually bound to an attribute of the class diagram (except for FreeText) and have some special features (by e.g. a Date Field can have min and max date). 
 * Each kind of fields will have different generated input field or/and different kind of validation rules. The validation rules are defined to control input in the model. 
 * Operations: The Operation ‘Group in a new group’ allows to group fields in a FormGroup (logical group). This group can be specialized to change its display (in tab, row or column by e.g.).
 * Inherits: FormElement.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.Field#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getError_messages <em>Error messages</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getInitial <em>Initial</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getField_size <em>Field size</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getStyle <em>Style</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getSearchOperatorConfiguration <em>Search Operator Configuration</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getMockup <em>Mockup</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getField()
 * @model abstract="true"
 *        annotation="http://www.bluexml.com/OCL mandatoryHiddenAndNoDefaultValue='self.initial.oclIsUndefined() or self.initial = \'\' implies self.mandatory = false or self.hidden = false'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore warning='mandatoryHiddenAndNoDefaultValue' constraints='mandatoryHiddenAndNoDefaultValue'"
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
	 * Constraint/limit: The field must be filled up in order to validate the form. A message (validation rule) for all required fields will appear if they are not filled up.
	 * Example:
	 * - 'false': the field is not mandatory.
	 * - 'true': the field is mandatory. Usually, the label of the field is followed by a star.
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
	 * Constraint/Limit: The internationalization convention may be used for the error_message's value; if the value of the error_messages in the form model is of the form "#label",  the token "label" is then considered as a “delayed message”, whose value will be provided by the forms server at runtime from a language resource file. In the case of Xform Chiba for Alfresco, the language resource file is '<xforms_webapp>/WEB-INF/classes/messages.properties'.
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
	 * Definition: The 'initial' attribute specifies a default value for a field.
	 * 
	 * Constraint/limit: 
	 * - If the field is linked to an enumeration, the initial value must be the exact value of an item of the enumeration but there is not validation performed either at modeling or generation: if a wrong value has been provided, no default value will be set up in the enumeration widget.
	 * - In the case of XForms generation on Alfresco, to specify an initial value for a field, add to the url the parameter &XX=YY. YY is an initial value; it follows the same rules as the value of the attribute 'initial' in the form modeler. XX is an id name of the field to initialize. For more flexibility, there are two different ways to initialize a field. The first way is to used the name that appears in the element 'uniqueName' which can be found in the file mapping.xml under the tag 'field' of the target field (for example: field_11). The second way is to use id name of the element 'alfrescoName' of the file mappin.xml (for example: modelcyvel_Fiche_titre).
	 * - In the case of XForms generation on Alfresco and considering different fields having the same alfresco attribute, all these fields can have a same initial value by setting '<alfrescoName>=<value>'. A particular field can have a different value to the other by setting '<uniqueName>=<value>'.
	 * 
	 * Example: 
	 * 	initial=Book
	 * 	http://vmbx2k3:8080/pforms/xforms?type=modelcyvel.Article&field_11=valeur
	 * 	http://vmbx2k3:8080/pforms/xforms?type=modelcyvel.Article&modelcyvel_Fiche_accroche=valeur
	 * 
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
	 * Definition: The 'disabled' attribute protects the field from being modified if true. If false (default), it can be modified.
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
	 * Returns the value of the '<em><b>Field size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'fieldSize' attribute specifies the number of element to show for a selection list.
	 * 
	 * 
	 * Constraint/limit: When there are two relations R1 and R2 between the two same classes A and B, the value of field size set for R1 will be set for R2 too, even there is no value for field size of R2.
	 * 
	 * 
	 * Example: Field Size = 10 will limit to 10 the number of elements in a generated selection list.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field size</em>' attribute.
	 * @see #setField_size(int)
	 * @see com.bluexml.side.form.FormPackage#getField_Field_size()
	 * @model
	 * @generated
	 */
	int getField_size();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getField_size <em>Field size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field size</em>' attribute.
	 * @see #getField_size()
	 * @generated
	 */
	void setField_size(int value);

	/**
	 * Returns the value of the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: a specific style code associated to the field which may be defined in style file like css file to be applied on the field input at runtime.
	 * Constraint/Limit: the semantic of the style code is not defined in the model but is known by the targeted form engine on which will be deployed the generated forms.  In the case of XForms generation on Alfresco, this style code refers a CSS class.
	 * <!-- end-model-doc -->
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

	/**
	 * Returns the value of the '<em><b>Search Operator Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Search Operator Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Search Operator Configuration</em>' containment reference.
	 * @see #setSearchOperatorConfiguration(SearchOperatorConfiguration)
	 * @see com.bluexml.side.form.FormPackage#getField_SearchOperatorConfiguration()
	 * @model containment="true"
	 * @generated
	 */
	SearchOperatorConfiguration getSearchOperatorConfiguration();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getSearchOperatorConfiguration <em>Search Operator Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Search Operator Configuration</em>' containment reference.
	 * @see #getSearchOperatorConfiguration()
	 * @generated
	 */
	void setSearchOperatorConfiguration(SearchOperatorConfiguration value);

	/**
	 * Returns the value of the '<em><b>Mockup</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mockup</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mockup</em>' attribute list.
	 * @see com.bluexml.side.form.FormPackage#getField_Mockup()
	 * @model
	 * @generated
	 */
	EList<String> getMockup();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if self.label.oclIsUndefined() or self.label.size() = 0 then\r self.id \relse\r self.label \rendif'"
	 * @generated
	 */
	String getLabel();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getInitialProposedOperators();

} // Field
