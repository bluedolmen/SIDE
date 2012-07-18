<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.view.generator.alfresco.templates.services.common

%>

<%script type="DataList" name="doclist_ftl" file="webapps/alfresco/WEB-INF/classes/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/doclist_views/doclist_<%name%>.ftl"%>
<#escape x as jsonUtils.encodeJSONString(x)>
<%for (children){%>
	<%if ( mapTo.filter("clazz.Attribute")){%>
	<#if (item.asset.properties["<%mapTo.filter("clazz.Attribute").getPrefixedQualifiedName()%>"])??>	
		<#if (item.asset.properties["<%mapTo.filter("clazz.Attribute").getPrefixedQualifiedName()%>"])?is_sequence>
		"<%mapTo.filter("clazz.Attribute").getPrefixedQualifiedName()%>":"<#list item.asset.properties["<%mapTo.filter("clazz.Attribute").getPrefixedQualifiedName()%>"] as key>${key<%mapTo.filter("clazz.Attribute").getFtlTypeConverter()%>} </#list>"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
		<#else>
		"<%mapTo.filter("clazz.Attribute").getPrefixedQualifiedName()%>":"${item.asset.properties["<%mapTo.filter("clazz.Attribute").getPrefixedQualifiedName()%>"]<%mapTo.filter("clazz.Attribute").getFtlTypeConverter()%>}"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
		</#if>
	<#else>
	"<%mapTo.filter("clazz.Attribute").getPrefixedQualifiedName()%>":""<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
	</#if>
	<%}%>
<%}%>
</#escape>