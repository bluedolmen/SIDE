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
<%script type="HavePortlet" name="createNavigationComponentTemplate"%>
<%if (associationPortlet.isPortletInternal == null && !(associationPortlet.metainfo[key == "rawContent"].nSize() > 0 || associationPortlet.metainfo[key == "rawContentFilePath"].nSize() > 0)){%>
<%getProperty("alf.share.paths.web-ext.components")%><%computeScope()%>.<%associationPortlet.name%>.<%associationPage.ID.toLowerCase()%>.xml
<%}%>
<%script type="HavePortlet" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope><%computeScope()%></scope>
   <region-id><%associationPortlet.name%></region-id>
   <source-id><%associationPage.ID.toLowerCase()%></source-id>
   <url><%computeUrl()%></url>
   <properties>
   <%for (associationPortlet.isInstanceOfPortletType.instances){%>
   <%if (instanceOf.name != "scope" && instanceOf.name != "url" && instanceOf.name != "region-id" && instanceOf.name != "source-id" && value != null && value != ""){%>   
      <<%instanceOf.name%>><%if (instanceOf.metainfo[key.equalsIgnoreCase("escape")].nSize() > 0 && instanceOf.metainfo[key.equalsIgnoreCase("escape")].nFirst().value.equalsIgnoreCase("false")){%><%value%><%}else{%><%value.replaceAll("\{", "%7B").replaceAll("\}", "%7D")%><%}%></<%instanceOf.name%>>
   <%}%>   	
   <%}%>
   </properties>
</component>

<%script type="HavePortlet" name="computeScope" post="trim()"%>
<%for (associationPortlet.isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "scope"){%><%value%><%}%>	
<%}%>
<%script type="HavePortlet" name="computeUrl" post="trim()"%>
<%for (associationPortlet.isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "url"){%><%value%><%}%><%}%>
