<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.service.ShareGeneratorServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createMessages"%>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.web-ext.alf.modules")%>/create-site.get_es.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
header.createSite=Crear un sitio
label.shortName=Nombre de URL
label.shortNameHelp=Esto es parte de la URL del sitio, tal como<br/>\
 http://domain.com/share/page/site/&lt;URL Name&gt;/dashboard<br/>\
 No utilice espacios o caracteres especiales.
label.access=Visibilidad
label.isPublic=P\u00fablico
label.isModerated=Moderada adhesi\u00f3n al sitio
label.moderatedHelp=Los administradores del sitio pueden controlar qui\u00e9n se une al sitio
label.isPrivate=Privado
label.type=Tipo
message.failure=No se pudo crear el sitio
message.creating=Se est\u00e1 creando el sitio...
error.duplicateShortName=No se pudo crear el sitio porque la direcci\u00f3n URL ya est\u00e1 en uso
error.loggedOut=Su sesi\u00f3n de usuario ha caducado, por favor inicie otra sesi\u00f3n y vuelva a intentar
title.collaborationSite=Sitio colaborativo
#SIDE generated sites
title.site-<%name%>Site=Sitio <%name%>