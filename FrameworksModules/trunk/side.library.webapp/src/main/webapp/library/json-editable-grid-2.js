Ext.onReady(function(){

	function coord(val, record) {
		if (val == "")
			return 0.0;
			
		var reg=new RegExp("(,)", "g");
		return val.replace(reg,".");
	}
	
	function ref(val, record) {
		if (val == "")
			return '000';
		return val;
	}

	var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Update',
        listeners: {
			'afteredit': function(data) {
            	var _params = 
            		{"properties":{
            			"name":data.record.data._name,
            			"library:com_bluexml_side_library_GeoLocalizable_latitude":data.record.data._latitude,
            			"library:com_bluexml_side_library_GeoLocalizable_longitude":data.record.data._longitude,
            			"library:com_bluexml_side_library_Document_reference":data.record.data._reference,
            			"library:com_bluexml_side_library_Document_typeOfDocument":data.record.data._typeOfDocument
					}};
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

	function load() {
	    var store = new Ext.data.JsonStore({
	    	url:getDataSource('json',_TICKET),
	        autoLoad:true,
	        fields: [
	           'id',
	           {name: 'size', type: 'float'},
	           '_name',
	           '_typeOfDocument',
	           {name: '_latitude', type: 'float', convert: coord},
	           {name: '_longitude', type: 'float', convert: coord},
	           {name: '_reference', convert: ref}
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
	
	    // create the Grid
	    var grid = new Ext.grid.EditorGridPanel({
	        store: store,
	        plugins: [editor],
	        columns: [
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
	            {id:'latitude',header: 'Latitude', width: 160, sortable: true, dataIndex: '_latitude', editor: new Ext.form.NumberField({allowBlank: false})},
	            {id:'longitude',header: 'Longitude', width: 160, sortable: true, dataIndex: '_longitude', editor: new Ext.form.NumberField({allowBlank: false})}
	        ],
	        stripeRows: true,
	        autoExpandColumn: 'name',
	        height: 350,
	        width: '100%',
	        id: 'grid',
	        title: 'Editable Grid'
	    });
	    
	    // render the grid to the specified div in the page
	    grid.render('grid-example-json-2');
	}
	
	loadWithAuthentication(load);
});