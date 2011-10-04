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