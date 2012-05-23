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