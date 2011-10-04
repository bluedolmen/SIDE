<% metamodel http://www.kerblue.org/class/1.0 
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.generator.alfresco.api.templates.extJS.services.extJS
import com.bluexml.side.clazz.service.alfresco.CommonServices 
import com.bluexml.side.clazz.service.alfresco.AttributeServices 
import com.bluexml.side.clazz.service.alfresco.AssociationServices 
%> 
<%script type="clazz.Clazz" name="validatedFilename"%> 
<%getProperty("extJSProject")%>/library/<%name%>/extJs/json-paging.js
<%script type="clazz.Clazz" name="fichierJs" file="<%validatedFilename%>"%> 
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
	    	url:getDataSource('json',_TICKET, '/alfresco/service/com/bluexml/side/contentType/<%getRootContainer().name%>/<%getQualifiedName()%>'),
	        fields: [
	           'id',
				<%for (getAllSortedAttibutes()){%>
		        {name: '<%getQualifiedName()%>', type: '<%getExtJSType()%>'}<%if (i() <current("Clazz").getAllSortedAttibutes().nSize() -1){%>, <%}%>
		        <%}%>
	        ],
	        root: 'records',
	        totalProperty: 'totalCount'
	    });
	
	    // create the Grid
	    var grid = new Ext.grid.GridPanel({
	        store: store,
	        columns: [
	        	<%for (getClassAndAspectAttributes()){%>
		        {id:'<%name%>',header: '<%name%>', width: calculatePropertySize(), sortable: true, dataIndex: '<%getQualifiedName()%>'}<%if (i() < current("Clazz").getClassAndAspectAttributes().nSize() -1){%>, <%}%>
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
					                '<p><%for (getClassAndAspectAttributes()){%><%name%> : {<%i()%>}<br/><%}%></p>',
					                <%for (getClassAndAspectAttributes()){%>record.data._<%name%> <%if (i() < current("Clazz").getClassAndAspectAttributes().nSize() -1){%>, <%}%><%}%>);
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
	    grid.render('<%name%>_grid-example-paging');
	    
	    store.load({params:{start:0, limit:25}});
	}
	
	loadWithAuthentication(load);
});