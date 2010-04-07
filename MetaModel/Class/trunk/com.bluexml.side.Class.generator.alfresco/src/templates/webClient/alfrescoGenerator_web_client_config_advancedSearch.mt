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

<%script type="clazz.ClassPackage" name="alfrescoGenerator_advancedSearch"%>

				<%for (getAllAspects().nSort("name")) {%>
					<%for (getSortedAttibutes()){%>
						<%if (metainfo[key.startsWith("propertySearched")].nSize()>0){%>
						<meta-data aspect="<%getFolder()%>:<%eContainer().getQualifiedName()%>" property="<%getFolder()%>:<%getQualifiedName()%>"/>
						<%}%>
					<%}%>
				<%}%>
				<%for (getAllClasses().nSort("name")) {%>
					<%for (getSortedAttibutes()){%>
						<%if (metainfo[key.startsWith("propertySearched")].nSize()>0){%>
						<meta-data type="<%getFolder()%>:<%eContainer().getQualifiedName()%>" property="<%getFolder()%>:<%getQualifiedName()%>"/>
						<%}%>
					<%}%>
				<%}%>