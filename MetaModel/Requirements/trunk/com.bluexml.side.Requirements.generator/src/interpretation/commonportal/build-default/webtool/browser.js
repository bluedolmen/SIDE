function createAnnotation(_id, _author, _date, _annotation, _comment) {
	var html =  
		"<div class='confirmationaccept'>"+
		"<b>["+_id+"]</b> Created by "+_author+" <i>("+_date+")</i> :<br/><b>Annotation :</b> "+_annotation+"<br/><b>Comment :</b>"+_comment+
		"</div><br/>";
	return html;
}

function refreshAnnotation(_id) {
	//Update annotation(s)
	var cReader = new Ext.data.JsonReader({
	    root: 'comments',
	    fields: ['id', 'date', 'author', 'comment', 'annotation']
	});
	
	var cStore = new Ext.data.Store({
	    url: 'mysql/listComment.php?elementId='+_id,
	    autoLoad: true,
	    reader: cReader
	});
	
	cStore.load({
		callback : function() {
			var html = "";
			for (var i = 0; i < cStore.getCount(); i++) {
				var r = cStore.getAt(i);
				html += createAnnotation(r.data.id, r.data.author, r.data.date, r.data.annotation,r.data.comment);
			}
			var p = Ext.getCmp('comment-panel');
			p.elementId = _id;
			p.update(html);
		}
	});
}

function createPanel(_record) {
	var p = new Ext.tree.TreePanel({
		title: _record.data.name,
	    autoScroll: true,
	    
	    // tree-specific configs:
	    rootVisible: false,
	    singleExpand: true,
	    useArrows: true,
	    
	    loader: new Ext.tree.TreeLoader({
	        dataUrl:'data/prototype/'+_record.data.goTo+'.json'
	    }),
	    
	    root: new Ext.tree.AsyncTreeNode()
	});
	
	p.on('click', function(n) {
   		var sn = this.selModel.selNode || {}; // selNode is null on initial selection
   		
   		var _id;
   		var _url = n.attributes.goTo;
   		if ((_url == null) || (_url.length == 0))
   			_url = 'overview.html';

		//Activate all tab
		Ext.getCmp('information-panel').activate('comment-panel');
		Ext.getCmp('information-panel').activate('description-panel');
		Ext.getCmp('information-panel').activate('synopsis-panel');

		var cPanel = Ext.getCmp('content-panel');
		var reader = new Ext.data.JsonReader({
		    root: 'info',
		    fields: ['title', 'description', 'synopsis', 'id']
		});
		
		if (n.getDepth() == 1) {
			var store = new Ext.data.Store({
			    url: 'data/prototype/page/project.json',
			    autoLoad: true,
			    reader: reader
			});
			
			store.load({
				callback : function() {
					if (store.getCount() > 0) {
						var r = store.getAt(0);
						cPanel.setTitle(r.data.title);
						cPanel.update(r.data.description);
						Ext.getCmp('information-panel').hideTabStripItem('description-panel');
						Ext.getCmp('information-panel').hideTabStripItem('synopsis-panel');
						Ext.getCmp('information-panel').activate('comment-panel');
						n.setId(r.data.id);
						refreshAnnotation(r.data.id);
					}
				}
			});
		} else {
			refreshAnnotation(n.id);
			
	   		cPanel.setLocation('data/prototype/page/'+_url);
	   		
			var store = new Ext.data.Store({
			    url: 'data/prototype/page/'+n.id+'.json',
			    autoLoad: true,
			    reader: reader
			});
			
			store.load({
				callback : function() {
					if (store.getCount() > 0) {
						var r = store.getAt(0);
						cPanel.setTitle(r.data.title);
						Ext.getCmp('information-panel').unhideTabStripItem('description-panel');
						Ext.getCmp('information-panel').unhideTabStripItem('synopsis-panel');
						Ext.getCmp('description-panel').update(r.data.description);
						Ext.getCmp('synopsis-panel').update(r.data.synopsis);
						Ext.getCmp('information-panel').activate('description-panel');
					}
				}
			});
		}
		

   	});
	
	return p;
}

var agents = new Ext.Panel({
	title: 'List of agents',
    layout: 'accordion',
    region:'west',
    width: 300,
    autoScroll: true
});

var agentReader = new Ext.data.JsonReader({
    root: 'agent',
    idProperty: 'name',
    fields: ['name', 'goTo']
});

var store = new Ext.data.Store({
    url: 'data/prototype/agent.json',
    autoLoad: true,
    reader: agentReader
});

store.load({
	callback : function() {
		for (var i = 0; i < store.getCount(); i++)
			agents.add(createPanel(store.getAt(i)));
	}
});

var dialog = new Ext.Window({
    width:420,
    height:300,
    closeAction:'hide',
    layout:'fit',
	title: 'Add an annotation',
	items:[	new Ext.FormPanel({
	    labelWidth: 75,
	    id:'form-add-annotation',
	    bodyStyle:'padding:5px 5px 0',
	    defaults: {width: 300},
	    defaultType: 'textfield',
	    items: [{
	            fieldLabel: 'Author',
	            name: 'author',
	            allowBlank:false
	        },new Ext.form.TextArea({
	            fieldLabel: 'Annotation',
	            name: 'annotation',
	            allowBlank:false,
	            height: 180
	        })
	    ],
	
	    buttons: [{
	        text: 'Save',
	        handler: function() {
	        	var p = Ext.getCmp('comment-panel');
	        	if (p.elementId == null) {
					Ext.Msg.alert('Error', 'Impossible to retrieve element\'s identifier.');
					return;
	        	}
	        	var data = Ext.getCmp('form-add-annotation').getForm().getValues();
	        	Ext.Ajax.request({
	        		method:'GET',
	        		url:'mysql/addComment.php',
	        		params:{
	        			elementId:p.elementId,
	        			author:data.author,
	        			annotation:data.annotation
	        		},
	        		success: function(response) {
	        			refreshAnnotation(p.elementId);
	        			Ext.Msg.alert('Annotation',response.responseText);
	        		}
	        	});
	        	dialog.hide();
	        } 
	    },{
	        text: 'Cancel',
	        handler: function() {
	        	Ext.getCmp('form-add-annotation').getForm().reset();
	        	dialog.hide();
	        }
	    }]
    })]
});

var tbar = new Ext.Toolbar();
tbar.add({
    text: 'Add an annotation',
    icon: 'images/add.png',
    handler: function() {
    	dialog.show();
    }
});

var contentPanel = new Ext.ux.ManagedIFrame.Panel({
	title:'Content panel',
	id:'content-panel',
	html: 'content',
	region:'center',
    autoScroll: true,
    padding: '10px'
});

var webtoolPanel = {
    layout: 'border',
    bodyBorder: false,
    items: [{
        title: 'Information',
        id: 'information-panel',
        region: 'south',
        height: 150,
        minSize: 75,
        maxSize: 250,
        xtype: 'tabpanel',
        activeTab: 0,
        items:[
        	{
        		title:'Description',
        		id:'description-panel',
        		autoScroll: true,
        		padding: '10px'
        	},
        	{
        		title:'Synopsis',
        		id:'synopsis-panel',
        		autoScroll: true,
        		padding: '10px'
        	},
        	{
        		title:'Comment(s)',
        		id:'comment-panel',
        		autoScroll: true,
        		padding: '10px',
        		tbar: tbar
        	}
        ]
    },agents,contentPanel]
};