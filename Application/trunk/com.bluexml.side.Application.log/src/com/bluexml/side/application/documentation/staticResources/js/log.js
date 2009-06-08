function showPannel(p_idPannel) {
	var pannels = new Array("stats", "deployement", "generation", "error");
	$.each(pannels, function(i,n){
		if (n == p_idPannel) {
			$("#" + n).show();
		} else {
			$("#" + n).hide();
		}
	})
}

function switchLog(clickedText) {
	if (clickedText != null) {
		$(clickedText).hide();
		var sib = $(clickedText).siblings();
		$(sib[0]).slideDown("slow");
	}
}

