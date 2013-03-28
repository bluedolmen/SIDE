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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/search/search.get_de.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
message.loading=Laden...
message.empty=Keine Ergebnisse gefunden
message.error=Fehler w\u00e4hrend der Suche
message.insite=In Site
message.ofsize=der Gr\u00f6\u00dfe
message.modifiedby=ge\u00e4ndert von
message.modifiedon=ge\u00e4ndert am
message.preview=Vorschau
message.singlesite={0} Site
message.allsites=Alle Sites
message.infolderpath=In Ordner
message.repository=Repository

label.folder=Ordner
label.document=Dokument
label.blogpost=Blog Post
label.forumpost=Forum Thema
label.calendarevent=Kalenderereignis
label.wikipage=Wiki Seite
label.link=Link
label.datalist=Datenliste
label.datalistitem=Element Datenliste
label.unknown=Unbekannt
label.sortby=Sortieren nach: {0}
label.download=Herunterladen
label.viewinbrowser=Im Browser anzeigen

button.search=Suche

search.info.resultinfo={0} Ergebnis(se)
search.info.resultinfomore=Mehr als {0} Ergebnisse
search.info.searching=Suche...
search.info.foundinsite=gefunden in Site {0}
search.info.foundinallsite=in allen Sites gefunden.
search.info.foundinrepository=im Repository gefunden.

search.sort.relevance=Relevanz
search.sort.size=Gr\u00f6\u00dfe
search.sort.mimetype=MimeType
search.sort.type=Typ

pagination.template={PreviousPageLink} {PageLinks} {NextPageLink}
pagination.template.page-report=

## Custom Types
<%for (getAllAbstractClasses().nSort("name")){%>
### <%getFullName()%>
<%for (getSortedAttibutes()){%>
<%getQualifiedName%>=<%getLabel()%>
<%}%>
<%}%>
