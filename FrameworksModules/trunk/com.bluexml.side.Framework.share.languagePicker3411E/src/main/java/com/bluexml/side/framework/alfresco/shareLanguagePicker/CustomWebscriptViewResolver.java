package com.bluexml.side.framework.alfresco.shareLanguagePicker;

import org.springframework.extensions.webscripts.Match;
import org.springframework.extensions.webscripts.servlet.mvc.WebScriptViewResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class CustomWebscriptViewResolver extends WebScriptViewResolver {

	LocaleResolver localeResolver;

	public LocaleResolver getLocaleResolver() {
		return localeResolver;
	}

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.servlet.view.UrlBasedViewResolver#buildView(java
	 * .lang.String)
	 */
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		AbstractUrlBasedView view = null;

		String uri = viewName;

		// path corrections
		if (uri != null) {
			if (uri.length() != 0 && uri.charAt(0) != '/') {
				uri = '/' + uri;
			}
		}

		// check the web script registry to see if a web script with this URI exists
		Match match = container.getRegistry().findWebScript("get", uri);
		if (match != null) {
			logger.debug("create instance of CustomWebScriptView");
			view = new CustomWebScriptView(container, authenticatorFactory, configService, getLocaleResolver());
			view.setUrl(uri);
		}

		return view;
	}
}
