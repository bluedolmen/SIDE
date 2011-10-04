package com.bluexml.side.framework.facetmap.multimap;
 
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facetmap.Map;
import com.facetmap.servlet.BrowseXmlServlet;
import com.facetmap.servlet.ServletUtil;
import com.facetmap.simple.XmlGenerator;

public class BrowseServlet extends BrowseXmlServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5723990888120722212L;

	public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws IOException {
		System.out.println("GET ------>");
		XmlGenerator xmlgenerator = new XmlGenerator();
		httpservletresponse.setContentType("text/xml");
		try {
			FacetMapAlfrescoServlet fms = (FacetMapAlfrescoServlet)getServletContext().getAttribute("com.facetmap.servlet");
			
			Map map = fms.getFacetInstance(httpservletrequest).getFacet();
			com.facetmap.Selection selection = ServletUtil.processRequest(httpservletrequest, map);
			xmlgenerator.outputSelection(selection, httpservletresponse.getWriter());
		} catch (Exception dataexception) {
			xmlgenerator.outputException(dataexception, httpservletresponse.getWriter());
		}
	}
}
