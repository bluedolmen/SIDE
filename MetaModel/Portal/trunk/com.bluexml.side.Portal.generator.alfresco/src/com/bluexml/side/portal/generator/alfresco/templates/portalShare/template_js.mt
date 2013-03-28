<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
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
