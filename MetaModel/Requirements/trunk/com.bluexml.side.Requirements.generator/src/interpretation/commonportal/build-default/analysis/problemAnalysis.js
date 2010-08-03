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
    url: 'data/analysis/problem.json',
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
