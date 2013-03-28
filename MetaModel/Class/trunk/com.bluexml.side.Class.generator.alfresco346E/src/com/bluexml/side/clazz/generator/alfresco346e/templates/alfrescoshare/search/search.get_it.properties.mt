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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/search/search.get_it.properties<%}%>
message.loading=Caricamento in corso...
message.empty=Nessun risultato trovato
message.error=Errore durante la ricerca
message.insite=nel sito
message.ofsize=di dimensioni
message.modifiedby=modificato da
message.modifiedon=modificato:
message.preview=Anteprima
message.singlesite=Sito {0}
message.allsites=Tutti i siti
message.infolderpath=Nella cartella
message.repository=Repository

label.folder=Cartella
label.document=Documento
label.blogpost=Post del blog
label.forumpost=Argomento del forum
label.calendarevent=Evento del calendario
label.wikipage=Pagina wiki
label.link=Link
label.datalist=Elenco dati
label.datalistitem=Elemento elenco dati
label.unknown=Sconosciuto
label.sortby=Ordina per: {0}
label.download=Download
label.viewinbrowser=Vedi nel browser

button.search=Cerca

search.info.resultinfo={0} risultato/i
search.info.resultinfomore=Pi\u00f9 di {0} risultati
search.info.searching=Ricerca in corso...
search.info.foundinsite=trovato/i nel sito {0}.
search.info.foundinallsite=trovato/i in tutti i siti.
search.info.foundinrepository=trovato/i nel repository.

search.sort.relevance=Pertinenza
search.sort.size=Dimensioni
search.sort.mimetype=Mimetype
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
