<% metamodel http://www.kerblue.org/view/1.0 
import com.bluexml.side.view.generator.alfresco.templates.services.common
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.templates.extJS.services.extJS
import com.bluexml.side.view.generator.alfresco.extension.sideenterprise.templates.extJS.services.classes
%> 
<%script type="view.AbstractViewOf" name="validatedFilename"%> 
<%if (eContainer() == getRootContainer()){%><%getProperty("extJSProject")%>/library/<%name%>/dojo/simple-grid.js<%}%>
<%script type="view.AbstractViewOf" name="fichierJs" file="<%validatedFilename%>"%> 
dojo.require("dojox.grid.DataGrid");
dojo.require("dojox.data.XmlStore");

dojo.addOnLoad(function() {

	function load() {
		// our test data store for this example:
		var store = new dojox.data.XmlStore({
			url:getDataSource('xml',_TICKET, '/alfresco/service/com/bluexml/side/view/<%getRootContainer().name%>/<%name%>'),
			rootItem: 'item',
			allowNoTrailingSlash: true
		});
		
		// set the layout structure:
		var layout = [
		<%for (getFields()){%>
	        <%if (path != null){%>
		        <%if (path != null && path.nSize() == 1){%>
        			<%for (path.filter("clazz.Association")){%>
        			<%getAssociationEnd(current("AbstractViewOf").viewOf.filter("clazz.Clazz")).put("assoEnd")%>
			{
				field: '<%getAssociationQName(get("assoEnd"))%>',
				name: '(<%name%>) <%current("Field").mapTo.filter("clazz.Attribute").getLabel()%>',
				width: '150px',
				formatter: function(item) {
					var label = "";
					var els = item.element.getElementsByTagName("target");
					for (var x=0;x<els.length;x++) {
						var toto = els[x];
						var noderef = toto.getElementsByTagName("nodeRef")[0].textContent;
						var prop  = toto.getElementsByTagName("<%current("Field").mapTo.filter("clazz.Attribute").getQualifiedName()%>")[0].textContent;
						label+=prop;
						if (x < els.length -1) {
							label+=", ";	
						}
					}
					return label;
				}
			}
					<%}%>
				<%}%>
			<%}else{%>{
			field: '<%current("Field").mapTo.filter("clazz.Attribute").getQualifiedName()%>',
			width: '150px',
			name: '<%current("Field").mapTo.filter("clazz.Attribute").getLabel()%>',
			formatter: function(item) { return item.toString();}
		}<%}%><%if (i() <current("AbstractViewOf").getFields().nSize() -1){%>, <%}%>
			    <%}%>
		];
		
		// create a new grid:
		var grid = new dojox.grid.DataGrid({
			store: store,
			clientSort: true,
			rowSelector: '20px',
			structure: layout
		},document.createElement('div'));
		
		// append the new grid to the div "gridContainer4":
		dojo.byId("gridContainer").appendChild(grid.domNode);
		
		// Call startup, in order to render the grid:
		grid.startup();
	}
		
	loadWithAuthentication(load);
});