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
