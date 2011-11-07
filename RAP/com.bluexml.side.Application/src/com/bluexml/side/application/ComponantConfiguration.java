/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Componant Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getId_techno_version <em>Id techno version</em>}</li>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getOptions <em>Options</em>}</li>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getImpl_class <em>Impl class</em>}</li>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getId <em>Id</em>}</li>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getMetaModelName <em>Meta Model Name</em>}</li>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getTechnologyVersionName <em>Technology Version Name</em>}</li>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getTechnologyName <em>Technology Name</em>}</li>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getModuleContraints <em>Module Contraints</em>}</li>
 *   <li>{@link com.bluexml.side.application.ComponantConfiguration#getContributorId <em>Contributor Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration()
 * @model abstract="true"
 * @generated
 */
public interface ComponantConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Id techno version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id techno version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id techno version</em>' attribute.
	 * @see #setId_techno_version(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_Id_techno_version()
	 * @model
	 * @generated
	 */
	String getId_techno_version();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ComponantConfiguration#getId_techno_version <em>Id techno version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id techno version</em>' attribute.
	 * @see #getId_techno_version()
	 * @generated
	 */
	void setId_techno_version(String value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.application.Option}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference list.
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_Options()
	 * @model containment="true"
	 * @generated
	 */
	EList<Option> getOptions();

	/**
	 * Returns the value of the '<em><b>Impl class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Impl class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * doc impl_class
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Impl class</em>' attribute.
	 * @see #setImpl_class(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_Impl_class()
	 * @model
	 * @generated
	 */
	String getImpl_class();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ComponantConfiguration#getImpl_class <em>Impl class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Impl class</em>' attribute.
	 * @see #getImpl_class()
	 * @generated
	 */
	void setImpl_class(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ComponantConfiguration#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Meta Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Model Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meta Model Name</em>' attribute.
	 * @see #setMetaModelName(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_MetaModelName()
	 * @model
	 * @generated
	 */
	String getMetaModelName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ComponantConfiguration#getMetaModelName <em>Meta Model Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Meta Model Name</em>' attribute.
	 * @see #getMetaModelName()
	 * @generated
	 */
	void setMetaModelName(String value);

	/**
	 * Returns the value of the '<em><b>Technology Version Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Technology Version Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Technology Version Name</em>' attribute.
	 * @see #setTechnologyVersionName(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_TechnologyVersionName()
	 * @model
	 * @generated
	 */
	String getTechnologyVersionName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ComponantConfiguration#getTechnologyVersionName <em>Technology Version Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Technology Version Name</em>' attribute.
	 * @see #getTechnologyVersionName()
	 * @generated
	 */
	void setTechnologyVersionName(String value);

	/**
	 * Returns the value of the '<em><b>Technology Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Technology Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Technology Name</em>' attribute.
	 * @see #setTechnologyName(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_TechnologyName()
	 * @model
	 * @generated
	 */
	String getTechnologyName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ComponantConfiguration#getTechnologyName <em>Technology Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Technology Name</em>' attribute.
	 * @see #getTechnologyName()
	 * @generated
	 */
	void setTechnologyName(String value);

	/**
	 * Returns the value of the '<em><b>Module Contraints</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.application.ModuleConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module Contraints</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Contraints</em>' containment reference list.
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_ModuleContraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<ModuleConstraint> getModuleContraints();

	/**
	 * Returns the value of the '<em><b>Contributor Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * doc impl_class
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contributor Id</em>' attribute.
	 * @see #setContributorId(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getComponantConfiguration_ContributorId()
	 * @model
	 * @generated
	 */
	String getContributorId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ComponantConfiguration#getContributorId <em>Contributor Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contributor Id</em>' attribute.
	 * @see #getContributorId()
	 * @generated
	 */
	void setContributorId(String value);

} // ComponantConfiguration
