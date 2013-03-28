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
<option value="${item.id}">${item.label}</option>
</#list>
</select>
</p>

<iframe id="xformSearch" src=""></iframe>

