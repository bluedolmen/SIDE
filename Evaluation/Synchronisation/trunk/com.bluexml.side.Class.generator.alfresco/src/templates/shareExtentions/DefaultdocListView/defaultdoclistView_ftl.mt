<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
%>

<%script type="clazz.Clazz" name="doclist_ftl" file="<%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/doclist_views/doclist_<%getQualifiedName()%>.ftl"%>
<%for (getAllAttributes()){%>
<#if (item.asset.properties["<%getPrefixedQualifiedName()%>"]!'')?is_sequence>
"<%getPrefixedQualifiedName()%>":"<#list item.asset.properties["<%getPrefixedQualifiedName()%>"] as key>${key} </#list>"<%if (i() < current("Clazz").getAllAttributes().nSize() -1){%>,<%}%>
<#else/>
"<%getPrefixedQualifiedName()%>":"${item.asset.properties["<%getPrefixedQualifiedName()%>"]!''}"<%if (i() < current("Clazz").getAllAttributes().nSize() -1){%>,<%}%>
</#if>
<%}%>
