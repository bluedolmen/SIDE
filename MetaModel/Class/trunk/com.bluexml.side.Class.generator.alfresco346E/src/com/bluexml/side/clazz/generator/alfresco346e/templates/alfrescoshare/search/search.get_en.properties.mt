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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/search/search.get_en.properties<%}%>
message.loading=Loading...
message.empty=No results found
message.error=Error during search
message.insite=in site
message.ofsize=of size
message.modifiedby=modified by
message.modifiedon=modified on
message.preview=Preview
message.singlesite={0} Site
message.allsites=All Sites
message.infolderpath=In folder
message.repository=Repository

label.folder=Folder
label.document=Document
label.blogpost=Blog Post
label.forumpost=Forum Topic
label.calendarevent=Calendar Event
label.wikipage=Wiki Page
label.link=Link
label.datalist=Data List
label.datalistitem=Data List Item
label.unknown=Unknown
label.sortby=Sort by: {0}
label.download=Download
label.viewinbrowser=View In Browser

button.search=Search

search.info.resultinfo={0} result(s)
search.info.resultinfomore=More than {0} results
search.info.searching=Searching...
search.info.foundinsite=found in {0} site.
search.info.foundinallsite=found in all sites.
search.info.foundinrepository=found in repository.

search.sort.relevance=Relevance
search.sort.size=Size
search.sort.mimetype=Mimetype
search.sort.type=Type

pagination.template={PreviousPageLink} {PageLinks} {NextPageLink}
pagination.template.page-report=

## Custom Types
<%for (getAllAbstractClasses().nSort("name")){%>
### <%getFullName()%>
<%for (getSortedAttibutes()){%>
<%getQualifiedName%>=<%getLabel()%>
<%}%>
<%}%>
