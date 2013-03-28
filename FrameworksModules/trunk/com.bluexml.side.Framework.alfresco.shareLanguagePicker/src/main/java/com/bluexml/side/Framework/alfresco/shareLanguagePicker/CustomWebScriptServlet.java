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
