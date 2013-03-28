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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.extensions.surf.RequestContext;
import org.springframework.extensions.surf.RequestContextUtil;
import org.springframework.extensions.surf.util.I18NUtil;
import org.springframework.web.servlet.LocaleResolver;

public class MyLocalResolver implements LocaleResolver, ApplicationContextAware {
	private static final Log logger = LogFactory.getLog(MyLocalResolver.class);
	ApplicationContext applicationContext;

	static {
		logger.debug("MyLocalResolver Class Loaded");
		logger.debug("MyLocalResolver default locale is :" + Locale.getDefault());
	}

	public Locale resolveLocale(HttpServletRequest request) {
		HttpSession session = request.getSession();
		logger.debug("[resolveLocale] ...");
		logger.debug("[resolveLocale] session Id :" + session.getId());
		Locale l = null;

		RequestContext initRequestContext;
		try {
			initRequestContext = RequestContextUtil.initRequestContext(applicationContext, request);
			String languageFromLayoutParam = LanguageSetter.getLanguageFromLayoutParam(request, initRequestContext);
			l = new Locale(languageFromLayoutParam);
		} catch (Exception e) {
			logger.error("oups " + e.getMessage(), e);
			e.printStackTrace();
		}
		logger.debug("[resolveLocale] resolveLocale = " + l);
		if (l == null) {
			l = Locale.getDefault();
			logger.warn("[resolveLocale] back to default (local is null)= " + l);
		}

		setLocale(request, null, l);

		return l;
	}

	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		logger.debug("setLocale " + locale.getLanguage());
		request.getSession().setAttribute(LanguageSetter.SHARE_LANG, locale.getLanguage());
		I18NUtil.setLocale(locale);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

}
