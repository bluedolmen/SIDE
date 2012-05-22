<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common

%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/single-child-associations.properties<%}%>
<%script type="clazz.ClassPackage" name="generator" file="<%validatedFilename%>"%>
# configuration file for com.bluexml.side.Framework.alfresco.singlePrimaryChildAssociation module
# the default value is false for all types not in configuration, only Class with metainfo "single-child" is included in this file
<%if (getAllClasses()[metainfo.key == "single-child"].nSize() > 0) {%>
policy.enabled=true
<%}else{%>
policy.enabled=false
<%}%>
<%for (getAllClasses()[metainfo[key == "single-child"].nSize() == 1].nSort("name")){%>
<%if (!abstract){%>
<%generateEntry%>
<%}%>
<%for (getAllSubTypes()[!aspects].filter("clazz.Clazz")){%>
<%generateEntry%>
<%}%>
<%}%>
<%script type="Clazz" name="generateEntry"%>
<%getPrefixedQualifiedName().replaceFirst(":", "\\\\:")%>=true