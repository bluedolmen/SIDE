<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%script type="view.FacetMap" name="validatedFilename"%>
<%if (getInnerView().filter("DataTable")){%>
	./webapps/facetmap/xsl/display/includes/xml-grid_<%name%>.js
<%}%>
<%script type="view.FacetMap" name="rightnavGenerator"  file="<%validatedFilename%>" %>

Ext.onReady(function(){
var doc = document.getElementById("data");
    // create the Data Store
    var store = new Ext.data.Store({
    	proxy: new Ext.data.MemoryProxy(doc),

        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'id',
               totalRecords: '@total'
           }, [
               // set up the fields mapping into the xml doc
               // The first needs mapping, the others are very basic
               <%for (getInnerView().getFields()){%>
               	'<%mapTo.filter("clazz.Attribute").getNamedModelElementQName()%>'<%if (i() < current("FacetMap").getInnerView().getFields().nSize() -1){%>,<%}%>
               <%}%>
           ])
    });

    // create the grid
    var grid = new Ext.grid.GridPanel({
    	id : '<%name%>',
        store: store,
        columns: [
        <%for (getInnerView().getFields()){%>
           	{header: "<%mapTo.filter("clazz.Attribute").getLabel()%>", width: 160, dataIndex: '<%mapTo.filter("clazz.Attribute").getNamedModelElementQName()%>', sortable: true}<%if (i() < current("FacetMap").getInnerView().getFields().nSize() -1){%>,<%}%>
        <%}%>
        ],
        renderTo:'extjs-grid',
        width:'auto',
        height:200
    });
	var menu = new Ext.menu.Menu( {
		id : 'mainMenu',
		style : {
			overflow : 'visible' // For the Combo popup
	},
	items : [
	// stick any markup in a menu
			'<b class="menu-title">Row Actions</b>', {
				icon : 'xsl/display/icons/eye.png',
				text : 'View',
				group : 'theme',
				handler : onItemCheck

			}, {
				icon : 'xsl/display/icons/edit.png',
				text : 'Edit',
				group : 'theme',
				handler : onItemCheck
			}, {
				icon : 'xsl/display/icons/disk.png',
				text : 'Download',
				group : 'theme',
				handler : onItemCheck
			} ]
	});

	// attach extJs contextMenu to cellcontextmenu event on grid object
	Ext.getCmp('<%name%>').on('cellcontextmenu',
			function(grid, rowIndex, index, event) {
				var rowSelected = grid.getSelectionModel().getSelected();
				if (rowSelected) {
					menu.showAt(event.getXY());
				}
			});

	// disable browser context menu
	Ext.getBody().on("contextmenu", Ext.emptyFn, null, {
		preventDefault : true
	});
	
	function onItemCheck(menuItem, event) {
			var item = menuItem.text;
			var menu = Ext.getCmp('mainMenu');
			var grid = Ext.getCmp('<%name%>');
			var id = "";
			var rowSelected = grid.getSelectionModel().getSelected();
			if (rowSelected) {
				id = rowSelected.id;
			} else {
				// must use another way to get id
			}

			if (id != "") {
				// get window to relocate
				var target = window.parent;
				if (!target) {
					target = window;
				}
				if (item == "Edit") {
					target.location = "../share/page/site/demo/edit-metadata?nodeRef=workspace://SpacesStore/"
							+ id;
				} else if (item == "View") {
					target.location = "/share/page/site/demo/document-details?nodeRef=workspace://SpacesStore/"
							+ id;
				} else if (item == "Download") {
					target.location = "/share/proxy/alfresco/api/node/content/workspace/SpacesStore/"
							+ id + "/?a=true";
				}

			}
		}
    store.load();
});
