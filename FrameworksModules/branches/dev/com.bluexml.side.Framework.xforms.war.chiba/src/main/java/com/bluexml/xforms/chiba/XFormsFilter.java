package com.bluexml.xforms.chiba;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.chiba.agent.web.WebFactory;
import org.chiba.agent.web.filter.BufferedHttpServletResponseWrapper;
import org.chiba.agent.web.servlet.WebUtil;
import org.chiba.agent.web.session.XFormsSession;
import org.chiba.agent.web.session.XFormsSessionManager;
import org.chiba.agent.web.session.impl.DefaultXFormsSessionManagerImpl;
import org.chiba.agent.web.session.impl.XFormsSessionBase;
import org.chiba.xml.xforms.exception.XFormsException;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;

public class XFormsFilter extends org.chiba.agent.web.filter.XFormsFilter {

	protected static final Log LOG = LogFactory.getLog(XFormsFilter.class);

	@Override
	public void doFilter(ServletRequest srvRequest,
			ServletResponse srvResponse, FilterChain filterChain)
			throws IOException, ServletException {

		//ensure correct Request encoding
		if (srvRequest.getCharacterEncoding() == null) {
			srvRequest.setCharacterEncoding(defaultRequestEncoding);
		}

		HttpServletRequest request = (HttpServletRequest) srvRequest;

		HttpServletResponse response = (HttpServletResponse) srvResponse;
		HttpSession session = request.getSession(true);

		if ("GET".equalsIgnoreCase(request.getMethod())
				&& request.getParameter("submissionResponse") != null) {
			doSubmissionResponse(request, response);
		} else {

			/* before servlet request */
			if (isXFormUpdateRequest(request)) {
				LOG.info("Start Update XForm");

				try {
					XFormsSession xFormsSession = WebUtil.getXFormsSession(
							request, session);
					xFormsSession.setRequest(request);
					xFormsSession.setResponse(response);
					xFormsSession.handleRequest();
				} catch (XFormsException e) {
					throw new ServletException(e);
				}
				LOG.info("End Update XForm");
			} else {

				/* do servlet request */
				LOG.info("Passing to Chain");
				BufferedHttpServletResponseWrapper bufResponse = new BufferedHttpServletResponseWrapper(
						(HttpServletResponse) srvResponse);
				filterChain.doFilter(srvRequest, bufResponse);
				LOG.info("Returned from Chain");

				// response is already committed to the client, so nothing is to
				// be done
				if (bufResponse.isCommitted())
					return;

				//set mode of operation (scripted/nonscripted) by config
				if (this.mode == null)
					this.mode = "true";

				if (this.mode.equalsIgnoreCase("true")) {
					request.setAttribute(WebFactory.SCRIPTED, "true");
				} else if (this.mode.equalsIgnoreCase("false")) {
					request.setAttribute(WebFactory.SCRIPTED, "false");
				}

				/* dealing with response from chain */
				if (handleResponseBody(request, bufResponse)) {
					byte[] data = prepareData(bufResponse);
					if (data.length > 0) {
						request.setAttribute(WebFactory.XFORMS_INPUTSTREAM,
								new ByteArrayInputStream(data));
					}
				}

				if (handleRequestAttributes(request)) {
					bufResponse.getOutputStream().close();
					LOG.info("Start Filter XForm");

					XFormsSession xFormsSession = null;
					try {
						XFormsSessionManager sessionManager = DefaultXFormsSessionManagerImpl
								.getXFormsSessionManager();
						xFormsSession = sessionManager.createXFormsSession(
								request, response, session);
						xFormsSession.setBaseURI(request.getRequestURL()
								.toString());
						xFormsSession.setXForms();
						xFormsSession.init();

						// FIXME patch for upload
						XFormsSessionBase xFormsSessionBase = (XFormsSessionBase) xFormsSession;
						reconfigure(xFormsSessionBase);

						xFormsSession.handleRequest();
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
						if (xFormsSession != null) {
							xFormsSession.close(e);
						}
						throw new ServletException(e.getMessage());
					}

					LOG.info("End Render XForm");
				} else {
					srvResponse.getOutputStream().write(bufResponse.getData());
					srvResponse.getOutputStream().close();
				}
			}
		}
	}

	private void reconfigure(XFormsSessionBase formsSessionBase) {
		formsSessionBase.getProcessor().setUploadDestination(
				AlfrescoController.TEMP_DIRECTORY.getAbsolutePath());
	}

}
