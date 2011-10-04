package com.bluexml.xforms.actions;

import javax.servlet.ServletException;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class SetTypeAction.<br>
 * Set current type (abstract class forms)
 */
public class SetTypeAction extends AbstractWriteAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionCaption()
	 */
	@Override
	public String getActionCaption() {
		return "Submit";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_SETTYPE.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	@Override
	public void submit() throws ServletException {
		if (controller.isInStandaloneMode()) {
			String msg = "The Alfresco Controller is in standalone mode. Some actions are unavailable";
			navigationPath.setStatusMsg(msg);
			throw new ServletException(msg);
		}
		// get data type from user input
		String dataType = DOMUtil.getNodeValueByTagName(node, MsgId.INT_INSTANCE_SIDE_DATATYPE
				.getText(), false);
		navigationPath.peekCurrentPage().setFormName(dataType);
		navigationPath.peekCurrentPage().setDataTypeSet(true);
		setSubmissionDefaultLocation(getServletURL(), result);
	}

}
