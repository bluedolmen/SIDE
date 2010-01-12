package com.bluexml.xforms.generator.forms;

import java.io.File;

import com.bluexml.xforms.actions.AbstractAction;
import com.bluexml.xforms.actions.CancelAction;
import com.bluexml.xforms.actions.DeleteAction;
import com.bluexml.xforms.actions.SetTypeAction;
import com.bluexml.xforms.actions.SubmitAction;
import com.bluexml.xforms.actions.WorkflowStartAction;
import com.bluexml.xforms.messages.MsgId;

public enum FormTypeRendered {

		// NOTE: the rendering procedure adds actions in reverse order!
		formClass(new AbstractAction[] { new DeleteAction(), new SubmitAction() }, "",
				MsgId.INT_DIRECTORY_FORM_CLASSES.getText()),
		formClassSubClassSelector(new AbstractAction[] { new SetTypeAction() }, "_selector", ""),
		formClassList(new AbstractAction[] {}, "", MsgId.INT_DIRECTORY_FORM_LISTS.getText()
				+ File.separatorChar),
		formForm(new AbstractAction[] { new DeleteAction(), new SubmitAction() }, "",
				MsgId.INT_DIRECTORY_FORM_FORMS.getText() + File.separatorChar),
		formWkflw(new AbstractAction[] { new SubmitAction() }, "", MsgId.INT_DIRECTORY_FORM_WKFLW
				.getText()
				+ File.separatorChar),
		// cancel action is added here because the process selection form (the only
		// form to have this type) goes through a specific rendering process.
		formWkflwSel(new AbstractAction[] { new CancelAction(), new WorkflowStartAction() }, "",
				MsgId.INT_DIRECTORY_FORM_WKFLW.getText() + File.separatorChar);

	private AbstractAction[] actions;
	private String suffix;
	private String folder;

	private FormTypeRendered(AbstractAction[] actions, String suffix, String folder) {
		this.actions = actions;
		this.suffix = suffix;
		this.folder = folder;
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

}
