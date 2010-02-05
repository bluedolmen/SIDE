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
<%getFolder()%>_<%name%>model.title=BlueXML Custom Model
<%getFolder()%>_<%name%>model.description=BlueXML Custom Model

<%for (getAllClasses()){%>
#Class' messages : <%getQualifiedName()%>
<%getFolder()%>_<%current(1).name%>model.type.<%getFolder()%>_<%getQualifiedName()%>.title=<%getLabel()%>
<%getFolder()%>_<%current(1).name%>model.type.<%getFolder()%>_<%getQualifiedName()%>.description=<%getDescriptionOrName()%>
	<%for (attributes){%>
<%getFolder()%>_<%current(2).name%>model.property.<%getFolder()%>_<%current(1).getQualifiedName()%>_<%name%>.title=<%getLabel()%>
<%getFolder()%>_<%current(2).name%>model.property.<%getFolder()%>_<%current(1).getQualifiedName()%>_<%name%>.description=<%getDescriptionOrName()%>
	<%}%>
	<%for (getSourceAssociationEnds()){%>
<%getFolder()%>_<%current(2).name%>model.association.<%eContainer().getFolder()%>_<%current(1).getQualifiedName()%>_<%if (name != null){%><%name%><%}%>.title=<%getRoleOrTitleFromSource()%>
<%--<%getFolder()%>_<%current(2).name%>model.association.<%getFolder()%>_<%current(1).getQualifiedName()%>_<%name%>_search.title=<%getRoleOrTitle(current(1))%>--%>
<%--<%getFolder()%>_<%current(2).name%>model.association.<%getFolder()%>_<%current(1).getQualifiedName()%>_<%name%>_search_operator=Option <%getLabel()%>--%>
<%getFolder()%>_<%current(2).name%>model.association.<%eContainer().getFolder()%>_<%current(1).getQualifiedName()%>_<%if (name != null){%><%name%><%}%>.description=<%eContainer().getDescriptionOrName()%>
	<%}%>	
	
<%}%>
<%for (getAllAspects()){%>
#Aspect's messages : <%getQualifiedName()%>
<%getFolder()%>_<%current(1).name%>model.aspect.<%getFolder()%>_<%getQualifiedName()%>.title=<%getLabel()%>
<%getFolder()%>_<%current(1).name%>model.aspect.<%getFolder()%>_<%getQualifiedName()%>.description=<%getDescriptionOrName()%>
	<%for (attributes){%>
<%getFolder()%>_<%current(2).name%>model.property.<%getFolder()%>_<%current(1).getQualifiedName()%>_<%name%>.title=<%getLabel()%>
<%getFolder()%>_<%current(2).name%>model.property.<%getFolder()%>_<%current(1).getQualifiedName()%>_<%name%>.description=<%getDescriptionOrName()%>
	<%}%>
<%}%>