<#include "/org/alfresco/include/alfresco-template.ftl"/>
<@templateHeader>
   <script type="text/javascript">//<![CDATA[
		function refreshGrid() {
		
			var _g = Ext.get('grid');
			if (_g != null) {
				var el = _g.dom;
				el.parentNode.removeChild(el);
			}
		
			var viewName = document.getElementById('viewName').value;
			var webscriptUrl = document.getElementById('alfrescoURL').value;
			
			if (webscriptUrl.match("/$") != "/")
				webscriptUrl += "/";
			webscriptUrl += "service/com/bluexml/side/view/"+viewName;
			
			Ext.Ajax.request({
				url: webscriptUrl,
				success: function ( result, request ) {
							var jsonData = Ext.util.JSON.decode(result.responseText);
							if (jsonData['records'] != null && jsonData['records'].length > 0) {
								var item = jsonData['records'][0];
								var webscriptFields = new Array();
								for (var f in item)
									webscriptFields[webscriptFields.length] = f;
								loadGrid(webscriptFields);
							}
		                 },
				failure: function ( result, request ) {
							alert("Problem to get data from this view...");
		                 }
			});
			
			function loadGrid(webscriptFields) {
		
				var myReader = new Ext.data.JsonReader({
			        fields: webscriptFields,
			        root: 'records'
				});
			
			    var store = new Ext.data.GroupingStore({
			    	url:webscriptUrl,
			        autoLoad:true,
			        reader:myReader
			    });
			    
			    var columns = new Array();
			    for (var i = 0; i < webscriptFields.length; i++) {
					var o = new Object;
					o.id = webscriptFields[i];
					o.dataIndex = webscriptFields[i];
					o.header = webscriptFields[i];
					
					if (webscriptFields[i].match("^_") != "_")
						o.hidden = true;
					
					columns[columns.length] = o;
			    }
		
			    var grid = new Ext.grid.GridPanel({
			    	id:'grid',
			        store: store,
			        columns: columns,
			        stripeRows: true,
			        height: 400,
			        width: '100%',
			        title: 'View : '+viewName,
			        view: new Ext.grid.GroupingView({
			            forceFit:true,
			            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
			        })       
			    });
			    
			    // render the grid to the specified div in the page
			    grid.render('grid-example');
			}
		};
   //]]></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>extJS Grid</title>

    <!-- ** CSS ** -->
    <link rel="stylesheet" type="text/css" href="${page.url.context}/ext-3.1.1/resources/css/ext-all.css" />

    <style type=text/css>
        /* style rows on mouseover */
        .x-grid3-row-over .x-grid3-cell-inner {
            font-weight: bold;
        }
        
        .box {
        	font-family: Verdana;
        	font-size: smaller;
        	padding: 10px;
        }
        
        .box input{
        	width: 300px;
        }
    </style>

    <!-- ** Javascript ** -->
    <!-- ExtJS library: base/adapter -->
    <script type="text/javascript" src="${url.context}/ext-3.1.1/adapter/ext/ext-base.js"></script>

    <!-- ExtJS library: all widgets -->
    <script type="text/javascript" src="${url.context}/ext-3.1.1/ext-all.js"></script>
</@>

<@templateBody>
   <div id="alf-hd">
      <@region id="header" scope="global" protected=true />
   </div>
   <div id="bd">
		<div class="box">
			View's name : <input type="text" id="viewName"/><br/>
			Alfresco URL : <input type="text" id="alfrescoURL" value="http://localhost:8080/alfresco"/><br/>
			<a href="javascript:refreshGrid();">Refresh</a>
		</div>
		<br/>
	    <div id="grid-example"></div>
	</div>
</@>