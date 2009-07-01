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
 *
 * @see com.bluexml.side.view.ViewPackage#getAbstractView()
 * @model abstract="true"
 * @generated
 */
public interface AbstractView extends FieldContainer {
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
