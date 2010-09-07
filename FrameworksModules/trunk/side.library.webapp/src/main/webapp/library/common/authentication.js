var _TICKET = "";

function loadWithAuthentication(_f) {
	Ext.Ajax.request({
		url: '/alfresco/service/api/login?u=admin&pw=admin',
		success: function ( result, request ) {
			var ticket = result.responseXML.getElementsByTagName('ticket');
			_TICKET = ticket[0].textContent;
			_f.call();
		}
	});
}