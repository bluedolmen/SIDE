<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.util.libs.ecore.EcoreHelper
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Page" name="createTemplates"%>
<%ID.toLowerCase().nPut("templates_name")%>
<%if (generate){%>
<%getProperty("alf.share.paths.core.templates")%><%nGet("templates_name")%>.js
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<%ID.toLowerCase().nPut("templates_name")%>
<%parent().name.nPut("site_name")%>
<%if (metainfo[key == "rawContentJsFilePath"]){%>
<%getFileContent(metainfo[key == "rawContentJsFilePath"].value)%>
<%}else if (metainfo[key == "rawContentJs"]){%>
<%metainfo[key == "rawContentJs"].multilineValue%>
<%}else{%>
script: {
	var connector = remote.connect("alfresco");

	// retrieve the web script response
	var ticket = connector.get("/com/bluexml/side/facetMap/ticket_user");
	
	if (ticket != null) {
		var res = ticket.getResponse();
		var obj = eval('('+res+')');	
		model.ticket = obj.ticket;
		model.userName = obj.userName;			
	}
	
	var siteID =page.url.templateArgs.site;
	model.siteID = siteID;

	/*
	var componentPropertyConf = unescape(args.conf);
	*/
}
<%}%>