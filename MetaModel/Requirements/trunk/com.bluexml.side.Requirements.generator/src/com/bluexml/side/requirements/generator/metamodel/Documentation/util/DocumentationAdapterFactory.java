/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.requirements.generator.metamodel.Documentation.Book;
import com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage;
import com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow;
import com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValueItem;
import com.bluexml.side.requirements.generator.metamodel.Documentation.Paragraph;
import com.bluexml.side.requirements.generator.metamodel.Documentation.ParagraphValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.Section;
import com.bluexml.side.requirements.generator.metamodel.Documentation.TextualValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage
 * @generated
 */
public class DocumentationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DocumentationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DocumentationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentationSwitch<Adapter> modelSwitch =
		new DocumentationSwitch<Adapter>() {
			@Override
			public Adapter caseBook(Book object) {
				return createBookAdapter();
			}
			@Override
			public Adapter caseParagraph(Paragraph object) {
				return createParagraphAdapter();
			}
			@Override
			public Adapter caseSection(Section object) {
				return createSectionAdapter();
			}
			@Override
			public Adapter caseParagraphValue(ParagraphValue object) {
				return createParagraphValueAdapter();
			}
			@Override
			public Adapter caseTextualValue(TextualValue object) {
				return createTextualValueAdapter();
			}
			@Override
			public Adapter caseItemizedListValue(ItemizedListValue object) {
				return createItemizedListValueAdapter();
			}
			@Override
			public Adapter caseItemizedListValueItem(ItemizedListValueItem object) {
				return createItemizedListValueItemAdapter();
			}
			@Override
			public Adapter caseEmphasisValue(EmphasisValue object) {
				return createEmphasisValueAdapter();
			}
			@Override
			public Adapter caseXRefValue(XRefValue object) {
				return createXRefValueAdapter();
			}
			@Override
			public Adapter caseInformalTableValue(InformalTableValue object) {
				return createInformalTableValueAdapter();
			}
			@Override
			public Adapter caseInformalTableValueRow(InformalTableValueRow object) {
				return createInformalTableValueRowAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Book <em>Book</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Book
	 * @generated
	 */
	public Adapter createBookAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Paragraph <em>Paragraph</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Paragraph
	 * @generated
	 */
	public Adapter createParagraphAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.Section
	 * @generated
	 */
	public Adapter createSectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.ParagraphValue <em>Paragraph Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.ParagraphValue
	 * @generated
	 */
	public Adapter createParagraphValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.TextualValue <em>Textual Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.TextualValue
	 * @generated
	 */
	public Adapter createTextualValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValue <em>Itemized List Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValue
	 * @generated
	 */
	public Adapter createItemizedListValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValueItem <em>Itemized List Value Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.ItemizedListValueItem
	 * @generated
	 */
	public Adapter createItemizedListValueItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue <em>Emphasis Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.EmphasisValue
	 * @generated
	 */
	public Adapter createEmphasisValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue <em>XRef Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue
	 * @generated
	 */
	public Adapter createXRefValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue <em>Informal Table Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue
	 * @generated
	 */
	public Adapter createInformalTableValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow <em>Informal Table Value Row</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow
	 * @generated
	 */
	public Adapter createInformalTableValueRowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //DocumentationAdapterFactory
