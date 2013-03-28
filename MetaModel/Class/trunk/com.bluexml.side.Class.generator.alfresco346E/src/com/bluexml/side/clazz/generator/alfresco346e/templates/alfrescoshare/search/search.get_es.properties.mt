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
metamodel http://www.kerblue.org/class/1.0

import templates.alfrescoshare.uploadForm.file-upload-js-get-lib
import templates.servicesTemplates.Common
%>

<%--
  -- This file is a patched copy of Alfresco Share's
  -- Contains additional message for custom Types 
  --%>
  
<%script type="clazz.ClassPackage" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/search/search.get_es.properties<%}%>
message.loading=Cargando...
message.empty=Ning\u00fan resultado encontrado
message.error=Error durante la b\u00fasqueda
message.insite=en el sitio
message.ofsize=de tama\u00f1o
message.modifiedby=modificado por
message.modifiedon=modificado:
message.preview=Previsualizar
message.singlesite=Sitio {0}
message.allsites=Todos los sitios
message.infolderpath=En carpeta
message.repository=Repositorio

label.folder=Carpeta
label.document=Documento
label.blogpost=Entrada del blog
label.forumpost=Tema del foro
label.calendarevent=Evento de calendario
label.wikipage=P\u00e1gina wiki
label.link=Enlace
label.datalist=Listas de datos
label.datalistitem=Elemento de Lista de Datos
label.unknown=Desconocida
label.sortby=Clasificar por: {0}
label.download=Descargar
label.viewinbrowser=Ver en el navegador

button.search=Buscar

search.info.resultinfo={0} resultado(s)
search.info.resultinfomore=M\u00e1s de {0} resultados
search.info.searching=Buscando...
search.info.foundinsite=se encontr\u00f3 en el sitio {0}.
search.info.foundinallsite=se encontr\u00f3 en todos los sitios.
search.info.foundinrepository=se encontr\u00f3 en el repositorio.

search.sort.relevance=Relevancia
search.sort.size=Tama\u00f1o
search.sort.mimetype=Tipo MIME
search.sort.type=Tipo

pagination.template={PreviousPageLink} {PageLinks} {NextPageLink}
pagination.template.page-report=

## Custom Types
<%for (getAllAbstractClasses().nSort("name")){%>
### <%getFullName()%>
<%for (getSortedAttibutes()){%>
<%getQualifiedName%>=<%getLabel()%>
<%}%>
<%}%>
