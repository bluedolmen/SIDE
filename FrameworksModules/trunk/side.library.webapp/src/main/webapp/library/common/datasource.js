function getDataSource(type, ticket, url) {	
	if (type == 'xml')
		url += '.xml';
		
	if (ticket != null && ticket.length > 0)
		url += "?alf_ticket="+ticket;
	
	return url;
}