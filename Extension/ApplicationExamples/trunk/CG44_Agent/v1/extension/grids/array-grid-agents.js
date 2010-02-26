/*!
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

	var myReader = new Ext.data.JsonReader({
        fields: [
           {name:'Nom', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Dossier_Nom"]}},
		   {name:'Prenom', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Dossier_Prenom"]}},
		   {name:'Matricule', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Dossier_Matricule"]}}
        ],
        root: 'items'
	});


    // create the Data Store
    var store = new Ext.data.GroupingStore({
    	url:'http://localhost:8080/alfresco/service/slingshot/doclib/doclist/all/site/cg44/documentLibrary/Dossier%20Agent',
        autoLoad:true,
        reader:myReader,
        sortInfo:{field: 'Matricule', direction: "ASC"}
    });
    store.load();


    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {id:'name',header: 'Nom', width: 160, sortable: true, dataIndex: 'Nom'},
            {id:'lastname',header: 'Prénom', width: 160, sortable: true, dataIndex: 'Prenom'},
            {id:'matricule',header: 'Matricule', width: 160, sortable: true, dataIndex: 'Matricule'}
        ],
        stripeRows: true,
        autoExpandColumn: 'fileName',
        height: 600,
        width: '100%',
        title: 'Liste des agents',
        // config options for stateful behavior
        stateful: true,
        stateId: 'grid',
        view: new Ext.grid.GroupingView({
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
        })       
    });
    
    // render the grid to the specified div in the page
    grid.render('grid-example');
});