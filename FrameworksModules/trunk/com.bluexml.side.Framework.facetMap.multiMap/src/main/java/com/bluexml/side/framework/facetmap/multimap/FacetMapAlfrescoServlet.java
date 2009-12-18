package com.bluexml.side.framework.facetmap.multimap;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.facetmap.DataException;
import com.facetmap.InternalException;
import com.facetmap.Map;
import com.facetmap.simple.logging.Log4jService;
import com.facetmap.simple.logging.LogService;
import com.facetmap.simple.logging.LogUtil;

public class FacetMapAlfrescoServlet extends com.facetmap.simple.SimpleFacetmapServlet {
	private static final long serialVersionUID = -8382975408002655812L;

	protected final Properties configProps = null; // do not use

	private FacetMapInstanceManager facetMapInstanceManager = null;
	private Logger logger = Logger.getLogger(getClass());

	

	public FacetMapAlfrescoServlet() throws Exception {
		super();
		logger.debug("FacetMapAlfrescoServlet instanciated");
		logger.debug("FacetMapAlfrescoServlet before");
		facetMapInstanceManager = new FacetMapInstanceManager();
		logger.debug("FacetMapAlfrescoServlet instanciated after");
	}

	@Override
	public Map createNewMap(ServletContext arg0) throws InternalException, DataException, IOException {
		logger.debug("createNewMap called");
		Map localMap = super.createNewMap(arg0);

		return localMap;

	}

	public void init() throws ServletException {
		logger.debug("My init");
		try {
			super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("My init after super.init");
	}

	public void init(ServletConfig ctx) {
		try {
			logger.debug("My init conf");
			super.init(ctx);
			logger.debug("My init conf after");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void configure() throws IOException, DataException, InternalException {
		ServletContext servletcontext = getServletContext();

		workDir = new File(servletcontext.getRealPath("."));
		LogUtil.setService(createLogService(servletcontext));
		if (LogUtil.isLoggingInfo()) {
			LogUtil.logInfo("Initializing FacetMap application...");
		}

		configured = true;
	}

	public FacetMapInstance getFacetInstance(HttpServletRequest req) throws Exception {
		String facetName = getFacetName(req);
		String community = getCommunity(req);
		return facetMapInstanceManager.getFacetInstance(facetName, community);
	}

	private String getCommunity(HttpServletRequest req) {
		String community = (String) req.getParameter("community");
		return community;
	}

	private String getFacetName(HttpServletRequest req) {
		String facetName = (String) req.getParameter("facetName");
		return facetName;
	}

	public void updateFacets() throws Exception {
		facetMapInstanceManager.update();
	}

	public LogService createLogService(ServletContext servletcontext) throws IOException {
		String s = "./classes/log4j.properties";
		return new Log4jService(getUrlForDocs(s).getPath());
	}

	public int getIntParameter(String key, HttpServletRequest req) throws Exception {
		return Integer.parseInt(getParameter(key, req));
	}

	public String getParameter(String key, HttpServletRequest req) throws Exception {
		return getFacetInstance(req).getProps().getProperty(key);
	}

	public boolean getBooleanParameter(String key, HttpServletRequest req) throws Exception {
		return Boolean.parseBoolean(getParameter(key, req));

	}

	public void getParameter(String key, HttpServletRequest req, String value) throws Exception {
		getFacetInstance(req).getProps().setProperty(key, value);
	}

	public Enumeration<Object> getParameterNames(HttpServletRequest req) throws Exception {
		return getFacetInstance(req).getProps().keys();

	}

	public void setParameter(String key, String value, HttpServletRequest req) throws Exception {
		getFacetInstance(req).getProps().setProperty(key, value);
	}

	public void writeProps(String comments, HttpServletRequest req) throws Exception {
		facetMapInstanceManager.save(getFacetName(req), comments);
	}
	
	/**
	 * Lock properties access using configProps
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.facetmap.servlet.FacetMapServlet#getIntParameter(java.lang.String)
	 */
	@Override
	public int getIntParameter(String s) throws DataException {
		throw new DataException("getIntParameter Not impleemnted use getIntParameter(String s,String facetName)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.facetmap.servlet.FacetMapServlet#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String s) {
		throw new RuntimeException("getParameter Not Implemented, use instead getParameter(String s,String facetName)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.facetmap.servlet.FacetMapServlet#getBooleanParameter(java.lang.String
	 * )
	 */
	@Override
	public boolean getBooleanParameter(String s) {
		throw new RuntimeException("getBooleanParameter Not Implemented, use instead getParameter(String s,String facetName)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.facetmap.servlet.FacetMapServlet#readProps()
	 */
	@Override
	public void readProps() throws IOException {
		// throw new
		// RuntimeException("readProps Not Implemented, use instead getParameter(String s,String facetName)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.facetmap.servlet.FacetMapServlet#writeProps(java.lang.String)
	 */
	@Override
	public void writeProps(String s) throws IOException {
		throw new RuntimeException("writeProps Not Implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.facetmap.servlet.FacetMapServlet#getParameterNames()
	 */
	@Override
	public Enumeration getParameterNames() {
		throw new RuntimeException("getParameterNames Not Implemented, use instead getParameter(String s,String facetName)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.facetmap.servlet.FacetMapServlet#setParameter(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setParameter(String s, String s1) {
		throw new RuntimeException("setParameter Not Implemented, use instead getParameter(String s,String facetName)");
	}

	/**
	 * @return the facetMapInstanceManager
	 */
	public FacetMapInstanceManager getFacetMapInstanceManager() {
		return facetMapInstanceManager;
	}

}
