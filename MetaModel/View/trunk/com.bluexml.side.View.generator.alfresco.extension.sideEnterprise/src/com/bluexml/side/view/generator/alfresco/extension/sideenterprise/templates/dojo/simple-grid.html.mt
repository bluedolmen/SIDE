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
<% metamodel http://www.kerblue.org/view/1.0 

%> 
<%script type="view.AbstractViewOf" name="validatedFilename"%> 
<%if (eContainer() == getRootContainer()){%><%getProperty("extJSProject")%>/library/<%name%>/dojo/simple-grid.html<%}%>
<%script type="view.AbstractViewOf" name="fichierJs" file="<%validatedFilename%>"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html dir="ltr">

    <head>
        <link rel="stylesheet" type="text/css" href="dojo-release-1.4.3/dijit/themes/tundra/tundra.css"/>
        <style type="text/css">
            body, html { font-family:helvetica,arial,sans-serif; font-size:90%; }
        </style>
        <style type="text/css">
            @import "./library/dojo/Grid.css";
            .dojoxGrid table {
            	margin: 0;
            } 
            html, body {
            	width: 100%;
            	height: 100%;
        		margin: 0;
        	}
        </style>
    
    </head>
    
    <body class="tundra">
        <div id="gridContainer" style="width: 100%; height: 100%;">
        </div>
    </body>

    <script type="text/javascript" src="./library/<%name%>/dojo/simple-grid.js"></script>

    <script type="text/javascript">
        dojo.addOnLoad(function() {
            if (document.pub) {
                document.pub();
            }
        });
    </script>

</html>
