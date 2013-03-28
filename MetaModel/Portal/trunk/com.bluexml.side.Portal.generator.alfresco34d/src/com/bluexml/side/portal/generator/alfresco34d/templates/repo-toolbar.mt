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
import com.bluexml.side.portal.generator.alfresco34d.templates.toolbar
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%if (pageSet[ID.toLowerCase().trim() == "documentlibrary"].nSize() > 0 &&
 pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets[associationPortlet.name.toLowerCase().trim() == "repo-toolbar-create-content" && associationPortlet.isPortletInternal != null].nSize() > 0){%>
 <%getProperty("alf.share.paths.web-ext.alf.components")%>/documentlibrary/repo-toolbar.get.config.xml
 <%}%>

<%script type="Portal" name="repo-toolbar-create-content" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[(name.toLowerCase().trim() == "repo-toolbar-create-content") && isPortletInternal != null]){%>
<%generate_toolbar_portlet()%>
<%}%>
