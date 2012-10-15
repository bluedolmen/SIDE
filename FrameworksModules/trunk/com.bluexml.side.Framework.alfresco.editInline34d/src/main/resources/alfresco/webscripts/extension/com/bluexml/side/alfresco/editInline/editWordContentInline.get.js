var ticket = side.getCurrentTicket();
var webdavUrl =args.webdavurl;


webdavUrl += '?ticket=' + ticket;
model.webdavUrl = url.getServer() + "/alfresco" + webdavUrl;
model.ticket = ticket;