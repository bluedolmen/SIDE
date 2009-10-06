/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation.impl;

import com.bluexml.side.requirements.generator.metamodel.Documentation.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentationFactoryImpl extends EFactoryImpl implements DocumentationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DocumentationFactory init() {
		try {
			DocumentationFactory theDocumentationFactory = (DocumentationFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.bluexml.com/rwm/documentation/1.0/"); 
			if (theDocumentationFactory != null) {
				return theDocumentationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DocumentationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DocumentationPackage.BOOK: return createBook();
			case DocumentationPackage.PARAGRAPH: return createParagraph();
			case DocumentationPackage.SECTION: return createSection();
			case DocumentationPackage.TEXTUAL_VALUE: return createTextualValue();
			case DocumentationPackage.ITEMIZED_LIST_VALUE: return createItemizedListValue();
			case DocumentationPackage.ITEMIZED_LIST_VALUE_ITEM: return createItemizedListValueItem();
			case DocumentationPackage.EMPHASIS_VALUE: return createEmphasisValue();
			case DocumentationPackage.XREF_VALUE: return createXRefValue();
			case DocumentationPackage.INFORMAL_TABLE_VALUE: return createInformalTableValue();
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP: return createInformalTableValueGroup();
			case DocumentationPackage.INFORMAL_TABLE_VALUE_HEAD: return createInformalTableValueHead();
			case DocumentationPackage.INFORMAL_TABLE_VALUE_BODY: return createInformalTableValueBody();
			case DocumentationPackage.INFORMAL_TABLE_VALUE_ROW: return createInformalTableValueRow();
			case DocumentationPackage.INFORMAL_TABLE_VALUE_ENTRY: return createInformalTableValueEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Book createBook() {
		BookImpl book = new BookImpl();
		return book;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Paragraph createParagraph() {
		ParagraphImpl paragraph = new ParagraphImpl();
		return paragraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Section createSection() {
		SectionImpl section = new SectionImpl();
		return section;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextualValue createTextualValue() {
		TextualValueImpl textualValue = new TextualValueImpl();
		return textualValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemizedListValue createItemizedListValue() {
		ItemizedListValueImpl itemizedListValue = new ItemizedListValueImpl();
		return itemizedListValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemizedListValueItem createItemizedListValueItem() {
		ItemizedListValueItemImpl itemizedListValueItem = new ItemizedListValueItemImpl();
		return itemizedListValueItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmphasisValue createEmphasisValue() {
		EmphasisValueImpl emphasisValue = new EmphasisValueImpl();
		return emphasisValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XRefValue createXRefValue() {
		XRefValueImpl xRefValue = new XRefValueImpl();
		return xRefValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformalTableValue createInformalTableValue() {
		InformalTableValueImpl informalTableValue = new InformalTableValueImpl();
		return informalTableValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformalTableValueGroup createInformalTableValueGroup() {
		InformalTableValueGroupImpl informalTableValueGroup = new InformalTableValueGroupImpl();
		return informalTableValueGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformalTableValueHead createInformalTableValueHead() {
		InformalTableValueHeadImpl informalTableValueHead = new InformalTableValueHeadImpl();
		return informalTableValueHead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformalTableValueBody createInformalTableValueBody() {
		InformalTableValueBodyImpl informalTableValueBody = new InformalTableValueBodyImpl();
		return informalTableValueBody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformalTableValueRow createInformalTableValueRow() {
		InformalTableValueRowImpl informalTableValueRow = new InformalTableValueRowImpl();
		return informalTableValueRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformalTableValueEntry createInformalTableValueEntry() {
		InformalTableValueEntryImpl informalTableValueEntry = new InformalTableValueEntryImpl();
		return informalTableValueEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentationPackage getDocumentationPackage() {
		return (DocumentationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DocumentationPackage getPackage() {
		return DocumentationPackage.eINSTANCE;
	}

} //DocumentationFactoryImpl
