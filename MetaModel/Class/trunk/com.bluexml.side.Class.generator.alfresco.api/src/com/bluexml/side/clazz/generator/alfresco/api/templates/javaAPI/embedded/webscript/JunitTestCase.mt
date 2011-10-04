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
<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSourceTest")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/test/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%getJavaTestName()%>.java
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
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
	
	static String requestAction = "?action=request";
	
	static String updateAction = "?action=update";
	
	static String deleteAction = "?action=delete";
	
	
<%for (getAllSourceAssociationEnds()){%>
	static String createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%> = "/<%current("Clazz").getJavaModelObjectName()%>?action=createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>";
	static String removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%> = "/<%current("Clazz").getJavaModelObjectName()%>?action=removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>";
	static String getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%> ="/<%current("Clazz").getJavaModelObjectName()%>?action=getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>";
	static String getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%> ="/<%current("Clazz").getJavaModelObjectName()%>?action=getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>";
<%}%>
	
	public String getCreateURL(String type, String qstring) {
		return servicesURL + "/" + type + createAction + qstring;
	}

	public String getRequestURL(String type, String qstring) {
		return servicesURL + "/" + type + requestAction + qstring;
	}

	public String getUpdateURL(String nodeRef, String type, String qstring) {
		return servicesURL + "/" + type + updateAction + "&nodeRef=" + nodeRef + qstring;
	}

	public String getDeleteURL(String nodeRef, String type) {
		return servicesURL + "/" + type + deleteAction + "&nodeRef=" + nodeRef;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		// delete all created nodes
		for (String nodeRef : created) {
			String json = getResponse(getDeleteURL(nodeRef, "<%getJavaModelObjectName%>"));
		}
	}
	
	
	public void testCreate() throws Exception {
		
		<%generateCreateStatementWebscript(name.toL1Case(),"")%>
		
		<%generatePropertiesAssertion("",name.toL1Case())%>
	}
	
	public void testRequest<%getJavaModelObjectName()%>() throws Exception {
		String operator = "";
		<%generateCreateStatementWebscript(name.toL1Case(),"")%>
		
		
		String jsonRequested = getResponse(getRequestURL("<%getJavaModelObjectName()%>", qstring));
		
		JSONArray jsa2 =(JSONArray) JSONSerializer.toJSON(jsonRequested);
		
		List<<%getJavaModelObjectName()%>> results = <%javaWebScriptFactoryAdapterName()%>.load<%getJavaModelObjectName()%>(jsa2);
		if (results.size() < 1) {
			fail("request result not match");
		}
		<%getJavaModelObjectName()%> c = results.get(0);
		
		<%generatePropertiesAssertionSearchable("","c")%>
	}
	
	public void testUpdate() throws Exception {
		// get uuid
		
		<%generateCreateStatementWebscript(name.toL1Case(),"")%>
		
		String nodeRef=<%name.toL1Case()%>.getUuid();
		
		<%generatePropertiesStatement(2)%>
		
		String qstringUpdate="";
		<%getPropertiesQueryString("2","qstringUpdate")%>
		
		String requestURLUpdate = getUpdateURL(nodeRef, "<%getJavaModelObjectName()%>", qstringUpdate);
		
		String jsonResquest = getResponse(requestURLUpdate + qstringUpdate);
		
		
		JSONObject jsa2 =(JSONObject) JSONSerializer.toJSON(jsonResquest);
		
		<%getJavaModelObjectName%> updated = <%javaWebScriptFactoryAdapterName()%>.load<%getJavaModelObjectName()%>(jsa2);
		
		<%generatePropertiesAssertion("2","updated")%>
	}
	
<%for (getAllSourceAssociationEnds()){%>
	
	public void testCreateAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>() throws Exception {
		// create source
		<%current("Clazz").generateCreateStatementWebscript("obj_"+current("Clazz").name+"2","")%>
		
		// create target

		<%getOpposite().linkedClass.filter("Clazz").generateCreateStatementWebscript("obj_"+getOpposite().linkedClass.name+"2","1")%>
		
		// create association
		String createAssoUrl = servicesURL + createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>;
		createAssoUrl += "&source=" + obj_<%current("Clazz").name%>2.getUuid();
		createAssoUrl += "&target=" + obj_<%getOpposite().linkedClass.name%>2.getUuid();
		
		String json2 = getResponse(createAssoUrl);
		JSONObject jsa2 =(JSONObject) JSONSerializer.toJSON(json2);
		
		// TODO : assertion
		assertEquals("ok", jsa2.getString("action"));
	}

	public void testRemoveAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>() throws Exception {
		// create source
		<%current("Clazz").generateCreateStatementWebscript("obj_"+current("Clazz").name+"2","")%>
		
		// create target

		<%getOpposite().linkedClass.filter("Clazz").generateCreateStatementWebscript("obj_"+getOpposite().linkedClass.name+"2","1")%>
		
		// create association
		String createAssoUrl = servicesURL + createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>;
		createAssoUrl += "&source=" + obj_<%current("Clazz").name%>2.getUuid();
		createAssoUrl += "&target=" + obj_<%getOpposite().linkedClass.name%>2.getUuid();
		String json2 = getResponse(createAssoUrl);
		JSONObject jsa2 =(JSONObject) JSONSerializer.toJSON(json2);
		
		assertEquals("ok", jsa2.getString("action"));
		
		// remove association
		String removeAssoUrl = servicesURL + removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>;
		removeAssoUrl += "&source=" + obj_<%current("Clazz").name%>2.getUuid();
		removeAssoUrl += "&target=" + obj_<%getOpposite().linkedClass.name%>2.getUuid();
		String json3 = getResponse(removeAssoUrl);
		JSONObject jsa3 = (JSONObject) JSONSerializer.toJSON(json3);
		
		// TODO : assertion
		assertEquals("ok", jsa3.getString("action"));
	}

	public void testGetAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>() throws Exception {
		// create source
		<%current("Clazz").generateCreateStatementWebscript("obj_"+current("Clazz").name+"2","")%>
		
		// create target

		<%getOpposite().linkedClass.filter("Clazz").generateCreateStatementWebscript("obj_"+getOpposite().linkedClass.name+"2","1")%>
		
		// create association
		String createAssoUrl = servicesURL + createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>;
		createAssoUrl += "&source=" + obj_<%current("Clazz").name%>2.getUuid();
		createAssoUrl += "&target=" + obj_<%getOpposite().linkedClass.name%>2.getUuid();
		String json2 = getResponse(createAssoUrl);
		JSONObject jsa2 =(JSONObject) JSONSerializer.toJSON(json2);
		
		assertEquals("ok", jsa2.getString("action"));
		
		// get associated
		String getAssoUrl = servicesURL + getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>;
		getAssoUrl += "&source=" + obj_<%current("Clazz").name%>2.getUuid();
		String json3 = getResponse(getAssoUrl);
		JSONArray jsa3 =(JSONArray) JSONSerializer.toJSON(json3);
		
		if (jsa3 !=null && jsa3.size() == 1) {
			String uuid = jsa3.get(0).toString();			
			assertEquals(obj_<%getOpposite().linkedClass.name%>2.getUuid(), uuid);
		} else {		
			fail("request result not match results numbers :" + jsa3.size());
		}
	}

	public void testGetAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>() throws Exception {
		// create source
		<%current("Clazz").generateCreateStatementWebscript("obj_"+current("Clazz").name+"2","")%>
		
		// create target

		<%getOpposite().linkedClass.filter("Clazz").generateCreateStatementWebscript("obj_"+getOpposite().linkedClass.name+"2","1")%>
		
		// create association
		String createAssoUrl = servicesURL + createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>;
		createAssoUrl += "&source=" + obj_<%current("Clazz").name%>2.getUuid();
		createAssoUrl += "&target=" + obj_<%getOpposite().linkedClass.name%>2.getUuid();
		String json2 = getResponse(createAssoUrl);
		JSONObject jsa2 =(JSONObject) JSONSerializer.toJSON(json2);
		
		assertEquals("ok", jsa2.getString("action"));
		
		// get associated
		String getAssoUrl = servicesURL + getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>;
		getAssoUrl += "&source=" + obj_<%current("Clazz").name%>2.getUuid();
		String json3 = getResponse(getAssoUrl);
		JSONArray jsa3 =(JSONArray) JSONSerializer.toJSON(json3);
		
		
		List<<%getOpposite().linkedClass.filter("Clazz").getJavaModelObjectName%>> qr = <%getOpposite().linkedClass.filter("Clazz").javaWebScriptFactoryAdapterName()%>.load<%getOpposite().linkedClass.filter("Clazz").getJavaModelObjectName()%>(jsa3);
		
		
		if (qr !=null && qr.size() == 1) {
			String uuid = qr.get(0).getUuid().toString();			
			assertEquals(obj_<%getOpposite().linkedClass.name%>2.getUuid(), uuid);
		} else {		
			fail("request result not match results numbers :" + jsa3.size());
		}
	}
	
<%}%>

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