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

public class SQLSearchServiceWebscript extends DeclarativeWebScript {
	
	Logger logger = Logger.getLogger(getClass());
	
	private static final String PARAM_ID_OBJECT_TYPE = "type";
	private static final String PARAM_ID_CONSTRAINT = "where";
	private static final String TEMPLATE_PARAM_ID_NODES = "nodes";
	
//	public void execute(WebScriptRequest webscriptrequest, WebScriptResponse webscriptresponse)
//			throws IOException {
//		String result = "";
//				
//		String searchedType = webscriptrequest.getParameter(PARAM_ID_OBJECT_TYPE);
//		String constraint = webscriptrequest.getParameter(PARAM_ID_CONSTRAINT);
//		
//		if (constraint == null || "".equals(constraint)) {
//			constraint = "true";
//		}
//		
//		Collection<NodeRef> resultCollection = sqlSearchService.selectNodes(searchedType, constraint);
//		result = formatResult_(resultCollection);
//		
//		//webscriptresponse.setContentType(WebScriptResponse.XML_FORMAT);
//		webscriptresponse.getWriter().write(result);
//
//	}

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
	
//	String formatResult_(Collection<NodeRef> collection) {
//		StringBuilder result = new StringBuilder();
//		
//		result.append("<nodes>");
//		for (NodeRef nodeRef : collection) {
//			result.append("<node>" + nodeRef + "</node>");
//		}
//		result.append("</nodes>");
//		
//		return result.toString();
//	}
	
	// BEAN MANAGEMENT
	
	private SQLSearchService sqlSearchService;
	
	public void setSQLSearchService(SQLSearchService sqlSearchService) {
		this.sqlSearchService = sqlSearchService;
	}
}
