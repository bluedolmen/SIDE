/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Attribute Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.requirements.RequirementsPackage#getAttributeType()
 * @model
 * @generated
 */
public enum AttributeType implements Enumerator {
	/**
	 * The '<em><b>Textual Value</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEXTUAL_VALUE_VALUE
	 * @generated
	 * @ordered
	 */
	TEXTUAL_VALUE(0, "TextualValue", "TextualValue"),

	/**
	 * The '<em><b>Numerical Value</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUMERICAL_VALUE_VALUE
	 * @generated
	 * @ordered
	 */
	NUMERICAL_VALUE(1, "NumericalValue", "NumericalValue"),

	/**
	 * The '<em><b>Temporal Value</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMPORAL_VALUE_VALUE
	 * @generated
	 * @ordered
	 */
	TEMPORAL_VALUE(2, "TemporalValue", "TemporalValue"),

	/**
	 * The '<em><b>Other</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OTHER_VALUE
	 * @generated
	 * @ordered
	 */
	OTHER(3, "Other", "Other");

	/**
	 * The '<em><b>Textual Value</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Textual Value</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEXTUAL_VALUE
	 * @model name="TextualValue"
	 * @generated
	 * @ordered
	 */
	public static final int TEXTUAL_VALUE_VALUE = 0;

	/**
	 * The '<em><b>Numerical Value</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Numerical Value</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NUMERICAL_VALUE
	 * @model name="NumericalValue"
	 * @generated
	 * @ordered
	 */
	public static final int NUMERICAL_VALUE_VALUE = 1;

	/**
	 * The '<em><b>Temporal Value</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Temporal Value</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEMPORAL_VALUE
	 * @model name="TemporalValue"
	 * @generated
	 * @ordered
	 */
	public static final int TEMPORAL_VALUE_VALUE = 2;

	/**
	 * The '<em><b>Other</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Other</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OTHER
	 * @model name="Other"
	 * @generated
	 * @ordered
	 */
	public static final int OTHER_VALUE = 3;

	/**
	 * An array of all the '<em><b>Attribute Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AttributeType[] VALUES_ARRAY =
		new AttributeType[] {
			TEXTUAL_VALUE,
			NUMERICAL_VALUE,
			TEMPORAL_VALUE,
			OTHER,
		};

	/**
	 * A public read-only list of all the '<em><b>Attribute Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AttributeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Attribute Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AttributeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AttributeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Attribute Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AttributeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AttributeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Attribute Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AttributeType get(int value) {
		switch (value) {
			case TEXTUAL_VALUE_VALUE: return TEXTUAL_VALUE;
			case NUMERICAL_VALUE_VALUE: return NUMERICAL_VALUE;
			case TEMPORAL_VALUE_VALUE: return TEMPORAL_VALUE;
			case OTHER_VALUE: return OTHER;
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
	private AttributeType(int value, String name, String literal) {
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
	
} //AttributeType
