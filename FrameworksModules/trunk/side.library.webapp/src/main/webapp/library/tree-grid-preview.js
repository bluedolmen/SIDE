function getProperty(el, name) {
	var props = el.getElementsByTagName('propertyId');
	if (props.length == 0)
		props = el.getElementsByTagName('cmis:propertyId');
	for (var i = 0; i < props.length; i++) {
	 	var el = props[i];
	 	if (el.getAttribute('propertyDefinitionId') == name) {
	 		var val = el.getElementsByTagName('value');
			if (val.length == 0)
				val = el.getElementsByTagName('cmis:value');
	 		if (val.length > 0) {
	 			return val[0].textContent;
	 		}
	 	}
	 }
	 return '';
} 

Ext.app.AlfrescoLoader = Ext.extend(Ext.tree.TreeLoader, {
	processResponse : function(response, node, callback) {
		try {
		    var entries = response.responseXML.getElementsByTagName('entry');
		    node.beginUpdate();
		    for(var i = 0, len = entries.length; i < len; i++){
				var child = new Object();
		    	
		    	var title = entries[i].getElementsByTagName('title');
				if (title.length == 0)
					title = entries[i].getElementsByTagName('cmis:title');
		    	child.text = title[0].textContent;
		    	
		    	child.leaf = getProperty(entries[i],'cmis:baseTypeId') != 'cmis:folder';
		    	child.id = getProperty(entries[i],'cmis:objectId');

				var icon = entries[i].getElementsByTagName('icon');
				if (icon.length == 0)
					icon = entries[i].getElementsByTagName('alf:icon');
		    	child.icon = icon[0].textContent;
		    	
		        var n = this.createNode(child);
		        if(n){
		            node.appendChild(n);
		        }
		    }
		    node.endUpdate();
		    if(typeof callback == "function"){
		        callback(this, node);
		    }
		}catch(e){
		    this.handleFailure(response);
		}    
	}
});

function updateGrid(uuid,ticket) {
	var myLoader = new  Ext.app.AlfrescoLoader({
		dataUrl: '/alfresco/service/cmis/i/'+uuid.split("/")[3]+'/children?alf_ticket='+ticket,
		requestMethod: 'get'
	});
	
	myLoader.on("beforeload", function(treeLoader, node) {
		if (!node.isRoot) 
        	treeLoader.dataUrl = '/alfresco/service/cmis/i/'+node.id.split("/")[3]+'/children?alf_ticket='+ticket;
    }, this);

    var tree = new Ext.tree.TreePanel({
		flex:1,
		loader: myLoader,
		root:'Alfresco Repository',
		autoScroll: true,
        height: 400
    });
    
    tree.on('click', function test(node, e) {
       	if (node != null) {
       		var reg = new RegExp("://","g");
           	var uuid = node.id.replace(reg,'/').split('/')[2];
           	
           	var embed = document.getElementById('embed');
           	var parent = embed.parentNode;
           	parent.removeChild(embed);
           	
           	embed = document.createElement('embed');
           	embed.setAttribute('flashvars',"fileName="+node.attributes.text+"&paging=true&url=%2Fshare%2Fproxy%2Falfresco%2Fapi%2Fnode%2Fworkspace%2FSpacesStore%2F"+uuid+"%2Fcontent%2Fthumbnails%2Fwebpreview%3Fc%3Dforce%26noCacheToken%3D1276076754220&i18n_actualSize=Actual%20Size&i18n_fitPage=Fit%20Page&i18n_fitWidth=Fit%20Width&i18n_fitHeight=Fit%20Height&i18n_fullscreen=Fullscreen&i18n_fullwindow=Maximize&i18n_fullwindow_escape=Press%20Esc%20to%20exit%20full%20window%20mode&i18n_page=Page&i18n_pageOf=of&show_fullscreen_button=true&show_fullwindow_button=true");
       		embed.setAttribute('src','/share/components/preview/WebPreviewer.swf');
       		embed.setAttribute('id','embed');
       		embed.setAttribute('width','100%');
       		embed.setAttribute('height','100%');
       		embed.setAttribute('wmode','transparent');
       		embed.setAttribute('allowfullscreen','true');
       		embed.setAttribute('allowscriptaccess','sameDomain');
       		embed.setAttribute('quality','high');
       		embed.setAttribute('type','application/x-shockwave-flash');

			parent.appendChild(embed);

       	}
    });
    
    tree.render('grid-example-tree-preview');	
};

function loadGrid() {
	Ext.Ajax.request({
		url: '/alfresco/service/cmis/p?alf_ticket='+_TICKET,
	   	success: function ( result, request ) {
	   		updateGrid(getProperty(result.responseXML,'cmis:objectId'), _TICKET);
	   	}
	});
};
 
Ext.onReady(function() {
    Ext.QuickTips.init();

	loadWithAuthentication(loadGrid);	
});