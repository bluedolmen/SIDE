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

	function coord(val, record) {
		var reg=new RegExp("(,)", "g");
		return val.replace(reg,".");
	}

	function load() {
	    var store = new Ext.data.JsonStore({
	    	url:getDataSource('json',_TICKET),
	        autoLoad:true,
	        fields: [
	           'id',
	           {name: 'size', convert:convertSize},
	           '_name',
	           '_typeOfDocument',
	           {name: '_latitude', type: 'float', convert: coord},
	           {name: '_longitude', type: 'float', convert: coord},
	           '_reference'
	        ],
	        root: 'records'
	    });
	
		var combo = new Ext.form.ComboBox({
		    typeAhead: true,
		    triggerAction: 'all',
		    lazyRender:true,
		    editable: false,
		    mode: 'local',
		    store: new Ext.data.ArrayStore({
		        id: 0,
		        fields: [
		            'id',
		            'displayText'
		        ],
		        data: [['Picture', 'Picture'], ['Textual document', 'Textual document'], ['Video','Video']]
		    }),
		    valueField: 'id',
		    displayField: 'displayText'
		});
	
		var number = new Ext.form.NumberField({
			allowBlank: false
		});
	
	    // create the Grid
	    var grid = new Ext.grid.EditorGridPanel({
	        store: store,
	        columns: [
	            {id:'id',header: 'Identifier', width: 160, sortable: true, dataIndex: 'id', hidden:true},
	            {
	            		id:'reference',
	            		header: 'Ref',
	            		width: 50,
	            		sortable: true,
	            		dataIndex: '_reference',
	            		editor: new Ext.form.TextField({allowBlank: false})
	            },{
	            		id:'name',
	            		header: 'Name',
	            		width: 100,
	            		sortable: true,
	            		dataIndex: '_name',
	            		editor: new Ext.form.TextField({allowBlank: false})
	            },
	            {id:'typeOfDocument',header: 'Type', width: 160, sortable: true, dataIndex: '_typeOfDocument', editor: combo},
	            {id:'latitude',header: 'Latitude', width: 160, sortable: true, dataIndex: '_latitude', editor: number},
	            {id:'longitude',header: 'Longitude', width: 160, sortable: true, dataIndex: '_longitude', editor: number},
	            {id:'size',header: 'Size', width: 50, sortable: true, dataIndex: 'size', renderer: size} 
	        ],
	        stripeRows: true,
	        autoExpandColumn: 'name',
	        height: 350,
	        width: '100%',
	        id: 'grid',
	        title: 'Editable Grid',
			listeners: {
				'afteredit': function(data) {
	            	var _params = 
	            		{"properties":{
	            			"name":data.record.data._name,
	            			"library:com_bluexml_side_library_Document_reference":data.record.data._reference
						}};
	
					if (data.record.data._typeOfDocument != "")
						_params.properties["library:com_bluexml_side_library_Document_typeOfDocument"] = data.record.data._typeOfDocument;				
					if (data.record.data._latitude != "")
						_params.properties["library:com_bluexml_side_library_GeoLocalizable_latitude"] = data.record.data._latitude;
					if (data.record.data._longitude != "")
						_params.properties["library:com_bluexml_side_library_GeoLocalizable_longitude"] = data.record.data._longitude;
					
					Ext.Ajax.request({
						url: '/alfresco/service/api/metadata/node/workspace/SpacesStore/'+data.record.id+'?alf_ticket='+_TICKET,
						method: 'POST',
						headers: {
							'Content-Type':'application/json; charset=UTF-8'
						},
						params: Ext.encode(_params),
					});
	           	}
			}
	    });
	    
	    // render the grid to the specified div in the page
	    grid.render('grid-example-json-1');
	}
	
	loadWithAuthentication(load);
});