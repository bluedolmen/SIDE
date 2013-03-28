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
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
webapps/share/searchFormCallBack.html
<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<html>
<head>
<title>edit-metadata call back page</title>
</head>

<body>
<script type="text/javascript">
/**
 * context is the alfresco window
 this code from edit-metadata-mgr.get.js
 */
function _navigateForward(context)
{	
	if (context.Alfresco) {
		var search=location.search;		
		var pageUrl = context.Alfresco.constants.URL_PAGECONTEXT + "site/" + context.Alfresco.constants.SITE+"/search"+search;
		context.location.href = pageUrl;
	}
}


var parent_=window;
if (window.parent) {
	parent_=window.parent;
}
	_navigateForward(parent_);

</script>
<p>Redirect ...</p>
</body>
</html>
