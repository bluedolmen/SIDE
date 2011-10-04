<% metamodel http://www.kerblue.org/view/1.0 

%> 
<%script type="view.ViewCollection" name="validatedFilename"%> 
<%getProperty("extJSProject")%>/tree-data.json
<%script type="view.ViewCollection" name="alfrescoGenerator" file="<%validatedFilename%>"%>
[{
    text:'Data',
    children:[
	<%for (views){%>
		{
			text: '<%name%>',
			children:[
			{
				text:'ExtJs',
				children:[
			    {
			        text:'<%name%> - Simple grid',
			        id:'<%name%>/extJs/json-simple-grid',
			        leaf:true
			    },
			    {
			        text:'<%name%> - Editable grid - version 1',
			        id:'<%name%>/extJs/json-editable-grid-1',
			        leaf:true
			    },
			    {
			        text:'<%name%> - Editable grid - version 2',
			        id:'<%name%>/extJs/json-editable-grid-2',
			        leaf:true
			    },
			    {
			        text:'<%name%> - Paging',
			        id:'<%name%>/extJs/json-paging',
			        leaf:true
			    },
			    {
			        text:'<%name%> - Grouping',
			        id:'<%name%>/extJs/json-grouping',
			        leaf:true
			    }]
			},
			{
				text:'Dojo',
				children:[
				{
		        	text:'<%name%> - Simple grid',
		        	id:'<%name%>/dojo/simple-grid',
		        	leaf:true
		    	}]
			}
			
			]
		}<%if (i() < current("ViewCollection").getAllViews().nSize() -1){%>, <%}%>
	<%}%>
    ]
},
{
    text:'Other sample',
    children:[{
        text:'Tree browser',
        id:'tree-grid',
        leaf:true
    },{
        text:'Tree browser with preview',
        id:'tree-grid-preview',
        leaf:true
    },{
        text:'Create new HTML content',
        id:'upload',
        leaf:true
    },{
        text:'Google Map',
        id:'gmap',
        leaf:true
    }]
}]