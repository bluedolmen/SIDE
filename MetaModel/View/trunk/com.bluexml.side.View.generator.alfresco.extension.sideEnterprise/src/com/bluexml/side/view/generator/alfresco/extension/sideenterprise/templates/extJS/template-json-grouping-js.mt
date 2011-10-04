<% metamodel http://www.kerblue.org/view/1.0 
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.extJS.services.extJS
import com.bluexml.side.view.generator.alfresco.extension.sideenterprise.templates.extJS.services.classes
%> 
<%script type="view.AbstractViewOf" name="validatedFilename"%> 
<%if (eContainer() == getRootContainer()){%><%getProperty("extJSProject")%>/library/<%name%>/extJs/json-grouping.js<%}%>
<%script type="view.AbstractViewOf" name="fichierJs" file="<%validatedFilename%>"%>
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

   	/**
	 * Cette méthode permet de calculer la taille du champ de la propriété
	 * par default cette méthode retourne 150.
	 */
	function calculatePropertySize() {
		// TODO - completer la methode pour qu'elle retourne la taille du champs en fonction
		// de son type ou de l'information qu'elle affichera.
		return 150;
	}

	function load() {
	    var reader = new Ext.data.JsonReader({
	        root: 'records',
	        fields: [
				'id',
		        <%for (getAllSortedAttibutes()){%>
		        {name: '<%getQualifiedName()%>', type: '<%getExtJSType()%>'}<%if (i() <current("AbstractViewOf").getAllSortedAttibutes().nSize() -1){%>, <%}%>
		        <%}%>
	    	]
	    });
	
	    var store = new Ext.data.GroupingStore({
	    	url:getDataSource('json',_TICKET, '/alfresco/service/com/bluexml/side/view/<%getRootContainer().name%>/<%name%>'),
	        autoLoad:true,
	        reader:reader,
	        groupField:'<%getAllSortedAttibutes().nGet(0).getQualifiedName()%>'
	    });
	
	    // create the Grid
	    var grid = new Ext.grid.GridPanel({
	        store: store,
	        columns: [
	            {id:'id',header: 'Identifier', width: 160, sortable: true, dataIndex: 'id', hidden:true, groupable: false},
	            <%for (getAllSortedAttibutes()){%>
		        {id:'<%getQualifiedName()%>',header: '<%name%>', width: calculatePropertySize(), sortable: true, hidden: false, dataIndex: '<%getQualifiedName()%>'}<%if (i() < current("AbstractViewOf").getAllSortedAttibutes().nSize() -1){%>, <%}%>
		        <%}%>
	        ],
	        view: new Ext.grid.GroupingView({
	            forceFit:true,
	            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
	        }),
	        stripeRows: true,
	        autoExpandColumn: false,
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
	    grid.render('<%name%>_grid-example-grouping');
	}
	
	loadWithAuthentication(load);
});
