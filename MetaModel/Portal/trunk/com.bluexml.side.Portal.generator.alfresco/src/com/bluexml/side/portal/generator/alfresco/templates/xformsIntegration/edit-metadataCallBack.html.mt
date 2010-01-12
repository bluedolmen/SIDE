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