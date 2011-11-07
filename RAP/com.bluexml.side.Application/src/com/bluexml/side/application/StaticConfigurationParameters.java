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
 * <!-- begin-model-doc -->
 * List of default configurations parameters.
 * <!-- end-model-doc -->
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
	 * The '<em><b>Generationoptionsdocumentation</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSDOCUMENTATION_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTIONSDOCUMENTATION(1, "generationoptionsdocumentation", "generation.options.documentation"), /**
	 * The '<em><b>UPDATE DEPENDENCIES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPDATE_DEPENDENCIES_VALUE
	 * @generated
	 * @ordered
	 */
	UPDATE_DEPENDENCIES(2, "UPDATE_DEPENDENCIES", "UPDATE_DEPENDENCIES"), /**
	 * The '<em><b>FM dev</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FM_DEV_VALUE
	 * @generated
	 * @ordered
	 */
	FM_DEV(3, "FM_dev", "FM_dev"), /**
	 * The '<em><b>Generationoptionslog Path</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSLOG_PATH_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTIONSLOG_PATH(4, "generationoptionslogPath", "generation.options.logPath"), /**
	 * The '<em><b>Generationoptionsdestination Path</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSDESTINATION_PATH_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTIONSDESTINATION_PATH(5, "generationoptionsdestinationPath", "generation.options.destinationPath"), /**
	 * The '<em><b>Generationoption Skip Validation</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTION_SKIP_VALIDATION_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATIONOPTION_SKIP_VALIDATION(6, "generationoptionSkipValidation", "generation.option.Skip.Validation"), /**
	 * The '<em><b>Generation Option Offline Mode</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATION_OPTION_OFFLINE_MODE_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATION_OPTION_OFFLINE_MODE(7, "generationOptionOfflineMode", "generation.option.offlineMode"), /**
	 * The '<em><b>Generation Option Generate</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATION_OPTION_GENERATE_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATION_OPTION_GENERATE(8, "generationOptionGenerate", "generation.option.generate"), /**
	 * The '<em><b>Generation Option Complete</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATION_OPTION_COMPLETE_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATION_OPTION_COMPLETE(9, "generationOptionComplete", "generation.option.complete"), /**
	 * The '<em><b>Generation Option Resolve Dependencies</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATION_OPTION_RESOLVE_DEPENDENCIES_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATION_OPTION_RESOLVE_DEPENDENCIES(10, "generationOptionResolveDependencies", "generation.option.resolve.dependencies");

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
	 * The '<em><b>Generationoptionsdocumentation</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generationoptionsdocumentation</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTIONSDOCUMENTATION
	 * @model name="generationoptionsdocumentation" literal="generation.options.documentation"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATIONOPTIONSDOCUMENTATION_VALUE = 1;

	/**
	 * The '<em><b>UPDATE DEPENDENCIES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPDATE DEPENDENCIES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPDATE_DEPENDENCIES
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UPDATE_DEPENDENCIES_VALUE = 2;

	/**
	 * The '<em><b>FM dev</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FM dev</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FM_DEV
	 * @model name="FM_dev"
	 * @generated
	 * @ordered
	 */
	public static final int FM_DEV_VALUE = 3;

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
	 * The '<em><b>Generationoption Skip Validation</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generationoption Skip Validation</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATIONOPTION_SKIP_VALIDATION
	 * @model name="generationoptionSkipValidation" literal="generation.option.Skip.Validation"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATIONOPTION_SKIP_VALIDATION_VALUE = 6;

	/**
	 * The '<em><b>Generation Option Offline Mode</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generation Option Offline Mode</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATION_OPTION_OFFLINE_MODE
	 * @model name="generationOptionOfflineMode" literal="generation.option.offlineMode"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATION_OPTION_OFFLINE_MODE_VALUE = 7;

	/**
	 * The '<em><b>Generation Option Generate</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generation Option Generate</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATION_OPTION_GENERATE
	 * @model name="generationOptionGenerate" literal="generation.option.generate"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATION_OPTION_GENERATE_VALUE = 8;

	/**
	 * The '<em><b>Generation Option Complete</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generation Option Complete</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATION_OPTION_COMPLETE
	 * @model name="generationOptionComplete" literal="generation.option.complete"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATION_OPTION_COMPLETE_VALUE = 9;

	/**
	 * The '<em><b>Generation Option Resolve Dependencies</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generation Option Resolve Dependencies</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATION_OPTION_RESOLVE_DEPENDENCIES
	 * @model name="generationOptionResolveDependencies" literal="generation.option.resolve.dependencies"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATION_OPTION_RESOLVE_DEPENDENCIES_VALUE = 10;

	/**
	 * An array of all the '<em><b>Static Configuration Parameters</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final StaticConfigurationParameters[] VALUES_ARRAY =
		new StaticConfigurationParameters[] {
			GENERATIONOPTIONSCLEAN,
			GENERATIONOPTIONSDOCUMENTATION,
			UPDATE_DEPENDENCIES,
			FM_DEV,
			GENERATIONOPTIONSLOG_PATH,
			GENERATIONOPTIONSDESTINATION_PATH,
			GENERATIONOPTION_SKIP_VALIDATION,
			GENERATION_OPTION_OFFLINE_MODE,
			GENERATION_OPTION_GENERATE,
			GENERATION_OPTION_COMPLETE,
			GENERATION_OPTION_RESOLVE_DEPENDENCIES,
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
			case GENERATIONOPTIONSDOCUMENTATION_VALUE: return GENERATIONOPTIONSDOCUMENTATION;
			case UPDATE_DEPENDENCIES_VALUE: return UPDATE_DEPENDENCIES;
			case FM_DEV_VALUE: return FM_DEV;
			case GENERATIONOPTIONSLOG_PATH_VALUE: return GENERATIONOPTIONSLOG_PATH;
			case GENERATIONOPTIONSDESTINATION_PATH_VALUE: return GENERATIONOPTIONSDESTINATION_PATH;
			case GENERATIONOPTION_SKIP_VALIDATION_VALUE: return GENERATIONOPTION_SKIP_VALIDATION;
			case GENERATION_OPTION_OFFLINE_MODE_VALUE: return GENERATION_OPTION_OFFLINE_MODE;
			case GENERATION_OPTION_GENERATE_VALUE: return GENERATION_OPTION_GENERATE;
			case GENERATION_OPTION_COMPLETE_VALUE: return GENERATION_OPTION_COMPLETE;
			case GENERATION_OPTION_RESOLVE_DEPENDENCIES_VALUE: return GENERATION_OPTION_RESOLVE_DEPENDENCIES;
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
