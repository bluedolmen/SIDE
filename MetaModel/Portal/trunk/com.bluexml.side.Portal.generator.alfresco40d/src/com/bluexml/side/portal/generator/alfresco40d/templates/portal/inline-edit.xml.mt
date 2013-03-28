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
%>
<%script type="HavePortlet" name="validatedFilename"%>
<%if (associationPage.ID.toLowerCase().trim() == "inline-edit" && !associationPage.generate && associationPortlet[name.toLowerCase().trim() == "inline-edit"].nSize() > 0) {%>
<%getProperty("alf.share.paths.web-ext.components")%>template.inline-edit.inline-edit.xml
<%}%>

<%script type="HavePortlet" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<!-- Inline Edit Form -->
<component>
   <scope>template</scope>
   <source-id>inline-edit</source-id>
   <region-id>inline-edit</region-id>
   <url>/components/form</url>
   <properties>
<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null && associationPortlet.isPortletInternal.form.name != "default"){%>
      <formId><%associationPortlet.isPortletInternal.form.name%></formId>
<%}%>
  <itemKind>node</itemKind>
  <itemId>{nodeRef}</itemId>
  <mode>edit</mode>
  <submitType>json</submitType>
  <showCaption>true</showCaption>
  <showCancelButton>true</showCancelButton>
</properties>
