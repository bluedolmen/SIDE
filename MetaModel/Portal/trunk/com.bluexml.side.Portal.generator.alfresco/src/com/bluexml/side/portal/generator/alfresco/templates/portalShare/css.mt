<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>

<%-- CSS creation --%>
<%script type="Page" name="createPagesCssStyle"%>
<%ID.toLowerCase().nPut("css_name")%>
<%if (isDefaultSharePage() =="false"){%>
<%getProperty("alf.share.paths.core.css")%><%nGet("css_name")%>/<%nGet("css_name")%>.css
<%}%>

<%script type="Page" name="alfrescoGenerator" file="<%createPagesCssStyle%>"%>
<%ID.toLowerCase().nPut("css_name")%>
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