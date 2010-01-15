package com.bluexml.xforms.controller.navigation;

/**
 * The Enum FormTypeEnum.
 */
public enum FormTypeEnum {

		/** Forms built from class diagrams. */
		CLASS,
		/** Forms built from form diagrams, for data. */
		FORM,
		/** Forms built from form diagrams, for workflows. */
		WKFLW,
		LIST,
		/** Form built programmatically for selecting workflow defs and instances. */
		WKFLW_SELECTION,
		/** Default value signaling that something is wrong. */
		BOTH;

}
