<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices

%>

<%-- Templates creation --%>
<%script type="Portlet" name="createTemplates"%>
<%if (name.toLowerCase() =="edit-metadata-XFrom"){%>
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
style="width: 600px; height: 600px;border:none;"></iframe>
<%if (current() == current("PortletInternal").form.forms.nLast()){%>
</#if>
<%}%>
<%}%>


<%}%>

<%}%>






