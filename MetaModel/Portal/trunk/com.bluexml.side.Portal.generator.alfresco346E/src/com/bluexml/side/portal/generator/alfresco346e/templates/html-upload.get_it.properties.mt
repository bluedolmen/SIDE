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
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/html-upload.get_it.properties
header.singleUpload=Carica file
header.singleUpdate=Aggiorna file

label.singleUploadTip=Dopo aver installato l'ultima versione di Flash Player, \u00e8 possibile caricare pi\u00f9 file. \
  Per scaricarlo, visitare il sito <a href="http://www.adobe.com/go/getflashplayer">Adobe Flash Player Download Center</a>.

label.singleUpdateTip=Carica una nuova versione di {0}
button.upload=Carica file

section.file=Scegli file
label.file=File

section.version=Informazioni sulla versione
label.version=Questa versione dispone di:
label.minorVersion=cambiamenti minori
label.majorVersion=cambiamenti maggiori
label.minorVersion.more=cambiamenti minori ({0})
label.majorVersion.more=cambiamenti maggiori ({0})
label.comments=Commenti

message.uploading=Caricamento del documento in corso...
message.success=Documento caricato
message.failure=Impossibile caricare il documento
message.failure.413=Quota superata
message.illegalCharacters=I nomi file non devono contenere caratteri non validi in qualsiasi sistema operativo.

type.cm_content=Contenuto
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%><%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
