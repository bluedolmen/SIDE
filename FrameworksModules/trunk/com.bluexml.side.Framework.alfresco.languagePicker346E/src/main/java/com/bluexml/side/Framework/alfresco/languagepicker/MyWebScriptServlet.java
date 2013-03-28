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
package com.bluexml.side.Framework.alfresco.languagepicker;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alfresco.service.ServiceRegistry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.extensions.surf.util.I18NUtil;
import org.springframework.extensions.webscripts.servlet.WebScriptServlet;
import org.springframework.extensions.webscripts.servlet.WebScriptServletRuntime;

/**
 * Servlet to use to let share endpoint to access to alfresco using alfresco/wcs (SSO)
 * the original behavior is to load locale from user session or from default
 * (local defined in the JVM)
 * So this servlet set locale in session with the locale read in http header, so
 * all data webscript are executed in the expected language (the one provided by
 * share)
 * 
 * To enable this you need to replace WebScriptServlet by this one in web.xml file
 * for all servlet that use webscripts.authenticator.webclient authenticator
 * @author davidabad
 */
public class MyWebScriptServlet extends WebScriptServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -446458081932063260L;

	private static final Log logger = LogFactory.getLog(MyWebScriptServlet.class);

	public static final String LOCALE = "locale"; // from org.alfresco.web.app.Application
	protected ServiceRegistry serviceRegistry;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.extensions.webscripts.servlet.WebScriptServlet#
	 * initServlet(org.springframework.context.ApplicationContext)
	 */
	@Override
	protected void initServlet(ApplicationContext context) {

		super.initServlet(context);

		// retrieve authenticator factory

		Object bean = context.getBean("ServiceRegistry");
		if (bean == null || !(bean instanceof ServiceRegistry)) {
			throw new RuntimeException("missing bean id : ServiceRegistry");
		}
		serviceRegistry = (ServiceRegistry) bean;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (logger.isDebugEnabled())
			logger.debug("Processing request (" + req.getMethod() + ") " + req.getRequestURL() + (req.getQueryString() != null ? "?" + req.getQueryString() : ""));

		if (req.getCharacterEncoding() == null) {
			req.setCharacterEncoding("UTF-8");
		}

		setUserLanguage(req);

		try {
			WebScriptServletRuntime runtime = new WebScriptServletRuntime(container, authenticatorFactory, req, res, serverProperties);
			runtime.executeScript();
		} finally {
			// clear threadlocal
			I18NUtil.setLocale(null);
		}
	}

	/**
	 * Apply Client and Repository language locale based on the
	 * 'Accept-Language' request header
	 */
	public static String getLanguageFromRequestHeader(HttpServletRequest req) {

		// set language locale from browser header
		String acceptLang = req.getHeader("Accept-Language");
		if (acceptLang != null && acceptLang.length() != 0) {
			StringTokenizer t = new StringTokenizer(acceptLang, ",; ");
			// get language and convert to java locale format
			String language = t.nextToken().replace('-', '_');
			return language;
		}
		return null;
	}

	protected void setUserLanguage(HttpServletRequest req) {

		String localeString = getLanguageFromRequestHeader(req);
		if (localeString != null) {
			// we need to set locale in session because of Application.getLanguage behavior
			HttpSession session = req.getSession();
			Locale locale = I18NUtil.parseLocale(localeString);
			Locale oldLocale = (Locale) session.getAttribute(LOCALE);
			logger.debug("replace current session locale :" + oldLocale + " by :" + locale);
			session.setAttribute(LOCALE, locale);
		}
	}

}
