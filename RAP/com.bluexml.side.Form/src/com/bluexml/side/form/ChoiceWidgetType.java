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
 * A representation of the literals of the enumeration '<em><b>Choice Widget Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Definition: The 'ChoiceWidgetType'  is an enumeration list with the different layouts for a choice field.
 * <!-- end-model-doc -->
 * @see com.bluexml.side.form.FormPackage#getChoiceWidgetType()
 * @model
 * @generated
 */
public enum ChoiceWidgetType implements Enumerator {
	/**
	 * The '<em><b>Show One</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHOW_ONE_VALUE
	 * @generated
	 * @ordered
	 */
	SHOW_ONE(0, "ShowOne", "Show One"), /**
	 * The '<em><b>List All</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIST_ALL_VALUE
	 * @generated
	 * @ordered
	 */
	LIST_ALL(1, "ListAll", "List All"), /**
	 * The '<em><b>Inline</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INLINE_VALUE
	 * @generated
	 * @ordered
	 */
	INLINE(2, "Inline", "Inline");

	/**
	 * The '<em><b>Show One</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Show One</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This option refers to displaying the enumeration as a combo box: only the chosen enumeration literal is visible.
	 * <!-- end-model-doc -->
	 * @see #SHOW_ONE
	 * @model name="ShowOne" literal="Show One"
	 * @generated
	 * @ordered
	 */
	public static final int SHOW_ONE_VALUE = 0;

	/**
	 * The '<em><b>List All</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This option specifies a list representation: all literals are listed in the selection window, possibly with the help of a scrollbar. Several literals may be visible at once.
	 * <!-- end-model-doc -->
	 * @see #LIST_ALL
	 * @model name="ListAll" literal="List All"
	 * @generated
	 * @ordered
	 */
	public static final int LIST_ALL_VALUE = 1;

	/**
	 * The '<em><b>Inline</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Inline</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * All options are visible on the page, each tied to a radio button.
	 * <!-- end-model-doc -->
	 * @see #INLINE
	 * @model name="Inline"
	 * @generated
	 * @ordered
	 */
	public static final int INLINE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Choice Widget Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ChoiceWidgetType[] VALUES_ARRAY =
		new ChoiceWidgetType[] {
			SHOW_ONE,
			LIST_ALL,
			INLINE,
		};

	/**
	 * A public read-only list of all the '<em><b>Choice Widget Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ChoiceWidgetType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Choice Widget Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChoiceWidgetType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ChoiceWidgetType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Choice Widget Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChoiceWidgetType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ChoiceWidgetType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Choice Widget Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChoiceWidgetType get(int value) {
		switch (value) {
			case SHOW_ONE_VALUE: return SHOW_ONE;
			case LIST_ALL_VALUE: return LIST_ALL;
			case INLINE_VALUE: return INLINE;
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
	private ChoiceWidgetType(int value, String name, String literal) {
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
	
} //ChoiceWidgetType
