package com.bluexml.side.framework.alfresco.shareLanguagePicker.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyHttpRequestServletAdaptator implements HttpServletRequest {
	private static final Log logger = LogFactory.getLog(MyHttpRequestServletAdaptator.class);
	protected final HttpServletRequest request;

	Headers headers = new Headers();

	class Headers {
		// we use list because some header name can be used more than one time example: Accept-language
		private final List<Object[]> headers = new ArrayList<Object[]>();

		public void remove(String key) {
			List<Object[]> toremove = get(key);
			for (Object[] objects : toremove) {
				logger.debug("remove from header " + objects[0] + ":" + objects[1]);
			}
			headers.removeAll(toremove);
		}

		public List<Object[]> get(String key) {
			List<Object[]> entries = new ArrayList<Object[]>();
			for (Object[] entry : headers) {
				if (key.toLowerCase().equals(entry[0])) {
					entries.add(entry);
				}
			}
			return entries;
		}

		public List<Object> getValues(String key) {
			List<Object[]> entries = get(key);
			List<Object> list = new ArrayList<Object>();
			for (Object[] objects : entries) {
				list.add(objects[1]);
			}
			return list;
		}

		public void add(String key, Object value) {
			logger.debug("add to header " + key + ":" + value);
			headers.add(new Object[] { key.toLowerCase(), value });
		}

		public List<String> getKeys() {
			Set<String> entries = new HashSet<String>();
			for (Object[] entry : headers) {
				entries.add((String) entry[0]);
			}
			return new ArrayList<String>(entries);
		}

		/**
		 * this add or replace all occurrence of entries with the given key
		 * 
		 * @param key
		 * @param value
		 */
		public void set(String key, Object value) {
			remove(key);
			add(key, value);

		}
	}

	public MyHttpRequestServletAdaptator(HttpServletRequest request) {
		this.request = request;
		Enumeration<?> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			headers.add(key, value);
		}
	}

	/**
	 * special method to set request header
	 * this allow to redefine request header before connector read it to build
	 * the proxied request
	 */

	public void setHeader(String key, String value) {
		headers.set(key, value);
	}

	/*
	 * implements HttpServletRequest interface
	 */

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getAuthType()
	 */
	public String getAuthType() {
		return request.getAuthType();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getCookies()
	 */
	public Cookie[] getCookies() {
		return request.getCookies();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
	 */
	public long getDateHeader(String name) {
		List<Object> values = headers.getValues(name);
		if (values.size() > 0) {
			Object object = values.get(0);
			if (object instanceof Date) {
				return ((Date) object).getTime();
			} else if (object instanceof String) {
				return new Date(Long.parseLong((String) object)).getTime();
			} else if (object instanceof Long) {
				return new Date((Long) object).getTime();
			}
		}
		return -1;
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	public Enumeration<?> getAttributeNames() {
		return request.getAttributeNames();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
	 */
	public String getHeader(String name) {
		List<Object> values = headers.getValues(name);
		if (values.size() == 0) {
			return null;
		}
		return (String) values.get(0);
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getContentLength()
	 */
	public int getContentLength() {
		return request.getContentLength();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
	 */
	public Enumeration<?> getHeaders(String name) {
		return Collections.enumeration(headers.getValues(name));
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getContentType()
	 */
	public String getContentType() {
		return request.getContentType();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see javax.servlet.ServletRequest#getInputStream()
	 */
	public ServletInputStream getInputStream() throws IOException {
		return request.getInputStream();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
	 */
	public Enumeration<?> getHeaderNames() {
		return Collections.enumeration(headers.getKeys());
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
	 */
	public String getParameter(String name) {
		return request.getParameter(name);
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
	 */
	public int getIntHeader(String name) {
		List<Object> values = headers.getValues(name);
		if (values.size() > 0) {
			Object object = values.get(0);
			if (object instanceof Integer) {
				return (Integer)object;
			} else if (object instanceof String) {
				return Integer.parseInt((String) object);
			}
		}
		return -1;
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getMethod()
	 */
	public String getMethod() {
		return request.getMethod();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getContextPath()
	 */
	public String getContextPath() {
		return request.getContextPath();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getLocale()
	 */
	public Locale getLocale() {
		return request.getLocale();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getLocales()
	 */
	public Enumeration<?> getLocales() {
		return request.getLocales();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getParameterMap()
	 */
	public Map<?, ?> getParameterMap() {
		return request.getParameterMap();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getParameterNames()
	 */
	public Enumeration<?> getParameterNames() {
		return request.getParameterNames();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getPathInfo()
	 */
	public String getPathInfo() {
		return request.getPathInfo();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
	 */
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getProtocol()
	 */
	public String getProtocol() {
		return request.getProtocol();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getPathTranslated()
	 */
	public String getPathTranslated() {
		return request.getPathTranslated();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getQueryString()
	 */
	public String getQueryString() {
		return request.getQueryString();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see javax.servlet.ServletRequest#getReader()
	 */
	public BufferedReader getReader() throws IOException {
		return request.getReader();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getRemoteUser()
	 */
	public String getRemoteUser() {
		return request.getRemoteUser();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getRemoteAddr()
	 */
	public String getRemoteAddr() {
		return request.getRemoteAddr();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getRemoteHost()
	 */
	public String getRemoteHost() {
		return request.getRemoteHost();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
	 */
	public String getRequestedSessionId() {
		return request.getRequestedSessionId();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getRequestURI()
	 */
	public String getRequestURI() {
		return request.getRequestURI();
	}

	/**
	 * @param path
	 * @return
	 * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
	 */
	public RequestDispatcher getRequestDispatcher(String path) {
		return request.getRequestDispatcher(path);
	}

	/**
	 * @param path
	 * @return
	 * @deprecated
	 * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
	 */
	public String getRealPath(String path) {
		return request.getRealPath(path);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getRequestURL()
	 */
	public StringBuffer getRequestURL() {
		return request.getRequestURL();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getScheme()
	 */
	public String getScheme() {
		return request.getScheme();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getServerName()
	 */
	public String getServerName() {
		return request.getServerName();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getServerPort()
	 */
	public int getServerPort() {
		return request.getServerPort();
	}

	/**
	 * @param role
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
	 */
	public boolean isUserInRole(String role) {
		return request.isUserInRole(role);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getUserPrincipal()
	 */
	public Principal getUserPrincipal() {
		return request.getUserPrincipal();
	}

	/**
	 * @param name
	 * @param o
	 * @see javax.servlet.ServletRequest#setAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	public void setAttribute(String name, Object o) {
		request.setAttribute(name, o);
	}

	/**
	 * @param name
	 * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String name) {
		request.removeAttribute(name);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getServletPath()
	 */
	public String getServletPath() {
		return request.getServletPath();
	}

	/**
	 * @param create
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
	 */
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#isSecure()
	 */
	public boolean isSecure() {
		return request.isSecure();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getSession()
	 */
	public HttpSession getSession() {
		return request.getSession();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
	 */
	public boolean isRequestedSessionIdValid() {
		return request.isRequestedSessionIdValid();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return request.isRequestedSessionIdFromCookie();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
	 */
	public boolean isRequestedSessionIdFromURL() {
		return request.isRequestedSessionIdFromURL();
	}

	/**
	 * @return
	 * @deprecated
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
	 */
	public boolean isRequestedSessionIdFromUrl() {
		return request.isRequestedSessionIdFromUrl();
	}

	/**
	 * @param arg0
	 * @throws UnsupportedEncodingException
	 * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
	 */
	public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
		request.setCharacterEncoding(arg0);
	}

}
