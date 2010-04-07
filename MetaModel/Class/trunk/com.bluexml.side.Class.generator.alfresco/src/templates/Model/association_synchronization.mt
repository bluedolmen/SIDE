<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common

%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/association-synchronization.properties<%}%>
<%script type="clazz.ClassPackage" name="generator" file="<%validatedFilename%>"%>

<%for (getAllAssociations().nSort("name")){%>
<%if (firstEnd.navigable && secondEnd.navigable){%>
<%getQualifiedName(firstEnd)%>=<%getQualifiedName(secondEnd)%>
<%}%>
<%}%>

