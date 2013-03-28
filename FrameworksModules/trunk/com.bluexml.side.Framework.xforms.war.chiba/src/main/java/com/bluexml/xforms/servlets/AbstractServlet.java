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
package com.bluexml.xforms.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class AbstractServlet.
 */
public abstract class AbstractServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7815279587960310608L;

	/** The Constant DATA_NODE. */
	protected static final String DATA_NODE = "data";

	/** The Constant DATA_ID. */
	protected static final String DATA_ID = "id";

	/** Whether servlet tells controller that service requests come from a servlet. For debugging. */
	protected static final String ID_AS_SERVLET = "identifyAsServlet";

	/** The document transformer. */
	protected static Transformer documentTransformer;
	static {
		try {
			documentTransformer = TransformerFactory.newInstance().newTransformer();
			documentTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected AlfrescoTransaction createTransaction(AlfrescoController controller, String userName) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(controller, userName);

		Map<String, String> simulatedParams = new HashMap<String, String>();
		simulatedParams.put(MsgId.PARAM_USER_NAME.getText(), userName);

		transaction.setLogin(controller.getParamUserName(simulatedParams));
		return transaction;
	}

	/**
	 * Gets the data document from the request.
	 * 
	 * @param req
	 *            the req
	 * 
	 * @return the document req
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected Node getDocumentReq(HttpServletRequest req) throws IOException {
		Node node = null;
		String dataNode = req.getParameter(DATA_NODE);
		if (dataNode == null) {
			node = DOMUtil.getDocumentFromStream(req.getInputStream());
		} else {
			node = DOMUtil.getDocumentFromString(dataNode);
		}
		return node;
	}

}
