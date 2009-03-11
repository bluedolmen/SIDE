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
 * A representation of the model object '<em><b>Clazz</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getGeneralizations <em>Generalizations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getAspects <em>Aspects</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#isIsDeprecated <em>Is Deprecated</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getHasView <em>Has View</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getClazz()
 * @model
 * @generated
 */
public interface Clazz extends AbstractClass {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

	/**
	 * Returns the value of the '<em><b>Generalizations</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Clazz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generalizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generalizations</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_Generalizations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Clazz> getGeneralizations();

	/**
	 * Returns the value of the '<em><b>Aspects</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Aspect}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aspects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aspects</em>' reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_Aspects()
	 * @model
	 * @generated
	 */
	EList<Aspect> getAspects();

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_IsAbstract()
	 * @model
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Clazz#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Deprecated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Deprecated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Deprecated</em>' attribute.
	 * @see #setIsDeprecated(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_IsDeprecated()
	 * @model
	 * @generated
	 */
	boolean isIsDeprecated();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Clazz#isIsDeprecated <em>Is Deprecated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Deprecated</em>' attribute.
	 * @see #isIsDeprecated()
	 * @generated
	 */
	void setIsDeprecated(boolean value);

	/**
	 * Returns the value of the '<em><b>Has View</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.View}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has View</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has View</em>' reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_HasView()
	 * @model
	 * @generated
	 */
	EList<View> getHasView();

} // Clazz
