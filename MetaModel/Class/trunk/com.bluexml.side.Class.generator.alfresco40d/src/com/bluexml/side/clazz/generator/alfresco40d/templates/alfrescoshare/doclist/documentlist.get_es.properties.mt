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
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%--
  -- This file is a patched copy of Alfresco Share's
  -- Contains additional message for custom Types 
  --%>
  
<%script type="clazz.ClassPackage" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/documentlibrary/documentlist.get_es.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Buttons
button.sort.ascending=Orden ascendente
button.sort.descending=Orden descendente
button.folders.show=Mostrar carpetas
button.folders.hide=Ocultar carpetas

## Drop-down Menus
menu.select=Seleccionar
menu.select.all=Todos
menu.select.none=Ninguno
menu.select.invert=Invertir la selecci\u00f3n
menu.select.folders=Carpetas
menu.select.documents=Documentos

## Details Banners
details.banner.editing=Este documento est\u00e1 bloqueado por usted para edici\u00f3n fuera de l\u00ednea.
details.banner.lock-owner=Este documento est\u00e1 bloqueado por usted.
details.banner.locked=Este documento est\u00e1 bloqueado por {0} para edici\u00f3n.
details.banner.google-docs.link=Google Docs
details.banner.google-docs-owner=Este documento ha sido subido por usted a {0} para su edici\u00f3n.
details.banner.google-docs-locked=Este documento ha sido subido por {0} a {1} para su edici\u00f3n.

## Actions - moved to slingshot.properties
actions.more=M\u00e1s...

## Status Indicators
status.active-workflows=Pertenece a {0} flujos de trabajo activos
status.editing=Est\u00e1 siendo editado por usted
status.google-docs-editing=Est\u00e1 siendo editado por usted a trav\u00e9s de Google Docs&trade;
status.google-docs-owner=Bloqueado por usted para editar con Google Docs&trade;
status.google-docs-locked=Bloqueado por {0} ({1}) para editar con Google Docs&trade;
status.locked=Bloqueado por {0} ({1})
status.lock-owner=Bloqueado por usted
status.rules=Carpetas con reglas aplicadas
status.simple-workflow=Flujo de trabajo sencillo de Aprobaci\u00f3n/Rechazo aplicado
status.exif=Metadatos EXIF disponibles
status.geographic=Metadatos de geolocalizaci\u00f3n disponibles
status.transferred-node=Transferido desde otro repositorio

## Tips
tip.insitu-rename=Cambiar nombre
tip.insitu-tag=Etiqueta

## File Upload (upload new version)
label.filter-description=Mismo tipo que {0}

## Edit Details Dialog
edit-details.title=Editar propiedades: {0}
edit-details.label.edit-metadata=Todas las propiedades...

## Help panels, including Drag and Drop
no.items.title=No hay elementos de contenido
show.folders=Mostrar {0} subcarpeta(s)
standard.upload.title=\u00a1Cree su biblioteca!
standard.upload.description=Subir ficheros
dnd.drop.title=\u00a1Arrastre y suelte para subir ficheros!
dnd.drop.doclist.description=Arrastre ficheros desde su escritorio y su\u00e9ltelos aqu\u00ed para subirlos.
dnd.drop.folder.description=Puede soltar ficheros directamente en las carpetas.
dnd.upload.description=Subir ficheros
dnd.newfolder.description=Crear una carpeta
other.options=Tambi\u00e9n puede...
dnd.upload.tooltip=<p>Tambi\u00e9n puede arrastrar y soltar ficheros desde el escritorio a la biblioteca de documentos.</p>
<p>Si prefiere arrastrar y soltar, cierre el cargador.</p>


## SIDE view labels
<%for (getAllReferencedAttributes()){%>
<%getPrefixedQualifiedName().replaceAll(":", "_")%>=<%getLabel()%>
<%}%>
