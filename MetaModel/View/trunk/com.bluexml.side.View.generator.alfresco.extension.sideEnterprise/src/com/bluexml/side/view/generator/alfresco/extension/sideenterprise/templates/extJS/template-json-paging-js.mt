<% metamodel http://www.kerblue.org/view/1.0 
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.extJS.services.extJS
import com.bluexml.side.view.generator.alfresco.extension.sideenterprise.templates.extJS.services.classes
%> 
<%script type="view.AbstractDataTable" name="validatedFilename"%> 
<%if (eContainer() == getRootContainer()){%><%getProperty("extJSProject")%>/library/<%name%>/extJs/json-paging.js<%}%>
<%script type="view.AbstractDataTable" name="fichierJs" file="<%validatedFilename%>"%>
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
	    var store = new Ext.data.JsonStore({
	    	url:getDataSource('json',_TICKET, '/alfresco/service/com/bluexml/side/view/<%getRootContainer().name%>/<%name%>'),
	        fields: [
	           'id',
				<%for (getAllSortedAttibutes()){%>
		        {name: '<%getQualifiedName()%>', type: '<%getExtJSType()%>'}<%if (i() <current("AbstractViewOf").getAllSortedAttibutes().nSize() -1){%>, <%}%>
		        <%}%>
	        ],
	        root: 'records',
	        totalProperty: 'totalCount'
	    });
	
	    // create the Grid
	    var grid = new Ext.grid.GridPanel({
	        store: store,
	        columns: [
	        	<%for (getAllSortedAttibutes()){%>
		        {id:'<%name%>',header: '<%name%>', width: calculatePropertySize(), sortable: true, dataIndex: '<%getQualifiedName()%>'}<%if (i() < current("AbstractViewOf").getAllSortedAttibutes().nSize() -1){%>, <%}%>
		        <%}%>
	        ],
	        stripeRows: true,
	        autoExpandColumn: false,
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
					                '<p><%for (getAllSortedAttibutes()){%><%name%> : {<%i()%>}<br/><%}%></p>',
					                <%for (getAllSortedAttibutes()){%>record.data._<%name%> <%if (i() < current("AbstractViewOf").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>);
	                    return 'x-grid3-row-expanded';
	                }
	                return 'x-grid3-row-collapsed';
	            }
	        },
	
	        // paging bar on the bottom
	        bbar: new Ext.PagingToolbar({
	            pageSize: <%getMaxResults()%>,
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
	    grid.render('<%name%>_grid-example-paging');
	    
	    store.load({params:{start:0, limit:<%getMaxResults()%>}});
	}
	
	loadWithAuthentication(load);
});