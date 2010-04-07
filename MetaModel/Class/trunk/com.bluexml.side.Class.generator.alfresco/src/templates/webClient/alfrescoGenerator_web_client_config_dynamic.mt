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
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="clazz.ClassPackage" name="alfrescoGenerator_dynamic" %>

<%-- GENERATE THE DECLARATION OF ICON'S SPACES  --%>
<%for (getAllClasses().nSort("name")){%>
	<%if (metainfo[key.equalsIgnoreCase("isContainer")].nSize()>0 && !abstract){%>
<config evaluator="string-compare" condition="<%getFolder()%>:<%getQualifiedName()%> icons">
	<icons>
		<icon name="<%name%>" path="/images/icons/<%getQualifiedName()%>.gif" />
	</icons>
</config>
	<%}%>
<%}%>

<%-- GENERATE THE PROPERTIES REPRESENTATION OF A CONTENT  --%>
<%for (getAllClasses().nSort("name")){%>
<config evaluator="node-type" condition="<%getFolder()%>:<%getQualifiedName()%>">
	<property-sheet>
		<%for (getAllSortedAttibutes()){%>
		<show-property name="<%getFolder()%>:<%getQualifiedName()%>" <%if (metainfo[key.equalsIgnoreCase("read-only")].nSize()>0) {%>read-only="true"<%}%> />				
		<%}%>
		<%for (getAllSourceAssociationEnds()){%>
		<show-<%eContainer().getAssociationType()%> name="<%eContainer().getFolder()%>:<%eContainer().getQualifiedName(current("AssociationEnd"))%>" />
		<%}%>			
		
		<!-- from Alfresco content model -->
		<show-property name="modified"/>
		<show-property name="modifier"/>
		<show-property name="creator"/>
		<show-property name="created"/>
		<%-- <show-property name="versionLabel"/> 2009-08-14 - removed from generation => should be managed through aspect representation ?--%>
	</property-sheet>
	<%-- 2009-08-14 removed => useless ???
	<%for (getAllSortedAttibutes()){%>
	<property name="<%getFolder()%>:<%getQualifiedName()%>" />
	<%}%>

	<%for (getAllSourceAssociationEnds()){%>
	<property name="<%eContainer().getFolder()%>:<%eContainer().getQualifiedName(current("AssociationEnd"))%>" />
	<%}%>							
		
	<%for (current("clazz.ClassPackage").getAllAspects()){%>
		<%for (getSortedAttibutes()){%>
	<property name="<%getFolder()%>:<%getQualifiedName()%>" />
		<%}%>				
	<%}%>
		
	<!-- from Alfresco content model -->			
	<property name="creator"/>
	<property name="created"/>		
	<property name="modifier"/>		
	<property name="modified"/>
	<property name="versionLabel"/>
	--%>	
</config>

<%}%>


<%-- GENERATE THE PROPERTIES REPRESENTATION OF AN ASPECT  --%>
<%for (getAllAspects()){%>
<config evaluator="aspect-name" condition="<%getFolder()%>:<%getQualifiedName()%>">
	<property-sheet>			
		<%for (getSortedAttibutes()){%>
		<show-property name="<%getFolder()%>:<%getQualifiedName()%>" <%if (metainfo[key.equalsIgnoreCase("read-only")].nSize()>0) {%>read-only="true"<%}%> />
		<%}%>			
	</property-sheet>
</config>
<%}%>