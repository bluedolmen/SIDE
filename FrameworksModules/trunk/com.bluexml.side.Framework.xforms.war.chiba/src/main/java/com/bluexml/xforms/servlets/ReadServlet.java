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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ReadServlet. via GET method @ http://HostAndPort/xforms/read?...<br>
 */
public class ReadServlet extends AbstractServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8814073354764935112L;

	/** The Constant DATA_TYPE. */
	public static final String DATA_TYPE = "type";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		read(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		read(req, resp);
	}

	/**
	 * Read.
	 * 
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 */
	protected void read(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		AlfrescoController controller = AlfrescoController.getInstance();
		String dataType = StringUtils.trimToNull(req.getParameter(DATA_TYPE));
		if (dataType != null) {
			dataType = dataType.replace('_', '.');
		}
		String dataId = StringUtils.trimToNull(req.getParameter(DATA_ID));
		dataId = controller.patchDataId(dataId);

		String skipIdStr = StringUtils.trimToNull(req.getParameter(ID_AS_SERVLET));
		boolean idAsServlet = !StringUtils.equals(skipIdStr, "false");

		try {
			String userName = req.getParameter(MsgId.PARAM_USER_NAME.getText());
			AlfrescoTransaction transaction = createTransaction(controller, userName);
			Document node = controller.getMappingAgent().getInstanceClass(transaction, dataType,
					dataId, false, idAsServlet);
			Source xmlSource = new DOMSource(node);
			Result outputTarget = new StreamResult(resp.getOutputStream());
			documentTransformer.transform(xmlSource, outputTarget);
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
		System.out.println("BlueXML XForms - ReadServlet initialized.");
	}

}
