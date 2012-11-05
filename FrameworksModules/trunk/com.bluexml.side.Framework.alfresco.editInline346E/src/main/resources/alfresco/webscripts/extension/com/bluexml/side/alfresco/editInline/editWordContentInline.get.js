function main() {

var ticket = side.getCurrentTicket();
model.ticket = ticket;

var webdavUrl = args.webdavurl;


var xmlConf = new XML(config.script);
model.publicHost = xmlConf.host.@value.toString();
model.context = xmlConf.context.@value.toString();
var commit = xmlConf.commit.@value.toString();
model.commit = commit;

var docs = null;
if (args.mime == "") {
	docs = search.query({query: "ID:\"" + args.noderef + "\""});
	if ( docs.length == 1) {
		model.mime = docs[0].properties.content.mimetype;
	} else {
		status.setCode(status.STATUS_INTERNAL_SERVER_ERROR, "Le document n'a pu être retrouvé.");
		return;
	}
} else {
	model.mime = args.mime;
}

var workingCopy = null;
if (commit === "true") {
	if (docs == null) {
		docs = search.query({query: "ID:\"" + args.noderef + "\""});
	}
	if ( docs.length == 1) {
		if (docs[0].assocs["cm:workingcopylink"] == null) {
			workingCopy = docs[0].checkout();
			var webdavUrlSplited = webdavUrl.split("/");
			webdavUrl = "";
			for ( var i = 0; i < webdavUrlSplited.length - 1; i++) {
				webdavUrl += webdavUrlSplited[i] + "/";
			}
			model.noderef = workingCopy.nodeRef.toString();
			webdavUrl += workingCopy.properties["cm:name"];
		} else {
			status.setCode(status.STATUS_INTERNAL_SERVER_ERROR, "La copie de travail de ce document n'a pu être créée.");
			return;
		}
	} else {
		status.setCode(status.STATUS_INTERNAL_SERVER_ERROR, "Le document n'a pu être retrouvé.");
		return;
	}
} else {
	model.noderef = "";
}


webdavUrl += '?ticket=' + ticket;
model.webdavUrl = "http://" + model.publicHost + "/alfresco" + webdavUrl;

}
main();