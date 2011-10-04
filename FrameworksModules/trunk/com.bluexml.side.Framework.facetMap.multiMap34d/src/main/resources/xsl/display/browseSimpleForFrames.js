function getHost(){
	return getFacetsFrame().document.location.hostname+":"+getFacetsFrame().document.location.port;
}

function show_selection(ref){
	var url = ref.replace("browseSimple_facets.jsp","browseSimple_content.jsp");
	var el_2 = getFacetContentFrame().document.getElementById('results'); 
	if(el_2){
		getFacetContentFrame().document.location.replace(url);
	}
	else{
		//alert('Erreur1');
	}
}

function update_facets(facetPara, communityPara){
	var el_1 = getFacetsFrame().document.getElementById('update_facets');
	if(el_1){
		getFacetsFrame().document.location.replace('http://'+getHost()+'/facetmap/browseSimple-update.jsp?'+facetPara+communityPara);
	}
	else{
		alert('Erreur update_facets');
	}
}

function setup(facetPara, communityPara){
	var el_1 = getFacetsFrame().document.getElementById('facets');
	if(el_1){
		getFacetsFrame().document.location.replace('http://'+getHost()+'/facetmap/servletParameters.jsp?'+facetPara+communityPara);
	}
	else{
		//alert('Erreur3');
	}
}

function transfer_config(){
	var el_1 = getFacetsFrame().document.getElementById('configServletFormFacet');
	if(el_1){
//		var map = getFacetsFrame().document.configServletFormFacet.map.value;
//		var view = getFacetsFrame().document.configServletFormFacet.view.value;
//		var resultLimit = getFacetsFrame().document.configServletFormFacet.resultLimit.value;
//		var showEmptySelections = getFacetsFrame().document.configServletFormFacet.showEmptySelections.value;
//		var log4jConfigFile = getFacetsFrame().document.configServletFormFacet.log4jConfigFile.value;
//		var workDirectory = getFacetsFrame().document.configServletFormFacet.workDirectory.value;
//		
//		getFacetContentFrame().document.location.replace('http://'+getHost()+'/facetmap/setServletParameters.jsp?map='+map+'&view='+view+'&resultLimit='+resultLimit+'&showEmptySelections='+showEmptySelections+'&log4jConfigFile='+log4jConfigFile+'&workDirectory='+workDirectory);
		
		//getFacetContentFrame().document.location =getFacetContentFrame().document.location; 
	}
	else{
		//alert('Pb el_1');
	}
}

function show_facet(name,morename){
	var ensemble = getFacetsFrame().document.getElementsByName(name);
	for (facet=0; facet<ensemble.length; facet++){
			ensemble[facet].style.display="";
		   }
	getFacetsFrame().document.getElementsByName(morename)[0].style.display="none";
}

function hide_facets(name,linkname){
	getFacetsFrame().document.getElementsByName(name)[0].style.display="none";
	getFacetsFrame().document.getElementsByName(linkname)[0].innerHTML = " <img class='imgIcon' src='xsl/display/icons/expand.png'/>";
	getFacetsFrame().document.getElementsByName(linkname)[0].onclick = new Function ("show_facets('"+name+"','"+linkname+"');");
}

function show_facets(name,linkname){
	getFacetsFrame().document.getElementsByName(name)[0].style.display="";
	getFacetsFrame().document.getElementsByName(linkname)[0].innerHTML =" <img class='imgIcon' src='xsl/display/icons/collapse.png'/>";
	getFacetsFrame().document.getElementsByName(linkname)[0].onclick = new Function ("hide_facets('"+name+"','"+linkname+"');");
}


function check_selected_criteria(){
	var url = getFacetContentFrame().document.location;
	var el_2 = getFacetContentFrame().document.getElementById('facetmap-browse-content');
	if(el_2){
		if(url == 'facetmap/browseSimple_content.jsp' || url == 'facetmap/browseSimple_content.jsp?&s='){
		el_2.style.display = 'none';
		}	
	}	
}

function getFacetsFrame() {
	
	return parent.frames[1];
}

function getFacetContentFrame() {
	return parent.frames[2];
}

function check_pagination(){
	/*alert("page");
	var url = parent.frames[1].document.location;
	if(url == 'http://localhost:8080/facetmap-content/browseSimple.jsp' || url == 'http://localhost:8080/facetmap-content/browseSimple.jsp?&s='){
		alert("page");
	}*/	
}