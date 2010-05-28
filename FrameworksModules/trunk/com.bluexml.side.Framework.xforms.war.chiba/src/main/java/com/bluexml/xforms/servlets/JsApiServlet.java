/**
 * 
 */
package com.bluexml.xforms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoControllerApiWrapperJs;
import com.bluexml.xforms.messages.MsgId;

/**
 * @author Amenel
 * 
 */
public class JsApiServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4218187107487547149L;

	private AlfrescoControllerApiWrapperJs jsGlue = null;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		jsGlue = new AlfrescoControllerApiWrapperJs(AlfrescoController.getInstance());
		System.out.println("BlueXML XForms - Javascript API servlet initialized.");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		processJsCall(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processJsCall(req, resp);
	}

	/**
	 * Entry point for exposing the AlfrescoControllerAPI to Javascript calls.
	 * <p>
	 * Input <b>MUST</b> be formatted as {function name}/{param1}/{param2}/{param3}/... Function
	 * names are case-insensitive but the parameter 'jscall' is case-sensitive.<br/>
	 * Examples:
	 * <ul>
	 * <li>.../xforms?jscall=getAlfrescoUrl</li>
	 * <li>.../xforms?jscall=authenticate/admin/passwd</li>
	 * </ul>
	 * </p>
	 * The output is always a JSON string returned as application/json in UTF-8.
	 * 
	 * @param req
	 * @param resp
	 * @param jsCallStr
	 * @throws IOException
	 */
	public void processJsCall(HttpServletRequest req, HttpServletResponse resp) throws IOException,
			ServletException {

		String jsCallStr = req.getParameter(MsgId.PARAM_JAVASCRIPT_CALL.getText());
		boolean isJsCall = (jsCallStr != null);
		if (isJsCall == false) {
			throw new ServletException("Invalid call. No function name and parameters given.");
		}
		String result = null;
		String outputStr;
		try {
			result = jsGlue.dispatch(jsCallStr);
			outputStr = "{\"result\": " + result + "}";
		} catch (Exception e) {
			outputStr = "{\"error\": " + e + "}";
		}

		// output the result of the call
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		ServletOutputStream out = resp.getOutputStream();
		out.println(outputStr);
		out.close();
	}

}
