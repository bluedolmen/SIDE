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
				MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText() };
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
		Node node = controller.getInstanceClass(transaction, dataType, dataId, null, false, false);
		if (node == null) {
			return null;
		}
		String realDataType = DOMUtil.getNodeValueByTagName(node, MsgId.INT_INSTANCE_SIDE_DATATYPE
				.getText(), false);
//		if (realDataType == null) {
//			realDataType = dataType;
//		}
//		return realDataType;
		return realDataType;
	}

	protected abstract void edit() throws ServletException;

}
