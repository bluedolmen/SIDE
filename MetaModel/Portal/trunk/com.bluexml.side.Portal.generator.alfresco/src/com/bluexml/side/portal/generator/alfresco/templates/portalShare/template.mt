<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Page" name="createTemplates"%>
<%ID.toLowerCase().nPut("templates_name")%>
<%if (generate){%>
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
         <div id="yui-main">
         	<%view()%>         
         </div>
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
					<%if (getGeneratorOptionValue("com.bluexml.side.Portal.generator.alfresco.forms") && current("view.FacetMap")){%>
					<@region scope="template" id="<%current("Portlet").name%>" protected=true/>
					<%}else{%>
<%-- TODO : manage other view --%>
<!-- portlet <%current("Portlet").name%>-->
					<%}%>
				<%}%>
			<%}else{%>
				<!-- use default share components-->
				<@region 
				<%for (isInstanceOfPortletType.instances){%>
					<%if (instanceOf.name == "scope"){%>
					<%instanceOf.name%>="<%value%>" 
					<%}%>
				<%}%>
					id="<%name%>"
				 	protected=true />
			<%}%>
		<%}%>
	<%}%>
<%}%>