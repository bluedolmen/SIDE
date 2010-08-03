Ext.onReady(function() {
  Ext.QuickTips.init();

  var viewport = new Ext.Viewport({
      layout:'fit',
      items:[{
        xtype: 'grouptabpanel',
  		tabWidth: 130,
  		activeGroup: 1,
  		items: [{
  			items: [ 
              {
                  title: 'Analysis',
              }, {
				title: 'Risk analysis',
                iconCls: 'x-icon-subscriptions',
                style: 'padding: 10px;',
				layout: 'fit',
  				items: [gridRisk]	
  			}, {
  				title: 'Problem analysis',
                iconCls: 'x-icon-subscriptions',
                style: 'padding: 10px;',
				layout: 'fit',
  				items: [gridProblem]
  			}]
          }, {
              expanded: true,
              items: [{
                  title: 'Web Tool',
                  iconCls: 'x-icon-configuration',
                  style: 'padding: 10px;'
              }, {
                  title: 'Go to the tool...',
  				  layout: 'fit',
  				  items: [webtoolPanel],
                  iconCls: 'x-icon-templates',
                  style: 'padding: 10px;'
              }]
          }]
}]
  });

  // trigger the data store load
  storeRisk.load();
  storeProblem.load();
});