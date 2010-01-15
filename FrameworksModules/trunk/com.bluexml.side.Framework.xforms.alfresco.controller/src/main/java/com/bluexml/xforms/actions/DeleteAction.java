package com.bluexml.xforms.actions;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.bluexml.xforms.controller.alfresco.AlfrescoControllerException;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import com.bluexml.side.form.utils.DOMUtil;

/**
 * The Class DeleteAction.<br>
 * Delete the edited instance
 */
public class DeleteAction extends AbstractTransactionalAction {

	private String elementId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionCaption()
	 */
	@Override
	public String getActionCaption() {
		return MsgPool.getMsg(MsgId.CAPTION_BUTTON_DELETE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_DELETE.getText();
	}

	@Override
	protected void prepareSubmit() throws Exception {
		// delete element
		deleteNode();
	}

	@Override
	protected void afterSubmit() throws Exception {
		// unstack last page
		Page currentPage = navigationPath.popCurrentPage();
		Map<String, String> initParams = currentPage.getInitParams();

		String url = StringUtils.trimToNull(initParams.get(MsgId.PARAM_PAGE_DELETE.getText()));
		if (StringUtils.trimToNull(url) != null) {
			super.redirectClient(url);
		} else {
			// previous page by default
			boolean empty = navigationPath.isEmpty();
			if (empty) {
				currentPage.setDataId(null);
				// forward to create if no page exists
				navigationPath.pushPage(currentPage);
			} else {
				// removes reference in current form
				if (elementId != null) {
					controller.removeReference(navigationPath.peekCurrentPage().getNode(),
							elementId);

				}
			}
			setSubmissionDefaultLocation(getServletURL(), result);
		}
	}

	/**
	 * Delete node.
	 * 
	 * @return the string
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	private void deleteNode() throws AlfrescoControllerException {
		elementId = DOMUtil.getNodeValueByTagName(node, MsgId.INT_INSTANCE_SIDEID.getText(), false);
		// #977: deletes fail to be performed in form's
		if (elementId == null) {
			Element root = ((Document) node).getDocumentElement();
			Element data = DOMUtil.getFirstElement(root);
			elementId = DOMUtil.getNodeValueByTagName(data, MsgId.INT_INSTANCE_SIDEID.getText(),
					false);
		}
		// #977
		// we should always have a valid Id here
		if (elementId != null) {
			controller.delete(transaction, elementId);
		} else {
			navigationPath.setStatusMsg("Delete was NOT performed: no object ID.");
		}
	}

}
