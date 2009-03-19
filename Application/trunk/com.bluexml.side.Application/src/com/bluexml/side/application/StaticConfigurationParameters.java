/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Static Configuration Parameters</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.application.ApplicationPackage#getStaticConfigurationParameters()
 * @model
 * @generated
 */
public enum StaticConfigurationParameters implements Enumerator {
	/**
	 * The '<em><b>Generationoptionsclean</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSCLEAN_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTIONSCLEAN(0, "generationoptionsclean", "generation.options.clean"),

	/**
	 * The '<em><b>Generationoptionsverbose</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSVERBOSE_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTIONSVERBOSE(1, "generationoptionsverbose", "generation.options.verbose"),

	/**
	 * The '<em><b>Generationoptionsupdate Tgt</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSUPDATE_TGT_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTIONSUPDATE_TGT(3, "generationoptionsupdateTgt", "generation.options.updateTgt"),

	/**
	 * The '<em><b>Generationoptionslog Path</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSLOG_PATH_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTIONSLOG_PATH(4, "generationoptionslogPath", "generation.options.logPath"),

	/**
	 * The '<em><b>Generationoptionsdestination Path</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSDESTINATION_PATH_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTIONSDESTINATION_PATH(5, "generationoptionsdestinationPath", "generation.options.destinationPath");

	/**
	 * The '<em><b>Generationoptionsclean</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generationoptionsclean</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSCLEAN
	 * @model name="generationoptionsclean" literal="generation.options.clean"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATIONOPTIONSCLEAN_VALUE = 0;

	/**
	 * The '<em><b>Generationoptionsverbose</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generationoptionsverbose</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSVERBOSE
	 * @model name="generationoptionsverbose" literal="generation.options.verbose"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATIONOPTIONSVERBOSE_VALUE = 1;

	/**
	 * The '<em><b>Generationoptionsupdate Tgt</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generationoptionsupdate Tgt</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSUPDATE_TGT
	 * @model name="generationoptionsupdateTgt" literal="generation.options.updateTgt"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATIONOPTIONSUPDATE_TGT_VALUE = 3;

	/**
	 * The '<em><b>Generationoptionslog Path</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generationoptionslog Path</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSLOG_PATH
	 * @model name="generationoptionslogPath" literal="generation.options.logPath"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATIONOPTIONSLOG_PATH_VALUE = 4;

	/**
	 * The '<em><b>Generationoptionsdestination Path</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generationoptionsdestination Path</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSDESTINATION_PATH
	 * @model name="generationoptionsdestinationPath" literal="generation.options.destinationPath"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATIONOPTIONSDESTINATION_PATH_VALUE = 5;

	/**
	 * An array of all the '<em><b>Static Configuration Parameters</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final StaticConfigurationParameters[] VALUES_ARRAY =
		new StaticConfigurationParameters[] {
			GENERATIONOPTIONSCLEAN,
			GENERATIONOPTIONSVERBOSE,
			GENERATIONOPTIONSUPDATE_TGT,
			GENERATIONOPTIONSLOG_PATH,
			GENERATIONOPTIONSDESTINATION_PATH,
		};

	/**
	 * A public read-only list of all the '<em><b>Static Configuration Parameters</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<StaticConfigurationParameters> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Static Configuration Parameters</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StaticConfigurationParameters get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StaticConfigurationParameters result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Static Configuration Parameters</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StaticConfigurationParameters getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StaticConfigurationParameters result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Static Configuration Parameters</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StaticConfigurationParameters get(int value) {
		switch (value) {
			case GENERATIONOPTIONSCLEAN_VALUE: return GENERATIONOPTIONSCLEAN;
			case GENERATIONOPTIONSVERBOSE_VALUE: return GENERATIONOPTIONSVERBOSE;
			case GENERATIONOPTIONSUPDATE_TGT_VALUE: return GENERATIONOPTIONSUPDATE_TGT;
			case GENERATIONOPTIONSLOG_PATH_VALUE: return GENERATIONOPTIONSLOG_PATH;
			case GENERATIONOPTIONSDESTINATION_PATH_VALUE: return GENERATIONOPTIONSDESTINATION_PATH;
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
	private StaticConfigurationParameters(int value, String name, String literal) {
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
	
} //StaticConfigurationParameters
