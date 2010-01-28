package com.bluexml.xforms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;

/**
 * The Class UpdateServlet. via GET method @ http://HostAndPort/xforms/update?...<br>
 */
public class UpdateServlet extends AbstractServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3093330611732814842L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		update(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		update(req, resp);
	}

	/**
	 * Update.
	 * 
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 */
	protected void update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		AlfrescoController controller = AlfrescoController.getInstance();
		try {
			Node node = getDocumentReq(req);
			String skipIdStr = StringUtils.trimToNull(req.getParameter(ID_AS_SERVLET));
			boolean idAsServlet = !StringUtils.equals(skipIdStr, "false");

			AlfrescoTransaction transaction = new AlfrescoTransaction(
					controller);
			transaction.setLogin(controller.getLoginUserName());
			controller.persistClass(transaction, node, idAsServlet);
			transaction.executeBatch();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("BlueXML XForms - UpdateServlet initialized.");
	}
	
}
