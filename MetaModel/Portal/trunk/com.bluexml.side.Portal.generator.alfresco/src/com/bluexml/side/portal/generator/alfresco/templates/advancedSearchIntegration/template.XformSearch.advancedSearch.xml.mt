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
import com.bluexml.side.portal.generator.alfresco.service.ShareGeneratorServices
%>

<%-- Templates creation --%>
<%script type="HavePortlet" name="createTemplates"%>
<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null){%>
<%for (associationPortlet.isPortletInternal.form){%>
	<%if (current("HavePortlet").associationPortlet.name.toLowerCase() =="advanced-search" && current("HavePortlet").associationPortlet.isFormPortlet()){%>
<%getProperty("alf.share.paths.web-ext.components")%>/template.advanced-search.<%current("HavePortlet").associationPage.ID.toLowerCase()%>.xml
	<%}%>
<%}%>
<%}%>

<%script type="HavePortlet" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>advanced-search</region-id>
   <source-id><%associationPage.ID.toLowerCase()%></source-id>
   <url>/components/XformSearch</url>
   <properties>   		
   		<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null){%>
			<searchForm>[<%for (associationPortlet.isPortletInternal.form.forms){%>{id:'<%id%>',label:'<%label%>'}<%if (current("HavePortlet").associationPortlet.isPortletInternal.form.forms.nLast() !=current()){%>,<%}%><%}%>]</searchForm>
   		<%}%>
   </properties>
</component>
