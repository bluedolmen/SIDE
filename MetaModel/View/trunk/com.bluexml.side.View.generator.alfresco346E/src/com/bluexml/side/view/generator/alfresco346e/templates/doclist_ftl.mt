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
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.view.generator.alfresco.templates.services.common

%>

<%script type="DataList" name="doclist_ftl" file="webapps/alfresco/WEB-INF/classes/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/doclist_views/doclist_<%getFullName().replaceAll("\\.", "_")%>.ftl"%>
<#escape x as jsonUtils.encodeJSONString(x)>
<%for (children){%>
	<%if ( mapTo.filter("clazz.Attribute")){%>
	<#if (node.properties["<%mapTo.filter("clazz.Attribute").getPrefixedQName()%>"])??>	
		<#if (node.properties["<%mapTo.filter("clazz.Attribute").getPrefixedQName()%>"])?is_sequence>
		"<%mapTo.filter("clazz.Attribute").getPrefixedQName()%>":"<#list node.properties["<%mapTo.filter("clazz.Attribute").getPrefixedQName()%>"] as key>${key<%mapTo.filter("clazz.Attribute").getFtlTypeConverter()%>} </#list>"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
		<#else>
		"<%mapTo.filter("clazz.Attribute").getPrefixedQName()%>":"${node.properties["<%mapTo.filter("clazz.Attribute").getPrefixedQName()%>"]<%mapTo.filter("clazz.Attribute").getFtlTypeConverter()%>}"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
		</#if>
	<#else>
	"<%mapTo.filter("clazz.Attribute").getPrefixedQName()%>":""<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
	</#if>
	<%}%>
<%}%>
</#escape>
