<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Page" name="createTemplates"%>
<%ID.toLowerCase().nPut("templates_name")%>
<%if (isDefaultSharePage() =="false"){%>
<%getProperty("alf.share.paths.core.templates")%><%nGet("templates_name")%>.ftl
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<%ID.toLowerCase().nPut("templates_name")%>
<%parent().name.nPut("site_name")%>
<#include "../../org/alfresco/include/alfresco-template.ftl" />
<@templateHeader>
  <@link rel="stylesheet" type="text/css" href="${url.context}/templates/<%nGet("templates_name")%>/<%nGet("templates_name")%>.css" />
</@>

<@templateBody>
   <div id="alf-hd">
      <@region id="header" scope="global" protected=true />
	  <@region id="title" scope="template" protected=true />
	  <@region id="navigation" scope="template" protected=true />
   </div>
   <div id="bd">
      <div class="yui-t1">
         <div id="yui-main"><%view()%></div>
      </div>
   </div>
</@>

<@templateFooter>
   <div id="alf-ft">
      <@region id="footer" scope="global" protected=true />
   </div>
</@>
<%script type="Page" name="view"%>
<%for (portlets){%>
	<%if (associationPortlet != null && associationPage != null){%>
		<%for (associationPortlet){%>
			<%if (isPortletInternal != null && isPortletInternal.view != null){%>
				<%for (isPortletInternal.view){%>
					<%if (current("view.FacetMap")){%>
<iframe src="/facetmap/browseSimple_facets.jsp?facetName=<%name%>&community=${siteID}&userName=${userName}&userTicket=${ticket}" id="facetsContainer" style="width: 600px; height: 600px"></iframe>
<iframe src="/facetmap/browseSimple_content.jsp?facetName=<%name%>&community=${siteID}&userName=${userName}&userTicket=${ticket}" id="resultsContainer" style="width: 600px; height: 600px"></iframe>
					<%}else{%>
<%-- TODO : manage other view types --%>
<!-- portlet <%current("Portlet").name%>-->
					<%}%>
				<%}%>
			<%}%>
		<%}%>
	<%}%>
<%}%>