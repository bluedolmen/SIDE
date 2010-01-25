package com.bluexml.xforms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;

/**
 * The Class DeleteServlet. via GET method @ http://HostAndPort/xforms/delete?...<br>
 */
public class DeleteServlet extends AbstractServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7533187086201472710L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		delete(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		delete(req, resp);
	}

	/**
	 * Delete.
	 * 
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 */
	protected void delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		AlfrescoController controller = AlfrescoController.getInstance();
		try {
			String dataId = StringUtils.trimToNull(req.getParameter(DATA_ID));
			dataId = AlfrescoController.patchDataId(dataId);
			AlfrescoTransaction transaction = new AlfrescoTransaction(
					controller);
			transaction.setLogin(controller.getLoginUserName());
			controller.delete(transaction, dataId);
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
		System.out.println("BlueXML XForms - DeleteServlet initialized.");
	}

}
