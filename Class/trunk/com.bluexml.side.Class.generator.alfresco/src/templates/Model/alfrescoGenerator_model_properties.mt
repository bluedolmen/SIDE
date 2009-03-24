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
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.services.ClassServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%>shared/classes/alfresco/extension/BxDS/model.properties<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>

#Model's messages
<%getFolder()%>_<%name%>model.title=BlueXML Custom Model
<%getFolder()%>_<%name%>model.description=BlueXML Custom Model

<%for (getAllClasses()){%>
#Class' messages : <%getQualifiedName()%>
<%getLabel()%>
<%getDescriptionOrName()%>
	<%for (attributes){%>
<%getLabel()%>
<%getDescriptionOrName()%>
	<%}%>
	<%for (associations){%>
<%getFolder()%>_<%current(2).name%>model.association.<%getFolder()%>_<%current(1).getQualifiedName()%>_<%name%>.title=<%getRoleOrTitle(current(1))%>
<%--<%getFolder()%>_<%current(2).name%>model.association.<%getFolder()%>_<%current(1).getQualifiedName()%>_<%name%>_search.title=<%getRoleOrTitle(current(1))%>--%>
<%--<%getLabel()%>--%>
<%getDescriptionOrName()%>
	<%}%>	
	
<%}%>
<%for (getAllAspects()){%>
#Aspect's messages : <%getQualifiedName()%>
<%getLabel()%>
<%getDescriptionOrName()%>
	<%for (attributes){%>
<%getLabel()%>
<%getDescriptionOrName()%>
	<%}%>
	<%for (associations){%>
<%getLabel()%>
<%getLabel()%>
	<%}%>	
	
<%}%>