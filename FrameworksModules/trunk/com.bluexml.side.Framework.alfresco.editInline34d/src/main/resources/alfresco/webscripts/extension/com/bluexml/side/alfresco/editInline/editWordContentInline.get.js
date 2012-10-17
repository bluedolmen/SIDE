var ticket = side.getCurrentTicket();
var webdavUrl = args.webdavurl;

var xmlConf = new XML(config.script);
model.publicHost = xmlConf.host.@value.toString();

webdavUrl += '?ticket=' + ticket;
model.webdavUrl = "http://" + model.publicHost + "/alfresco" + webdavUrl;
model.ticket = ticket;