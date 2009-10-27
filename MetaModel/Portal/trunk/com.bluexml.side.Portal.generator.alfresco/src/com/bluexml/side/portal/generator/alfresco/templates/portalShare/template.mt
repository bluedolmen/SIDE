<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Page" name="createTemplates"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("templates_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("alf.share.paths.core.templates")%><%nGet("templates_name")%>.ftl
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
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
					<%if (current().startsWith("view.FacetMap")){%>
<iframe name="frame1_<%nGet("templates_name").substring(nGet("templates_name").lastIndexOf("-") +1)%>" id="frame1_<%nGet("templates_name").substring(nGet("templates_name").lastIndexOf("-") +1)%>" src="${url.context}/../facetmap-facets-<%nGet("templates_name").substring(nGet("templates_name").lastIndexOf("-") +1)%>/browseSimple.jsp"></iframe>
<iframe name="frame2_<%nGet("templates_name").substring(nGet("templates_name").lastIndexOf("-") +1)%>" id="frame2_<%nGet("templates_name").substring(nGet("templates_name").lastIndexOf("-") +1)%>" src="${url.context}/../facetmap-content-<%nGet("templates_name").substring(nGet("templates_name").lastIndexOf("-") +1)%>/browseSimple.jsp"></iframe>
					<%}else{%>
<table>
						<%for (children){%>
<tr>
<td><%mapTo.filter("common.NamedModelElement").getFolder()%>:<%mapTo.filter("common.NamedModelElement").getQualifiedName()%></td>
</tr>
						<%}%>
</table>
					<%}%>
				<%}%>
			<%}%>
		<%}%>
	<%}%>
<%}%>