<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.service.ShareGeneratorServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createMessages"%>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.web-ext.alf.modules")%>/create-site.get_de.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
header.createSite=Site erstellen
label.shortName=URL Name
label.shortNameHelp=Das ist Bestandteil der URL der Site wie <br/>\
 http://domain.com/share/page/site/&lt;URL Name&gt;/dashboard<br/>\
 Keine Leer- oder Sonderzeichen verwenden.
label.access=Sichtbarkeit
label.isPublic=\u00d6ffentlich
label.isModerated=Moderierte Site Mitgliedschaft
label.moderatedHelp=Site Manager k\u00f6nnen kontrollieren, wer dieser Site beitritt
label.isPrivate=Privat
label.type=Typ
message.failure=Site konnte nicht erstellt werden
message.creating=Site wird erstellt...
error.duplicateShortName=Site konnte nicht erstellt werden, da URL bereits verwendet wird
error.loggedOut=Ihre Sitzung ist abgelaufen, bitte melden Sie sich neu an und versuchen Sie es erneut.
title.collaborationSite=Collaboration-Site
#SIDE generated sites
title.site-<%name%>Site=<%name%>-Site