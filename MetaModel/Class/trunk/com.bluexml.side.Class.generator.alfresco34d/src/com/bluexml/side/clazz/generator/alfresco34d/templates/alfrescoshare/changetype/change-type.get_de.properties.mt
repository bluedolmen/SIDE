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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext.modules")%>documentlibrary/change-type.get_de.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Titles
title=Typ \u00e4ndern

## Labels
label.type=Neuer Typ
label.select=Typ w\u00e4hlen...

## types
<%for (getAllClasses()[!abstract]){%>
type.<%getPrefixedQualifiedName().replaceAll(":", "_")%>=<%getLabel()%>
<%}%>

