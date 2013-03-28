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

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.messages.MsgId;

public abstract class AbstractEditAction extends AbstractWriteAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] {
				MsgId.INT_ACT_PARAM_ANY_ASSOC.getText(),
				MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText(),
				MsgId.INT_ACT_PARAM_ANY_HINT.getText() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	@Override
	public void submit() throws ServletException {
		// if (AlfrescoController.isStandaloneMode()) {
		// String msg =
		// "The Alfresco Controller is in standalone mode. Some actions are unavailable";
		// navigationPath.setStatusMsg(msg);
		// throw new ServletException(msg);
		// }

		// save form state
		navigationPath.peekCurrentPage().setDataId(null);
		navigationPath.peekCurrentPage().setNode((Document) node);
		navigationPath.clearStatusMsg();
		edit();
		setSubmissionDefaultLocation(getServletURL(), result);
	}

	/**
	 * Retrieves an existing node's data type from Alfresco.
	 * 
	 * @param login
	 *            the login
	 * @param controller
	 *            the controller
	 * @param dataType
	 *            the data type
	 * @param dataId
	 *            the data id
	 * 
	 * @return the data type as returned by Alfresco, or null if 1- the type is not found in the
	 *         document read from Alfresco or 2- the document is null.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected String findRealDataType(AlfrescoController controller, String dataType, String dataId)
			throws ServletException {
		// FIXME do not get all class with all associations...
		Node node = controller.getInstanceClass(transaction, dataType, dataId, false, false);
		if (node == null) {
			return null;
		}
		String realDataType = DOMUtil.getNodeValueByTagName(node, MsgId.INT_INSTANCE_SIDE_DATATYPE
				.getText(), false);
		// if (realDataType == null) {
		// realDataType = dataType;
		// }
		// return realDataType;
		return realDataType;
	}

	protected abstract void edit() throws ServletException;

}
