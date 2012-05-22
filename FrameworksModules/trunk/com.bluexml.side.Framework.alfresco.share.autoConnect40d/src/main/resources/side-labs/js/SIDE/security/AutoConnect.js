var SIDE = SIDE || {};
SIDE.Util = SIDE.Util || {};

SIDE.Util.getParam = function(param) {
	var qs = window.location.search.substring(1);
    var params = SIDE.Util.getQueryParams(qs)

    return params[param];
}

SIDE.Util.getQueryParams = function(qs) {
    qs = qs.split("+").join(" ");
    var params = {};
    var tokens;
    var pattern = /[?&]?([^=]+)=([^&]*)/g;

    while (tokens = pattern.exec(qs)) {
        params[decodeURIComponent(tokens[1])]
            = decodeURIComponent(tokens[2]);
    }

    return params;
}

SIDE.Util.addQueryParam = function(qs, param) {
	return qs + (qs.indexOf('?') != -1 ? '&' : '?') + param;
}

if ((SIDE.Util.getParam('auto'))
	&& (!SIDE.Util.getParam('try'))
	&& (
		(document.referrer && document.referrer.indexOf('/share/page/user/') == -1)
		|| (document.referrer == ""))) {
	var jqxhr = $.ajax({
	  type: 'GET',
	  url: '/share/page/dologin',
	  data: {
	  	'username': 'anonymous',
	  	'password': 'anonymous'
  	  },
  	  statusCode: {
	    302: function(a, b, c) {
  			//alert('302');
	  	}
	  }
  })
  .success(function(a, b, c){
  	//alert('success');
  })
  .error(function(a, b, c){
  	//alert('error');
  })
  .complete(function(a, b ,c){
  	//alert('complete');
  	var redirect = SIDE.Util.getParam('r');
  	if (redirect != null) {
  		window.location = SIDE.Util.addQueryParam(redirect, 'try=1');
  	} else {
  		window.location = SIDE.Util.addQueryParam(window.location.toString(), 'try=1');
  	}
  });
}