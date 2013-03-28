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
import com.bluexml.side.clazz.service.alfresco.ClassServices

%>

<%-- Templates creation --%>
<%script type="Portlet" name="createTemplates"%>
<%if (name.toLowerCase() =="edit-metadata-xform"){%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/com/bluexml/components/Xform/Xform.get.html.ftl
<%}%>

<%script type="Portlet" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<%for (isPortletInternal){%>
<%if (form != null){%>
<%for (form.forms){%>
<%if (current() == current("PortletInternal").form.forms.nFirst()){%>
<#if<%}else{%>
<#elseif<%}%> form.type == "<%filter("form.FormClass").real_class.getPrefixedQName()%>" >
<iframe src="<%getXFORMURL()%>/xforms?type=<%id%>&id=${nodeRef}&formType=form&nextPageSubmit=<%getSHAREURL()%>/edit-metadataCallBack.html"
 id="xformEdit"></iframe>
<%if (current() == current("PortletInternal").form.forms.nLast()){%>
</#if>
<%}%>
<%}%>


<%}%>

<%}%>
