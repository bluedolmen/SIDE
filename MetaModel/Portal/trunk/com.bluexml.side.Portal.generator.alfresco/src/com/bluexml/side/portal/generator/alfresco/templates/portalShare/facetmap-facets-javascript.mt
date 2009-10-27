<%
metamodel http://www.kerblue.org/portal/1.0
%>

<%script type="Page" name="facetmapfacetsbuild"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("javascript_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("facetmap.webapps")%>facetmap-facets-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>/xsl/display/browseSimple.js
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="facetmapGenerator" file="<%facetmapfacetsbuild%>" post="trim()"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("javascript_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
function getHost(){
	return self.document.location.hostname+":"+self.document.location.port;
}

function show_selection(ref){
	var url = ref;
	var el_2 = parent.frame2_<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>.document.getElementById('results-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>'); 
	if(el_2){
		parent.frame2_<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>.document.location.replace(url);
	}
	else{
		alert('Erreur1');
	}
}

/*
function update_facets(){
	var el_1 = self.document.getElementById('facets-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>');
	if(el_1){
		self.document.location.replace('http://'+getHost()+'/facetmap-facets-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>/browseSimple-update.jsp');
	}
	else{
		alert('Erreur2');
	}
}
*/

function update_facets_sql(){
	var el_1 = self.document.getElementById('facets-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>');
	if(el_1){
		self.document.location.replace('http://'+getHost()+'/facetmap-facets-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>/browseSimple-update-SQL.jsp');
	}
	else{
		alert('Erreur3');
	}
}

/*
function setup(){
	var el_1 = self.document.getElementById('facets-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>');
	if(el_1){
		self.document.location.replace('http://'+getHost()+'/facetmap-facets-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>/servletParameters.jsp');
	}
	else{
		alert('Erreur4');
	}
}
*/

function transfer_config(){
	var el_1 = self.document.getElementById('configServletFormFacet');
	if(el_1){
		var map = self.document.configServletFormFacet.map.value;
		var view = self.document.configServletFormFacet.view.value;
		var resultLimit = self.document.configServletFormFacet.resultLimit.value;
		var showEmptySelections = self.document.configServletFormFacet.showEmptySelections.value;
		var log4jConfigFile = self.document.configServletFormFacet.log4jConfigFile.value;
		var workDirectory = self.document.configServletFormFacet.workDirectory.value;
		
		parent.frame2_<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>.document.location.replace('http://'+getHost()+'/facetmap-content-<%nGet("javascript_name").substring(nGet("javascript_name").lastIndexOf("-") +1)%>/setServletParameters.jsp?map='+map+'&view='+view+'&resultLimit='+resultLimit+'&showEmptySelections='+showEmptySelections+'&log4jConfigFile='+log4jConfigFile+'&workDirectory='+workDirectory);
	}
	else{
		alert('Erreur5');
	}
}

function show_facet(name,morename){
	var ensemble = parent.frames[0].document.getElementsByName(name);
	for (facet=0; facet<ensemble.length; facet++){
			ensemble[facet].style.display="";
		   }
	self.document.getElementsByName(morename)[0].style.display="none";
}

function hide_facets(name,linkname){
	self.document.getElementsByName(name)[0].style.display="none";
	self.document.getElementsByName(linkname)[0].innerHTML = " <img class='imgIcon' src='xsl/display/icons/expand.png'/>";
	self.document.getElementsByName(linkname)[0].onclick = new Function ("show_facets('"+name+"','"+linkname+"');");
}

function show_facets(name,linkname){
	self.document.getElementsByName(name)[0].style.display="";
	self.document.getElementsByName(linkname)[0].innerHTML =" <img class='imgIcon' src='xsl/display/icons/collapse.png'/>";
	self.document.getElementsByName(linkname)[0].onclick = new Function ("hide_facets('"+name+"','"+linkname+"');");
}
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>