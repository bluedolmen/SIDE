<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<% metamodel http://www.kerblue.org/class/1.0 
import templates.servicesTemplates.Common 
import com.bluexml.side.clazz.service.alfresco.ClassServices 
import com.bluexml.side.clazz.service.alfresco.CommonServices 
import com.bluexml.side.clazz.service.alfresco.AttributeServices 
import com.bluexml.side.clazz.service.alfresco.AssociationServices 
%> 
<%script type="clazz.ClassPackage" name="validatedFilename"%> 
<%getProperty("extJSProject")%>/tree-data.json
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
[{
    text:'Data',
    children:[
	<%for (getAllClasses()){%>
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
			}]
		}<%if (i() < eContainer().getAllClasses().nSize() -1){%>, <%}%>
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
