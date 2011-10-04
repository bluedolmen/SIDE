<% metamodel http://www.kerblue.org/view/1.0 
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.extJS.services.extJS
import com.bluexml.side.view.generator.alfresco.extension.sideenterprise.templates.extJS.services.classes
%> 
<%script type="view.AbstractViewOf" name="validatedFilename"%> 
<%if (eContainer() == getRootContainer()){%><%getProperty("extJSProject")%>/library/<%name%>/extJs/json-simple-grid.js<%}%>
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
	    var store = new Ext.data.JsonStore({
	    	url:getDataSource('json',_TICKET, '/alfresco/service/com/bluexml/side/view/<%getRootContainer().name%>/<%name%>'),
	        autoLoad:true,
	        fields: [
	        	'id',
		        <%for (getAllSortedAttibutes){%>
		        {name: '<%getQualifiedName()%>', type: '<%getExtJSType()%>'}<%if (i() < current("AbstractViewOf").getAllSortedAttibutes().nSize() -1){%>, <%}%>
		        <%}%> 
		    ],
	        root: 'records'
	    });
		    
	    // create the Grid
	    var grid = new Ext.grid.GridPanel({
	        store: store,
	        columns: [
	        	{id:'id',header: 'Identifier', width: 160, sortable: true, dataIndex: 'id', hidden:true},
		        <%for (getAllSortedAttibutes()){%>
		        {id:'<%name%>',header: '<%name%>', width: calculatePropertySize(), sortable: true, dataIndex: '<%getQualifiedName()%>'}<%if (i() < current("AbstractViewOf").getAllSortedAttibutes().nSize() -1){%>, <%}%>
		        <%}%> 
	        ],
	        stripeRows: true,
	        autoExpandColumn: false,
	        height: 350,
	        width: '100%',
	        title: 'Simple Grid'     
	    });
	    
	    // render the grid to the specified div in the page
	    grid.render('<%name%>_grid-example');
	}
	
	loadWithAuthentication(load);
})