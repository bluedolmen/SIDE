/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import com.bluexml.side.common.Container;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.OperationComponent;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.AbstractView#getViewOf <em>View Of</em>}</li>
 *   <li>{@link com.bluexml.side.view.AbstractView#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getAbstractView()
 * @model abstract="true"
 * @generated
 */
public interface AbstractView extends FieldContainer {
	/**
	 * Returns the value of the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Of</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Of</em>' reference.
	 * @see #setViewOf(Container)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractView_ViewOf()
	 * @model
	 * @generated
	 */
	Container getViewOf();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractView#getViewOf <em>View Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Of</em>' reference.
	 * @see #getViewOf()
	 * @generated
	 */
	void setViewOf(Container value);

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference.
	 * @see #setOperations(OperationComponent)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractView_Operations()
	 * @model containment="true"
	 * @generated
	 */
	OperationComponent getOperations();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractView#getOperations <em>Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operations</em>' containment reference.
	 * @see #getOperations()
	 * @generated
	 */
	void setOperations(OperationComponent value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the fields of the AbstractView, excluding the FieldContainers' body='self.getCols()->children->select(oclIsKindOf(Field))->asSet()->union(self.getDirectChildFields())'"
	 * @generated
	 */
	EList<Field> getFields();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the Cols of the AbstractView' body='self.children->select(oclIsTypeOf(Col)).oclAsType(Col)'"
	 * @generated
	 */
	EList<Col> getCols();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the fields of the AbstractView, excluding the FieldContainers' body='self.children->select(oclIsKindOf(Field))'"
	 * @generated
	 */
	EList<Field> getDirectChildFields();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get inner AbtractView of the AbstractView' body='self.children->select(oclIsKindOf(AbstractView))'"
	 * @generated
	 */
	EList<AbstractView> getInnerView();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the fields of the AbstractView, including disabled Field, excluding the FieldContainers' body='self.getFields()->union(self.getDisabledFields())'"
	 * @generated
	 */
	EList<Field> getDisabledAndEnabledField();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the disabled Fields' body='self.disabled->select(oclIsKindOf(Col)).oclAsType(Col).children->select(oclIsKindOf(Field))'"
	 * @generated
	 */
	EList<Field> getDisabledFields();
		
} // AbstractView
