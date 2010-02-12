package com.bluexml.xforms.controller.navigation;

/**
 * The Enum FormTypeEnum.<br/>
 * This should be kept in sync with FormTypeRendered, at least for forms (and not enums).
 */
public enum FormTypeEnum {

		/** Forms built from class diagrams. */
		CLASS,
		/** Forms built from form diagrams, for data. */
		FORM,
		/** Forms for abstract types (and types with subtypes) */
		SELECTOR,
		/** Forms built from search forms */
		SEARCH,
		/** Forms built from form diagrams, for workflows. */
		WKFLW,
		LIST,
		/** Form built programmatically for selecting workflow defs and instances. */
		WKFLW_SELECTION,
		/** Default value signaling that something is wrong. */
		BOTH;

}
