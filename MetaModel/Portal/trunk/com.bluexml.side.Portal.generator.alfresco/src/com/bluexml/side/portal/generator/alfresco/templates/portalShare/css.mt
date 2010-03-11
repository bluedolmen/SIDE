<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>

<%-- CSS creation --%>
<%script type="Page" name="createPagesCssStyle"%>
<%ID.toLowerCase().nPut("css_name")%>
<%if (generate){%>
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