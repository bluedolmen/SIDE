package com.bluexml.side.Framework.alfresco.shareLanguagePicker;

import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alfresco.connector.Connector;
import org.alfresco.connector.Response;
import org.alfresco.connector.User;
import org.alfresco.connector.exception.ConnectorServiceException;
import org.alfresco.i18n.I18NUtil;
import org.alfresco.util.URLEncoder;
import org.alfresco.web.scripts.Status;
import org.alfresco.web.site.FrameworkHelper;
import org.alfresco.web.site.RequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

public class LanguageSetter {
	private static final Log logger = LogFactory.getLog(LanguageSetter.class);
	static String preferences_values = "{http://www.alfresco.org/model/content/1.0}preferenceValues";
	public static String profile_language = "user_language";
	public static String userSessionok = "userSessionLanguageSets";

	public static void setLanguageFromLayoutParam(HttpServletRequest req, RequestContext context) throws ServletException {
		logger.debug("# Set Language ...");
		User user = context.getUser();
		String userId = context.getUserId();
		String urlLang = req.getParameter("shareLang");
		HttpSession currentSession = req.getSession();
		String sessionLang = (String) currentSession.getAttribute("shareLang");
		if (logger.isTraceEnabled()) {
			logger.trace("urlLang : " + urlLang);
			logger.trace("sessionLang : " + sessionLang);
		}

		// set language
		
		String language = null;
		// is no user or guest use browser language
		if (user != null && !userId.toLowerCase().equals("guest")) {
			if (urlLang != null) {
				language = urlLang;
				if (logger.isDebugEnabled()) {
					logger.debug("Use language in url :" + urlLang);
				}
			} else if (sessionLang != null && currentSession.getAttribute(userSessionok) != null) {
				// only if language already loaded from preference
				if (logger.isDebugEnabled()) {
					logger.debug("Use language stored in session :" + sessionLang);
				}
				language = sessionLang;

			} else {
				// initialize user language
				try {
					language = getUserLanguage(userId, context);
					logger.debug("Use language from user preferences :" + language);

				} catch (ConnectorServiceException e) {
					throw new ServletException(e);
				} catch (JSONException e) {
					throw new ServletException(e);
				}
				if (language == null) {
					// language not yet set so sue default from browser
					language = getLanguageFromBrowser(req);
					logger.debug("Use language from browser :" + language);
				}
				currentSession.setAttribute(userSessionok, true);
			}
		} else {
			// language not yet set so sue default from browser
			language = getLanguageFromBrowser(req);
			logger.debug("no user or Guest, use language from browser :" + language);
		}

		if (language != null) {
			// update language
			I18NUtil.setLocale(I18NUtil.parseLocale(language));
			currentSession.setAttribute("shareLang", language);
			logger.debug("# Language updated to :" + I18NUtil.getLocale());
		} else {
			// bad news no language founded
			logger.warn("Language can't not be updated (no value found anywhere)");
		}
		
	}

	private static String getLanguageFromBrowser(HttpServletRequest req) {
		// set language locale from browser header
		String acceptLang = req.getHeader("Accept-Language");
		String language = null;
		if (acceptLang != null && acceptLang.length() != 0) {
			StringTokenizer t = new StringTokenizer(acceptLang, ",; ");
			// get language and convert to java locale format
			language = t.nextToken().replace('-', '_');
		}
		return language;
	}

	protected static String getUserLanguage(String currentUserId, RequestContext context) throws ConnectorServiceException, ServletException, JSONException {
		// get a connector whose connector session is bound to the current session
		Connector connector = FrameworkHelper.getConnector(context.getRequest().getSession(), currentUserId, "alfresco");

		// build the REST URL to retrieve user preferences
		String uri = "/api/people/" + URLEncoder.encode(currentUserId) + "/preferences?pf=" + profile_language;

		// invoke and check for OK response
		Response response = connector.call(uri);
		if (Status.STATUS_OK != response.getStatus().getCode()) {
			throw new ServletException("failed to retrieve user preferences: " + response.getStatus().getMessage(), (Exception) response.getStatus().getException());
		}

		// Load the user properties via the JSON parser
		String responseString = response.getResponse();
		JSONObject root = new JSONObject(responseString);
		String language = null;
		if (!root.isNull(profile_language)) {
			language = root.getString(profile_language);
		}

		return language;
	}
}
