//var sitePresets = [{id: "site-dashboard", name: msg.get("title.collaborationSite")}
//,{id: "site-demo", name: msg.get("title.demoSite")}
//,{id: "site-test", name: msg.get("title.testSite")}
//];
var sitePresets = [];
for ( var c = 0; c < presets.length; c++) {
	var id_ = presets[c];
	var record = null;
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

	sitePresets[c] = record;
}

model.sitePresets = sitePresets;