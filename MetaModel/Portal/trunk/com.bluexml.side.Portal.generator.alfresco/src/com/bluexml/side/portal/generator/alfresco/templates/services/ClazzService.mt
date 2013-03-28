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
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%-- Must be genereated using Portal generator to choose the view --%>


<%script type="common.NamedModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.ClassModelElement" name="getNameSpace"%>
<%getNamespaceURI()%>
<%script type="common.NamedModelElement" name="getContentType"%>
<%getPrefixedQName()%>
<%script type="common.NamedModelElement" name="getQualifiedName"%>
<%getNamedModelElementQName()%>

<%script type="portal.Page" name="isDefaultSharePage"  post="trim()"%>
<%if (ID == "documentlibrary"
|| ID == "calendar"
|| ID == "wiki-page"
|| ID == "discussions-topiclist"
|| ID == "blog-postlist"
){%>
true
<%}else{%>
false
<%}%>
<%script type="portal.Page" name="isDefaultPublicSharePage"  post="trim()"%>
<%if (ID == "documentlibrary"
|| ID == "calendar"
|| ID == "wiki-page"
|| ID == "discussions-topiclist"
|| ID == "blog-postlist"
){%>
true
<%}else{%>
false
<%}%>
<%script type="portal.Portlet" name="isDefaultShareComponent" post="trim()"%>
<%if (name == "documentlibrary"
|| name == "documentdetails"
){%>
true
<%}else{%>
false
<%}%>
