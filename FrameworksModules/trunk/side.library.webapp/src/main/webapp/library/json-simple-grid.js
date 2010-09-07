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
	    var store = new Ext.data.JsonStore({
	    	url:getDataSource('json',_TICKET),
	        autoLoad:true,
	        fields: [
	           'id',
	           {name: 'size', type: 'float', convert:convertSize},
	           '_name',
	           '_typeOfDocument',
	           {name: '_latitude', type: 'flloat'},
	           {name: '_longitude', type: 'auto'},
	           '_reference'
	        ],
	        root: 'records'
	    });
	
	    // create the Grid
	    var grid = new Ext.grid.GridPanel({
	        store: store,
	        columns: [
	            {id:'id',header: 'Identifier', width: 160, sortable: true, dataIndex: 'id', hidden:true},
	            {id:'reference',header: 'Ref', width: 50, sortable: true, dataIndex: '_reference'},
	            {id:'name',header: 'Name', width: 100, sortable: true, dataIndex: '_name'},
	            {id:'typeOfDocument',header: 'Type', width: 160, sortable: true, dataIndex: '_typeOfDocument'},
	            {id:'latitude',header: 'Latitude', width: 160, sortable: true, dataIndex: '_latitude'},
	            {id:'longitude',header: 'Longitude', width: 160, sortable: true, dataIndex: '_longitude'},
	            {id:'size',header: 'Size', width: 50, sortable: true, dataIndex: 'size', renderer: size} 
	        ],
	        stripeRows: true,
	        autoExpandColumn: 'name',
	        height: 350,
	        width: '100%',
	        title: 'Simple Grid'     
	    });
	    
	    // render the grid to the specified div in the page
	    grid.render('grid-example');
	}
	
	loadWithAuthentication(load);
});