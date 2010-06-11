/**
 * 
 */
package com.bluexml.xforms.generator.forms.modelelement;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * @author Amenel
 * @deprecated
 */
public class ModelElementWorkflowInstanceUpdater extends AbstractModelElementUpdater {

	public ModelElementWorkflowInstanceUpdater() {
		// super("", MsgId.INT_WKFLW_INSTANCE_INSTANCE_NAME.getText());
		super("", null);
	}

	@Override
	public Element getModelElement() {
		Element submission = XFormsGenerator.createElement("submission",
				XFormsGenerator.NAMESPACE_XFORMS);
		// submission.setAttribute("action", MsgId.INT_URI_SCHEME_WRITER.getText()
		// + MsgId.INT_ACT_CODE_WRKFLW_INSTANCE_LIST + "/");
		submission.setAttribute("replace", "instance");
		submission.setAttribute("instance", instanceName);
		submission.setAttribute("method", "post");

		String submitId = XFormsGenerator.getId("submit");
		for (Element linkedElement : linkedElements) {
			linkedElement.setAttribute("submission", submitId);
		}
		submission.setAttribute("id", submitId);
		return submission;
	}
}
