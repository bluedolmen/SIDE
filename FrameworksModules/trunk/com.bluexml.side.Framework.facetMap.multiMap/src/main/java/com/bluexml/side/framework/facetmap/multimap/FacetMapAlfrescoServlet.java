package com.bluexml.side.framework.facetmap.multimap;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bluexml.side.framework.facetmap.alfrescoConnector.Helper;
import com.facetmap.DataException;
import com.facetmap.InternalException;
import com.facetmap.Map;
import com.facetmap.simple.SimpleFacetMapX;
import com.facetmap.simple.logging.LogUtil;

public class FacetMapAlfrescoServlet extends com.facetmap.simple.SimpleFacetmapServlet {
	private static final long serialVersionUID = -8382975408002655812L;
	protected java.util.Map<String, Map> availableXFacetMap = new HashMap<String, com.facetmap.Map>();

	FacetMapCacheManager fmcm = null;
	private Logger logger = Logger.getLogger(getClass());

	public FacetMapAlfrescoServlet() {
		super();
		try {
			fmcm = new FacetMapCacheManager();
			System.out.println("FacetMapAlfrescoServlet instanciated");
			System.out.println("FacetMapAlfrescoServlet instanciated 2");
			// init();
			System.out.println("FacetMapAlfrescoServlet instanciated 3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Map createNewMap(ServletContext arg0) throws InternalException, DataException, IOException {
		System.out.println("createNewMap called");
		Map localMap = super.createNewMap(arg0);
		try {
			System.out.println("createNewMap loaded :" + new Helper().serializeFMap(localMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return localMap;

	}

	private Map getFacetMap(String groupId) throws Exception {
		System.out.println("getFacetMap called");
		Map localMap = SimpleFacetMapX.createFromXml(fmcm.getFacetMap(groupId).openStream(), this.docsUrl, this.workDir);
		System.out.println("facetMap loaded :" + new Helper().serializeFMap(localMap));
		return localMap;
	}

	public void init() throws ServletException {
		System.out.println("My init");
		super.init();
		System.out.println("My init after super.init");
	}

	public void init(ServletConfig ctx) {
		try {
			System.out.println("My init conf");
			super.init(ctx);
			System.out.println("My init conf after");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void configure() throws IOException, DataException, InternalException {
		ServletContext servletcontext = getServletContext();
		String s = getParameter("workDirectory");
		if (s == null || s.equals("")) {
			throw new DataException("Couldn't find \"workDirectory\", a required parameter.");
		}
		workDir = new File(servletcontext.getRealPath(s));
		LogUtil.setService(createLogService(servletcontext));
		if (LogUtil.isLoggingInfo()) {
			LogUtil.logInfo("Initializing FacetMap application...");
		}
		Map map = createNewMap(servletcontext);
		if (getBooleanParameter("showEmptySelections")) {
			map.setShowEmptySelections(true);
		}
		String limit = getParameter("resultLimit");
		Collection<Map> maps = availableXFacetMap.values();
		// update limits for results
		for (Map map2 : maps) {
			((SimpleFacetMapX)map2).setResultLimit(Integer.parseInt(limit));
		}
		configured = true;
	}

	public com.facetmap.Map getMap(HttpServletRequest req, String mapId) throws Exception {
		com.facetmap.Map map = getMap_(mapId);
		if (map == null) {
			// map not created call update
			// Updater.update(req.getHeader("host"), mapId);
		}
		req.getSession().setAttribute("current_map", mapId);
		return map;
	}

	private com.facetmap.Map getMap_(String mapId) throws Exception {
		com.facetmap.Map map = null;
		if (availableXFacetMap.containsKey(mapId)) {
			map = availableXFacetMap.get(mapId);
		} else {
			map = getFacetMap(mapId);
			if (map == null) {
				throw new FacetMapNotAvailableException("Required FacetMap not builded, please to run update on setings page");
			}
			((SimpleFacetMapX)map).setResultLimit(getIntParameter("resultLimit"));
			availableXFacetMap.put(mapId, map);
		}

		return map;

	}

	public com.facetmap.Map getCurrentMap(HttpServletRequest req) throws Exception {
		String mapId = (String) req.getSession().getAttribute("current_map");
		return getMap_(mapId);
	}
}
