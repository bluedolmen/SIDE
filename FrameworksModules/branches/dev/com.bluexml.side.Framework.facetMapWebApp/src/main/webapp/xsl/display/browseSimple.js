
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