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
<%getProperty("alf.share.paths.web-ext.alf.modules")%>/create-site.get_nl.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
header.createSite=Site maken
label.shortName=URL-naam
label.shortNameHelp=Dit is een onderdeel van de site-URL, zoals<br/>\
 http://domein.com/share/page/site/&lt;URL-naam&gt;/dashboard<br/>\
 Gebruik geen spaties of speciale tekens.
label.access=Zichtbaarheid
label.isPublic=Openbaar
label.isModerated=Lidmaatschap van site onder toezicht
label.moderatedHelp=Sitemanagers bepalen wie lid mag worden van de site
label.isPrivate=Priv\u00e9
label.type=Type
message.failure=Kan site niet maken
message.creating=Site wordt gemaakt...
error.duplicateShortName=Kan site niet maken omdat de URL al in gebruik is
error.loggedOut=Er is een time-out opgetreden voor uw gebruikerssessie, meld u aan en probeer het opnieuw
title.collaborationSite=Samenwerkingssite
#SIDE generated sites
title.site-<%name%>Site=<%name%>-Site
