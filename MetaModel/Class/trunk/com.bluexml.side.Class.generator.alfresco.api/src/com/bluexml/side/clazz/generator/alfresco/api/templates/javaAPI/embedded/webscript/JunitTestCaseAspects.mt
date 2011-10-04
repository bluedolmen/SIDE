<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.webscript.JunitLib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
%>
<%script type="clazz.Aspect" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSourceTest")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/test/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%getJavaTestName()%>.java
<%script type="clazz.Aspect" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getJavaTestPackage%>;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.alfresco.repo.template.TemplateNode;

import <%getJavaAPIPackage()%>.*;
import <%getJavaAPIPackage()%>.script.*;
import <%getJavaModelObjectPackage()%>.*;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class <%getJavaTestName()%> extends TestCase {

	private static final String propertiesFileName = "test.properties";
	private WebConversation wc;
	
	static String createAction = "?action=create";
	
	static String deleteAction = "?action=delete";
	
	public String getCreateURL(String type, String qstring) {
		return servicesURL + "/" + type + createAction + qstring;
	}
	
	public String getDeleteURL(String nodeRef, String type) {
		return servicesURL + "/" + type + deleteAction + "&nodeRef=" + nodeRef;
	}
	
	static String addAction = "?action=add";
	
	static String updateAction = "?action=update";
	
	static String removeAction = "?action=remove";
	
	
	public String getAddURL(String nodeRef, String type, String qstring) {
		return servicesURL + "/" + type + addAction + "&nodeRef=" + nodeRef + qstring;
	}
	
	public String getUpdateURL(String nodeRef, String type, String qstring) {
		return servicesURL + "/" + type + updateAction + "&nodeRef=" + nodeRef + qstring;
	}
	
	public String getRemoveURL(String nodeRef, String type) {
		return servicesURL + "/" + type + removeAction + "&nodeRef=" + nodeRef;
	}
	
	String servicesURL="";

	List<String> created = new ArrayList<String>();
	
	/**
	 * @param name
	 */
	public <%getJavaTestName()%>(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// get properties
		String login="admin";
		String password="admin";
		servicesURL = "http://localhost:8080/alfresco/service/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api";
		Properties props = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);
		try {
			props.load(is);
			login = props.getProperty("login");
			password = props.getProperty("password");
			servicesURL = props.getProperty("host") + "/alfresco/service/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api";
		} catch (Exception e) {
			// Do nothing, just use the default alfresco connection
			System.out.println("Unable to load webservice client properties from " + propertiesFileName + ": " + e.getMessage());
			System.out.println("default values used");
		}
		// open connection to Alfresco
		wc = new WebConversation();
		wc.setAuthentication("Alfresco", login, password);

				
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}

	public void testAddAspectTo() throws Exception {
		// create <%getFistClass().getJavaAPIName()%> <%getFistClass().name.toLowerCase()%> instance where to add Aspect
		
		<%getFistClass().generateCreateStatementWebscript(getFistClass().name.toL1Case(),"")%>
		
		<%generatePropertiesStatement(1)%>
		String qstring2="";
		<%getPropertiesQueryString("1","qstring2")%>
		
		String json2 = getResponse(getAddURL(<%getFistClass().name.toL1Case()%>.getUuid(), "<%getJavaModelObjectName()%>", qstring2));
		JSONObject jsa2 =(JSONObject) JSONSerializer.toJSON(json2);
		<%getJavaModelObjectName%> <%name.toL1Case()%>Obj = <%javaWebScriptFactoryAdapterName()%>.load<%getJavaModelObjectName()%>(jsa2);
		
		// assert
		<%current("AbstractClass").generatePropertiesAssertion("1", name.toL1Case()+"Obj")%>
	}

	public void testUpdate() throws Exception {
		<%getFistClass().generateCreateStatementWebscript(getFistClass().name.toL1Case(),"")%>
		
		<%generatePropertiesStatement(1)%>
		String qstring2="";
		<%getPropertiesQueryString("1","qstring2")%>
		
		String json2 = getResponse(getAddURL(<%getFistClass().name.toL1Case()%>.getUuid(), "<%getJavaModelObjectName()%>", qstring2));
		JSONObject jsa2 =(JSONObject) JSONSerializer.toJSON(json2);
		
		
		// <%getJavaModelObjectName%> <%name.toL1Case()%> = <%javaWebScriptFactoryAdapterName()%>.load<%getJavaModelObjectName()%>(jsa2);
		
		// update
		<%generatePropertiesStatement(2)%>
		String qstring3="";
		<%getPropertiesQueryString("2","qstring3")%>
		
		String json3 = getResponse(getUpdateURL(<%getFistClass().name.toL1Case()%>.getUuid(), "<%getJavaModelObjectName()%>", qstring3));
		JSONObject jsa3 =(JSONObject) JSONSerializer.toJSON(json3);
		<%getJavaModelObjectName%> updated = <%javaWebScriptFactoryAdapterName()%>.load<%getJavaModelObjectName()%>(jsa3);
		
		// assert
		
		<%current("AbstractClass").generatePropertiesAssertion("2","updated")%>
	}

	public void testRemoveAspect() throws Exception {
		<%getFistClass().generateCreateStatementWebscript(getFistClass().name.toL1Case(),"")%>
		
		<%generatePropertiesStatement(1)%>
		String qstring2="";
		<%getPropertiesQueryString("1","qstring2")%>
		
		String json2 = getResponse(getAddURL(<%getFistClass().name.toL1Case()%>.getUuid(), "<%getJavaModelObjectName()%>", qstring2));
		JSONObject jsa2 =(JSONObject) JSONSerializer.toJSON(json2);
		<%getJavaModelObjectName%> <%name.toL1Case()%>Obj = <%javaWebScriptFactoryAdapterName()%>.load<%getJavaModelObjectName()%>(jsa2);
		
		String json3 = getResponse(getRemoveURL(<%getFistClass().name.toL1Case()%>.getUuid(), "<%getJavaModelObjectName()%>"));
		JSONObject jsa3 =(JSONObject) JSONSerializer.toJSON(json3);
		
	}
	
	private String getResponse(String url) throws Exception {
		WebRequest req = new GetMethodWebRequest(url);
		WebResponse resp = wc.getResponse(req);

		String ct = resp.getContentType();

		System.out.println(ct);
		String json = resp.getText();
		System.out.println(json);

		if (resp.getResponseCode() == 200 && ct.equals("application/json")) {
			System.out.println("response ok can load json object");
			return json;
		}
		return null;
	}
}
