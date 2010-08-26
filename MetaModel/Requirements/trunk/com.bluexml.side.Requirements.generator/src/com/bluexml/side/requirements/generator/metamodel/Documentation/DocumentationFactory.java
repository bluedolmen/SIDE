/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage
 * @generated
 */
public interface DocumentationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DocumentationFactory eINSTANCE = com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Book</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Book</em>'.
	 * @generated
	 */
	Book createBook();

	/**
	 * Returns a new object of class '<em>Paragraph</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Paragraph</em>'.
	 * @generated
	 */
	Paragraph createParagraph();

	/**
	 * Returns a new object of class '<em>Section</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Section</em>'.
	 * @generated
	 */
	Section createSection();

	/**
	 * Returns a new object of class '<em>Textual Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Textual Value</em>'.
	 * @generated
	 */
	TextualValue createTextualValue();

	/**
	 * Returns a new object of class '<em>Itemized List Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Itemized List Value</em>'.
	 * @generated
	 */
	ItemizedListValue createItemizedListValue();

	/**
	 * Returns a new object of class '<em>Itemized List Value Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Itemized List Value Item</em>'.
	 * @generated
	 */
	ItemizedListValueItem createItemizedListValueItem();

	/**
	 * Returns a new object of class '<em>Emphasis Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Emphasis Value</em>'.
	 * @generated
	 */
	EmphasisValue createEmphasisValue();

	/**
	 * Returns a new object of class '<em>XRef Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XRef Value</em>'.
	 * @generated
	 */
	XRefValue createXRefValue();

	/**
	 * Returns a new object of class '<em>Informal Table Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Informal Table Value</em>'.
	 * @generated
	 */
	InformalTableValue createInformalTableValue();

	/**
	 * Returns a new object of class '<em>Informal Table Value Row</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Informal Table Value Row</em>'.
	 * @generated
	 */
	InformalTableValueRow createInformalTableValueRow();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DocumentationPackage getDocumentationPackage();

} //DocumentationFactory
