/**
 * PLEASE DO NOT FORMAT THIS FILE.
 */
package com.bluexml.xforms.generator.forms;

import com.bluexml.xforms.actions.AbstractAction;
import com.bluexml.xforms.actions.CancelAction;
import com.bluexml.xforms.actions.DeleteAction;
import com.bluexml.xforms.actions.SetTypeAction;
import com.bluexml.xforms.actions.SubmitAction;
import com.bluexml.xforms.actions.WorkflowStartAction;
import com.bluexml.xforms.messages.MsgId;

public enum FormTypeRendered {

		// NOTE: the rendering procedure adds actions in reverse order!
		formClass(
				new AbstractAction[] { new DeleteAction(), new SubmitAction() }, 
				"",
				MsgId.INT_DIRECTORY_FORM_CLASSES.getText(), 
				"Class forms", 
				"default forms based on data diagrams"),
		formClassSubClassSelector(
				new AbstractAction[] { new SetTypeAction() }, 
				"_selector", 
				MsgId.INT_DIRECTORY_FORM_SELECTOR.getText(),
				"Special forms for selecting subtypes",
				"these should not be called directly as they are reserved for the controller"),
		formClassList(
				new AbstractAction[] {}, 
				"", 
				MsgId.INT_DIRECTORY_FORM_LISTS.getText(),
				"List forms",
				"for listing the objects present in the repository and editing them in a default form"),
		formEnum(
				null,
				"",
				MsgId.INT_DIRECTORY_ENUMS.getText(),
				"Static enumerations",
				""
				),
		formForm(
				new AbstractAction[] { new DeleteAction(), new SubmitAction() }, 
				"",
				MsgId.INT_DIRECTORY_FORM_FORMS.getText(),
				"Customized forms",
				"based on form models"),
		formWkflw(
				new AbstractAction[] { new SubmitAction() }, 
				"", 
				MsgId.INT_DIRECTORY_FORM_WKFLW.getText(),
				"Workflow forms",
				""),
		// cancel action is added here because the process selection form (the only
		// form to have this type) goes through a specific rendering process.
		formWkflwSel(
				new AbstractAction[] { new CancelAction(), new WorkflowStartAction() }, 
				"",
				MsgId.INT_DIRECTORY_FORM_WKFLW.getText(),
				"",
				"");

	private AbstractAction[] actions;
	private String suffix;
	private String folder;
	private String label;
	private String description;
	

	private FormTypeRendered(AbstractAction[] actions, String suffix, String folder, String label, String description) {
		this.actions = actions;
		this.suffix = suffix;
		this.folder = folder;
		this.label = label;
		this.description = description;
	}

	public AbstractAction[] getActions() {
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
