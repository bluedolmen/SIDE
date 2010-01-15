/**
 * 
 */
package com.bluexml.xforms.actions;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;

/**
 * Returns the list of process definitions from Alfresco.
 * <p>
 * Format:
 * 
 * <pre>
 * &lt;SELECTEDID&gt;
 * &lt;SELECTEDLABEL&gt;
 * &lt;item&gt; (repeatable)
 *   &lt;value&gt; (the displayed label)
 *   &lt;id&gt; (matched to XForms 'value' - for XForms internal processing)
 * &lt;/item&gt;
 * </pre>
 * 
 * Read action.
 * 
 * @author Amenel
 */
public class WorkflowProcessListAction extends AbstractReadAction {

	/**
	 * 
	 */
	public WorkflowProcessListAction() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_WRKFLW_PROCESS_LIST.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		// no parameters needed
		return new String[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#resolve()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Node resolve() throws Exception {
		// get to know if we should be limiting process defs to BlueXML-generated ones
		Page currentPage = navigationPath.peekCurrentPage();
		Map<String, String> initParams = currentPage.getInitParams();
		String paramValue = null;
		if (initParams != null) {
			paramValue = initParams.get(MsgId.PARAM_ALLOW_ALL_DEFS.getText());
		}
		boolean allowAllDefs = StringUtils.equals(paramValue, "true");
		// build skeleton of the instance
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document instance = docBuilder.newDocument();
		Element rootElement = instance.createElement("root");
		Element selLabel = instance.createElement(MsgId.INT_INSTANCE_SELECTEDLABEL.getText());
		Element selId = instance.createElement(MsgId.INT_INSTANCE_SELECTEDID.getText());
		rootElement.appendChild(selLabel);
		rootElement.appendChild(selId);

		// retrieve the process definitions id
		List<WorkflowDefinition> processList;
		processList = (List<WorkflowDefinition>) controller.workflowRequest(transaction,
				"getDefinitions", new Vector<Object>());
		// for each relevant definition, return an item
		for (WorkflowDefinition process : processList) {
			if (allowAllDefs || AlfrescoController.workflowIsBlueXMLDefinition(process.name)) {
				Element item = instance.createElement("item");
				Element id = instance.createElement("id");
				Element value = instance.createElement("value");

				id.setTextContent(process.getId());
				value.setTextContent(process.getTitle());
				item.appendChild(id);
				item.appendChild(value);
				rootElement.appendChild(item);
			}
		}

		instance.appendChild(rootElement);
		return instance;
	}

}
