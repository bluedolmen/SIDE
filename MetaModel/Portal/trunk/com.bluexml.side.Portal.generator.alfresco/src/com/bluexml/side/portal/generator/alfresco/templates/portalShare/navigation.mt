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
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>


<%-- Navigation component templates creation --%>
<%script type="Page" name="createNavigationComponentTemplate"%>
<%ID.toLowerCase().nPut("navigation_name")%>
<%if (generate){%>
<%getProperty("alf.share.paths.web-ext.components")%>template.navigation.<%nGet("navigation_name")%>.xml
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<%ID.toLowerCase().nPut("navigation_name")%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>navigation</region-id>
   <source-id><%nGet("navigation_name")%></source-id>
   <url>/components/navigation/collaboration-navigation</url>
</component>
