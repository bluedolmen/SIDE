/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation Ship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.RelationShip#getSource <em>Source</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.RelationShip#getTarget <em>Target</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.RelationShip#getSourceMin <em>Source Min</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.RelationShip#getSourceMax <em>Source Max</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.RelationShip#getTargetMin <em>Target Min</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.RelationShip#getTargetMax <em>Target Max</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.RequirementsPackage#getRelationShip()
 * @model
 * @generated
 */
public interface RelationShip extends BasicElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Entity)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getRelationShip_Source()
	 * @model required="true"
	 * @generated
	 */
	Entity getSource();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.RelationShip#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Entity value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Entity)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getRelationShip_Target()
	 * @model required="true"
	 * @generated
	 */
	Entity getTarget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.RelationShip#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Entity value);

	/**
	 * Returns the value of the '<em><b>Source Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Min</em>' attribute.
	 * @see #setSourceMin(int)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getRelationShip_SourceMin()
	 * @model
	 * @generated
	 */
	int getSourceMin();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.RelationShip#getSourceMin <em>Source Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Min</em>' attribute.
	 * @see #getSourceMin()
	 * @generated
	 */
	void setSourceMin(int value);

	/**
	 * Returns the value of the '<em><b>Source Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Max</em>' attribute.
	 * @see #setSourceMax(int)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getRelationShip_SourceMax()
	 * @model
	 * @generated
	 */
	int getSourceMax();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.RelationShip#getSourceMax <em>Source Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Max</em>' attribute.
	 * @see #getSourceMax()
	 * @generated
	 */
	void setSourceMax(int value);

	/**
	 * Returns the value of the '<em><b>Target Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Min</em>' attribute.
	 * @see #setTargetMin(int)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getRelationShip_TargetMin()
	 * @model
	 * @generated
	 */
	int getTargetMin();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.RelationShip#getTargetMin <em>Target Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Min</em>' attribute.
	 * @see #getTargetMin()
	 * @generated
	 */
	void setTargetMin(int value);

	/**
	 * Returns the value of the '<em><b>Target Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Max</em>' attribute.
	 * @see #setTargetMax(int)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getRelationShip_TargetMax()
	 * @model
	 * @generated
	 */
	int getTargetMax();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.RelationShip#getTargetMax <em>Target Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Max</em>' attribute.
	 * @see #getTargetMax()
	 * @generated
	 */
	void setTargetMax(int value);

} // RelationShip
