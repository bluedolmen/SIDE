/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generator Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.application.GeneratorConfiguration#getId_metamodel <em>Id metamodel</em>}</li>
 *   <li>{@link com.bluexml.side.application.GeneratorConfiguration#getGeneratorName <em>Generator Name</em>}</li>
 *   <li>{@link com.bluexml.side.application.GeneratorConfiguration#getGeneratorId <em>Generator Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.application.ApplicationPackage#getGeneratorConfiguration()
 * @model
 * @generated
 */
public interface GeneratorConfiguration extends ComponantConfiguration {
	/**
	 * Returns the value of the '<em><b>Id metamodel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id metamodel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id metamodel</em>' attribute.
	 * @see #setId_metamodel(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getGeneratorConfiguration_Id_metamodel()
	 * @model
	 * @generated
	 */
	String getId_metamodel();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.GeneratorConfiguration#getId_metamodel <em>Id metamodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id metamodel</em>' attribute.
	 * @see #getId_metamodel()
	 * @generated
	 */
	void setId_metamodel(String value);

	/**
	 * Returns the value of the '<em><b>Generator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generator Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generator Name</em>' attribute.
	 * @see #setGeneratorName(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getGeneratorConfiguration_GeneratorName()
	 * @model
	 * @generated
	 */
	String getGeneratorName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.GeneratorConfiguration#getGeneratorName <em>Generator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generator Name</em>' attribute.
	 * @see #getGeneratorName()
	 * @generated
	 */
	void setGeneratorName(String value);

	/**
	 * Returns the value of the '<em><b>Generator Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generator Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generator Id</em>' attribute.
	 * @see #setGeneratorId(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getGeneratorConfiguration_GeneratorId()
	 * @model
	 * @generated
	 */
	String getGeneratorId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.GeneratorConfiguration#getGeneratorId <em>Generator Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generator Id</em>' attribute.
	 * @see #getGeneratorId()
	 * @generated
	 */
	void setGeneratorId(String value);

} // GeneratorConfiguration
