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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext.modules")%>documentlibrary/change-type.get_ja.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Titles
title=\u30bf\u30a4\u30d7\u306e\u5909\u66f4

## Labels
label.type=\u65b0\u898f\u30bf\u30a4\u30d7
label.select=\u30bf\u30a4\u30d7\u306e\u9078\u629e...


## types
<%for (getAllClasses()[!abstract]){%>
type.<%getPrefixedQualifiedName().replaceAll(":", "_")%>=<%getLabel()%>
<%}%>

