function createPanel(_record) {
	return new Ext.tree.TreePanel({
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
}

var agents = new Ext.Panel({
	title: 'List of agents',
    layout: 'accordion',
    region:'west',
    width: 175,
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

var contentPanel = new Ext.Panel({
	title:'Content panel',
	html: 'content',
	region:'east'
});



var webtoolPanel = {
    layout: 'border',
    bodyBorder: false,
    items: [{
        title: 'Footer',
        region: 'south',
        height: 150,
        minSize: 75,
        maxSize: 250,
        html: '<p>Footer content</p>'
    },agents,{
        title: 'Main Content',
        collapsible: false,
        region: 'center',
        html: '<h1>Main Page</h1><p>This is where the main content would go</p>'
    }]
};