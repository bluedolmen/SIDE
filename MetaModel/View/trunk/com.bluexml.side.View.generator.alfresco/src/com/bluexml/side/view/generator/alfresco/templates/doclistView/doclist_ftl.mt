<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.alfresco.templates.services.common
%>

<%script type="DataList" name="doclist_ftl" file="webapps/alfresco/WEB-INF/classes/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/doclist_views/doclist_<%name%>.ftl"%>
<#escape x as jsonUtils.encodeJSONString(x)>
<%for (children){%>
	<%if ( mapTo.filter("common.NamedModelElement")){%>
	"<% mapTo.filter("common.NamedModelElement").getPrefixedQualifiedName()%>":"${item.asset.properties["<% mapTo.filter("common.NamedModelElement").getPrefixedQualifiedName()%>"]}"<%if (i() < current("DataList").children.nSize() -1){%>,<%}%>
	<%}%>
<%}%>
</#escape>