package com.bluexml.xforms.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class CreateServlet. via GET method @ http://HostAndPort/xforms/create?...<br>
 */
public class CreateServlet extends AbstractServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1433772962163231503L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		create(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		create(req, resp);
	}

	/**
	 * Creates the.
	 * 
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 */
	protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		AlfrescoController controller = AlfrescoController.getInstance();
		try {
			Node node = getDocumentReq(req);
			String userName = req.getParameter(MsgId.PARAM_USER_NAME.getText());
			AlfrescoTransaction transaction = createTransaction(controller, userName);
			PersistFormResultBean resultBean = controller.getMappingAgent().persistClass(
					transaction, node, true, null);
			String result = resultBean.getResultStr();
			transaction.executeBatch();
			result = transaction.getIds().get(result);
			resp.getOutputStream().write(result.getBytes());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("BlueXML XForms - CreateServlet initialized.");
	}

}
