package com.bluexml.xforms.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class GetAction.<br>
 * Returns the main instance for a form (the form is determined through the "datatype" field of the
 * current page).<br/>
 * Supports: class forms, FormClass, FormSearch. <br/>
 * 
 */
public class GetAction extends AbstractReadAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeResolve()
	 */
	@Override
	public Node resolve() throws ServletException {
		Page currentPage = navigationPath.peekCurrentPage();
		String dataType = currentPage.getFormName();
		Map<String, String> initParams = currentPage.getInitParams();
		String dataId = currentPage.getDataId();
		FormTypeEnum formType = currentPage.getFormType();
		Document node = currentPage.getNode();
		// 
		// node = provideInstance(controller, transaction, dataType, initParams, dataId, node,
		// formType, formIsReadOnly);

		boolean formIsReadOnly = (currentPage.getDataType() != currentPage.getFormName());
		if (StringUtils.trimToNull(dataId) != null || node == null) {
			if (formType == FormTypeEnum.FORM) {
				node = controller
						.getForm(transaction, dataType, dataId, initParams, formIsReadOnly);
			} else if (formType == FormTypeEnum.CLASS) {
				node = controller.getClass(transaction, dataType, dataId, initParams,
						formIsReadOnly, false);
			} else if (formType == FormTypeEnum.WKFLW) {
				node = getInstanceWorkflow(currentPage, dataType);
			} else if ((formType == FormTypeEnum.LIST) || (formType == FormTypeEnum.SELECTOR)) {
				return getInstanceListSelector(dataType, formType);
				// } else if (formType == FormTypeEnum.SEARCH) {
				// node = getInstanceSearch(currentPage, dataType);
			}
		}

		return node;
	}

	/**
	 * Gets the instance for forms that do not require calling the controller because of how simple
	 * their instance is. The controller acts as a bridge to the mapping; when there's no
	 * information to extract from the mapping, a better option is to serve the instance here.
	 * 
	 * @param dataType
	 * @param formType
	 * @return the instance
	 */
	private Document getInstanceListSelector(String dataType, FormTypeEnum formType) {
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.error("Failed to obtain a document builder", e);
			return null;
		}
		Document instance = docBuilder.newDocument();
		Element rootElement = instance.createElement(dataType);
		instance.appendChild(rootElement);
		if (formType == FormTypeEnum.SELECTOR) {
			// the data type in the combo box
			Element sideData = instance.createElement(MsgId.INT_INSTANCE_SIDE_DATATYPE.getText());
			sideData.setTextContent(dataType);
			rootElement.appendChild(sideData);
			return instance;
		} else if (formType == FormTypeEnum.LIST) {
			// the id of the object whose Edit button was clicked
			Element editedid = instance.createElement(MsgId.INT_INSTANCE_SIDEEDIT.getText());
			rootElement.appendChild(editedid);
			return instance;
		}

		return null; // we never reach here
	}

	/**
	 * Returns the instance for workflow forms, which includes the instance for the data form. <br/>
	 * NOTE: the form name is expected in the returned instance (incl. process & task names), and
	 * the data form is nested under the [form name] tag.
	 * <p>
	 * Format:
	 * 
	 * <pre>
	 * &lt;workflow&gt; &lt;!-- this is the node returned by getDocumentElement() on XForms instances --&gt;
	 *   &lt;FORM NAME&gt;
	 *     PROPERTIES OF THE WORFKLOW
	 *     &lt;DATA FORM &gt;
	 *       THE DATA FORM'S PROPERTIES
	 *     &lt;/DATA FORM &gt;
	 *   &lt;/FORM NAME&gt;
	 * &lt;/workflow&gt;
	 * </pre>
	 */
	private Document getInstanceWorkflow(Page currentPage, String wkFormName)
			throws ServletException {
		//
		// get the instance for the task
		Document docWkflw = controller.getWorkflowFormInstance(wkFormName);
		controller.patchWorkflowInstance(transaction, wkFormName, docWkflw, currentPage
				.getWkflwInstanceId());
		//
		// get the instance of the attached data form
		Map<String, String> initParams = currentPage.getInitParams();
		String dataId = currentPage.getDataId();
		WorkflowTaskType taskType = controller.getWorkflowTaskType(currentPage.getFormName());
		String dataFormName = taskType.getDataForm();
		Document docForm = controller.getForm(transaction, dataFormName, dataId, initParams, false);
		//
		// we need to nest the data form instance under workflow
		Element wkDocElt = docWkflw.getDocumentElement();
		List<Element> childrenWk = DOMUtil.getAllChildren(wkDocElt);
		Element wkElt = DOMUtil.getOneElementByTagName(childrenWk, wkFormName);

		Element clone;
		List<Element> children = DOMUtil.getAllChildren(docForm.getDocumentElement());
		Element dataElt = DOMUtil.getOneElementByTagName(children, dataFormName);
		clone = (Element) dataElt.cloneNode(true);
		docWkflw.adoptNode(clone);
		wkElt.appendChild(clone);
		//
		// also copy supplementary tags that are added for internal usage (SIDEDataType, etc.)
		for (Element child : children) {
			if (child.getTagName().equals(dataFormName) == false) {
				clone = (Element) child.cloneNode(true);
				docWkflw.adoptNode(clone);
				wkDocElt.appendChild(clone);
			}
		}
		DOMUtil.logXML(docWkflw, false);

		return docWkflw;
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
		return new String[] { MsgId.INT_ACT_PARAM_GET_FORMTYPE.getText() };
	}

}
