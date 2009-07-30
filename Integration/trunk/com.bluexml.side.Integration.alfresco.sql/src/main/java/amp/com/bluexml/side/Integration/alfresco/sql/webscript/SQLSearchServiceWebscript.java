package com.bluexml.side.Integration.alfresco.sql.webscript;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.web.scripts.AbstractWebScript;
import org.alfresco.web.scripts.WebScriptRequest;
import org.alfresco.web.scripts.WebScriptResponse;

import com.bluexml.side.Integration.alfresco.sql.searcher.SQLSearchService;

public class SQLSearchServiceWebscript extends AbstractWebScript {
	
	public void init() {
		// TODO : logger init
	}
		
	public void execute(WebScriptRequest webscriptrequest, WebScriptResponse webscriptresponse)
			throws IOException {
		String result = "";
				
		String searchedType = webscriptrequest.getParameter("type");
		
		Collection<NodeRef> resultCollection = sqlSearchService.selectNodes(searchedType, "true");
		result = formatResult_(resultCollection);
		
		// TODO : Return status as an xml stream?!
		OutputStream outputStream = webscriptresponse.getOutputStream();
		webscriptresponse.setContentType(WebScriptResponse.XML_FORMAT);
		
		outputStream.write(result.getBytes());
	}

	String formatResult_(Collection<NodeRef> collection) {
		StringBuilder result = new StringBuilder();
		
		result.append("<nodes>");
		for (NodeRef nodeRef : collection) {
			result.append("<node>" + nodeRef + "</node>");
		}
		result.append("</nodes>");
		
		return result.toString();
	}
	
	// BEAN MANAGEMENT
	
	private SQLSearchService sqlSearchService;
		
	public SQLSearchService getSQLSearchService() {
		return sqlSearchService;
	}
	
	public void setSQLSearchService(SQLSearchService sqlSearchService) {
		this.sqlSearchService = sqlSearchService;
	}
}
