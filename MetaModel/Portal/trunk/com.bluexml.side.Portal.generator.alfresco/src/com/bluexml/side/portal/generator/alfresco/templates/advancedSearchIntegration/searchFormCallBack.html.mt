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
		var pageUrl = context.Alfresco.constants.URL_PAGECONTEXT + "site/" + context.thisHeader.options.siteId+"/search"+search;
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
