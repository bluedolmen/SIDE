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
	        fields: [
	           'id',
	           {name: 'size', convert: convertSize},
	           '_name',
	           '_typeOfDocument',
	           {name: '_latitude', type: 'auto'},
	           {name: '_longitude', type: 'auto'},
	           '_reference'
	        ],
	        root: 'records',
	        totalProperty: 'totalCount'
	    });
	
	    // create the Grid
	    var grid = new Ext.grid.GridPanel({
	        store: store,
	        columns: [
	            {id:'name',header: 'Document', width: 100, sortable: true, dataIndex: '_name'},
	            {id:'reference',header: 'Ref', width: 50, sortable: true, dataIndex: '_reference'},
	            {id:'size',header: 'Size', width: 50, sortable: true, dataIndex: 'size', renderer: size} 
	        ],
	        stripeRows: true,
	        autoExpandColumn: 'name',
	        height: 350,
	        width: '100%',
	        title: 'Paging',
	        
	        // customize view config
	        viewConfig: {
	            forceFit:true,
	            enableRowBody:true,
	            showPreview:true,
	            getRowClass : function(record, rowIndex, p, store){
	                if(this.showPreview){
	                    p.body = String.format(
					                '<p>Latitude : {0}<br/>Longitude : {1}<br/>Size : {2}B<br/>Type : {3}</p>',
	                				record.data._latitude, record.data._longitude,record.data.size,record.data._typeOfDocument);
	                    return 'x-grid3-row-expanded';
	                }
	                return 'x-grid3-row-collapsed';
	            }
	        },
	
	        // paging bar on the bottom
	        bbar: new Ext.PagingToolbar({
	            pageSize: 25,
	            store: store,
	            displayInfo: true,
	            displayMsg: 'Displaying topics {0} - {1} of {2}',
	            emptyMsg: "No topics to display",
	            items:[
	                '-', {
	                pressed: true,
	                enableToggle:true,
	                text: 'Show Preview',
	                cls: 'x-btn-text-icon details',
	                toggleHandler: function(btn, pressed){
	                    var view = grid.getView();
	                    view.showPreview = pressed;
	                    view.refresh();
	                }
	            }]
	        })
	   
	    });
	    
	    // render the grid to the specified div in the page
	    grid.render('grid-example-paging');
	    
	    store.load({params:{start:0, limit:25}});
	}
	
	loadWithAuthentication(load);
});