Ext.onReady(function() {
  Ext.QuickTips.init();

  function convertPercent(v, record) {
  	var val = v.replace(/,/g,".");
  	val = val * 100;
  	//Take only 2 decimals
  	val = Math.round(val*100)/100
  	return val;
  }

  // custom plugin Ext.ux.ProgressColumn example
  var riskColumn = new Ext.ux.grid.ProgressColumn({
    header : "Risk",
    dataIndex : 'value',
    width : 85,
    sortable : true,
    groupable : false,
    textPst : '%', // string added to the end of the cell value (defaults to '%')
    colored : true // True for pretty colors, false for just blue (defaults to false)
  });

  // the column model has information about grid columns
  // dataIndex maps the column to the specific data field in
  // the data store (created below)
  var cmRisk = new Ext.grid.ColumnModel([{
    id : 'name',
    groupable : false,
    header : "Name",
    dataIndex : 'name',
    width : 220,
    sortable : true
  }, {
    header : "Type",
    dataIndex : 'type',
    width : 130,
    sortable : true
  },
  riskColumn]);

  var storeRisk = new Ext.data.GroupingStore({
    url: 'diagnostic.json',
    reader: new Ext.data.JsonReader({
	    root: 'diagnostic',
    	fields: ['name', 'type', {name:'value', convert:convertPercent}]
    }),
    groupField:'type'
  });

  var gridRisk = new Ext.grid.GridPanel({
    store : storeRisk,
    cm : cmRisk,
    autoExpandColumn : 'name',
    plugins : [riskColumn],
    view: new Ext.grid.GroupingView({
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
    })
  });
  
  var cmProblem = new Ext.grid.ColumnModel([{
    id : 'name',
    header : "Name",
    dataIndex : 'name',
    width : 220,
    sortable : true
  }, {
    header : "Type",
    dataIndex : 'type',
    width : 130,
    sortable : true
  },{
    header : "Severity",
    dataIndex : 'severity',
    width : 130,
    sortable : true
  },{
    header : "Description",
    dataIndex : 'description',
    width : 130,
    sortable : true
  },
]);

  var storeProblem = new Ext.data.GroupingStore({
    url: 'problem.json',
    reader: new Ext.data.JsonReader({
	    root: 'diagnostic',
	    fields: ['name', 'type', 'severity', 'description']
    }),
    groupField:'severity'
  });

  var gridProblem = new Ext.grid.GridPanel({
    store : storeProblem,
    cm : cmProblem,
    autoExpandColumn : 'name',
    view: new Ext.grid.GroupingView({
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
        })
  });  

  var viewport = new Ext.Viewport({
      layout:'fit',
      items:[{
        xtype: 'grouptabpanel',
  		tabWidth: 130,
  		activeGroup: 0,
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
                  title: 'Configuration',
                  iconCls: 'x-icon-configuration',
                  tabTip: 'Configuration tabtip',
                  style: 'padding: 10px;'
              }, {
                  title: 'Email Templates',
                  iconCls: 'x-icon-templates',
                  tabTip: 'Templates tabtip',
                  style: 'padding: 10px;'
              }]
          }]
}]
  });


  // trigger the data store load
  storeRisk.load();
  storeProblem.load();
});