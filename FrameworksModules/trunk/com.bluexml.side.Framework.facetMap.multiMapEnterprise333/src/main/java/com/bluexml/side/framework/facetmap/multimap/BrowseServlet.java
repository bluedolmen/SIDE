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
