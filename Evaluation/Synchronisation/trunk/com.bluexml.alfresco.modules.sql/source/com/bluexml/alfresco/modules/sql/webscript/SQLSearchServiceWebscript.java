package com.bluexml.alfresco.modules.sql.webscript;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.web.scripts.AbstractWebScript;
import org.alfresco.web.scripts.WebScriptRequest;
import org.alfresco.web.scripts.WebScriptResponse;

import com.bluexml.alfresco.modules.sql.searcher.SQLSearchService;

public class SQLSearchServiceWebscript extends AbstractWebScript {
	
	
	
	public void init() {
		// TODO : logger init
	}
		
	public void execute(WebScriptRequest webscriptrequest, WebScriptResponse webscriptresponse)
			throws IOException {
		String result = "";
		
		webscriptresponse.setContentType("text/xml");
		
		String searchedType = webscriptrequest.getParameter("type");
		
		//SQLSearch sqlSearch = new SQLSearch();
		//ResultSet resultSet = sqlSearch.query(searchedType);
		Collection<NodeRef> resultCollection = sqlSearchService.query(searchedType);
		result = formatResult_(resultCollection);
		
		// TODO : Return status as an xml stream?!
		OutputStream outputStream = webscriptresponse.getOutputStream();
		outputStream.write(result.getBytes());
	}

	String formatResult_(Collection<NodeRef> collection) {
		StringBuilder result = new StringBuilder();
		
		result.append("<nodes>");
		for (NodeRef nodeRef : collection) {
			result.append("<node>" + nodeRef.getId() + "</node>");
		}
		result.append("</nodes>");
		
		return result.toString();
	}
	
	// BEAN MANAGEMENT
	
	private ServiceRegistry serviceRegistry;
	private SQLSearchService sqlSearchService;
	
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
	
	public SQLSearchService getSQLSearchService() {
		return sqlSearchService;
	}
	
	public void setSQLSearchService(SQLSearchService sqlSearchService) {
		this.sqlSearchService = sqlSearchService;
	}
}
