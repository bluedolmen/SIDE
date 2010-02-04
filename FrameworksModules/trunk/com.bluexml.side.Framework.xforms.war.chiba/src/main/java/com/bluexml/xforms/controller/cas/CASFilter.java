package com.bluexml.xforms.controller.cas;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * The Class CASFilter.<br>
 * Inherits from Yale CASFilter Allows configuration in property files, instead
 * of web.xml Allows disabling of filter from property
 */
public class CASFilter extends edu.yale.its.tp.cas.client.filter.CASFilter {

	/** The cas url. */
	private static String casUrl;

	/** The server name. */
	private static String serverName;

	/** The enabled. */
	private static boolean enabled = false;

	static {
		// Inits the properties
		Properties config = new Properties();
		try {
			config.load(CASFilter.class
					.getResourceAsStream("/forms.properties"));
			casUrl = config.getProperty("cas.url");
			serverName = config.getProperty("cas.serverName");
			enabled = Boolean.parseBoolean(config.getProperty("cas.enabled"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see edu.yale.its.tp.cas.client.filter.CASFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws ServletException, IOException {
		if (enabled) {
			super.doFilter(arg0, arg1, arg2);
		} else {
			arg2.doFilter(arg0, arg1);
		}
	}

	/* (non-Javadoc)
	 * @see edu.yale.its.tp.cas.client.filter.CASFilter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Use a custom filter config
		FilterConfig customFilterConfig = new CASFilterConfig(filterConfig);
		super.init(customFilterConfig);
	}

	/**
	 * @return the casUrl
	 */
	public static String getCasUrl() {
		return casUrl;
	}

	/**
	 * The Class CASFilterConfig. Overrides filter config
	 */
	protected class CASFilterConfig implements FilterConfig {

		/** The owned filter config. */
		private FilterConfig ownedFilterConfig;

		/**
		 * Instantiates a new cAS filter config.
		 * 
		 * @param onwedFilterConfig
		 *            the onwed filter config
		 */
		public CASFilterConfig(FilterConfig onwedFilterConfig) {
			super();
			this.ownedFilterConfig = onwedFilterConfig;
		}

		/* (non-Javadoc)
		 * @see javax.servlet.FilterConfig#getFilterName()
		 */
		public String getFilterName() {
			return ownedFilterConfig.getFilterName();
		}

		/* (non-Javadoc)
		 * @see javax.servlet.FilterConfig#getInitParameter(java.lang.String)
		 */
		public String getInitParameter(String parameterName) {
			if (parameterName.equals(CASFilter.LOGIN_INIT_PARAM)) {
				return getCasUrl() + "/login";
			}
			if (parameterName.equals(CASFilter.VALIDATE_INIT_PARAM)) {
				return getCasUrl() + "/serviceValidate";
			}
			if (parameterName.equals(CASFilter.SERVERNAME_INIT_PARAM)) {
				return getServerName();
			}
			return ownedFilterConfig.getInitParameter(parameterName);
		}

		/* (non-Javadoc)
		 * @see javax.servlet.FilterConfig#getInitParameterNames()
		 */
		public Enumeration<?> getInitParameterNames() {
			return ownedFilterConfig.getInitParameterNames();
		}

		/* (non-Javadoc)
		 * @see javax.servlet.FilterConfig#getServletContext()
		 */
		public ServletContext getServletContext() {
			return ownedFilterConfig.getServletContext();
		}

	}

	/**
	 * @return the serverName
	 */
	public static String getServerName() {
		return serverName;
	}

}
