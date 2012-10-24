var ticket = side.getCurrentTicket();
var webdavUrl = args.webdavurl;
var webdavUrlSplited = webdavUrl.split("/");
webdavUrl = "";
for ( var i = 0; i < webdavUrlSplited.length - 1; i++) {
	webdavUrl += webdavUrlSplited[i] + "/";
} 
var xmlConf = new XML(config.script);
model.publicHost = xmlConf.host.@value.toString();
model.context = xmlConf.context.@value.toString();
var commit = xmlConf.commit.@value.toString();
model.commit = commit;
var workingCopy = null;
if (commit === "true") {
	var docs = search.query({query: "ID:\"" + args.noderef + "\""});
	if ( docs.length == 1) {
		if (docs[0].assocs["cm:workingcopylink"] == null) {
			workingCopy = docs[0].checkout();
		} else {
			status.setCode(status.STATUS_INTERNAL_SERVER_ERROR, "Could not checkout: " + args.noderef);
		}
	} else {
	status.setCode(status.STATUS_INTERNAL_SERVER_ERROR, "Could not checkout: " + args.noderef);
	}
}
webdavUrl += workingCopy.properties["cm:name"];
webdavUrl += '?ticket=' + ticket;
model.webdavUrl = "http://" + model.publicHost + "/alfresco" + webdavUrl;
model.ticket = ticket;
model.noderef = workingCopy.nodeRef.toString();