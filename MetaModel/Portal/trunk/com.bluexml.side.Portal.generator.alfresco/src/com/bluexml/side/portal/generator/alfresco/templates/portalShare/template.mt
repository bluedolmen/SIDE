<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.portal.generator.alfresco.service.ShareGeneratorServices
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
<%if (metainfo[key == "rawFtlFilePath"]){%>
<%getFileContent(metainfo[key == "rawFtlFilePath"].value)%>
<%-- load external files content and add it here --%>
<%}else if (metainfo[key == "rawContent"]){%>
<%metainfo[key == "rawContent"].multilineValue%>
<%}else{%>
<%for (useLayout.columns){%>
<%generateLayout()%>
<%}%>
<%}%>
<%script type="Column" name="generateLayout_sub"%>
<%current("Page").view(current())%>
<%for (subColumns){%>
<%generateLayout()%>
<%}%>
<%script type="Column" name="generateLayout"%>
<%if (metainfo[key == "tag"]){%>
	<%if (metainfo[key == "tag"].value != null && metainfo[key == "tag"].value != ""){%>
		<%if (metainfo[key == "tag"].value.startsWith("@")){%>
<<%metainfo[key == "tag"].value%>>
	<%generateLayout_sub()%>
</@>
		<%}else{%>
<<%metainfo[key == "tag"].value%><%for (metainfo){%> <%key%>="<%value%>"<%}%>>
	<%generateLayout_sub()%>
</<%metainfo[key == "tag"].value%>>
		<%}%>
	<%}else{%>
<%generateLayout_sub()%>
	<%}%>
<%}else if(metainfo[key == "rawContent"].nSize() > 0){%>
<%metainfo[key == "rawContent"].multilineValue%>
<%}else{%>
<div id="<%name%>"<%for (metainfo){%> <%key%>="<%value%>"<%}%>>
	<%generateLayout_sub()%>
</div>
<%}%>
<%script type="Page" name="view" post="trim()" %>
<%for (getOrderedHaveProtlets(args(0).filter("Column")).filter("HavePortlet")){%>
	<%if (associationPortlet != null && associationPage != null){%>
		<%for (associationPortlet){%>
			<%if (isPortletInternal != null && (isPortletInternal.view != null || isPortletInternal.form != null)){%>
				<%for (isPortletInternal.view){%>
						<%if (current("view.FacetMap")){%>
            <@region scope="template" id="<%current("portal.Portlet").name%>" protected=true/>
						<%}%>
				<%}%>
				<%for (isPortletInternal.form){%>
					<%if (current("Portlet").name.toLowerCase() =="advanced-search"){%>
						<%if (current("form.SearchFormCollection")){%>
            <@region scope="template" id="advanced-search" protected=true/>
						<%}%>
					<%}%>
				<%}%>
			<%}else if (metainfo[key == "rawContentFilePath"]){%>
			<%-- load external files content and add it here --%>
<%getFileContent(metainfo[key == "rawContentFilePath"].value)%>
			<%}else if (metainfo[key == "rawContent"]){%>
			<%metainfo[key == "rawContent"].multilineValue%>
			<%}else{%><%-- use default share components --%>
            <@region 
				<%for (isInstanceOfPortletType.instances){%>
					<%if (instanceOf.name == "scope"){%>
               <%instanceOf.name%>="<%value%>" 
					<%}%>
				<%}%>
               id=<%if (current("HavePortlet").metainfo[key == "region-id"].nSize() > 0){%><%current("HavePortlet").metainfo[key == "region-id"].value%><%}else{%><%if (name.indexOf("\"") == -1){%>"<%name%>"<%}else{%><%name%><%}%><%}%>
               protected=true />
			<%}%>
		<%}%>
	<%}%>
<%}%>
