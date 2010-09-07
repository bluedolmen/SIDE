/*!
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */

//
// This is the main layout definition.
//
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	/*
	 * ================  Start page config  =======================
	 */
	// The default start page, also a simple example of a FitLayout.
	var start = {
	    id: 'start-panel',
	    title: 'Start Page',
	    layout: 'fit',
	    bodyStyle: 'padding:25px',
	    contentEl: 'start-div'  // pull existing content from the page
	};
	
	var tabs = new Ext.TabPanel({
	    activeTab: 0,
	    id: 'tab-panel',
	    items: [start]
	});
	
	// This is an inner body element within the Details panel created to provide a "slide in" effect
	// on the panel body without affecting the body's box itself.  This element is created on
	// initial use and cached in this var for subsequent access.
	var detailEl;
	
	// This is the main content center region that will contain each example layout panel.
	// It will be implemented as a CardLayout since it will contain multiple panels with
	// only one being visible at any given time.
	var contentPanel = {
		id: 'content-panel',
		region: 'center', // this is what makes this panel into a region within the containing layout
		layout: 'card',
		margins: '2 5 5 0',
		activeItem: 0,
		border: false,
		items: [
			tabs
			// from basic.js:
			//start, absolute, accordion, anchor, border, cardTabs, cardWizard, column, fit, form, table, vbox, hbox,
			// from custom.js:
			//rowLayout, centerLayout,
			// from combination.js:
			//absoluteForm, tabsNestedLayouts
		]
	};
    
	// Go ahead and create the TreePanel now so that we can use it below
    var treePanel = new Ext.tree.TreePanel({
    	id: 'tree-panel',
    	title: 'Sample Integration',
        region:'north',
        split: true,
        height: 500,
        minSize: 150,
        autoScroll: true,
        
        // tree-specific configs:
        rootVisible: false,
        lines: false,
        singleExpand: true,
        useArrows: true,
        
        loader: new Ext.tree.TreeLoader({
            dataUrl:'tree-data.json'
        }),
        
        root: new Ext.tree.AsyncTreeNode()
    });
    
	// Assign the changeLayout function to be called on tree node click.
    treePanel.on('click', function(n){
    	var sn = this.selModel.selNode || {}; // selNode is null on initial selection
    	
    	var tab = Ext.getCmp(n.id);
    	if (tab != null)
    		Ext.getCmp('tab-panel').activate(tab);
    	
    	if(n.leaf && tab == null){  // ignore clicks on folders and currently selected node 
    		Ext.getCmp('content-panel').layout.setActiveItem(n.id + '-panel');
    		if(!detailEl){
    			var bd = Ext.getCmp('details-panel').body;
    			bd.update('').setStyle('background','#fff');
    			detailEl = bd.createChild(); //create default empty div
    		}
    		detailEl.update('');
    		
    		if (n.attributes.url != null && n.attributes.url.length > 0) {
   			    var extension = new Ext.ux.ManagedIFrame.Panel({
				    title: n.text,
				    id: n.id,
				    closable: true,
				    layout: 'fit',
			        defaultSrc: n.attributes.url,
				    bodyStyle: 'padding:25px',
				    autoScroll: true
			    });
			    
			    Ext.getCmp('tab-panel').add(extension);
				Ext.getCmp('tab-panel').activate(extension);
    			
	    		document.getElementById('file-href').setAttribute("href",n.attributes.url);
	    		document.getElementById('file-href').innerHTML = "Click to open the URL";
	    		detailEl.update(Ext.getDom('details').innerHTML);
    		} else {
   				var extension = new Ext.Panel({
				    title: n.text,
				    id: n.id,
				    closable: true,
				    layout: 'fit',
				    bodyStyle: 'padding:25px',
				    autoScroll: true,
				    autoLoad: {
				    	url:'./library/'+n.id+'.html',
				    	scripts: true
				    }
				});
				
				Ext.getCmp('tab-panel').add(extension);
				Ext.getCmp('tab-panel').activate(extension);

		    	document.getElementById('file-href').setAttribute("href","./library/"+n.id+".js");
		    	document.getElementById('file-href').innerHTML = n.id+".js";
		    	detailEl.update(Ext.getDom('details').innerHTML);
    		}
    	}
    });
    
	// This is the Details panel that contains the description for each example layout.
	var detailsPanel = {
		id: 'details-panel',
        title: 'Details',
        region: 'center',
        bodyStyle: 'padding-bottom:15px;background:#eee;',
		autoScroll: true,
		html: '<p class="details-info">When you select an item from the tree, additional details will display here.</p>'
    };
	
	// Finally, build the main layout once all the pieces are ready.  This is also a good
	// example of putting together a full-screen BorderLayout within a Viewport.
    new Ext.Viewport({
		layout: 'border',
		title: 'SIDE Library Browser',
		items: [{
			xtype: 'box',
			region: 'north',
			applyTo: 'header',
			height: 30
		},{
			layout: 'border',
	    	id: 'layout-browser',
	        region:'west',
	        border: false,
	        split:true,
			margins: '2 0 5 5',
	        width: 275,
	        minSize: 100,
	        maxSize: 500,
			items: [treePanel, detailsPanel]
		},
			contentPanel
		],
        renderTo: Ext.getBody()
    });
});
