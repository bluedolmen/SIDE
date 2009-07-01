/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import com.bluexml.side.common.OperationComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tree</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Display data as Tree, the model Element "dataType" can be an clazz.AssociationEnd, so the tree can be buid following the Association
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Tree#getNodeOperations <em>Node Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.Tree#getNodeValue <em>Node Value</em>}</li>
 *   <li>{@link com.bluexml.side.view.Tree#getDefaultDepth <em>Default Depth</em>}</li>
 *   <li>{@link com.bluexml.side.view.Tree#getMaxDepth <em>Max Depth</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getTree()
 * @model
 * @generated
 */
public interface Tree extends Sortable, Editable, Movable, Filterable, AbstractViewOf, Actionable {
	/**
	 * Returns the value of the '<em><b>Node Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Operations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Operations</em>' containment reference.
	 * @see #setNodeOperations(OperationComponent)
	 * @see com.bluexml.side.view.ViewPackage#getTree_NodeOperations()
	 * @model containment="true"
	 * @generated
	 */
	OperationComponent getNodeOperations();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Tree#getNodeOperations <em>Node Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Operations</em>' containment reference.
	 * @see #getNodeOperations()
	 * @generated
	 */
	void setNodeOperations(OperationComponent value);

	/**
	 * Returns the value of the '<em><b>Node Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Value</em>' containment reference.
	 * @see #setNodeValue(FieldElement)
	 * @see com.bluexml.side.view.ViewPackage#getTree_NodeValue()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FieldElement getNodeValue();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Tree#getNodeValue <em>Node Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Value</em>' containment reference.
	 * @see #getNodeValue()
	 * @generated
	 */
	void setNodeValue(FieldElement value);

	/**
	 * Returns the value of the '<em><b>Default Depth</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Depth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Depth</em>' attribute.
	 * @see #setDefaultDepth(int)
	 * @see com.bluexml.side.view.ViewPackage#getTree_DefaultDepth()
	 * @model default="1"
	 * @generated
	 */
	int getDefaultDepth();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Tree#getDefaultDepth <em>Default Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Depth</em>' attribute.
	 * @see #getDefaultDepth()
	 * @generated
	 */
	void setDefaultDepth(int value);

	/**
	 * Returns the value of the '<em><b>Max Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Depth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Depth</em>' attribute.
	 * @see #setMaxDepth(int)
	 * @see com.bluexml.side.view.ViewPackage#getTree_MaxDepth()
	 * @model
	 * @generated
	 */
	int getMaxDepth();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Tree#getMaxDepth <em>Max Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Depth</em>' attribute.
	 * @see #getMaxDepth()
	 * @generated
	 */
	void setMaxDepth(int value);
		
} // Tree
