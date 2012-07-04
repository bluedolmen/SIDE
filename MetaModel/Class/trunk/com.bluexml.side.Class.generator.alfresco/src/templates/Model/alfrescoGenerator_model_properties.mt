<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
<%--encoding=iso-8859-1--%>
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator
%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/model/messages.properties<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>

#Model's messages
<%getFolder()%>_model.title=BlueXML Custom Model
<%getFolder()%>_model.description=BlueXML Custom Model

<%for (getAllClasses().nSort("name")){%>
#Class' messages : <%getQualifiedName()%>
<%getFolder()%>_model.type.<%getPrefixedQName("_")%>.title=<%getLabel()%>
<%getFolder()%>_model.type.<%getPrefixedQName("_")%>.description=<%getDescriptionOrName()%>
<%generateMessagesForAbsClasses()%>
<%}%>
<%for (getAllAspects().nSort("name")){%>
#Aspect's messages : <%getQualifiedName()%>
<%getFolder()%>_model.aspect.<%getPrefixedQName("_")%>.title=<%getLabel()%>
<%getFolder()%>_model.aspect.<%getPrefixedQName("_")%>.description=<%getDescriptionOrName()%>
<%generateMessagesForAbsClasses()%>	
<%}%>

<%script type="AbstractClass" name="generateMessagesForAbsClasses"%>
<%for (getSortedAttibutes()){%>
<%getFolder()%>_model.property.<%getPrefixedQName("_")%>.title=<%getLabel()%>
<%getFolder()%>_model.property.<%getPrefixedQName("_")%>.description=<%getDescriptionOrName()%>
<%}%>
<%for (getSourceAssociationEnds()){%>
<%getFolder()%>_model.association.<%eContainer().getPrefixedAssociationQName(current("AssociationEnd"))%>.title=<%getRoleOrTitleFromSource()%>
<%getFolder()%>_model.association.<%eContainer().getPrefixedAssociationQName(current("AssociationEnd"))%>.description=<%eContainer().getDescriptionOrName()%>
<%}%>