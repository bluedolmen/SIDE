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
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
webapps/share/edit-metadataCallBack.html
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
	var alfersco_EditMetadataMsg=context.editMetadataMgr;
   /* Did we come from the document library? If so, then direct the user back there */
   if (context.document.referrer.match(/documentlibrary([?]|$)/))
   {
      // go back to the referrer page
      context.history.go(-2);
	  
   }
   else
   {
      // go back to the appropriate details page for the node
      var pageUrl = context.Alfresco.constants.URL_PAGECONTEXT + "site/" + alfersco_EditMetadataMsg.options.siteId + 
         "/" + alfersco_EditMetadataMsg.options.nodeType + "-details?nodeRef=" + alfersco_EditMetadataMsg.options.nodeRef;

      context.location.href = pageUrl;
   }
}


if (parent.location.pathname.indexOf("edit-metadata") !=-1) {
	_navigateForward(parent);
}
</script>
<p>Redirect ...</p>
</body>
</html>
