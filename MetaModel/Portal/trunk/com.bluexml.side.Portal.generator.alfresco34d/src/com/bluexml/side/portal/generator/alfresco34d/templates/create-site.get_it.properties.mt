<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.service.ShareGeneratorServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createMessages"%>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.web-ext.alf.modules")%>/create-site.get_it.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
header.createSite=Crea sito
label.shortName=Nome URL
label.shortNameHelp=Fa parte dell'URL del sito come in<br/>\
 http://domain.com/share/page/site/&lt;URL Name&gt;/dashboard<br/>\
 Non utilizzare spazi o caratteri speciali.
label.access=Visibilit\u00e0
label.isPublic=Pubblico
label.isModerated=Appartenenze al sito moderate
label.moderatedHelp=I manager del sito possono controllare chi partecipa
label.isPrivate=Privato
label.type=Tipo
message.failure=Impossibile creare il sito
message.creating=Creazione del sito in corso...
error.duplicateShortName=Impossibile creare il sito in quanto l'URL \u00e8 gi\u00e0 utilizzato
error.loggedOut=La sessione utente \u00e8 scaduta, effettuare di nuovo il login e riprovare
title.collaborationSite=Sito di collaborazione
#SIDE generated sites
title.site-<%name%>Site=Sito <%name%>