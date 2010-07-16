/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Model Choice Widget Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Definition: The 'ReferenceWidgetType'  is an enumeration list with the different layout for a model choice field.
 * <!-- end-model-doc -->
 * @see com.bluexml.side.form.FormPackage#getModelChoiceWidgetType()
 * @model
 * @generated
 */
public enum ModelChoiceWidgetType implements Enumerator {
	/**
	 * The '<em><b>Select</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SELECT_VALUE
	 * @generated
	 * @ordered
	 */
	SELECT(0, "Select", "Select"),

	/**
	 * The '<em><b>Inline</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INLINE_VALUE
	 * @generated
	 * @ordered
	 */
	INLINE(1, "Inline", "Inline"), /**
	 * The '<em><b>Item Selector</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ITEM_SELECTOR_VALUE
	 * @generated
	 * @ordered
	 */
	ITEM_SELECTOR(2, "ItemSelector", "ItemSelector");

	/**
	 * The '<em><b>Select</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This option allows selecting the associated items using a selection list. Associated items are considered existent although they may still be created.
	 * <!-- end-model-doc -->
	 * @see #SELECT
	 * @model name="Select"
	 * @generated
	 * @ordered
	 */
	public static final int SELECT_VALUE = 0;

	/**
	 * The '<em><b>Inline</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The form designed for the target class or target form is included in the parent form. Associated items can be defined directly from the currently displayed form.
	 * <!-- end-model-doc -->
	 * @see #INLINE
	 * @model name="Inline"
	 * @generated
	 * @ordered
	 */
	public static final int INLINE_VALUE = 1;

	/**
	 * The '<em><b>Item Selector</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Item Selector</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ITEM_SELECTOR
	 * @model name="ItemSelector"
	 * @generated
	 * @ordered
	 */
	public static final int ITEM_SELECTOR_VALUE = 2;

	/**
	 * An array of all the '<em><b>Model Choice Widget Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ModelChoiceWidgetType[] VALUES_ARRAY =
		new ModelChoiceWidgetType[] {
			SELECT,
			INLINE,
			ITEM_SELECTOR,
		};

	/**
	 * A public read-only list of all the '<em><b>Model Choice Widget Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ModelChoiceWidgetType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Model Choice Widget Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelChoiceWidgetType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ModelChoiceWidgetType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Model Choice Widget Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelChoiceWidgetType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ModelChoiceWidgetType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Model Choice Widget Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelChoiceWidgetType get(int value) {
		switch (value) {
			case SELECT_VALUE: return SELECT;
			case INLINE_VALUE: return INLINE;
			case ITEM_SELECTOR_VALUE: return ITEM_SELECTOR;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ModelChoiceWidgetType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ModelChoiceWidgetType
