/**
 * 
 */
package com.bluexml.xforms.actions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.messages.MsgId;

/**
 * Read action: returns the list of in-progress tasks from Alfresco for a specific user and a
 * specific process definition.
 * <p>
 * Requires both process def id and user name.
 * <p>
 * Format:
 * 
 * <pre>
 * &lt;SELECTEDID&gt;
 * &lt;SELECTEDLABEL&gt;
 * &lt;item&gt; (repeatable)
 *   &lt;value&gt; (the display label)
 *   &lt;id&gt; (the &lt;b&gt;task id&lt;/b&gt;)
 * &lt;/item&gt;
 * </pre>
 * 
 * 
 * @author Amenel
 * @deprecated
 */
public class WorkflowInstanceListAction extends AbstractAction {

	/**
	 * 
	 */
	public WorkflowInstanceListAction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
//		return MsgId.INT_ACT_CODE_WRKFLW_INSTANCE_LIST.getText();
		return "dummy";
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
	/**
	 * Called when the form loads initially.
	 */
	@Override
	public Node resolve() {
			try {
				return buildInstance();
			} catch (ParserConfigurationException e) {
				logger.error("Failed to obtain a document builder", e);
				return null;
			}
	}

	private Document buildInstance() throws ParserConfigurationException {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document instance = docBuilder.newDocument();
		Element rootElement = instance.createElement("root");
		instance.appendChild(rootElement);
		Element selLabel = instance.createElement(MsgId.INT_INSTANCE_SELECTEDLABEL.getText());
		Element selId = instance.createElement(MsgId.INT_INSTANCE_SELECTEDID.getText());
		rootElement.appendChild(selLabel);
		rootElement.appendChild(selId);
		return instance;
	}

	/**
	 * Builds a descriptive label for a workflow instance.
	 * 
	 * @param task
	 * @return
	 */
	@SuppressWarnings("unused")
	private String buildDisplayTitle(WorkflowTask task) {
		String result;
		NodeRef initiator = task.path.instance.initiator;
		Date startDate = task.path.instance.startDate;
		String initiatorName;
		if (initiator == null) {
			initiatorName = "System";
		} else {
			initiatorName = controller.systemGetNodeProperty(initiator, ContentModel.PROP_USERNAME);
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String dateString = formatter.format(startDate);
		result = "Tâche '" + task.description + "', démarrée par '" + initiatorName + "' @ "
				+ dateString;
		return result;
	}

	/**
	 * Called when there's a change of selection in the process list.
	 * @throws ServletException 
	 * 
	 */
	@Override
	public void submit() throws ServletException {
//		Map<String, String> initParams = navigationPath.peekCurrentPage().getInitParams();
//		String userName = initParams.get(MsgId.PARAM_USER_NAME.getText());
//		if (StringUtils.trimToNull(userName) == null) {
//			throw new ServletException("Can't retrieve instances: no user name is provided.");
//		}
//		String defId;
//		Document instance;
//		try {
//			instance = buildInstance();
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//			logger.error("Failed to obtain a document builder", e);
//			return;
//		}
//		Element rootElement = instance.getDocumentElement();
//
//		if (node != null) {
//			// retrieve the process id
//			Element element = null;
//			if (node instanceof Document) {
//				element = ((Document) node).getDocumentElement();
//			} else if (node instanceof Element) {
//				element = (Element) node;
//			}
//			Element processElt = DOMUtil.getChild(element, MsgId.INT_WKFLW_PROCESS_NODESET
//					.getText());
//			Element IDElt = DOMUtil.getChild(processElt, MsgId.INT_INSTANCE_SIDEID.getText());
//			defId = StringUtils.trimToNull(IDElt.getTextContent());
//			// ask for instances
//			if (StringUtils.trimToNull(defId) != null) {
//				List<WorkflowTask> taskList = controller.workflowGetPooledTasks(userName);
//				if (taskList != null) {
//					// find the def name of the selected process; we can't rely on ids because
//					// each server launch creates a new version of the def, with different ids...
//					WorkflowDefinition def = controller.workflowGetWorkflowById(defId);
//
//					// for each filtered instance, return an item
//					for (WorkflowTask wkTask : taskList) {
//						if (StringUtils.equals(wkTask.path.instance.definition.name, def.name)) {
//							Element item = instance.createElement("item");
//							Element id = instance.createElement("id");
//							Element value = instance.createElement("value");
//
//							id.setTextContent(wkTask.id);
//							String displayTitle = buildDisplayTitle(wkTask);
//							value.setTextContent(displayTitle);
//							item.appendChild(id);
//							item.appendChild(value);
//							rootElement.appendChild(item);
//						}
//					}
//				}
//			}
//			// convert to string
//			Source xmlSource = new DOMSource(instance);
//			ByteArrayOutputStream pos = new ByteArrayOutputStream();
//			Result outputTarget = new StreamResult(pos);
//			try {
//				documentTransformer.transform(xmlSource, outputTarget);
//			} catch (TransformerException e) {
//				logger.error("Failed to convert the list document into a string", e);
//				throw new ServletException(MsgId.MSG_DEFAULT_ERROR_MSG.getText());
//			}
//
//			ByteArrayInputStream pis = new ByteArrayInputStream(pos.toByteArray());
//
//			result.put(XFormsProcessor.SUBMISSION_RESPONSE_STREAM, pis);
//		}
	}

}
