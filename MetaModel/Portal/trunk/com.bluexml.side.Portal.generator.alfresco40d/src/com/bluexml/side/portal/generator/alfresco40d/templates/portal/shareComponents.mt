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
import com.bluexml.side.portal.generator.alfresco40d.templates.portal.template-instance
%>


<%-- Navigation component templates creation --%>
<%script type="HavePortlet" name="createNavigationComponentTemplate"%>
<%if (associationPortlet.isInstanceOfPortletType != null && associationPortlet.isInstanceOfPortletType.instances[instanceOf.name == "scope"].value == "global"){%>
<%getProperty("alf.share.paths.web-ext.components")%><%computeScope()%>.<%associationPortlet.name%>.<%associationPage.ID.toLowerCase()%>.xml
<%}%>
<%script type="HavePortlet" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope><%computeScope()%></scope>
   <region-id><%associationPortlet.name%></region-id>
   <source-id><%associationPage.ID.toLowerCase()%></source-id>
   <url><%computeUrl()%></url>
   <%associationPortlet.computeProperties()%>
   <%compute_subComponent()%>
</component>

<%script type="HavePortlet" name="computeScope" post="trim()"%>
<%for (associationPortlet.isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "scope"){%><%value%><%}%>	
<%}%>
<%script type="HavePortlet" name="computeUrl" post="trim()"%>
<%for (associationPortlet.isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "url"){%><%value%><%}%><%}%>
