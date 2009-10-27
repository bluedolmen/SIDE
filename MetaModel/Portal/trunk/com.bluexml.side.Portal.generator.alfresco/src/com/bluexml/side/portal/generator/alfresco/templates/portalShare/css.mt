<%
metamodel http://www.kerblue.org/portal/1.0
%>

<%-- CSS creation --%>
<%script type="Page" name="createPagesCssStyle"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("css_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("alf.share.paths.core.css")%><%nGet("css_name")%>/<%nGet("css_name")%>.css
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createPagesCssStyle%>"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("css_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
.yui-t1
{
   width: auto;
}
.yui-t1 #yui-main
{
   margin-left: -50em;
}
#frame1_<%nGet("css_name").substring(nGet("css_name").lastIndexOf("-") +1)%>
{
	width:35%;
	height:600px;
	margin:0;
	padding:0;
	border-width:0;
	border-style:none;
	align:left;
}
#frame2_<%nGet("css_name").substring(nGet("css_name").lastIndexOf("-") +1)%>
{
	width:45%;
	height:600px;
	margin:0;
	padding:0;
	border-width:0;
	border-style:none;
	align:left;
}
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>