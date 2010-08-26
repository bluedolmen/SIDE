/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationFactory
 * @model kind="package"
 * @generated
 */
public interface DocumentationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Documentation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bluexml.com/rwm/documentation/1.0/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Documentation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DocumentationPackage eINSTANCE = com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.BookImpl <em>Book</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.BookImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getBook()
	 * @generated
	 */
	int BOOK = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__CONTENT = 1;

	/**
	 * The number of structural features of the '<em>Book</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ParagraphImpl <em>Paragraph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ParagraphImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getParagraph()
	 * @generated
	 */
	int PARAGRAPH = 1;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAGRAPH__VALUES = 0;

	/**
	 * The number of structural features of the '<em>Paragraph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAGRAPH_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.SectionImpl <em>Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.SectionImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getSection()
	 * @generated
	 */
	int SECTION = 2;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__SECTION = 0;

	/**
	 * The feature id for the '<em><b>Para</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__PARA = 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__TITLE = 2;

	/**
	 * The number of structural features of the '<em>Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ParagraphValueImpl <em>Paragraph Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ParagraphValueImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getParagraphValue()
	 * @generated
	 */
	int PARAGRAPH_VALUE = 3;

	/**
	 * The number of structural features of the '<em>Paragraph Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAGRAPH_VALUE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.TextualValueImpl <em>Textual Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.TextualValueImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getTextualValue()
	 * @generated
	 */
	int TEXTUAL_VALUE = 4;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTUAL_VALUE__VALUE = PARAGRAPH_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Textual Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTUAL_VALUE_FEATURE_COUNT = PARAGRAPH_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ItemizedListValueImpl <em>Itemized List Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ItemizedListValueImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getItemizedListValue()
	 * @generated
	 */
	int ITEMIZED_LIST_VALUE = 5;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEMIZED_LIST_VALUE__ITEMS = PARAGRAPH_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Itemized List Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEMIZED_LIST_VALUE_FEATURE_COUNT = PARAGRAPH_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ItemizedListValueItemImpl <em>Itemized List Value Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ItemizedListValueItemImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getItemizedListValueItem()
	 * @generated
	 */
	int ITEMIZED_LIST_VALUE_ITEM = 6;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEMIZED_LIST_VALUE_ITEM__VALUES = PARAGRAPH__VALUES;

	/**
	 * The number of structural features of the '<em>Itemized List Value Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEMIZED_LIST_VALUE_ITEM_FEATURE_COUNT = PARAGRAPH_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.EmphasisValueImpl <em>Emphasis Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.EmphasisValueImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getEmphasisValue()
	 * @generated
	 */
	int EMPHASIS_VALUE = 7;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPHASIS_VALUE__VALUE = PARAGRAPH_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPHASIS_VALUE__ROLE = PARAGRAPH_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Emphasis Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPHASIS_VALUE_FEATURE_COUNT = PARAGRAPH_VALUE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.XRefValueImpl <em>XRef Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.XRefValueImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getXRefValue()
	 * @generated
	 */
	int XREF_VALUE = 8;

	/**
	 * The feature id for the '<em><b>Linkend</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XREF_VALUE__LINKEND = PARAGRAPH_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>XRef Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XREF_VALUE_FEATURE_COUNT = PARAGRAPH_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueImpl <em>Informal Table Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValue()
	 * @generated
	 */
	int INFORMAL_TABLE_VALUE = 9;

	/**
	 * The feature id for the '<em><b>Cols</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE__COLS = PARAGRAPH_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Body Rows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE__BODY_ROWS = PARAGRAPH_VALUE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Head Rows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE__HEAD_ROWS = PARAGRAPH_VALUE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Informal Table Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_FEATURE_COUNT = PARAGRAPH_VALUE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueRowImpl <em>Informal Table Value Row</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueRowImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueRow()
	 * @generated
	 */
	int INFORMAL_TABLE_VALUE_ROW = 10;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_ROW__ENTRY = 0;

	/**
	 * The number of structural features of the '<em>Informal Table Value Row</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_ROW_FEATURE_COUNT = 1;

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Book <em>Book</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Book</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Book
	 * @generated
	 */
	EClass getBook();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Book#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Book#getTitle()
	 * @see #getBook()
	 * @generated
	 */
	EAttribute getBook_Title();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Book#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Book#getContent()
	 * @see #getBook()
	 * @generated
	 */
	EReference getBook_Content();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Paragraph <em>Paragraph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Paragraph</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Paragraph
	 * @generated
	 */
	EClass getParagraph();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Paragraph#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Paragraph#getValues()
	 * @see #getParagraph()
	 * @generated
	 */
	EReference getParagraph_Values();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Section
	 * @generated
	 */
	EClass getSection();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Section#getSection <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Section</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Section#getSection()
	 * @see #getSection()
	 * @generated
	 */
	EReference getSection_Section();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Section#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Section#getTitle()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_Title();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Section#getPara <em>Para</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Para</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Section#getPara()
	 * @see #getSection()
	 * @generated
	 */
	EReference getSection_Para();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.ParagraphValue <em>Paragraph Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Paragraph Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.ParagraphValue
	 * @generated
	 */
	EClass getParagraphValue();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.TextualValue <em>Textual Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Textual Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.TextualValue
	 * @generated
	 */
	EClass getTextualValue();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.TextualValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.TextualValue#getValue()
	 * @see #getTextualValue()
	 * @generated
	 */
	EAttribute getTextualValue_Value();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValue <em>Itemized List Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Itemized List Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValue
	 * @generated
	 */
	EClass getItemizedListValue();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValue#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValue#getItems()
	 * @see #getItemizedListValue()
	 * @generated
	 */
	EReference getItemizedListValue_Items();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValueItem <em>Itemized List Value Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Itemized List Value Item</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValueItem
	 * @generated
	 */
	EClass getItemizedListValueItem();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue <em>Emphasis Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Emphasis Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue
	 * @generated
	 */
	EClass getEmphasisValue();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue#getRole()
	 * @see #getEmphasisValue()
	 * @generated
	 */
	EAttribute getEmphasisValue_Role();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue <em>XRef Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XRef Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue
	 * @generated
	 */
	EClass getXRefValue();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue#getLinkend <em>Linkend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Linkend</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue#getLinkend()
	 * @see #getXRefValue()
	 * @generated
	 */
	EReference getXRefValue_Linkend();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue <em>Informal Table Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Informal Table Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue
	 * @generated
	 */
	EClass getInformalTableValue();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getCols <em>Cols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cols</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getCols()
	 * @see #getInformalTableValue()
	 * @generated
	 */
	EAttribute getInformalTableValue_Cols();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getBodyRows <em>Body Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Body Rows</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getBodyRows()
	 * @see #getInformalTableValue()
	 * @generated
	 */
	EReference getInformalTableValue_BodyRows();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getHeadRows <em>Head Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Head Rows</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getHeadRows()
	 * @see #getInformalTableValue()
	 * @generated
	 */
	EReference getInformalTableValue_HeadRows();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow <em>Informal Table Value Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Informal Table Value Row</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow
	 * @generated
	 */
	EClass getInformalTableValueRow();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow#getEntry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entry</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow#getEntry()
	 * @see #getInformalTableValueRow()
	 * @generated
	 */
	EReference getInformalTableValueRow_Entry();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DocumentationFactory getDocumentationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.BookImpl <em>Book</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.BookImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getBook()
		 * @generated
		 */
		EClass BOOK = eINSTANCE.getBook();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOK__TITLE = eINSTANCE.getBook_Title();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOK__CONTENT = eINSTANCE.getBook_Content();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ParagraphImpl <em>Paragraph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ParagraphImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getParagraph()
		 * @generated
		 */
		EClass PARAGRAPH = eINSTANCE.getParagraph();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAGRAPH__VALUES = eINSTANCE.getParagraph_Values();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.SectionImpl <em>Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.SectionImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getSection()
		 * @generated
		 */
		EClass SECTION = eINSTANCE.getSection();

		/**
		 * The meta object literal for the '<em><b>Section</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION__SECTION = eINSTANCE.getSection_Section();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__TITLE = eINSTANCE.getSection_Title();

		/**
		 * The meta object literal for the '<em><b>Para</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION__PARA = eINSTANCE.getSection_Para();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ParagraphValueImpl <em>Paragraph Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ParagraphValueImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getParagraphValue()
		 * @generated
		 */
		EClass PARAGRAPH_VALUE = eINSTANCE.getParagraphValue();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.TextualValueImpl <em>Textual Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.TextualValueImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getTextualValue()
		 * @generated
		 */
		EClass TEXTUAL_VALUE = eINSTANCE.getTextualValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXTUAL_VALUE__VALUE = eINSTANCE.getTextualValue_Value();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ItemizedListValueImpl <em>Itemized List Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ItemizedListValueImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getItemizedListValue()
		 * @generated
		 */
		EClass ITEMIZED_LIST_VALUE = eINSTANCE.getItemizedListValue();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITEMIZED_LIST_VALUE__ITEMS = eINSTANCE.getItemizedListValue_Items();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ItemizedListValueItemImpl <em>Itemized List Value Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.ItemizedListValueItemImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getItemizedListValueItem()
		 * @generated
		 */
		EClass ITEMIZED_LIST_VALUE_ITEM = eINSTANCE.getItemizedListValueItem();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.EmphasisValueImpl <em>Emphasis Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.EmphasisValueImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getEmphasisValue()
		 * @generated
		 */
		EClass EMPHASIS_VALUE = eINSTANCE.getEmphasisValue();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMPHASIS_VALUE__ROLE = eINSTANCE.getEmphasisValue_Role();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.XRefValueImpl <em>XRef Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.XRefValueImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getXRefValue()
		 * @generated
		 */
		EClass XREF_VALUE = eINSTANCE.getXRefValue();

		/**
		 * The meta object literal for the '<em><b>Linkend</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XREF_VALUE__LINKEND = eINSTANCE.getXRefValue_Linkend();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueImpl <em>Informal Table Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValue()
		 * @generated
		 */
		EClass INFORMAL_TABLE_VALUE = eINSTANCE.getInformalTableValue();

		/**
		 * The meta object literal for the '<em><b>Cols</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMAL_TABLE_VALUE__COLS = eINSTANCE.getInformalTableValue_Cols();

		/**
		 * The meta object literal for the '<em><b>Body Rows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMAL_TABLE_VALUE__BODY_ROWS = eINSTANCE.getInformalTableValue_BodyRows();

		/**
		 * The meta object literal for the '<em><b>Head Rows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMAL_TABLE_VALUE__HEAD_ROWS = eINSTANCE.getInformalTableValue_HeadRows();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueRowImpl <em>Informal Table Value Row</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueRowImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueRow()
		 * @generated
		 */
		EClass INFORMAL_TABLE_VALUE_ROW = eINSTANCE.getInformalTableValueRow();

		/**
		 * The meta object literal for the '<em><b>Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMAL_TABLE_VALUE_ROW__ENTRY = eINSTANCE.getInformalTableValueRow_Entry();

	}

} //DocumentationPackage
