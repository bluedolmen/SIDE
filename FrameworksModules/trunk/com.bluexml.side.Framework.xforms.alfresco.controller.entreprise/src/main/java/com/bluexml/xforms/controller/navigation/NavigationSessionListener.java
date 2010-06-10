package com.bluexml.xforms.controller.navigation;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang.StringUtils;

/**
 * The listener interface for receiving navigationSession events. The class that is interested in
 * processing a navigationSession event implements this interface, and the object created with that
 * class is registered with a component using the component's
 * <code>addNavigationSessionListener<code> method. When
 * the navigationSession event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see NavigationSessionEvent
 */
public class NavigationSessionListener implements ServletContextListener, HttpSessionListener {

	/** The context. */
	private static ServletContext context = null;

	/** The navigations. */
	private static Map<String, Map<String, NavigationPath>> navigations = Collections
			.synchronizedMap(new TreeMap<String, Map<String, NavigationPath>>());

	/** The sessions. */
	private static Map<String, HttpSession> sessions = Collections
			.synchronizedMap(new TreeMap<String, HttpSession>());

	/** The servletURLs identified by sessionId. */
	protected static Map<String, String> servletURLs = Collections
			.synchronizedMap(new TreeMap<String, String>());

	/**
	 * Gets the navigation path from session.
	 * 
	 * @param sessionId
	 *            the session id
	 * 
	 * @return the navigation path from session
	 */
	private static Map<String, NavigationPath> getNavigationPathFromSession(String sessionId) {
		Map<String, NavigationPath> result = navigations.get(sessionId);
		if (result == null) {
			result = new TreeMap<String, NavigationPath>();
			navigations.put(sessionId, result);
		}
		return result;
	}

	/**
	 * Gets the navigation path.
	 * 
	 * @param sessionId
	 *            the session id
	 * @param pageId
	 *            the page id
	 * 
	 * @return the navigation path
	 */
	public static NavigationPath getNavigationPath(String sessionId, String pageId) {
		NavigationPath navigationPath = getNavigationPathFromSession(sessionId).get(pageId);
		if (navigationPath == null) {
			navigationPath = new NavigationPath();
			navigationPath.setSessionId(sessionId);
			navigationPath.setPageId(pageId);
			getNavigationPathFromSession(sessionId).put(pageId, navigationPath);
		}
		return navigationPath;
	}

	/**
	 * Gets the context.
	 * 
	 * @return the context
	 */
	public static ServletContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		context = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		sessions.put(httpsessionevent.getSession().getId(), httpsessionevent.getSession());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		String sessionId = httpsessionevent.getSession().getId();
		navigations.remove(sessionId);
		sessions.remove(sessionId);
		servletURLs.remove(sessionId);
	}

	/**
	 * Gets the page id.
	 * 
	 * @param sessionId
	 *            the session id
	 * 
	 * @return the page id
	 */
	public static String getPageId(String sessionId) {
		Map<String, NavigationPath> pages = getNavigationPathFromSession(sessionId);
		Integer i = pages.size();
		do {
			i++;
		} while (pages.get(i.toString()) != null);
		return i.toString();
	}

	/**
	 * Gets the session.
	 * 
	 * @param sessionId
	 *            the session id
	 * 
	 * @return the session
	 */
	public static HttpSession getSession(String sessionId) {
		return sessions.get(sessionId);
	}

	/**
	 * Get the URL associated with this session
	 * 
	 * @param sessionId
	 * @return the servletURL
	 */
	public static String getServletURL(String sessionId) {
		return servletURLs.get(sessionId);
	}

	/**
	 * Associates a url to a session.
	 * 
	 * @param sessionId
	 * @param UrlToSave
	 */
	public static boolean registerServletURL(String sessionId, String UrlToSave) {
		servletURLs.put(sessionId, UrlToSave);
		return (StringUtils.equals(servletURLs.get(sessionId), UrlToSave));
	}
}
