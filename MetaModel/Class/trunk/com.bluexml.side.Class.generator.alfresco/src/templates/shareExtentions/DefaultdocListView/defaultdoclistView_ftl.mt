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
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
%>

<%script type="clazz.Clazz" name="doclist_ftl" file="<%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/doclist_views/doclist_<%getQualifiedName()%>.ftl"%>
<%for (getAllSortedAttibutes()){%>
<#if (item.asset.properties["<%getPrefixedQualifiedName()%>"])??>	
	<#if (item.asset.properties["<%getPrefixedQualifiedName()%>"])?is_sequence>
	"<%getPrefixedQualifiedName()%>":"<#list item.asset.properties["<%getPrefixedQualifiedName()%>"] as key>${key<%getFtlTypeConverter()%>} </#list>"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
	<#else>
	"<%getPrefixedQualifiedName()%>":"${item.asset.properties["<%getPrefixedQualifiedName()%>"]<%getFtlTypeConverter()%>}"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
	</#if>
<#else>
"<%getPrefixedQualifiedName()%>":""<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
</#if>
<%}%>
