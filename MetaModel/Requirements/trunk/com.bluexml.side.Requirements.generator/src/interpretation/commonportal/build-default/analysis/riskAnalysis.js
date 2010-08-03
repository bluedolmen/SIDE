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
    url: 'data/analysis/diagnostic.json',
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