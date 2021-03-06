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
package com.bluexml.side.framework.alfresco.shareLanguagePicker;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.config.ConfigService;
import org.springframework.extensions.webscripts.RuntimeContainer;
import org.springframework.extensions.webscripts.servlet.ServletAuthenticatorFactory;
import org.springframework.extensions.webscripts.servlet.mvc.WebScriptView;
import org.springframework.extensions.webscripts.servlet.mvc.WebScriptViewRuntime;
import org.springframework.web.servlet.LocaleResolver;

public class CustomWebScriptView extends WebScriptView {
	private static final Log logger = LogFactory.getLog(CustomWebScriptView.class);
	LocaleResolver localeResolver;

	public CustomWebScriptView(RuntimeContainer container, ServletAuthenticatorFactory authenticatorFactory, ConfigService configService, LocaleResolver localeResolver) {
		super(container, authenticatorFactory, configService);
		logger.debug("CustomWebScriptView.CustomWebScriptView()");
		this.localeResolver = localeResolver;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
	 * (java.util.Map, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Expose the model object as request attributes.
		exposeModelAsRequestAttributes(model, request);

		if (logger.isDebugEnabled())
			logger.debug("Processing request (" + request.getMethod() + ") " + request.getRequestURL() + (request.getQueryString() != null ? "?" + request.getQueryString() : ""));

		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		Locale resolveLocale = localeResolver.resolveLocale(request);
		localeResolver.setLocale(request, response, resolveLocale);

		// hand off to the WebScript Servlet View runtime
		WebScriptViewRuntime runtime = new WebScriptViewRuntime(getUrl(), container, authenticatorFactory, request, response, serverProperties);
		runtime.executeScript();
	}

}
