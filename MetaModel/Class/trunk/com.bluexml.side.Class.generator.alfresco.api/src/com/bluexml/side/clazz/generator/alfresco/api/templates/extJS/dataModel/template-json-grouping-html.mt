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
<%script type="clazz.Clazz" name="validatedFilename"%> 
<%getProperty("extJSProject")%>/library/<%name%>/extJs/json-grouping.html

<%script type="clazz.Clazz" name="fichierHtml" file="<%validatedFilename%>"%>
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
        .icon-clear-group {
            background-image:url(./css/images/control_rewind.png) !important;
        }
    </style>

    <!-- page specific -->
    <script type="text/javascript" src="./library/<%name%>/extJs/json-grouping.js"></script>

</head>
<body>
    <div id="<%name%>_grid-example-grouping"></div>
</body>
</html>
