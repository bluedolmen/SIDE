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
