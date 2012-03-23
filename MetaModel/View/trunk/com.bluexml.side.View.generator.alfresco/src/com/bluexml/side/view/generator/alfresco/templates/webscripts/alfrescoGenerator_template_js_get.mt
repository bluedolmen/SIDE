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
metamodel http://www.kerblue.org/view/1.0

import com.bluexml.side.view.generator.alfresco.templates.services.common

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="view.AbstractViewOf" name="validatedFilename"%>
<%if (eContainer() == getRootContainer()){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/data/<%viewOf.getPrefixedQName("_")%>/<%name%>/<%name%>.get.js
<%}else if (eContainer().filter("ComposedView") != null){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/data/<%viewOf.getPrefixedQName("_")%>/<%eContainer().filter("ComposedView").name%>/<%eContainer().filter("ComposedView").name%>.get.js
<%}%>
<%script type="view.AbstractViewOf" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<import resource="classpath:/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/doclistSearch.lib.js">

model.csvSeparator = ";";

if (args.q || args.t) {
	// use search parameters
	var params = {};
	if (args.savedSearchNodeRef) {
		params = getSavedSearchQueryDef(args.savedSearchNodeRef);
	} else {
		// get search args
		params = {
			siteId : (args.site !== null) ? args.site : null,
			containerId : (args.container !== null) ? args.container : null,
			repo : (args.repo !== null) ? (args.repo == "true") : false,
			term : (args.term !== null) ? args.term : null,
			tag : (args.tag !== null) ? args.tag : null,
			query : (args.query !== null) ? args.query : null,
			sort : (args.sort !== null) ? args.sort : null,
			maxResults : -1
		};

	}
	var queryDef = getSearchDef(params);
	model.nse_DocumentEmis_list = search.query(queryDef);

} else {
	var myNode = null;
	if (argsM["nodeRef"] != null) {
		var myNodeRef="workspace://SpacesStore/"+argsM["nodeRef"][0];
		myNode = search.findNode(myNodeRef);
		var xpath = "./*[subtypeOf('<%viewOf.getPrefixedQName()%>')]";
		model.records = myNode.childrenByXPath(xpath);
	} else {
		var lucene="TYPE:\"<%viewOf.getPrefixedNamespaceQName()%>\"";
    	model.records = search.luceneSearch(lucene);
	}
	
	if (myNode != null) {
  		model.<%viewOf.getPrefixedQName("_")%>_list = myNode.childrenByXPath("./*[subtypeOf('<%viewOf.getPrefixedQName()%>')]");
	} else {
  		var lucene="TYPE:\"<%viewOf.getPrefixedNamespaceQName()%>\"";
  		model.<%viewOf.getPrefixedQName("_")%>_list = search.luceneSearch(lucene);
	}

}

