function getHost(){
	return parent.frames[0].document.location.hostname+":"+parent.frames[0].document.location.port;
}

function show_selection(ref){
	var url = ref.replace("browseSimple_facets.jsp","browseSimple_content.jsp");
	var el_2 = parent.frames[1].document.getElementById('results'); 
	if(el_2){
		parent.frames[1].document.location.replace(url);
	}
	else{
		//alert('Erreur1');
	}
}

function update_facets(){
	var el_1 = parent.frames[0].document.getElementById('facets');
	if(el_1){
		parent.frames[0].document.location.replace('http://'+getHost()+'/facetmap/browseSimple-update.jsp');
	}
	else{
		//alert('Erreur3');
	}
}

function setup(){
	var el_1 = parent.frames[0].document.getElementById('facets');
	if(el_1){
		parent.frames[0].document.location.replace('http://'+getHost()+'/facetmap/servletParameters.jsp');
	}
	else{
		//alert('Erreur3');
	}
}

function transfer_config(){
	var el_1 = parent.frames[0].document.getElementById('configServletFormFacet');
	if(el_1){
//		var map = parent.frames[0].document.configServletFormFacet.map.value;
//		var view = parent.frames[0].document.configServletFormFacet.view.value;
//		var resultLimit = parent.frames[0].document.configServletFormFacet.resultLimit.value;
//		var showEmptySelections = parent.frames[0].document.configServletFormFacet.showEmptySelections.value;
//		var log4jConfigFile = parent.frames[0].document.configServletFormFacet.log4jConfigFile.value;
//		var workDirectory = parent.frames[0].document.configServletFormFacet.workDirectory.value;
//		
//		parent.frames[1].document.location.replace('http://'+getHost()+'/facetmap/setServletParameters.jsp?map='+map+'&view='+view+'&resultLimit='+resultLimit+'&showEmptySelections='+showEmptySelections+'&log4jConfigFile='+log4jConfigFile+'&workDirectory='+workDirectory);
		
		parent.frames[1].document.location =parent.frames[1].document.location; 
	}
	else{
		//alert('Pb el_1');
	}
}

function show_facet(name,morename){
	var ensemble = parent.frames[0].document.getElementsByName(name);
	for (facet=0; facet<ensemble.length; facet++){
			ensemble[facet].style.display="";
		   }
	parent.frames[0].document.getElementsByName(morename)[0].style.display="none";
}

function hide_facets(name,linkname){
	parent.frames[0].document.getElementsByName(name)[0].style.display="none";
	parent.frames[0].document.getElementsByName(linkname)[0].innerHTML = " <img class='imgIcon' src='xsl/display/icons/expand.png'/>";
	parent.frames[0].document.getElementsByName(linkname)[0].onclick = new Function ("show_facets('"+name+"','"+linkname+"');");
}

function show_facets(name,linkname){
	parent.frames[0].document.getElementsByName(name)[0].style.display="";
	parent.frames[0].document.getElementsByName(linkname)[0].innerHTML =" <img class='imgIcon' src='xsl/display/icons/collapse.png'/>";
	parent.frames[0].document.getElementsByName(linkname)[0].onclick = new Function ("hide_facets('"+name+"','"+linkname+"');");
}


function check_selected_criteria(){
	var url = parent.frames[1].document.location;
	var el_2 = parent.frames[1].document.getElementById('facetmap-browse-content');
	if(el_2){
		if(url == 'facetmap/browseSimple_content.jsp' || url == 'facetmap/browseSimple_content.jsp?&s='){
		el_2.style.display = 'none';
		}	
	}	
}

function check_pagination(){
	/*alert("page");
	var url = parent.frames[1].document.location;
	if(url == 'http://localhost:8080/facetmap-content/browseSimple.jsp' || url == 'http://localhost:8080/facetmap-content/browseSimple.jsp?&s='){
		alert("page");
	}*/	
}