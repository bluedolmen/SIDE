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
 * A representation of the model object '<em><b>Abstract Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.AbstractContainer#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.AbstractContainer#getAssociations <em>Associations</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAbstractContainer()
 * @model abstract="true"
 *        annotation="http://www.bluexml.com/OCL TwoModelElementWithSameName='AbstractContainer.allInstances()->select(a | a.name = self.name and a <> self)->size() = 0' NameNull='not self.name.oclIsUndefined() and self.name <> \'\'' noSpecialCharacters='self.name.regexMatch(\'\\w\') <> null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='TwoModelElementWithSameName NameNull noSpecialCharacters'"
 * @generated
 */
public interface AbstractContainer extends TitledNamedClassModelElement {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getAbstractContainer_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Associations</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associations</em>' reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getAbstractContainer_Associations()
	 * @model
	 * @generated
	 */
	EList<Association> getAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='self.name = other.name and self.title = other.title'"
	 * @generated
	 */
	boolean equalsForMerge(AbstractContainer other);

} // AbstractContainer
