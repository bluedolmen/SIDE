var ticket = side.getCurrentTicket();
var webdavUrl =args.webdavurl;


webdavUrl += '?ticket=' + ticket;
model.webdavUrl = webdavUrl;
model.ticket = ticket;