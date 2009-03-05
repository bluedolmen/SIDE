/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Association#getSource <em>Source</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getDestination <em>Destination</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#isIsNavigableSRC <em>Is Navigable SRC</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#isIsNavigableTARGET <em>Is Navigable TARGET</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getMinSRC <em>Min SRC</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getMaxSRC <em>Max SRC</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getMinTARGET <em>Min TARGET</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getMaxTARGET <em>Max TARGET</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getAssociationType <em>Association Type</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getAssociationsClass <em>Associations Class</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getRoleSrc <em>Role Src</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getRoleTarget <em>Role Target</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getRoleSrcTitle <em>Role Src Title</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getRoleTargetTitle <em>Role Target Title</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation()
 * @model
 * @generated
 */
public interface Association extends NamedClassModelElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(ClassModelElement)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_Source()
	 * @model
	 * @generated
	 */
	ClassModelElement getSource();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(ClassModelElement value);

	/**
	 * Returns the value of the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destination</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination</em>' reference.
	 * @see #setDestination(ClassModelElement)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_Destination()
	 * @model
	 * @generated
	 */
	ClassModelElement getDestination();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getDestination <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination</em>' reference.
	 * @see #getDestination()
	 * @generated
	 */
	void setDestination(ClassModelElement value);

	/**
	 * Returns the value of the '<em><b>Is Navigable SRC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Navigable SRC</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Navigable SRC</em>' attribute.
	 * @see #setIsNavigableSRC(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_IsNavigableSRC()
	 * @model
	 * @generated
	 */
	boolean isIsNavigableSRC();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#isIsNavigableSRC <em>Is Navigable SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Navigable SRC</em>' attribute.
	 * @see #isIsNavigableSRC()
	 * @generated
	 */
	void setIsNavigableSRC(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Navigable TARGET</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Navigable TARGET</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Navigable TARGET</em>' attribute.
	 * @see #setIsNavigableTARGET(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_IsNavigableTARGET()
	 * @model
	 * @generated
	 */
	boolean isIsNavigableTARGET();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#isIsNavigableTARGET <em>Is Navigable TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Navigable TARGET</em>' attribute.
	 * @see #isIsNavigableTARGET()
	 * @generated
	 */
	void setIsNavigableTARGET(boolean value);

	/**
	 * Returns the value of the '<em><b>Min SRC</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min SRC</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min SRC</em>' attribute.
	 * @see #setMinSRC(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_MinSRC()
	 * @model default="1"
	 * @generated
	 */
	String getMinSRC();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getMinSRC <em>Min SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min SRC</em>' attribute.
	 * @see #getMinSRC()
	 * @generated
	 */
	void setMinSRC(String value);

	/**
	 * Returns the value of the '<em><b>Max SRC</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max SRC</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max SRC</em>' attribute.
	 * @see #setMaxSRC(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_MaxSRC()
	 * @model default="1"
	 * @generated
	 */
	String getMaxSRC();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getMaxSRC <em>Max SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max SRC</em>' attribute.
	 * @see #getMaxSRC()
	 * @generated
	 */
	void setMaxSRC(String value);

	/**
	 * Returns the value of the '<em><b>Min TARGET</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min TARGET</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min TARGET</em>' attribute.
	 * @see #setMinTARGET(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_MinTARGET()
	 * @model default="1"
	 * @generated
	 */
	String getMinTARGET();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getMinTARGET <em>Min TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min TARGET</em>' attribute.
	 * @see #getMinTARGET()
	 * @generated
	 */
	void setMinTARGET(String value);

	/**
	 * Returns the value of the '<em><b>Max TARGET</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max TARGET</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max TARGET</em>' attribute.
	 * @see #setMaxTARGET(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_MaxTARGET()
	 * @model default="1"
	 * @generated
	 */
	String getMaxTARGET();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getMaxTARGET <em>Max TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max TARGET</em>' attribute.
	 * @see #getMaxTARGET()
	 * @generated
	 */
	void setMaxTARGET(String value);

	/**
	 * Returns the value of the '<em><b>Association Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.clazz.AssociationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Type</em>' attribute.
	 * @see com.bluexml.side.clazz.AssociationType
	 * @see #setAssociationType(AssociationType)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_AssociationType()
	 * @model
	 * @generated
	 */
	AssociationType getAssociationType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getAssociationType <em>Association Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Type</em>' attribute.
	 * @see com.bluexml.side.clazz.AssociationType
	 * @see #getAssociationType()
	 * @generated
	 */
	void setAssociationType(AssociationType value);

	/**
	 * Returns the value of the '<em><b>Associations Class</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Clazz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associations Class</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associations Class</em>' reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_AssociationsClass()
	 * @model
	 * @generated
	 */
	EList<Clazz> getAssociationsClass();

	/**
	 * Returns the value of the '<em><b>Role Src</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Src</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Src</em>' attribute.
	 * @see #setRoleSrc(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_RoleSrc()
	 * @model
	 * @generated
	 */
	String getRoleSrc();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getRoleSrc <em>Role Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Src</em>' attribute.
	 * @see #getRoleSrc()
	 * @generated
	 */
	void setRoleSrc(String value);

	/**
	 * Returns the value of the '<em><b>Role Target</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Target</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Target</em>' attribute.
	 * @see #setRoleTarget(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_RoleTarget()
	 * @model default=""
	 * @generated
	 */
	String getRoleTarget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getRoleTarget <em>Role Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Target</em>' attribute.
	 * @see #getRoleTarget()
	 * @generated
	 */
	void setRoleTarget(String value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Role Src Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Src Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Src Title</em>' attribute.
	 * @see #setRoleSrcTitle(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_RoleSrcTitle()
	 * @model
	 * @generated
	 */
	String getRoleSrcTitle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getRoleSrcTitle <em>Role Src Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Src Title</em>' attribute.
	 * @see #getRoleSrcTitle()
	 * @generated
	 */
	void setRoleSrcTitle(String value);

	/**
	 * Returns the value of the '<em><b>Role Target Title</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Target Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Target Title</em>' attribute.
	 * @see #setRoleTargetTitle(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_RoleTargetTitle()
	 * @model default=""
	 * @generated
	 */
	String getRoleTargetTitle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getRoleTargetTitle <em>Role Target Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Target Title</em>' attribute.
	 * @see #getRoleTargetTitle()
	 * @generated
	 */
	void setRoleTargetTitle(String value);

} // Association
