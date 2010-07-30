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
    textPst : '%', // string added to the end of the cell value (defaults to '%')
    colored : true // True for pretty colors, false for just blue (defaults to false)
  });

  // the column model has information about grid columns
  // dataIndex maps the column to the specific data field in
  // the data store (created below)
  var cm = new Ext.grid.ColumnModel([{
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
  },
  riskColumn]);

  var store = new Ext.data.JsonStore({
    url: 'diagnostic.json',
    storeId: 'myStore',
    root: 'diagnostic',
    idProperty: 'name',
    fields: ['name', 'type', {name:'value', convert:convertPercent}]
  });

  var grid = new Ext.grid.GridPanel({
    store : store,
    cm : cm,
    renderTo : 'grid',
    width : '100%',
    height : 400,
    autoExpandColumn : 'name',
    plugins : [riskColumn]
  });

  // trigger the data store load
  store.load();
});