<%
metamodel http://www.kerblue.org/class/1.0

import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%--
  -- This file is a patched copy of Alfresco Share's
  -- Contains additional message for custom Types 
  --%>
  
<%script type="clazz.ClassPackage" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%><%name%>/messages/slingshot.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Data Dictionary
# Aspects
<%for (getAllAspects()){%>
aspect.<%getPrefixedQName("_")%>=<%getLabel()%>
<%}%>