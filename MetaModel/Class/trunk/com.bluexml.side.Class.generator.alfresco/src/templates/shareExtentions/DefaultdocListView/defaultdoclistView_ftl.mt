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
	<#else/>
	"<%getPrefixedQualifiedName()%>":"${item.asset.properties["<%getPrefixedQualifiedName()%>"]<%getFtlTypeConverter()%>}"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
	</#if>
<#else/>
"<%getPrefixedQualifiedName()%>":""<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
</#if>
<%}%>
