/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import com.bluexml.side.common.NamedModelElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.PortalLayout#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PortalLayout#isColumnMode <em>Column Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPortalLayout()
 * @model
 * @generated
 */
public interface PortalLayout extends NamedModelElement {
	/**
	 * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.Column}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPortalLayout_Columns()
	 * @model containment="true"
	 * @generated
	 */
	EList<Column> getColumns();

	/**
	 * Returns the value of the '<em><b>Column Mode</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Mode</em>' attribute.
	 * @see #setColumnMode(boolean)
	 * @see com.bluexml.side.portal.PortalPackage#getPortalLayout_ColumnMode()
	 * @model default="true"
	 * @generated
	 */
	boolean isColumnMode();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortalLayout#isColumnMode <em>Column Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Mode</em>' attribute.
	 * @see #isColumnMode()
	 * @generated
	 */
	void setColumnMode(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.columns -> union(self.columns->closure(subColumns)) ->sortedBy(x:Column|x.name)'"
	 * @generated
	 */
	EList<Column> getAllColumns();

} // PortalLayout
