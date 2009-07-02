/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Component</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.bluexml.side.common.CommonPackage#getOperationComponent()
 * @model abstract="true"
 * @generated
 */
public interface OperationComponent extends NamedModelElement {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if (self.oclIsKindOf(OperationGroup)) then\n\tself.oclAsType(OperationGroup).children->iterate(e:OperationComponent; result :Set(OperationComponent)=Set{}|result->union(e.getOperations()))->flatten()->asOrderedSet()\nelse\n\tself.oclAsType(OperationComponent)->asOrderedSet()\nendif'"
	 * @generated
	 */
	EList<OperationComponent> getOperations();
} // OperationComponent
