package com.bluexml.side.Framework.alfresco.shareLanguagePicker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alfresco.web.scripts.servlet.WebScriptServlet;
import org.alfresco.web.scripts.servlet.WebScriptServletRuntime;
import org.alfresco.web.site.FrameworkHelper;
import org.alfresco.web.site.RequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CustomWebScriptServlet extends WebScriptServlet {
	private static final Log logger = LogFactory.getLog(CustomWebScriptServlet.class);
	public static String profile_language = "user_language";
	public static String userSessionok = "userSessionLanguageSets";

	private static final long serialVersionUID = -4026282755157639503L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Processing request (" + req.getMethod() + ") " + req.getRequestURL() + (req.getQueryString() != null ? "?" + req.getQueryString() : ""));
		}

		if (req.getCharacterEncoding() == null) {
			req.setCharacterEncoding("UTF-8");
		}
		// initialize the request context
		RequestContext context = null;
		try {
			context = FrameworkHelper.initRequestContext(req);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		LanguageSetter.setLanguageFromLayoutParam(req, context);

		WebScriptServletRuntime runtime = new WebScriptServletRuntime(container, authenticatorFactory, req, res, serverProperties);
		runtime.executeScript();
	}

	
}
