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
