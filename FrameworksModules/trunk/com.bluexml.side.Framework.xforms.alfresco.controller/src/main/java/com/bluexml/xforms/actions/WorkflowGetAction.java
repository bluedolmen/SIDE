/**
 *
 */
package com.bluexml.xforms.actions;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.messages.MsgId;

/**
 * Returns the instance for the process selection form.
 * <p>
 * Format:
 * 
 * <pre>
 * &lt;process&gt;
 *   &lt;SIDEID /&gt;
 *   &lt;SIDELABEL /&gt;
 * &lt;/process&gt;
 * &lt;instance&gt;
 *   &lt;SIDEID /&gt;
 *   &lt;SIDELABEL /&gt;
 * &lt;/instance&gt;
 * </pre>
 * 
 * @author Amenel
 */
public class WorkflowGetAction extends AbstractReadAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_GET_WKFLW_SELECTION.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		// nothing needed
		return new String[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#resolve()
	 */
	@Override
	public Node resolve() throws Exception {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document instance = docBuilder.newDocument();
		Element rootElement = instance.createElement("root");

		Element plabel = instance.createElement(MsgId.INT_INSTANCE_SIDELABEL.getText());
		Element pid = instance.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
		Element tlabel = instance.createElement(MsgId.INT_INSTANCE_SIDELABEL.getText());
		Element tid = instance.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
		// Element wkflw =
		// instance.createElement(MsgId.INT_WKFLW_NODESET_PREFIX.getText());
		Element process = instance.createElement(MsgId.INT_WKFLW_PROCESS_NODESET.getText());
		Element wkInstance = instance.createElement(MsgId.INT_WKFLW_INSTANCE_NODESET.getText());

		process.appendChild(pid);
		process.appendChild(plabel);

		wkInstance.appendChild(tid);
		wkInstance.appendChild(tlabel);

		rootElement.appendChild(process);
		rootElement.appendChild(wkInstance);

		instance.appendChild(rootElement);
		return instance;
	}

}
