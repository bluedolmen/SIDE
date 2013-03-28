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
package com.bluexml.side.Integration.alfresco.sql.webscript;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.web.scripts.Cache;
import org.alfresco.web.scripts.DeclarativeWebScript;
import org.alfresco.web.scripts.Status;
import org.alfresco.web.scripts.WebScriptException;
import org.alfresco.web.scripts.WebScriptRequest;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.searcher.SQLSearchService;

public class SelectNodesWebscript extends DeclarativeWebScript {
	
	Logger logger = Logger.getLogger(getClass());
	
	private static final String PARAM_ID_OBJECT_TYPE = "type";
	private static final String PARAM_ID_CONSTRAINT = "where";
	private static final String TEMPLATE_PARAM_ID_NODES = "nodes";
	

    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
    	Map<String, Object> model = new HashMap<String, Object>();
    	
		String searchedType = req.getParameter(PARAM_ID_OBJECT_TYPE);
		String constraint = req.getParameter(PARAM_ID_CONSTRAINT);
		
		if (searchedType == null || "".equals(searchedType)) {
			throw new WebScriptException(HttpServletResponse.SC_BAD_REQUEST, "Search type not provided");
			// TODO : check whether this type is a valid type (and thus that the table exists)
//			logger.error("searched type have to be defined");
//			status.setCode(400, "type is a required parameter");
//			status.setRedirect(true);
		}
		
		if (constraint == null || "".equals(constraint)) {
			constraint = "true";
		}
		
		Collection<NodeRef> resultCollection = sqlSearchService.selectNodes(searchedType, constraint);
		model.put(TEMPLATE_PARAM_ID_NODES, resultCollection);
		
		return model;
    }
	
	// BEAN MANAGEMENT
	
	private SQLSearchService sqlSearchService;
	
	public void setSQLSearchService(SQLSearchService sqlSearchService) {
		this.sqlSearchService = sqlSearchService;
	}
}
