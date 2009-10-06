/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Relation Ship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#getIdLeft <em>Id Left</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#getIdRight <em>Id Right</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isMandatoryLeft <em>Mandatory Left</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isMandatoryRight <em>Mandatory Right</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isManyLeft <em>Many Left</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isManyRight <em>Many Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentRelationShip()
 * @model
 * @generated
 */
public interface ComponentRelationShip extends ComponentProperty {
	/**
	 * Returns the value of the '<em><b>Id Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Left</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Left</em>' reference.
	 * @see #setIdLeft(Field)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentRelationShip_IdLeft()
	 * @model
	 * @generated
	 */
	Field getIdLeft();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#getIdLeft <em>Id Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Left</em>' reference.
	 * @see #getIdLeft()
	 * @generated
	 */
	void setIdLeft(Field value);

	/**
	 * Returns the value of the '<em><b>Id Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Right</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Right</em>' reference.
	 * @see #setIdRight(Field)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentRelationShip_IdRight()
	 * @model
	 * @generated
	 */
	Field getIdRight();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#getIdRight <em>Id Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Right</em>' reference.
	 * @see #getIdRight()
	 * @generated
	 */
	void setIdRight(Field value);

	/**
	 * Returns the value of the '<em><b>Mandatory Left</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Left</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory Left</em>' attribute.
	 * @see #setMandatoryLeft(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentRelationShip_MandatoryLeft()
	 * @model
	 * @generated
	 */
	boolean isMandatoryLeft();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isMandatoryLeft <em>Mandatory Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mandatory Left</em>' attribute.
	 * @see #isMandatoryLeft()
	 * @generated
	 */
	void setMandatoryLeft(boolean value);

	/**
	 * Returns the value of the '<em><b>Mandatory Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Right</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory Right</em>' attribute.
	 * @see #setMandatoryRight(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentRelationShip_MandatoryRight()
	 * @model
	 * @generated
	 */
	boolean isMandatoryRight();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isMandatoryRight <em>Mandatory Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mandatory Right</em>' attribute.
	 * @see #isMandatoryRight()
	 * @generated
	 */
	void setMandatoryRight(boolean value);

	/**
	 * Returns the value of the '<em><b>Many Left</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many Left</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Many Left</em>' attribute.
	 * @see #setManyLeft(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentRelationShip_ManyLeft()
	 * @model
	 * @generated
	 */
	boolean isManyLeft();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isManyLeft <em>Many Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Many Left</em>' attribute.
	 * @see #isManyLeft()
	 * @generated
	 */
	void setManyLeft(boolean value);

	/**
	 * Returns the value of the '<em><b>Many Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many Right</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Many Right</em>' attribute.
	 * @see #setManyRight(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentRelationShip_ManyRight()
	 * @model
	 * @generated
	 */
	boolean isManyRight();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isManyRight <em>Many Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Many Right</em>' attribute.
	 * @see #isManyRight()
	 * @generated
	 */
	void setManyRight(boolean value);

} // ComponentRelationShip
