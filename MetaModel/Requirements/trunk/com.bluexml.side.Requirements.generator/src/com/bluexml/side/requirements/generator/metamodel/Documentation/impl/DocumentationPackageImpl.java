/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation.impl;

import com.bluexml.side.requirements.generator.metamodel.Documentation.Book;
import com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationFactory;
import com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage;
import com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueBody;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueEntry;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueHead;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow;
import com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValueItem;
import com.bluexml.side.requirements.generator.metamodel.Documentation.Paragraph;
import com.bluexml.side.requirements.generator.metamodel.Documentation.ParagraphValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.Section;
import com.bluexml.side.requirements.generator.metamodel.Documentation.TextualValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentationPackageImpl extends EPackageImpl implements DocumentationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paragraphEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paragraphValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textualValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass itemizedListValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass itemizedListValueItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emphasisValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xRefValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informalTableValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informalTableValueGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informalTableValueHeadEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informalTableValueBodyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informalTableValueRowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informalTableValueEntryEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DocumentationPackageImpl() {
		super(eNS_URI, DocumentationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link DocumentationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DocumentationPackage init() {
		if (isInited) return (DocumentationPackage)EPackage.Registry.INSTANCE.getEPackage(DocumentationPackage.eNS_URI);

		// Obtain or create and register package
		DocumentationPackageImpl theDocumentationPackage = (DocumentationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DocumentationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DocumentationPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theDocumentationPackage.createPackageContents();

		// Initialize created meta-data
		theDocumentationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDocumentationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DocumentationPackage.eNS_URI, theDocumentationPackage);
		return theDocumentationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBook() {
		return bookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBook_Title() {
		return (EAttribute)bookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBook_Content() {
		return (EReference)bookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParagraph() {
		return paragraphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParagraph_Values() {
		return (EReference)paragraphEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSection() {
		return sectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSection_Section() {
		return (EReference)sectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_Title() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSection_Para() {
		return (EReference)sectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParagraphValue() {
		return paragraphValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextualValue() {
		return textualValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextualValue_Value() {
		return (EAttribute)textualValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getItemizedListValue() {
		return itemizedListValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItemizedListValue_Items() {
		return (EReference)itemizedListValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getItemizedListValueItem() {
		return itemizedListValueItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmphasisValue() {
		return emphasisValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEmphasisValue_Value() {
		return (EAttribute)emphasisValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEmphasisValue_Role() {
		return (EAttribute)emphasisValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXRefValue() {
		return xRefValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getXRefValue_Linkend() {
		return (EReference)xRefValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInformalTableValue() {
		return informalTableValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInformalTableValue_Tgroup() {
		return (EReference)informalTableValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInformalTableValueGroup() {
		return informalTableValueGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInformalTableValueGroup_Cols() {
		return (EAttribute)informalTableValueGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInformalTableValueGroup_Thead() {
		return (EReference)informalTableValueGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInformalTableValueGroup_Tbody() {
		return (EReference)informalTableValueGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInformalTableValueHead() {
		return informalTableValueHeadEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInformalTableValueHead_Rows() {
		return (EReference)informalTableValueHeadEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInformalTableValueBody() {
		return informalTableValueBodyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInformalTableValueBody_Rows() {
		return (EReference)informalTableValueBodyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInformalTableValueRow() {
		return informalTableValueRowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInformalTableValueRow_Entry() {
		return (EReference)informalTableValueRowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInformalTableValueEntry() {
		return informalTableValueEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInformalTableValueEntry_Value() {
		return (EAttribute)informalTableValueEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentationFactory getDocumentationFactory() {
		return (DocumentationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		bookEClass = createEClass(BOOK);
		createEAttribute(bookEClass, BOOK__TITLE);
		createEReference(bookEClass, BOOK__CONTENT);

		paragraphEClass = createEClass(PARAGRAPH);
		createEReference(paragraphEClass, PARAGRAPH__VALUES);

		sectionEClass = createEClass(SECTION);
		createEReference(sectionEClass, SECTION__SECTION);
		createEAttribute(sectionEClass, SECTION__TITLE);
		createEReference(sectionEClass, SECTION__PARA);

		paragraphValueEClass = createEClass(PARAGRAPH_VALUE);

		textualValueEClass = createEClass(TEXTUAL_VALUE);
		createEAttribute(textualValueEClass, TEXTUAL_VALUE__VALUE);

		itemizedListValueEClass = createEClass(ITEMIZED_LIST_VALUE);
		createEReference(itemizedListValueEClass, ITEMIZED_LIST_VALUE__ITEMS);

		itemizedListValueItemEClass = createEClass(ITEMIZED_LIST_VALUE_ITEM);

		emphasisValueEClass = createEClass(EMPHASIS_VALUE);
		createEAttribute(emphasisValueEClass, EMPHASIS_VALUE__VALUE);
		createEAttribute(emphasisValueEClass, EMPHASIS_VALUE__ROLE);

		xRefValueEClass = createEClass(XREF_VALUE);
		createEReference(xRefValueEClass, XREF_VALUE__LINKEND);

		informalTableValueEClass = createEClass(INFORMAL_TABLE_VALUE);
		createEReference(informalTableValueEClass, INFORMAL_TABLE_VALUE__TGROUP);

		informalTableValueGroupEClass = createEClass(INFORMAL_TABLE_VALUE_GROUP);
		createEAttribute(informalTableValueGroupEClass, INFORMAL_TABLE_VALUE_GROUP__COLS);
		createEReference(informalTableValueGroupEClass, INFORMAL_TABLE_VALUE_GROUP__THEAD);
		createEReference(informalTableValueGroupEClass, INFORMAL_TABLE_VALUE_GROUP__TBODY);

		informalTableValueHeadEClass = createEClass(INFORMAL_TABLE_VALUE_HEAD);
		createEReference(informalTableValueHeadEClass, INFORMAL_TABLE_VALUE_HEAD__ROWS);

		informalTableValueBodyEClass = createEClass(INFORMAL_TABLE_VALUE_BODY);
		createEReference(informalTableValueBodyEClass, INFORMAL_TABLE_VALUE_BODY__ROWS);

		informalTableValueRowEClass = createEClass(INFORMAL_TABLE_VALUE_ROW);
		createEReference(informalTableValueRowEClass, INFORMAL_TABLE_VALUE_ROW__ENTRY);

		informalTableValueEntryEClass = createEClass(INFORMAL_TABLE_VALUE_ENTRY);
		createEAttribute(informalTableValueEntryEClass, INFORMAL_TABLE_VALUE_ENTRY__VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		textualValueEClass.getESuperTypes().add(this.getParagraphValue());
		itemizedListValueEClass.getESuperTypes().add(this.getParagraphValue());
		itemizedListValueItemEClass.getESuperTypes().add(this.getParagraph());
		emphasisValueEClass.getESuperTypes().add(this.getParagraphValue());
		xRefValueEClass.getESuperTypes().add(this.getParagraphValue());
		informalTableValueEClass.getESuperTypes().add(this.getParagraphValue());

		// Initialize classes and features; add operations and parameters
		initEClass(bookEClass, Book.class, "Book", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBook_Title(), ecorePackage.getEString(), "title", null, 0, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBook_Content(), this.getSection(), null, "content", null, 0, -1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(paragraphEClass, Paragraph.class, "Paragraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParagraph_Values(), this.getParagraphValue(), null, "values", null, 0, -1, Paragraph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sectionEClass, Section.class, "Section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSection_Section(), this.getSection(), null, "section", null, 0, -1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_Title(), ecorePackage.getEString(), "title", null, 1, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSection_Para(), this.getParagraph(), null, "para", null, 0, -1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(paragraphValueEClass, ParagraphValue.class, "ParagraphValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(textualValueEClass, TextualValue.class, "TextualValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextualValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, TextualValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(itemizedListValueEClass, ItemizedListValue.class, "ItemizedListValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getItemizedListValue_Items(), this.getItemizedListValueItem(), null, "items", null, 0, -1, ItemizedListValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(itemizedListValueItemEClass, ItemizedListValueItem.class, "ItemizedListValueItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(emphasisValueEClass, EmphasisValue.class, "EmphasisValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEmphasisValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, EmphasisValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEmphasisValue_Role(), ecorePackage.getEString(), "role", null, 0, 1, EmphasisValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(xRefValueEClass, XRefValue.class, "XRefValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getXRefValue_Linkend(), this.getSection(), null, "linkend", null, 1, 1, XRefValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(informalTableValueEClass, InformalTableValue.class, "InformalTableValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInformalTableValue_Tgroup(), this.getInformalTableValueGroup(), null, "tgroup", null, 1, 1, InformalTableValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(informalTableValueGroupEClass, InformalTableValueGroup.class, "InformalTableValueGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInformalTableValueGroup_Cols(), ecorePackage.getEInt(), "cols", null, 0, 1, InformalTableValueGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInformalTableValueGroup_Thead(), this.getInformalTableValueHead(), null, "thead", null, 0, 1, InformalTableValueGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInformalTableValueGroup_Tbody(), this.getInformalTableValueBody(), null, "tbody", null, 0, 1, InformalTableValueGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(informalTableValueHeadEClass, InformalTableValueHead.class, "InformalTableValueHead", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInformalTableValueHead_Rows(), this.getInformalTableValueRow(), null, "rows", null, 0, -1, InformalTableValueHead.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(informalTableValueBodyEClass, InformalTableValueBody.class, "InformalTableValueBody", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInformalTableValueBody_Rows(), this.getInformalTableValueRow(), null, "rows", null, 0, -1, InformalTableValueBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(informalTableValueRowEClass, InformalTableValueRow.class, "InformalTableValueRow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInformalTableValueRow_Entry(), this.getInformalTableValueEntry(), null, "entry", null, 0, -1, InformalTableValueRow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(informalTableValueEntryEClass, InformalTableValueEntry.class, "InformalTableValueEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInformalTableValueEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, InformalTableValueEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //DocumentationPackageImpl
