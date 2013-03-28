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
label.isModerated=Sitio moderado
label.moderatedHelp=Los administradores del sitio pueden controlar qui\u00e9n se une al sitio
label.isPrivate=Privado
label.type=Tipo
message.failure=No se pudo crear el sitio
message.creating=Se est\u00e1 creando el sitio...
error.duplicateShortName=No se pudo crear el sitio porque la direcci\u00f3n URL ya est\u00e1 en uso
error.loggedOut=Su sesi\u00f3n de usuario se ha vencido, por favor inicie otra sesi\u00f3n y vuelva a intentar
title.collaborationSite=Sitio colaborativo
#SIDE generated sites
title.site-<%name%>Site=<%name%>-Site
