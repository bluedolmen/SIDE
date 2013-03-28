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
<%getProperty("alf.share.paths.web-ext.alf.modules")%>/create-site.get_fr.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
header.createSite=Cr\u00e9er un site
label.shortName=Nom de l'URL
label.shortNameHelp=Fait partie de l'URL du site, comme <br/>\
 http://domain.com/share/page/site/&lt;URL Name&gt;/dashboard<br/>\
 Do not use spaces or special characters.
label.access=Visibilit\u00e9
label.isPublic=Public
label.isModerated=Site mod\u00e9r\u00e9
label.moderatedHelp=Les coordinateurs du site peuvent contr\u00f4ler qui rejoint le site
label.isPrivate=Priv\u00e9
label.type=Type
message.failure=\u00c9chec de la cr\u00e9ation du site
message.creating=Site en cours de cr\u00e9ation...
error.duplicateShortName=\u00c9chec de la cr\u00e9ation du site car cette URL est d\u00e9j\u00e0 utilis\u00e9e
error.loggedOut=Votre session a expir\u00e9, veuillez vous reconnecter et r\u00e9essayer
title.collaborationSite=Site de Collaboration
#SIDE generated sites
title.site-<%name%>Site=<%name%> Site
