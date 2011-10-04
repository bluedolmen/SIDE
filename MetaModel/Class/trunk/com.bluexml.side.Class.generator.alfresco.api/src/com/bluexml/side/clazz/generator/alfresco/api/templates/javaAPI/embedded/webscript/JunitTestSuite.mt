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
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSourceTest")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/test/AllTests.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageTest")%>.<%service::getRootContainer().name%>.test;

import junit.framework.Test;
import junit.framework.TestSuite;

<%for (getAllAbstractClasses().nSort("name")){%>
import <%getJavaTestPackage()%>.<%getJavaTestName()%>;
<%}%>


public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for <%getProperty("javaPackageTest")%>.<%service::getRootContainer().name%>.*");
		//$JUnit-BEGIN$
<%for (getAllAbstractClasses().nSort("name")){%>
		suite.addTestSuite(<%getJavaTestName()%>.class);
<%}%>
		//$JUnit-END$
		return suite;
	}

}
