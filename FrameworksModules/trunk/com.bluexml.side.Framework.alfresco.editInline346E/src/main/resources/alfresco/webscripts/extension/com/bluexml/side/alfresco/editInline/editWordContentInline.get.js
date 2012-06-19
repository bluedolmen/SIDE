var ticket = side.getCurrentTicket();
var webdavUrl =args.webdavurl;


webdavUrl += '?ticket=' + ticket;
model.webdavUrl = url.getServer() + ":8080/alfresco" + webdavUrl;
model.ticket = ticket;