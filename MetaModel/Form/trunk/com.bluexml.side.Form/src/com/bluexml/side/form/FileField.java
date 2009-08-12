/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: used to upload file.
 * Operations: Trasnformation to other formats
 * - Transform FileField into ImageField.
 * - Transform FileField into CharField.
 * Inherits: Field
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FileField#isInRepository <em>In Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFileField()
 * @model
 * @generated
 */
public interface FileField extends Field {

	/**
	 * Returns the value of the '<em><b>In Repository</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Repository</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'inRepository' attributes specifies if the file will be import in the target repository (for instance Alfresco) or on the disk. 
	 * In the case of Xforms generation on Alfresco, the target path to store the file is given through:
	 * - If upload on disk (inRepository=false), the target directory must be set up either in the Alfresco.properties file using the ‘upload.directory’  parameter or through the url parameter ‘uploadDirectory’.
	 * - if upload on Alfresco repository (inRepository=true), the target space must be set up either in the Alfresco.properties file using the ‘upload.repository’  parameter or through the url parameter ‘uploadRepository’.
	 * 
	 * Example:
	 * - 'false': The file will be uploaded to a directory of the file system. For instance, ‘upload.directory=c:\myWorkspace\files’ or ‘uploadDirectory=c:\myWorkspace\files’; if not set up through upload.directory or uploadDirectory, default value is the current directory. 
	 * - 'true': The file will be uploaded to the target repository. For instance, ‘upload.repository=app:company_home/cm:dictionary’ or ‘uploadRepository’= app:company_home/cm:dictionary’; if not set up through upload.repository or uploadRepository’, default value is ‘app:company_home’ for Alfresco.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>In Repository</em>' attribute.
	 * @see #setInRepository(boolean)
	 * @see com.bluexml.side.form.FormPackage#getFileField_InRepository()
	 * @model default="false"
	 * @generated
	 */
	boolean isInRepository();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FileField#isInRepository <em>In Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Repository</em>' attribute.
	 * @see #isInRepository()
	 * @generated
	 */
	void setInRepository(boolean value);
} // FileField
