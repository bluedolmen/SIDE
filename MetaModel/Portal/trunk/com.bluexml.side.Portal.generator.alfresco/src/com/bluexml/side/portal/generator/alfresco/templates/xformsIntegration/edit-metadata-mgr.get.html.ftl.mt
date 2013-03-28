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
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/org/alfresco/components/edit-metadata/edit-metadata-mgr.get.html.ftl

<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<script type="text/javascript">//<![CDATA[
   var editMetadataMgr=new Alfresco.EditMetadataMgr("${args.htmlid}");
   editMetadataMgr.setOptions(
   {
      nodeRef: "${page.url.args.nodeRef!}",
      nodeType: "${nodeType}",
      siteId: "${page.url.templateArgs.site!""}"
   }).setMessages(
      ${messages}
   );
//]]></script>

<div class="edit-metadata-mgr">
   <div class="heading">${msg("edit-metadata-mgr.heading")}</div>
</div>
