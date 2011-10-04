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