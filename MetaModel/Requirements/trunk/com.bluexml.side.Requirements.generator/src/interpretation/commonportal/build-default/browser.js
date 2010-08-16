Ext.onReady(function() {
  Ext.QuickTips.init();

  var viewport = new Ext.Viewport({
      layout:'fit',
      items:[{
        xtype: 'grouptabpanel',
  		tabWidth: 180,
  		activeGroup: 1,
  		items: [{
  			items: [ 
              {
                  title: 'Analysis',
              }, {
				title: 'Risk analysis',
                iconCls: 'x-icon-risk',
                style: 'padding: 10px;',
				layout: 'fit',
  				items: [gridRisk]	
  			}, {
  				title: 'Problem analysis',
                iconCls: 'x-icon-diagnostic',
                style: 'padding: 10px;',
				layout: 'fit',
  				items: [gridProblem]
  			}]
          }, {
              expanded: true,
              items: [{
                  title: 'Web Tool',
                  iconCls: 'x-icon-webtool',
                  style: 'padding: 10px;'
              }, {
                  title: 'Go to the tool...',
  				  layout: 'fit',
  				  items: [webtoolPanel],
                  iconCls: 'x-icon-webtool-go',
                  style: 'padding: 10px;'
              }]
          }]
}]
  });

  // trigger the data store load
  storeRisk.load();
  storeProblem.load();
});