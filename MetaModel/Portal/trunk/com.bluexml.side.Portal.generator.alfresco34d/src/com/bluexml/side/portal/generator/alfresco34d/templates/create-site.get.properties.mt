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
error.loggedOut=You user session has timed out, please login and try again again
title.collaborationSite=Collaboration Site
#SIDE generated sites
title.site-<%name%>Site=<%name%> Site
