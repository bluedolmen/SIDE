<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices


%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%getProperty("java.classes.factoryRegistry")%>.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>;

<%for (getAllSortedAbstractClasses()){%>
import <%getJavaAPIPackage()%>.<%getJavaAPIName%>;
<%}%>

public class <%getProperty("java.classes.factoryRegistry")%> {

<%for (getAllSortedAbstractClasses()){%>
	protected <%getJavaAPIName()%> <%getJavaAPIName().toL1Case()%>;
<%}%>	
<%for (getAllSortedAbstractClasses()){%>

	public <%getJavaAPIName%> get<%getJavaAPIName%>() {
		return <%getJavaAPIName().toL1Case()%>;
	}

	public void set<%getJavaAPIName%>(<%getJavaAPIName%> <%getJavaAPIName().toL1Case()%>) {
		this.<%getJavaAPIName().toL1Case()%> = <%getJavaAPIName().toL1Case()%>;
	}
<%}%>

}
