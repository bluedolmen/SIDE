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
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.Aspect" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%getJavaAPIName()%>.java
<%script type="clazz.Aspect" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>;

import java.io.Serializable;
import java.util.ArrayList;

<%if (getAllSortedAttibutes().filter("Attribute").typ.nContains("Date") ||
 getAllSortedAttibutes().filter("Attribute").typ.nContains("DateTime") ||
 getAllSortedAttibutes().filter("Attribute").typ.nContains("Time")){%>
import java.util.Date;
<%}%>
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.namespace.QName;

import <%getProperty("javaPackageAPI")%>.<%getProperty("java.classes.abstractAspectFactory")%>;
import <%getProperty("javaPackageModel")%>.<%service::getRootContainer().name%>.ModelQNames;
import <%getProperty("javaPackageModel")%>.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.*;

<%if (getAllSortedAttibutes().filter("Attribute").typ.nContains("Date") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("DateTime") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("Time")){%>
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
<%}%>

public class <%getJavaAPIName()%> extends <%getProperty("java.classes.abstractAspectFactory")%> {

<%if (getAllSortedAttibutes().filter("Attribute").typ.nContains("Date") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("DateTime") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("Time")){%>
	DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();
<%}%>
	/**
	 * add Coordonnees <%getJavaModelObjectName()%> to node
	 <%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 <%}%>	
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public <%getJavaModelObjectName()%> addAspectTo(NodeRef uuid<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodSignature()%>) {
<%generatePropertiesStatement()%>
		addAspectTo(QName.createQName(ModelQNames.CONTENT_MODEL_ASPECT_<%name.toUpperCase()%>.getQnameString()), props, uuid);
		return get<%getJavaModelObjectName()%>(uuid);
	}
	
		
	/**
	 * 
	 * @param uuid
	 * @throws Exception
	 */
	public void removeAspect(NodeRef uuid) throws Exception {
		removeAspectTo(QName.createQName(ModelQNames.CONTENT_MODEL_ASPECT_<%name.toUpperCase()%>.getQnameString()), uuid);
	}
	
	public boolean is<%getJavaModelObjectName()%>(NodeRef uuid) throws Exception {
		return nodeService.hasAspect(uuid, QName.createQName(ModelQNames.CONTENT_MODEL_ASPECT_<%name.toUpperCase()%>.getQnameString()));
	}
	
<%commonMethods()%>
}
