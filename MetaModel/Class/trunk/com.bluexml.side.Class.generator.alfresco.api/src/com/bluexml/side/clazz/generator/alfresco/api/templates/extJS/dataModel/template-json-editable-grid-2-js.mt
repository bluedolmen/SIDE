<% metamodel http://www.kerblue.org/class/1.0 
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.extJS.services.extJS
import com.bluexml.side.clazz.service.alfresco.ClassServices 
import com.bluexml.side.clazz.service.alfresco.CommonServices 
import com.bluexml.side.clazz.service.alfresco.AttributeServices 
import com.bluexml.side.clazz.service.alfresco.AssociationServices 
%> 
<%script type="clazz.Clazz" name="validatedFilename"%> 
<%getProperty("extJSProject")%>/library/<%name%>/extJs/json-editable-grid-2.js
<%script type="clazz.Clazz" name="fichierJs" file="<%validatedFilename%>"%> 
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
            			<%for (getClassAndAspectAttributes()){%>
				        	"library:<%getQualifiedName()%>":data.record.data._<%name%><%if (i() < current("Clazz").getClassAndAspectAttributes().nSize() -1){%>,<%}%>
				        <%}%>
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
	    	url:getDataSource('json',_TICKET, '/alfresco/service/com/bluexml/side/contentType/<%getRootContainer().name%>/<%getQualifiedName()%>'),
	        autoLoad:true,
	        fields: [
	           'id',
	           <%for (getAllSortedAttibutes()){%>
		        {name: '<%getQualifiedName()%>', type: '<%getExtJSType()%>'}<%if (i() <current("Clazz").getAllSortedAttibutes().nSize() -1){%>, <%}%>
		        <%}%>
	        ],
	        root: 'records'
	    });
		
		<%for (getRootContainer().filter("clazz.Model").getAllEnumerations()){%>
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
	        plugins: [editor],
	        columns: [
				<%for (getClassAndAspectAttributes()){%>
		        {
		        	id: '<%getQualifiedName()%>', 
		        	header: '<%name%>', 
		        	width: 150, 
		        	sortable: true, 
		        	dataIndex: '<%getQualifiedName()%>', 
		        	editor: <%getExtJSEditor()%>
		        }<%if (i() < current("Clazz").getClassAndAspectAttributes().nSize() -1){%>, <%}%>
		        <%}%> 
	            
	        ],
	        stripeRows: true,
	        autoExpandColumn: false,
	        height: 350,
	        width: '100%',
	        id: 'grid',
	        title: 'Editable Grid'
	    });
	    
	    // render the grid to the specified div in the page
	    grid.render('<%name%>_grid-example-json-2');
	}
	
	loadWithAuthentication(load);
});
