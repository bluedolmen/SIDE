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
