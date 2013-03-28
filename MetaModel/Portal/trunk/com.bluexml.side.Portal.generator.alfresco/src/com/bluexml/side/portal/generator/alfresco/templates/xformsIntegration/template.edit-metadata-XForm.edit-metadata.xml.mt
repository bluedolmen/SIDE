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
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portlet" name="createTemplates"%>
<%if (name.toLowerCase() =="edit-metadata-xform"){%>
<%getProperty("alf.share.paths.web-ext.components")%>/template.edit-metadata-XForm.edit-metadata.xml
<%}%>

<%script type="Portlet" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>edit-metadata-XForm</region-id>
   <source-id>edit-metadata</source-id>
   <url>/components/Xform</url>
   <properties>
      <itemKind>node</itemKind>
      <itemId>{nodeRef}</itemId>
      <mode>edit</mode>
      <submitType>json</submitType>
      <showCaption>true</showCaption>
      <showCancelButton>true</showCancelButton>
   </properties>
</component>
