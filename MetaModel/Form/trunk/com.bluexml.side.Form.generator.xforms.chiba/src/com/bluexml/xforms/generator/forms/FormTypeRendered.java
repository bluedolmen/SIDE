/**
 * PLEASE DO NOT FORMAT THIS FILE.
 */
package com.bluexml.xforms.generator.forms;

import com.bluexml.xforms.messages.MsgId;

public enum FormTypeRendered {

		// NOTE: the rendering procedure adds actions in reverse order!
		formClass(
				new FormSubmissionActions[] { FormSubmissionActions.Submit, FormSubmissionActions.Delete, FormSubmissionActions.Cancel }, 
				"",
				MsgId.INT_DIRECTORY_FORM_CLASSES.getText(), 
				"Class forms", 
				"default forms based on data diagrams"),
		formClassSubClassSelector(
				new FormSubmissionActions[] { FormSubmissionActions.SetType, FormSubmissionActions.Cancel }, 
				MsgId.INT_SUFFIX_FILENAME_SELECTORS.getText(), 
				MsgId.INT_DIRECTORY_FORM_SELECTOR.getText(),
				"Special forms for selecting subtypes",
				"these should not be called directly as they are reserved for the controller"),
		formClassList(
				new FormSubmissionActions[] {}, 
				"", 
				MsgId.INT_DIRECTORY_FORM_LISTS.getText(),
				"List forms",
				"for listing the objects present in the repository and editing them in a default form"),
		formEnum(
				null, // not relevant for enums
				"",
				MsgId.INT_DIRECTORY_ENUMS.getText(),
				"Static enumerations",
				""
				),
		formForm(
				new FormSubmissionActions[] { FormSubmissionActions.Submit, FormSubmissionActions.Delete, FormSubmissionActions.Cancel }, 
				"",
				MsgId.INT_DIRECTORY_FORM_FORMS.getText(),
				"Customized forms",
				"based on form models"),
		formSearch(
				new FormSubmissionActions[] { FormSubmissionActions.Search, FormSubmissionActions.Cancel }, 
				"",
				MsgId.INT_DIRECTORY_FORM_SEARCH.getText(),
				"Search forms",
				""),
		formWkflw(
				new FormSubmissionActions[] { FormSubmissionActions.Submit }, 
				"", 
				MsgId.INT_DIRECTORY_FORM_WKFLW.getText(),
				"Workflow forms",
				"");

	private FormSubmissionActions[] actions;
	private String suffix;
	private String folder;
	private String label;
	private String description;
	

	private FormTypeRendered(FormSubmissionActions[] actions, String suffix, String folder, String label, String description) {
		this.actions = actions;
		this.suffix = suffix;
		this.folder = folder;
		this.label = label;
		this.description = description;
	}

	public FormSubmissionActions[] getActions() {
		return actions;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getFolder() {
		return folder;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
