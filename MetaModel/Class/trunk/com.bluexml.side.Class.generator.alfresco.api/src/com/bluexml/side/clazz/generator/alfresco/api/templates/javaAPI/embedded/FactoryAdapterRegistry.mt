<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices


%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%getProperty("java.classes.factoryRegistryAdapter")%>.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;

<%for (getAllSortedAbstractClasses()){%>
import <%getJavaAPIPackage()%>.script.<%javaWebScriptFactoryAdapterName()%>;
<%}%>

public class <%getProperty("java.classes.factoryRegistryAdapter")%> extends BaseScopableProcessorExtension {

<%for (getAllSortedAbstractClasses()){%>
	protected <%javaWebScriptFactoryAdapterName()%> <%javaWebScriptFactoryAdapterName().toL1Case()%>;
<%}%>	
<%for (getAllSortedAbstractClasses()){%>

	public <%javaWebScriptFactoryAdapterName()%> get<%javaWebScriptFactoryAdapterName()%>() {
		return <%javaWebScriptFactoryAdapterName().toL1Case()%>;
	}

	public void set<%javaWebScriptFactoryAdapterName()%>(<%javaWebScriptFactoryAdapterName()%> <%javaWebScriptFactoryAdapterName().toL1Case()%>) {
		this.<%javaWebScriptFactoryAdapterName().toL1Case()%> = <%javaWebScriptFactoryAdapterName().toL1Case()%>;
	}
<%}%>

}
