<% metamodel http://www.kerblue.org/view/1.0 

%> 
<%script type="view.AbstractViewOf" name="validatedFilename"%> 
<%if (eContainer() == getRootContainer()){%><%getProperty("extJSProject")%>/library/<%name%>/extJs/json-grouping.html<%}%>
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