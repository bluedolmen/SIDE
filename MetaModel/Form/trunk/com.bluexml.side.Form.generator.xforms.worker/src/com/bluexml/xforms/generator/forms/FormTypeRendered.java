/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
				"Default forms", 
				"based on data models"),
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
				new FormSubmissionActions[] { }, 
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
