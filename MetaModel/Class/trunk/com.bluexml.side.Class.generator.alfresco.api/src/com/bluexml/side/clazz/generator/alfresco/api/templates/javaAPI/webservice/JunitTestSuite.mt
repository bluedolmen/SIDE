<%--
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
--%>
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
<%if (eContainer() == null) {%><%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSourceTest")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/test/AllTests.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageTest")%>.<%service::getRootContainer().name%>.test;

import junit.framework.Test;
import junit.framework.TestSuite;

<%for (getAllClasses().nSort("name")){%>
import <%getJavaTestPackage()%>.<%getJavaTestName()%>;
<%}%>


public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for <%getProperty("javaPackageTest")%>.<%service::getRootContainer().name%>.*");
		//$JUnit-BEGIN$
<%for (getAllClasses().nSort("name")){%>
		suite.addTestSuite(<%getJavaTestName()%>.class);
<%}%>
		//$JUnit-END$
		return suite;
	}

}
