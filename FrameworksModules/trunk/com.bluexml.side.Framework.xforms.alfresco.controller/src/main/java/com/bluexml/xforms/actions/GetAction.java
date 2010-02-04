package com.bluexml.xforms.actions;

import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class GetAction.<br>
 * Returns the main instance for a form (the form is determined through the "datatype" field of the
 * current page).
 */
public class GetAction extends AbstractReadAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeResolve()
	 */
	@Override
	public Node resolve() throws ServletException {
		// load element from current page
		Page currentPage = navigationPath.peekCurrentPage();
		String dataType = currentPage.getFormName();
		Map<String, String> initParams = currentPage.getInitParams();
		String dataId = currentPage.getDataId();
		FormTypeEnum formType = currentPage.getFormType();
		boolean formIsReadOnly = (currentPage.getDataType() != currentPage.getFormName());
		Document node = currentPage.getNode();
		// 
		node = provideInstance(controller, transaction, dataType, initParams, dataId, node,
				formType, formIsReadOnly);
		return node;
	}

	/**
	 * @param dataType
	 * @param initParams
	 * @param dataId
	 * @param node
	 * @param formType
	 * @return
	 * @throws ServletException
	 */
	public static Document provideInstance(AlfrescoController controller,
			AlfrescoTransaction transaction, String dataType, Map<String, String> initParams,
			String dataId, Document node, FormTypeEnum formType, boolean formIsReadOnly)
			throws ServletException {
		Document doc = node;
		if (StringUtils.trimToNull(dataId) != null || node == null) {
			if (formType == FormTypeEnum.FORM) {
				doc = controller
						.getForm(transaction, dataType, dataId, initParams, formIsReadOnly);
			} else { // expecting FormTypeEnum.CLASS here
				doc = controller.getClass(transaction, dataType, dataId, initParams,
						formIsReadOnly, false);
			}
		}
		return doc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_GET_FORM.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] {};
	}

}
