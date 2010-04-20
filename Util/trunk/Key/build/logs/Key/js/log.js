function showPannel(p_idPannel) {
	var pannels = new Array("stats", "deployment", "generation", "console", "service", "documentation");
	$.each(pannels, function(i, n) {
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

function collapseBox(boxId, icon, otherIconId) {
	if (boxId != null) {
		$(document.getElementById(boxId)).slideUp("slow");
		$(icon).hide();
		$(document.getElementById(otherIconId)).show();
	}
}
function expandBox(boxId, icon, otherIconId) {
	if (boxId != null && $('#' + boxId) != null) {
		$(document.getElementById(boxId)).slideDown("slow");
		$(icon).hide();
		$(document.getElementById(otherIconId)).show();
	}
}