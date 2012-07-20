<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
 <%
metamodel http://www.kerblue.org/class/1.0

import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("alf.paths.extension.side.webscripts")%>/<%getFolder()%>/<%getQualifiedName().replaceAll("_","/")%>/<%getQualifiedName()%>.get.js
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
var myNode = null;
if (argsM["nodeRef"] != null) {
	var myNodeRef="workspace://SpacesStore/"+argsM["nodeRef"][0];
	myNode = search.findNode(myNodeRef);
	var xpath = "./*[subtypeOf('<%getPrefixedQName()%>')]";
	model.records = myNode.childrenByXPath(xpath);
} else {
	var lucene="TYPE:\"<%getPrefixedNamespaceQName()%>\"";
    model.records = search.luceneSearch(lucene);
}

if (myNode != null) {
<%for (getAllLinkedClasses().nSort("name")){%>
<%if (getFolder() == current(1).getRootContainer().name){%>
  model.<%getQualifiedName()%>_list = myNode.childrenByXPath("./*[subtypeOf('<%getPrefixedQName()%>')]");
<%}%>
<%}%>
} else {
<%for (getAllLinkedClasses().nSort("name")){%>
<%if (getFolder() == current(1).getRootContainer().name){%>
  var lucene="TYPE:\"<%getPrefixedNamespaceQName()%>\"";
  model.<%getQualifiedName()%>_list = search.luceneSearch(lucene);
<%}%>
<%}%>
}