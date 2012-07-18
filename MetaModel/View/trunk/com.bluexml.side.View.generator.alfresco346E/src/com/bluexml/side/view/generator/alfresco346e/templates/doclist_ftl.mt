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