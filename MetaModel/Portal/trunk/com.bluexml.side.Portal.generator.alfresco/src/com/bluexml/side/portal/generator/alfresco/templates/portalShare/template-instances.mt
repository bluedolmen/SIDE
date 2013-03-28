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

<%-- Templates instance creation --%>
<%script type="Page" name="createTemplateInstances"%>
<%ID.toLowerCase().nPut("ti_name")%>
<%if (generate){%>
<%getProperty("alf.share.paths.web-ext.template-instances")%><%nGet("ti_name")%>.xml
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createTemplateInstances%>" post="trim()"%>
<%ID.toLowerCase().nPut("ti_name")%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>com/bluexml/<%nGet("ti_name")%></template-type>
   <properties>
   <%-- need to skep metainfo like rawcontent ... --%>
   <%for (metainfo[key.toLowerCase().indexOf("raw") == -1]){%>
   		<<%key%>><%value%></<%key%>>
   <%}%>
   </properties>
</template-instance>
