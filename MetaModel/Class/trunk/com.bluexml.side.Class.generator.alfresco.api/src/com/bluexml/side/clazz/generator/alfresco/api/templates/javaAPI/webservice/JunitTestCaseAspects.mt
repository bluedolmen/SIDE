<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
%>
<%script type="clazz.Aspect" name="validatedFilename"%>
<%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSourceTest")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/test/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%getJavaTestName()%>.java
<%script type="clazz.Aspect" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getJavaTestPackage%>;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLCreate;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.ParentReference;
import org.alfresco.webservice.types.Store;
import org.alfresco.webservice.util.AuthenticationUtils;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.WebServiceFactory;

import <%getJavaAPIPackage()%>.*;
import <%getJavaModelObjectPackage()%>.*;

public class <%getJavaTestName()%> extends TestCase {
	private static final String propertiesFileName = "test.properties";
	
	<%getJavaAPIName()%> <%getJavaAPIName().toL1Case()%>;
	<%getFistClass().getJavaAPIName()%> <%getFistClass().name.toLowerCase()%>Factory;
	List<String> created = new ArrayList<String>();

	public <%getJavaTestName()%>(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		// get properties
		String login="admin";
		String password="admin";
		Properties props = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);
		try {
			props.load(is);
			login = props.getProperty("login");
			password = props.getProperty("password");
		} catch (Exception e) {
			// Do nothing, just use the default endpoint
			System.out.println("Unable to load webservice client properties from " + propertiesFileName + ": " + e.getMessage());
			System.out.println("default values used");
		}
		// open connection to Alfresco
		AuthenticationUtils.startSession(login, password);

		// Instantiate Factories	
		
		<%getJavaAPIName().toL1Case()%> = new <%getJavaAPIName()%>();
		<%getFistClass().name.toLowerCase()%>Factory = new <%getFistClass().getJavaAPIName()%>();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		// delete all created node		
		for (String uuid : created) {
			System.out.println("delete created "+uuid);
			<%getFistClass().name.toLowerCase()%>Factory.delete(uuid);
		}
		// close connection
		AuthenticationUtils.endSession();
	}

	public void testAddAspectTo() throws Exception {
		String uuid = createNewContent();
		
		<%generatePropertiesStatement("1")%>
		
		<%getJavaAPIName().toL1Case()%>.addAspectTo(uuid, <%getJavaPropertiesMethodCall("1")%>);
		
		// assert
		assertTrue(<%getJavaAPIName().toL1Case()%>.is<%getJavaModelObjectName()%>(uuid));
	}

	public void testUpdate() throws Exception {
		String uuid = createNewContent();
		<%generatePropertiesStatement("1")%>
		
		<%getJavaAPIName().toL1Case()%>.addAspectTo(uuid, <%getJavaPropertiesMethodCall("1")%>);
		
		// new values
		<%generatePropertiesStatement("2")%>
		// update
		<%getJavaAPIName().toL1Case()%>.update(uuid,  <%getJavaPropertiesMethodCall("2")%>);
		
		// assert
		<%getJavaModelObjectName()%> updated = <%getJavaAPIName().toL1Case()%>.get<%getJavaModelObjectName()%>(uuid);
		
		<%current("AbstractClass").generatePropertiesAssertion("2","updated")%>
	}

	public void testRemoveAspect() throws Exception {
		String uuid = createNewContent();
		<%generatePropertiesStatement("1")%>
		
		<%getJavaAPIName().toL1Case()%>.addAspectTo(uuid, <%getJavaPropertiesMethodCall("1")%>);		
		
		// remove
		<%getJavaAPIName().toL1Case()%>.removeAspect(uuid);
		
		// assert
		assertFalse(<%getJavaAPIName().toL1Case()%>.is<%getJavaModelObjectName()%>(uuid));
		
	}
	
	public void testIs<%getJavaModelObjectName()%>() throws Exception {
		String uuid = createNewContent();
		<%generatePropertiesStatement("1")%>
		
		// assert
		assertFalse(<%getJavaAPIName().toL1Case()%>.is<%getJavaModelObjectName()%>(uuid));
		
		<%getJavaAPIName().toL1Case()%>.addAspectTo(uuid, <%getJavaPropertiesMethodCall("1")%>);
		
		// assert
		assertTrue(<%getJavaAPIName().toL1Case()%>.is<%getJavaModelObjectName()%>(uuid));
		
	}

	public void testGet<%getJavaModelObjectName()%>() throws Exception {
		String uuid = createNewContent();
		<%generatePropertiesStatement("1")%>
		
		<%getJavaAPIName().toL1Case()%>.addAspectTo(uuid, <%getJavaPropertiesMethodCall("1")%>);
		
		<%getJavaModelObjectName()%> <%name.toLowerCase()%>Obj = <%getJavaAPIName().toL1Case()%>.get<%getJavaModelObjectName()%>(uuid);
		
		<%current("AbstractClass").generatePropertiesAssertion("1",name.toLowerCase()+"Obj")%>
	}
	
	private String createNewContent() throws RemoteException, RepositoryFault {
		NamedValue[] properties= new NamedValue[0];
		Store store = new Store(Constants.WORKSPACE_STORE, "SpacesStore");
		ParentReference parentReference = new ParentReference(store, null, "/app:company_home", Constants.ASSOC_CONTAINS, Constants.createQNameString(Constants.NAMESPACE_CONTENT_MODEL, "Clazz1"));
		CMLCreate create = new CMLCreate(null, parentReference , null, null, null, Constants.TYPE_CONTENT, properties);
		CML cml = new CML();
		cml.setCreate(new CMLCreate[] { create });
		UpdateResult[] results = WebServiceFactory.getRepositoryService().update(cml);
		String uuid = results[0].getDestination().getUuid();
		return uuid;
	}
}
