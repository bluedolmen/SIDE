package com.bluexml.xforms.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.chiba.xml.dom.DOMUtil;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;

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
			documentTransformer = TransformerFactory.newInstance()
					.newTransformer();
			documentTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected AlfrescoTransaction createTransaction(
			AlfrescoController controller) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(controller);
		transaction.setLogin(controller.getLoginUserName());
		return transaction;
	}

	/**
	 * Gets the document req.
	 * 
	 * @param req
	 *            the req
	 * 
	 * @return the document req
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected Node getDocumentReq(HttpServletRequest req)
			throws ParserConfigurationException, SAXException, IOException {
		Node node = null;
		String dataNode = req.getParameter(DATA_NODE);
		if (dataNode == null) {
			node = DOMUtil.parseInputStream(req.getInputStream(), true, false);
		} else {
			node = DOMUtil.parseString(dataNode, true, false);
		}
		return node;
	}

}
