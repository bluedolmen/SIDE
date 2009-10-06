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
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__TITLE = 1;

	/**
	 * The feature id for the '<em><b>Para</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__PARA = 2;

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
	 * The feature id for the '<em><b>Tgroup</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE__TGROUP = PARAGRAPH_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Informal Table Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_FEATURE_COUNT = PARAGRAPH_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueGroupImpl <em>Informal Table Value Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueGroupImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueGroup()
	 * @generated
	 */
	int INFORMAL_TABLE_VALUE_GROUP = 10;

	/**
	 * The feature id for the '<em><b>Cols</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_GROUP__COLS = 0;

	/**
	 * The feature id for the '<em><b>Thead</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_GROUP__THEAD = 1;

	/**
	 * The feature id for the '<em><b>Tbody</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_GROUP__TBODY = 2;

	/**
	 * The number of structural features of the '<em>Informal Table Value Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_GROUP_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueHeadImpl <em>Informal Table Value Head</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueHeadImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueHead()
	 * @generated
	 */
	int INFORMAL_TABLE_VALUE_HEAD = 11;

	/**
	 * The feature id for the '<em><b>Rows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_HEAD__ROWS = 0;

	/**
	 * The number of structural features of the '<em>Informal Table Value Head</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_HEAD_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueBodyImpl <em>Informal Table Value Body</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueBodyImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueBody()
	 * @generated
	 */
	int INFORMAL_TABLE_VALUE_BODY = 12;

	/**
	 * The feature id for the '<em><b>Rows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_BODY__ROWS = 0;

	/**
	 * The number of structural features of the '<em>Informal Table Value Body</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_BODY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueRowImpl <em>Informal Table Value Row</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueRowImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueRow()
	 * @generated
	 */
	int INFORMAL_TABLE_VALUE_ROW = 13;

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
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueEntryImpl <em>Informal Table Value Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueEntryImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueEntry()
	 * @generated
	 */
	int INFORMAL_TABLE_VALUE_ENTRY = 14;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_ENTRY__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Informal Table Value Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMAL_TABLE_VALUE_ENTRY_FEATURE_COUNT = 1;


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
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue#getValue()
	 * @see #getEmphasisValue()
	 * @generated
	 */
	EAttribute getEmphasisValue_Value();

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
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getTgroup <em>Tgroup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tgroup</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getTgroup()
	 * @see #getInformalTableValue()
	 * @generated
	 */
	EReference getInformalTableValue_Tgroup();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup <em>Informal Table Value Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Informal Table Value Group</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup
	 * @generated
	 */
	EClass getInformalTableValueGroup();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getCols <em>Cols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cols</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getCols()
	 * @see #getInformalTableValueGroup()
	 * @generated
	 */
	EAttribute getInformalTableValueGroup_Cols();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getThead <em>Thead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Thead</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getThead()
	 * @see #getInformalTableValueGroup()
	 * @generated
	 */
	EReference getInformalTableValueGroup_Thead();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getTbody <em>Tbody</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tbody</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getTbody()
	 * @see #getInformalTableValueGroup()
	 * @generated
	 */
	EReference getInformalTableValueGroup_Tbody();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueHead <em>Informal Table Value Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Informal Table Value Head</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueHead
	 * @generated
	 */
	EClass getInformalTableValueHead();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueHead#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rows</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueHead#getRows()
	 * @see #getInformalTableValueHead()
	 * @generated
	 */
	EReference getInformalTableValueHead_Rows();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueBody <em>Informal Table Value Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Informal Table Value Body</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueBody
	 * @generated
	 */
	EClass getInformalTableValueBody();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueBody#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rows</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueBody#getRows()
	 * @see #getInformalTableValueBody()
	 * @generated
	 */
	EReference getInformalTableValueBody_Rows();

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
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueEntry <em>Informal Table Value Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Informal Table Value Entry</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueEntry
	 * @generated
	 */
	EClass getInformalTableValueEntry();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueEntry#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueEntry#getValue()
	 * @see #getInformalTableValueEntry()
	 * @generated
	 */
	EAttribute getInformalTableValueEntry_Value();

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
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMPHASIS_VALUE__VALUE = eINSTANCE.getEmphasisValue_Value();

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
		 * The meta object literal for the '<em><b>Tgroup</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMAL_TABLE_VALUE__TGROUP = eINSTANCE.getInformalTableValue_Tgroup();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueGroupImpl <em>Informal Table Value Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueGroupImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueGroup()
		 * @generated
		 */
		EClass INFORMAL_TABLE_VALUE_GROUP = eINSTANCE.getInformalTableValueGroup();

		/**
		 * The meta object literal for the '<em><b>Cols</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMAL_TABLE_VALUE_GROUP__COLS = eINSTANCE.getInformalTableValueGroup_Cols();

		/**
		 * The meta object literal for the '<em><b>Thead</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMAL_TABLE_VALUE_GROUP__THEAD = eINSTANCE.getInformalTableValueGroup_Thead();

		/**
		 * The meta object literal for the '<em><b>Tbody</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMAL_TABLE_VALUE_GROUP__TBODY = eINSTANCE.getInformalTableValueGroup_Tbody();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueHeadImpl <em>Informal Table Value Head</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueHeadImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueHead()
		 * @generated
		 */
		EClass INFORMAL_TABLE_VALUE_HEAD = eINSTANCE.getInformalTableValueHead();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMAL_TABLE_VALUE_HEAD__ROWS = eINSTANCE.getInformalTableValueHead_Rows();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueBodyImpl <em>Informal Table Value Body</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueBodyImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueBody()
		 * @generated
		 */
		EClass INFORMAL_TABLE_VALUE_BODY = eINSTANCE.getInformalTableValueBody();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMAL_TABLE_VALUE_BODY__ROWS = eINSTANCE.getInformalTableValueBody_Rows();

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

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueEntryImpl <em>Informal Table Value Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueEntryImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.impl.DocumentationPackageImpl#getInformalTableValueEntry()
		 * @generated
		 */
		EClass INFORMAL_TABLE_VALUE_ENTRY = eINSTANCE.getInformalTableValueEntry();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMAL_TABLE_VALUE_ENTRY__VALUE = eINSTANCE.getInformalTableValueEntry_Value();

	}

} //DocumentationPackage
