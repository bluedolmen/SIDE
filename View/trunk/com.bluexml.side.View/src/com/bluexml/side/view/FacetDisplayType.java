/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Facet Display Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.view.ViewPackage#getFacetDisplayType()
 * @model
 * @generated
 */
public enum FacetDisplayType implements Enumerator {
	/**
	 * The '<em><b>List</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIST_VALUE
	 * @generated
	 * @ordered
	 */
	LIST(0, "list", "list"),

	/**
	 * The '<em><b>Cloud</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUD_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUD(1, "cloud", "cloud"), /**
	 * The '<em><b>Improved Cloud</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPROVED_CLOUD_VALUE
	 * @generated
	 * @ordered
	 */
	IMPROVED_CLOUD(2, "improvedCloud", "improvedCloud");

	/**
	 * The '<em><b>List</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>List</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LIST
	 * @model name="list"
	 * @generated
	 * @ordered
	 */
	public static final int LIST_VALUE = 0;

	/**
	 * The '<em><b>Cloud</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cloud</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLOUD
	 * @model name="cloud"
	 * @generated
	 * @ordered
	 */
	public static final int CLOUD_VALUE = 1;

	/**
	 * The '<em><b>Improved Cloud</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Improved Cloud</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IMPROVED_CLOUD
	 * @model name="improvedCloud"
	 * @generated
	 * @ordered
	 */
	public static final int IMPROVED_CLOUD_VALUE = 2;

	/**
	 * An array of all the '<em><b>Facet Display Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final FacetDisplayType[] VALUES_ARRAY =
		new FacetDisplayType[] {
			LIST,
			CLOUD,
			IMPROVED_CLOUD,
		};

	/**
	 * A public read-only list of all the '<em><b>Facet Display Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<FacetDisplayType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Facet Display Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FacetDisplayType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FacetDisplayType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Facet Display Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FacetDisplayType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FacetDisplayType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Facet Display Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FacetDisplayType get(int value) {
		switch (value) {
			case LIST_VALUE: return LIST;
			case CLOUD_VALUE: return CLOUD;
			case IMPROVED_CLOUD_VALUE: return IMPROVED_CLOUD;
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
	private FacetDisplayType(int value, String name, String literal) {
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
	
} //FacetDisplayType
