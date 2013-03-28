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
<%if (eContainer() == getRootContainer()){%><%getProperty("extJSProject")%>/library/<%name%>/extJs/json-simple-grid.html<%}%>
<%script type="view.AbstractViewOf" name="fichierJs" file="<%validatedFilename%>"%> 
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- page specific -->
    <link rel="stylesheet" type="text/css" href="library/examples.css" />
    <link rel="stylesheet" type="text/css" href="library/grid-examples.css" />

    <style type=text/css>
        /* style rows on mouseover */
        .x-grid3-row-over .x-grid3-cell-inner {
            font-weight: bold;
        }
    </style>

    <!-- page specific -->
    <script type="text/javascript" src="./library/<%name%>/extJs/json-simple-grid.js"></script>

</head>
<body>
    <div id="<%name%>_grid-example"></div>
</body>
</html>
