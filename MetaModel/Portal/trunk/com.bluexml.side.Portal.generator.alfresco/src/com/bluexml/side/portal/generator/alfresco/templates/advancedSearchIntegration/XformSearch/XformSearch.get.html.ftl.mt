<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/com/bluexml/components/XformSearch/XformSearch.get.html.ftl
<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<script>
function selectXFormSearch(item) {
	var blankurl = "<%getXFORMURL()%>/xforms?type={formName}&formType=search&nextPageSubmit=<%getSHAREURL()%>/searchFormCallBack.html";
	var selected = item.options[item.selectedIndex];
	var iframe = document.getElementById('xformSearch');
	iframe.src=blankurl.replace(/{formName}/,selected.value);
}
</script>

<p>
select search form
<select id="selectSearchForm" onchange="javascript:selectXFormSearch(this);">
<option value="">Select a search form :</option>
<#list searchForms as item>
<option value="${item}">${item}</option>
</#list>
</select>
</p>

<iframe id="xformSearch" src=""></iframe>

