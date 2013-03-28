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
%>

<%-- Templates creation --%>
<%script type="Portal" name="createMessages"%>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.web-ext")%>/sharePortal<%name%>Extensions/messages.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
## BlueXML SIDE Custom pages keys
<%for (pageSet){%>
<%ID.toLowerCase().nPut("messages_name")%>
<%ID.nPut("title_name")%>
<%title.nPut("description_name")%>
<%if (current().generate){%>
page.<%nGet("messages_name")%>.title=<%nGet("title_name")%>
page.<%nGet("messages_name")%>.description=<%if (nGet("description_name") !=null){%><%nGet("description_name")%><%}else{%><%}%>
<%}%>

<%if (ID.toLowerCase().trim() == "documentlibrary" && portlets[associationPortlet.name.toLowerCase().trim() == "toolbar-create-content" && associationPortlet.isPortletInternal != null].nSize() > 0){%>
<%for (portlets.associationPortlet[name.toLowerCase().trim() == "toolbar-create-content" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms.filter("form.FormClass")){%>
menu.create-content.<%real_class.name%>=<%real_class.getLabel()%> ...
<%}%>
<%}%>
<%}%>

<%for (portlets){%>
portlet.<%nGet("messages_name")%>.<%associationPortlet.name%>.header=<%associationPortlet.getLabel()%>
<%}%>

<%}%>
