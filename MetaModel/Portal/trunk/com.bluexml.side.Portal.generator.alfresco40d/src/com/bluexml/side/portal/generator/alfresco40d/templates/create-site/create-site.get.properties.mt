<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.service.ShareGeneratorServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createMessages"%>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.web-ext.alf.modules")%>/create-site.get.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
header.createSite=Create Site
label.shortName=URL Name
label.shortNameHelp=This is part of the site URL such as<br/>\
 http://domain.com/share/page/site/&lt;URL Name&gt;/dashboard<br/>\
 Do not use spaces or special characters.
label.access=Visibility
label.isPublic=Public
label.isModerated=Moderated site membership
label.moderatedHelp=Site managers can control who joins the site
label.isPrivate=Private
label.type=Type
message.failure=Could not create site
message.creating=Site is being created...
error.duplicateShortName=Could not create site since the URL is already used
error.loggedOut=Your user session has timed out, please login and try again
title.collaborationSite=Collaboration Site
#SIDE generated sites
title.site-<%name%>Site=<%name%>-Site