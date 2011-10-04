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
<%if (eContainer() == getRootContainer()){%><%getProperty("extJSProject")%>/library/<%name%>/extJs/json-editable-grid-1.js<%}%>
<%script type="view.AbstractViewOf" name="fichierJs" file="<%validatedFilename%>"%> 
Ext.onReady(function(){
	
	function convertToISO8601(date) {		
		return date.substring(0,date.length -2) + ":"+ date.substring(date.length -2);
	}
	
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
	    	url:getDataSource('json',_TICKET, '/alfresco/service/com/bluexml/side/view/<%getRootContainer().name%>/<%name%>'),
	        autoLoad:true,
	        fields: [
	        	'id',
		        <%for (getFields()){%>
			        <%if (path != null){%>
				        <%if (path != null && path.nSize() == 1){%>
		        			<%for (path.filter("clazz.Association")){%>
<%getAssociationEnd(current("AbstractViewOf").viewOf.filter("clazz.Clazz")).put("assoEnd")%>{name: '<%current("Field").mapTo.filter("clazz.Attribute").getQualifiedName()%>', type: 'String', mapping: '<%getAssociationQName(get("assoEnd"))%>.toSource()'}<%}%>
			        	<%}%>
			        <%}else{%>
{name: '<%current("Field").mapTo.filter("clazz.Attribute").getQualifiedName()%>', type: '<%current("Field").mapTo.filter("clazz.Attribute").getExtJSType()%>'}<%}%><%if (i() <current("AbstractViewOf").getFields().nSize() -1){%>, <%}%>
			    <%}%>
	        ],
	        root: 'records'
	    });
	
		<%for (getClassModel().getAllEnumerations()){%>
		var combo_enum_<%name%> = new Ext.form.ComboBox({
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
		        data: [<%for (literals){%> ['<%name%>', '<%if (value != null && value !="") {%><%value%><%}else{%><%name%><%}%>']<%if (i() <current("Enumeration").literals.nSize() -1){%>,<%}%><%}%> ]
		    }),
		    valueField: 'id',
		    displayField: 'displayText'
		});
		<%}%>
	
		
	
	    // create the Grid
	    var grid = new Ext.grid.EditorGridPanel({
	        store: store,
	        columns: [
	            {id:'id',header: 'Identifier', width: 160, sortable: true, dataIndex: 'id', hidden:true},
		        <%for (getFields()){%>
		        <%for (mapTo.filter("clazz.Attribute")){%>{
		        	id: '<%getQualifiedName()%>', 
		        	header: '<%if (current("Field").path != null){%>(<%current("Field").path.name%>) <%}%><%name%>', 
		        	width: 150, 
		        	sortable: true, 
		        	dataIndex: '<%getQualifiedName()%>', 
		        	editor: <%getExtJSEditor()%>,
		        	<%if (current("Field").path != null){%>
		        	renderer : function(val, meta, record) {
		        		var display ="";
		        		var obj = eval('('+val+')');
		        		for (var i=0;i<obj.length;i++) {
		        			var nodeRef = obj[i].nodeRef;
		        			var <%getQualifiedName()%> = obj[i].<%getQualifiedName()%>;
		        			display += <%getQualifiedName()%>;
		        			if (i < obj.length -1) {
		        				display+=", "
		        			}
		        		}
		        		return display;
		        	}
		        	<%}%>
		        }<%}%><%if (i() < current("AbstractViewOf").getFields().nSize() -1){%>, <%}%>
		        <%}%>
	        ],
	        stripeRows: true,
	        autoExpandColumn: false,
	        height: 350,
	        width: '100%',
	        id: 'grid',
	        title: 'Editable Grid',
			listeners: {
				'afteredit': function(data) {
	            	var _params = {"properties":{}};
	
					<%for (getAllSortedAttibutes()){%>
					if (data.record.data.<%getQualifiedName()%> != "") {
						_params.properties["<%current("AbstractViewOf").getClassModel().name%>:<%getQualifiedName()%>"] = <%if (typ.toString().equalsIgnoreCase("Date")){%>convertToISO8601(<%}%>data.record.data.<%getQualifiedName()%><%if (typ.toString().equalsIgnoreCase("Date")){%>)<%}%>;
					}
					<%}%>
					
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
	    grid.render('<%name%>_grid-example-json-1');
	}
	
	loadWithAuthentication(load);
});