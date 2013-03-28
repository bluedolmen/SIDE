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
