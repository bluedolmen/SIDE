<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.ClazzService
%>
<%-- Must be genereated using Portal generator to choose the view --%>

<%script type="Portal" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/customViews.ftl<%}%>

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (portletSet){%>
<%if (name == "DocumentLibraryDocList" && isPortletInternal != null && isPortletInternal.view != null) {%>
<%for (isPortletInternal.view.getInnerView()){%>
<#if item.asset.type == "{<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getNameSpace()%>}<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getQualifiedName()%>">
	<#include "doclist_views/doclist_<%filter("view.AbstractViewOf").name%>.ftl">
</#if>
<%}%>
<%}%>
<%}%>