<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
%>
<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSourceTest")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/test/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%getJavaTestName()%>.java
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getJavaTestPackage%>;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

<%if (getSourceAssociationEnds().nSize() > 0){%>import org.alfresco.webservice.repository.QueryResult;<%}%>
import org.alfresco.webservice.types.ResultSet;
import org.alfresco.webservice.types.ResultSetRow;
import org.alfresco.webservice.types.ResultSetRowNode;
import org.alfresco.webservice.util.AuthenticationUtils;

import <%getJavaAPIPackage()%>.*;
import <%getJavaModelObjectPackage()%>.*;


public class <%getJavaTestName()%> extends TestCase {
	private static final String propertiesFileName = "test.properties";
	
	<%getJavaAPIName()%> <%name.toLowerCase()%>Factory;

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
		Properties props = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);
		try {
			props.load(is);
			login = props.getProperty("login");
			password = props.getProperty("password");
		} catch (Exception e) {
			// Do nothing, just use the default alfresco connection
			System.out.println("Unable to load webservice client properties from " + propertiesFileName + ": " + e.getMessage());
			System.out.println("default values used");
		}
		// open connection to Alfresco
		AuthenticationUtils.startSession(login, password);

		// instanciate <%getJavaModelObjectName()%>Factory
		<%name.toLowerCase()%>Factory = new <%getJavaAPIName()%>();		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		// delete all created node
		for (String uuid : created) {
			System.out.println("delete created "+uuid);
			<%name.toLowerCase()%>Factory.delete(uuid);
		}
		// close connection
		AuthenticationUtils.endSession();
	}
	
	
	public void testCreate() throws Exception {
		<%generateCreateStatement("f","")%>
		
		// assert for node
		assertNotNull(f);
		assertNotNull(f.getUuid());
		
		<%generatePropertiesAssertion("","f")%>
	}
	
	public void testRequest<%getJavaModelObjectName()%>() throws Exception {
		String operator = "";
		<%generateCreateStatement("f","")%>
		
		List<<%getJavaModelObjectName()%>> results = <%name.toLowerCase()%>Factory.request<%getJavaModelObjectName()%>(operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchCall(null)%>);
		if (results.size() < 1) {
			fail("request result not match");
		}
		<%getJavaModelObjectName()%> c = results.get(0);
		assertNotNull(c);
		<%generatePropertiesAssertionSearchable("","c")%>
	}
	
	/**
	 * Test method for
	 * {@link <%getJavaAPIQualifiedName()%>}
	 * 
	 */
	public void testRequest() throws Exception {
		String operator = "";
		<%generateCreateStatement("f","")%>
		
		ResultSet resultSet = <%name.toLowerCase()%>Factory.request(operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchCall(null)%>);
		ResultSetRow[] rows = resultSet.getRows();
		if (rows != null && rows.length == 1) {
			ResultSetRow resultSetRow = rows[0];
			ResultSetRowNode node = resultSetRow.getNode();
			// get uuid
			String uuid=node.getId();
			assertEquals(f.getUuid(), uuid);
		} else {
			fail("request result not match");
		}
		
	}
	
	public void testUpdate() throws Exception {
		// get uuid
		
		<%generateCreateStatement("f","")%>
		
		String uuid=f.getUuid();
		
		<%generatePropertiesStatement("2")%>
		
		<%name.toLowerCase()%>Factory.update(uuid<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodCall(2)%>);
	
		<%getJavaModelObjectName()%> updated = <%name.toLowerCase()%>Factory.get<%getJavaModelObjectName()%>(uuid);
		
		<%generatePropertiesAssertion("2","updated")%>
	}
	
<%for (getSourceAssociationEnds()){%>
	
	public void testCreateAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>() throws Exception {
		// create source
		<%current("Clazz").generateCreateStatement("obj_"+current("Clazz").name+"2","")%>
		
		// create target
	<%if (current("Clazz") != getOpposite().linkedClass){%>
		<%getOpposite().linkedClass.getJavaAPIName()%> <%getOpposite().linkedClass.name.toLowerCase()%>Factory = new <%getOpposite().linkedClass.getJavaAPIName()%>();
	<%}%>

		<%getOpposite().linkedClass.filter("Clazz").generateCreateStatement("obj_"+getOpposite().linkedClass.name+"2","1")%>
		
		// create association
		<%current("Clazz").name.toLowerCase()%>Factory.createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(obj_<%current("Clazz").name%>2, obj_<%getOpposite().linkedClass.name%>2);
	}

	public void testRemoveAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>() throws Exception {
		
		// create source
		<%current("Clazz").generateCreateStatement("obj_"+current("Clazz").name+"2","")%>		
		
		// create target
	<%if (current("Clazz") != getOpposite().linkedClass){%>		
		<%getOpposite().linkedClass.getJavaAPIName()%> <%getOpposite().linkedClass.name.toLowerCase()%>Factory = new <%getOpposite().linkedClass.getJavaAPIName()%>();		
	<%}%>
		
		<%getOpposite().linkedClass.filter("Clazz").generateCreateStatement("obj_"+getOpposite().linkedClass.name+"2",1)%>
	
		// create association
		<%current("Clazz").name.toLowerCase()%>Factory.createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(obj_<%current("Clazz").name%>2, obj_<%getOpposite().linkedClass.name%>2);
		
		// remove association
		<%current("Clazz").name.toLowerCase()%>Factory.removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(obj_<%current("Clazz").name%>2, obj_<%getOpposite().linkedClass.name%>2);
		
	}

	public void testGetAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>() throws Exception {
		// create source
		<%current("Clazz").generateCreateStatement("source","")%>
		
		// create target
	<%if (current("Clazz") != getOpposite().linkedClass){%>
		<%getOpposite().linkedClass.getJavaAPIName()%> <%getOpposite().linkedClass.name.toLowerCase()%>Factory = new <%getOpposite().linkedClass.getJavaAPIName()%>();
	<%}%>
		
		<%getOpposite().linkedClass.filter("Clazz").generateCreateStatement("target",1)%>
		
		// create association
		<%current("Clazz").name.toLowerCase()%>Factory.createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source, target);
		
		
		QueryResult qr = <%current("Clazz").name.toLowerCase()%>Factory.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source);
		
		 ResultSetRow[] rows = qr.getResultSet().getRows();
		if (rows != null && rows.length == 1) {
			ResultSetRow resultSetRow = rows[0];
			ResultSetRowNode node = resultSetRow.getNode();
			// get uuid
			String uuid = node.getId();
			assertEquals(target.getUuid(), uuid);
		} else {
			fail("request result not match");
		}
	}

	public void testGetAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>() throws Exception {
		// create source
		<%current("Clazz").generateCreateStatement("source","")%>
		
		// create target
	<%if (current("Clazz") != getOpposite().linkedClass){%>
		<%getOpposite().linkedClass.getJavaAPIName()%> <%getOpposite().linkedClass.name.toLowerCase()%>Factory = new <%getOpposite().linkedClass.getJavaAPIName()%>();
	<%}%>
		
		<%getOpposite().linkedClass.filter("Clazz").generateCreateStatement("target",1)%>
		
		// create association
		<%current("Clazz").name.toLowerCase()%>Factory.createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source, target);
				

		List<<%getOpposite().linkedClass.name.toU1Case()%>> associated_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%> = <%current("Clazz").name.toLowerCase()%>Factory.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(source);
		
		assertEquals(associated_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>.get(0).getUuid(), target.getUuid());
	}
	
<%}%>
}