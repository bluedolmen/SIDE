<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common

%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/association-synchronization.properties<%}%>
<%script type="clazz.ClassPackage" name="generator" file="<%validatedFilename%>"%>

<%for (getAllAssociations().nSort("name")){%>
<%if (firstEnd.navigable && secondEnd.navigable){%>
<%getPrefixedQualifiedName(firstEnd)%>=<%getPrefixedQualifiedName(secondEnd)%>
<%}%>


<%for (metainfo[key == "synchro-association"]){%>
<%if (current("Association").firstEnd.navigable && current("Association").secondEnd.navigable){%>
# syncho-association on two way association is not Implemented ! (<%current("Association")%>)
<%}else{%>
<%current("Association").getPrefixedQualifiedName(current("Association").getSource())%>=<%EObjectValue.filter("Association").getPrefixedQualifiedName(EObjectValue.filter("Association").getSource())%>
<%}%>

<%}%>


--%>
<%}%>

