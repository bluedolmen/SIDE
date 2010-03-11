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
/*!
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
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
        store: store,
        columns: [
        <%for (getInnerView().getFields()){%>
           	{header: "<%mapTo.filter("clazz.Attribute").getLabel()%>", width: 160, dataIndex: '<%mapTo.filter("clazz.Attribute").getNamedModelElementQName()%>', sortable: true}<%if (i() < current("FacetMap").getInnerView().getFields().nSize() -1){%>,<%}%>
        <%}%>
        ],
        renderTo:'example-grid',
        width:'auto',
        height:200
    });

    store.load();
});
