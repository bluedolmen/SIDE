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
 * A representation of the literals of the enumeration '<em><b>Char Field Search Operators</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.form.FormPackage#getCharFieldSearchOperators()
 * @model
 * @generated
 */
public enum CharFieldSearchOperators implements Enumerator {
	/**
	 * The '<em><b>Contains</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTAINS_VALUE
	 * @generated
	 * @ordered
	 */
	CONTAINS(0, "contains", "contains"), /**
	 * The '<em><b>Icontains</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ICONTAINS_VALUE
	 * @generated
	 * @ordered
	 */
	ICONTAINS(1, "icontains", "icontains"), /**
	 * The '<em><b>Starts With</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STARTS_WITH_VALUE
	 * @generated
	 * @ordered
	 */
	STARTS_WITH(2, "startsWith", "startsWith"), /**
	 * The '<em><b>Istarts With</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISTARTS_WITH_VALUE
	 * @generated
	 * @ordered
	 */
	ISTARTS_WITH(3, "istartsWith", "istartsWith"), /**
	 * The '<em><b>Ends With</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENDS_WITH_VALUE
	 * @generated
	 * @ordered
	 */
	ENDS_WITH(4, "endsWith", "endsWith"), /**
	 * The '<em><b>Iends With</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IENDS_WITH_VALUE
	 * @generated
	 * @ordered
	 */
	IENDS_WITH(5, "iendsWith", "iendsWith");

	/**
	 * The '<em><b>Contains</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Contains</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTAINS
	 * @model name="contains"
	 * @generated
	 * @ordered
	 */
	public static final int CONTAINS_VALUE = 0;

	/**
	 * The '<em><b>Icontains</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Icontains</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ICONTAINS
	 * @model name="icontains"
	 * @generated
	 * @ordered
	 */
	public static final int ICONTAINS_VALUE = 1;

	/**
	 * The '<em><b>Starts With</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Starts With</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTS_WITH
	 * @model name="startsWith"
	 * @generated
	 * @ordered
	 */
	public static final int STARTS_WITH_VALUE = 2;

	/**
	 * The '<em><b>Istarts With</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Istarts With</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ISTARTS_WITH
	 * @model name="istartsWith"
	 * @generated
	 * @ordered
	 */
	public static final int ISTARTS_WITH_VALUE = 3;

	/**
	 * The '<em><b>Ends With</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Ends With</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENDS_WITH
	 * @model name="endsWith"
	 * @generated
	 * @ordered
	 */
	public static final int ENDS_WITH_VALUE = 4;

	/**
	 * The '<em><b>Iends With</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Iends With</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IENDS_WITH
	 * @model name="iendsWith"
	 * @generated
	 * @ordered
	 */
	public static final int IENDS_WITH_VALUE = 5;

	/**
	 * An array of all the '<em><b>Char Field Search Operators</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CharFieldSearchOperators[] VALUES_ARRAY =
		new CharFieldSearchOperators[] {
			CONTAINS,
			ICONTAINS,
			STARTS_WITH,
			ISTARTS_WITH,
			ENDS_WITH,
			IENDS_WITH,
		};

	/**
	 * A public read-only list of all the '<em><b>Char Field Search Operators</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<CharFieldSearchOperators> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Char Field Search Operators</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CharFieldSearchOperators get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CharFieldSearchOperators result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Char Field Search Operators</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CharFieldSearchOperators getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CharFieldSearchOperators result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Char Field Search Operators</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CharFieldSearchOperators get(int value) {
		switch (value) {
			case CONTAINS_VALUE: return CONTAINS;
			case ICONTAINS_VALUE: return ICONTAINS;
			case STARTS_WITH_VALUE: return STARTS_WITH;
			case ISTARTS_WITH_VALUE: return ISTARTS_WITH;
			case ENDS_WITH_VALUE: return ENDS_WITH;
			case IENDS_WITH_VALUE: return IENDS_WITH;
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
	private CharFieldSearchOperators(int value, String name, String literal) {
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
	
} //CharFieldSearchOperators
