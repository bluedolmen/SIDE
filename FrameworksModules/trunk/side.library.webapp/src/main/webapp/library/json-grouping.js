Ext.onReady(function(){

    function size(val){
        if ((val/1000 > 500) || (val/1000 < 100)){
            return '<span style="color:red;">' + Math.floor(val/1024) + ' KB</span>';
        }
        return '<span style="color:green;">' + Math.floor(val/1024) + ' KB</span>';
    }
    
    function convertSize(val, record){
    	var reg=new RegExp("(\\s)", "g");
		return val.replace(reg,"");
    }

	function load() {
	    var reader = new Ext.data.JsonReader({
	        root: 'records',
	        fields: [
				'id',
		        {name: 'size', type: 'float', convert:convertSize},
		        '_name',
		        '_typeOfDocument',
		        {name: '_latitude', type: 'flloat'},
		        {name: '_longitude', type: 'auto'},
		        '_reference'
	    	]
	    });
	
	    var store = new Ext.data.GroupingStore({
	    	url:getDataSource('json',_TICKET),
	        autoLoad:true,
	        reader:reader,
	        groupField:'_typeOfDocument'
	    });
	
	    // create the Grid
	    var grid = new Ext.grid.GridPanel({
	        store: store,
	        columns: [
	            {id:'id',header: 'Identifier', width: 160, sortable: true, dataIndex: 'id', hidden:true, groupable: false},
	            {id:'reference',header: 'Ref', width: 50, sortable: true, dataIndex: '_reference', groupable: false},
	            {id:'name',header: 'Name', width: 100, sortable: true, dataIndex: '_name', groupable: false},
	            {id:'typeOfDocument',header: 'Type', width: 160, sortable: true, dataIndex: '_typeOfDocument'},
	            {id:'latitude',header: 'Latitude', width: 160, sortable: true, dataIndex: '_latitude', groupable: false},
	            {id:'longitude',header: 'Longitude', width: 160, sortable: true, dataIndex: '_longitude', groupable: false},
	            {id:'size',header: 'Size', width: 50, sortable: true, dataIndex: 'size', renderer: size} 
	        ],
	        view: new Ext.grid.GroupingView({
	            forceFit:true,
	            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
	        }),
	        stripeRows: true,
	        autoExpandColumn: 'name',
	        height: 350,
	        width: '100%',
	        title: 'Grouping',
	        fbar  : ['->', {
	            text:'Clear Grouping',
	            iconCls: 'icon-clear-group',
	            handler : function(){
	                store.clearGrouping();
	            }
	        }] 
	    });
	    
	    // render the grid to the specified div in the page
	    grid.render('grid-example-grouping');
	}
	
	loadWithAuthentication(load);
});