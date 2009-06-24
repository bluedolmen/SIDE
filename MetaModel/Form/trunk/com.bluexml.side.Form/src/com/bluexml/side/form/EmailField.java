/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Email Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'EmailField' specifies an email address.
 * 
 * Constraint/limit: If the input is not an email, the EmailField background color will change.
 * 
 * Operations:
 * - 'Transform/to CharField': Transform EmailField into CharField.
 * - 'Transform/to URLField': Transform EmailField into URLField.
 * - 'Transform/to PasswordField': Transform EmailField into PasswordField.
 * - 'Transform/to RegexField': Transform EmailField into RegexField.
 * - 'Transform/to PhoneNumberField': Transform EmailField into PhoneNumberField.
 * - 'Transform/to TextField': Transform EmailField into TextField.
 * 
 * Example: contact@bluexml.com
 * 
 * Inherits: 
 * - CharField
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.form.FormPackage#getEmailField()
 * @model
 * @generated
 */
public interface EmailField extends CharField {
} // EmailField
