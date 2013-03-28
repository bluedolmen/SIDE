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
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/html-upload.get_es.properties
header.singleUpload=A\u00f1adir fichero
header.singleUpdate=Actualizar fichero

label.singleUploadTip=Con la \u00faltima versi\u00f3n instalada de Flash Player, puede subir m\u00faltiples ficheros. Desc\u00e1rguelo del\
   <a href="http://www.adobe.com/go/getflashplayer">Centro de Descargas de Adobe Flash Player</a>.

label.singleUpdateTip=Subir una nueva versi\u00f3n de {0}
button.upload=A\u00f1adir fichero

section.file=Seleccionar fichero
label.file=Fichero

section.version=Informaci\u00f3n de la versi\u00f3n
label.version=Esta versi\u00f3n tiene:
label.minorVersion=cambios menores
label.majorVersion=cambios mayores
label.minorVersion.more=cambios menores ({0})
label.majorVersion.more=cambios mayores ({0})
label.comments=Comentarios

message.uploading=Documento en proceso de subida...
message.success=Documento subido con \u00e9xito
message.failure=No se pudo subir el documento
message.failure.413=Cuota excedida
message.illegalCharacters=Los nombres de fichero no pueden contener caracteres no admitidos por los sistemas operativos.

type.cm_content=Contenido
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%><%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
