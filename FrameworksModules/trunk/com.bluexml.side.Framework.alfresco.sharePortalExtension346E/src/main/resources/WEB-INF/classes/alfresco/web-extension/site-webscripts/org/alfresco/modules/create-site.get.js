// var sitePresets = [{id: "site-dashboard", name: msg.get("title.collaborationSite")}];
// model.sitePresets = sitePresets;
var sitePresets = [];
var siteDisabled = ["user-dashboard", "rm-site-dashboard", "document-workspace", "meeting-workspace"];


for ( var c = 0; c < presets.length; c++) {
	var id_ = presets[c];
	var record = null;
	
	if (!disabled(id_)) {
		// manage collaborationSite
		if (id_ == "site-dashboard") {
			record = {
				id : "site-dashboard",
				name : msg.get("title.collaborationSite")
			}
		} else {
			var name_ = msg.get("title." + id_ + "Site");
			record = {
				id : id_,
				name : name_
			};
		}
		sitePresets.push(record);
	}
	
	
}

model.sitePresets = sitePresets;

function disabled(id) {	
	for (item in siteDisabled) {
		if (id == siteDisabled[item]) {
			return true;
		}
	}	
	return false;
}