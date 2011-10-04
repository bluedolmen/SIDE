<% metamodel http://www.kerblue.org/class/1.0 
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices 
import com.bluexml.side.clazz.service.alfresco.CommonServices 
import com.bluexml.side.clazz.service.alfresco.AttributeServices 
import com.bluexml.side.clazz.service.alfresco.AssociationServices 
%> 
<%script type="clazz.Clazz" name="validatedFilename"%> 
<%getProperty("extJSProject")%>/library/<%name%>/extJs/json-editable-grid-2.html
<%script type="clazz.Clazz" name="fichierJs" file="<%validatedFilename%>"%> 
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
    <script type="text/javascript" src="./library/RowEditor.js"></script>
    <script type="text/javascript" src="./library/<%name%>/extJs/json-editable-grid-2.js"></script>

</head>
<body>
    <div id="<%name%>_grid-example-json-2"></div>
</body>
</html>