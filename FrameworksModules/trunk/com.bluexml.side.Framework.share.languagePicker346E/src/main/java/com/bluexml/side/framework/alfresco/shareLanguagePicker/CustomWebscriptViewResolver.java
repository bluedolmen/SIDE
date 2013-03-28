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
