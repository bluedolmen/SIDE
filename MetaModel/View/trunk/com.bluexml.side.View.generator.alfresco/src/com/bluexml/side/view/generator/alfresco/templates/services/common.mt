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
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
%>

<%script type="common.NamedModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="view.AbstractViewOf" name="getAllSortedAttibutes"%>
<%getFields()[path == null].mapTo.filter("clazz.Attribute").nSort("name")%>
<%script type="view.AbstractViewOf" name="getClassModel"%>
<%viewOf.getRootContainer().filter("clazz.Model")%>

<%script type="clazz.Attribute" name="generateAttributeStatement"%>
<#if (<%args(0)%>.properties["<%getPrefixedQName()%>"]?exists)>
	<#if <%args(0)%>.properties["<%getPrefixedQName()%>"]?is_sequence>
	"<%getPrefixedQName()%>":"<#list <%args(0)%>.properties["<%getPrefixedQName()%>"] as key>${key} </#list>"
	<#else>
	<%if (typ.toString().equalsIgnoreCase("date")){%>
	"<%getPrefixedQName()%>":"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"
	<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
	"<%getPrefixedQName()%>":"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"
	<%}else{%>
	"<%getPrefixedQName()%>":"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string!""}"
	<%}%>
	</#if>
<#else>
	"<%getPrefixedQName()%>":""
</#if>

<%script type="clazz.Attribute" name="generateAttributeStatement_csv"%>
<#if (<%args(0)%>.properties["<%getPrefixedQName()%>"]?exists)><#t>
	<#if <%args(0)%>.properties["<%getPrefixedQName()%>"]?is_sequence><#t>
	"<#list <%args(0)%>.properties["<%getPrefixedQName()%>"] as key>${key}<#if key_has_next> </#if></#list>"<#t>
	<#else><#t>
	<%if (typ.toString().equalsIgnoreCase("date")){%><#t>
	"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"<#t>
	<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
	"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"<#t>
	<%}else{%>
	"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string!""}"<#t>
	<%}%>
	</#if><#t>
<#else><#t>
	""<#t>
</#if><#t>
