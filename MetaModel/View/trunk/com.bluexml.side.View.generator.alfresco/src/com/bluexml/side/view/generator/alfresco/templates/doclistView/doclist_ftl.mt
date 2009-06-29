<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.alfresco.templates.services.common
%>

<%script type="DataList" name="doclist_ftl" file="webapps/alfresco/WEB-INF/classes/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/doclist_views/doclist_<%name%>.ftl"%>
<%for (children){%>
	<%if ( mapTo.filter("common.NamedModelElement")){%>
	<#if (item.asset.properties["<%mapTo.filter("common.NamedModelElement").getPrefixedQualifiedName()%>"]!'')?is_sequence>
	"<%mapTo.filter("common.NamedModelElement").getPrefixedQualifiedName()%>":"<#list item.asset.properties["<%mapTo.filter("common.NamedModelElement").getPrefixedQualifiedName()%>"] as key>${key} </#list>"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
	<#else/>
	"<%mapTo.filter("common.NamedModelElement").getPrefixedQualifiedName()%>":"${item.asset.properties["<%mapTo.filter("common.NamedModelElement").getPrefixedQualifiedName()%>"]!''}"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
	</#if>
	<%}%>
<%}%>